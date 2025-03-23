package ProjetoImplementação_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner teclado = new Scanner(System.in);
        String arquivo = "ProjetoImplementacao_1/graph-test-100-1.txt"; //Mudar o path/nome do arquivo
        LerArquivo(arquivo);
        teclado.close();
    }

    public static void LerArquivo(String arquivo) throws FileNotFoundException {
        File leitor = new File(arquivo);
        Scanner teclado = new Scanner(leitor);
        int vertices=teclado.nextInt(); //Lê o cabeçalho, pegando as arestas e vertices de lá
        int arestas=teclado.nextInt();
        Grafos matriz= new Grafos(arestas, vertices);
        int i=0;
        while(teclado.hasNext()){
            int origem=teclado.nextInt();
            int destino=teclado.nextInt();
            matriz.addAresta(origem-1, destino-1, i); //Enquanto tiverem linhas, ele as lerá e armazenará na matriz
            i++;
        }
        teclado = new Scanner(System.in);
        System.out.println("O arquivo foi lido e armazenado. Por favor, digite o vertice que voce deseja investigar:");
       int vert= teclado.nextInt();
       int entrada= matriz.getGrauEntrada(vert-1);
       int saida= matriz.getGrauSaida(vert-1); 
       System.out.println("O grau de entrada e: " +entrada); //Faz as operações com os vértices relativos
       System.out.println("O grau de saida e: " +saida);
       matriz.getSucessores(vert);
       matriz.getPredecessores(vert);
       teclado.close();
    }
}