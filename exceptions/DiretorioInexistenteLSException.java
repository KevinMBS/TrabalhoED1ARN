package TrabalhoED1ARN.exceptions;

public class DiretorioInexistenteLSException extends Exception{
    public DiretorioInexistenteLSException(){
        super("cannot list from this directory ");
    }
}
