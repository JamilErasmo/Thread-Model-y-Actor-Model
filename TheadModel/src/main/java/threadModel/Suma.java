/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadModel;

/**
 *
 * @author Usuario
 */
public class Suma extends Thread {
 private final int[][] matrizRes;
    private final int[][] matrizA;
    private final int[][] matrizB;
    private final int minFil;
    private final int maxFil;

    public Suma(int[][] matrizRes, int[][] matrizA, int[][] matrizB,
            int minFil, int maxFil) {
        this.minFil = minFil;
        this.maxFil = maxFil;
        this.matrizRes = matrizRes;
        this.matrizA = matrizA;
        this.matrizB = matrizB;
    }

    @Override
    public void run() {
        for (int i = minFil; i < maxFil; ++i) {
            for (int j = 0; j < matrizRes[i].length; ++j) {
                matrizRes[i][j] = matrizA[i][j] + matrizB[i][j];
            }
        }
    }
   

}
