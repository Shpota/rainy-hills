package com.shpota.rainyhills.rest;

import com.shpota.rainyhills.service.SolvingService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigInteger;
import java.util.List;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SolvingEndpointTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void shouldGetWaterVolume() {
        SolvingService service = mock(SolvingService.class);
        List<BigInteger> volumes = asList(ONE, ZERO);
        given(service.calculateWaterVolume(volumes)).willReturn(BigInteger.valueOf(12));
        SolvingEndpoint endpoint = new SolvingEndpoint(service);

        String response = endpoint.getWaterVolume(volumes);

        assertThat(response, is("12"));
        verify(service).calculateWaterVolume(volumes);
    }

    @Test
    public void shouldFailToConstructGivenNullService() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("service must not be null");
        SolvingService service = null;

        new SolvingEndpoint(service);
    }
}
