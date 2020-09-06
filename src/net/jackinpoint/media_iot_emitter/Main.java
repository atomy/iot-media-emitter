package net.jackinpoint.media_iot_emitter;

/**
 * Main class, entry class.
 */
public class Main {
    /**
     * Main method.
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        while (true) {
            System.out.println("running...");

            try {
                MetaSender metaSender = new MetaSender();
                metaSender.send();
                Thread.sleep(100 * 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
