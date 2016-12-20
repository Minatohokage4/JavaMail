import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.net.PasswordAuthentication;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Mail();
    }
    public static void Mail()
    {
        final String username="";
        final String password="";
        Properties props=new Properties();
        props.put("mail.smtp.auth",true);
        props.put("mail.smtp.starttls.enable",true);
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.post","587");

        Session session=Session.getInstance(props,new javax.mail.Authenticator()
        {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication()
            {
                return new javax.mail.PasswordAuthentication(username,password);
            }
        });
        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress("@gmail.com")); //Email You
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("keymasterkanda1150@gmail.com")); //Email HIM
            message.setSubject("Test Subject");
            message.setText("Fuck u");
            MimeBodyPart messageBodyPart=new  MimeBodyPart();
            Multipart multipart=new MimeMultipart();


            String file=" Path file";
            String filename="Namefile";
            messageBodyPart =new MimeBodyPart();
            DataSource source=new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);
            System.out.println("Send...");
            Transport.send(message);
            System.out.println("Done");
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

    }
}
