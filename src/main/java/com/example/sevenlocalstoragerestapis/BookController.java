package com.example.sevenlocalstoragerestapis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private static Logger logger = LoggerFactory.getLogger(BookController.class);

    private HashMap<Integer, Book> bookHashMap = new HashMap();

    // to fetch the bookId from the client and return
    // the details of that particular book
    /*Simple GET method*/
    @GetMapping("/book")
    public Book getBook(@RequestParam(value = "bid", required = false) Integer bookId){
        logger.info("bId - {}", bookId);
        return  bookHashMap.get(bookId);
    }

    /*Path parameter GET one element*/
    @GetMapping("/book/{bookId}")
    public  Book getBookId(@PathVariable("bookId") int bookId){
        logger.info("bId - {}", bookId);
        return  bookHashMap.get(bookId);
    }


    /*GET all books request*/
    @GetMapping("/book/all")
    public List<Book> getBooks(){
        return (List<Book>) bookHashMap.values().stream().collect(Collectors.toList());
    }

    /*GET with requestbody param*/
//    @GetMapping("/insert_book")
//    public void insertBook(@RequestBody Book book, @RequestParam String msg){
//        logger.info("Book coming from the request - {}, msg --{}", book, msg);
//        bookHashMap.put(book.getId(), book);
//    }

    /*GET and POST method in same call - NO RECOMMENDED*/
//    @RequestMapping(value = "/book", method = {RequestMethod.GET, RequestMethod.POST})
//    public Book getBook(@RequestParam("bid") Integer bookId){
//        logger.info("bId - {}", bookId);
//        return  bookHashMap.get(bookId);
//    }

    /*simple POST request*/
    @PostMapping("/book")
    public void insertBook(@RequestBody Book book){
        logger.info("Book coming from the request - {}", book);
        bookHashMap.put(book.getId(), book);
    }

    /*PUT Request*/
    @PutMapping("/book")
    public Book updateBook(@RequestBody Book book, @RequestParam("id") int bookId){
        bookHashMap.put(bookId, book);
        return bookHashMap.get(bookId);
    }

    /*DELETE request*/
    @DeleteMapping("/book")
    public Book deleteBook(@RequestParam("id") int id){
        return bookHashMap.remove(id);
    }

    /*test path variable and parameter*/
    @GetMapping("dummy/{id}")
    public String dummy(@PathVariable("id") int id, @RequestParam("param") int param){
        logger.info("id - {}, param - {}", id, param);
        return "Hello!";
    }

}
