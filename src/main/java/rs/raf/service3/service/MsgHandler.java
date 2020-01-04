package rs.raf.service3.service;

import rs.raf.service3.dto.MailDto;

public interface MsgHandler {

    void sendEmail (MailDto mailDto);

}
