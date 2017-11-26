package com.shpota.rainyhills.rest;

import com.shpota.rainyhills.service.SolvingService;
import org.jboss.shrinkwrap.impl.base.Validate;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import java.math.BigInteger;
import java.util.List;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 * REST endpoint for Rainy Hills problem calculation.
 * Serves requests to "/calculate-water-volume".
 */
@Singleton
@Path("/calculate-water-volume")
public class SolvingEndpoint {

    private final SolvingService service;

    /**
     * Construct {@link SolvingEndpoint} using {@link SolvingService}
     * instance.
     *
     * @param service {@link SolvingService} instance, must not be null.
     */
    @Inject
    public SolvingEndpoint(@Context SolvingService service) {
        Validate.notNull(service, "service must not be null");
        this.service = service;
    }

    /**
     * Serves GET requests for water volume calculation.
     *
     * @param hills list of numbers representing hills before rain
     * @return string value of a number representing water volume
     * after rain
     */
    @GET
    @Produces(TEXT_PLAIN)
    public String getWaterVolume(@QueryParam("hills") List<BigInteger> hills) {
        BigInteger volume = service.calculateWaterVolume(hills);
        return volume.toString();
    }
}
