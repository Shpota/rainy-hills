package com.shpota.rainyhills.service;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigInteger;
import java.util.List;

import static java.math.BigInteger.ZERO;

/**
 * Solving Service for Rainy Hills problem.
 */
@ApplicationScoped
public class SolvingService {

    /**
     * Calculates water volume in Rainy Hills problem. The
     * algorithm performs calculation in three rounds:
     * <ul>
     *   <li>calculates highest left value for every item
     *   <li>calculates highest right value for every item
     *   <li>calculates and accumulates the difference for
     *   every item in the hills list
     * </ul>
     *
     * @param hills list of numbers representing hills before rain
     * @return positive integer number or 0 representing water
     * volume after rain
     */
    public BigInteger calculateWaterVolume(List<BigInteger> hills) {
        if (hills.size() < 3) {
            return ZERO;
        }
        BigInteger[] values = hills.toArray(new BigInteger[0]);
        BigInteger[] leftTops = calculateLeftTops(values);
        BigInteger[] rightTops = calculateRightTops(values);
        return accumulateDifference(values, leftTops, rightTops);
    }

    /**
     * Calculates highest left values for every item.
     *
     * @param values array of numbers representing hills before rain
     * @return array of numbers representing highest left values for
     * every item
     */
    private BigInteger[] calculateLeftTops(BigInteger[] values) {
        BigInteger highestLeft = values[0];
        BigInteger[] leftTops = new BigInteger[values.length];
        for (int index = 0; index < values.length; index++) {
            BigInteger currentValue = values[index];
            if (currentValue.compareTo(highestLeft) > 0) {
                highestLeft = currentValue;
            }
            leftTops[index] = highestLeft;
        }
        return leftTops;
    }

    /**
     * Calculates highest right values for every item.
     *
     * @param values array of numbers representing hills before rain
     * @return array of numbers representing highest right values for
     * every item
     */
    private BigInteger[] calculateRightTops(BigInteger[] values) {
        BigInteger highestRight = values[values.length - 1];
        BigInteger[] rightTops = new BigInteger[values.length];
        for (int index = values.length - 1; index >= 0; index--) {
            BigInteger currentValue = values[index];
            if (currentValue.compareTo(highestRight) > 0) {
                highestRight = currentValue;
            }
            rightTops[index] = highestRight;
        }
        return rightTops;
    }

    /**
     * Accumulates difference for every item which value is less then
     * minimum value from highest left and highest right values. The
     * difference is calculated as minimum value from highest left and
     * highest right values minus item's value. Calculated differences
     * are accumulated and returned as the result.
     *
     * @param values hills values before rain
     * @param leftTops highest left values for every item
     * @param rightTops highest right values for every item
     * @return positive integer number or 0 representing water volume
     * after rain
     */
    private BigInteger accumulateDifference(BigInteger[] values, BigInteger[] leftTops, BigInteger[] rightTops) {
        BigInteger accumulator = ZERO;
        for (int index = 0; index < values.length; index++) {
            BigInteger leftTop = leftTops[index];
            BigInteger rightTop = rightTops[index];
            BigInteger minTop = leftTop.min(rightTop);
            BigInteger currentValue = values[index];
            if (currentValue.compareTo(minTop) < 0) {
                BigInteger difference = minTop.subtract(currentValue);
                accumulator = accumulator.add(difference);
            }
        }
        return accumulator;
    }
}
