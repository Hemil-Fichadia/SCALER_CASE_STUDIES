package TicTacToe.Implementation.exceptions;

public class InvalidMoveException extends Exception{
    public InvalidMoveException(String message){
        super(message);
    }
}
