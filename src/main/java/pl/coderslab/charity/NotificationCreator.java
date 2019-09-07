package pl.coderslab.charity;

import lombok.Data;
import org.springframework.ui.Model;

@Data
public class NotificationCreator {

    private String notificationSite = "notification";

    public String showNotification(String notification, Model model){
        model.addAttribute("notification", notification);
        return notificationSite;
    }
}
