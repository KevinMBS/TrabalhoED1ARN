package TrabalhoED1ARN.elementos;

import java.util.ArrayList;

public class ArvoreRN {
    
    private Arquivo raiz;
    
    public ArvoreRN(){
        this.raiz = null;
    }

    public Arquivo getRaiz() {
        return raiz;
    }

    public void setRaiz(Arquivo raiz) {
        this.raiz = raiz;
    }
    
    //Metódos especificos para a arvore AVL
    private int altura(Arquivo arq){
        if(arq == null)
            return -1;
        
        return arq.getAlt();
    }
    
    private int fatorBalanceamento(Arquivo arq){
        if(arq == null)
            return 0;
        
        return altura(arq.getDireito()) - altura(arq.getEsquerdo());
    }
    
    private Arquivo rotacaoDireita(Arquivo pai){
        Arquivo filho = pai.getEsquerdo();
        Arquivo neto = filho.getDireito();
        
        //Realiza a rotação
        filho.setDireito(pai);
        pai.setEsquerdo(neto);
        
        //Atualiza a altura desses nós
        pai.setAlt(Math.max(altura(pai.getEsquerdo()), altura(pai.getDireito())) + 1);
        filho.setAlt(Math.max(altura(filho.getEsquerdo()), altura(filho.getDireito())) + 1);
        
        return filho;
    }
    
    private Arquivo rotacaoEsquerda(Arquivo pai){
        Arquivo filho = pai.getDireito();
        Arquivo neto = filho.getEsquerdo();
        
        //Realiza a rotação
        filho.setEsquerdo(pai);
        pai.setDireito(neto);
        
        //Atualiza a altura desses nós
        pai.setAlt(Math.max(altura(pai.getEsquerdo()), altura(pai.getDireito())) + 1);
        filho.setAlt(Math.max(altura(filho.getEsquerdo()), altura(filho.getDireito())) + 1);
        
        return filho;
    }
    
    private Arquivo balanceiaAVL(Arquivo arq){
        arq.setAlt(Math.max(altura(arq.getEsquerdo()), altura(arq.getDireito())) + 1);
        int fb = fatorBalanceamento(arq);
        
        if(fb > 1){
            if(altura(arq.getDireito().getDireito()) > altura(arq.getDireito().getEsquerdo())){
                //Rotação simples para a esquerda
                arq = rotacaoEsquerda(arq);
            }else{
                //Rotação dupla para a esquerda
                arq.setDireito(rotacaoDireita(arq.getDireito()));
                arq = rotacaoEsquerda(arq);
            }
        }else if(fb < -1){
            if(altura(arq.getEsquerdo().getEsquerdo()) > altura(arq.getEsquerdo().getDireito())){
                //Rotação simples para a direita
                arq = rotacaoDireita(arq);
            }else{
                //Rotação dupla para a direita
                arq.setEsquerdo(rotacaoEsquerda(arq.getEsquerdo()));
                arq = rotacaoDireita(arq);
            }
        }
        
        return arq;
    }
    
    public Arquivo procuraArquivo(String chave){ //Serve mais para chamar o metodo recursivo
        return procuraArquivoRec(this.raiz, chave);
    }
    
    private Arquivo procuraArquivoRec(Arquivo raiz, String chave){
        Arquivo atual = raiz;
        
        if(atual == null || atual.getChave().equals(chave)){ //Achou ou não existe
            return atual;
        }
        
        if(chave.compareTo(atual.getChave()) < 0)
            return procuraArquivoRec(atual.getEsquerdo(), chave);
        
        return procuraArquivoRec(atual.getDireito(), chave);
    }
    
    public void addDiretorio(String chave){ //Serve mais para chamar o metodo recursivo
        this.raiz = addDiretorioRec(this.raiz, chave);
    }
    
    private Arquivo addDiretorioRec(Arquivo raiz, String chave){
        Arquivo atual = raiz;
        
        if(atual == null){ //Não existe, então cria um novo Diretório
            atual = new Diretorio(chave);
            return (Diretorio) atual;
        }
        
        if(chave.compareTo(atual.getChave()) < 0){
            atual.setEsquerdo(addDiretorioRec(raiz.getEsquerdo(), chave));
        }else if(chave.compareTo(atual.getChave()) > 0){
            atual.setDireito(addDiretorioRec(raiz.getDireito(), chave));
        }
        return balanceiaAVL(atual);
    }
    
    public void addArquivo(String chave){ //Serve mais para chamar o metodo recursivo
        this.raiz = addArquivoRec(this.raiz, chave);
    }
    
    private Arquivo addArquivoRec(Arquivo raiz, String chave){
        Arquivo atual = raiz;
        
        if(atual == null){ //Não existe, então cria um novo Arquivo
            atual = new Arquivo(chave);
            return atual;
        }
        
        if(chave.compareTo(atual.getChave()) < 0){
            atual.setEsquerdo(addArquivoRec(raiz.getEsquerdo(), chave));
        }else if(chave.compareTo(atual.getChave()) > 0){
            atual.setDireito(addArquivoRec(raiz.getDireito(), chave));
        }
        return balanceiaAVL(atual);
    }
    
    public Arquivo interpretaPath(String path){ //Serve mais para chamar o metodo recursivo
        return interpretaPathRec(this, path);
    }
    
    private Arquivo interpretaPathRec(ArvoreRN atual, String path){
        int index = 0;
        String dirAtual = path;
        Arquivo dir;
        if(path.contains("/")){
            index = path.indexOf("/");
            dirAtual = dirAtual.substring(0, index);
            dir = atual.procuraArquivo(dirAtual); //Procura o arquivo dado pelo path
            if(dir instanceof Diretorio) //Se ele for diretório mesmo, passa a próxima arvore e continua
                return interpretaPathRec(((Diretorio) dir).getDir(), path.substring(index+1, path.length()));
            return dir; //Só entra aqui se achar um arquivo com o mesmo nome da chave dada
        }else{ //Chegou no diretório desejado
            return atual.procuraArquivo(dirAtual);
        }
    }
    
    public static void printArvoreRecursivo(String caminho, ArvoreRN arvore, String pasta){
        Arquivo raiz = arvore.getRaiz();
        ArrayList<Diretorio> dirsAvisitar = new ArrayList<>();
        if(!pasta.equals("")&&!pasta.equals(" ")){            
            caminho = caminho.concat("/" + pasta);
        }
        
        if(pasta.equals(" ")){
            System.out.println("" + caminho + "/" + " :");     
        }else{
            System.out.println("" + caminho + " :");
        }
        
        
        //Imprimindo e guardando em um array os nós desta arvore que são diretorios,portanto estes devem ser visitados
        dirsAvisitar = auxPrintArvoreRec(raiz);
        
        //visantando diretorios(arvores) contidas nos nós 
        
        System.out.println("");
                    
        for(Diretorio d:dirsAvisitar){
            printArvoreRecursivo(caminho, d.getDir(),d.getChave());
        }
        
        
    }
    
    private static ArrayList<Diretorio> auxPrintArvoreRec(Arquivo atual){
        ArrayList<Diretorio> dirsAvisitar = new ArrayList<>();
        if(atual != null){
            ArrayList<Diretorio> esquerdo = auxPrintArvoreRec(atual.getEsquerdo());
            System.out.println(" "+atual.getChave());
            ArrayList<Diretorio> direito = auxPrintArvoreRec(atual.getDireito());
            if(esquerdo.isEmpty() == false){
                for(Diretorio d:esquerdo){
                    dirsAvisitar.add(d);
                }
            }
            if(atual instanceof Diretorio){
                dirsAvisitar.add((Diretorio)atual);
            }
            if(direito.isEmpty() == false){
                for(Diretorio d:direito){
                    dirsAvisitar.add(d);
                }
            }    
        }
        return dirsAvisitar;       
    }

    public void printArvore(String nomeDir) {
        //System.out.println("./"+ nomeDir);
        Arquivo raiz = this.getRaiz();
        auxPrintArvore(raiz);    
    }

    private static void auxPrintArvore(Arquivo raiz) {
        if(raiz.getEsquerdo() != null){
            auxPrintArvore(raiz.getEsquerdo());
        }
        System.out.println(raiz.getChave());
        if(raiz.getDireito() != null){
            auxPrintArvore(raiz.getDireito());
        }
    }
    
    public void procuraChave(String caminho, String chave){
        
            Arquivo atual = this.raiz;
            while(atual != null){
                if(chave.compareTo(atual.getChave()) < 0){
                    //esquerda
                    atual = atual.getEsquerdo();
                }else if(chave.compareTo(atual.getChave()) > 0){
                    //direita
                    atual = atual.getDireito();
                }else{
                    //achou
                    if(caminho.length() == 1 && caminho.equals(".") == false){
                        caminho = "./"+caminho;
                        
                    }
                    System.out.println(""+caminho+"/"+atual.getChave());
                    
                    atual = null;
                }
            }
        
        
    }
    
    public void procuraChaveRec(String caminho, String nomePasta, String chave){
        Arquivo raiz = this.getRaiz();
        ArrayList<Diretorio> dirsAvisitar = new ArrayList<>();
        if(!nomePasta.equals("")){
            caminho = caminho.concat("/" + nomePasta);
        }
        
        
        //guardando em um array os nós desta arvore que são diretorios,portanto estes devem ser visitados
        dirsAvisitar = auxProcuraArvoreRec(raiz);
        this.procuraChave(caminho, chave);
        
        //visantando diretorios(arvores) contidas nos nós 
                    
        for(Diretorio d:dirsAvisitar){
            d.getDir().procuraChaveRec(caminho,d.getChave(),chave);
        }
        
    }

    private ArrayList<Diretorio> auxProcuraArvoreRec(Arquivo atual) {
        ArrayList<Diretorio> dirsAvisitar = new ArrayList<>();
        if(atual != null){
            ArrayList<Diretorio> esquerdo = auxProcuraArvoreRec(atual.getEsquerdo());
            ArrayList<Diretorio> direito = auxProcuraArvoreRec(atual.getDireito());
            if(esquerdo.isEmpty() == false){
                for(Diretorio d:esquerdo){
                    dirsAvisitar.add(d);
                }
            }
            if(atual instanceof Diretorio){
                dirsAvisitar.add((Diretorio)atual);
            }
            if(direito.isEmpty() == false){
                for(Diretorio d:direito){
                    dirsAvisitar.add(d);
                }
            }    
        }
        return dirsAvisitar;
    }
}
