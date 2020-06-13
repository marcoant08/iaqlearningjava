/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import iaqlearning.QLearning;
import iaqlearning.Vertice;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author MarcoAntônio, Ítalo, Wellison
 */
public class Tela extends javax.swing.JFrame {

    private ImageIcon rato, queijo, parede, chao, caminho;
    private QLearning ql;
    private int R = 8, Q = 9, C = 1, P = 0, V = 2, episodios, idframe = 0;
    private Double gamma;
    private Double[][] tabelaQ = new Double[144][144];
    private Vertice[][] labCompleto = new Vertice[12][12];
    private ArrayList<int[][]> caminhos = new ArrayList<>();
    private ArrayList<ArrayList<int[]>> verticespercorridos = new ArrayList<>();
    private int[][] labValores = new int[12][12];
    private int[][] ambiente = new int[12][12];
    private ArrayList<int[][]> frames = new ArrayList<int[][]>();
    private boolean emexecucao = false, iniciou = false;
    
    public Tela() {
        initComponents();
        rato = new ImageIcon("src\\imagens\\rato_32x34p.PNG");
        queijo = new ImageIcon("src\\imagens\\queijo_32x34p.PNG");
        parede = new ImageIcon("src\\imagens\\parede_32x34p.PNG");
        chao = new ImageIcon("src\\imagens\\chao_32x34p.PNG");
        caminho = new ImageIcon("src\\imagens\\caminho_32x34p.PNG");
        ql = new QLearning();
        panelCaminhoPercorrido.setVisible(false);
        panelPassoPasso.setVisible(false);
        panel.setVisible(false);
        buttonExecutar.setEnabled(false);
        NroEpisodios.setEnabled(false);
        buttonEncerrarExec.setEnabled(false);
        buttonProxEp.setEnabled(false);
        buttonEpAnterior.setEnabled(false);
        buttonProxPasso.setEnabled(false);
        buttonPassoAnterior.setEnabled(false);
        buttonParar.setEnabled(false);
        buttonPararTipoExec.setEnabled(false);
        buttonMostrarCaminhosPercorridos.setEnabled(false);
        buttonPercorrerPassoPasso.setEnabled(false);
    }

    public void setLabValores(int[][] labValores) {
        this.labValores = labValores;
    }
    
    public void proximoFrame(int[][] amb, int[] coordenadas){//atualiza o mapa de frames com o rato em uma nova posição
        for(int i=0; i<12; i++){
            for(int j=0; j<12; j++){
                if(i == coordenadas[0] && j == coordenadas[1]) amb[i][j] = V;
                if(i == coordenadas[2] && j == coordenadas[3]) amb[i][j] = R;
            }
        }
    }
    
    public int posicao(int l, int c){//auxilia na impressao dos valores da tabela q
        return l*12+c;
    }
    
    public int[][] copiarAmb(int[][] a){//retorna a copa da matriz recebida (basicamente um .clone())
        int[][] amb = new int[12][12];
        for(int i=0; i<12; i++){
            for(int j=0; j<12; j++){
                amb[i][j] = a[i][j];
            }
        }
        return amb;
    }
    
    public int[][] iniciarAmbiente(){//iguala os valores do mapa ambiente aos do mapa de valores enviado da main
        int[][] amb = new int[12][12];
        for(int i=0; i<12; i++){
            for(int j=0; j<12; j++){
                amb[i][j] = this.labValores[i][j];
            }
        }
        return amb;
    }
    
    
    public void atualizarLabels(int[][] mat){
        if(mat[0][0] == R) l0c0.setIcon(rato);
        if(mat[0][1] == R) l0c1.setIcon(rato);
        if(mat[0][2] == R) l0c2.setIcon(rato);
        if(mat[0][3] == R) l0c3.setIcon(rato);
        if(mat[0][4] == R) l0c4.setIcon(rato);
        if(mat[0][5] == R) l0c5.setIcon(rato);
        if(mat[0][6] == R) l0c6.setIcon(rato);
        if(mat[0][7] == R) l0c7.setIcon(rato);
        if(mat[0][8] == R) l0c8.setIcon(rato);
        if(mat[0][9] == R) l0c9.setIcon(rato);
        if(mat[0][10] == R) l0c10.setIcon(rato);
        if(mat[0][11] == R) l0c11.setIcon(rato);
        if(mat[1][0] == R) l1c0.setIcon(rato);
        if(mat[1][1] == R) l1c1.setIcon(rato);
        if(mat[1][2] == R) l1c2.setIcon(rato);
        if(mat[1][3] == R) l1c3.setIcon(rato);
        if(mat[1][4] == R) l1c4.setIcon(rato);
        if(mat[1][5] == R) l1c5.setIcon(rato);
        if(mat[1][6] == R) l1c6.setIcon(rato);
        if(mat[1][7] == R) l1c7.setIcon(rato);
        if(mat[1][8] == R) l1c8.setIcon(rato);
        if(mat[1][9] == R) l1c9.setIcon(rato);
        if(mat[1][10] == R) l1c10.setIcon(rato);
        if(mat[1][11] == R) l1c11.setIcon(rato);
        if(mat[2][0] == R) l2c0.setIcon(rato);
        if(mat[2][1] == R) l2c1.setIcon(rato);
        if(mat[2][2] == R) l2c2.setIcon(rato);
        if(mat[2][3] == R) l2c3.setIcon(rato);
        if(mat[2][4] == R) l2c4.setIcon(rato);
        if(mat[2][5] == R) l2c5.setIcon(rato);
        if(mat[2][6] == R) l2c6.setIcon(rato);
        if(mat[2][7] == R) l2c7.setIcon(rato);
        if(mat[2][8] == R) l2c8.setIcon(rato);
        if(mat[2][9] == R) l2c9.setIcon(rato);
        if(mat[2][10] == R) l2c10.setIcon(rato);
        if(mat[2][11] == R) l2c11.setIcon(rato);
        if(mat[3][0] == R) l3c0.setIcon(rato);
        if(mat[3][1] == R) l3c1.setIcon(rato);
        if(mat[3][2] == R) l3c2.setIcon(rato);
        if(mat[3][3] == R) l3c3.setIcon(rato);
        if(mat[3][4] == R) l3c4.setIcon(rato);
        if(mat[3][5] == R) l3c5.setIcon(rato);
        if(mat[3][6] == R) l3c6.setIcon(rato);
        if(mat[3][7] == R) l3c7.setIcon(rato);
        if(mat[3][8] == R) l3c8.setIcon(rato);
        if(mat[3][9] == R) l3c9.setIcon(rato);
        if(mat[3][10] == R) l3c10.setIcon(rato);
        if(mat[3][11] == R) l3c11.setIcon(rato);
        if(mat[4][0] == R) l4c0.setIcon(rato);
        if(mat[4][1] == R) l4c1.setIcon(rato);
        if(mat[4][2] == R) l4c2.setIcon(rato);
        if(mat[4][3] == R) l4c3.setIcon(rato);
        if(mat[4][4] == R) l4c4.setIcon(rato);
        if(mat[4][5] == R) l4c5.setIcon(rato);
        if(mat[4][6] == R) l4c6.setIcon(rato);
        if(mat[4][7] == R) l4c7.setIcon(rato);
        if(mat[4][8] == R) l4c8.setIcon(rato);
        if(mat[4][9] == R) l4c9.setIcon(rato);
        if(mat[4][10] == R) l4c10.setIcon(rato);
        if(mat[4][11] == R) l4c11.setIcon(rato);
        if(mat[5][0] == R) l5c0.setIcon(rato);
        if(mat[5][1] == R) l5c1.setIcon(rato);
        if(mat[5][2] == R) l5c2.setIcon(rato);
        if(mat[5][3] == R) l5c3.setIcon(rato);
        if(mat[5][4] == R) l5c4.setIcon(rato);
        if(mat[5][5] == R) l5c5.setIcon(rato);
        if(mat[5][6] == R) l5c6.setIcon(rato);
        if(mat[5][7] == R) l5c7.setIcon(rato);
        if(mat[5][8] == R) l5c8.setIcon(rato);
        if(mat[5][9] == R) l5c9.setIcon(rato);
        if(mat[5][10] == R) l5c10.setIcon(rato);
        if(mat[5][11] == R) l5c11.setIcon(rato);
        if(mat[6][0] == R) l6c0.setIcon(rato);
        if(mat[6][1] == R) l6c1.setIcon(rato);
        if(mat[6][2] == R) l6c2.setIcon(rato);
        if(mat[6][3] == R) l6c3.setIcon(rato);
        if(mat[6][4] == R) l6c4.setIcon(rato);
        if(mat[6][5] == R) l6c5.setIcon(rato);
        if(mat[6][6] == R) l6c6.setIcon(rato);
        if(mat[6][7] == R) l6c7.setIcon(rato);
        if(mat[6][8] == R) l6c8.setIcon(rato);
        if(mat[6][9] == R) l6c9.setIcon(rato);
        if(mat[6][10] == R) l6c10.setIcon(rato);
        if(mat[6][11] == R) l6c11.setIcon(rato);
        if(mat[7][0] == R) l7c0.setIcon(rato);
        if(mat[7][1] == R) l7c1.setIcon(rato);
        if(mat[7][2] == R) l7c2.setIcon(rato);
        if(mat[7][3] == R) l7c3.setIcon(rato);
        if(mat[7][4] == R) l7c4.setIcon(rato);
        if(mat[7][5] == R) l7c5.setIcon(rato);
        if(mat[7][6] == R) l7c6.setIcon(rato);
        if(mat[7][7] == R) l7c7.setIcon(rato);
        if(mat[7][8] == R) l7c8.setIcon(rato);
        if(mat[7][9] == R) l7c9.setIcon(rato);
        if(mat[7][10] == R) l7c10.setIcon(rato);
        if(mat[7][11] == R) l7c11.setIcon(rato);
        if(mat[8][0] == R) l8c0.setIcon(rato);
        if(mat[8][1] == R) l8c1.setIcon(rato);
        if(mat[8][2] == R) l8c2.setIcon(rato);
        if(mat[8][3] == R) l8c3.setIcon(rato);
        if(mat[8][4] == R) l8c4.setIcon(rato);
        if(mat[8][5] == R) l8c5.setIcon(rato);
        if(mat[8][6] == R) l8c6.setIcon(rato);
        if(mat[8][7] == R) l8c7.setIcon(rato);
        if(mat[8][8] == R) l8c8.setIcon(rato);
        if(mat[8][9] == R) l8c9.setIcon(rato);
        if(mat[8][10] == R) l8c10.setIcon(rato);
        if(mat[8][11] == R) l8c11.setIcon(rato);
        if(mat[9][0] == R) l9c0.setIcon(rato);
        if(mat[9][1] == R) l9c1.setIcon(rato);
        if(mat[9][2] == R) l9c2.setIcon(rato);
        if(mat[9][3] == R) l9c3.setIcon(rato);
        if(mat[9][4] == R) l9c4.setIcon(rato);
        if(mat[9][5] == R) l9c5.setIcon(rato);
        if(mat[9][6] == R) l9c6.setIcon(rato);
        if(mat[9][7] == R) l9c7.setIcon(rato);
        if(mat[9][8] == R) l9c8.setIcon(rato);
        if(mat[9][9] == R) l9c9.setIcon(rato);
        if(mat[9][10] == R) l9c10.setIcon(rato);
        if(mat[9][11] == R) l9c11.setIcon(rato);
        if(mat[10][0] == R) l10c0.setIcon(rato);
        if(mat[10][1] == R) l10c1.setIcon(rato);
        if(mat[10][2] == R) l10c2.setIcon(rato);
        if(mat[10][3] == R) l10c3.setIcon(rato);
        if(mat[10][4] == R) l10c4.setIcon(rato);
        if(mat[10][5] == R) l10c5.setIcon(rato);
        if(mat[10][6] == R) l10c6.setIcon(rato);
        if(mat[10][7] == R) l10c7.setIcon(rato);
        if(mat[10][8] == R) l10c8.setIcon(rato);
        if(mat[10][9] == R) l10c9.setIcon(rato);
        if(mat[10][10] == R) l10c10.setIcon(rato);
        if(mat[10][11] == R) l10c11.setIcon(rato);
        if(mat[11][0] == R) l11c0.setIcon(rato);
        if(mat[11][1] == R) l11c1.setIcon(rato);
        if(mat[11][2] == R) l11c2.setIcon(rato);
        if(mat[11][3] == R) l11c3.setIcon(rato);
        if(mat[11][4] == R) l11c4.setIcon(rato);
        if(mat[11][5] == R) l11c5.setIcon(rato);
        if(mat[11][6] == R) l11c6.setIcon(rato);
        if(mat[11][7] == R) l11c7.setIcon(rato);
        if(mat[11][8] == R) l11c8.setIcon(rato);
        if(mat[11][9] == R) l11c9.setIcon(rato);
        if(mat[11][10] == R) l11c10.setIcon(rato);
        if(mat[11][11] == R) l11c11.setIcon(rato);
        
        if(mat[0][0] == C) l0c0.setIcon(chao);
        if(mat[0][1] == C) l0c1.setIcon(chao);
        if(mat[0][2] == C) l0c2.setIcon(chao);
        if(mat[0][3] == C) l0c3.setIcon(chao);
        if(mat[0][4] == C) l0c4.setIcon(chao);
        if(mat[0][5] == C) l0c5.setIcon(chao);
        if(mat[0][6] == C) l0c6.setIcon(chao);
        if(mat[0][7] == C) l0c7.setIcon(chao);
        if(mat[0][8] == C) l0c8.setIcon(chao);
        if(mat[0][9] == C) l0c9.setIcon(chao);
        if(mat[0][10] == C) l0c10.setIcon(chao);
        if(mat[0][11] == C) l0c11.setIcon(chao);
        if(mat[1][0] == C) l1c0.setIcon(chao);
        if(mat[1][1] == C) l1c1.setIcon(chao);
        if(mat[1][2] == C) l1c2.setIcon(chao);
        if(mat[1][3] == C) l1c3.setIcon(chao);
        if(mat[1][4] == C) l1c4.setIcon(chao);
        if(mat[1][5] == C) l1c5.setIcon(chao);
        if(mat[1][6] == C) l1c6.setIcon(chao);
        if(mat[1][7] == C) l1c7.setIcon(chao);
        if(mat[1][8] == C) l1c8.setIcon(chao);
        if(mat[1][9] == C) l1c9.setIcon(chao);
        if(mat[1][10] == C) l1c10.setIcon(chao);
        if(mat[1][11] == C) l1c11.setIcon(chao);
        if(mat[2][0] == C) l2c0.setIcon(chao);
        if(mat[2][1] == C) l2c1.setIcon(chao);
        if(mat[2][2] == C) l2c2.setIcon(chao);
        if(mat[2][3] == C) l2c3.setIcon(chao);
        if(mat[2][4] == C) l2c4.setIcon(chao);
        if(mat[2][5] == C) l2c5.setIcon(chao);
        if(mat[2][6] == C) l2c6.setIcon(chao);
        if(mat[2][7] == C) l2c7.setIcon(chao);
        if(mat[2][8] == C) l2c8.setIcon(chao);
        if(mat[2][9] == C) l2c9.setIcon(chao);
        if(mat[2][10] == C) l2c10.setIcon(chao);
        if(mat[2][11] == C) l2c11.setIcon(chao);
        if(mat[3][0] == C) l3c0.setIcon(chao);
        if(mat[3][1] == C) l3c1.setIcon(chao);
        if(mat[3][2] == C) l3c2.setIcon(chao);
        if(mat[3][3] == C) l3c3.setIcon(chao);
        if(mat[3][4] == C) l3c4.setIcon(chao);
        if(mat[3][5] == C) l3c5.setIcon(chao);
        if(mat[3][6] == C) l3c6.setIcon(chao);
        if(mat[3][7] == C) l3c7.setIcon(chao);
        if(mat[3][8] == C) l3c8.setIcon(chao);
        if(mat[3][9] == C) l3c9.setIcon(chao);
        if(mat[3][10] == C) l3c10.setIcon(chao);
        if(mat[3][11] == C) l3c11.setIcon(chao);
        if(mat[4][0] == C) l4c0.setIcon(chao);
        if(mat[4][1] == C) l4c1.setIcon(chao);
        if(mat[4][2] == C) l4c2.setIcon(chao);
        if(mat[4][3] == C) l4c3.setIcon(chao);
        if(mat[4][4] == C) l4c4.setIcon(chao);
        if(mat[4][5] == C) l4c5.setIcon(chao);
        if(mat[4][6] == C) l4c6.setIcon(chao);
        if(mat[4][7] == C) l4c7.setIcon(chao);
        if(mat[4][8] == C) l4c8.setIcon(chao);
        if(mat[4][9] == C) l4c9.setIcon(chao);
        if(mat[4][10] == C) l4c10.setIcon(chao);
        if(mat[4][11] == C) l4c11.setIcon(chao);
        if(mat[5][0] == C) l5c0.setIcon(chao);
        if(mat[5][1] == C) l5c1.setIcon(chao);
        if(mat[5][2] == C) l5c2.setIcon(chao);
        if(mat[5][3] == C) l5c3.setIcon(chao);
        if(mat[5][4] == C) l5c4.setIcon(chao);
        if(mat[5][5] == C) l5c5.setIcon(chao);
        if(mat[5][6] == C) l5c6.setIcon(chao);
        if(mat[5][7] == C) l5c7.setIcon(chao);
        if(mat[5][8] == C) l5c8.setIcon(chao);
        if(mat[5][9] == C) l5c9.setIcon(chao);
        if(mat[5][10] == C) l5c10.setIcon(chao);
        if(mat[5][11] == C) l5c11.setIcon(chao);
        if(mat[6][0] == C) l6c0.setIcon(chao);
        if(mat[6][1] == C) l6c1.setIcon(chao);
        if(mat[6][2] == C) l6c2.setIcon(chao);
        if(mat[6][3] == C) l6c3.setIcon(chao);
        if(mat[6][4] == C) l6c4.setIcon(chao);
        if(mat[6][5] == C) l6c5.setIcon(chao);
        if(mat[6][6] == C) l6c6.setIcon(chao);
        if(mat[6][7] == C) l6c7.setIcon(chao);
        if(mat[6][8] == C) l6c8.setIcon(chao);
        if(mat[6][9] == C) l6c9.setIcon(chao);
        if(mat[6][10] == C) l6c10.setIcon(chao);
        if(mat[6][11] == C) l6c11.setIcon(chao);
        if(mat[7][0] == C) l7c0.setIcon(chao);
        if(mat[7][1] == C) l7c1.setIcon(chao);
        if(mat[7][2] == C) l7c2.setIcon(chao);
        if(mat[7][3] == C) l7c3.setIcon(chao);
        if(mat[7][4] == C) l7c4.setIcon(chao);
        if(mat[7][5] == C) l7c5.setIcon(chao);
        if(mat[7][6] == C) l7c6.setIcon(chao);
        if(mat[7][7] == C) l7c7.setIcon(chao);
        if(mat[7][8] == C) l7c8.setIcon(chao);
        if(mat[7][9] == C) l7c9.setIcon(chao);
        if(mat[7][10] == C) l7c10.setIcon(chao);
        if(mat[7][11] == C) l7c11.setIcon(chao);
        if(mat[8][0] == C) l8c0.setIcon(chao);
        if(mat[8][1] == C) l8c1.setIcon(chao);
        if(mat[8][2] == C) l8c2.setIcon(chao);
        if(mat[8][3] == C) l8c3.setIcon(chao);
        if(mat[8][4] == C) l8c4.setIcon(chao);
        if(mat[8][5] == C) l8c5.setIcon(chao);
        if(mat[8][6] == C) l8c6.setIcon(chao);
        if(mat[8][7] == C) l8c7.setIcon(chao);
        if(mat[8][8] == C) l8c8.setIcon(chao);
        if(mat[8][9] == C) l8c9.setIcon(chao);
        if(mat[8][10] == C) l8c10.setIcon(chao);
        if(mat[8][11] == C) l8c11.setIcon(chao);
        if(mat[9][0] == C) l9c0.setIcon(chao);
        if(mat[9][1] == C) l9c1.setIcon(chao);
        if(mat[9][2] == C) l9c2.setIcon(chao);
        if(mat[9][3] == C) l9c3.setIcon(chao);
        if(mat[9][4] == C) l9c4.setIcon(chao);
        if(mat[9][5] == C) l9c5.setIcon(chao);
        if(mat[9][6] == C) l9c6.setIcon(chao);
        if(mat[9][7] == C) l9c7.setIcon(chao);
        if(mat[9][8] == C) l9c8.setIcon(chao);
        if(mat[9][9] == C) l9c9.setIcon(chao);
        if(mat[9][10] == C) l9c10.setIcon(chao);
        if(mat[9][11] == C) l9c11.setIcon(chao);
        if(mat[10][0] == C) l10c0.setIcon(chao);
        if(mat[10][1] == C) l10c1.setIcon(chao);
        if(mat[10][2] == C) l10c2.setIcon(chao);
        if(mat[10][3] == C) l10c3.setIcon(chao);
        if(mat[10][4] == C) l10c4.setIcon(chao);
        if(mat[10][5] == C) l10c5.setIcon(chao);
        if(mat[10][6] == C) l10c6.setIcon(chao);
        if(mat[10][7] == C) l10c7.setIcon(chao);
        if(mat[10][8] == C) l10c8.setIcon(chao);
        if(mat[10][9] == C) l10c9.setIcon(chao);
        if(mat[10][10] == C) l10c10.setIcon(chao);
        if(mat[10][11] == C) l10c11.setIcon(chao);
        if(mat[11][0] == C) l11c0.setIcon(chao);
        if(mat[11][1] == C) l11c1.setIcon(chao);
        if(mat[11][2] == C) l11c2.setIcon(chao);
        if(mat[11][3] == C) l11c3.setIcon(chao);
        if(mat[11][4] == C) l11c4.setIcon(chao);
        if(mat[11][5] == C) l11c5.setIcon(chao);
        if(mat[11][6] == C) l11c6.setIcon(chao);
        if(mat[11][7] == C) l11c7.setIcon(chao);
        if(mat[11][8] == C) l11c8.setIcon(chao);
        if(mat[11][9] == C) l11c9.setIcon(chao);
        if(mat[11][10] == C) l11c10.setIcon(chao);
        if(mat[11][11] == C) l11c11.setIcon(chao);
        
        if(mat[0][0] == Q) l0c0.setIcon(queijo);
        if(mat[0][1] == Q) l0c1.setIcon(queijo);
        if(mat[0][2] == Q) l0c2.setIcon(queijo);
        if(mat[0][3] == Q) l0c3.setIcon(queijo);
        if(mat[0][4] == Q) l0c4.setIcon(queijo);
        if(mat[0][5] == Q) l0c5.setIcon(queijo);
        if(mat[0][6] == Q) l0c6.setIcon(queijo);
        if(mat[0][7] == Q) l0c7.setIcon(queijo);
        if(mat[0][8] == Q) l0c8.setIcon(queijo);
        if(mat[0][9] == Q) l0c9.setIcon(queijo);
        if(mat[0][10] == Q) l0c10.setIcon(queijo);
        if(mat[0][11] == Q) l0c11.setIcon(queijo);
        if(mat[1][0] == Q) l1c0.setIcon(queijo);
        if(mat[1][1] == Q) l1c1.setIcon(queijo);
        if(mat[1][2] == Q) l1c2.setIcon(queijo);
        if(mat[1][3] == Q) l1c3.setIcon(queijo);
        if(mat[1][4] == Q) l1c4.setIcon(queijo);
        if(mat[1][5] == Q) l1c5.setIcon(queijo);
        if(mat[1][6] == Q) l1c6.setIcon(queijo);
        if(mat[1][7] == Q) l1c7.setIcon(queijo);
        if(mat[1][8] == Q) l1c8.setIcon(queijo);
        if(mat[1][9] == Q) l1c9.setIcon(queijo);
        if(mat[1][10] == Q) l1c10.setIcon(queijo);
        if(mat[1][11] == Q) l1c11.setIcon(queijo);
        if(mat[2][0] == Q) l2c0.setIcon(queijo);
        if(mat[2][1] == Q) l2c1.setIcon(queijo);
        if(mat[2][2] == Q) l2c2.setIcon(queijo);
        if(mat[2][3] == Q) l2c3.setIcon(queijo);
        if(mat[2][4] == Q) l2c4.setIcon(queijo);
        if(mat[2][5] == Q) l2c5.setIcon(queijo);
        if(mat[2][6] == Q) l2c6.setIcon(queijo);
        if(mat[2][7] == Q) l2c7.setIcon(queijo);
        if(mat[2][8] == Q) l2c8.setIcon(queijo);
        if(mat[2][9] == Q) l2c9.setIcon(queijo);
        if(mat[2][10] == Q) l2c10.setIcon(queijo);
        if(mat[2][11] == Q) l2c11.setIcon(queijo);
        if(mat[3][0] == Q) l3c0.setIcon(queijo);
        if(mat[3][1] == Q) l3c1.setIcon(queijo);
        if(mat[3][2] == Q) l3c2.setIcon(queijo);
        if(mat[3][3] == Q) l3c3.setIcon(queijo);
        if(mat[3][4] == Q) l3c4.setIcon(queijo);
        if(mat[3][5] == Q) l3c5.setIcon(queijo);
        if(mat[3][6] == Q) l3c6.setIcon(queijo);
        if(mat[3][7] == Q) l3c7.setIcon(queijo);
        if(mat[3][8] == Q) l3c8.setIcon(queijo);
        if(mat[3][9] == Q) l3c9.setIcon(queijo);
        if(mat[3][10] == Q) l3c10.setIcon(queijo);
        if(mat[3][11] == Q) l3c11.setIcon(queijo);
        if(mat[4][0] == Q) l4c0.setIcon(queijo);
        if(mat[4][1] == Q) l4c1.setIcon(queijo);
        if(mat[4][2] == Q) l4c2.setIcon(queijo);
        if(mat[4][3] == Q) l4c3.setIcon(queijo);
        if(mat[4][4] == Q) l4c4.setIcon(queijo);
        if(mat[4][5] == Q) l4c5.setIcon(queijo);
        if(mat[4][6] == Q) l4c6.setIcon(queijo);
        if(mat[4][7] == Q) l4c7.setIcon(queijo);
        if(mat[4][8] == Q) l4c8.setIcon(queijo);
        if(mat[4][9] == Q) l4c9.setIcon(queijo);
        if(mat[4][10] == Q) l4c10.setIcon(queijo);
        if(mat[4][11] == Q) l4c11.setIcon(queijo);
        if(mat[5][0] == Q) l5c0.setIcon(queijo);
        if(mat[5][1] == Q) l5c1.setIcon(queijo);
        if(mat[5][2] == Q) l5c2.setIcon(queijo);
        if(mat[5][3] == Q) l5c3.setIcon(queijo);
        if(mat[5][4] == Q) l5c4.setIcon(queijo);
        if(mat[5][5] == Q) l5c5.setIcon(queijo);
        if(mat[5][6] == Q) l5c6.setIcon(queijo);
        if(mat[5][7] == Q) l5c7.setIcon(queijo);
        if(mat[5][8] == Q) l5c8.setIcon(queijo);
        if(mat[5][9] == Q) l5c9.setIcon(queijo);
        if(mat[5][10] == Q) l5c10.setIcon(queijo);
        if(mat[5][11] == Q) l5c11.setIcon(queijo);
        if(mat[6][0] == Q) l6c0.setIcon(queijo);
        if(mat[6][1] == Q) l6c1.setIcon(queijo);
        if(mat[6][2] == Q) l6c2.setIcon(queijo);
        if(mat[6][3] == Q) l6c3.setIcon(queijo);
        if(mat[6][4] == Q) l6c4.setIcon(queijo);
        if(mat[6][5] == Q) l6c5.setIcon(queijo);
        if(mat[6][6] == Q) l6c6.setIcon(queijo);
        if(mat[6][7] == Q) l6c7.setIcon(queijo);
        if(mat[6][8] == Q) l6c8.setIcon(queijo);
        if(mat[6][9] == Q) l6c9.setIcon(queijo);
        if(mat[6][10] == Q) l6c10.setIcon(queijo);
        if(mat[6][11] == Q) l6c11.setIcon(queijo);
        if(mat[7][0] == Q) l7c0.setIcon(queijo);
        if(mat[7][1] == Q) l7c1.setIcon(queijo);
        if(mat[7][2] == Q) l7c2.setIcon(queijo);
        if(mat[7][3] == Q) l7c3.setIcon(queijo);
        if(mat[7][4] == Q) l7c4.setIcon(queijo);
        if(mat[7][5] == Q) l7c5.setIcon(queijo);
        if(mat[7][6] == Q) l7c6.setIcon(queijo);
        if(mat[7][7] == Q) l7c7.setIcon(queijo);
        if(mat[7][8] == Q) l7c8.setIcon(queijo);
        if(mat[7][9] == Q) l7c9.setIcon(queijo);
        if(mat[7][10] == Q) l7c10.setIcon(queijo);
        if(mat[7][11] == Q) l7c11.setIcon(queijo);
        if(mat[8][0] == Q) l8c0.setIcon(queijo);
        if(mat[8][1] == Q) l8c1.setIcon(queijo);
        if(mat[8][2] == Q) l8c2.setIcon(queijo);
        if(mat[8][3] == Q) l8c3.setIcon(queijo);
        if(mat[8][4] == Q) l8c4.setIcon(queijo);
        if(mat[8][5] == Q) l8c5.setIcon(queijo);
        if(mat[8][6] == Q) l8c6.setIcon(queijo);
        if(mat[8][7] == Q) l8c7.setIcon(queijo);
        if(mat[8][8] == Q) l8c8.setIcon(queijo);
        if(mat[8][9] == Q) l8c9.setIcon(queijo);
        if(mat[8][10] == Q) l8c10.setIcon(queijo);
        if(mat[8][11] == Q) l8c11.setIcon(queijo);
        if(mat[9][0] == Q) l9c0.setIcon(queijo);
        if(mat[9][1] == Q) l9c1.setIcon(queijo);
        if(mat[9][2] == Q) l9c2.setIcon(queijo);
        if(mat[9][3] == Q) l9c3.setIcon(queijo);
        if(mat[9][4] == Q) l9c4.setIcon(queijo);
        if(mat[9][5] == Q) l9c5.setIcon(queijo);
        if(mat[9][6] == Q) l9c6.setIcon(queijo);
        if(mat[9][7] == Q) l9c7.setIcon(queijo);
        if(mat[9][8] == Q) l9c8.setIcon(queijo);
        if(mat[9][9] == Q) l9c9.setIcon(queijo);
        if(mat[9][10] == Q) l9c10.setIcon(queijo);
        if(mat[9][11] == Q) l9c11.setIcon(queijo);
        if(mat[10][0] == Q) l10c0.setIcon(queijo);
        if(mat[10][1] == Q) l10c1.setIcon(queijo);
        if(mat[10][2] == Q) l10c2.setIcon(queijo);
        if(mat[10][3] == Q) l10c3.setIcon(queijo);
        if(mat[10][4] == Q) l10c4.setIcon(queijo);
        if(mat[10][5] == Q) l10c5.setIcon(queijo);
        if(mat[10][6] == Q) l10c6.setIcon(queijo);
        if(mat[10][7] == Q) l10c7.setIcon(queijo);
        if(mat[10][8] == Q) l10c8.setIcon(queijo);
        if(mat[10][9] == Q) l10c9.setIcon(queijo);
        if(mat[10][10] == Q) l10c10.setIcon(queijo);
        if(mat[10][11] == Q) l10c11.setIcon(queijo);
        if(mat[11][0] == Q) l11c0.setIcon(queijo);
        if(mat[11][1] == Q) l11c1.setIcon(queijo);
        if(mat[11][2] == Q) l11c2.setIcon(queijo);
        if(mat[11][3] == Q) l11c3.setIcon(queijo);
        if(mat[11][4] == Q) l11c4.setIcon(queijo);
        if(mat[11][5] == Q) l11c5.setIcon(queijo);
        if(mat[11][6] == Q) l11c6.setIcon(queijo);
        if(mat[11][7] == Q) l11c7.setIcon(queijo);
        if(mat[11][8] == Q) l11c8.setIcon(queijo);
        if(mat[11][9] == Q) l11c9.setIcon(queijo);
        if(mat[11][10] == Q) l11c10.setIcon(queijo);
        if(mat[11][11] == Q) l11c11.setIcon(queijo);
        
        if(mat[0][0] == P) l0c0.setIcon(parede);
        if(mat[0][1] == P) l0c1.setIcon(parede);
        if(mat[0][2] == P) l0c2.setIcon(parede);
        if(mat[0][3] == P) l0c3.setIcon(parede);
        if(mat[0][4] == P) l0c4.setIcon(parede);
        if(mat[0][5] == P) l0c5.setIcon(parede);
        if(mat[0][6] == P) l0c6.setIcon(parede);
        if(mat[0][7] == P) l0c7.setIcon(parede);
        if(mat[0][8] == P) l0c8.setIcon(parede);
        if(mat[0][9] == P) l0c9.setIcon(parede);
        if(mat[0][10] == P) l0c10.setIcon(parede);
        if(mat[0][11] == P) l0c11.setIcon(parede);
        if(mat[1][0] == P) l1c0.setIcon(parede);
        if(mat[1][1] == P) l1c1.setIcon(parede);
        if(mat[1][2] == P) l1c2.setIcon(parede);
        if(mat[1][3] == P) l1c3.setIcon(parede);
        if(mat[1][4] == P) l1c4.setIcon(parede);
        if(mat[1][5] == P) l1c5.setIcon(parede);
        if(mat[1][6] == P) l1c6.setIcon(parede);
        if(mat[1][7] == P) l1c7.setIcon(parede);
        if(mat[1][8] == P) l1c8.setIcon(parede);
        if(mat[1][9] == P) l1c9.setIcon(parede);
        if(mat[1][10] == P) l1c10.setIcon(parede);
        if(mat[1][11] == P) l1c11.setIcon(parede);
        if(mat[2][0] == P) l2c0.setIcon(parede);
        if(mat[2][1] == P) l2c1.setIcon(parede);
        if(mat[2][2] == P) l2c2.setIcon(parede);
        if(mat[2][3] == P) l2c3.setIcon(parede);
        if(mat[2][4] == P) l2c4.setIcon(parede);
        if(mat[2][5] == P) l2c5.setIcon(parede);
        if(mat[2][6] == P) l2c6.setIcon(parede);
        if(mat[2][7] == P) l2c7.setIcon(parede);
        if(mat[2][8] == P) l2c8.setIcon(parede);
        if(mat[2][9] == P) l2c9.setIcon(parede);
        if(mat[2][10] == P) l2c10.setIcon(parede);
        if(mat[2][11] == P) l2c11.setIcon(parede);
        if(mat[3][0] == P) l3c0.setIcon(parede);
        if(mat[3][1] == P) l3c1.setIcon(parede);
        if(mat[3][2] == P) l3c2.setIcon(parede);
        if(mat[3][3] == P) l3c3.setIcon(parede);
        if(mat[3][4] == P) l3c4.setIcon(parede);
        if(mat[3][5] == P) l3c5.setIcon(parede);
        if(mat[3][6] == P) l3c6.setIcon(parede);
        if(mat[3][7] == P) l3c7.setIcon(parede);
        if(mat[3][8] == P) l3c8.setIcon(parede);
        if(mat[3][9] == P) l3c9.setIcon(parede);
        if(mat[3][10] == P) l3c10.setIcon(parede);
        if(mat[3][11] == P) l3c11.setIcon(parede);
        if(mat[4][0] == P) l4c0.setIcon(parede);
        if(mat[4][1] == P) l4c1.setIcon(parede);
        if(mat[4][2] == P) l4c2.setIcon(parede);
        if(mat[4][3] == P) l4c3.setIcon(parede);
        if(mat[4][4] == P) l4c4.setIcon(parede);
        if(mat[4][5] == P) l4c5.setIcon(parede);
        if(mat[4][6] == P) l4c6.setIcon(parede);
        if(mat[4][7] == P) l4c7.setIcon(parede);
        if(mat[4][8] == P) l4c8.setIcon(parede);
        if(mat[4][9] == P) l4c9.setIcon(parede);
        if(mat[4][10] == P) l4c10.setIcon(parede);
        if(mat[4][11] == P) l4c11.setIcon(parede);
        if(mat[5][0] == P) l5c0.setIcon(parede);
        if(mat[5][1] == P) l5c1.setIcon(parede);
        if(mat[5][2] == P) l5c2.setIcon(parede);
        if(mat[5][3] == P) l5c3.setIcon(parede);
        if(mat[5][4] == P) l5c4.setIcon(parede);
        if(mat[5][5] == P) l5c5.setIcon(parede);
        if(mat[5][6] == P) l5c6.setIcon(parede);
        if(mat[5][7] == P) l5c7.setIcon(parede);
        if(mat[5][8] == P) l5c8.setIcon(parede);
        if(mat[5][9] == P) l5c9.setIcon(parede);
        if(mat[5][10] == P) l5c10.setIcon(parede);
        if(mat[5][11] == P) l5c11.setIcon(parede);
        if(mat[6][0] == P) l6c0.setIcon(parede);
        if(mat[6][1] == P) l6c1.setIcon(parede);
        if(mat[6][2] == P) l6c2.setIcon(parede);
        if(mat[6][3] == P) l6c3.setIcon(parede);
        if(mat[6][4] == P) l6c4.setIcon(parede);
        if(mat[6][5] == P) l6c5.setIcon(parede);
        if(mat[6][6] == P) l6c6.setIcon(parede);
        if(mat[6][7] == P) l6c7.setIcon(parede);
        if(mat[6][8] == P) l6c8.setIcon(parede);
        if(mat[6][9] == P) l6c9.setIcon(parede);
        if(mat[6][10] == P) l6c10.setIcon(parede);
        if(mat[6][11] == P) l6c11.setIcon(parede);
        if(mat[7][0] == P) l7c0.setIcon(parede);
        if(mat[7][1] == P) l7c1.setIcon(parede);
        if(mat[7][2] == P) l7c2.setIcon(parede);
        if(mat[7][3] == P) l7c3.setIcon(parede);
        if(mat[7][4] == P) l7c4.setIcon(parede);
        if(mat[7][5] == P) l7c5.setIcon(parede);
        if(mat[7][6] == P) l7c6.setIcon(parede);
        if(mat[7][7] == P) l7c7.setIcon(parede);
        if(mat[7][8] == P) l7c8.setIcon(parede);
        if(mat[7][9] == P) l7c9.setIcon(parede);
        if(mat[7][10] == P) l7c10.setIcon(parede);
        if(mat[7][11] == P) l7c11.setIcon(parede);
        if(mat[8][0] == P) l8c0.setIcon(parede);
        if(mat[8][1] == P) l8c1.setIcon(parede);
        if(mat[8][2] == P) l8c2.setIcon(parede);
        if(mat[8][3] == P) l8c3.setIcon(parede);
        if(mat[8][4] == P) l8c4.setIcon(parede);
        if(mat[8][5] == P) l8c5.setIcon(parede);
        if(mat[8][6] == P) l8c6.setIcon(parede);
        if(mat[8][7] == P) l8c7.setIcon(parede);
        if(mat[8][8] == P) l8c8.setIcon(parede);
        if(mat[8][9] == P) l8c9.setIcon(parede);
        if(mat[8][10] == P) l8c10.setIcon(parede);
        if(mat[8][11] == P) l8c11.setIcon(parede);
        if(mat[9][0] == P) l9c0.setIcon(parede);
        if(mat[9][1] == P) l9c1.setIcon(parede);
        if(mat[9][2] == P) l9c2.setIcon(parede);
        if(mat[9][3] == P) l9c3.setIcon(parede);
        if(mat[9][4] == P) l9c4.setIcon(parede);
        if(mat[9][5] == P) l9c5.setIcon(parede);
        if(mat[9][6] == P) l9c6.setIcon(parede);
        if(mat[9][7] == P) l9c7.setIcon(parede);
        if(mat[9][8] == P) l9c8.setIcon(parede);
        if(mat[9][9] == P) l9c9.setIcon(parede);
        if(mat[9][10] == P) l9c10.setIcon(parede);
        if(mat[9][11] == P) l9c11.setIcon(parede);
        if(mat[10][0] == P) l10c0.setIcon(parede);
        if(mat[10][1] == P) l10c1.setIcon(parede);
        if(mat[10][2] == P) l10c2.setIcon(parede);
        if(mat[10][3] == P) l10c3.setIcon(parede);
        if(mat[10][4] == P) l10c4.setIcon(parede);
        if(mat[10][5] == P) l10c5.setIcon(parede);
        if(mat[10][6] == P) l10c6.setIcon(parede);
        if(mat[10][7] == P) l10c7.setIcon(parede);
        if(mat[10][8] == P) l10c8.setIcon(parede);
        if(mat[10][9] == P) l10c9.setIcon(parede);
        if(mat[10][10] == P) l10c10.setIcon(parede);
        if(mat[10][11] == P) l10c11.setIcon(parede);
        if(mat[11][0] == P) l11c0.setIcon(parede);
        if(mat[11][1] == P) l11c1.setIcon(parede);
        if(mat[11][2] == P) l11c2.setIcon(parede);
        if(mat[11][3] == P) l11c3.setIcon(parede);
        if(mat[11][4] == P) l11c4.setIcon(parede);
        if(mat[11][5] == P) l11c5.setIcon(parede);
        if(mat[11][6] == P) l11c6.setIcon(parede);
        if(mat[11][7] == P) l11c7.setIcon(parede);
        if(mat[11][8] == P) l11c8.setIcon(parede);
        if(mat[11][9] == P) l11c9.setIcon(parede);
        if(mat[11][10] == P) l11c10.setIcon(parede);
        if(mat[11][11] == P) l11c11.setIcon(parede);
        
        if(mat[0][0] == V) l0c0.setIcon(caminho);
        if(mat[0][1] == V) l0c1.setIcon(caminho);
        if(mat[0][2] == V) l0c2.setIcon(caminho);
        if(mat[0][3] == V) l0c3.setIcon(caminho);
        if(mat[0][4] == V) l0c4.setIcon(caminho);
        if(mat[0][5] == V) l0c5.setIcon(caminho);
        if(mat[0][6] == V) l0c6.setIcon(caminho);
        if(mat[0][7] == V) l0c7.setIcon(caminho);
        if(mat[0][8] == V) l0c8.setIcon(caminho);
        if(mat[0][9] == V) l0c9.setIcon(caminho);
        if(mat[0][10] == V) l0c10.setIcon(caminho);
        if(mat[0][11] == V) l0c11.setIcon(caminho);
        if(mat[1][0] == V) l1c0.setIcon(caminho);
        if(mat[1][1] == V) l1c1.setIcon(caminho);
        if(mat[1][2] == V) l1c2.setIcon(caminho);
        if(mat[1][3] == V) l1c3.setIcon(caminho);
        if(mat[1][4] == V) l1c4.setIcon(caminho);
        if(mat[1][5] == V) l1c5.setIcon(caminho);
        if(mat[1][6] == V) l1c6.setIcon(caminho);
        if(mat[1][7] == V) l1c7.setIcon(caminho);
        if(mat[1][8] == V) l1c8.setIcon(caminho);
        if(mat[1][9] == V) l1c9.setIcon(caminho);
        if(mat[1][10] == V) l1c10.setIcon(caminho);
        if(mat[1][11] == V) l1c11.setIcon(caminho);
        if(mat[2][0] == V) l2c0.setIcon(caminho);
        if(mat[2][1] == V) l2c1.setIcon(caminho);
        if(mat[2][2] == V) l2c2.setIcon(caminho);
        if(mat[2][3] == V) l2c3.setIcon(caminho);
        if(mat[2][4] == V) l2c4.setIcon(caminho);
        if(mat[2][5] == V) l2c5.setIcon(caminho);
        if(mat[2][6] == V) l2c6.setIcon(caminho);
        if(mat[2][7] == V) l2c7.setIcon(caminho);
        if(mat[2][8] == V) l2c8.setIcon(caminho);
        if(mat[2][9] == V) l2c9.setIcon(caminho);
        if(mat[2][10] == V) l2c10.setIcon(caminho);
        if(mat[2][11] == V) l2c11.setIcon(caminho);
        if(mat[3][0] == V) l3c0.setIcon(caminho);
        if(mat[3][1] == V) l3c1.setIcon(caminho);
        if(mat[3][2] == V) l3c2.setIcon(caminho);
        if(mat[3][3] == V) l3c3.setIcon(caminho);
        if(mat[3][4] == V) l3c4.setIcon(caminho);
        if(mat[3][5] == V) l3c5.setIcon(caminho);
        if(mat[3][6] == V) l3c6.setIcon(caminho);
        if(mat[3][7] == V) l3c7.setIcon(caminho);
        if(mat[3][8] == V) l3c8.setIcon(caminho);
        if(mat[3][9] == V) l3c9.setIcon(caminho);
        if(mat[3][10] == V) l3c10.setIcon(caminho);
        if(mat[3][11] == V) l3c11.setIcon(caminho);
        if(mat[4][0] == V) l4c0.setIcon(caminho);
        if(mat[4][1] == V) l4c1.setIcon(caminho);
        if(mat[4][2] == V) l4c2.setIcon(caminho);
        if(mat[4][3] == V) l4c3.setIcon(caminho);
        if(mat[4][4] == V) l4c4.setIcon(caminho);
        if(mat[4][5] == V) l4c5.setIcon(caminho);
        if(mat[4][6] == V) l4c6.setIcon(caminho);
        if(mat[4][7] == V) l4c7.setIcon(caminho);
        if(mat[4][8] == V) l4c8.setIcon(caminho);
        if(mat[4][9] == V) l4c9.setIcon(caminho);
        if(mat[4][10] == V) l4c10.setIcon(caminho);
        if(mat[4][11] == V) l4c11.setIcon(caminho);
        if(mat[5][0] == V) l5c0.setIcon(caminho);
        if(mat[5][1] == V) l5c1.setIcon(caminho);
        if(mat[5][2] == V) l5c2.setIcon(caminho);
        if(mat[5][3] == V) l5c3.setIcon(caminho);
        if(mat[5][4] == V) l5c4.setIcon(caminho);
        if(mat[5][5] == V) l5c5.setIcon(caminho);
        if(mat[5][6] == V) l5c6.setIcon(caminho);
        if(mat[5][7] == V) l5c7.setIcon(caminho);
        if(mat[5][8] == V) l5c8.setIcon(caminho);
        if(mat[5][9] == V) l5c9.setIcon(caminho);
        if(mat[5][10] == V) l5c10.setIcon(caminho);
        if(mat[5][11] == V) l5c11.setIcon(caminho);
        if(mat[6][0] == V) l6c0.setIcon(caminho);
        if(mat[6][1] == V) l6c1.setIcon(caminho);
        if(mat[6][2] == V) l6c2.setIcon(caminho);
        if(mat[6][3] == V) l6c3.setIcon(caminho);
        if(mat[6][4] == V) l6c4.setIcon(caminho);
        if(mat[6][5] == V) l6c5.setIcon(caminho);
        if(mat[6][6] == V) l6c6.setIcon(caminho);
        if(mat[6][7] == V) l6c7.setIcon(caminho);
        if(mat[6][8] == V) l6c8.setIcon(caminho);
        if(mat[6][9] == V) l6c9.setIcon(caminho);
        if(mat[6][10] == V) l6c10.setIcon(caminho);
        if(mat[6][11] == V) l6c11.setIcon(caminho);
        if(mat[7][0] == V) l7c0.setIcon(caminho);
        if(mat[7][1] == V) l7c1.setIcon(caminho);
        if(mat[7][2] == V) l7c2.setIcon(caminho);
        if(mat[7][3] == V) l7c3.setIcon(caminho);
        if(mat[7][4] == V) l7c4.setIcon(caminho);
        if(mat[7][5] == V) l7c5.setIcon(caminho);
        if(mat[7][6] == V) l7c6.setIcon(caminho);
        if(mat[7][7] == V) l7c7.setIcon(caminho);
        if(mat[7][8] == V) l7c8.setIcon(caminho);
        if(mat[7][9] == V) l7c9.setIcon(caminho);
        if(mat[7][10] == V) l7c10.setIcon(caminho);
        if(mat[7][11] == V) l7c11.setIcon(caminho);
        if(mat[8][0] == V) l8c0.setIcon(caminho);
        if(mat[8][1] == V) l8c1.setIcon(caminho);
        if(mat[8][2] == V) l8c2.setIcon(caminho);
        if(mat[8][3] == V) l8c3.setIcon(caminho);
        if(mat[8][4] == V) l8c4.setIcon(caminho);
        if(mat[8][5] == V) l8c5.setIcon(caminho);
        if(mat[8][6] == V) l8c6.setIcon(caminho);
        if(mat[8][7] == V) l8c7.setIcon(caminho);
        if(mat[8][8] == V) l8c8.setIcon(caminho);
        if(mat[8][9] == V) l8c9.setIcon(caminho);
        if(mat[8][10] == V) l8c10.setIcon(caminho);
        if(mat[8][11] == V) l8c11.setIcon(caminho);
        if(mat[9][0] == V) l9c0.setIcon(caminho);
        if(mat[9][1] == V) l9c1.setIcon(caminho);
        if(mat[9][2] == V) l9c2.setIcon(caminho);
        if(mat[9][3] == V) l9c3.setIcon(caminho);
        if(mat[9][4] == V) l9c4.setIcon(caminho);
        if(mat[9][5] == V) l9c5.setIcon(caminho);
        if(mat[9][6] == V) l9c6.setIcon(caminho);
        if(mat[9][7] == V) l9c7.setIcon(caminho);
        if(mat[9][8] == V) l9c8.setIcon(caminho);
        if(mat[9][9] == V) l9c9.setIcon(caminho);
        if(mat[9][10] == V) l9c10.setIcon(caminho);
        if(mat[9][11] == V) l9c11.setIcon(caminho);
        if(mat[10][0] == V) l10c0.setIcon(caminho);
        if(mat[10][1] == V) l10c1.setIcon(caminho);
        if(mat[10][2] == V) l10c2.setIcon(caminho);
        if(mat[10][3] == V) l10c3.setIcon(caminho);
        if(mat[10][4] == V) l10c4.setIcon(caminho);
        if(mat[10][5] == V) l10c5.setIcon(caminho);
        if(mat[10][6] == V) l10c6.setIcon(caminho);
        if(mat[10][7] == V) l10c7.setIcon(caminho);
        if(mat[10][8] == V) l10c8.setIcon(caminho);
        if(mat[10][9] == V) l10c9.setIcon(caminho);
        if(mat[10][10] == V) l10c10.setIcon(caminho);
        if(mat[10][11] == V) l10c11.setIcon(caminho);
        if(mat[11][0] == V) l11c0.setIcon(caminho);
        if(mat[11][1] == V) l11c1.setIcon(caminho);
        if(mat[11][2] == V) l11c2.setIcon(caminho);
        if(mat[11][3] == V) l11c3.setIcon(caminho);
        if(mat[11][4] == V) l11c4.setIcon(caminho);
        if(mat[11][5] == V) l11c5.setIcon(caminho);
        if(mat[11][6] == V) l11c6.setIcon(caminho);
        if(mat[11][7] == V) l11c7.setIcon(caminho);
        if(mat[11][8] == V) l11c8.setIcon(caminho);
        if(mat[11][9] == V) l11c9.setIcon(caminho);
        if(mat[11][10] == V) l11c10.setIcon(caminho);
        if(mat[11][11] == V) l11c11.setIcon(caminho);
    }//atualiza os labels na tela
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        l0c1 = new javax.swing.JLabel();
        l0c0 = new javax.swing.JLabel();
        l0c2 = new javax.swing.JLabel();
        l0c3 = new javax.swing.JLabel();
        l0c5 = new javax.swing.JLabel();
        l0c6 = new javax.swing.JLabel();
        l0c4 = new javax.swing.JLabel();
        l0c7 = new javax.swing.JLabel();
        l0c8 = new javax.swing.JLabel();
        l0c9 = new javax.swing.JLabel();
        l1c0 = new javax.swing.JLabel();
        l1c1 = new javax.swing.JLabel();
        l1c2 = new javax.swing.JLabel();
        l1c3 = new javax.swing.JLabel();
        l1c4 = new javax.swing.JLabel();
        l1c5 = new javax.swing.JLabel();
        l1c6 = new javax.swing.JLabel();
        l1c7 = new javax.swing.JLabel();
        l1c8 = new javax.swing.JLabel();
        l1c9 = new javax.swing.JLabel();
        l2c0 = new javax.swing.JLabel();
        l2c1 = new javax.swing.JLabel();
        l2c2 = new javax.swing.JLabel();
        l2c3 = new javax.swing.JLabel();
        l2c4 = new javax.swing.JLabel();
        l2c5 = new javax.swing.JLabel();
        l2c6 = new javax.swing.JLabel();
        l2c7 = new javax.swing.JLabel();
        l2c8 = new javax.swing.JLabel();
        l2c9 = new javax.swing.JLabel();
        l3c0 = new javax.swing.JLabel();
        l3c1 = new javax.swing.JLabel();
        l3c2 = new javax.swing.JLabel();
        l3c3 = new javax.swing.JLabel();
        l3c4 = new javax.swing.JLabel();
        l3c5 = new javax.swing.JLabel();
        l3c6 = new javax.swing.JLabel();
        l3c7 = new javax.swing.JLabel();
        l3c8 = new javax.swing.JLabel();
        l3c9 = new javax.swing.JLabel();
        l4c0 = new javax.swing.JLabel();
        l4c1 = new javax.swing.JLabel();
        l4c2 = new javax.swing.JLabel();
        l4c3 = new javax.swing.JLabel();
        l4c4 = new javax.swing.JLabel();
        l4c5 = new javax.swing.JLabel();
        l4c6 = new javax.swing.JLabel();
        l4c7 = new javax.swing.JLabel();
        l4c8 = new javax.swing.JLabel();
        l4c9 = new javax.swing.JLabel();
        l5c0 = new javax.swing.JLabel();
        l5c1 = new javax.swing.JLabel();
        l5c2 = new javax.swing.JLabel();
        l5c3 = new javax.swing.JLabel();
        l5c4 = new javax.swing.JLabel();
        l5c5 = new javax.swing.JLabel();
        l5c6 = new javax.swing.JLabel();
        l5c7 = new javax.swing.JLabel();
        l5c8 = new javax.swing.JLabel();
        l5c9 = new javax.swing.JLabel();
        l6c0 = new javax.swing.JLabel();
        l6c1 = new javax.swing.JLabel();
        l6c2 = new javax.swing.JLabel();
        l6c3 = new javax.swing.JLabel();
        l6c4 = new javax.swing.JLabel();
        l6c5 = new javax.swing.JLabel();
        l6c6 = new javax.swing.JLabel();
        l6c7 = new javax.swing.JLabel();
        l6c8 = new javax.swing.JLabel();
        l6c9 = new javax.swing.JLabel();
        l7c0 = new javax.swing.JLabel();
        l7c1 = new javax.swing.JLabel();
        l7c2 = new javax.swing.JLabel();
        l7c3 = new javax.swing.JLabel();
        l7c4 = new javax.swing.JLabel();
        l7c5 = new javax.swing.JLabel();
        l7c6 = new javax.swing.JLabel();
        l7c7 = new javax.swing.JLabel();
        l7c8 = new javax.swing.JLabel();
        l7c9 = new javax.swing.JLabel();
        l8c0 = new javax.swing.JLabel();
        l8c1 = new javax.swing.JLabel();
        l8c2 = new javax.swing.JLabel();
        l8c3 = new javax.swing.JLabel();
        l8c4 = new javax.swing.JLabel();
        l8c5 = new javax.swing.JLabel();
        l8c6 = new javax.swing.JLabel();
        l8c7 = new javax.swing.JLabel();
        l8c8 = new javax.swing.JLabel();
        l8c9 = new javax.swing.JLabel();
        l9c0 = new javax.swing.JLabel();
        l9c1 = new javax.swing.JLabel();
        l9c2 = new javax.swing.JLabel();
        l9c3 = new javax.swing.JLabel();
        l9c4 = new javax.swing.JLabel();
        l9c5 = new javax.swing.JLabel();
        l9c6 = new javax.swing.JLabel();
        l9c7 = new javax.swing.JLabel();
        l9c8 = new javax.swing.JLabel();
        l9c9 = new javax.swing.JLabel();
        l0c10 = new javax.swing.JLabel();
        l1c10 = new javax.swing.JLabel();
        l2c10 = new javax.swing.JLabel();
        l3c10 = new javax.swing.JLabel();
        l4c10 = new javax.swing.JLabel();
        l5c10 = new javax.swing.JLabel();
        l6c10 = new javax.swing.JLabel();
        l7c10 = new javax.swing.JLabel();
        l8c10 = new javax.swing.JLabel();
        l9c10 = new javax.swing.JLabel();
        l0c11 = new javax.swing.JLabel();
        l1c11 = new javax.swing.JLabel();
        l2c11 = new javax.swing.JLabel();
        l3c11 = new javax.swing.JLabel();
        l4c11 = new javax.swing.JLabel();
        l5c11 = new javax.swing.JLabel();
        l6c11 = new javax.swing.JLabel();
        l7c11 = new javax.swing.JLabel();
        l8c11 = new javax.swing.JLabel();
        l9c11 = new javax.swing.JLabel();
        l10c0 = new javax.swing.JLabel();
        l10c1 = new javax.swing.JLabel();
        l10c2 = new javax.swing.JLabel();
        l10c3 = new javax.swing.JLabel();
        l10c4 = new javax.swing.JLabel();
        l10c5 = new javax.swing.JLabel();
        l10c6 = new javax.swing.JLabel();
        l10c7 = new javax.swing.JLabel();
        l10c8 = new javax.swing.JLabel();
        l10c9 = new javax.swing.JLabel();
        l10c10 = new javax.swing.JLabel();
        l10c11 = new javax.swing.JLabel();
        l11c0 = new javax.swing.JLabel();
        l11c1 = new javax.swing.JLabel();
        l11c2 = new javax.swing.JLabel();
        l11c3 = new javax.swing.JLabel();
        l11c4 = new javax.swing.JLabel();
        l11c5 = new javax.swing.JLabel();
        l11c6 = new javax.swing.JLabel();
        l11c7 = new javax.swing.JLabel();
        l11c8 = new javax.swing.JLabel();
        l11c9 = new javax.swing.JLabel();
        l11c10 = new javax.swing.JLabel();
        l11c11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        buttonExecutar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        NroEpisodios = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        buttonIniciarExec = new javax.swing.JButton();
        buttonEncerrarExec = new javax.swing.JButton();
        panelTipoExec = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        buttonMostrarCaminhosPercorridos = new javax.swing.JButton();
        buttonPercorrerPassoPasso = new javax.swing.JButton();
        buttonPararTipoExec = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        panelPassoPasso = new javax.swing.JPanel();
        buttonAssistir = new javax.swing.JButton();
        idEpisodio2 = new javax.swing.JTextField();
        buttonPassoAnterior = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        buttonProxPasso = new javax.swing.JButton();
        buttonParar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        labelVertAtual = new javax.swing.JLabel();
        panelCaminhoPercorrido = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        idEpisodio = new javax.swing.JTextField();
        buttonVerCaminho = new javax.swing.JButton();
        labelVertPercorridos = new javax.swing.JLabel();
        buttonEpAnterior = new javax.swing.JButton();
        buttonProxEp = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        itemVerCaminhos = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QLearning");
        setPreferredSize(new java.awt.Dimension(936, 580));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        l0c1.setBackground(new java.awt.Color(255, 255, 255));
        l0c1.setAlignmentY(0.0F);
        l0c1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l0c0.setBackground(new java.awt.Color(255, 255, 255));
        l0c0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l0c2.setBackground(new java.awt.Color(255, 255, 255));
        l0c2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l0c3.setBackground(new java.awt.Color(255, 255, 255));
        l0c3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l0c5.setBackground(new java.awt.Color(255, 255, 255));
        l0c5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l0c6.setBackground(new java.awt.Color(255, 255, 255));
        l0c6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l0c4.setBackground(new java.awt.Color(255, 255, 255));
        l0c4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l0c7.setBackground(new java.awt.Color(255, 255, 255));
        l0c7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l0c8.setBackground(new java.awt.Color(255, 255, 255));
        l0c8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l0c9.setBackground(new java.awt.Color(255, 255, 255));
        l0c9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l1c0.setBackground(new java.awt.Color(255, 255, 255));
        l1c0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l1c1.setBackground(new java.awt.Color(255, 255, 255));
        l1c1.setAlignmentY(0.0F);
        l1c1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l1c2.setBackground(new java.awt.Color(255, 255, 255));
        l1c2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l1c3.setBackground(new java.awt.Color(255, 255, 255));
        l1c3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l1c4.setBackground(new java.awt.Color(255, 255, 255));
        l1c4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l1c5.setBackground(new java.awt.Color(255, 255, 255));
        l1c5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l1c6.setBackground(new java.awt.Color(255, 255, 255));
        l1c6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l1c7.setBackground(new java.awt.Color(255, 255, 255));
        l1c7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l1c8.setBackground(new java.awt.Color(255, 255, 255));
        l1c8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l1c9.setBackground(new java.awt.Color(255, 255, 255));
        l1c9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l2c0.setBackground(new java.awt.Color(255, 255, 255));
        l2c0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l2c1.setBackground(new java.awt.Color(255, 255, 255));
        l2c1.setAlignmentY(0.0F);
        l2c1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l2c2.setBackground(new java.awt.Color(255, 255, 255));
        l2c2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l2c3.setBackground(new java.awt.Color(255, 255, 255));
        l2c3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l2c4.setBackground(new java.awt.Color(255, 255, 255));
        l2c4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l2c5.setBackground(new java.awt.Color(255, 255, 255));
        l2c5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l2c6.setBackground(new java.awt.Color(255, 255, 255));
        l2c6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l2c7.setBackground(new java.awt.Color(255, 255, 255));
        l2c7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l2c8.setBackground(new java.awt.Color(255, 255, 255));
        l2c8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l2c9.setBackground(new java.awt.Color(255, 255, 255));
        l2c9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l3c0.setBackground(new java.awt.Color(255, 255, 255));
        l3c0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l3c1.setBackground(new java.awt.Color(255, 255, 255));
        l3c1.setAlignmentY(0.0F);
        l3c1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l3c2.setBackground(new java.awt.Color(255, 255, 255));
        l3c2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l3c3.setBackground(new java.awt.Color(255, 255, 255));
        l3c3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l3c4.setBackground(new java.awt.Color(255, 255, 255));
        l3c4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l3c5.setBackground(new java.awt.Color(255, 255, 255));
        l3c5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l3c6.setBackground(new java.awt.Color(255, 255, 255));
        l3c6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l3c7.setBackground(new java.awt.Color(255, 255, 255));
        l3c7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l3c8.setBackground(new java.awt.Color(255, 255, 255));
        l3c8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l3c9.setBackground(new java.awt.Color(255, 255, 255));
        l3c9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l4c0.setBackground(new java.awt.Color(255, 255, 255));
        l4c0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l4c1.setBackground(new java.awt.Color(255, 255, 255));
        l4c1.setAlignmentY(0.0F);
        l4c1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l4c2.setBackground(new java.awt.Color(255, 255, 255));
        l4c2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l4c3.setBackground(new java.awt.Color(255, 255, 255));
        l4c3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l4c4.setBackground(new java.awt.Color(255, 255, 255));
        l4c4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l4c5.setBackground(new java.awt.Color(255, 255, 255));
        l4c5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l4c6.setBackground(new java.awt.Color(255, 255, 255));
        l4c6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l4c7.setBackground(new java.awt.Color(255, 255, 255));
        l4c7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l4c8.setBackground(new java.awt.Color(255, 255, 255));
        l4c8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l4c9.setBackground(new java.awt.Color(255, 255, 255));
        l4c9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l5c0.setBackground(new java.awt.Color(255, 255, 255));
        l5c0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l5c1.setBackground(new java.awt.Color(255, 255, 255));
        l5c1.setAlignmentY(0.0F);
        l5c1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l5c2.setBackground(new java.awt.Color(255, 255, 255));
        l5c2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l5c3.setBackground(new java.awt.Color(255, 255, 255));
        l5c3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l5c4.setBackground(new java.awt.Color(255, 255, 255));
        l5c4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l5c5.setBackground(new java.awt.Color(255, 255, 255));
        l5c5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l5c6.setBackground(new java.awt.Color(255, 255, 255));
        l5c6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l5c7.setBackground(new java.awt.Color(255, 255, 255));
        l5c7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l5c8.setBackground(new java.awt.Color(255, 255, 255));
        l5c8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l5c9.setBackground(new java.awt.Color(255, 255, 255));
        l5c9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l6c0.setBackground(new java.awt.Color(255, 255, 255));
        l6c0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l6c1.setBackground(new java.awt.Color(255, 255, 255));
        l6c1.setAlignmentY(0.0F);
        l6c1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l6c2.setBackground(new java.awt.Color(255, 255, 255));
        l6c2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l6c3.setBackground(new java.awt.Color(255, 255, 255));
        l6c3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l6c4.setBackground(new java.awt.Color(255, 255, 255));
        l6c4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l6c5.setBackground(new java.awt.Color(255, 255, 255));
        l6c5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l6c6.setBackground(new java.awt.Color(255, 255, 255));
        l6c6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l6c7.setBackground(new java.awt.Color(255, 255, 255));
        l6c7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l6c8.setBackground(new java.awt.Color(255, 255, 255));
        l6c8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l6c9.setBackground(new java.awt.Color(255, 255, 255));
        l6c9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l7c0.setBackground(new java.awt.Color(255, 255, 255));
        l7c0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l7c1.setBackground(new java.awt.Color(255, 255, 255));
        l7c1.setAlignmentY(0.0F);
        l7c1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l7c2.setBackground(new java.awt.Color(255, 255, 255));
        l7c2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l7c3.setBackground(new java.awt.Color(255, 255, 255));
        l7c3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l7c4.setBackground(new java.awt.Color(255, 255, 255));
        l7c4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l7c5.setBackground(new java.awt.Color(255, 255, 255));
        l7c5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l7c6.setBackground(new java.awt.Color(255, 255, 255));
        l7c6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l7c7.setBackground(new java.awt.Color(255, 255, 255));
        l7c7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l7c8.setBackground(new java.awt.Color(255, 255, 255));
        l7c8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l7c9.setBackground(new java.awt.Color(255, 255, 255));
        l7c9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l8c0.setBackground(new java.awt.Color(255, 255, 255));
        l8c0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l8c1.setBackground(new java.awt.Color(255, 255, 255));
        l8c1.setAlignmentY(0.0F);
        l8c1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l8c2.setBackground(new java.awt.Color(255, 255, 255));
        l8c2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l8c3.setBackground(new java.awt.Color(255, 255, 255));
        l8c3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l8c4.setBackground(new java.awt.Color(255, 255, 255));
        l8c4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l8c5.setBackground(new java.awt.Color(255, 255, 255));
        l8c5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l8c6.setBackground(new java.awt.Color(255, 255, 255));
        l8c6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l8c7.setBackground(new java.awt.Color(255, 255, 255));
        l8c7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l8c8.setBackground(new java.awt.Color(255, 255, 255));
        l8c8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l8c9.setBackground(new java.awt.Color(255, 255, 255));
        l8c9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l9c0.setBackground(new java.awt.Color(255, 255, 255));
        l9c0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l9c1.setBackground(new java.awt.Color(255, 255, 255));
        l9c1.setAlignmentY(0.0F);
        l9c1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l9c2.setBackground(new java.awt.Color(255, 255, 255));
        l9c2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l9c3.setBackground(new java.awt.Color(255, 255, 255));
        l9c3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l9c4.setBackground(new java.awt.Color(255, 255, 255));
        l9c4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l9c5.setBackground(new java.awt.Color(255, 255, 255));
        l9c5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l9c6.setBackground(new java.awt.Color(255, 255, 255));
        l9c6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l9c7.setBackground(new java.awt.Color(255, 255, 255));
        l9c7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l9c8.setBackground(new java.awt.Color(255, 255, 255));
        l9c8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l9c9.setBackground(new java.awt.Color(255, 255, 255));
        l9c9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l0c10.setBackground(new java.awt.Color(255, 255, 255));
        l0c10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l1c10.setBackground(new java.awt.Color(255, 255, 255));
        l1c10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l2c10.setBackground(new java.awt.Color(255, 255, 255));
        l2c10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l3c10.setBackground(new java.awt.Color(255, 255, 255));
        l3c10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l4c10.setBackground(new java.awt.Color(255, 255, 255));
        l4c10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l5c10.setBackground(new java.awt.Color(255, 255, 255));
        l5c10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l6c10.setBackground(new java.awt.Color(255, 255, 255));
        l6c10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l7c10.setBackground(new java.awt.Color(255, 255, 255));
        l7c10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l8c10.setBackground(new java.awt.Color(255, 255, 255));
        l8c10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l9c10.setBackground(new java.awt.Color(255, 255, 255));
        l9c10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l0c11.setBackground(new java.awt.Color(255, 255, 255));
        l0c11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l1c11.setBackground(new java.awt.Color(255, 255, 255));
        l1c11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l2c11.setBackground(new java.awt.Color(255, 255, 255));
        l2c11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l3c11.setBackground(new java.awt.Color(255, 255, 255));
        l3c11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l4c11.setBackground(new java.awt.Color(255, 255, 255));
        l4c11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l5c11.setBackground(new java.awt.Color(255, 255, 255));
        l5c11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l6c11.setBackground(new java.awt.Color(255, 255, 255));
        l6c11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l7c11.setBackground(new java.awt.Color(255, 255, 255));
        l7c11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l8c11.setBackground(new java.awt.Color(255, 255, 255));
        l8c11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l9c11.setBackground(new java.awt.Color(255, 255, 255));
        l9c11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l10c0.setBackground(new java.awt.Color(255, 255, 255));
        l10c0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l10c1.setBackground(new java.awt.Color(255, 255, 255));
        l10c1.setAlignmentY(0.0F);
        l10c1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l10c2.setBackground(new java.awt.Color(255, 255, 255));
        l10c2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l10c3.setBackground(new java.awt.Color(255, 255, 255));
        l10c3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l10c4.setBackground(new java.awt.Color(255, 255, 255));
        l10c4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l10c5.setBackground(new java.awt.Color(255, 255, 255));
        l10c5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l10c6.setBackground(new java.awt.Color(255, 255, 255));
        l10c6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l10c7.setBackground(new java.awt.Color(255, 255, 255));
        l10c7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l10c8.setBackground(new java.awt.Color(255, 255, 255));
        l10c8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l10c9.setBackground(new java.awt.Color(255, 255, 255));
        l10c9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l10c10.setBackground(new java.awt.Color(255, 255, 255));
        l10c10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l10c11.setBackground(new java.awt.Color(255, 255, 255));
        l10c11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l11c0.setBackground(new java.awt.Color(255, 255, 255));
        l11c0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l11c1.setBackground(new java.awt.Color(255, 255, 255));
        l11c1.setAlignmentY(0.0F);
        l11c1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l11c2.setBackground(new java.awt.Color(255, 255, 255));
        l11c2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l11c3.setBackground(new java.awt.Color(255, 255, 255));
        l11c3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l11c4.setBackground(new java.awt.Color(255, 255, 255));
        l11c4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l11c5.setBackground(new java.awt.Color(255, 255, 255));
        l11c5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l11c6.setBackground(new java.awt.Color(255, 255, 255));
        l11c6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l11c7.setBackground(new java.awt.Color(255, 255, 255));
        l11c7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l11c8.setBackground(new java.awt.Color(255, 255, 255));
        l11c8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l11c9.setBackground(new java.awt.Color(255, 255, 255));
        l11c9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l11c10.setBackground(new java.awt.Color(255, 255, 255));
        l11c10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        l11c11.setBackground(new java.awt.Color(255, 255, 255));
        l11c11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(244, 244, 244)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(l11c0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l11c1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l11c2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l11c3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l11c4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l11c5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l11c6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l11c7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l11c8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l11c9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l11c10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l11c11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(l0c0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l0c1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l0c2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l0c3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l0c4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l0c5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l0c6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l0c7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l0c8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l0c9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(l1c0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l1c1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l1c2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l1c3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l1c4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l1c5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l1c6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l1c7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l1c8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l1c9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(l2c0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l2c1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l2c2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l2c3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l2c4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l2c5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l2c6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l2c7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l2c8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l2c9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(l3c0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l3c1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l3c2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l3c3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l3c4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l3c5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l3c6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l3c7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l3c8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l3c9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(l4c0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l4c1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l4c2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l4c3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l4c4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l4c5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l4c6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l4c7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l4c8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l4c9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(l5c0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l5c1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l5c2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l5c3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l5c4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l5c5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l5c6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l5c7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l5c8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l5c9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(l6c0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l6c1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l6c2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l6c3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l6c4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l6c5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l6c6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l6c7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l6c8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l6c9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(l7c0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l7c1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l7c2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l7c3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l7c4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l7c5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l7c6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l7c7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l7c8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l7c9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(l8c0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l8c1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l8c2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l8c3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l8c4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l8c5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l8c6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l8c7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l8c8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l8c9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(l9c0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l9c1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l9c2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l9c3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l9c4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l9c5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l9c6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l9c7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l9c8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l9c9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(l0c10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l1c10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l2c10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l3c10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l4c10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l5c10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l6c10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l7c10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l8c10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l9c10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(l0c11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l1c11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l2c11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l3c11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l4c11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l5c11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l6c11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l7c11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l8c11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(l9c11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(l10c0, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l10c1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l10c2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l10c3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l10c4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l10c5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l10c6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l10c7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l10c8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l10c9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l10c10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l10c11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(l0c0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l0c1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l0c2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l0c3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l0c5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l0c6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l0c4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l0c7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l0c8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l0c9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(l1c0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l1c1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l1c2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l1c3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l1c5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l1c6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l1c4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l1c7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l1c8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l1c9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(l2c0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l2c1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l2c2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l2c3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l2c5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l2c6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l2c4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l2c7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l2c8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l2c9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(l3c0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l3c1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l3c2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l3c3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l3c5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l3c6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l3c4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l3c7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l3c8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l3c9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(l4c0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l4c1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l4c2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l4c3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l4c5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l4c6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l4c4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l4c7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l4c8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l4c9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(l5c0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l5c1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l5c2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l5c3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l5c5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l5c6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l5c4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l5c7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l5c8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l5c9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(l6c0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l6c1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l6c2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l6c3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l6c5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l6c6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l6c4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l6c7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l6c8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l6c9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(l7c0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l7c1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l7c2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l7c3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l7c5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l7c6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l7c4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l7c7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l7c8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l7c9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(l8c0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l8c1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l8c2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l8c3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l8c5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l8c6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l8c4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l8c7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l8c8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l8c9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(l9c0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l9c1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l9c2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l9c3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l9c5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l9c6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l9c4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l9c7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l9c8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(l9c9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(l0c10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(l1c10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(l2c10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(l3c10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(l4c10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(l5c10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(l6c10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(l7c10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(l8c10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(l9c10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(l0c11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l1c11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l2c11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l3c11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l4c11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l5c11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l6c11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l7c11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l8c11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l9c11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(l10c0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l10c1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l10c2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l10c3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l10c5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l10c6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l10c4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l10c7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l10c8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l10c9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l10c10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(l10c11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(l11c0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l11c1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l11c2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l11c3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l11c5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l11c6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l11c4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l11c7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l11c8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l11c9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(l11c10, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(l11c11, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Inteligência Computacional - QLearning");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));

        buttonExecutar.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        buttonExecutar.setText("Executar");
        buttonExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExecutarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel2.setText("Número de episódios:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NroEpisodios))
                    .addComponent(buttonExecutar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(NroEpisodios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonExecutar)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));

        buttonIniciarExec.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        buttonIniciarExec.setText("Iniciar programa");
        buttonIniciarExec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIniciarExecActionPerformed(evt);
            }
        });

        buttonEncerrarExec.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        buttonEncerrarExec.setText("Encerrar Execução");
        buttonEncerrarExec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEncerrarExecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonIniciarExec, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonEncerrarExec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonIniciarExec)
                    .addComponent(buttonEncerrarExec))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTipoExec.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));

        jLabel7.setText("Selecione o tipo de execução que deseja realizar:");

        buttonMostrarCaminhosPercorridos.setText("Caminhos percorridos");
        buttonMostrarCaminhosPercorridos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMostrarCaminhosPercorridosActionPerformed(evt);
            }
        });

        buttonPercorrerPassoPasso.setText("Percorrer passo-a-passo");
        buttonPercorrerPassoPasso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPercorrerPassoPassoActionPerformed(evt);
            }
        });

        buttonPararTipoExec.setText("Parar");
        buttonPararTipoExec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPararTipoExecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTipoExecLayout = new javax.swing.GroupLayout(panelTipoExec);
        panelTipoExec.setLayout(panelTipoExecLayout);
        panelTipoExecLayout.setHorizontalGroup(
            panelTipoExecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTipoExecLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTipoExecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTipoExecLayout.createSequentialGroup()
                        .addComponent(buttonMostrarCaminhosPercorridos, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonPercorrerPassoPasso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelTipoExecLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonPararTipoExec)))
                .addContainerGap())
        );
        panelTipoExecLayout.setVerticalGroup(
            panelTipoExecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTipoExecLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTipoExecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(buttonPararTipoExec))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTipoExecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonMostrarCaminhosPercorridos)
                    .addComponent(buttonPercorrerPassoPasso))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelTipoExec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTipoExec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        panelPassoPasso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)));

        buttonAssistir.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        buttonAssistir.setText("Assistir");
        buttonAssistir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonAssistirMouseClicked(evt);
            }
        });
        buttonAssistir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAssistirActionPerformed(evt);
            }
        });

        buttonPassoAnterior.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        buttonPassoAnterior.setText("Passo anterior");
        buttonPassoAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPassoAnteriorActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel4.setText("Digite o episódio em que deseja caminhar:");

        buttonProxPasso.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        buttonProxPasso.setText("Próximo passo");
        buttonProxPasso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProxPassoActionPerformed(evt);
            }
        });

        buttonParar.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        buttonParar.setText("Parar");
        buttonParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPararActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel6.setText("Tipo de execução: Percorrer Passo a Passo");

        labelVertAtual.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        labelVertAtual.setText("Você está no vértice --");

        javax.swing.GroupLayout panelPassoPassoLayout = new javax.swing.GroupLayout(panelPassoPasso);
        panelPassoPasso.setLayout(panelPassoPassoLayout);
        panelPassoPassoLayout.setHorizontalGroup(
            panelPassoPassoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPassoPassoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPassoPassoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelPassoPassoLayout.createSequentialGroup()
                        .addGroup(panelPassoPassoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelPassoPassoLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelPassoPassoLayout.createSequentialGroup()
                                .addComponent(buttonPassoAnterior)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonProxPasso)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(panelPassoPassoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idEpisodio2)
                            .addGroup(panelPassoPassoLayout.createSequentialGroup()
                                .addComponent(buttonParar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonAssistir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelPassoPassoLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(labelVertAtual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelPassoPassoLayout.setVerticalGroup(
            panelPassoPassoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPassoPassoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPassoPassoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(idEpisodio2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelPassoPassoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonPassoAnterior)
                    .addComponent(buttonAssistir)
                    .addComponent(buttonProxPasso)
                    .addComponent(buttonParar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelVertAtual)
                .addContainerGap())
        );

        panelCaminhoPercorrido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel3.setText("Digite o episódio que deseja ver o caminho:");

        buttonVerCaminho.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        buttonVerCaminho.setText("Ver caminho");
        buttonVerCaminho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVerCaminhoActionPerformed(evt);
            }
        });

        labelVertPercorridos.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        labelVertPercorridos.setText("Numero de vertices percorridos no episódio --: --");

        buttonEpAnterior.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        buttonEpAnterior.setText("Episódio anterior");
        buttonEpAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEpAnteriorActionPerformed(evt);
            }
        });

        buttonProxEp.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        buttonProxEp.setText("Próximo episódio");
        buttonProxEp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProxEpActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel5.setText("Tipo de execução: mostrar caminhos percorridos");

        javax.swing.GroupLayout panelCaminhoPercorridoLayout = new javax.swing.GroupLayout(panelCaminhoPercorrido);
        panelCaminhoPercorrido.setLayout(panelCaminhoPercorridoLayout);
        panelCaminhoPercorridoLayout.setHorizontalGroup(
            panelCaminhoPercorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCaminhoPercorridoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCaminhoPercorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelVertPercorridos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelCaminhoPercorridoLayout.createSequentialGroup()
                        .addGroup(panelCaminhoPercorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(panelCaminhoPercorridoLayout.createSequentialGroup()
                                .addComponent(buttonEpAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonProxEp)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelCaminhoPercorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idEpisodio)
                            .addComponent(buttonVerCaminho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelCaminhoPercorridoLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelCaminhoPercorridoLayout.setVerticalGroup(
            panelCaminhoPercorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCaminhoPercorridoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCaminhoPercorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(idEpisodio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCaminhoPercorridoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonVerCaminho)
                    .addComponent(buttonEpAnterior)
                    .addComponent(buttonProxEp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelVertPercorridos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPassoPasso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelCaminhoPercorrido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCaminhoPercorrido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelPassoPasso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jMenu1.setText("Sobre os tipo de execução");

        jMenuItem1.setText("Percorrer passo-a-passo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Mostrar caminhos percorridos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Mais opções");

        itemVerCaminhos.setText("Ver recompensas dos caminhos percorridos (Tabela-Q)");
        itemVerCaminhos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemVerCaminhosActionPerformed(evt);
            }
        });
        jMenu2.add(itemVerCaminhos);

        jMenuItem4.setText("Sobre");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Projeto criado por:\nMarco Antônio\nWellison Santos\nÍtalo Lopes"+
                "\n\n© 2018.");
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Ao selecionar a opção 'Percorrer passo-a-passo',\n"+
                "você poderá assistir o rato caminhando pelo ambiente até encontrar o queijo.");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Ao selecionar a opção 'Mostrar caminhos percorridos',\n"+
                "você poderá escolher qualquer um dos episódios para visualizar o caminho pelo "+
                "qual o rato andou antes de encontrar o queijo.");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void buttonIniciarExecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIniciarExecActionPerformed
        // TODO add your handling code here:
        buttonIniciarExec.setEnabled(false);
        buttonExecutar.setEnabled(true);
        NroEpisodios.setEnabled(true);
        buttonEncerrarExec.setEnabled(true);
        this.emexecucao = true;
        NroEpisodios.grabFocus();
    }//GEN-LAST:event_buttonIniciarExecActionPerformed

    private void buttonExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExecutarActionPerformed
        // TODO add your handling code here:
        this.episodios = Integer.parseInt(NroEpisodios.getText());
        ql.zerarTabelaQ(this.tabelaQ);
        ql.iniciarVertices(this.labValores, this.labCompleto);
        gamma = 0.8;
        buttonExecutar.setEnabled(false);
        NroEpisodios.setEnabled(false);
        buttonMostrarCaminhosPercorridos.setEnabled(true);
        buttonPercorrerPassoPasso.setEnabled(true);
        
        ql.QL(this.tabelaQ, this.labCompleto, this.gamma, this.episodios, this.labValores, this.caminhos, this.verticespercorridos);
        JOptionPane.showMessageDialog(null, "Executado com sucesso!");
        JOptionPane.showMessageDialog(null, "Clique em uma das opções de execução!");
        buttonMostrarCaminhosPercorridos.setEnabled(true);
        buttonPercorrerPassoPasso.setEnabled(true);
        this.iniciou = true;
    }//GEN-LAST:event_buttonExecutarActionPerformed

    private void buttonEncerrarExecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEncerrarExecActionPerformed
        // TODO add your handling code here:
        buttonIniciarExec.setEnabled(true);
        buttonEncerrarExec.setEnabled(false);
        panelCaminhoPercorrido.setVisible(false);
        panelPassoPasso.setVisible(false);
        buttonProxEp.setEnabled(false);
        buttonEpAnterior.setEnabled(false);
        buttonPararTipoExec.setEnabled(false);
        buttonMostrarCaminhosPercorridos.setEnabled(false);
        buttonPercorrerPassoPasso.setEnabled(false);
        atualizarLabels(labValores);
        NroEpisodios.setEnabled(false);
        buttonExecutar.setEnabled(false);
        panel.setVisible(false);
        idEpisodio.setText("");
        idEpisodio2.setText("");
        NroEpisodios.setText("");
        this.emexecucao = false;
        this.iniciou = false;
        this.frames.clear();
    }//GEN-LAST:event_buttonEncerrarExecActionPerformed

    private void buttonVerCaminhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerCaminhoActionPerformed
        // TODO add your handling code here:
        int id = Integer.parseInt(idEpisodio.getText());
        if(id<1 || id>this.episodios){
            JOptionPane.showMessageDialog(null, "Você digitou um id de episódio inválido.\n"+
                        "Por favor, digite um valor entre 1 e "+this.episodios+".");
        }else{
            atualizarLabels(this.caminhos.get(id-1));
            labelVertPercorridos.setText("Numero de vertices percorridos no episódio "+id+": "+this.verticespercorridos.get(id-1).size());
            buttonProxEp.setEnabled(true);
            buttonEpAnterior.setEnabled(true); 
            if(id == 1) buttonEpAnterior.setEnabled(false);
            if(id == episodios) buttonProxEp.setEnabled(false);
        }
        
    }//GEN-LAST:event_buttonVerCaminhoActionPerformed

    private void itemVerCaminhosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemVerCaminhosActionPerformed
        // TODO add your handling code here:
        if(this.emexecucao){
            if(this.iniciou){
                int totalep = Integer.parseInt(NroEpisodios.getText())-1;
                int idEp = Integer.parseInt(JOptionPane.showInputDialog("Digite o episódio que você deseja ver as recompensas:"))-1;
                if(idEp<0 || idEp>totalep){
                    JOptionPane.showMessageDialog(null, "Você digitou um id de episódio inválido.\n"+
                                "Por favor, digite um valor entre 1 e "+this.episodios+".");
                }else{
                    String[] recompensas = new String[this.verticespercorridos.get(idEp).size()];
                    int[] aux = new int[2];
                    aux[0] = 0;
                    aux[1] = 0;
                    for(int i=0; i<this.verticespercorridos.get(idEp).size(); i++){
                        int linha = this.verticespercorridos.get(idEp).get(i)[2], coluna = this.verticespercorridos.get(idEp).get(i)[3];
                        int acao = posicao(linha, coluna);
                        int estado = posicao(aux[0], aux[1]);
                        recompensas[i] = " \nVertice <"+linha+", "+coluna+">; recompensa para chegar neste vertice: "+tabelaQ[estado][acao]+";\n";
                        aux[0] = linha;
                        aux[1] = coluna;
                    }
                    
                    TelaRecompensas tr = new TelaRecompensas();
                    tr.setVisible(true);
                    tr.atualizarTextArea(recompensas);
                }
            }else{
                JOptionPane.showMessageDialog(null, "O algoritmo Q-Learning não está em execução.\n"+
                        "Digite um número total de episódios e clique\n"+""
                        + "em 'Executar' para iniciar o algoritmo.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "O algoritmo Q-Learning não está em execução.\n"+
                    "Esta opção ficará disponíve assim que o algoritmo iniciar.");
        }
    }//GEN-LAST:event_itemVerCaminhosActionPerformed

    private void buttonProxEpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProxEpActionPerformed
        // TODO add your handling code here:
        int id = Integer.parseInt(idEpisodio.getText());
        int novoid;
        if(id<1 || id>=this.episodios){
            JOptionPane.showMessageDialog(null, "Não existe episódio depois do digitado. "+
                    "(valor digitado: "+id+")");
        }else{
            novoid = id+1;
            idEpisodio.setText(""+novoid);
            atualizarLabels(this.caminhos.get(novoid-1));
            labelVertPercorridos.setText("Numero de vertices percorridos no episódio "+novoid+": "+this.verticespercorridos.get(novoid-1).size());

            if(novoid == this.episodios) buttonProxEp.setEnabled(false);
            if(novoid > 1) buttonEpAnterior.setEnabled(true);
        }
    }//GEN-LAST:event_buttonProxEpActionPerformed

    private void buttonEpAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEpAnteriorActionPerformed
        // TODO add your handling code here:
        int id = Integer.parseInt(idEpisodio.getText());
        int novoid;
        if(id<=1 || id>this.episodios){
            JOptionPane.showMessageDialog(null, "Não existe episódio anterior ao digitado. "+
                    "(valor digitado: "+id+")");
        }else{
            novoid = id-1;
            idEpisodio.setText(""+novoid);
            atualizarLabels(this.caminhos.get(novoid-1));
            labelVertPercorridos.setText("Numero de vertices percorridos no episódio "+novoid+": "+this.verticespercorridos.get(novoid-1).size());

            if(novoid == 1) buttonEpAnterior.setEnabled(false);
            if(novoid < this.episodios) buttonProxEp.setEnabled(true);
        }
    }//GEN-LAST:event_buttonEpAnteriorActionPerformed

    private void buttonPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPararActionPerformed
        // TODO add your handling code here:
        
        buttonAssistir.setEnabled(true);
        buttonProxPasso.setEnabled(false);
        buttonPassoAnterior.setEnabled(false);
        buttonParar.setEnabled(false);
        idEpisodio2.setEnabled(true);
        
        this.frames.clear();
        atualizarLabels(this.labValores);
        labelVertAtual.setText("Você está no vértice --.");
        
        int id = Integer.parseInt(idEpisodio2.getText());
        if(id == 1) buttonPassoAnterior.setEnabled(false);
        if(id == episodios) buttonProxPasso.setEnabled(false);
    }//GEN-LAST:event_buttonPararActionPerformed

    private void buttonAssistirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAssistirActionPerformed
        int id = Integer.parseInt(idEpisodio2.getText())-1;
        if(id<0 || id>this.episodios-1){
            JOptionPane.showMessageDialog(null, "Você digitou um id de episódio inválido.\n"+
                        "Por favor, digite um valor entre 1 e "+this.episodios+".");
        }else{
            this.idframe = -1;

            this.ambiente = iniciarAmbiente();
            int[][] amb = this.ambiente;

            for(int i=0; i<this.verticespercorridos.get(id).size(); i++){
                int[] b = this.verticespercorridos.get(id).get(i).clone();
                proximoFrame(amb, b);
                this.frames.add(copiarAmb(amb));
            }

            buttonProxPasso.setEnabled(true);
            buttonPassoAnterior.setEnabled(false);
            buttonParar.setEnabled(true);
            buttonAssistir.setEnabled(false);
            idEpisodio2.setEnabled(false);
            idEpisodio2.setEnabled(false);       
            labelVertAtual.setText("Você está no vértice "+(this.idframe+1)+".");
        }
    }//GEN-LAST:event_buttonAssistirActionPerformed

    private void buttonProxPassoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProxPassoActionPerformed
        // TODO add your handling code here:
        int id = Integer.parseInt(idEpisodio2.getText())-1;
        buttonPassoAnterior.setEnabled(true);
        
        this.idframe++;
        
        this.ambiente = this.frames.get(idframe);
        atualizarLabels(this.ambiente);
        labelVertAtual.setText("Você está no vértice "+(this.idframe+1)+".");
                
        if(this.idframe+1==this.verticespercorridos.get(id).size()) buttonProxPasso.setEnabled(false);
    }//GEN-LAST:event_buttonProxPassoActionPerformed

    private void buttonPassoAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPassoAnteriorActionPerformed
        // TODO add your handling code here:
        int id = Integer.parseInt(idEpisodio2.getText())-1;
        buttonProxPasso.setEnabled(true);
        
        this.idframe--;
        if(this.idframe<0){
            atualizarLabels(this.labValores);
            labelVertAtual.setText("Você está no vértice "+(this.idframe+1)+".");
        }else{
            this.ambiente = this.frames.get(idframe);
            atualizarLabels(this.ambiente);
            labelVertAtual.setText("Você está no vértice "+(this.idframe+1)+".");
        }
        if(this.idframe<0) buttonPassoAnterior.setEnabled(false);
    }//GEN-LAST:event_buttonPassoAnteriorActionPerformed

    private void buttonAssistirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonAssistirMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonAssistirMouseClicked

    private void buttonMostrarCaminhosPercorridosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMostrarCaminhosPercorridosActionPerformed
        // TODO add your handling code here:
        buttonMostrarCaminhosPercorridos.setEnabled(false);
        buttonPercorrerPassoPasso.setEnabled(false);
        buttonPararTipoExec.setEnabled(true);
        panel.setVisible(true);
        panelCaminhoPercorrido.setVisible(true);
        panelPassoPasso.setVisible(false);
        idEpisodio.grabFocus();
    }//GEN-LAST:event_buttonMostrarCaminhosPercorridosActionPerformed

    private void buttonPercorrerPassoPassoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPercorrerPassoPassoActionPerformed
        // TODO add your handling code here:
        buttonMostrarCaminhosPercorridos.setEnabled(false);
        buttonPercorrerPassoPasso.setEnabled(false);
        buttonPararTipoExec.setEnabled(true);
        panel.setVisible(true);
        panelCaminhoPercorrido.setVisible(false);
        panelPassoPasso.setVisible(true);
        idEpisodio2.grabFocus();
    }//GEN-LAST:event_buttonPercorrerPassoPassoActionPerformed

    private void buttonPararTipoExecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPararTipoExecActionPerformed
        // TODO add your handling code here:
        buttonMostrarCaminhosPercorridos.setEnabled(true);
        buttonPercorrerPassoPasso.setEnabled(true);
        buttonPararTipoExec.setEnabled(false);
        panel.setVisible(false);
        atualizarLabels(labValores);
        idEpisodio.setText("");
        idEpisodio2.setText("");
        idEpisodio2.setEnabled(true);
        buttonAssistir.setEnabled(true);
        buttonProxEp.setEnabled(false);
        buttonEpAnterior.setEnabled(false);
        buttonPassoAnterior.setEnabled(false);
        buttonProxPasso.setEnabled(false);
        labelVertAtual.setText("Você está no vértice --.");
        labelVertPercorridos.setText("Você está no vértice --.");
        this.frames.clear();
    }//GEN-LAST:event_buttonPararTipoExecActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField NroEpisodios;
    private javax.swing.JButton buttonAssistir;
    private javax.swing.JButton buttonEncerrarExec;
    private javax.swing.JButton buttonEpAnterior;
    private javax.swing.JButton buttonExecutar;
    private javax.swing.JButton buttonIniciarExec;
    private javax.swing.JButton buttonMostrarCaminhosPercorridos;
    private javax.swing.JButton buttonParar;
    private javax.swing.JButton buttonPararTipoExec;
    private javax.swing.JButton buttonPassoAnterior;
    private javax.swing.JButton buttonPercorrerPassoPasso;
    private javax.swing.JButton buttonProxEp;
    private javax.swing.JButton buttonProxPasso;
    private javax.swing.JButton buttonVerCaminho;
    private javax.swing.JTextField idEpisodio;
    private javax.swing.JTextField idEpisodio2;
    private javax.swing.JMenuItem itemVerCaminhos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel l0c0;
    private javax.swing.JLabel l0c1;
    private javax.swing.JLabel l0c10;
    private javax.swing.JLabel l0c11;
    private javax.swing.JLabel l0c2;
    private javax.swing.JLabel l0c3;
    private javax.swing.JLabel l0c4;
    private javax.swing.JLabel l0c5;
    private javax.swing.JLabel l0c6;
    private javax.swing.JLabel l0c7;
    private javax.swing.JLabel l0c8;
    private javax.swing.JLabel l0c9;
    private javax.swing.JLabel l10c0;
    private javax.swing.JLabel l10c1;
    private javax.swing.JLabel l10c10;
    private javax.swing.JLabel l10c11;
    private javax.swing.JLabel l10c2;
    private javax.swing.JLabel l10c3;
    private javax.swing.JLabel l10c4;
    private javax.swing.JLabel l10c5;
    private javax.swing.JLabel l10c6;
    private javax.swing.JLabel l10c7;
    private javax.swing.JLabel l10c8;
    private javax.swing.JLabel l10c9;
    private javax.swing.JLabel l11c0;
    private javax.swing.JLabel l11c1;
    private javax.swing.JLabel l11c10;
    private javax.swing.JLabel l11c11;
    private javax.swing.JLabel l11c2;
    private javax.swing.JLabel l11c3;
    private javax.swing.JLabel l11c4;
    private javax.swing.JLabel l11c5;
    private javax.swing.JLabel l11c6;
    private javax.swing.JLabel l11c7;
    private javax.swing.JLabel l11c8;
    private javax.swing.JLabel l11c9;
    private javax.swing.JLabel l1c0;
    private javax.swing.JLabel l1c1;
    private javax.swing.JLabel l1c10;
    private javax.swing.JLabel l1c11;
    private javax.swing.JLabel l1c2;
    private javax.swing.JLabel l1c3;
    private javax.swing.JLabel l1c4;
    private javax.swing.JLabel l1c5;
    private javax.swing.JLabel l1c6;
    private javax.swing.JLabel l1c7;
    private javax.swing.JLabel l1c8;
    private javax.swing.JLabel l1c9;
    private javax.swing.JLabel l2c0;
    private javax.swing.JLabel l2c1;
    private javax.swing.JLabel l2c10;
    private javax.swing.JLabel l2c11;
    private javax.swing.JLabel l2c2;
    private javax.swing.JLabel l2c3;
    private javax.swing.JLabel l2c4;
    private javax.swing.JLabel l2c5;
    private javax.swing.JLabel l2c6;
    private javax.swing.JLabel l2c7;
    private javax.swing.JLabel l2c8;
    private javax.swing.JLabel l2c9;
    private javax.swing.JLabel l3c0;
    private javax.swing.JLabel l3c1;
    private javax.swing.JLabel l3c10;
    private javax.swing.JLabel l3c11;
    private javax.swing.JLabel l3c2;
    private javax.swing.JLabel l3c3;
    private javax.swing.JLabel l3c4;
    private javax.swing.JLabel l3c5;
    private javax.swing.JLabel l3c6;
    private javax.swing.JLabel l3c7;
    private javax.swing.JLabel l3c8;
    private javax.swing.JLabel l3c9;
    private javax.swing.JLabel l4c0;
    private javax.swing.JLabel l4c1;
    private javax.swing.JLabel l4c10;
    private javax.swing.JLabel l4c11;
    private javax.swing.JLabel l4c2;
    private javax.swing.JLabel l4c3;
    private javax.swing.JLabel l4c4;
    private javax.swing.JLabel l4c5;
    private javax.swing.JLabel l4c6;
    private javax.swing.JLabel l4c7;
    private javax.swing.JLabel l4c8;
    private javax.swing.JLabel l4c9;
    private javax.swing.JLabel l5c0;
    private javax.swing.JLabel l5c1;
    private javax.swing.JLabel l5c10;
    private javax.swing.JLabel l5c11;
    private javax.swing.JLabel l5c2;
    private javax.swing.JLabel l5c3;
    private javax.swing.JLabel l5c4;
    private javax.swing.JLabel l5c5;
    private javax.swing.JLabel l5c6;
    private javax.swing.JLabel l5c7;
    private javax.swing.JLabel l5c8;
    private javax.swing.JLabel l5c9;
    private javax.swing.JLabel l6c0;
    private javax.swing.JLabel l6c1;
    private javax.swing.JLabel l6c10;
    private javax.swing.JLabel l6c11;
    private javax.swing.JLabel l6c2;
    private javax.swing.JLabel l6c3;
    private javax.swing.JLabel l6c4;
    private javax.swing.JLabel l6c5;
    private javax.swing.JLabel l6c6;
    private javax.swing.JLabel l6c7;
    private javax.swing.JLabel l6c8;
    private javax.swing.JLabel l6c9;
    private javax.swing.JLabel l7c0;
    private javax.swing.JLabel l7c1;
    private javax.swing.JLabel l7c10;
    private javax.swing.JLabel l7c11;
    private javax.swing.JLabel l7c2;
    private javax.swing.JLabel l7c3;
    private javax.swing.JLabel l7c4;
    private javax.swing.JLabel l7c5;
    private javax.swing.JLabel l7c6;
    private javax.swing.JLabel l7c7;
    private javax.swing.JLabel l7c8;
    private javax.swing.JLabel l7c9;
    private javax.swing.JLabel l8c0;
    private javax.swing.JLabel l8c1;
    private javax.swing.JLabel l8c10;
    private javax.swing.JLabel l8c11;
    private javax.swing.JLabel l8c2;
    private javax.swing.JLabel l8c3;
    private javax.swing.JLabel l8c4;
    private javax.swing.JLabel l8c5;
    private javax.swing.JLabel l8c6;
    private javax.swing.JLabel l8c7;
    private javax.swing.JLabel l8c8;
    private javax.swing.JLabel l8c9;
    private javax.swing.JLabel l9c0;
    private javax.swing.JLabel l9c1;
    private javax.swing.JLabel l9c10;
    private javax.swing.JLabel l9c11;
    private javax.swing.JLabel l9c2;
    private javax.swing.JLabel l9c3;
    private javax.swing.JLabel l9c4;
    private javax.swing.JLabel l9c5;
    private javax.swing.JLabel l9c6;
    private javax.swing.JLabel l9c7;
    private javax.swing.JLabel l9c8;
    private javax.swing.JLabel l9c9;
    private javax.swing.JLabel labelVertAtual;
    private javax.swing.JLabel labelVertPercorridos;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panelCaminhoPercorrido;
    private javax.swing.JPanel panelPassoPasso;
    private javax.swing.JPanel panelTipoExec;
    // End of variables declaration//GEN-END:variables
}

