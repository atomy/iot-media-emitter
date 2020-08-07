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
                Thread.sleep(500 * 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
