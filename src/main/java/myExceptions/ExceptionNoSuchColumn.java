package myExceptions;

public class ExceptionNoSuchColumn extends Exception {
    @Override
    public String getMessage() {
        return "Impossible de réaliser une opération arithmétique sur une colonne de type String.";
    }
}
