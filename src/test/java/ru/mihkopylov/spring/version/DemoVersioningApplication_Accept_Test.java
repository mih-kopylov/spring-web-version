package ru.mihkopylov.spring.version;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.mvc.versioning.type=ACCEPT")
public class DemoVersioningApplication_Accept_Test {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void wihtoutVersion() throws Exception {
        final String content =
                mockMvc.perform( MockMvcRequestBuilders.get( "/list" ) ).andReturn().getResponse().getContentAsString();
        final List<String> result = objectMapper.readValue( content, new TypeReference<List<String>>() {
        } );
        assertThat( result ).isEqualTo( List.of( "" ) );
    }

    @Test
    public void version1() throws Exception {
        final String content = mockMvc.perform(
                MockMvcRequestBuilders.get( "/list" ).header( HttpHeaders.ACCEPT, "application/vnd.v1+json" ) )
                .andReturn()
                .getResponse()
                .getContentAsString();
        final List<String> result = objectMapper.readValue( content, new TypeReference<List<String>>() {
        } );
        assertThat( result ).isEqualTo( List.of( "1" ) );
    }

    @Test
    public void version2() throws Exception {
        final String content = mockMvc.perform(
                MockMvcRequestBuilders.get( "/list" ).header( HttpHeaders.ACCEPT, "application/vnd.v2+json" ) )
                .andReturn()
                .getResponse()
                .getContentAsString();
        final List<String> result = objectMapper.readValue( content, new TypeReference<List<String>>() {
        } );
        assertThat( result ).isEqualTo( List.of( "1" ) );
    }

    @Test
    public void version3() throws Exception {
        final String content = mockMvc.perform(
                MockMvcRequestBuilders.get( "/list" ).header( HttpHeaders.ACCEPT, "application/vnd.v3+json" ) )
                .andReturn()
                .getResponse()
                .getContentAsString();
        final List<String> result = objectMapper.readValue( content, new TypeReference<List<String>>() {
        } );
        assertThat( result ).isEqualTo( List.of( "3" ) );
    }
}