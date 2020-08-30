package other.pack.spring.version.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mihkopylov.spring.version.VersionedResource;

@RestController
@VersionedResource(from = 1)
public class Class1Controller {
    @GetMapping("/class")
    public String getVersion1() {
        return "1";
    }
}
