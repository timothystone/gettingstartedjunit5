package patientintake.notifier;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import patientintake.ClinicCalendar;

class UpcomingAppointmentNotifierShould {

  @Test
  void sendNotificationWithCorrectFormat() {
    ClinicCalendar calendar = new ClinicCalendar(LocalDate.of(2018, 8, 26));
    calendar.addAppointment("Jim", "Weaver", "avery",
        "08/27/2018 2:00 pm");
    UpcomingAppointmentNotifier notifier = new UpcomingAppointmentNotifier(calendar);

    notifier.run();
  }
}
