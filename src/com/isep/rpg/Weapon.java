package com.isep.rpg;

public class Weapon extends Item {

    public Weapon(String name, String description, int damagePoints) {
        super(name, description);
        this.damagePoints = damagePoints;
    }

    public int getDamagePoints() {
        return damagePoints;
    }

    // Une arme inflige des points de dégats
    private int damagePoints;
}