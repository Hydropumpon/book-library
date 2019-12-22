package com.example.library.controller;

import com.example.library.converter.BorrowedConverter;
import com.example.library.converter.CycleAvoidingMappingContext;
import com.example.library.dto.BorrowedDto;
import com.example.library.model.Borrowed;
import com.example.library.service.BorrowedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/library/borrow")
public class BorrowedController
{
	@Autowired
	private BorrowedService borrowedService;

	@Autowired
	private BorrowedConverter borrowedConverter;

	@PostMapping(value = "/take")
	public BorrowedDto borrowBook(@RequestBody BorrowedDto borrowedDto)
	{
		Borrowed borrowed = borrowedConverter.fromDto(borrowedDto, new CycleAvoidingMappingContext());
		return borrowedConverter.toDto(borrowedService.borrowBook(borrowed), new CycleAvoidingMappingContext());
	}

	@PostMapping(value = "/return/{borrowedId}")
	public BorrowedDto returnBook(@PathVariable Long borrowedId)
	{
		return borrowedConverter.toDto(borrowedService.returnBook(borrowedId), new CycleAvoidingMappingContext());
	}
}
