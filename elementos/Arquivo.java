package TrabalhoED1ARN.elementos;

import java.util.Objects;

public class Arquivo implements Comparable<Arquivo>{
    private String chave;
    private Arquivo esquerdo, direito, pai;
    private int cor;
    
    public Arquivo(String chave){
        this.chave = chave;
        this.esquerdo = null;
        this.direito = null;
        this.pai = null;
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

    public Arquivo getPai() {
        return pai;
    }

    public void setPai(Arquivo pai) {
        this.pai = pai;
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }   

    @Override
    public int compareTo(Arquivo outro) {
        return this.chave.compareTo(outro.chave);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Arquivo other = (Arquivo) obj;
        if (!Objects.equals(this.chave, other.chave)) {
            return false;
        }
        return true;
    }
    
    
    
}
