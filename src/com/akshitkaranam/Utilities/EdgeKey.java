package com.akshitkaranam.Utilities;

public class EdgeKey {

    private final int key1;
    private final int key2;

    public EdgeKey(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeKey edgeKey = (EdgeKey) o;
        return key1 == edgeKey.key1 && key2 == edgeKey.key2 || key1 == edgeKey.key2 && key2 == edgeKey.key1;
    }

    @Override
    public int hashCode() {
        return (key1) * key2;
    }

    public int getKey1() {
        return key1;
    }

    public int getKey2() {
        return key2;
    }

    @Override
    public String toString() {
        return "(" +
                key1 + "," + key2 +
                ')';
    }
}
