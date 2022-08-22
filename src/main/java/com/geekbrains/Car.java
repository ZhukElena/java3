package com.geekbrains;


public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;
    private static final Object finishMonitor = new Object();
    private static boolean hasFinished = false;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            Main.cdlReady.countDown();
            System.out.println(this.name + " готов");
            Main.cdlReady.await();

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        synchronized (finishMonitor) {
            if (!hasFinished) {
                hasFinished = true;
                System.out.println(this.name + " выиграл гонку!!!");
            }
        }
        Main.cdlRace.countDown();
    }

}