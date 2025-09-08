package webCalendarSpring;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;

@JsonPropertyOrder({"message", "event", "date"})
public class CreateEventResponseDto {
    private final String event;
    private final LocalDate date;

    public CreateEventResponseDto(String event, LocalDate date) {
        this.event = event;
        this.date = date;
    }

    public String getMessage() {
        return "The event has been added!";
    }

    public String getEvent() {
        return event;
    }

    public LocalDate getDate() {
        return date;
    }
}
