package view;

import controller.ThreadCalc;

import java.util.concurrent.Semaphore;

public class main {
    public static void main(String[] args) {
        int permissoes = 2;
        Semaphore semaforo = new Semaphore(permissoes);
        for(int idCalc = 0; idCalc < 21; idCalc++) {
            Thread tCalc = new ThreadCalc(idCalc, semaforo);
            tCalc.start();
        }
    }
}
