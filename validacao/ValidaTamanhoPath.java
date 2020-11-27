package TrabalhoED1ARN.validacao;

import TrabalhoED1ARN.exceptions.PathInvalidoException;

public class ValidaTamanhoPath {
    public static void validador(String path) throws PathInvalidoException{
        int count = path.length() - path.replaceAll("/", "").length();
        if(!(count < 100)){
            throw new PathInvalidoException();
        }
    }
}
