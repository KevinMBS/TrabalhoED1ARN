package TrabalhoED1ARN.exceptions;

public class DiretorioInexistenteException extends Exception{
    public DiretorioInexistenteException(String comando, String op){
        super(comando + ": cannot create/touch '" + op + "': No such file or directory");
    }
}
