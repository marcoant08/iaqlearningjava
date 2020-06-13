package iaqlearning;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author MarcoAntônio, Wellison, Ítalo
 */
public class QLearning {
    private int R = 8, Q = 9, C = 1, P = 0, V = 2;
    
    public QLearning(){
        
    }
    
    public void iniciarVertices(int[][] labi, Vertice[][] v){
        for(int i=0; i<12; i++){//inicia os valores dos vertices
            for(int j=0; j<12; j++){
                v[i][j] = new Vertice(i, j, labi[i][j]);
            }
        }
        for(int i=0; i<12; i++){
            for(int j=0; j<12; j++){//adiciona todas as possiveis adjacencias nos vertices
                ArrayList<Vertice> adjacencias = new ArrayList<>();
                if(v[i][j].getValor() != P){
                    if(i-1>=0 && i+1<12){
                        if(v[i-1][j].getValor() != P) adjacencias.add(v[i-1][j]);
                        if(v[i+1][j].getValor() != P) adjacencias.add(v[i+1][j]);
                    }
                    if(j-1>=0 && j+1<12){
                        if(v[i][j-1].getValor() != P) adjacencias.add(v[i][j-1]);
                        if(v[i][j+1].getValor() != P) adjacencias.add(v[i][j+1]);
                    }
                    if(i==0) if(v[i+1][j].getValor() != P) adjacencias.add(v[i+1][j]);
                    if(i==11) if(v[i-1][j].getValor() != P) adjacencias.add(v[i-1][j]);
                    if(j==0) if(v[i][j+1].getValor() != P) adjacencias.add(v[i][j+1]);
                    if(j==11) if(v[i][j-1].getValor() != P) adjacencias.add(v[i][j-1]);
                }
                v[i][j].setAdjacencias(adjacencias);//as adjacencias possiveis são guardadas em um arraylist
            }
        }
    }
    
    public void QL(Double[][] tabQ, Vertice[][] lab, Double gm, int totalEp, int[][] labValores, ArrayList<int[][]> caminhos,  ArrayList<ArrayList<int[]>> allpassagens){//inicia a busca
        for(int i=0; i<totalEp; i++){// i será o numero de episodios
            if(i>=50) gm = 0.5;//quando tiver mais de 50 episodios, gamma passará a ser 0.5
            if(i>=150) gm = 0.1;//quando tiver mais de 150 episodios, gamma passará a ser 0.1
            if(i==totalEp-1) gm = 0.0;//no ultimo episodio ele sempre escolhera a maior recompensa
            
            //System.out.println("Episódio "+(i+1)+". gamma = "+gm+".");
            iniciarVertices(labValores, lab);//inicia os valores dos vertices antes de iniciar o episodio
            Episodio(tabQ, lab, gm, caminhos, i, allpassagens);
        }
    }
    
    public void Episodio(Double[][] tabQ, Vertice[][] v, Double gm, ArrayList<int[][]> caminhos, int epAtual,  ArrayList<ArrayList<int[]>> allpassagens){//inicia um novo episodio
        Vertice rato = v[0][0], queijo = v[11][11], anterior;//atribui as posições do rato e do queijo
        Random random = new Random();
        ArrayList<int[]> passagens = new ArrayList<>();//cria um arraylist que armazenará todas as linhas e colunas visitadas e atuais no episodio atual
        int[] pass = new int[4];//cria um vetor que armazenará a linha e a coluna que o rato visitou e que ele está
        
        while(!(rato.getLinha() == queijo.getLinha() && rato.getColuna() == queijo.getColuna())){
            anterior = rato;//o vertice anterior armazena a ultima posicao do rato
            rato = proximoVertice(v[rato.getLinha()][rato.getColuna()], rato, tabQ, gm);//rato vai para um novo vertice
            
            pass[0] = anterior.getLinha();//atribui a linha que o rato visitou ao vetor
            pass[1] = anterior.getColuna();//atribui a coluna que o rato visitou ao vetor
            pass[2] = rato.getLinha();//atribui a linha que o rato está ao vetor
            pass[3] = rato.getColuna();//atribui a coluna que o rato está ao vetor
            passagens.add(pass.clone());//adiciona num arraylist todas as linhas e colunas que o rato visitou
            
            rato.setValor(R);//atribui o valor R (int) para o vertice atual do rato
            anterior.setValor(V);//atribui o valor V (int) para informar que o vertice ja foi visitado pelo rato
            
            Double recompensa = 0.0;//cria o valor de recompensa
            if((rato.getLinha() == 11) && (rato.getColuna() == 11)) recompensa = 100.0;//atribui valor à recompensa
            
            atualizarTabQ(tabQ, recompensa, posicao(anterior), posicao(rato), rato, gm);//atribui valores à tabela Q
        }
        allpassagens.add(passagens);//adiciona num array o array que contem todos os caminhos percorridos neste episodio
        caminhos.add(caminhoPercorrido(v));//atribui a uma lista o caminho percorrido em cada episodio ate achar o queijo
        //a lista de caminhos também apresentará o numero de vertices visitados em cada episodio através do metodo size()
    }
    
    public int[][] caminhoPercorrido(Vertice[][] v){//retorna uma matriz com o valor do mapa (com caminhos percorridos)
        int[][] caminho = new int[12][12];
        for(int i=0; i<12; i++){
            for(int j=0; j<12; j++) caminho[i][j] = v[i][j].getValor();
        }
        return caminho;
    }
    
    public void atualizarTabQ(Double[][] tabQ, Double recompensa, int estado, int acao, Vertice rato, Double gm){//função que altera a tabela q
        if(tabQ[estado][acao] == 0) tabQ[estado][acao] = recompensa + gm*max(tabQ, rato);
    }
    
    public Vertice proximoVertice (Vertice v, Vertice rato, Double[][] tabQ, Double gm){//função que retorna o proximo vertice a ser visitado
        Random random = new Random();
        if(random.nextInt(100) < gm*100){
                int idProx = random.nextInt(v.getAdjacencias().size());//escolhe aleatoriamente um dos vertices adjacentes disponiveis
                return v.getAdjacencias().get(idProx);//vai para o prox vertice
            }else{
                int id = maiorRecompensa(tabQ, rato);//escolhe o vertice de maior recompensa
                return v.getAdjacencias().get(id);
            }
            
    }
    
    public int[][] atualizarCaminho(int[][] lab, int lineAtual, int columnAtual, int lineAnterior, int columnAnterior){//atualiza o mapa atribuindo valores de 'visitado' onde o rato percorreu
        int[][] c = lab;
        
        c[lineAtual][columnAtual] = R;
        c[lineAnterior][columnAnterior] = V;
        
        return c;
    }
    
    public int maiorRecompensa(Double[][] tabQ, Vertice v){//retorna o id da adjacencia de maior recompensa
        Random random = new Random(); 
        int id = random.nextInt(v.getAdjacencias().size());
        double max = 0;
        for(int i=0; i<v.getAdjacencias().size(); i++){
//            if(tabQ[posicao(v)][posicao(v.getAdjacencias().get(i))] == max){
//                id = random.nextInt(v.getAdjacencias().size());
//            }
            if(tabQ[posicao(v)][posicao(v.getAdjacencias().get(i))] > max){
                max  = tabQ[posicao(v)][posicao(v.getAdjacencias().get(i))];
                id = i;
            }
        }
        return id;
    }
    
    public int posicao(Vertice v){//auxilia na atribuição de valores à tabela q
        return v.getLinha()*12+v.getColuna();
    }
    
    public double max(Double[][] tabQ, Vertice a){//função que retorna o valor maximo de recompensa entre adjacencias
        Double max = 0.0;
        Random random = new Random();
        int id = random.nextInt(a.getAdjacencias().size());
        ArrayList<Vertice> adj = a.getAdjacencias();
        for(int i=0; i<a.getAdjacencias().size(); i++){
            if(tabQ[posicao(a)][posicao(adj.get(i))] > max) max = tabQ[posicao(a)][posicao(adj.get(i))];
        }
        
        return max;
    }
    
    public void zerarTabelaQ(Double[][] t){//zera a tabela q
        for(int i=0; i<144; i++){
            for(int j=0; j<144; j++) t[i][j] = 0.0;
        }
    }
    
}