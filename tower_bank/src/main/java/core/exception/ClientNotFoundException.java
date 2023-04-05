package core.exception;

public class ClientNotFoundException extends Exception{

    public ClientNotFoundException() {
        super("Cliente não encontrado");
    }
}
