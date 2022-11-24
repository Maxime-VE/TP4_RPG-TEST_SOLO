package com.isep.rpg;

public abstract class Ennemy extends Combattant{
    public Ennemy(String n, int h, int d, boolean def, String t) {

        super(n, h, d, def);
        type = t;
    }
    private String type;
    public String getType(){ return type;}
}
