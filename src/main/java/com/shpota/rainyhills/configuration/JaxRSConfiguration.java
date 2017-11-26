package com.shpota.rainyhills.configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures JAX-RS to handle requests prefixed with "/api".
 */
@ApplicationPath("/api")
public class JaxRSConfiguration extends Application {
}
