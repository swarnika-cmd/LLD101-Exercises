package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/**
 * INTENTION: Global metrics registry (should be a Singleton).
 *
 * CURRENT STATE (BROKEN ON PURPOSE):
 * - Constructor is public -> anyone can create instances.
 * - getInstance() is lazy but NOT thread-safe -> can create multiple instances.
 * - Reflection can call the constructor to create more instances.
 * - Serialization can create a new instance when deserialized.
 *
 * TODO (student):
 *  1) Make it a proper lazy, thread-safe singleton (private ctor)
 *  2) Block reflection-based multiple construction
 *  3) Preserve singleton on serialization (readResolve)
 */

/* -------------Solution---------------- */

public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // Counters map – individual methods are synchronized for thread-safe mutation.
    private final Map<String, Long> counters = new HashMap<>();

    private MetricsRegistry() {
        // Guard: if Holder already holds an instance, someone is using
        // reflection to break the singleton contract – deny it.
        if (Holder.INSTANCE != null) {
            throw new IllegalStateException(
                    "Singleton already created – reflection-based construction is not allowed.");
        }
    }

    private static final class Holder {
        static final MetricsRegistry INSTANCE = new MetricsRegistry();
    }

    /**
     * Returns the single, lazily created, thread-safe instance.
     */
    public static MetricsRegistry getInstance() {
        return Holder.INSTANCE;
    }

    // ── Serialization guard ───────────────────────────────────────────────────
   
    @Serial
    protected Object readResolve() {
        return Holder.INSTANCE;
    }

    // ── Public API ────────────────────────────────────────────────────────────

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }
}
