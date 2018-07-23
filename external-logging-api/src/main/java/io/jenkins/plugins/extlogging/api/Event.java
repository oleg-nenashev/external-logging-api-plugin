package io.jenkins.plugins.extlogging.api;

import javax.annotation.CheckForNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Oleg Nenashev
 * @since TODO
 */
public class Event {

    final String message;
    final long timestamp;
    final long id;

    Map<String, Object> data = new HashMap<>();

    public Event(long id, String message, long timestamp) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public String toString() {
        return String.format("[%d] - %s", timestamp, message);
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String toStringWithData() {
        return String.format("[%d] - %s: %s", timestamp, message, data);
    }
}
