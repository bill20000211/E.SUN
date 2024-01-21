package com.javabycode.springboot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javabycode.springboot.model.borrowing_record;
import com.javabycode.springboot.model.inventory;
import com.javabycode.springboot.model.users;
import com.javabycode.springboot.repository.BookRepository;
import com.javabycode.springboot.repository.BorrowingRecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;
    private final BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    public BookService(BookRepository bookRepository, BorrowingRecordRepository borrowingRecordRepository) {
        this.bookRepository = bookRepository;
        this.borrowingRecordRepository = borrowingRecordRepository;
    }

    public List<inventory> queryAllInventory() {
        logger.info("Retrieving all Inventory");

        List<inventory> allBooks = bookRepository.findAll();

        logger.info("Retrieved {} Inventory", allBooks.size());

        for (int i = 0; i < allBooks.size(); i++) {
            logger.info("inventory_id: {}", allBooks.get(i).getinventory_id());
            logger.info("isbn: {}", allBooks.get(i).getisbn());
            logger.info("store_time: {}", allBooks.get(i).getstore_time());
            logger.info("status: {}", allBooks.get(i).getstatus());
        }

        return allBooks;
    }

    public void borrowBook(int inventory_id, users user) {
        logger.info("borrowing book with inventory_id: {}", inventory_id);
        logger.info("borrowing book with user: {}", user.getuser_id());

        borrowing_record record = new borrowing_record();

        inventory book = bookRepository.findByinventory_id(inventory_id);

        if (book == null) {
            logger.info("book not found");
            return;
        }

        // 0為已借出，1為未借出
        if (book.getstatus() == "0") {
            logger.info("book already borrowed");
            return;
        }
        else {
            book.setstatus("0");
            bookRepository.save(book);

            record.setuser_id(user.getuser_id());
            record.setinventory_id(inventory_id);
            record.setborrowing_time(LocalDateTime.now().toString());

            borrowingRecordRepository.save(record);

        }
    }

    public List<borrowing_record> queryAllBorrow(users user) {
        logger.info("Retrieving all Inventory");

        int user_id = user.getuser_id();

        List<borrowing_record> allRecords = borrowingRecordRepository.findByUserIdAndReturnTimeIsNull(user_id);

        logger.info("Retrieved {} Inventory", allRecords.size());

        return allRecords;
    }

    public void returnBook(int record_id) {
        logger.info("returning book with record_id: {}", record_id);

        borrowing_record record = borrowingRecordRepository.findByRecordIdAndReturnTimeIsNull(record_id);

        if (record == null) {
            logger.info("record not found");
            return;
        }

        record.setreturn_time(LocalDateTime.now().toString());

        borrowingRecordRepository.save(record);

        inventory book = bookRepository.findByinventory_id(record.getinventory_id());

        book.setstatus("1");

        bookRepository.save(book);
    }
}
