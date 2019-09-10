package com.ubs.test.sa;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ubs.test.sa.exceptions.NegativeNotSupportedException;

public class CalculatorTest {
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void testCalculator_emptyArgument() {
		assertEquals( 0, Calculator.add( "" ) );
	}

	@Test
	public void testCalculator_singleNumberArgument() {
		assertEquals( 10, Calculator.add( "10" ) );
	}
	
	@Test
	public void testCalculator_twoNumberArgument() {
		assertEquals( 15, Calculator.add( "10,5" ) );
	}
	
	@Test
	public void testCalculator_multipleNumberArgument() {
		assertEquals( 33, Calculator.add( "10,5,2,7,9" ) );
	}
	
	@Test
	public void testCalculator_newLineDelimiter() {
		assertEquals( 33, Calculator.add( "10\n5,2,7,9" ) );
	}
	
	@Test
	public void testCalculator_supportDifferentDelimiter() {
		assertEquals( 33, Calculator.add( "//;\n10;5;2;7;9" ) );
	}
	
	@Test
	public void testCalculator_supportMultipleDelimiter() {
		assertEquals( 33, Calculator.add( "//;|,\n10;5,2;7;9" ) );
	}
	
	@Test
	public void testCalculator_supportAnyLengthDelimiter() {
		assertEquals( 33, Calculator.add( "//;;;;\n10;;;;5;;;;2;;;;7;;;;9" ) );
	}
	
	@Test
	public void testCalculator_supportMultipleAnyLengthDelimiter() {
		assertEquals( 33, Calculator.add( "//;;;;|,,\n10;;;;5,,2,,7;;;;9" ) );
	}
	
	@Test
	public void testCalculator_ignoreGt1000() {
		assertEquals( 23, Calculator.add( "1001\n5,2,7,9" ) );
	}
	
	@Test
	public void testCalculator_shouldWorkFor1000() {
		assertEquals( 1023, Calculator.add( "1000,5,2,7,9" ) );
	}
	
	@Test
	public void testCalculator_negativeNumberException() {
		expectedEx.expect( NegativeNotSupportedException.class );
		expectedEx.expectMessage( "Negatives not allowed: [-2, -7]");
		Calculator.add( "1001\n5,-2,-7,9" );
	}
	
}
