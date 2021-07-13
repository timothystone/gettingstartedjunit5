package patientintake.notifier;

public interface EmailNotifer {
  void sendNotification(String address, String subject, String body);
}
