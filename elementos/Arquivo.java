package TrabalhoED1ARN.elementos;

public class Arquivo {
    private String chave;
    private Arquivo esquerdo, direito;
    private int alt;
    
    public Arquivo(String chave){
        this.chave = chave;
        this.esquerdo = null;
        this.direito = null;
    }

    
    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Arquivo getEsquerdo() {
        return esquerdo;
    }

    public void setEsquerdo(Arquivo esquerdo) {
        this.esquerdo = esquerdo;
    }

    public Arquivo getDireito() {
        return direito;
    }

    public void setDireito(Arquivo direito) {
        this.direito = direito;
    }

    public int getAlt() {
        return alt;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }
    
    
    
}
