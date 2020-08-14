package net.jackinpoint.media_iot_client;

/**
 *
 */
public class Main {
    /**
     * Main method.
     *
     * @param args
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
