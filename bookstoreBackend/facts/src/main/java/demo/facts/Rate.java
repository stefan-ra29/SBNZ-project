package demo.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "rates")
@Table(name = "rates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int rate;
    private int bookId;
    private int userId;

}
