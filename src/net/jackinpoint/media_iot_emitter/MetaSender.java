package net.jackinpoint.media_iot_emitter;

/**
 * Class MetaSender.
 */
public class MetaSender {
    protected final NatsSender natsSender;

    public MetaSender() {
        this.natsSender = new NatsSender();
    }

    public void send() {
        System.out.println("Emitting message...");
        NatsIotMessage natsIotMessage = new NatsIotMessage();
        natsIotMessage.action = "HEARTBEAT";
        natsIotMessage.emitterVersion = "1.0.0"; // %TODO, get version from filesystem
        natsIotMessage.message = "{}";
        this.natsSender.sendMessage(message);
        System.out.println("Emitting message...DONE");
    }
}
