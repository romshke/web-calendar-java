package webCalendarSpring;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;

@JsonPropertyOrder({"message", "event", "date"})
public class EventDto {
    private final String event;
    private final LocalDate date;

    public EventDto(Event event) {
        this.event = event.getEvent();
        this.date = event.getDate();
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
