package TrabalhoED1ARN.funcoes;

import TrabalhoED1ARN.elementos.ArvoreRN;

public interface Funcao {
    abstract public void fazFuncao(ArvoreRN arvore, String... resComando) throws Exception; 
}
