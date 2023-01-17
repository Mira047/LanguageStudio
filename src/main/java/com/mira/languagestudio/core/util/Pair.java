package com.mira.languagestudio.core.util;

public class Pair<L, R> {
    private L left;
    private R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public Pair() {
        this(null, null);
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public static <L, R> Pair<L, R> of(L left, R right) {
        return new Pair<>(left, right);
    }

    public void setLeft(L left) {
        this.left = left;
    }

    public void setRight(R right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair<?, ?> p)) {
            return false;
        }
        return this.left.equals(p.getLeft()) &&
                this.right.equals(p.getRight());
    }

    @Override
    public int hashCode() {
        return left.hashCode() ^ right.hashCode();
    }

    @Override
    public String toString() {
        return "(" + left + ", " + right + ")";
    }
}