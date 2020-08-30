package other.pack.spring.version;

import java.util.stream.Stream;
import lombok.NonNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.Nullable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;
import ru.mihkopylov.spring.version.exception.UnsupportedVersionExcepion;

import static java.util.Objects.isNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.mvc.versioning.type=PATH")
@DirtiesContext
public class Versioning_Test {
    @Autowired
    private MockMvc mockMvc;

    @NonNull
    private static Stream<Arguments> versions() {
        //@formatter:off
        return Stream.of(
                Arguments.of( null, "" ),
                Arguments.of( "v1", "1" ),
                Arguments.of( "v2", "1" ),
                Arguments.of( "v3", "3" ),
                Arguments.of( "v4", "3" ),
                Arguments.of( "v5", "3" )
        );
        //@formatter:on
    }

    @ParameterizedTest
    @MethodSource("versions")
    public void methodVersions( @Nullable String version, @NonNull String expectedResult ) throws Exception {
        String path = (isNull( version ) ? "" : "/" + version) + "/method";
        final String result =
                mockMvc.perform( MockMvcRequestBuilders.get( path ) ).andReturn().getResponse().getContentAsString();
        assertThat( result ).isEqualTo( expectedResult );
    }

    @ParameterizedTest
    @MethodSource("versions")
    public void classVersions( @Nullable String version, @NonNull String expectedResult ) throws Exception {
        String path = (isNull( version ) ? "" : "/" + version) + "/class";
        final String result =
                mockMvc.perform( MockMvcRequestBuilders.get( path ) ).andReturn().getResponse().getContentAsString();
        assertThat( result ).isEqualTo( expectedResult );
    }

    @ParameterizedTest
    @MethodSource("versions")
    public void classMethodVersions( @Nullable String version, @NonNull String expectedResult ) throws Exception {
        String path = (isNull( version ) ? "" : "/" + version) + "/classMethod";
        final String result =
                mockMvc.perform( MockMvcRequestBuilders.get( path ) ).andReturn().getResponse().getContentAsString();
        assertThat( result ).isEqualTo( expectedResult );
    }

    @Test
    public void requestVersionLessThanApiMinVersion() {
        assertThatExceptionOfType( NestedServletException.class ).isThrownBy(
                () -> mockMvc.perform( MockMvcRequestBuilders.get( "/v0/method" ) ) )
                .withCause( new UnsupportedVersionExcepion( 1, 0 ) );
    }

    @Test
    public void negativeRequestVersion() {
        assertThatExceptionOfType( NestedServletException.class ).isThrownBy(
                () -> mockMvc.perform( MockMvcRequestBuilders.get( "/v-1/method" ) ) )
                .withCause( new UnsupportedVersionExcepion( 1, -1 ) );
    }

    @Test
    public void moreThanMaxIntRequestVersion() {
        assertThatExceptionOfType( NestedServletException.class ).isThrownBy(
                () -> mockMvc.perform( MockMvcRequestBuilders.get( "/v2147483648/method" ) ) )
                .withCause( new NumberFormatException( "For input string: \"2147483648\"" ) );
    }
}