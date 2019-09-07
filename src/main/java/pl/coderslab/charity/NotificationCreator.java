package pl.coderslab.charity;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;

@Data
public class NotificationCreator {

    private String notificationSite = "test";

    public String showNotification(String notification, Model model){
        model.addAttribute("notification", notification);
        return notificationSite;
    }
}
