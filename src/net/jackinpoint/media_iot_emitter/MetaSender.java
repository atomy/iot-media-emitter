package net.jackinpoint.media_iot_emitter;

import com.google.gson.Gson;

/**
 * Class MetaSender.
 */
public class MetaSender {
    protected final NatsSender natsSender;

    public MetaSender() {
        this.natsSender = new NatsSender();
    }

    /**
     * Sending a message to the NATS backend.
     */
    public void send() {
        if (!this.natsSender.isConnected()) {
            this.natsSender.connect(Config.getNatsUri());
        }

        System.out.println("Emitting message...");
        NatsIotMessage natsIotMessage = new NatsIotMessage();
        natsIotMessage.action = "HEARTBEAT";
        natsIotMessage.emitterVersion = Config.getVersion();
        natsIotMessage.message = "HELLO WORLD"; // %TODO, put in `uptime`

        Gson gson = new Gson();
        String jsonMessage = gson.toJson(natsIotMessage);
        this.natsSender.sendMessage(jsonMessage);
        System.out.println("Emitting message...DONE");
    }
}
