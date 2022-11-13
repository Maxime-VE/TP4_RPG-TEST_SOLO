package com.isep.rpg;

public abstract class Hero extends Combattant{
    private String name;
    private int healthPoint;

    public Hero(String n, int h, int d, boolean def) {
        super(n, h, d, def);
    }


}
