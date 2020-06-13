
package iaqlearning;

import java.util.ArrayList;

/**
 *
 * @author MarcoAntônio, Wellison, Ítalo
 */
public class Vertice {
    int linha, coluna, valor;
    ArrayList<Vertice> adjacencias = new ArrayList<>();
    
    public Vertice(int l, int c, int v){
        setLinha(l);
        setColuna(c);
        setValor(v);
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public ArrayList<Vertice> getAdjacencias() {
        return adjacencias;
    }

    public void setAdjacencias(ArrayList<Vertice> adjacencias) {
        this.adjacencias = adjacencias;
    }
    
}
