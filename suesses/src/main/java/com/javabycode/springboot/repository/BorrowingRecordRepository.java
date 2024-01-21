
package com.javabycode.springboot.repository;

import com.javabycode.springboot.model.borrowing_record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BorrowingRecordRepository extends JpaRepository<borrowing_record, Long>{

    @Query(value = "SELECT * FROM borrowing_record WHERE user_id = :userId AND return_time IS NULL", nativeQuery = true)
    List<borrowing_record> findByUserIdAndReturnTimeIsNull(@Param("userId") int userId);

    @Query(value = "SELECT * FROM borrowing_record WHERE record_id = :recordId AND return_time IS NULL", nativeQuery = true)
    borrowing_record findByRecordIdAndReturnTimeIsNull(@Param("recordId") int recordId);

    
}
