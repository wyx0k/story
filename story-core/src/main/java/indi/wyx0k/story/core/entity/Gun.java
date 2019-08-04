package indi.wyx0k.story.core.entity;


public class Gun {
    String name;
    int range;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Override public String toString() {
        return "Gun{" +
                "name='" + name + '\'' +
                ", range=" + range +
                '}';
    }
}
