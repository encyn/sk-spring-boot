package rs.raf.service3.ctrl;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.MessageEndpoint;
import rs.raf.service3.dto.MailDto;
import rs.raf.service3.service.InputChannel;
import rs.raf.service3.service.MsgHandler;

@MessageEndpoint
@EnableBinding(InputChannel.class)
@RequiredArgsConstructor
public class MessageReceiver {

    private final MsgHandler msgHandler;

    @StreamListener(InputChannel.CHANNEL_NAME)
    public void receive(MailDto mailDto) {
        msgHandler.sendEmail(mailDto);
    }

}
