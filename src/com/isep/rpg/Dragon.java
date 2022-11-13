package com.isep.rpg;


import static java.lang.StrictMath.abs;

public class Dragon extends Ennemy{
    public Dragon(String n, int h, int d, boolean def) {
        super(n, h, d, def);
    }

    @Override
    public void fight(Combattant combattant) {
        System.out.println(getName() + " lance une attaque !");
        if (combattant.getProtection()) {
            System.out.println(combattant.getName() + " est protégé !");
            int attack = (int) getDegat()/2;
            combattant.loose(attack);
            System.out.println("Il inflige " + attack + " points de dégât");
        }else {
            combattant.loose(getDegat());
            System.out.println("Il inflige " + getDegat() + " points de dégât");
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
