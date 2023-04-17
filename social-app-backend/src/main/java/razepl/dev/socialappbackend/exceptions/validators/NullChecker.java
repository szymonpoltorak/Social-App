package razepl.dev.socialappbackend.exceptions.validators;

import razepl.dev.socialappbackend.exceptions.NullArgumentException;

import java.util.Arrays;
import java.util.Objects;

public class NullChecker {
    private NullChecker() {
    }

    public static void throwAppropriateException(Object... objects) {
        if (!NullChecker.areArgumentsNullSafe(objects)) {
            throw new NullArgumentException("Encountered null arguments in method!");
        }
    }

    private static boolean areArgumentsNullSafe(Object... objects) {
        return Arrays.stream(objects).noneMatch(Objects::isNull);
    }
}
