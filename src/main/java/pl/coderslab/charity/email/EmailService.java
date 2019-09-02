package pl.coderslab.charity.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

    private JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendRegisterConfirmation(String toWhom){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toWhom);
        simpleMailMessage.setSubject("TEST");
        simpleMailMessage.setText("Właśnie się zarejestrowałeś i otrzymałeś tego maila!");
        emailSender.send(simpleMailMessage);
    }
}
