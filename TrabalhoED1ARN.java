package TrabalhoED1ARN;

import TrabalhoED1ARN.comandos.ComandoLinux;
import TrabalhoED1ARN.elementos.ArvoreRN;
import java.util.Scanner;

public class TrabalhoED1ARN {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String op;
        ArvoreRN arvore = new ArvoreRN();
        
        op = input.nextLine();
        
        while(!op.equals("exit")){
            String[] comandoStrArray = op.split("\\s");
            //separa o comando dado pelo usuario em um array de Strings
            
            try {
                ComandoLinux comando = ComandoLinux.opcaoPelaString(comandoStrArray[0]);
                comando.getFuncao().fazFuncao(arvore, comandoStrArray);
                //A opção feita pelo usuario é recebida através de uma classe de Enum
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
            
            op = input.nextLine();
            
        }
    }
    
}
