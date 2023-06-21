package sbnz.integracija.example.transaction;

import demo.facts.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query(value = "SELECT AVG(amount) from transactions t WHERE t.sender_id = ?1", nativeQuery = true)
    double getAverageTransactionAmountBySenderId(int senderId);
}
