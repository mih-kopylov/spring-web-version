package other.pack.spring.version.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassController {
    @GetMapping("/class")
    public String getVersion() {
        return "";
    }
}
