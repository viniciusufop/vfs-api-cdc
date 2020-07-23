package br.com.vfs.api.cdc.mvc;

import br.com.vfs.api.cdc.shared.errors.ErrorMessage;
import br.com.vfs.api.cdc.testcontainer.TestContainerMysqlTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class ControllerTest extends TestContainerMysqlTest {

    private static final String URL_AUTHOR = "/api/author";
    private static final String URL_CATEGORY = "/api/category";

    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper objectMapper;

    @Test
    @Order(1)
    @DisplayName("create a new author")
    void testAuthorOne() throws Exception {
        final var builder = post(URL_AUTHOR)
                        .content("{ \"email\":\"test@mock.com\", \"name\":\"Test\", \"description\":\"Author Test\" }")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON);
        final var resultCreated = mvc.perform(builder).andExpect(status().isOk()).andReturn();
        assertEquals("Success create author",
                resultCreated.getResponse().getContentAsString(),
                "Invalid message return");
    }

    @Test
    @Order(2)
    @DisplayName("not create a new author with duplicate email")
    void testAuthorTwo() throws Exception {
        final var builder = post(URL_AUTHOR)
                .content("{ \"email\":\"test@mock.com\", \"name\":\"Test\", \"description\":\"Author Test\" }")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
        final var resultFail = mvc.perform(builder).andExpect(status().isBadRequest()).andReturn();
        final var errorMessage = objectMapper.readValue(resultFail.getResponse().getContentAsString(), ErrorMessage.class);
        assertFalse(errorMessage.getErrors().isEmpty(), "Return fail");
    }

    @Test
    @Order(3)
    @DisplayName("create a new category")
    void testCategoryOne() throws Exception {
        final var builder = post(URL_CATEGORY)
                .content("{ \"name\":\"Computing\" }")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
        final var resultCreated = mvc.perform(builder).andExpect(status().isOk()).andReturn();
        assertEquals("Success create category",
                resultCreated.getResponse().getContentAsString(),
                "Invalid message return");
    }

    @Test
    @Order(4)
    @DisplayName("not create a new category with duplicate name")
    void testCategoryTwo() throws Exception {
        final var builder = post(URL_CATEGORY)
                .content("{ \"name\":\"Computing\" }")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
        final var resultFail = mvc.perform(builder).andExpect(status().isBadRequest()).andReturn();
        final var errorMessage = objectMapper.readValue(resultFail.getResponse().getContentAsString(), ErrorMessage.class);
        assertFalse(errorMessage.getErrors().isEmpty(), "Return fail");
    }
}
