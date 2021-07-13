package patientintake.notifier;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patientintake.ClinicCalendar;

class UpcomingAppointmentNotifierShould {

  private EmailNotifierTestDouble emailDouble;

  @BeforeEach
  void setUp() {
    emailDouble = new EmailNotifierTestDouble();
  }

  @Test
  void sendNotificationWithCorrectFormat() {
    ClinicCalendar calendar = new ClinicCalendar(LocalDate.of(2018, 8, 26));
    calendar.addAppointment("Timothy", "Stone", "avery",
        "08/27/2018 2:00 pm");
    UpcomingAppointmentNotifier notifier = new UpcomingAppointmentNotifier(calendar, emailDouble);

    notifier.run();

    assertEquals(1, emailDouble.receivedMessages.size());
    EmailNotifierTestDouble.Message expectedMessage = emailDouble.receivedMessages.get(0);
    assertAll(
        () -> assertEquals("stonetimothy@mail.com", expectedMessage.toAddress),
        () -> assertEquals("Appointment Reminder", expectedMessage.subject),
        () -> assertEquals("You have an appointment tomorrow at 2:00 PM "
            + "with Dr. Ralph Avery.", expectedMessage.body)
    );

  }
}
