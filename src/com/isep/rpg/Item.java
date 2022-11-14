package com.isep.rpg;

public abstract class Item {

    public Item(String name, String etat) {
        this.name = name;
        description = etat;

    }

    public String getName() {return name;}
    public String getDescription() {return description;}


    private String name;
    private String description;
}