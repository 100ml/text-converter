package anno;

public @interface Header {
    /**
     * Marks if headers enabled.
     * @return
     */
    boolean enabled() default false;

    Strategy strategy() default Strategy.ORDER;
}
