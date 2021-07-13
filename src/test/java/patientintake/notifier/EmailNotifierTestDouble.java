package patientintake.notifier;

import java.util.ArrayList;

public class EmailNotifierTestDouble implements EmailNotifer {

  ArrayList<Message> receivedMessages = new ArrayList<>();

  @Override
  public void sendNotification(String toAddress, String subject, String body) {
    receivedMessages.add(new Message(toAddress, subject, body));
  }

  class Message {
    public String toAddress;
    public String subject;
    public String body;

    public Message(String toAddress, String subject, String body) {
      this.toAddress = toAddress;
      this.subject = subject;
      this.body = body;
    }
  }
}
