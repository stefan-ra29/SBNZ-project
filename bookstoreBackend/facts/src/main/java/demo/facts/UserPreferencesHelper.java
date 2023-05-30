package demo.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserPreferencesHelper {

    List<Book> booksInLast6Months;

    public boolean isUserInterestedInAuthor(String author) {
        System.out.println("asd");
        return booksInLast6Months.stream().filter(b -> b.getAuthor().equals(author)).count() >= 3;
    }

    public boolean isUserInterestedInBookCategory(BookCategory category) {
        System.out.println("asd");
        double boughtBooksWithGivenCategory = (double) booksInLast6Months.stream()
                .filter(b -> b.getCategory().equals(category))
                .count();
        return boughtBooksWithGivenCategory / booksInLast6Months.size() >= 0.3;
    }
}
