package webCalendarSpring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EventController {
    @GetMapping("/event/today")
    public ResponseEntity<List> getTodayEvent() {
        return ResponseEntity.ok(new ArrayList());
    }
}
