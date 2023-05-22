package ISBNValidator;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

import static org.mockito.Mockito.*;


public class StockManagementTest {

    ExternalISBNDataService testWebService;
    ExternalISBNDataService testDataBaseService;
    StockManager stockManager;

    @Before
    public void setup(){
        testWebService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        testDataBaseService = mock(ExternalISBNDataService.class);
        stockManager.setDataBaseService(testDataBaseService);
    }

    @Test
    public void testCanGetACorrectLocatorCode(){

//        ExternalISBNDataService testWebService = new ExternalISBNDataService() {
//            @Override
//            public Book lookup(String isbn) {
//                return new Book(isbn, "Of Mice And Men", "J. Steinbeck");
//            }
//        };
        String isbn = "0140177396";
        when(testWebService.lookup(anyString())).thenReturn(new Book(isbn, "Of Mice And Men", "J. Steinbeck"));
//        ExternalISBNDataService testDataBaseService = new ExternalISBNDataService() {
//            @Override
//            public Book lookup(String isbn) {
//                return null;
//            }
//        };

        when(testDataBaseService.lookup(anyString())).thenReturn(null);

        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4",locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent(){

        when(testDataBaseService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));

        String isbn = "0140177396";

        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(testDataBaseService).lookup("0140177396"); //times(1) is the default so it could be left out
        verify(testWebService,never()).lookup(anyString());// never() is equivalent to times(0)
    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase(){

        when(testDataBaseService.lookup("0140177396")).thenReturn(null);
        when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396","abc","abc"));
        String isbn = "0140177396";

        String locatorCode = stockManager.getLocatorCode(isbn);
        verify(testDataBaseService).lookup("0140177396");
        verify(testWebService).lookup("0140177396");
    }
}


