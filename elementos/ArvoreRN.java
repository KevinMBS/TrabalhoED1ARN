package TrabalhoED1ARN.elementos;

import java.util.ArrayList;

public class ArvoreRN {
    
    private final int vermelho = 1;
    private final int preto = 0;
    
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
    
    //Metódos especificos para a arvore Rubro Negra
    private Arquivo getPai(Arquivo arq){
        if(arq != null)
            return arq.getPai();
        return null;
    }
    
    private Arquivo getVo(Arquivo arq){
        return getPai(getPai(arq));
    }
    
    private Arquivo getTio(Arquivo arq){
        Arquivo vo = getVo(arq);
        if(vo == null)
            return null;
        
        Arquivo pai = getPai(arq);
        
        if(pai.compareTo(vo) > 0)
            return vo.getEsquerdo();
        return vo.getDireito();
    }
    
    private void rotacaoDireita(Arquivo pai){
        Arquivo filho = pai.getEsquerdo();
        
        pai.setEsquerdo(filho.getDireito());
        if(filho.getDireito()!= null){
            filho.getDireito().setPai(pai);
        }
        
        filho.setPai(getPai(pai));
        if(getPai(pai) == null){
            this.raiz = filho;
        }else if(pai.equals(getPai(pai).getDireito())){
            getPai(pai).setDireito(filho);
        }else{
            getPai(pai).setEsquerdo(filho);
        }
        filho.setDireito(pai);
        pai.setPai(filho);
    }
    
    private void rotacaoEsquerda(Arquivo pai){
        Arquivo filho = pai.getDireito();
        
        pai.setDireito(filho.getEsquerdo());
        if(filho.getEsquerdo() != null){
            filho.getEsquerdo().setPai(pai);
        }
        
        filho.setPai(getPai(pai));
        if(getPai(pai) == null){
            this.raiz = filho;
        }else if(pai.equals(getPai(pai).getEsquerdo())){
            getPai(pai).setEsquerdo(filho);
        }else{
            getPai(pai).setDireito(filho);
        }
        filho.setEsquerdo(pai);
        pai.setPai(filho);
    }
    
    private void trocarCor(Arquivo arq){
        getPai(arq).setCor(preto);
        getTio(arq).setCor(preto);
        getVo(arq).setCor(vermelho);
    }
    
    private void balanceiaRN(Arquivo arq){
        if(getPai(arq) == null){
            //É a raiz, então só pinta o novo nó de preto
            arq.setCor(preto);
        }else if(getPai(arq).getCor() == preto){
            //A regra da RN está valida, não faz nada
            return;
        }else if(getTio(arq) != null && getTio(arq).getCor() == vermelho){
            //Inverte as cores
            trocarCor(arq);
            //Continua a verificação
            balanceiaRN(getVo(arq));
        }else{
            //É necessário fazer uma rotação
            Arquivo pai = getPai(arq);
            Arquivo vo = getVo(arq);
            
            //Esses casos se aplicam se quando o nó está sendo o filho interno, então faz uma troca de posição
            if(arq.equals(pai.getDireito()) && pai.equals(vo.getEsquerdo())){
                rotacaoEsquerda(arq);
                arq = arq.getEsquerdo();
            }else if(arq.equals(pai.getEsquerdo()) && pai.equals(vo.getDireito())){
                rotacaoDireita(arq);
                arq = arq.getDireito();
            }
            
            pai = getPai(arq);
            vo = getVo(arq);
            
            //Rotação padrão com troca de cor depois
            if(arq.compareTo(pai) > 0){
                rotacaoEsquerda(vo);
            }else{
                rotacaoDireita(vo);
            }
            
            pai.setCor(preto);
            vo.setCor(vermelho);
            
        }
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
    
    public void addDiretorio(String chave){
        Diretorio novoDir = new Diretorio(chave);
        
        addDiretorioRec(this.raiz, novoDir);
        
        while(getPai(novoDir) != null){
            novoDir = (Diretorio)getPai(novoDir);
        }
        
        this.raiz = novoDir;
    }
    
    private void addDiretorioRec(Arquivo raiz, Arquivo novoDir){
        if(raiz != null){
            if(novoDir.compareTo(raiz) < 0){
                if(raiz.getEsquerdo() != null){
                    addDiretorioRec(raiz.getEsquerdo(), novoDir);
                    return;
                }else{
                    raiz.setEsquerdo(novoDir);
                }
            }else{
                if(raiz.getDireito()!= null){
                    addDiretorioRec(raiz.getDireito(), novoDir);
                    return;
                }else{
                    raiz.setDireito(novoDir);
                }
            }
            
        }
        
        novoDir.setCor(vermelho);
        novoDir.setPai(raiz);
        
        balanceiaRN(novoDir);
    }
    
    public void addArquivo(String chave){
        Arquivo novoArq = new Arquivo(chave);
        
        addArquivoRec(this.raiz, novoArq);
        
        while(getPai(novoArq) != null){
            novoArq = getPai(novoArq);
        }
        
        this.raiz = novoArq;
    }
    
    private void addArquivoRec(Arquivo raiz, Arquivo novoArq){
        if(raiz != null){
            if(novoArq.compareTo(raiz) < 0){
                if(raiz.getEsquerdo() != null){
                    addArquivoRec(raiz.getEsquerdo(), novoArq);
                    return;
                }else{
                    raiz.setEsquerdo(novoArq);
                }
            }else{
                if(raiz.getDireito()!= null){
                    addArquivoRec(raiz.getDireito(), novoArq);
                    return;
                }else{
                    raiz.setDireito(novoArq);
                }
            }
            
        }
        
        novoArq.setCor(vermelho);
        novoArq.setPai(raiz);
        
        balanceiaRN(novoArq);
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
