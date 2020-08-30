package other.pack.spring.version.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classMethod")
public class ClassMethodController {
    @GetMapping
    public String getVersion() {
        return "";
    }
}
