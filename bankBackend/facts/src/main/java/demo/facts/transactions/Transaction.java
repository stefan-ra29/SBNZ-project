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

    public double distanceInKilometresTo(double longitude, double latitude) {
        double earthRadius = 6371; // Radius of the Earth in kilometers

        double lat1 = Math.toRadians(this.locationLatitude);
        double lon1 = Math.toRadians(this.locationLongitude);
        double lat2 = Math.toRadians(latitude);
        double lon2 = Math.toRadians(longitude);

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = earthRadius * c;
        return distance;
    }
}
