package csc402.week4;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PositionPlayer implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Map<String, String> attributes;

    public PositionPlayer() {
        this.attributes = new HashMap<>();
    }

    public void setAttribute(String key, String value) {
        attributes.put(key, value);
    }

    public String getAttribute(String key) {
        return attributes.get(key);
    }

    @Override
    public String toString() {
        return "PositionPlayer " + attributes;
    }
}