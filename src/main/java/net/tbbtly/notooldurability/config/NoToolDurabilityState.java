package net.tbbtly.notooldurability.config;

import java.util.concurrent.atomic.AtomicBoolean;

public final class NoToolDurabilityState {

    private NoToolDurabilityState() {}

    /** {@code true} = items do NOT lose durability. */
    private static final AtomicBoolean SUPPRESSED = new AtomicBoolean(true);

    /** Returns {@code true} when durability loss is currently suppressed. */
    public static boolean isEnabled() {
        return SUPPRESSED.get();
    }

    /** Enable durability suppression (items become unbreakable). */
    public static void enable() {
        SUPPRESSED.set(true);
    }

    /** Disable durability suppression (restore vanilla behaviour). */
    public static void disable() {
        SUPPRESSED.set(false);
    }

    /** Toggle and return the new state. */
    public static boolean toggle() {
        return !(SUPPRESSED.get());
    }
}
