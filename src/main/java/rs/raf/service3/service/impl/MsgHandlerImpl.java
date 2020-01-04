package rs.raf.service3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import rs.raf.service3.dto.MailDto;
import rs.raf.service3.service.MsgHandler;

@Service
@Component
public class MsgHandlerImpl implements MsgHandler {

    @Autowired
    JavaMailSender mailSender;

    @Async
    @Override
    public void sendEmail (MailDto mailDto) {
        //System.out.println(mailDto.toString());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getTo());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getBody());
        mailSender.send(message);
    }

}
