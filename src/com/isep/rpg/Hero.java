package com.isep.rpg;

public abstract class Hero extends Combattant{
    private String name;
    private int healthPoint;

    public Hero(String n, int h, int d, boolean def) {
        super(n, h, d, def);
    }

    // Abstrait car n'importe quel hero peut prendre un objet mais son
    // utilisation dépend du type du héro (Epée -> Warrior  Baton -> Mage)
    public abstract void take(Item item);

    public abstract void changeWeapon(Item item);


}
