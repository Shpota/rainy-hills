package com.shpota.rainyhills.service;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static java.math.BigInteger.ZERO;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class SolvingServiceTest {

    private SolvingService service;

    @Before
    public void setUp() {
        service = new SolvingService();
    }

    @Test
    public void shouldCalculateWaterVolumeGivenAscendingTops() {
        List<BigInteger> hills = list(1, 3, 2, 5, 4, 7, 6);

        BigInteger volume = service.calculateWaterVolume(hills);

        assertThat(volume, is(2));
    }

    @Test
    public void shouldCalculateWaterVolumeGivenDescendingTops() {
        List<BigInteger> hills = list(6, 7, 4, 5, 2, 3, 1);

        BigInteger volume = service.calculateWaterVolume(hills);

        assertThat(volume, is(2));
    }

    @Test
    public void shouldCalculateWaterVolumeGivenMixedTops() {
        List<BigInteger> hills = list(4, 3, 1, 2, 1, 8, 6, 2, 3);

        BigInteger volume = service.calculateWaterVolume(hills);

        assertThat(volume, is(10));
    }

    @Test
    public void shouldCalculateWaterVolumeGivenEmptyHills() {
        List<BigInteger> hills = emptyList();

        BigInteger volume = service.calculateWaterVolume(hills);

        assertThat(volume, is(0));
    }

    @Test
    public void shouldCalculateWaterVolumeGivenOneHill() {
        List<BigInteger> hills = singletonList(ZERO);

        BigInteger volume = service.calculateWaterVolume(hills);

        assertThat(volume, is(0));
    }

    @Test
    public void shouldCalculateWaterVolumeGivenTwoHills() {
        List<BigInteger> hills = list(1, 2);

        BigInteger volume = service.calculateWaterVolume(hills);

        assertThat(volume, is(0));
    }

    private List<BigInteger> list(int... input) {
        return Arrays.stream(input)
                .boxed()
                .map(BigInteger::valueOf)
                .collect(toList());
    }

    private Matcher<BigInteger> is(int value) {
        return equalTo(BigInteger.valueOf(value));
    }
}
