package ProjetoImplementação_1;

class Grafos {
    int matriz[][];
    int arestas;
    int vertices;

    Grafos(int arestas, int vertices) {
        matriz = new int[arestas][vertices];
        this.arestas=arestas;
        this.vertices=vertices;
    }

    public void addAresta(int origem, int destino, int i){
        matriz[i][origem] = -1; //Adicionar uma aresta.
        matriz[i][destino] = 1;
    }

    public int getGrauEntrada(int vertice){
        int contador=0;
        for(int i=0; i<arestas; i++){
            if(matriz[i][vertice]==1){ //Pega todos os graus de entrada de um evrtice
                contador++;
            }
        }
        return contador;
    }

    public int getGrauSaida(int vertice){
        int contador=0;
        for(int i=0; i<arestas; i++){ //Pega todos os graus de saida de um vertice
            if(matriz[i][vertice]==-1){
                contador++;
            }
        }
        return contador;
    }

    public void getPredecessores(int vert) {
        System.out.print("Predecessores de " + vert + ": ");
        for (int j = 0; j < arestas; j++) {
            if (matriz[j][vert] == -1) { // Verifica as arestas que entram no vértice
                for (int i = 0; i < vertices; i++) {
                    if (matriz[j][i] == 1) { // Encontra o vértice de origem da aresta
                        System.out.print(i + " ");
                    }
                }
            }
        }
        System.out.println();
    }

    public void getSucessores(int vert) {
        System.out.print("Sucessores de " + vert + ": ");
        for (int j = 0; j < arestas; j++) {
            if (matriz[j][vert] == 1) { // Verifica as arestas que saem do vértice
                for (int i = 0; i < vertices; i++) {
                    if (matriz[j][i] == -1) { // Encontra o vértice de destino da aresta
                        System.out.print(i + " ");
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