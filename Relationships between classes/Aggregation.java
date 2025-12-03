import java.util.ArrayList;
import java.util.List;

public class Aggregation {
    public static void main(String args[]) {
        //Books created independently. They can exist without Library.
        Book b1 = new Book("Book1", "Author1");
        Book b2 = new Book("Book2", "Author2");
        
        Library library = new Library();
        
        library.addBook(b1);
        library.addBook(b2);
        
        library.displayBooks();
    }
}

class Book {
    String name;
    String author;
    
    Book(String name, String author) {
        this.name = name;
        this.author = author;
    }
}

class Library {
    List<Book> books;   //Library has books (weak ownership). So it is aggregation.
    
    Library() {
         books = new ArrayList<>();
    }
    
    void addBook(Book book) {
        books.add(book);
    }
    
    void displayBooks() {
        for(Book book : books) {
            System.out.println(book.name + " - " + book.author);
        }
    }
}