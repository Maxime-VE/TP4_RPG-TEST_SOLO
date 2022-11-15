package com.isep.rpg;

import java.util.Scanner;

public class Healer extends SpellCaster{
    public Healer(String n, int h, int d, boolean def) {super(n, h, d, def);}


    public void special(Combattant combattant) {
        if (mana < coutSoin){
            System.out.println(getName() + " n'a plus assez de mana, " + getName() + " médite pendant ce tour et reçoit +5 Mana");
            mana += 5;
        }else{
            System.out.println(combattant.getName() + " reçoit +" + Math.abs(heal) + "PV");
            combattant.loose(heal);
            mana -= coutSoin;
            System.out.println("Il reste " + mana + " Mana à " + getName());
        }

    }


    @Override
    public void fight(Combattant combattant) {
        System.out.println(getName() + " lance une attaque !");
        combattant.loose(getDegat());
    }

    public void sayAction() {
        System.out.println("1- Attaque \n" +
                "2- Soin : +" + (int) Math.abs(heal) + "PV (coûte "+ coutSoin + " Mana)\n" +
                "3- Protection \n" +
                "4- Objet \n" +
                "Mana actuel : " + mana);
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
    public int mana = (getDegat()+ getHealthPoint()*3);
    int heal = -20;
    public int coutSoin = 100;
}
