package com.example.library.common.exception;

import org.springframework.http.HttpStatus;

public class UnavailableException extends ServiceException
{
	public UnavailableException(String message, ServiceErrorCode errorCode)
	{
		super(message, HttpStatus.IM_USED, errorCode);
	}
}
