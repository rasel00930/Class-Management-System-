package security;

public class MailingThread implements Runnable{
    
    String recipientEmailAddress, emailSubject, emailMessage;

    public MailingThread(String recipientEmailAddress, String emailSubject, String emailMessage) {
        this.recipientEmailAddress = recipientEmailAddress;
        this.emailSubject = emailSubject;
        this.emailMessage = emailMessage;
    }
    
    @Override
    public void run() {
        Mail.sendMail(recipientEmailAddress, emailSubject, emailMessage);
    }
    
}
