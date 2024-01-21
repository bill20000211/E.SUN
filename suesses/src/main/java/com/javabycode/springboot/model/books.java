package com.javabycode.springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private String isbn;
    private String name;
    private String author;
    private String introduction;

    public books() {
    }

    public books(String name, String author, String isbn) {
        super();
        this.name = name;
        this.author = author;
        this.isbn = isbn;
    }

    // Getters and Setters
}

// CREATE TABLE books (
//     isbn VARCHAR(20) PRIMARY KEY,
//     name VARCHAR(255),
//     author VARCHAR(255),
//     introduction TEXT
// );
