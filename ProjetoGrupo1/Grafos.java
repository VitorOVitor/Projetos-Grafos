package ProjetoGrupo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.Set;

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
        int[] tempo = { 0 };

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

    public boolean isConexo() {
        if (vertices == 0) return true;
    
        boolean[] visitados = new boolean[vertices];
        Queue<Integer> fila = new LinkedList<>();
        int indicePrimeiroVertice = 0;
        while (indicePrimeiroVertice < vertices && adjacencias.get(indicePrimeiroVertice).isEmpty()) {
            indicePrimeiroVertice++;
        }
        if (indicePrimeiroVertice == vertices) return true; 
    
        fila.add(indicePrimeiroVertice);
        visitados[indicePrimeiroVertice] = true;
    
        while (!fila.isEmpty()) {
            int vertice = fila.poll();
            for (int vizinho : adjacencias.get(vertice)) {
                if (!visitados[vizinho]) {
                    visitados[vizinho] = true;
                    fila.add(vizinho);
                }
            }
        }
    
        for (int i = 0; i < vertices; i++) {
            if (!adjacencias.get(i).isEmpty() && !visitados[i]) {
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> encontrarPontesNaive() {
        List<List<Integer>> pontes = new ArrayList<>();
        Set<String> arestasUnicas = new HashSet<>();
        for (int verts = 0; verts < vertices; verts++) {
            for (int vizinhos : adjacencias.get(verts)) {
                if (verts < vizinhos) {
                    arestasUnicas.add(verts + "," + vizinhos);
                }
            }
        }
        for (String aresta : arestasUnicas) {
            String[] partes = aresta.split(",");
            int origem = Integer.parseInt(partes[0]);
            int destino = Integer.parseInt(partes[1]);
            Grafos copia = copiarGrafo();
            for (int i = 0; i < vertices; i++) {
                copia.adjacencias.add(new ArrayList<>(this.adjacencias.get(i)));
            }
            copia.adjacencias.get(origem).remove(Integer.valueOf(destino));
            copia.adjacencias.get(destino).remove(Integer.valueOf(origem));
            if (!copia.isConexo()) {
                pontes.add(Arrays.asList(origem, destino));
            }
        }

        return pontes;
    }

    public List<List<Integer>> encontrarPontesTarjan() {
        List<List<Integer>> pontes = new ArrayList<>();
        int[] descoberta = new int[vertices];
        int[] menorValor = new int[vertices];
        Arrays.fill(descoberta, -1);
        int[] tempo = { 0 };

        for (int u = 0; u < vertices; u++) {
            if (descoberta[u] == -1) {
                tarjanDFS(u, -1, descoberta, menorValor, tempo, pontes);
            }
        }

        return pontes;
    }

    private void tarjanDFS(int u, int pai, int[] descoberta, int[] menorValor, int[] tempo, List<List<Integer>> pontes) {
        descoberta[u] = tempo[0];
        menorValor[u] = tempo[0];
        tempo[0]++;
        for (int v : adjacencias.get(u)) {
            if (v == pai)
                continue;
            if (descoberta[v] == -1) {
                tarjanDFS(v, u, descoberta, menorValor, tempo, pontes);
                menorValor[u] = Math.min(menorValor[u], menorValor[v]);
                if (menorValor[v] > descoberta[u]) {
                    pontes.add(Arrays.asList(u, v));
                }
            } 
            else {
                menorValor[u] = Math.min(menorValor[u], descoberta[v]);
            }
        }
    }

    public boolean isCaminhoEuleriano() {
        if (!isConexo()) return false;
        
        int contImpar = 0;
        for (int i = 0; i < vertices; i++) {
            if (getGrau(i) % 2 != 0) contImpar++;
        }
        return contImpar == 0 || contImpar == 2;
    }
    
  
    public int getGrau(int vertice) {
        return adjacencias.get(vertice).size();
    }
    
  
    private int acharVerticeComeco() {
        int comeco = 0;
        for (int i = 0; i < vertices; i++) {
            if (getGrau(i) % 2 != 0) return i;
            if (getGrau(i) > 0) comeco = i;
        }
        return comeco;
    }
    
    
    public Grafos copiarGrafo() {
        Grafos copia = new Grafos(this.vertices);
        for (int i = 0; i < vertices; i++) {
            copia.adjacencias.get(i).addAll(this.adjacencias.get(i));
        }
        return copia;
    }

    public int getVertices() {
        return vertices;
    }

    public List<Integer> fleuryNaive() {
        List<Integer> caminho = new ArrayList<>();
        if (!isCaminhoEuleriano()) return caminho;
    
        Grafos copia = copiarGrafo();
        int verticeAtual = acharVerticeComeco();
        caminho.add(verticeAtual + 1); // Ajuste para 1-based
    
        while (copia.getGrau(verticeAtual) > 0) {
            List<Integer> vizinhos = new ArrayList<>(copia.adjacencias.get(verticeAtual));
            
            for (int vizinho : vizinhos) {
                copia.removerAresta(verticeAtual, vizinho);
                boolean isPonte = !copia.isConexo();
                copia.addAresta(vizinho, verticeAtual);
                
                if (!isPonte) {
                    copia.removerAresta(verticeAtual, vizinho);
                    caminho.add(vizinho + 1);
                    verticeAtual = vizinho;
                    break;
                }
            }
        }
        
        return caminho;
    }

    public List<Integer> fleuryTarjan() {
        List<Integer> caminho = new ArrayList<>();
        if (!isCaminhoEuleriano()) return caminho;
    
        Grafos copia = copiarGrafo();
        int verticeAtual = acharVerticeComeco();
        caminho.add(verticeAtual + 1);
    
        while (copia.getGrau(verticeAtual) > 0) {
            List<List<Integer>> pontes = copia.encontrarPontesTarjan();
            List<Integer> vizinhos = new ArrayList<>(copia.adjacencias.get(verticeAtual));
            
            int next = -1;
            for (int vizinho : vizinhos) {
                boolean isPonte = false;
                for (List<Integer> ponte : pontes) {
                    if ((ponte.get(0) == verticeAtual && ponte.get(1) == vizinho) ||
                        (ponte.get(0) == vizinho && ponte.get(1) == verticeAtual)) {
                        isPonte = true;
                        break;
                    }
                }
                
                if (!isPonte) {
                    next = vizinho;
                    break;
                }
            }
    
            if (next == -1) next = vizinhos.get(0); // Única opção é uma ponte
            
            copia.removerAresta(verticeAtual, next);
            caminho.add(next + 1);
            verticeAtual = next;
        }
        
        return caminho;
    }

    public void removerAresta(int origem, int destino) {
        adjacencias.get(origem).remove(Integer.valueOf(destino));
        adjacencias.get(destino).remove(Integer.valueOf(origem));
    }
    
   
}