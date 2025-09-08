package webCalendarSpring;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/event")
    public ResponseEntity<CreateEventResponseDto> createEvent(@RequestBody @Valid EventRequestDto request) {
        return ResponseEntity
                .ok(eventService.createEvent(request));
    }

    @GetMapping("/event/today")
    public ResponseEntity<List<Event>> getTodayEvents() {
        return ResponseEntity
                .ok(eventService.getTodayEvents());
    }

    @GetMapping("/event")
    public ResponseEntity<List<Event>> getEvents(
            @RequestParam(name = "start_time", required = false) @Valid LocalDate startTime,
            @RequestParam(name = "end_time", required = false) @Valid LocalDate endTime) {

        System.out.println(startTime + " " + endTime);

        return eventService.getEvents(startTime, endTime).isEmpty() ?
                ResponseEntity
                        .status(HttpStatus.NO_CONTENT)
                        .build() :
                ResponseEntity
                        .ok(eventService.getEvents(startTime, endTime));
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return ResponseEntity
                .ok(eventService.getEvent(id));
    }

    @DeleteMapping("/event/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable Long id) {
        return ResponseEntity
                .ok(eventService.deleteEvent(id));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> handleValidationException(MethodArgumentNotValidException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<?> handleEventNotFoundException(EventNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", e.getMessage()));
    }
}
