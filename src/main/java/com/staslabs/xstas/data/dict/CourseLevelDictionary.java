package com.staslabs.xstas.data.dict;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CourseLevelDictionary {

    private String[] levels = new String[] {null, "Beginner", "Intermediate", "Expert"};

    public String getName(int id) {
        return levels[id];
    }

    public int getId(String name) {
        for (int i = 0; i < levels.length; i++) {
            if (Objects.equals(levels[i], name)) {
                return i;
            }
        }
        throw new IllegalArgumentException("No level exists with name '" + name + "'");
    }
}
