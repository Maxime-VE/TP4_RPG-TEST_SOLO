package com.isep.rpg;

public class Mage extends SpellCaster{

    public Mage(String n, int h, int d, boolean def) {super(n, h, d, def);}

    public void special(Combattant combattant) {
        System.out.println(getName() + " lance un sort !");
        combattant.loose(getDegat()*2);
    }

    @Override
    public void fight(Combattant combattant) {
        System.out.println(getName() + " lance une attaque !");
        combattant.loose(getDegat());
    }
    public void sayAction() {
        System.out.println("1- Attaque \n" +
                "2- Sortilège \n" +
                "3- Protection \n" +
                "4- Objet");
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

    private Weapon weapon;
}
