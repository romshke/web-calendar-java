package webCalendarSpring;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EventController {

    @PostMapping("/event")
    public ResponseEntity<?> createEvent(@RequestBody @Valid Event event) {
        return ResponseEntity.ok(new EventDto(event));
    }

    @GetMapping("/event/today")
    public ResponseEntity<List> getTodayEvent() {
        return ResponseEntity.ok(new ArrayList());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Void> handleValidationException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
