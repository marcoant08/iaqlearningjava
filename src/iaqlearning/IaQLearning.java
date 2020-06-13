
package iaqlearning;

import visual.Tela;

/**
 *
 * @author MarcoAntônio, Wellison, Ítalo
 */
public class IaQLearning {
    public static void main(String[] args){
        int R = 8, Q = 9, C = 1, P = 0;
        int[][] labValores = {
                                {R, C, P, P, P, P, P, P, P, P, P, C},
                                {C, C, C, C, P, C, C, C, C, C, C, C},
                                {C, P, C, C, C, C, P, C, C, C, P, C},
                                {C, P, P, C, C, C, P, C, P, C, P, P},
                                {C, C, C, C, C, P, P, P, P, C, C, P},
                                {C, C, C, C, C, C, C, C, P, C, C, P},
                                {P, C, C, P, P, C, C, C, C, C, C, C},
                                {P, C, C, C, C, C, P, C, P, P, P, C},
                                {P, C, C, C, C, C, C, C, C, C, C, C},
                                {P, P, P, C, C, P, P, P, C, P, C, C},
                                {C, C, C, C, C, C, C, C, C, P, C, C},
                                {C, C, P, P, P, P, P, C, C, P, C, Q}
                               };
        
        Tela tela = new Tela();
        tela.setLabValores(labValores);
        tela.setVisible(true);
        tela.atualizarLabels(labValores);
        
    }
    
}
