package ProjetoImplementação_1;

class Grafos {
    private int[][] matriz; // Matriz [aresta][vértice]
    private int[] grauEntrada; // Grau de entrada de cada vértice
    private int[] grauSaida;   // Grau de saída de cada vértice
    private int vertices;
    private int arestas;

    Grafos(int vertices, int arestas) {
        this.vertices = vertices;
        this.arestas = arestas;
        matriz = new int[arestas][vertices]; // Inicializa a matriz [aresta][vértice]
        grauEntrada = new int[vertices];
        grauSaida = new int[vertices];
    }

    // Adiciona uma aresta direcionada de 'origem' para 'destino'
    public void adicionarAresta(int idAresta, int origem, int destino) {
        // Marca a aresta na matriz
        matriz[idAresta][origem] = 1;   // Aresta sai de 'origem'
        matriz[idAresta][destino] = -1; // Aresta entra em 'destino'
        
        // Atualiza os graus
        grauSaida[origem]++; 
        grauEntrada[destino]++; 
    }

    // Retorna o grau de entrada (arestas ENTRANDO no vértice)
    public int getGrauEntrada(int vert) {
        return grauEntrada[vert];
    }

    // Retorna o grau de saída (arestas SAINDO do vértice)
    public int getGrauSaida(int vert) {
        return grauSaida[vert];
    }

    // Imprime os predecessores (vértices que APONTAM para 'vert')
    public void getPredecessores(int vert) {
        System.out.print("Predecessores de " + vert + ": ");
        for (int j = 0; j < arestas; j++) {
            if (matriz[j][vert] == -1) { // Verifica as arestas que entram no vértice
                for (int i = 0; i < vertices; i++) {
                    if (matriz[j][i] == 1) { // Encontra o vértice de origem da aresta
                        System.out.print(i + " ");
                        break;
                    }
                }
            }
        }
        System.out.println();
    }

    // Imprime os sucessores (vértices APONTADOS por 'vert')
    public void getSucessores(int vert) {
        System.out.print("Sucessores de " + vert + ": ");
        for (int j = 0; j < arestas; j++) {
            if (matriz[j][vert] == 1) { // Verifica as arestas que saem do vértice
                for (int i = 0; i < vertices; i++) {
                    if (matriz[j][i] == -1) { // Encontra o vértice de destino da aresta
                        System.out.print(i + " ");
                        break;
                    }
                }
            }
        }
        System.out.println();
    }

    public void imprimirMatriz() {
        System.out.println("Matriz [Aresta][Vértice]:");
        for (int i = 0; i < arestas; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}