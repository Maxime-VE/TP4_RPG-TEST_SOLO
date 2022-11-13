package com.isep.rpg;
import java.util.concurrent.TimeUnit;

public class Hunter extends Hero{

    public Hunter(String n, int h, int d, boolean def) {
        super(n, h, d, def);
    }

    int compteur_fleche = 1;

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
}
