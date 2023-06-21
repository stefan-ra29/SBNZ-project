package sbnz.integracija.example.transaction;

import demo.facts.User;
import demo.facts.transactions.BankAccount;
import demo.facts.transactions.Transaction;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sbnz.integracija.example.bankAccount.BankAccountRepository;
import sbnz.integracija.example.transaction.dto.SendTransactionDTO;
import sbnz.integracija.example.user.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private KieContainer kieContainer;

    @Transactional
    public String sendTransaction(SendTransactionDTO sendTransactionDTO) {
        BankAccount senderBankAccount = bankAccountRepository.findBankAccountByAccountNumber(sendTransactionDTO.getSenderAccountNumber()).orElseThrow();
        BankAccount receiverBankAccount = bankAccountRepository.findBankAccountByAccountNumber(sendTransactionDTO.getReceiverAccountNumber()).orElseThrow();
        User sender = senderBankAccount.getUser();

        Transaction transaction = Transaction.builder()
                .amount(sendTransactionDTO.getAmount())
                .locationLongitude(sendTransactionDTO.getLocationLongitude())
                .locationLatitude(sendTransactionDTO.getLocationLatitude())
                .dateTime(LocalDateTime.now())
                .receiverAccount(receiverBankAccount)
                .senderAccount(senderBankAccount)
                .fraudulentWarning(false)
                .fraudulentWarningMessage("")
                .sender(sender)
                .build();


        String validityMessage = checkValidity(senderBankAccount, sendTransactionDTO);

        System.out.println(transaction.getDateTime().getHour());

        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(transaction);
        kieSession.getAgenda().getAgendaGroup("fraudDetection").setFocus();
        kieSession.fireAllRules();

        if(transaction.isFraudulentWarning()) {
            return transaction.getFraudulentWarningMessage();
        }

        if(validityMessage.equals("Success")) {
            senderBankAccount.setBalance(senderBankAccount.getBalance() - sendTransactionDTO.getAmount());
            receiverBankAccount.setBalance(receiverBankAccount.getBalance() + sendTransactionDTO.getAmount());

            transactionRepository.save(transaction);
        }

        return validityMessage;
    }

    public String checkValidity(BankAccount senderBankAccount, SendTransactionDTO sendTransactionDTO) {

        if(sendTransactionDTO.getSenderCardNumber() != senderBankAccount.getCardNumber())
            return "Invalid sender card number";
        else if(sendTransactionDTO.getSenderCvvNumber() != senderBankAccount.getCvvNumber())
            return "Invalid sender card cvv number";
        else if(senderBankAccount.getBalance() - sendTransactionDTO.getAmount() < 0)
            return "Insufficient account balance";
        else if(senderBankAccount.getCardExpirationYear() != sendTransactionDTO.getCardExpirationYear() ||
                senderBankAccount.getCardExpirationMonth() != sendTransactionDTO.getCardExpirationMonth())
            return  "Invalid sender card expiration date";
        else if(LocalDate.of(senderBankAccount.getCardExpirationYear(), senderBankAccount.getCardExpirationMonth(), 1).isBefore(LocalDate.now()))
            return "The card has expired";

        return "Success";
    }


}
