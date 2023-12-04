package br.com.lrfs.integrationtests.testcontainers;

import java.util.stream.Stream;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

import java.util.*;

@ContextConfiguration(initializers = { AbstractIntegrationTest.Initializer.class })
public class AbstractIntegrationTest {

    public class Initializer implements ApplicationContextInitializer<org.springframework.context.ConfigurableApplicationContext> {
        static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.32")
            ;

        private static void startContainers() {
            Startables.deepStart(Stream.of(mysql)).join();
        }

        @SuppressWarnings({"unchecked","rawtypes"})
        @Override
        public void initialize(org.springframework.context.ConfigurableApplicationContext configurableApplicationContext) {
            startContainers();
            
            ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
            MapPropertySource testcontainers = new MapPropertySource("testcontainers",(Map)createConnectionConfiguration());

            environment.getPropertySources().addFirst(testcontainers);
        }

        private static Map<String,String> createConnectionConfiguration() {
            return Map.of(
                "spring.datasource.url", mysql.getJdbcUrl(),
                "spring.datasource.username", mysql.getUsername(),
                "spring.datasource.password", mysql.getPassword()
            );
        }
    }
    
}
