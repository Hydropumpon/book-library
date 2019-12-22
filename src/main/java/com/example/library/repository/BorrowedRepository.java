package com.example.library.repository;

import com.example.library.model.Borrowed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowedRepository extends JpaRepository<Borrowed, Long >
{
}
