/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.ArrayUtils
 */
package xyz.xianyu.util;
import java.util.UUID;
import org.apache.commons.lang3.ArrayUtils;

public class NameHistory {
    private UUID uuid;
    private UUIDFetcher[] changes;

    public NameHistory(UUID uuid, UUIDFetcher[] changes) {
        this.uuid = uuid;
        this.changes = changes;
        ArrayUtils.reverse((Object[])this.changes);
    }

    public UUIDFetcher[] getChanges() {
        return this.changes;
    }

    public UUID getUUID() {
        return this.uuid;
    }
}

