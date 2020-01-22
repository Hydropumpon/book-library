package com.example.library.repository;

import com.example.library.model.Borrowed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BorrowedRepository extends JpaRepository<Borrowed, Long> {
    List<Borrowed> getAllByReturnDateIsNull();

    Optional<Borrowed> findByIdAndReturnDateIsNull(Long id);

    List<Borrowed> getAllByReturnDateIsNullAndReturnTillDateLessThan(LocalDate localDate);
}
