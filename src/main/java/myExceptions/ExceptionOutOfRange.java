package myExceptions;

public class ExceptionOutOfRange extends Exception{
    @Override
    public String getMessage() {
        return "Porté précisée invalide.";
    }
}
