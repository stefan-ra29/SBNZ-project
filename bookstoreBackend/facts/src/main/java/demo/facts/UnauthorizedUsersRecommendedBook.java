package demo.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UnauthorizedUsersRecommendedBook implements Serializable {

    private List<Book> recommendedBooks;

    public UnauthorizedUsersRecommendedBook() {
        this.recommendedBooks = new ArrayList<>();
    }

    public void addBook(Book book){
        this.recommendedBooks.add(book);
    }

    public void removeBook(Book book){
        this.recommendedBooks.remove(book);
    }

    public void removeRandomBook(){
        while(this.recommendedBooks.size() > 10){
            int randomIndex = getRandomNumber(0, recommendedBooks.size() - 1);
            this.recommendedBooks.remove(randomIndex);
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}


