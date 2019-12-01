package springbootcrud;

import org.junit.jupiter.api.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class SpringbootCrudApplicationTests {
    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    void contextLoads() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("今晚回家吃饭");
        simpleMailMessage.setText("今晚吃平菇肉片汤");
        simpleMailMessage.setTo("18030638805@163.com");
        simpleMailMessage.setFrom("505970021@qq.com");
        mailSender.send(simpleMailMessage);
    }

    @Test
    void sendMultiMail() throws Exception{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setSubject("明天不上班");
        mimeMessageHelper.setText("<h2 style='color: #f00;'>明天规定在家休息</h2>", true);
        mimeMessageHelper.addAttachment("shop.jpg", new File("/Users/rickyhuang/Desktop/1.jpg"));

        mimeMessageHelper.setTo("18030638805@163.com");
        mimeMessageHelper.setFrom("505970021@qq.com");

        mailSender.send(mimeMessage);

    }
    @Test
    void testTime() {
        System.out.println(System.currentTimeMillis());
    }

}
