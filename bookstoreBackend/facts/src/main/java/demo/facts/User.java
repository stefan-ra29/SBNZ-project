package demo.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "users")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String username;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Rate> rates;
    @ManyToMany()
    @JoinTable(name="user_genres", joinColumns = @JoinColumn(name="users_id"), inverseJoinColumns = @JoinColumn(name="genres_id"))
    private List<Genre> genres;
    public double getAverageRate() {
        return rates.stream().mapToInt(Rate::getRate).average().orElse(0);
    }
}
