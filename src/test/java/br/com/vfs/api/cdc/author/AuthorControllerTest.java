package br.com.vfs.api.cdc.author;

import br.com.vfs.api.cdc.testcontainer.TestContainerMysqlTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class AuthorControllerTest extends TestContainerMysqlTest {

    private static final String URL = "/api/author";
    @Autowired private MockMvc mvc;
    @Test
    void testOne() throws Exception {
        final var newAuthor = new NewAuthor();
        newAuthor.setEmail("test@mock.com");
        newAuthor.setName("Test");
        newAuthor.setDescription("Author Test");
        final var builder = post(URL)
                        .content("{ \"email\":\"test@mock.com\", \"name\":\"Test\", \"description\":\"Author Test\" }")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON);
        final var resultCreated = mvc.perform(builder).andExpect(status().isOk()).andReturn();
        assertEquals("Success create author",
                resultCreated.getResponse().getContentAsString(),
                "Invalid message return");
    }
}
