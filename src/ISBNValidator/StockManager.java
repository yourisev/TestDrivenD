package ISBNValidator;

public class StockManager {

    private ExternalISBNDataService webService;

    private ExternalISBNDataService dataBaseService;

    public void setDataBaseService(ExternalISBNDataService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public void setWebService(ExternalISBNDataService webService) {
        this.webService = webService;
    }

    public String getLocatorCode(String isbn) {

        Book book = dataBaseService.lookup(isbn);
        if(book == null) book = webService.lookup(isbn);
        StringBuilder locator =new StringBuilder();
        locator.append(isbn.substring(isbn.length() - 4));
        locator.append(book.getAuthor().substring(0,1));
        locator.append(book.getTitle().split(" ").length);

        return locator.toString();
    }
}
