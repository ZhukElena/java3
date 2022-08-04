package com.geekbrains;

public class Main {
    private final Object mon = new Object();

    private volatile char currentLetter = 'A';

    public static void main(String[] args) {

        Main waitNotifyObj = new Main();
        Thread thread1 = new Thread(() -> {
            waitNotifyObj.printA();
        });
        Thread thread2 = new Thread(() -> {
            waitNotifyObj.printB();
        });
        Thread thread3 = new Thread(() -> {
            waitNotifyObj.printC();
        });
        thread1.start();
        thread2.start();
        thread3.start();
    }

    public void printA() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'A') {
                        mon.wait(1);
                    }
                    System.out.print("A");
                    currentLetter = 'B';
                    mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printB() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'B') {
                        mon.wait(1);
                    }
                    System.out.print("B");
                    currentLetter = 'C';
                    mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printC() {
        synchronized (mon) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != 'C') {
                        mon.wait(1);
                    }
                    System.out.print("C");
                    currentLetter = 'A';
                    mon.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

