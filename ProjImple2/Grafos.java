package ProjImple2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;

class Grafos {
    private List<List<Integer>> adjacencias;
    private List<List<Integer>> predecessores;
    private int vertices;
    
    // Estruturas para DFS
    private int[] TD;
    private int[] TT;
    private int[] pai;
    private List<String> arestasArvore;

    public Grafos(int vertices) {
        this.vertices = vertices;
        adjacencias = new ArrayList<>(vertices);
        predecessores = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencias.add(new ArrayList<>());
            predecessores.add(new ArrayList<>());
        }
    }

    public void addAresta(int origem, int destino) {
        adjacencias.get(origem).add(destino);
        predecessores.get(destino).add(origem);
    }

    public void ordenarAdjacencias() {
        for (List<Integer> lista : adjacencias) {
            Collections.sort(lista);
        }
    }

    public void DFS(int inicio) {
        TD = new int[vertices];
        TT = new int[vertices];
        pai = new int[vertices];
        Arrays.fill(pai, -1);
        arestasArvore = new ArrayList<>();
        boolean[] visitados = new boolean[vertices];
        int[] tempo = {0};

        System.out.print("\nDFS a partir do vértice " + (inicio + 1) + ": ");
        DFSRecursivo(inicio, visitados, tempo);
        System.out.println("\nArestas de árvore: " + arestasArvore);
    }

    private void DFSRecursivo(int v, boolean[] visitados, int[] tempo) {
        visitados[v] = true;
        TD[v] = tempo[0]++;
        System.out.print((v + 1) + " ");

        for (int destino : adjacencias.get(v)) {
            if (!visitados[destino]) {
                pai[destino] = v;
                arestasArvore.add((v + 1) + "--" + (destino + 1));
                DFSRecursivo(destino, visitados, tempo);
            }
        }
        TT[v] = tempo[0]++;
    }

    public void classificarArestas(int vertice) {
        System.out.println("\nClassificação das arestas de " + (vertice + 1) + ":");
        for (int destino : adjacencias.get(vertice)) {
            String tipo;
            if (pai[destino] == vertice) {
                tipo = "Árvore";
            } else if (TD[destino] < TD[vertice] && TT[destino] > TT[vertice]) {
                tipo = "Retorno";
            } else if (TD[vertice] < TD[destino] && TT[vertice] > TT[destino]) {
                tipo = "Avanço";
            } else {
                tipo = "Cruzamento";
            }
            System.out.println((vertice + 1) + "--" + (destino + 1) + " - " + tipo);
        }
    }

    public int getVertices() {
        return vertices;
    }
}