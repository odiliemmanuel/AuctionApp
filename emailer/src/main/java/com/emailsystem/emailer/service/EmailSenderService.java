package com.emailsystem.emailer.service;

import com.emailsystem.emailer.model.EmailEvent;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailSenderService {

    private final JavaMailSender mailSender;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }



    public void send(EmailEvent event) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("odiliejeh09@gmail.com", "Email Service");


            helper.setTo(event.to());

            helper.setSubject(event.subject());

            helper.setText(buildHtmlBody(event.body()), true);

            mailSender.send(message);
            System.out.println("Email sent to: " + event.to());

        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
            throw new RuntimeException("Email sending failed", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    private String buildHtmlBody(String body) {
        return """
            <!DOCTYPE html>
            <html>
            <body style="margin: 0; padding: 0; background-color: #000000;">
            
                <table width="100%%" cellpadding="0" cellspacing="0"
                       style="background-color: #000000; padding: 40px 0;">
                    <tr>
                        <td align="center">
                        
                            <table width="600" cellpadding="0" cellspacing="0"
                                   style="background-color: #1a1a1a;
                                          border-radius: 8px;
                                          overflow: hidden;
                                          font-family: Arial, sans-serif;">
                                          
                                <!-- Header -->
                                <tr>
                                    <td style="background-color: #4A90E2;
                                               padding: 25px;
                                               text-align: center;">
                                        <h2 style="margin: 0;
                                                   color: #ffffff;
                                                   font-size: 22px;">
                                            Email Service
                                        </h2>
                                    </td>
                                </tr>
                                
                                <!-- Body -->
                                <tr>
                                    <td style="padding: 30px;
                                               color: #ffffff;
                                               font-size: 15px;
                                               line-height: 1.7;">
                                        <p style="margin: 0;">%s</p>
                                    </td>
                                </tr>
                                
                                <!-- Footer -->
                                <tr>
                                    <td style="background-color: #111111;
                                               padding: 15px;
                                               text-align: center;
                                               color: #777777;
                                               font-size: 12px;">
                                        <p style="margin: 0;">
                                            This email was sent automatically. 
                                            Please do not reply.
                                        </p>
                                    </td>
                                </tr>
                                
                            </table>
                            <!-- End container -->
                            
                        </td>
                    </tr>
                </table>
                
            </body>
            </html>
            """.formatted(body);
    }



    public void sendWelcomeEmail(String toEmail, String firstName) {
        sendEmail(toEmail, "Welcome to Auction System!",
                buildHtml("Welcome!", "Hi <strong>" + firstName + "</strong>, your account has been created successfully."));
    }

    public void sendAuctionCreatedEmail(String toEmail, String auctionId) {
        sendEmail(toEmail, "Your Auction is Live!",
                buildHtml("Auction Created", "Your auction <strong>" + auctionId + "</strong> is now live."));
    }

    public void sendAuctionCancelledEmail(String toEmail, String auctionId) {
        sendEmail(toEmail, "Auction Cancelled",
                buildHtml("Auction Cancelled", "Your auction <strong>" + auctionId + "</strong> has been cancelled."));
    }

    public void sendNewBidEmail(String toEmail, String auctionId) {
        sendEmail(toEmail, "New Bid on Your Auction",
                buildHtml("New Bid!", "Someone placed a new bid on auction <strong>" + auctionId + "</strong>."));
    }

    private void sendEmail(String toEmail, String subject, String htmlBody) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("odiliejeh09@gmail.com", "Auction System");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);
            mailSender.send(message);
            System.out.println("Email sent to: " + toEmail);
        } catch (MessagingException e) {
            System.err.println("Failed to send email to " + toEmail + ": " + e.getMessage());
            throw new RuntimeException("Email sending failed", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildHtml(String title, String body) {
        return """
                <!DOCTYPE html>
                <html>
                <body style="margin:0;padding:0;background-color:#000000;">
                <table width="100%%" cellpadding="0" cellspacing="0"
                       style="background-color:#000000;padding:40px 0;">
                    <tr><td align="center">
                        <table width="600" cellpadding="0" cellspacing="0"
                               style="background-color:#1a1a1a;border-radius:8px;font-family:Arial,sans-serif;">
                            <tr>
                                <td style="background-color:#4A90E2;padding:25px;text-align:center;">
                                    <h2 style="margin:0;color:#ffffff;">%s</h2>
                                </td>
                            </tr>
                            <tr>
                                <td style="padding:30px;color:#ffffff;font-size:15px;line-height:1.7;">
                                    <p style="margin:0;">%s</p>
                                </td>
                            </tr>
                            <tr>
                                <td style="background-color:#111111;padding:15px;
                                           text-align:center;color:#777777;font-size:12px;">
                                    <p style="margin:0;">This is an automated email. Please do not reply.</p>
                                </td>
                            </tr>
                        </table>
                    </td></tr>
                </table>
                </body>
                </html>
                """.formatted(title, body);
    }
}
