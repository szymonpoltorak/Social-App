package razepl.dev.socialappbackend.exceptions.validators;

import razepl.dev.socialappbackend.exceptions.NegativeIdException;
import razepl.dev.socialappbackend.exceptions.NullArgumentException;

import java.util.Arrays;
import java.util.Objects;

public class ArgumentValidator {
    private ArgumentValidator() {
    }

    public static void throwIfNegativeId(long... ids) {
        if (!ArgumentValidator.areIdPositive(ids)) {
            throw new NegativeIdException("There are negative id's!");
        }
    }

    public static void throwIfNegativeId(int... ids) {
        if (!ArgumentValidator.areIdPositive(ids)) {
            throw new NegativeIdException("There are negative id's!");
        }
    }

    public static void throwIfNull(Object... objects) {
        if (!ArgumentValidator.areArgumentsNullSafe(objects)) {
            throw new NullArgumentException("Encountered null arguments in method!");
        }
    }

    private static boolean areIdPositive(long... ids) {
        return Arrays.stream(ids).noneMatch(id -> id < 0L);
    }

    private static boolean areIdPositive(int... ids) {
        return Arrays.stream(ids).noneMatch(id -> id < 0);
    }

    private static boolean areArgumentsNullSafe(Object... objects) {
        return Arrays.stream(objects).noneMatch(Objects::isNull);
    }
}
