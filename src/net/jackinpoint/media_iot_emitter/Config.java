package net.jackinpoint.media_iot_emitter;

/**
 * Class Config.
 */
public class Config {
    /**
     * Build and return nats-uri for connection.
     *
     * @return String nats-uri e.g. nats://user1:secret2@1.1.1.1:8888
     */
    public static String getNatsUri() {
        String natsHost = System.getenv("NATS_HOST");

        if (null == natsHost || natsHost.length() <= 0) {
            throw new RuntimeException("Missing env *NATS_HOST*!");
        }

        String natsPort = System.getenv("NATS_PORT");
        if (null == natsPort) {
            throw new RuntimeException("Missing env *NATS_PORT*!");
        }

        int natsPortNumber = Integer.parseInt(System.getenv("NATS_PORT"));
        if (natsPortNumber <= 0) {
            throw new RuntimeException("Missing env *NATS_PORT*!");
        }

        String natsUser = System.getenv("NATS_USER");
        String natsPass = System.getenv("NATS_PASS");

        if (null != natsUser && natsUser.length() > 0 && null != natsPass && natsPass.length() > 0) {
            return String.format("nats://%s:%s@%s:%d", natsUser, natsPass, natsHost, natsPortNumber);
        }

        return String.format("nats://%s:%d", natsHost, natsPortNumber);
    }
}
