package demo.facts.transactions;

import demo.facts.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "bankAccounts")
@Table(name = "bankAccounts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int cardExpirationYear;
    private int cardExpirationMonth;
    @Column(unique = true)
    private long cardNumber;
    @Column(unique = true)
    private long accountNumber;
    private int cvvNumber;
    private long balance;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToMany(mappedBy = "receiverAccount", cascade = CascadeType.ALL)
    private List<Transaction> inboundTransactions;
    @OneToMany(mappedBy = "senderAccount", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transaction> outboundTransactions;
}
