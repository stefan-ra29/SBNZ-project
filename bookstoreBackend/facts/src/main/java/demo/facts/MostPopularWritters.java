package demo.facts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
@Getter
@Setter
public class MostPopularWritters implements Serializable {

    private Map<String, Long> writters = new HashMap<>();
    private List<Book> books;
    private List<BookCategory> genres;

    public MostPopularWritters() {
        this.writters = new HashMap<>();
        this.books = new ArrayList<>();
        this.genres = new ArrayList<>();
    }

    public MostPopularWritters(List<Book> books, List<BookCategory> genres) {
        this.books = books;
        this.genres = genres;
    }

    public void addWritter(String writter){
        System.out.println("addWriter");
        long numberOfGrades = getWrittersNumberOfGrades(writter);
        if(this.writters.size() < 4){
            this.writters.put(writter, numberOfGrades);
        }else{
            Optional<Map.Entry<String, Long>> minEntry = this.writters.entrySet().stream().min(Map.Entry.comparingByValue());
            Long minValue = minEntry.get().getValue();
            if(minValue < numberOfGrades){
                this.writters.remove(minEntry.get().getKey());
                this.writters.put(writter, numberOfGrades);
            }
        }
    }

    public boolean isWritterInGenre(String author){
        System.out.println("isInGenre");
        List<Book> authorsBooks = books.stream().filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
        for(BookCategory genre : genres){
            List<Book> authorsBooksInGenre =authorsBooks.stream().filter(book -> book.getCategory().equals(genre)).collect(Collectors.toList());
            if((double)authorsBooksInGenre.size() /authorsBooks.size() > 0.3){
                return true;
            }
        }
        return false;
    }

    public long getWrittersNumberOfGrades(String author){
        return books.stream().filter(book -> book.getAuthor().equals(author)).flatMapToInt(b -> IntStream.of(b.getRates().size())).count();

    }

    public boolean containsKey(String writer) {
        return writters.containsKey(writer);
    }
}
