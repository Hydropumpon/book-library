package com.example.library.service;

import com.example.library.common.exception.NotFoundException;
import com.example.library.common.exception.ServiceErrorCode;
import com.example.library.model.Book;
import com.example.library.model.Borrowed;
import com.example.library.model.Customer;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowedRepository;
import com.example.library.repository.CustomerRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BorrowedService
{

	@Autowired
	private BorrowedRepository borrowedRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public Borrowed borrowBook(Borrowed borrowed)
	{
		Optional<Book>  book = bookRepository.findById(borrowed.getBook().getId());
		Optional<Customer> customer = customerRepository.findById(borrowed.getCustomer().getId());
		if (customer.isPresent() && (book.isPresent() && book.get().getQuantity()!=0))
		{
			book.get().setQuantity(book.get().getQuantity()-1);
			borrowed.setBook(book.get());
			borrowed.setCustomer(customer.get());
			bookRepository.save(book.get());
			return borrowedRepository.save(borrowed);
		}
		throw new NotFoundException("test message", ServiceErrorCode.NOT_FOUND);
	}

	public Borrowed returnBook(Long borrowedId)
	{
		Optional<Borrowed> borrowed = borrowedRepository.findById(borrowedId);
		if (!borrowed.isPresent() || (borrowed.get().getReturnDate()!=null))
		{
			throw new NotFoundException("No borrow with such id", ServiceErrorCode.NOT_FOUND);
		}
		Optional<Book>  book = bookRepository.findById(borrowed.get().getBook().getId());
		book.get().setQuantity(book.get().getQuantity()+1);
		bookRepository.save(book.get());
		borrowed.get().setReturnDate(LocalDate.now());
		return borrowedRepository.save(borrowed.get());
	}
}
