package TrabalhoED1ARN.elementos;

public class Diretorio extends Arquivo{
    private ArvoreRN dir;

    public Diretorio(String chave) {
        super(chave);
        dir = new ArvoreRN();
    }
    
    public ArvoreRN getDir() {
        return dir;
    }

    public void setDir(ArvoreRN dir) {
        this.dir = dir;
    }
    
}
