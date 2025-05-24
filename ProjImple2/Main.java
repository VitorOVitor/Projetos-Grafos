package ProjImple2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String arquivo = "Projetos-Grafos/ProjImple2/graph-test-100-1.txt"; 
        Grafos grafo = lerArquivo(arquivo);
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("Digite o vértice que deseja investigar: ");
        int vert = teclado.nextInt();
        teclado.close();
        grafo.DFS(vert - 1);
        grafo.classificarArestas(vert - 1);
    }

    private static Grafos lerArquivo(String arquivo) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(arquivo));
        int vertices = scanner.nextInt();
        int arestas = scanner.nextInt();
        
        Grafos grafo = new Grafos(vertices);
        for (int i = 0; i < arestas; i++) {
            int origem = scanner.nextInt() - 1;
            int destino = scanner.nextInt() - 1;
            grafo.addAresta(origem, destino);
        }
        scanner.close();
        grafo.ordenarAdjacencias();
        return grafo;
    }
}