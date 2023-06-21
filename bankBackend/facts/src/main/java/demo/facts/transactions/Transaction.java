package demo.facts.transactions;

import demo.facts.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "transactions")
@Table(name = "transactions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "sender_bank_account_id", nullable = false)
    private BankAccount senderAccount;
    @ManyToOne
    @JoinColumn(name = "receiver_bank_account_id", nullable = false)
    private BankAccount receiverAccount;
    private long amount;
    private double locationLongitude;
    private double locationLatitude;
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;
    private boolean fraudulentWarning;
    private String fraudulentWarningMessage;
}
