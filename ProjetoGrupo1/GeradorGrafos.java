package ProjetoGrupo1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeradorGrafos {

    public enum TipoGrafo {
        EULERIANO,
        SEMI_EULERIANO,
        NAO_EULERIANO
    }

    public static Grafos gerarGrafo(int numVertices, TipoGrafo tipo) {
        if (tipo == TipoGrafo.EULERIANO) {
            return gerarGrafoEuleriano(numVertices);
        }
        else if (tipo == TipoGrafo.SEMI_EULERIANO) {
            return gerarGrafoSemiEuleriano(numVertices);
        }
        else if (tipo == TipoGrafo.NAO_EULERIANO) {
            return gerarGrafoNaoEuleriano(numVertices);
        }
        else {
            throw new IllegalArgumentException("Tipo de grafo não reconhecido: " + tipo);
        }
    }


    private static Grafos gerarGrafoEuleriano(int numVertices) {
        Grafos grafo = new Grafos(numVertices);
        Random rand = new Random();
        criarArvoreGeradora(grafo, numVertices, rand);
        ajustarParaGrausPares(grafo, numVertices, rand);
        return grafo;
    }


    private static Grafos gerarGrafoSemiEuleriano(int numVertices) {
        Grafos grafo = new Grafos(numVertices);
        Random rand = new Random();
        criarArvoreGeradora(grafo, numVertices, rand);
        ajustarParaDoisVerticesImpares(grafo, numVertices, rand);

        return grafo;
    }

    private static Grafos gerarGrafoNaoEuleriano(int numVertices) {
        Grafos grafo = new Grafos(numVertices);
        Random rand = new Random();

        if (rand.nextBoolean()) {
            criarGrafoDesconexo(grafo, numVertices, rand);
        } else {
            criarGrafoConexoNaoEuleriano(grafo, numVertices, rand);
        }

        return grafo;
    }

 
    private static void criarArvoreGeradora(Grafos grafo, int numVertices, Random rand) {
        for (int i = 1; i < numVertices; i++) {
            int pai = rand.nextInt(i);
            grafo.addAresta(pai, i);
            grafo.addAresta(i, pai); 
        }
    }

    private static void ajustarParaGrausPares(Grafos grafo, int numVertices, Random rand) {
        List<Integer> verticesImpares = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            if (grafo.getGrau(i) % 2 != 0) {
                verticesImpares.add(i);
            }
        }

 
        Collections.shuffle(verticesImpares, rand);
        for (int i = 0; i < verticesImpares.size(); i += 2) {
            if (i + 1 < verticesImpares.size()) {
                int u = verticesImpares.get(i);
                int v = verticesImpares.get(i + 1);
                grafo.addAresta(u, v);
            }
        }
    }

    private static void ajustarParaDoisVerticesImpares(Grafos grafo, int numVertices, Random rand) {
        List<Integer> verticesImpares = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            if (grafo.getGrau(i) % 2 != 0) {
                verticesImpares.add(i);
            }
        }

        if (verticesImpares.size() < 2) {
            int origem = rand.nextInt(numVertices);
            int destino;
            do {
                destino = rand.nextInt(numVertices);
            } while (destino == origem);
            grafo.addAresta(origem, destino);
        } else {
            Collections.shuffle(verticesImpares, rand);
            for (int i = 2; i < verticesImpares.size(); i += 2) {
                if (i + 1 < verticesImpares.size()) {
                    int origem = verticesImpares.get(i);
                    int destino = verticesImpares.get(i + 1);
                    grafo.addAresta(origem, destino);
                }
            }
        }
    }

    private static void criarGrafoDesconexo(Grafos grafo, int numVertices, Random rand) {
        int split = 1 + rand.nextInt(numVertices - 1);
        for (int i = 1; i < split; i++) {
            int pai = rand.nextInt(i);
            grafo.addAresta(pai, i);
        }
        
    
        for (int i = split; i < numVertices; i++) {
            int pai = split + rand.nextInt(i - split + 1);
            grafo.addAresta(pai, i);
        }
    }

    private static void criarGrafoConexoNaoEuleriano(Grafos grafo, int numVertices, Random rand) {
        criarArvoreGeradora(grafo, numVertices, rand);
        while (contarVerticesImpares(grafo, numVertices) <= 2) {
            int origem = rand.nextInt(numVertices);
            int destino;
            do {
                destino = rand.nextInt(numVertices);
            } while (origem == destino);
            grafo.addAresta(origem, destino);
        }
    }

    private static int contarVerticesImpares(Grafos grafo, int numVertices) {
        int count = 0;
        for (int i = 0; i < numVertices; i++) {
            if (grafo.getGrau(i) % 2 != 0) {
                count++;
            }
        }
        return count;
    }
}