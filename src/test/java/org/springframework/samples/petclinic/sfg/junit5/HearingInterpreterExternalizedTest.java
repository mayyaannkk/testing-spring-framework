package org.springframework.samples.petclinic.sfg.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.samples.petclinic.sfg.HearingInterpreter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("classpath:yanny.properties")
@SpringJUnitConfig(classes = HearingInterpreterExternalizedTest.TestConfig.class)
@ActiveProfiles("externalized")
class HearingInterpreterExternalizedTest {

    @ComponentScan("org.springframework.samples.petclinic.sfg")
    @Configuration
    static class TestConfig {
    }

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void getWord() {
        String name = hearingInterpreter.whatIHeard();

        assertEquals("YaNNy", name);
    }
}