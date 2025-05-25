package altamirano.hernandez.devjobs_springboot.helpers;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import org.thymeleaf.context.Context;

@Service
public class EnvioEmails {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    public void emailRecuperacionPassword(String to, String subject, String nombre, String tokenURL){
        Context context = new Context();
        context.setVariable("nombre", nombre);
        String url = "http://localhost:8080/olvide-password/" + tokenURL ;
        context.setVariable("url", url);

        String htmlContent = templateEngine.process("email-template", context);
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
