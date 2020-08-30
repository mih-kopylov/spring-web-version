package other.pack.spring.version.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mihkopylov.spring.version.VersionedResource;

@RestController
public class MethodController {
    @GetMapping("/method")
    @VersionedResource(from = 1)
    public String getVersion1() {
        return "1";
    }

    @GetMapping("/method")
    @VersionedResource(from = 3)
    public String getVersion3() {
        return "3";
    }

    @GetMapping("/method")
    public String getWithoutVersion() {
        return "";
    }
}
