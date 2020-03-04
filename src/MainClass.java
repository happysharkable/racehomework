import java.util.concurrent.CountDownLatch;

public class MainClass {
    private static final int CARS_COUNT = 4;
    private static CountDownLatch isReadyCdl = new CountDownLatch(CARS_COUNT);
    private static CountDownLatch isFinishedCdl = new CountDownLatch(CARS_COUNT);

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        try {
            isReadyCdl.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            isFinishedCdl.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static int getCarCount() {
        return CARS_COUNT;
    }

    static CountDownLatch getReadyCountDownLatch() {
        return MainClass.isReadyCdl;
    }

    static CountDownLatch getFinishedCountDownLatch() {
        return MainClass.isFinishedCdl;
    }
}