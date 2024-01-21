package com.javabycode.springboot.Presentation;

import com.javabycode.springboot.model.users;
import com.javabycode.springboot.model.books;
import com.javabycode.springboot.model.borrowing_record;
import com.javabycode.springboot.model.inventory;
import com.javabycode.springboot.service.BookService;
import com.javabycode.springboot.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties.Reactive.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book_inventory")
    public String showBookList(Model model, HttpSession session) {

        List <inventory> allBooks = bookService.queryAllInventory();
        
        model.addAttribute("allBooks", allBooks);
        // model.addAttribute("loggedInUser", ((users) session.getAttribute("loggedInUser")).getuser_name());
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));

        return "book_inventory";
    }

    @PostMapping("/book_borrow")
    public String processBookList(Model model, HttpSession session, @RequestParam("inventory_id") int inventory_id) {

        logger.info("borrowing book with inventory_id: {}", inventory_id);
        logger.info("borrowing book with user: {}", ((users) session.getAttribute("loggedInUser")).getuser_name());

        bookService.borrowBook(inventory_id, (users) session.getAttribute("loggedInUser"));

        List <inventory> allBooks = bookService.queryAllInventory();
        
        model.addAttribute("allBooks", allBooks);
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));

        return "book_inventory";
    }

    @GetMapping("/book_borrow_list")
    public String showBookBorrowList(Model model, HttpSession session) {

        List <borrowing_record> allRecords = bookService.queryAllBorrow((users) session.getAttribute("loggedInUser"));
        
        model.addAttribute("allRecords", allRecords);
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));

        return "book_borrow_list";
    }

    @PostMapping("/book_borrow_list")
    public String processBookBorrowList(Model model, HttpSession session, @RequestParam("record_id") int record_id) {

        bookService.returnBook(record_id);
        List <borrowing_record> allRecords = bookService.queryAllBorrow((users) session.getAttribute("loggedInUser"));
        
        model.addAttribute("allRecords", allRecords);
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));

        return "book_borrow_list";
    }

}
