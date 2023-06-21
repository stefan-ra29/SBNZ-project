package demo.facts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import demo.facts.transactions.BankAccount;
import demo.facts.transactions.Transaction;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity(name = "users")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String username;
    private String password;
    private UserRole role;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BankAccount> bankAccounts;
    @OneToMany(mappedBy = "sender", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> outboundTransactions;


    public double getAverageOutboundTransactionsAmount() {
        return getOutboundTransactions().stream()
                .mapToLong(Transaction::getAmount)
                .average()
                .orElse(0.0);
    }

    public List<Transaction> getTwoPreviousOutboundTransactions() {
        return getOutboundTransactions().stream()
                .sorted(Comparator.comparing(Transaction::getDateTime).reversed())
                .limit(2)
                .collect(Collectors.toList());
    }

    public Transaction getLastTransaction() {
        Optional<Transaction> lastTransaction = getOutboundTransactions().stream()
                .max(Comparator.comparing(Transaction::getDateTime));

        return lastTransaction.orElse(null);
    }

}
