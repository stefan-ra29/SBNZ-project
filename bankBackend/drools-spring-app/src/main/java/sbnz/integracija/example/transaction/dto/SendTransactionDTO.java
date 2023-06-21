package sbnz.integracija.example.transaction.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SendTransactionDTO {
    private long senderCardNumber;
    private int senderCvvNumber;
    private long senderAccountNumber;
    private long receiverAccountNumber;
    private long amount;
    private double locationLatitude;
    private double locationLongitude;
}
