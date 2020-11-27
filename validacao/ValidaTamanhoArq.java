package TrabalhoED1ARN.validacao;

import TrabalhoED1ARN.exceptions.NomeInvalidoException;

public class ValidaTamanhoArq {
    public static void validador(String nome) throws NomeInvalidoException{
        if(nome.length() > 50)
            throw new NomeInvalidoException(nome);
    }
}
