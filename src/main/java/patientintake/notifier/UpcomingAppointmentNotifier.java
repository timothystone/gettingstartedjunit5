package patientintake.notifier;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import patientintake.ClinicCalendar;
import patientintake.PatientAppointment;

public class UpcomingAppointmentNotifier {

  private ClinicCalendar calendar;
  private EmailNotifer emailNotifer;

  public UpcomingAppointmentNotifier(ClinicCalendar calendar, EmailNotifer emailNotifer) {
    this.calendar = calendar;
    this.emailNotifer = emailNotifer;
  }

  public void run() {
    calendar.getTomorrowAppointments().forEach(appt -> {
      sendNotificationForAppointment(appt);
    });
  }

  private void sendNotificationForAppointment(PatientAppointment appt) {
    String email = appt.getPatientLastName().toLowerCase() + appt.getPatientFirstName().toLowerCase() + "@mail.com";
    System.out.println("Sending with body: " + buildMessageBody(appt));
    emailNotifer.sendNotification(email,"Appointment Reminder", buildMessageBody(appt) );
  }

  private String buildMessageBody(PatientAppointment appt) {
    return "You have an appointment tomorrow at "
        + appt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("h:mm a", Locale.US))
        + " with Dr. "
        + appt.getDoctor().getName() + ".";
  }

}