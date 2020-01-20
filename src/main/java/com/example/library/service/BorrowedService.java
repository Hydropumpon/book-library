package com.example.library.service;

import com.example.library.model.Borrowed;

import java.util.List;

public interface BorrowedService {
    Borrowed borrowBook(Borrowed borrowed);

    Borrowed returnBook(Long borrowedId);

    List<Borrowed> getActiveBorrows();

    List<Borrowed> getExpiredBorrows();

    List<Borrowed> getAllBorrows();

    Borrowed getBorrowInfo(Long borrowId);
}
