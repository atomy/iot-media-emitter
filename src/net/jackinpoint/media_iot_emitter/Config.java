package net.jackinpoint.media_iot_emitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class Config.
 */
public class Config {
    public static String currentVersion;

    public static String hostname;

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

    /**
     * Read current version of app from file.
     *
     * @return String
     */
    public static String getVersion() {
        if (null != Config.currentVersion && Config.currentVersion.length() > 0) {
            return Config.currentVersion;
        }

        String fileContent = "0.0.0";

        try {
            fileContent = new String(Files.readAllBytes(Paths.get("current_version")));
        } catch (IOException e) {
            System.out.println(e);
        }

        Config.currentVersion = fileContent.trim();
        return Config.currentVersion;
    }

    /**
     * Read hostname of host from file.
     *
     * @return String
     */
    public static String getHostname() {
        if (null != Config.hostname && Config.hostname.length() > 0) {
            return Config.hostname;
        }

        String fileContent = "N/A";

        try {
            fileContent = new String(Files.readAllBytes(Paths.get("hostname")));
        } catch (IOException e) {
            System.out.println(e);
        }

        Config.hostname = fileContent.trim();
        return Config.hostname;
    }

    /**
     * Read load of host from host.
     *
     * @return String
     */
    public static String getLoad() {
        try {
            Process uptimeProc = Runtime.getRuntime().exec("uptime");
            BufferedReader in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
            String uptimeContent = in.readLine();
            String pattern = "^.*load average: (.*)$";

            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(uptimeContent);

            if (m.find()) {
                return m.group(1).trim();
            }
        } catch (Exception exception) {
            return "N/A";
        }

        return "N/A";
    }
}
