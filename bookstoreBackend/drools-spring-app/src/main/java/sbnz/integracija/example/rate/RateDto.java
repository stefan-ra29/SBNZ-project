package sbnz.integracija.example.rate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RateDto {
    private int rate;
    private int bookId;
    private int userId;
}
