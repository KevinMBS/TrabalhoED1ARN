package TrabalhoED1ARN.comandos;

import TrabalhoED1ARN.elementos.Arquivo;
import TrabalhoED1ARN.elementos.ArvoreRN;
import static TrabalhoED1ARN.elementos.ArvoreRN.printArvoreRecursivo;
import TrabalhoED1ARN.elementos.Diretorio;
import TrabalhoED1ARN.exceptions.DiretorioInexistenteLSException;
import TrabalhoED1ARN.funcoes.Funcao;


public class ComandoLs implements Funcao{

    @Override
    public void fazFuncao(ArvoreRN arvore, String... resComando) throws Exception {
        
        ArvoreRN dir; 
        Arquivo aux = null;
        if(resComando.length == 1){
            arvore.printArvore("");
        }else if(resComando[1].equals("-R")){
            //recursiva
            if(resComando.length == 3){
                if(resComando[2].endsWith("/")){
                    resComando[2] = resComando[2].substring(0, resComando[2].length()-1);
                }
                aux = arvore.interpretaPath(resComando[2]);
                if(aux == null){ //Diretorio não existe
                    throw new DiretorioInexistenteLSException();
                }else if(!(aux instanceof Diretorio)){ //Ou seja, se ele for um arquivo base não é possivel imprir seu conteudo no nosso sistema
                     throw new DiretorioInexistenteLSException();
                }
                //printArvoreRecursivo(".", ((Diretorio)aux).getDir(),aux.getChave());
                printArvoreRecursivo(".", ((Diretorio)aux).getDir(),resComando[2]);
            }else{
                dir = arvore;       
                printArvoreRecursivo(".", dir," ");
            }

        }else{
            //não recursiva
            if(resComando[1].endsWith("/")){
                resComando[1] = resComando[1].substring(0, resComando[1].length()-1);
            }
            aux = arvore.interpretaPath(resComando[1]);
            if(aux == null){ //Diretorio não existe
                throw new DiretorioInexistenteLSException();
            }else if(!(aux instanceof Diretorio)){ //Ou seja, se ele for um arquivo base não é possivel imprir seu conteudo no nosso sistema
                throw new DiretorioInexistenteLSException();
            }else{
                ((Diretorio)aux).getDir().printArvore(aux.getChave());
        
            }
        }
    }
    
}
