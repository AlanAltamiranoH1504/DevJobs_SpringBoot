package altamirano.hernandez.devjobs_springboot.helpers;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${backend.ruta.base}")
    String rutaBaseBackenEnd;

    public void emailRecuperacionPassword(String to, String subject, String nombre, String tokenURL) {
        Context context = new Context();
        context.setVariable("nombre", nombre);
        String url = "http://localhost:8080/olvide-password/" + tokenURL;
        context.setVariable("url", url);

        String templateHTML = templateEngine.process("email-template", context);
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(templateHTML, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean emailConfirmacionCuenta(String to, String subject, String nombre, String tokenURL) {
        Context context = new Context();
        context.setVariable("nombre", nombre);
        String url = rutaBaseBackenEnd + "/confirmacion/" + tokenURL;
        context.setVariable("url", url);

        String templateHTML = templateEngine.process("emai-confirmacion", context);
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(templateHTML, true);
            javaMailSender.send(message);

            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
