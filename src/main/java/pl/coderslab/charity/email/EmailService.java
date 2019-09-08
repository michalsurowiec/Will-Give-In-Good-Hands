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

    public void sendRegisterConfirmation(String toWhom, String authenticationToken, String pathContext){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toWhom);
        simpleMailMessage.setSubject("Potwierdzenie rejestracji");
        simpleMailMessage.setText("Dziękujemy za zarejestrowanie się na naszej stronie!\n" +
                "W celu aktywowanie swojego konta skopiuj poniższy link do przeglądarki i go otwórz. \n" +
                "Jeżeli nie rejestrowałeś się u nas zignoruj wiadomość\n" +
                pathContext.replace("register", "confirm/") + authenticationToken);
        emailSender.send(simpleMailMessage);
    }

    public void sendChangingPasswordForm(String toWhom){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toWhom);
        simpleMailMessage.setSubject("Zmiana hasła");
        simpleMailMessage.setText("Wysyłamy tego maila ponieważ poprosiłeś o zmianę hasła. Jeżeli nie zrobiłeś tego to zignoruj tą wiadomość. \n" +
                "W celu zmiany hasła skopiuj poniższy link do przeglądarki i go otwórz. \n" +
                "/changePassword");
        emailSender.send(simpleMailMessage);
    }
}
