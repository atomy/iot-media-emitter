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
        MetaSender metaSender = new MetaSender();

        while (true) {
            System.out.println("running...");

            try {
                metaSender.send();
                Thread.sleep(500 * 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
