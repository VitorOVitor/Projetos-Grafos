package ProjImple3;

class No implements Comparable<No> {
    int vertice;
    int distancia;
    int arestas;

    public No(int vertice, int distancia, int arestas) {
        this.vertice = vertice;
        this.distancia = distancia;
        this.arestas = arestas;
    }

    @Override
    public int compareTo(No outro) {
        if (this.distancia != outro.distancia) {
            return Integer.compare(this.distancia, outro.distancia);
        } else {
            return Integer.compare(this.arestas, outro.arestas);
        }
    }
}