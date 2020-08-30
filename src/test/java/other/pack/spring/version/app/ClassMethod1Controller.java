package other.pack.spring.version.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mihkopylov.spring.version.VersionedResource;

@RestController
@RequestMapping("/classMethod")
@VersionedResource(from = 1)
public class ClassMethod1Controller {
    @GetMapping
    @VersionedResource(from = 1)
    public String getVersion() {
        return "1";
    }
}
