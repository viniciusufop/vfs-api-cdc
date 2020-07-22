package br.com.vfs.api.cdc.testcontainer;

import br.com.vfs.api.cdc.Application;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;

@ContextConfiguration(initializers = TestContainerMysqlTest.Initializer.class,
        classes = Application.class)
public abstract class TestContainerMysqlTest {

    @ClassRule
    protected final static MySQLContainer MYSQL = new MySQLContainer();

    public static class Initializer implements
            ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    "spring.datasource.url=" + MYSQL.getJdbcUrl(),
                    "spring.datasource.username="+ MYSQL.getUsername(),
                    "spring.datasource.password="+ MYSQL.getPassword());
            values.applyTo(configurableApplicationContext);
        }
    }

    @BeforeAll
    static void setup() {
        MYSQL.start();
    }

    @AfterAll
    static void tearDown() {
        MYSQL.stop();
    }
}