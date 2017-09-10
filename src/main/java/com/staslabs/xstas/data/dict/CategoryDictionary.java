package com.staslabs.xstas.data.dict;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CategoryDictionary {

    private String[] categories = new String[] {null, "development", "business", "productivity", "design"};

    public String getName(int id) {
        return categories[id];
    }

    public int getId(String name) {
        for (int i = 0; i < categories.length; i++) {
            if (Objects.equals(categories[i], name)) {
                return i;
            }
        }
        throw new IllegalArgumentException("No category exists with name '" + name + "'");
    }
}
