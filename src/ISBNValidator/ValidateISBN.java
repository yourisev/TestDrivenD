package ISBNValidator;

public class ValidateISBN {


    public static final int LONG_ISBN_LENGTH = 13;
    public static final int SHORT_ISBN_LENGTH = 10;
    public static final int SHORT_ISBN_MULTIPLIER = 11;
    public static final int LONG_ISBN_MULTIPLIER = 10;

    public boolean checkISBN(String isbn) {

        if(isbn.length() == LONG_ISBN_LENGTH){
            return isThisAValidLongISBN(isbn);
        }else if(isbn.length() == SHORT_ISBN_LENGTH){
            return isThisAValidShortISBN(isbn);
        }
        throw new NumberFormatException("ISBN numbers must be 10 or 13 digits long");
    }

    private static boolean isThisAValidShortISBN(String isbn) {
        int sum = 0;
        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
            if(!Character.isDigit(isbn.charAt(i))){
                if( i == 9 && isbn.charAt(i) == 'X'){
                    sum += (SHORT_ISBN_LENGTH * (SHORT_ISBN_LENGTH - i));
                }else{
                    throw new NumberFormatException("ISBN numbers can only contain numeric digits");
                }
            }else{
                sum += ((isbn.charAt(i) - '0') * (SHORT_ISBN_LENGTH - i));
            }
        }
        return (sum % SHORT_ISBN_MULTIPLIER == 0);
    }

    private static boolean isThisAValidLongISBN(String isbn) {
        int sum = 0;
        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {
            if(i % 2 == 0){
                sum += (isbn.charAt(i) - '0');
            }else{
                sum += ((isbn.charAt(i) - '0') * 3);
            }
        }
        return (sum % LONG_ISBN_MULTIPLIER == 0);
    }


}
