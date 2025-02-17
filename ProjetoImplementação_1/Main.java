package ProjetoImplementação_1;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Main{
    public static void main(String[]  args) throws FileNotFoundException, IOException{
        Scanner teclado= new Scanner(System.in);
        String arquivoTxt= "ProjetoImplementação_1/graph-test-100-1.txt";
        Grafos grafoMatriz= new Grafos(100); //Coloque aqui o numero de arestas do arquivo sendo lido.
        LerArquivo(arquivoTxt, grafoMatriz);

    }

    public static void LerArquivo(String arquivo, Grafos grafoMatriz) throws FileNotFoundException, IOException{
    try(BufferedReader leitor = new BufferedReader(new FileReader(arquivo))  ){
    leitor.readLine(); //Pula o cabeçalho. 
    String linha;

    while((linha=leitor.readLine())!=null){//Enquanto tiver o que ler, será lido
    String [] tokens= linha.split("//s+"); //Os pares demonstram arestas. O primeiro elemento é de onde sai, o segundo para onde vai
    int vertice1= Integer.parseInt(tokens[0]);
    int vertice2= Integer.parseInt(tokens[1]); 
    grafoMatriz.adicionarAresta(vertice1, vertice2);
    }
    }
    }
}