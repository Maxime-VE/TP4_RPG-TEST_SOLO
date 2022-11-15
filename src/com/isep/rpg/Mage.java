package com.isep.rpg;

public class Mage extends SpellCaster{

    public Mage(String n, int h, int d, boolean def) {super(n, h, d, def);}

    public void special(Combattant combattant) {
        if (mana < coutSort){
            System.out.println(getName() + " n'a plus assez de mana, " + getName() + " médite pendant ce tour et reçoit +5 Mana");
            mana += 5;
        }else{
            System.out.println(getName() + " lance un sort !");
            combattant.loose(getDegat()*2);
            mana -= coutSort;
            System.out.println("Il reste " + mana + " Mana à " + getName());
        }


    }

    @Override
    public void fight(Combattant combattant) {
        System.out.println(getName() + " lance une attaque !"); //@TODO Indiquer les dégat totaux reçus (getDegat + arme)
        combattant.loose(getDegat());
    }
    public void sayAction() {
        System.out.println("1- Attaque \n" +
                "2- Sortilège (coûte " + coutSort +  " Mana) \n" +
                "3- Protection \n" +
                "4- Objet\n" +
                "Mana actuel : " + mana);
    }


    public void protection() {
        System.out.println(getName() + " se protège !");
        isProtected = true;
    }

    // Implémentation de la méthode abstraite "take" par le Mage :
    //   Le guerrier ne peut utiliser que les objets de type "Weapon"
    @Override
    public void take(Item item) {
        if (item instanceof Weapon) {
            weapon = (Weapon) item;
        } else {
            System.out.println("Oups ! " + item.getName() + " ne convient pas aux Mage !");
        }
    }

    @Override
    public void changeWeapon(Weapon item) {

    }

    private Weapon weapon;
    int coutSort = 100;
    int mana = (getDegat()+ getHealthPoint()*3);
}
