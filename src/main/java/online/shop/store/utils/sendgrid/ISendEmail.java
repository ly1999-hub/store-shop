package online.shop.store.utils.sendgrid;

import java.util.List;

public interface ISendEmail {
   void sendMail(String subject, String content, List<String> sendToEmails, List<String> ccEmails, List<String> bccEmails);
}
