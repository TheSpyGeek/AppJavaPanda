package myExceptions;

public class ExceptionColIsGrouped extends Exception{
    @Override
    public String getMessage() {
        return "Impossible d'appliquer une opération sur une colonne déjà groupée.";
    }
}
