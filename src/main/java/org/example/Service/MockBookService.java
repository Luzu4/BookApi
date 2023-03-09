package org.example.Service;

import org.example.Model.Book;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MockBookService {
    private List<Book> list;
    private static Long nextId = 4L;

    public MockBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        list.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
    }


    public List<Book> getList() {
        return list;
    }

    public void addNewBook(Book book) {
        book.setId(nextId);
        nextId++;
        list.add(book);
    }

    public Book getBookById(long id) {
        Book bookToShow = list.stream()
                .filter(book -> book.getId().equals(id))
                .findAny()
                .orElse(null);
        return bookToShow;
    }

    public String removeBookById(long id){
        int sizeBefore = list.size();
        list.removeIf(book ->book.getId().equals(id));
        int sizeAfter = list.size();
        if(sizeBefore != sizeAfter){
            return "Failed";
        }else{
            return "Success";
        }
    }

    public void editBookById(Book bookToEdit){
        long idToEdit = bookToEdit.getId();

        list.removeIf(book ->book.getId().equals(idToEdit));
        list.add(bookToEdit);
        list.sort(Comparator.comparingLong(Book::getId));
    }


}
