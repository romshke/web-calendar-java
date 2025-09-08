package webCalendarSpring;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public CreateEventResponseDto createEvent(EventRequestDto request) {
        Event event = new Event();
        event.setEvent(request.getEvent());
        event.setDate(request.getDate());

        eventRepository.save(event);

        return new CreateEventResponseDto(event.getEvent(), event.getDate());
    }

    public List<Event> getTodayEvents() {
        return eventRepository.findByDate(LocalDate.now());
    }

    public List<Event> getEvents(LocalDate startDate, LocalDate endDate) {
        return startDate == null || endDate == null ?
                eventRepository.findAll() :
                eventRepository.findByDateBetween(startDate, endDate);
    }

    public Event getEvent(Long id) {
        return eventRepository.findById(id).orElseThrow(() ->
                new EventNotFoundException("The event doesn't exist!"));
    }

    public Event deleteEvent(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() ->
                new EventNotFoundException("The event doesn't exist!"));

        eventRepository.deleteById(id);
        return event;
    }
}
