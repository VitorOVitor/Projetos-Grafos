package ProjetoImplementação_1;

class Grafos{ //A estrutura sendo usada é a matriz de adjacência.
    private int[][] matriz;
    private int[] grauEntrada;
    private int[] grauSaida;
    private int vertices;


Grafos(int vertices){
    this.vertices=vertices; //Preenche o número de vértices, e coloca o tamanho adequado para o array
    matriz= new int [vertices][vertices];
    grauEntrada= new int[vertices];
    grauSaida=new int[vertices];
}

public void adicionarAresta(int vertice1, int vertice2){
    matriz[vertice1][vertice2]=1; //1 pois é uma aresta de "entrada"
    matriz[vertice2][vertice1]=-1; //-1 pois é uma aresta de "saída"
    grauEntrada[vertice1]++;
    grauSaida[vertice2]--;
}

public int getEntrada(int pos){
    return grauEntrada[pos];
}

public int getSaida(int pos){
    return grauSaida[pos];
}
}