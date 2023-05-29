package demo.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class PiersonCorrelationHelper implements Serializable {

    private User activeUser;
    private List<Book> books;

    public boolean isPiersonRuleSatisfied(Book book) {
        List<User> users = book.getRates().stream().filter(r -> r.getRate() >= 4).map(Rate::getUser)
                .collect(Collectors.toList());
        for (var user : users) {
            if (firstPart(user) / (secondPart() * thirdPart(user)) >= 0.5) {
                return true;
            }
        }
        return false;
    }

    private double firstPart(User user) {
        double ra = this.activeUser.getAverageRate();
        double ru = user.getAverageRate();
        double sum = 0;
        for (var book : books) {
            double rui = user.getRates().stream().filter(r -> r.getBook().getId() == book.getId())
                    .findFirst().orElse(Rate.builder()
                            .rate(1)
                            .build()).getRate();
            double rai = activeUser.getRates().stream().filter(r -> r.getBook().getId() == book.getId())
                    .findFirst().orElse(Rate.builder()
                            .rate(1)
                            .build()).getRate();
            sum += (rai - ra) * (rui - ru);
        }
        return sum;
    }

    private double secondPart() {
        double ra = this.activeUser.getAverageRate();
        double sum = 0;
        for (var book : books) {
            double rai = activeUser.getRates().stream().filter(r -> r.getBook().getId() == book.getId())
                    .findFirst().orElse(Rate.builder()
                            .rate(1)
                            .build()).getRate();
            sum += (rai - ra) * (rai - ra);
        }
        return Math.sqrt(sum);
    }

    private double thirdPart(User user) {
        double ru = user.getAverageRate();
        double sum = 0;
        for (var book : books) {
            double rui = user.getRates().stream().filter(r -> r.getBook().getId() == book.getId())
                    .findFirst().orElse(Rate.builder()
                            .rate(1)
                            .build()).getRate();

            sum += (rui - ru) * (rui - ru);
        }
        return Math.sqrt(sum);
    }

}
