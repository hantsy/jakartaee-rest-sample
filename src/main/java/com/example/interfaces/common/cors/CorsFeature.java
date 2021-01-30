package com.example.interfaces.common.cors;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
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
