package demo.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class UserLikedBooksHelper implements Serializable {

    private User activeUser;

    private  List<Book> likedBooks;


    public boolean isItTheDifferentBook(Book book){
        if (this.likedBooks.contains(book)){
            return false;
        }else{
            return true;
        }
    }

    public boolean areBooksSimilar(Book book){
        double similarGrade = 0;
        double differentGrade = 0;
        for(Book book1 : this.likedBooks){
            for (Rate rate: book.getRates()){
                for(Rate rate1 : book1.getRates()){
                    if(rate1.getUser().equals(rate.getUser())){
                        if (Math.abs(rate.getRate() - rate1.getRate()) <= 1) {
                            similarGrade++;
                        }else{
                            differentGrade++;
                        }
                    }
                }

            }
            if(similarGrade/(similarGrade+differentGrade) >= 0.7){
                return true;
            }
        }
        return false;
    }
}
