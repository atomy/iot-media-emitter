package net.jackinpoint.media_iot_emitter;

import io.nats.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Class NatsSender. Connecting to NATS backend and emitting new messages.
 */
public class NatsSender {
    protected Connection connection;
    protected Subscription subscription;
    protected Dispatcher dispatcher;
    protected boolean isConnected;

    /**
     * Connect to backend.
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public void connect(String uri) {
        System.out.println(String.format("Connecting to \"%s\"", uri));

        try {
            this.connection = Nats.connect(uri);

            Connection.Status connectionStatus = this.connection.getStatus();

            if (connectionStatus.toString().equalsIgnoreCase("CONNECTED")) {
                this.isConnected = true;
            } else {
                this.isConnected = false;
            }
        } catch (Exception exception) {
            this.isConnected = false;
            System.out.println("EXCEPTION: " + exception.toString());
            return;
        }

        System.out.println(String.format("Connection-Status to \"%s\" is: %s", uri, this.connection.getStatus()));
    }

    /**
     * Getter for isConnected property.
     *
     * @return boolean
     */
    public boolean isConnected() {
        return this.isConnected;
    }

    public void sendMessage(String jsonMessage) {
        System.out.printf(" Sending message, subject: '%s'%n", "heartbeat");
        System.out.printf("  payload: '%s'%n", jsonMessage);
        this.connection.publish("heartbeat", jsonMessage.getBytes(StandardCharsets.UTF_8));
    }

//    /**
//     * Subscribe to message-topic, create callback for new messages.
//     */
//    public void subscribe() {
//        this.dispatcher = this.connection.createDispatcher((message) -> {
//            if (null == this.messageCallback) {
//                return;
//            }
//
//            String response = new String(message.getData(), StandardCharsets.UTF_8);
//            System.out.println("Received message: " + response);
//            this.messageCallback.onNewMessage(new Message(response));
//        });
//
//        this.dispatcher.subscribe("iot.incomming");
//        System.out.println("SUBSCRIBED to *iot.incomming*");
//    }
//
//    /**
//     * Setter for callback.
//     *
//     * @param messageCallback MessageCallback
//     */
//    public void setOnNewMessage(MessageCallback messageCallback) {
//        this.messageCallback = messageCallback;
//    }
}
