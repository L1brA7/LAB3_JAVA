package Threads;
import javafx.scene.control.ProgressBar;

public class Thread1 implements Runnable {

    private ProgressBar PB;
    int maxL = 100;
    double i = 0;
    public boolean suspendFlag;
    public boolean deadFlag;
    public Thread t;
    public Thread1(ProgressBar pb) {
        t = new Thread(this);
        PB = pb;
        suspendFlag = true;
        deadFlag = false;
        t.start();
    }
    public void run() {
        try {
            for(i = 0; i <= maxL; i++) {
                synchronized(this) {
                    while(suspendFlag) {
                        wait();
                    }
                }
                synchronized(this) {
                    if(deadFlag) break;
                }
                PB.setProgress(i / maxL);
                Thread.sleep(20);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread1 stopped");
        }
        System.out.println("Thread1 ended");
    }

    public synchronized void mystart() {
        suspendFlag = false;
        i = 0;
        notify();
    }
    public synchronized void mysuspend() {
        suspendFlag = true;
    }
    public synchronized void myresume() {
        suspendFlag = false;
        notify();
    }
    public synchronized void myend() {
        suspendFlag = false;
        deadFlag = true;
        notify();
        PB.setProgress(0);
    }
}