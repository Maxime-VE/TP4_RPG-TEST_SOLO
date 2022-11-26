package com.isep.rpg;

public abstract class Combattant {

    public Combattant(String n, int h, int d, boolean def, int r) {
        name = n;
        healthPoint = h;
        degat = d;
        isProtected = def;
        resistance = r ;


    }


    public int getResistance() {return resistance;}
    public void addResistance(int r) { resistance += r;   }
    public String getName() {
        return name;
    }
    public int getHealthPoint() {
        return healthPoint;
    }
    public int getDegat() {
        return degat;
    }
    public boolean getProtection() {return isProtected;}

    public abstract void fight(Combattant combattant);
    public void loose(int hp) {
        healthPoint -= hp;
    }
    public abstract void sayAction();
    public abstract void special(Combattant combattant);
    public abstract void protection();



    private String name;
    private int healthPoint;
    public int degat;
    private int resistance;
    public boolean isProtected;
}
