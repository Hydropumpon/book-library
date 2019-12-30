package com.example.library.controller;

import com.example.library.converter.response.BorrowedFullResponseDtoConverter;
import com.example.library.converter.response.BorrowedMinimalResponseDtoConverter;
import com.example.library.converter.request.BorrowedRequestDtoConverter;
import com.example.library.dto.request.BorrowedRequestDto;
import com.example.library.dto.response.BorrowedFullResponseDto;
import com.example.library.dto.response.BorrowedMinimalResponseDto;
import com.example.library.model.Borrowed;
import com.example.library.service.BorrowedService;
import com.example.library.views.transfer.New;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/library/borrow")
public class BorrowedController
{
	private BorrowedService borrowedService;

	private BorrowedRequestDtoConverter borrowedRequestDtoConverter;

	private BorrowedMinimalResponseDtoConverter borrowedMinimalResponseDtoConverter;

	private BorrowedFullResponseDtoConverter borrowedFullResponseDtoConverter;

	@Autowired
	public BorrowedController(BorrowedService borrowedService, BorrowedRequestDtoConverter borrowedRequestDtoConverter,
							  BorrowedMinimalResponseDtoConverter borrowedMinimalResponseDtoConverter,
							  BorrowedFullResponseDtoConverter borrowedFullResponseDtoConverter)
	{
		this.borrowedService = borrowedService;
		this.borrowedRequestDtoConverter = borrowedRequestDtoConverter;
		this.borrowedMinimalResponseDtoConverter = borrowedMinimalResponseDtoConverter;
		this.borrowedFullResponseDtoConverter = borrowedFullResponseDtoConverter;
	}

	@PostMapping(value = "/take")
	public BorrowedFullResponseDto borrowBook(@RequestBody @Validated(New.class) BorrowedRequestDto borrowedRequestDto)
	{
		Borrowed borrowed = borrowedRequestDtoConverter.fromDto(borrowedRequestDto);
		return borrowedFullResponseDtoConverter.toDto(borrowedService.borrowBook(borrowed));
	}


	@GetMapping(value = "/{borrowId}")
	public BorrowedFullResponseDto getBorrowInfo(@PathVariable Long borrowId)
	{
		return borrowedFullResponseDtoConverter.toDto(borrowedService.getBorrowInfo(borrowId));
	}

	@PostMapping(value = "/return/{borrowedId}")
	public BorrowedFullResponseDto returnBook(@PathVariable Long borrowedId)
	{
		return borrowedFullResponseDtoConverter.toDto(borrowedService.returnBook(borrowedId));
	}

	@GetMapping(value = "/active")
	public List<BorrowedMinimalResponseDto> getActiveBorrows()
	{
		List<Borrowed> borrowedList = borrowedService.getActiveBorrows();
		return borrowedList.stream().map(borrowed -> borrowedMinimalResponseDtoConverter.toDto(borrowed))
						   .collect(Collectors.toList());

	}

	@GetMapping(value = "/expired")
	public List<BorrowedMinimalResponseDto> getExpiredBorrows()
	{
		List<Borrowed> borrowedList = borrowedService.getExpiredBorrows();
		return borrowedList.stream()
						   .map(borrowed -> borrowedMinimalResponseDtoConverter.toDto(borrowed))
						   .collect(Collectors.toList());

	}

	@GetMapping
	public List<BorrowedMinimalResponseDto> getAllBorrows()
	{
		List<Borrowed> borrowedList = borrowedService.getAllBorrows();
		return borrowedList.stream()
						   .map(borrowed -> borrowedMinimalResponseDtoConverter.toDto(borrowed))
						   .collect(Collectors.toList());
	}

}
