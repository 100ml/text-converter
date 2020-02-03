package util;

import javax.annotation.Nonnull;

public class StringUtil {

    @Nonnull
    public static String capitalize(@Nonnull String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
