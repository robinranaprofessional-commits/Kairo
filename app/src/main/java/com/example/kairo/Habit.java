package com.example.kairo;

// class for one habit row
public class Habit {

    private final String name;
    private final boolean placeholder;

    public Habit(String name, boolean placeholder) {
        this.name = name;
        this.placeholder = placeholder;
    }

    public String getName() {
        return name;
    }

    public boolean isPlaceholder() {
        return placeholder;
    }
}