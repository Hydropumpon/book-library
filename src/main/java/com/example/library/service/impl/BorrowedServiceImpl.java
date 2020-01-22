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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowedServiceImpl implements BorrowedService {
    private static int NO_BOOK_AVAILABLE = 0;

    private BorrowedRepository borrowedRepository;

    private BookRepository bookRepository;

    private CustomerRepository customerRepository;

    @Autowired
    public BorrowedServiceImpl(BorrowedRepository borrowedRepository, BookRepository bookRepository,
                               CustomerRepository customerRepository) {
        this.borrowedRepository = borrowedRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Borrowed borrowBook(Borrowed borrowed) {
        Book book = bookRepository.findById(borrowed.getBook().getId()).orElseThrow(
                () -> new NotFoundException(ErrorMessage.BOOK_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
        Customer customer = customerRepository.findById(borrowed.getCustomer().getId()).orElseThrow(
                () -> new NotFoundException(ErrorMessage.CUSTOMER_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
        validateBorrowTake(book);
        book.setQuantity(book.getQuantity() - 1);
        borrowed.setBook(book);
        borrowed.setCustomer(customer);
        bookRepository.save(book);
        return borrowedRepository.save(borrowed);
    }

    @Override
    @Transactional
    public Borrowed returnBook(Long borrowedId) {
        Borrowed borrowed = borrowedRepository.findByIdAndReturnDateIsNull(borrowedId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.BORROW_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
        Book book = bookRepository.findById(borrowed.getBook().getId()).orElseThrow(
                () -> new NotFoundException(ErrorMessage.BOOK_NOT_FOUND, ServiceErrorCode.NOT_FOUND));
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
        borrowed.setReturnDate(LocalDate.now());
        return borrowedRepository.save(borrowed);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Borrowed> getActiveBorrows() {
        return borrowedRepository.getAllByReturnDateIsNull();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Borrowed> getExpiredBorrows() {
        return borrowedRepository.getAllByReturnDateIsNullAndReturnTillDateLessThan(LocalDate.now());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Borrowed> getAllBorrows() {
        return borrowedRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Borrowed getBorrowInfo(Long borrowId) {
        return borrowedRepository.findById(borrowId)
                                 .orElseThrow(() -> new NotFoundException(ErrorMessage.BORROW_NOT_FOUND,
                                                                          ServiceErrorCode.NOT_FOUND));
    }

    private void validateBorrowTake(Book book) {
        if (book.getQuantity() == NO_BOOK_AVAILABLE) {
            throw new UnavailableException(ErrorMessage.BOOK_NOT_AVAILABLE, ServiceErrorCode.UNAVAILABLE);
        }
    }
}
