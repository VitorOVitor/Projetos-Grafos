package ProjetoGrupo1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Digite o número de vértices desejado: ");
        int numVertices = teclado.nextInt();
        testarTodosTipos(numVertices);
        teclado.close();
    }

    private static void testarTodosTipos(int numVertices) {
        testarGrafo(numVertices, GeradorGrafos.TipoGrafo.EULERIANO);
        testarGrafo(numVertices, GeradorGrafos.TipoGrafo.SEMI_EULERIANO);
        testarGrafo(numVertices, GeradorGrafos.TipoGrafo.NAO_EULERIANO);
    }

    private static void testarGrafo(int numVertices, GeradorGrafos.TipoGrafo tipo) {
        System.out.println("\n=== Testando grafo " + tipo.name() + " com " + numVertices + " vértices ===");
        
        long inicioGeracao = System.currentTimeMillis();
        Grafos grafo = GeradorGrafos.gerarGrafo(numVertices, tipo);
        long tempoGeracao = System.currentTimeMillis() - inicioGeracao;
        System.out.println("Tempo de geração: " + tempoGeracao + " ms");

        long inicioNaive = System.currentTimeMillis();
        List<List<Integer>> pontesNaive = grafo.encontrarPontesNaive();
        long tempoNaive = System.currentTimeMillis() - inicioNaive;
        System.out.println("Método Naïve: " + tempoNaive + " ms | Pontes: " + pontesNaive.size());

        
        long inicioTarjan = System.currentTimeMillis();
        List<List<Integer>> pontesTarjan = grafo.encontrarPontesTarjan();
        long tempoTarjan = System.currentTimeMillis() - inicioTarjan;
        System.out.println("Método Tarjan: " + tempoTarjan + " ms | Pontes: " + pontesTarjan.size());
    }
}