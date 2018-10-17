package com.rozdolsky.isbntools;

import static org.junit.Assert.*;

import org.junit.Test;

public class ValidateISBNTest {

	@Test
	public void checkAValid10DigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.chekISBN("0140449116");
		assertTrue("first value", result);
		result = validator.chekISBN("0140177396");
		assertTrue("second value", result);
	}

	@Test
	public void checkAValid13DigitISBNNumber() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.chekISBN("9781501175510");
		assertTrue("first value",result);
		result = validator.chekISBN("9781420951400");
		assertTrue("second value", result);
	}

	@Test
	public void checkAnInvalid13DigitISBN() {	
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.chekISBN("9781420951406");
		assertFalse(result);

	}

	@Test
	public void tenDigitISBNNumbersEndingInAnXAreValid() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.chekISBN("012000030X");
		assertTrue(result);	
	}

	@Test
	public void checkInValid10DigitISBN() {
		ValidateISBN validator = new ValidateISBN();
		boolean result = validator.chekISBN("0140449117");
		assertFalse(result);
	}

	@Test(expected = NumberFormatException.class)
	public void nineDidgetISBNNumberAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		validator.chekISBN("123456789");	
	}

	@Test(expected = NumberFormatException.class)
	public void nonNumericISBNsAreNotAllowed() {
		ValidateISBN validator = new ValidateISBN();
		validator.chekISBN("Helloworld");

	}

}
