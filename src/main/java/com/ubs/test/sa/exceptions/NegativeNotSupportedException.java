package com.ubs.test.sa.exceptions;

public class NegativeNotSupportedException extends RuntimeException 
{
	private static final long serialVersionUID = 4163536557367721227L;

	public NegativeNotSupportedException(String message )
	{
		super(message);
	}
}
