package demo.facts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "rates")
@Table(name = "rates")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Rate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int rate;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
