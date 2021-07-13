package patientintake;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ClinicCalendarShould {

  private ClinicCalendar calendar;

  @BeforeEach
  void setUp() {
    calendar = new ClinicCalendar(LocalDate.of(2021, 7, 12));
  }

  @Test
  void allowEntryOfAnAppointment() {
    calendar.addAppointment("Timothy", "Stone", "Avery", "07/12/2021 07:00 am");
    List<PatientAppointment> appointments = calendar.getAppointments();
    assertNotNull(appointments);
    assertEquals(1, appointments.size());
    PatientAppointment appointment = appointments.get(0);

    assertAll(
        () -> assertEquals("Timothy", appointment.getPatientFirstName()),
        () -> assertEquals("Stone", appointment.getPatientLastName()),
        () -> assertEquals(Doctor.avery, appointment.getDoctor()),
        () -> assertEquals("7/12/2021 07:00 AM",
            appointment.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")))
    );
  }

  @Test
  void returnTrueForHasAppointmentsIfThereAreAppointments() {
    calendar.addAppointment("Timothy", "Stone", "Avery", "07/12/2021 07:00 am");
    assertTrue(calendar.hasAppointment(LocalDate.of(2021, 7, 12)));
  }

  @Test
  void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
    assertFalse(calendar.hasAppointment(LocalDate.of(2021, 7, 12)));

  }

  @Nested
  @DisplayName("return appointments correctly")
  class AppointmentsForDay {

    @Test
    @DisplayName("for today")
    void returnCurrentDayAppointments() {
      calendar.addAppointment("David", "Stone", "Avery", "07/12/2021 04:00 pm");
      calendar.addAppointment("Dawn", "Stone", "Avery", "07/12/2021 06:00 pm");
      calendar.addAppointment("Timothy", "Stone", "Murphy", "07/13/2021 07:00 am");
      assertEquals(2, calendar.getTodayAppointments().size());
    }

    @Test
    @DisplayName("for tomorrow")
    void returnTomorrowsAppointments() {
      calendar.addAppointment("David", "Stone", "Avery", "07/13/2021 04:00 pm");
      calendar.addAppointment("Dawn", "Stone", "Avery", "07/13/2021 06:00 pm");
      calendar.addAppointment("Timothy", "Stone", "Murphy", "07/12/2021 07:00 am");
      assertEquals(2, calendar.getTomorrowAppointments().size());
    }

  }

  @Nested
  @DisplayName("return upcoming appointments")
  class UpcomingAppointments {

    @Test
    @DisplayName("as empty list when there are none")
    void whenThereAreNone() {
      List<PatientAppointment> appointments = calendar.getUpcomingAppointments();
      assertEquals(0, appointments.size());
    }

    @Test
    @DisplayName("correctly when there are some in the past")
    void whenThereAreSomePastAndFuture() {
      calendar.addAppointment("David", "Stone", "Avery", "06/13/2021 04:00 pm");
      calendar.addAppointment("Dawn", "Stone", "Avery", "06/13/2021 06:00 pm");
      calendar.addAppointment("Timothy", "Stone", "Murphy", "08/12/2021 07:00 am");
      assertEquals(1, calendar.getUpcomingAppointments().size());
    }
  }
}
