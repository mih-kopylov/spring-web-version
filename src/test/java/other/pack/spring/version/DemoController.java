package other.pack.spring.version;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mihkopylov.spring.version.VersionedResource;

@RestController
public class DemoController {
    @GetMapping("/list")
    @VersionedResource(from = 1)
    public List<String> getListVersion1() {
        return List.of( "1" );
    }

    @GetMapping("/list")
    @VersionedResource(from = 3)
    public List<String> getListVersion3() {
        return List.of( "3" );
    }

    @GetMapping("/list")
    public List<String> getListWithoutVersion() {
        return List.of( "" );
    }
}
