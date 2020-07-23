package br.com.vfs.api.cdc;

import br.com.vfs.api.cdc.testcontainer.TestContainerMysqlTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ApplicationTests extends TestContainerMysqlTest {

	@Test
	void contextLoads() {
		assertTrue(true, "Up context load success");
	}
}
