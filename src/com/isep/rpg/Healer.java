package com.isep.rpg;

public class Healer extends SpellCaster{
    public Healer(String n, int h, int d, boolean def) {super(n, h, d, def);}


    int heal = -20;

    public void special(Combattant combattant) {
        System.out.println(getName() + " soigne " + combattant);
        combattant.loose(heal);
    }
    @Override
    public void fight(Combattant combattant) {
        System.out.println(getName() + " lance une attaque !");
        combattant.loose(getDegat());
    }

    public void sayAction() {
        System.out.println("1- Attaque \n" +
                "2- Soin \n" +
                "3- Protection \n" +
                "4- Objet");
    }

    public void protection() {
        System.out.println(getName() + " se protège !");
        isProtected = true;
    }
}
