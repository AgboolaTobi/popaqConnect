package org.popaqConnect.services.email;

import org.popaqConnect.dtos.requests.EmailRequest;
import org.popaqConnect.dtos.response.EmailResponse;
import org.popaqConnect.services.email.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServicesImpl implements EmailServices {
    @Autowired
    JavaMailSender javaMailSender;
    @Value("ogungbeniopemipo1@gmail.com")
    private String sender;
    @Override
    public EmailResponse send(EmailRequest emailRequest) {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            try {
                mailMessage.setFrom(sender);
                mailMessage.setTo(emailRequest.getReciepent());
                mailMessage.setSubject(emailRequest.getSubject());
                mailMessage.setText(emailRequest.getMessage());
                javaMailSender.send(mailMessage);
                EmailResponse emailResponse = new EmailResponse();
                emailResponse.setMessage("Mail sent Successfully");
                return emailResponse;
            } catch (Exception ex) {
                EmailResponse emailResponse = new EmailResponse();
                emailResponse.setMessage("Error while sending Mail");
                return emailResponse;
            }
        }

}
