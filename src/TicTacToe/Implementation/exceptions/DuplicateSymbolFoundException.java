package TicTacToe.Implementation.exceptions;

public class DuplicateSymbolFoundException extends Exception{
    public DuplicateSymbolFoundException(String message){
        super(message);
    }
}
