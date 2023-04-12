package sbnz.integracija.example.book.dto;

import demo.facts.Book;
import demo.facts.BookCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDisplayDTO {
    private  int id;

    private String name;
    private String author;
    private double price;
    private String category;

    public BookDisplayDTO(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        switch (book.getCategory()) {
            case FICTION:
                this.category = "Fiction";
                break;
            case HISTORY:
                this.category = "History";
                break;
            case CHILDREN:
                this.category = "Childrens book";
                break;
            case EDUCATION:
                this.category = "Education";
                break;
            case PHILOSOPHY:
                this.category = "Philosophy";
                break;
        }
    }
}
