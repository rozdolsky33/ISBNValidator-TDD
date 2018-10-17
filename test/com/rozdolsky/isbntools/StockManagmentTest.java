package com.rozdolsky.isbntools;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.extensions.TestDecorator;

import static org.mockito.Mockito.*;

public class StockManagmentTest {
	
	ExternalISBNDataService testWebService; 
	StockManager stockManager;
	ExternalISBNDataService testDatabaseService;
	
	@Before
	public void setup() {
		testWebService = mock(ExternalISBNDataService.class);
		stockManager = new StockManager();
		stockManager.setWebService(testWebService);
		testDatabaseService = mock(ExternalISBNDataService.class);
		stockManager.setDatabaseService(testDatabaseService);
	}
	

	@Test
	public void testCanGetACorrectLocatorCode() {
		when(testWebService.lookup(anyString())).thenReturn(new Book("0140177396", "Of Mice And Men", "J. Steinbeck"));
		when(testDatabaseService.lookup(anyString())).thenReturn(null);

		String isbn ="0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);
		assertEquals("7396J4", locatorCode);
	}
	@Test
	public void databaseIsUsedIfDataIsPresent() {
	
		when(testDatabaseService.lookup("0140177396")).thenReturn(new Book("0140177396","abs","abs"));

		String isbn ="0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);

		verify(testDatabaseService).lookup("0140177396"); 
		verify(testWebService, never()).lookup(anyString());
	}
	@Test
	public void webserviceIsUsedIfDataNotPresentInDatabase() {

		when(testDatabaseService.lookup("0140177396")).thenReturn(null);
		when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396","abs","abs"));

		String isbn ="0140177396";
		String locatorCode = stockManager.getLocatorCode(isbn);

		verify(testDatabaseService, times(1)).lookup("0140177396"); 
		verify(testWebService, times(1)).lookup("0140177396");
	}

}
