package org.example;

public class TreeEntity implements Comparable<TreeEntity> {
    private final Integer id;
    private final String name;
    private final Integer parentId;

    public TreeEntity(Integer id, String name, Integer parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    @Override
    public int compareTo(TreeEntity o) {
        if (this.parentId == null && o.parentId == null){
            return 0;
        }
        if (this.parentId == null){
            return -1;
        }
        if (o.parentId == null){
            return 1;
        }
        return this.parentId - o.parentId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getParentId() {
        return parentId;
    }
}
