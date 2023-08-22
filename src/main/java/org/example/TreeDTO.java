package org.example;

import java.util.ArrayList;
import java.util.List;

public class TreeDTO {
    private final Integer id;
    private final String name;
    private final List<TreeDTO> children = new ArrayList<>();

    public TreeDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TreeDTO> getChildren() {
        return children;
    }
}
