package sbnz.integracija.example.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.integracija.example.transaction.dto.ResponseTransactionDTO;
import sbnz.integracija.example.transaction.dto.SendTransactionDTO;

@RestController
@RequestMapping(path="api/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    private ResponseTransactionDTO sendTransaction(@RequestBody SendTransactionDTO sendTransactionDTO) {
        return new ResponseTransactionDTO(transactionService.sendTransaction(sendTransactionDTO));
    }

    @PostMapping
    @RequestMapping("/without-check")
    private ResponseTransactionDTO sendTransactionWithoutCheck(@RequestBody SendTransactionDTO sendTransactionDTO) {
        return new ResponseTransactionDTO(transactionService.sendTransactionWithoutCheck(sendTransactionDTO));
    }
}
