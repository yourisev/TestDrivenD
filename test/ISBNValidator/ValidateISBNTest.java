package ISBNValidator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidateISBNTest {

    @Test
    public void checkAValidISBN10Digit() {

        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449116");
        assertTrue("First value", result);
        result = validator.checkISBN("0140177396");
        assertTrue("Second value", result);
    }

    @Test
    public void checkAValidISBN13Digit() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781853260087");
        assertTrue("First value",result);
        result = validator.checkISBN("9781853267338");
        assertTrue("Second value", result);
    }

    @Test
    public void checkAValidISBNWithAnX(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result);
    }

    @Test
    public void checkAnInvalidValidISBN13Digit() {

        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781853267335");
        assertFalse(result);
    }

    @Test
    public void checkAnInvalidValidISBN10Digit() {

        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test(expected = NumberFormatException.class)
    public void nineDigitISBNsAreNotAllowed(){
        ValidateISBN validator = new ValidateISBN();
        validator.checkISBN("123456789");
    }

    @Test(expected = NumberFormatException.class)
    public void nonNumericISBAreNotAllowed(){
        ValidateISBN validator = new ValidateISBN();
        validator.checkISBN("helloworld");
    }

}