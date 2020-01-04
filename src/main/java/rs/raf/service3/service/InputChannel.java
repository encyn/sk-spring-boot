package rs.raf.service3.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputChannel {

    String CHANNEL_NAME = "S1";

    @Input(CHANNEL_NAME)
    SubscribableChannel input();

}
