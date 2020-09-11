package net.jackinpoint.media_iot_emitter;

import com.google.gson.annotations.SerializedName;

/**
 * Class NatsIotMessage.
 */
public class NatsIotMessage {
    @SerializedName("emitter-version")
    public String emitterVersion;

    public String action;

    public String message;

    public long timestamp;

    public String hostname;

    public NatsIotMessage() {
        this.timestamp = System.currentTimeMillis();
    }
}
