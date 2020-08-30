package other.pack.spring.version.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mihkopylov.spring.version.VersionedResource;

@RestController
@VersionedResource(from = 3)
public class Class3Controller {
    @GetMapping("/class")
    public String getVersion3() {
        return "3";
    }
}
