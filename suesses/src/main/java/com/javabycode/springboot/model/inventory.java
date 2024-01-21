package com.javabycode.springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class inventory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "inventory_id")
    private int inventory_id;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "store_time")
    private String store_time;
    @Column(name = "status")
    private String status;

    public inventory() {
    }

    public inventory(int inventory_id, String isbn, String store_time, String status) {
        super();
        this.inventory_id = inventory_id;
        this.isbn = isbn;
        this.store_time = store_time;
        this.status = status;
    }

    public int getinventory_id() {
        return inventory_id;
    }

    public String getisbn() {
        return isbn;
    }

    public String getstore_time() {
        return store_time;
    }

    public String getstatus() {
        return status;
    }

    public void setinventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public void setisbn(String isbn) {
        this.isbn = isbn;
    }

    public void setstore_time(String store_time) {
        this.store_time = store_time;
    }

    public void setstatus(String status) {
        this.status = status;
    }

}

// CREATE TABLE inventory (
//     inventory_id INT PRIMARY KEY IDENTITY(1,1),
//     isbn VARCHAR(20) NOT NULL,
//     store_time DATETIME,
//     status VARCHAR(20)
// );
