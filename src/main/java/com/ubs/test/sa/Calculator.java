package com.ubs.test.sa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.ubs.test.sa.exceptions.NegativeNotSupportedException;

public class Calculator 
{
	public static int add( String numbers )
	{	
		Result result = new Result();
		Stream.of( split( numbers ) )
					.map( s -> StringUtils.isEmpty( s ) ? 0 : Integer.valueOf( s ) )
					.filter( n -> n <= 1000 )  //Ignore numbers > 1000
					.forEach( result::process );
		
		return result.getValue();
	}

	private static String[] split( String numbers )
	{
		String defaultDelimiter = "\n|,";
		String dynamicDelimiters = StringUtils.substringBetween( numbers, "//", "\n"); 
		String regex = (dynamicDelimiters != null ? dynamicDelimiters : defaultDelimiter);
		
		return dynamicDelimiters != null ? numbers.replace( "//" + dynamicDelimiters + "\n", "" ).split( regex ) : numbers.split(regex);
	}
	

	static class Result
	{
		private int result;
		private List<Integer> negatives = new ArrayList<Integer>();
		
		public void addNegative( int negative )
		{
			negatives.add( negative );
		}
		
		public void addValue( int number )
		{
			result += number;
		}
		
		void process( int number ) 
		{
			if( number < 0 )
				addNegative( number );
			else
				addValue(number);
		}
		
		public int getValue()
		{
			if( negatives.isEmpty() )
				return result;
			else
				throw new NegativeNotSupportedException( "Negatives not allowed: " + negatives );
		}
	}
}
