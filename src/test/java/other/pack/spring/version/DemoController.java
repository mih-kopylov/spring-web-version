package other.pack.spring.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mihkopylov.spring.version.VersionedResource;

@RestController
public class DemoController {
    @GetMapping("/method")
    @VersionedResource(from = 1)
    public String getListVersion1() {
        return "1";
    }

    @GetMapping("/method")
    @VersionedResource(from = 3)
    public String getListVersion3() {
        return "3";
    }

    @GetMapping("/method")
    public String getListWithoutVersion() {
        return "";
    }
}
