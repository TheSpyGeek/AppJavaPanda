import myExceptions.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExceptionTest {


    @Test
    public void testColIsGrouped(){
        ExceptionColIsGrouped ex = new ExceptionColIsGrouped();
        assertEquals("Impossible d'appliquer une opération sur une colonne déjà groupée.", ex.getMessage());
    }

    @Test
    public void testNoSuchColumn(){
        ExceptionNoSuchColumn ex = new ExceptionNoSuchColumn();
        assertEquals("Impossible de réaliser une opération arithmétique sur une colonne de type String.", ex.getMessage());
    }

    @Test
    public void testNotSameSize(){
        ExceptionNotSameSize ex = new ExceptionNotSameSize();
        assertEquals("Une colonne a plus d'éléments qu'une autre!", ex.getMessage());
    }

    @Test
    public void testOutOfRange(){
        ExceptionOutOfRange ex = new ExceptionOutOfRange();
        assertEquals("Porté précisée invalide.", ex.getMessage());
    }

    @Test
    public void testExceptionString(){
        ExceptionString ex = new ExceptionString();
        assertEquals("Impossible de réaliser une opération arithmétique sur une colonne de type String", ex.getMessage());
    }
}
