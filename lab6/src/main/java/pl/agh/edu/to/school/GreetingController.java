package pl.agh.edu.to.school;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    @GetMapping
    public List<String> greeting() {
        return List.of("Technologie", "obiektowe");
    }
}