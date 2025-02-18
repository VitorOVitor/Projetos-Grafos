package ProjetoImplementação_1;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner teclado = new Scanner(System.in);
        String arquivoTxt = "ProjetoImplementação_1/graph-test-100-1.txt";
        Grafos grafoMatriz = new Grafos(779); // Coloque aqui o numero de arestas do arquivo sendo lido.
        LerArquivo(arquivoTxt, grafoMatriz);// Leitura do arquivo e geração da matriz
        grafoMatriz.imprimirMatriz(); // Impressao para a visualizacao da matriz
        String teste = teclado.nextLine();
        while (!teste.equals("FIM")) { // Enquanto teste fôr diferente de FIM, o usuário continuará testando vértices
            int vert = Integer.parseInt(teste); // Converte para um inteiro
            int saida = grafoMatriz.getGrauSaida(vert);
            System.out.println("O grau de saida deste vertice e de: " + saida);
            int entrada = grafoMatriz.getGrauEntrada(vert);
            System.out.println("O grau de entrada deste vertice e de: " + entrada);
            System.out.println("Os predecessores deste vertice sao: ");
            grafoMatriz.getPredecessores(vert);
            System.out.println("Os sucessores deste vertice sao: ");
            grafoMatriz.getSucessores(vert);
            teste = teclado.nextLine();
        }
        teclado.close();
    }

    public static void LerArquivo(String arquivo, Grafos grafoMatriz) throws IOException {
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            leitor.readLine(); // Pula o cabeçalho.
            String linha;

            while ((linha = leitor.readLine()) != null) {
                linha = linha.trim(); // Remove espaços do início e fim
                if (linha.isEmpty())
                    continue; // Ignora linhas vazias após o trim()

                // Divide por um ou mais espaços
                String[] tokens = linha.split("\\s+");

                try {
                    int vertice1 = Integer.parseInt(tokens[0]);
                    int vertice2 = Integer.parseInt(tokens[1]);
                    grafoMatriz.adicionarAresta(vertice1, vertice2);
                } catch (NumberFormatException e) {
                    System.out.println("Erro.");
                }
            }
        }
    }
}