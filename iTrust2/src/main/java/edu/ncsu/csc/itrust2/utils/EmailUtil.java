package edu.ncsu.csc.itrust2.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.ncsu.csc.itrust2.models.Email;
import edu.ncsu.csc.itrust2.models.User;
import edu.ncsu.csc.itrust2.services.EmailService;

@Component
public class EmailUtil {

    private final EmailService service;

    public EmailUtil(EmailService service) {
        this.service = service;
    }

    public void sendEmail ( final User receiver, final String subject, final String messageBody ) {
        sendEmail( "iTrust2 System", receiver, subject, messageBody );
    }

    public void sendEmail ( final String sender, final User receiver, final String subject, final String messageBody ) {
        final Email email = new Email( sender, receiver, subject, messageBody );
        service.save( email );
    }
}
