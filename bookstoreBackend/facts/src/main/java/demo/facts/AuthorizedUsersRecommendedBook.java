package demo.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class AuthorizedUsersRecommendedBook implements Serializable {

    private List<Book> recommendedBooks;

    public AuthorizedUsersRecommendedBook() {
        this.recommendedBooks = new ArrayList<>();
    }

    public void addBook(Book book){
        if(this.recommendedBooks.size() < 10){
            this.recommendedBooks.add(book);
        }else{
            Book minRatingBook = this.recommendedBooks
                    .stream()
                    .min(Comparator.comparingDouble(Book::getAverageRating)).get();
            if(minRatingBook.getAverageRating() < book.getAverageRating()){
                this.recommendedBooks.add(book);
                this.recommendedBooks.remove(minRatingBook);

            }
        }
    }

    public void removeBook(Book book){
        this.recommendedBooks.remove(book);
    }

}
