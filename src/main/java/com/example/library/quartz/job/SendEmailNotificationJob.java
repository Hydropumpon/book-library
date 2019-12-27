package com.example.library.quartz.job;

import com.example.library.model.Book;
import com.example.library.model.Borrowed;
import com.example.library.model.Customer;
import com.example.library.service.BorrowedService;
import com.example.library.service.MailService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SendEmailNotificationJob implements Job
{
	private static final String MAIL_SUBJECT = "Library expired borrow";

	private BorrowedService borrowedService;

	private MailService mailService;

	@Autowired
	public SendEmailNotificationJob(BorrowedService borrowedService, MailService mailService)
	{
		this.mailService = mailService;
		this.borrowedService = borrowedService;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		List<Borrowed> expiredBorrows = borrowedService.getExpiredBorrows();
		Map<Customer, List<Book>> customerListMap = populateBorrowers(expiredBorrows);
		for (Map.Entry<Customer, List<Book>> entry : customerListMap.entrySet())
		{
			String mailMessage = prepareMailMessage(entry);
			mailService.send(entry.getKey().getEmail(), MAIL_SUBJECT, mailMessage);
		}
	}

	private Map<Customer, List<Book>> populateBorrowers(List<Borrowed> expiredBorrows)
	{
		Map<Customer, List<Book>> customerListMap = new HashMap<>();
		for (Borrowed borrowed : expiredBorrows)
		{
			if (!customerListMap.containsKey(borrowed.getCustomer()))
			{
				customerListMap.put(borrowed.getCustomer(), new ArrayList<>());
			}
			customerListMap.get(borrowed.getCustomer()).add(borrowed.getBook());
		}
		return customerListMap;
	}

	private String prepareMailMessage(Map.Entry<Customer, List<Book>> entry)
	{
		Customer customer = entry.getKey();
		List<Book> books = entry.getValue();
		List<String> bookNames = books.stream().map(Book::getTitle).collect(Collectors.toList());
		return String.format("Dear %s \n" + "Please return books %s", customer.getFirstName(), bookNames.toString());
	}
}
