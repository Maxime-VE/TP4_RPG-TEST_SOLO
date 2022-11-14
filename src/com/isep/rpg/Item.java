package com.isep.rpg;

public abstract class Item {

    public Item(String name, String etat) {
        this.name = name;
        description = etat;

    }

    public String getName() {
        return name;
    }

    private String name;
    private String description;
}