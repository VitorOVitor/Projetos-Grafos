package ProjImple3;

import java.io.*;
import java.util.*;

public class Main {

    static class Aresta {
        int destino;
        int peso;

        public Aresta(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite o caminho do arquivo de entrada: ");
        String caminhoArquivo = scanner.nextLine().trim();
        
        try {
            BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
            
            // Restante do código mantido igual a partir daqui...
            String[] cabecalho = leitor.readLine().split(" ");
            int numVertices = Integer.parseInt(cabecalho[0]);
            int numArestas = Integer.parseInt(cabecalho[1]);

            List<List<Aresta>> grafo = new ArrayList<>();
            for (int i = 0; i < numVertices; i++) {
                grafo.add(new ArrayList<>());
            }

            for (int i = 0; i < numArestas; i++) {
                String[] aresta = leitor.readLine().split(" ");
                int origem = Integer.parseInt(aresta[0]);
                int destino = Integer.parseInt(aresta[1]);
                int peso = Integer.parseInt(aresta[2]);
                grafo.get(origem).add(new Aresta(destino, peso));
            }

            String[] busca = leitor.readLine().split(" ");
            int origem = Integer.parseInt(busca[0]);
            int destino = Integer.parseInt(busca[1]);
            leitor.close();

            int[] distancias = new int[numVertices];
            int[] qtdArestas = new int[numVertices];
            int[] anteriores = new int[numVertices];
            
            Arrays.fill(distancias, Integer.MAX_VALUE);
            Arrays.fill(qtdArestas, Integer.MAX_VALUE);
            Arrays.fill(anteriores, -1);
            
            distancias[origem] = 0;
            qtdArestas[origem] = 0;

            PriorityQueue<int[]> fila = new PriorityQueue<>(
                (a, b) -> a[1] != b[1] ? Integer.compare(a[1], b[1]) : Integer.compare(a[2], b[2])
            );
            
            fila.add(new int[]{origem, 0, 0});

            while (!fila.isEmpty()) {
                int[] atual = fila.poll();
                int vertice = atual[0];
                int dist = atual[1];
                int arestas = atual[2];

                if (vertice == destino) break;

                if (dist > distancias[vertice] || (dist == distancias[vertice] && arestas > qtdArestas[vertice])) {
                    continue;
                }

                for (Aresta proxima : grafo.get(vertice)) {
                    int novaDist = dist + proxima.peso;
                    int novasArestas = arestas + 1;
                    
                    if (novaDist < distancias[proxima.destino] || 
                       (novaDist == distancias[proxima.destino] && novasArestas < qtdArestas[proxima.destino])) {
                        
                        distancias[proxima.destino] = novaDist;
                        qtdArestas[proxima.destino] = novasArestas;
                        anteriores[proxima.destino] = vertice;
                        fila.add(new int[]{proxima.destino, novaDist, novasArestas});
                    }
                }
            }

            if (distancias[destino] == Integer.MAX_VALUE) {
                System.out.println("Não existe caminho entre " + origem + " e " + destino);
            } else {
                List<Integer> caminho = new ArrayList<>();
                int atual = destino;
                while (atual != -1) {
                    caminho.add(atual);
                    atual = anteriores[atual];
                }
                Collections.reverse(caminho);

                System.out.println("\nResultados:");
                System.out.println("Distância total: " + distancias[destino]);
                System.out.println("Quantidade de arestas: " + qtdArestas[destino]);
                System.out.println("Caminho percorrido: " + caminho.toString());
            }

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}