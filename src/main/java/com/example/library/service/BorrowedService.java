package com.example.library.service;

import com.example.library.model.Borrowed;

import javax.transaction.Transactional;
import java.util.List;

public interface BorrowedService
{
	@Transactional
	Borrowed borrowBook(Borrowed borrowed);

	@Transactional
	Borrowed returnBook(Long borrowedId);

	List<Borrowed> getActiveBorrows();

	List<Borrowed> getExpiredBorrows();

	List<Borrowed> getAllBorrows();
}
