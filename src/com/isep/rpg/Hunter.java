package com.isep.rpg;


import java.util.ArrayList;

public class Hunter extends Hero{

    public Hunter(String n, int h, int d, boolean def) {
        super(n, h, d, def);
    }


    public void special(Combattant combattant) {
        if (compteur_fleche != 0) {
            System.out.println(getName() + " tire une flèche !");
            combattant.loose(getDegat()*2);
            compteur_fleche -= 1;
            System.out.println("Il reste " + compteur_fleche + " flèches à " + getName());
        }
        else {
            System.out.println(getName() + " n'a plus de flèche... \n" +
                    getName() + "perd trop de temps et passe son tour !");

        }
    }

    public void rechargeFleche(int recharge) {

        compteur_fleche += recharge;
        System.out.println(getName() + " reçoit " + recharge + " flèches ! ");
    }

    @Override
    public void fight(Combattant combattant) {
        System.out.println(getName() + " lance une attaque !");
        combattant.loose(getDegat());
    }
    public void sayAction() {
        System.out.println("1- Attaque \n" +
                "2- Tir à l'arc (Consomme 1 flèche) \n" +
                "3- Protection \n" +
                "4- Objet");
    }

    public void protection() {
        System.out.println(getName() + " se protège !");
        isProtected = true;
    }

    // Implémentation de la méthode abstraite "take" par le Hunter :
    //   Le guerrier ne peut utiliser que les objets de type "Weapon"
    @Override
    public void take(Item item) {
        if (item instanceof Weapon) {
            weapon = (Weapon) item;
        } else {
            System.out.println("Oups ! " + item.getName() + " ne convient pas aux Hunter !");
        }
    }
    @Override
    public void changeWeapon(Item item) {

    }

    private Weapon weapon;
    int compteur_fleche = 1;
}
