package demo.facts;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "books")
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String author;
    private double price;
    private BookCategory category;
    private LocalDate addedToStoreDate;
    private LocalDate publishedDate;
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Rate> rates;
    @JsonInclude
    @Transient
    private boolean isNew;
    @JsonInclude
    @Transient
    private boolean isPopular;
    @JsonInclude
    @Transient
    private BookRatingLevel bookRatingLevel;

    public double getAverageRating() {
        if (rates.size() == 0)
            return 0;
        double sum = 0;
        for (Rate rate: rates) {
            sum += rate.getRate();
        }
        return sum / rates.size();
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

}
