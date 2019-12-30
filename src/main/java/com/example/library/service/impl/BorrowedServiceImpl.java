package com.example.library.service.impl;

import com.example.library.common.exception.ErrorMessage;
import com.example.library.common.exception.NotFoundException;
import com.example.library.common.exception.ServiceErrorCode;
import com.example.library.common.exception.UnavailableException;
import com.example.library.model.Book;
import com.example.library.model.Borrowed;
import com.example.library.model.Customer;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowedRepository;
import com.example.library.repository.CustomerRepository;
import com.example.library.service.BorrowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowedServiceImpl implements BorrowedService
{
	private static int NO_BOOK_AVAILABLE = 0;

	private BorrowedRepository borrowedRepository;

	private BookRepository bookRepository;

	private CustomerRepository customerRepository;

	@Autowired
	public BorrowedServiceImpl(BorrowedRepository borrowedRepository, BookRepository bookRepository,
							   CustomerRepository customerRepository)
	{
		this.borrowedRepository = borrowedRepository;
		this.bookRepository = bookRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	@Transactional
	public Borrowed borrowBook(Borrowed borrowed)
	{
		Optional<Book> book = bookRepository.findById(borrowed.getBook().getId());
		Optional<Customer> customer = customerRepository.findById(borrowed.getCustomer().getId());

		validateBorrowTake(book, customer);
		book.get().setQuantity(book.get().getQuantity() - 1);
		borrowed.setBook(book.get());
		borrowed.setCustomer(customer.get());
		bookRepository.save(book.get());
		return borrowedRepository.save(borrowed);
	}

	@Override
	@Transactional
	public Borrowed returnBook(Long borrowedId)
	{
		Optional<Borrowed> borrowed = borrowedRepository.findById(borrowedId);
		checkBorrowActive(borrowed);
		Optional<Book> book = bookRepository.findById(borrowed.get().getBook().getId());
		book.get().setQuantity(book.get().getQuantity() + 1);
		bookRepository.save(book.get());
		borrowed.get().setReturnDate(LocalDate.now());
		return borrowedRepository.save(borrowed.get());
	}

	@Override
	public List<Borrowed> getActiveBorrows()
	{
		return borrowedRepository.getAllByReturnDateIsNull();
	}

	@Override
	public List<Borrowed> getExpiredBorrows()
	{
		return borrowedRepository.getAllByReturnDateIsNullAndReturnTillDateLessThan(LocalDate.now());
	}

	@Override
	public List<Borrowed> getAllBorrows()
	{
		return borrowedRepository.findAll();
	}

	@Override
	public Borrowed getBorrowInfo(Long borrowId)
	{
		return borrowedRepository.findById(borrowId).orElseThrow(
				() -> new NotFoundException(ErrorMessage.BORROW_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
	}

	private void checkBorrowActive(Optional<Borrowed> borrowed)
	{
		if (!borrowed.isPresent() || (borrowed.get().getReturnDate() != null))
		{
			throw new NotFoundException(ErrorMessage.BORROW_NOT_FOUND, ServiceErrorCode.NOT_FOUND);
		}
	}

	private void validateBorrowTake(Optional<Book> book, Optional<Customer> customer)
	{
		if (!customer.isPresent())
		{
			throw new NotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND, ServiceErrorCode.NOT_FOUND);
		}
		if (!book.isPresent())
		{
			throw new NotFoundException(ErrorMessage.BOOK_NOT_FOUND, ServiceErrorCode.NOT_FOUND);
		}
		if (book.get().getQuantity() == NO_BOOK_AVAILABLE)
		{
			throw new UnavailableException(ErrorMessage.BOOK_NOT_AVAILABLE, ServiceErrorCode.UNAVAILABLE);
		}
	}
}
