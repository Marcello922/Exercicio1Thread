package controller;

import java.util.concurrent.Semaphore;

public class ThreadCalc extends Thread{
    private int tId,vezes,restoid;
    private long tempocalc,tempobd;
    private Semaphore semaforo;
    public ThreadCalc(int tId, Semaphore semaforo){
        this.tId = tId;
        this.semaforo = semaforo;
        restoid = tId % 3;
        if(restoid == 1){
            vezes = 2;
        }
        else{
            vezes = 3;
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < vezes; i++){
            try {
                calcula();
                semaforo.acquire();
                transacaoBd();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                semaforo.release();
                }
            }
    }

    public void calcula(){
        double max = 0,min = 0;
        switch (restoid){
            case 0:
                min = 1000;
                max = 2000;
                break;
            case 1:
                min = 200;
                max = 1000;
                break;
            case 2:
                min = 500;
                max = 1500;
                break;
        }
        tempocalc = (long)(Math.random() * (max - min + 1) + min);
        try {
            System.out.println("#"+tId+" Realizando calculos;");
            sleep(tempocalc);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void transacaoBd(){
        double max = 0,min = 0;
        switch (restoid){
            case 0:
                tempobd = 1500;
                break;
            case 1:
                tempobd = 1000;
                break;
            case 2:
                tempobd = 1500;
                break;
        }

        try {
            System.out.println("#"+tId+" Realizando transação de BD;");
            sleep(tempobd);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
