package TrabalhoED1ARN.exceptions;

public class NaoEDiretorioException extends Exception{
    public NaoEDiretorioException(String comando, String op){
        super(comando + ": cannot create/touch '" + op + "': Not a directory");
    }
}
