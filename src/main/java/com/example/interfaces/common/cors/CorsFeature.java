package com.example.interfaces.common.cors;

import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;
import jakarta.ws.rs.ext.Provider;
import java.util.Set;

/**
 * Activate CorsFilter.
 */
@Provider
public class CorsFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        CorsFilter corsFilter = new CorsFilter();
        corsFilter.getAllowedOrigins().addAll(Set.of("http://localhost:4200"));
        context.register(corsFilter);
        return true;
    }
}
