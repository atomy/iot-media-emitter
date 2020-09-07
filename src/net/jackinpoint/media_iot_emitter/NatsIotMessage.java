package net.jackinpoint.media_iot_emitter;

/**
 * Class NatsIotMessage.
 */
public class NatsIotMessage {
    public String emitterVersion;

    public String action;

    public String message;

    public long timestamp;

    public NatsIotMessage() {
        this.timestamp = System.currentTimeMillis();
    }
}
