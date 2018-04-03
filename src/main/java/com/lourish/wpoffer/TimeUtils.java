package com.lourish.wpoffer;

public final class TimeUtils {

    private static final int MILLIS_IN_ONE_SECOND = 1000;

    private TimeUtils() {

    }

    public static long asSeconds(final long millis) {
        return millis / MILLIS_IN_ONE_SECOND;
    }
}
