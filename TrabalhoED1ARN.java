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
        long startTimeGeral = System.nanoTime();
        while(!op.equals("exit")){
            String[] comandoStrArray = op.split("\\s");
            //separa o comando dado pelo usuario em um array de Strings
            
            long startTime = 0;
            
            if(comandoStrArray[0].equals("search")){
                startTime = System.nanoTime();
            }
            
            try {
                ComandoLinux comando = ComandoLinux.opcaoPelaString(comandoStrArray[0]);
                comando.getFuncao().fazFuncao(arvore, comandoStrArray);
                //A opção feita pelo usuario é recebida através de uma classe de Enum
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
            
            if(comandoStrArray[0].equals("search")){
                long endTime = System.nanoTime() - startTime;
                double segundos = (double)endTime / 1000000.0;
                System.out.println("O tempo percorrido para a execucao do " + comandoStrArray[0] + " foi " + segundos + " microssegundos.");
            }
            op = input.nextLine();
            
        }
        long endTimeGeral = System.nanoTime() - startTimeGeral;
        double segundos = (double)endTimeGeral / 1000000000.0;
        System.out.println("O tempo percorrido para a execucao do programa foi " + segundos + " segundos.");
    }
    
}
