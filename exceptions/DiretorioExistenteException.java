package TrabalhoED1ARN.exceptions;

public class DiretorioExistenteException extends Exception {
    
    public DiretorioExistenteException(String op){
        super("mkdir: cannot create directory '" + op + "': File exists");
    }
    
}
