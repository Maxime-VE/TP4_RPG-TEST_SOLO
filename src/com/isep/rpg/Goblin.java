package com.isep.rpg;

public class Goblin extends Ennemy{
    public Goblin(String n, int h, int d, boolean def, int r, String t) {

        super(n, h, d, def, r, t);
    }

    @Override
    public void fight(Combattant combattant) {
        System.out.println(getName() + " lance une attaque !");
        int degatInfliges = (getDegat()-combattant.getResistance());
        if (combattant.getProtection()) {
            System.out.println(combattant.getName() + " est protégé !");
            int attack = (int) degatInfliges / 2;
            combattant.loose(attack);
            System.out.println("Il inflige " + attack + " points de dégât");
        }else {
            combattant.loose(getDegat());
            System.out.println("Il inflige " + degatInfliges + " points de dégât");
        }
    }
    public void sayAction() {
        System.out.print("");
    }
    public void special(Combattant combattant) {
        System.out.print("");
    }

    @Override
    public void protection() {

    }

}