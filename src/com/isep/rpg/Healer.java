package com.isep.rpg;

import java.util.Scanner;

public class Healer extends SpellCaster{
    public Healer(String n, int h, int d, boolean def) {super(n, h, d, def);}


    int heal = -20;

    public void special(Combattant combattant) {
        System.out.println("Qui " + getName() + " va t-il soigner ?");
        Scanner choixSoin = new Scanner(System.in);
        int idSoin = choixSoin.nextInt();

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

    // Implémentation de la méthode abstraite "take" par le Healer :
    //   Le guerrier ne peut utiliser que les objets de type "Weapon"
    @Override
    public void take(Item item) {
        if (item instanceof Weapon) {
            weapon = (Weapon) item;
        } else {
            System.out.println("Oups ! " + item.getName() + " ne convient pas aux Healer !");
        }
    }

    @Override
    public void changeWeapon(Weapon item) {

    }
    private Weapon weapon;
}
