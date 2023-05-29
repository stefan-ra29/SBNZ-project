package demo.facts;

import java.io.Serializable;
import java.util.*;

public class AuthorizedUsersRecommendedBook implements Serializable {

    private List<Book> recommendedBooks;

    public AuthorizedUsersRecommendedBook(List<BookCategory> userGenres) {
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
