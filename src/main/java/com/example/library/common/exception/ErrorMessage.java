package com.example.library.common.exception;

public abstract class ErrorMessage {
    private ErrorMessage() {
    }

    public static final String AUTHOR_EXIST = "Author already exist";

    public static final String AUTHOR_NOT_FOUND = "Author not found";

    public static final String BOOK_EXIST = "Book already exist";

    public static final String BOOK_NOT_FOUND = "Book not found";

    public static final String BOOK_NOT_AVAILABLE = "Book can not be borrowed";

    public static final String CUSTOMER_NOT_FOUND = "Customer not found";

    public static final String CUSTOMER_EMAIL_ALREADY_EXIST = "Customer with same email already exists";

    public static final String CUSTOMER_LOGIN_ALREADY_EXIST = "Customer with same login already exists";

    public static final String BORROW_NOT_FOUND = "Borrow not found";
}
