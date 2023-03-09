package org.example.Controller;

import org.example.Model.Book;
import org.example.Service.MockBookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/books")
public class BookController {

    private MockBookService mockBookService;

    public BookController(MockBookService mockBookService){
        this.mockBookService = mockBookService;
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @GetMapping("")
    public List<Book> getBooks(){
        return mockBookService.getList();
    }

    @PostMapping("")
    public void addNewBook(@RequestBody Map<String, String> data){
        Book bookToAdd = new Book(data.get("isbn"),data.get("title"),data.get("author"),data.get("publisher"),data.get("type"));
        mockBookService.addNewBook(bookToAdd);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable long id){
        return mockBookService.getBookById(id);
    }

    @DeleteMapping("/{id}")
    public String removeBookById(@PathVariable long id){
        return mockBookService.removeBookById(id);
    }

    @PutMapping("")
    public void editBookById (@RequestBody Map<String, String> data){
        long id = Long.parseLong(data.get("id"));
        Book bookToEdit = new Book(id,data.get("isbn"),data.get("title"),data.get("author"),data.get("publisher"),data.get("type"));
        mockBookService.editBookById(bookToEdit);
    }

}