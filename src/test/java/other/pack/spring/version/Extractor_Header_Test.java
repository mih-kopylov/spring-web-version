package other.pack.spring.version;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.mvc.versioning.type=HEADER")
@DirtiesContext
public class Extractor_Header_Test {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void version() throws Exception {
        final String result = mockMvc.perform( get( "/method" ).header( "version", "2" ) )
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat( result ).isEqualTo( "1" );
    }
}