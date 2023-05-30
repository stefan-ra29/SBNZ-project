package demo.facts;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "genres")
@Table(name = "genres")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private BookCategory genre;
    @ManyToMany(mappedBy = "genres")
    List<User> users;
}
