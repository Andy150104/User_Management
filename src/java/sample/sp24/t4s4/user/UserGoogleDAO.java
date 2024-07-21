package sample.sp24.t4s4.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.text.DecimalFormat;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import sample.sp24.t4s4.shopping.Cart;
import sample.sp24.t4s4.shopping.Computer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public class UserGoogleDAO {

    public static String GOOGLE_CLIENT_ID = "148451594700-697vg9r0iehunncv9uobsi4r6kdv2h9f.apps.googleusercontent.com";

    public static String GOOGLE_CLIENT_SECRET = "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";

    public static String GOOGLE_REDIRECT_URI = "http://localhost:8084/UserManagementT4S4_ASM_JSTL/LoginWithGoogleController";

    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    public static String GOOGLE_GRANT_TYPE = "authorization_code";

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", GOOGLE_CLIENT_ID)
                        .add("client_secret", GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogleDTO googlePojo = new Gson().fromJson(response, UserGoogleDTO.class);

        return googlePojo;
    }

    public static void sendEmail(String recipient, String subject, Cart cart) throws MessagingException, AddressException, javax.mail.MessagingException, FileNotFoundException, IOException {
        // Sender's email address
        try {
             String senderEmail = "tranduyanh7766@gmail.com";
        // Sender's password
        String password = "aivj ssax fdcy defj";

        // Setup mail server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(senderEmail, password);//To change body of generated methods, choose Tools | Templates.
            }

        });

        // Create a message
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(subject);

        String htmlContent = generateHTMLContent(cart);

        // Set the HTML content
        message.setContent(htmlContent, "text/html");

        // Send the message
        Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String generateHTMLContent(Cart cart) throws IOException {
        StringBuilder htmlContent = new StringBuilder();
        DecimalFormat decimalFormat=new DecimalFormat("#,###");
        int count = 0;
        long total=0;
        htmlContent.append("<html>");
        htmlContent.append("<html lang=\"en\">");
        htmlContent.append("<meta charset=\"UTF-8\">");
        htmlContent.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        htmlContent.append("<head>");
        htmlContent.append("<title>Verification Email</title>");
        htmlContent.append("</head>");
        htmlContent.append("<body>");
        htmlContent.append("<h1 style=\"color: red\">Your Bill</h1>");
        htmlContent.append("<table border=\"1\">");
        htmlContent.append("<thead>");
        htmlContent.append("<tr>");
        htmlContent.append("<th>No</th>");
        htmlContent.append("<th>ID</th>");
        htmlContent.append("<th>Name</th>");
        htmlContent.append("<th>Price</th>");
        htmlContent.append("<th>Quantity</th>");
        htmlContent.append("<th>Total</th>");
        htmlContent.append("<tr>");
        htmlContent.append("</thead>");
        htmlContent.append("<tbody>");
        for (Computer computer : cart.getCart().values()) {
            htmlContent.append("<tr>");
            htmlContent.append("<td>").append(count++).append("</td>");
            htmlContent.append("<td>").append(computer.getId()).append("</td>");
            htmlContent.append("<td>").append(computer.getName()).append("</td>");
            htmlContent.append("<td>").append(decimalFormat.format(computer.getPrice())).append("</td>");
            htmlContent.append("<td>").append(computer.getQuantity()).append("</td>");
            htmlContent.append("<td>").append(decimalFormat.format(computer.getQuantity() * computer.getPrice())).append("</td>");
            htmlContent.append("</tr>");
            total=(long) (computer.getQuantity() * computer.getPrice());
        }
        htmlContent.append("</tbody>");
        htmlContent.append("</table>");
        htmlContent.append("<h2 style=\"color: green\">").append("Total: ").append(decimalFormat.format(total)).append("</h2>");
        htmlContent.append("</body>");
        htmlContent.append("</html>");
        return htmlContent.toString();
    }

}
