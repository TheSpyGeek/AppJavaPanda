package myExceptions;

public class ExceptionNotSameSize extends Exception {
    @Override
    public String getMessage() {
        return "Une colonne a plus d'éléments qu'une autre!";
    }
}
