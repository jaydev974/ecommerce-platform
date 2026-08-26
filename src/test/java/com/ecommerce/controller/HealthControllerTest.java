package com.ecommerce.controller;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HealthControllerTest {

    private final HealthController controller = new HealthController();

    @Test
    void healthReportsApplicationAsUp() {
        Map<String, Object> body = controller.health().getBody();

        assertNotNull(body);
        assertEquals("UP", body.get("status"));
        assertNotNull(body.get("timestamp"));
    }
}
