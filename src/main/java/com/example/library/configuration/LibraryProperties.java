package com.example.library.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("library.borrow")
@EnableConfigurationProperties
public class LibraryProperties
{
	private Integer days = 30;

	private String remainder = "0 0 12 * * ?";

	public String getRemainder()
	{
		return remainder;
	}

	public void setRemainder(String remainder)
	{
		this.remainder = remainder;
	}

	public Integer getDays()
	{
		return days;
	}

	public void setDays(Integer days)
	{
		this.days = days;
	}

}
