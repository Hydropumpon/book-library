package com.example.library.controller;

import com.example.library.converter.BorrowedConverter;
import com.example.library.converter.CycleAvoidingMappingContext;
import com.example.library.dto.BorrowedDto;
import com.example.library.model.Borrowed;
import com.example.library.service.BorrowedService;
import com.example.library.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/library/borrow")
public class BorrowedController
{
	private BorrowedService borrowedService;

	private BorrowedConverter borrowedConverter;

	@Autowired
	public BorrowedController(BorrowedService borrowedService, BorrowedConverter borrowedConverter)
	{
		this.borrowedService = borrowedService;
		this.borrowedConverter = borrowedConverter;
	}

	@PostMapping(value = "/take")
	@JsonView(Views.FullData.class)
	public BorrowedDto borrowBook(@RequestBody BorrowedDto borrowedDto)
	{
		Borrowed borrowed = borrowedConverter.fromDto(borrowedDto, new CycleAvoidingMappingContext());
		return borrowedConverter.toDto(borrowedService.borrowBook(borrowed), new CycleAvoidingMappingContext());
	}

	@JsonView(Views.FullData.class)
	@PostMapping(value = "/return/{borrowedId}")
	public BorrowedDto returnBook(@PathVariable Long borrowedId)
	{
		return borrowedConverter.toDto(borrowedService.returnBook(borrowedId), new CycleAvoidingMappingContext());
	}

	@JsonView(Views.IdName.class)
	@GetMapping(value = "/active")
	public List<BorrowedDto> getActiveBorrows()
	{
		List<Borrowed> borrowedList = borrowedService.getActiveBorrows();
		return borrowedList.stream()
						   .map(borrowed -> borrowedConverter.toDto(borrowed, new CycleAvoidingMappingContext()))
						   .collect(Collectors.toList());

	}

	@JsonView(Views.IdName.class)
	@GetMapping(value = "/expired")
	public List<BorrowedDto> getExpiredBorrows()
	{
		List<Borrowed> borrowedList = borrowedService.getExpiredBorrows();
		return borrowedList.stream()
						   .map(borrowed -> borrowedConverter.toDto(borrowed, new CycleAvoidingMappingContext()))
						   .collect(Collectors.toList());

	}

	@JsonView(Views.IdName.class)
	@GetMapping
	public List<BorrowedDto> getAllBorrows()
	{
		List<Borrowed> borrowedList = borrowedService.getAllBorrows();
		return borrowedList.stream()
						   .map(borrowed -> borrowedConverter.toDto(borrowed, new CycleAvoidingMappingContext()))
						   .collect(Collectors.toList());
	}

}
