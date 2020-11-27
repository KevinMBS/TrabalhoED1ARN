package TrabalhoED1ARN.comandos;

import TrabalhoED1ARN.funcoes.Funcao;

public interface Comando {
    public abstract String getOpcao();
    public abstract Funcao getFuncao();
}
