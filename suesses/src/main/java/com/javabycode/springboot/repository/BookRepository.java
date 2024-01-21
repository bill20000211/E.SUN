package com.javabycode.springboot.repository;

import com.javabycode.springboot.model.inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<inventory, Long> {
    
    @Query(value = "SELECT * FROM inventory WHERE inventory_id = :inventoryId", nativeQuery = true)
    inventory findByinventory_id(@Param("inventoryId") int inventoryid);
}
