package sbnz.integracija.example.bankAccount;

import demo.facts.transactions.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    Optional<BankAccount> findBankAccountByAccountNumber(long accountNumber);
}
