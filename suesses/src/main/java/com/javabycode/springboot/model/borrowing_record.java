package com.javabycode.springboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "borrowing_record")
public class borrowing_record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "record_id")
    private int record_id;
    @Column(name = "user_id")
    private int user_id;
    @Column(name = "inventory_id")
    private int inventory_id;
    @Column(name = "borrowing_time")
    private String borrowing_time;
    @Column(name = "return_time")
    private String return_time;
    
    public borrowing_record() {
    }

    public borrowing_record(int user_id, int inventory_id, String borrowing_time, String return_time) {
        super();
        this.user_id = user_id;
        this.inventory_id = inventory_id;
        this.borrowing_time = borrowing_time;
        this.return_time = return_time;
    }

    public int getrecord_id() {
        return record_id;
    }

    public int getuser_id() {
        return user_id;
    }

    public int getinventory_id() {
        return inventory_id;
    }

    public String getborrowing_time() {
        return borrowing_time;
    }

    public String getreturn_time() {
        return return_time;
    }

    public void setrecord_id(int record_id) {
        this.record_id = record_id;
    }

    public void setuser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setinventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public void setborrowing_time(String borrowing_time) {
        this.borrowing_time = borrowing_time;
    }

    public void setreturn_time(String return_time) {
        this.return_time = return_time;
    }


}


// CREATE TABLE borrowing_record (
//     user_id INT,
//     inventory_id INT,
//     borrowing_time DATETIME,
//     return_time DATETIME,
//     FOREIGN KEY (user_id) REFERENCES users (user_id),
//     FOREIGN KEY (inventory_id) REFERENCES inventory (inventory_id)
// );