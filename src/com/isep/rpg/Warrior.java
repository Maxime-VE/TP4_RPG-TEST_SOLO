package com.isep.rpg;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Warrior extends Hero{

    public Warrior(String n, int h, int d, boolean def) {
        super(n, h, d, def);
    }

    @Override
    public void fight(Combattant combattant) {
        System.out.println(getName() + " lance une attaque !");
        combattant.loose(degatTotal);
    }
    public void sayAction() {
        System.out.println("1- Attaque \n" +
                "2- Attaque Spéciale (Rage) \n" +
                "3- Protection \n" +
                "4- Objet");
    }

    public void protection() {
        System.out.println(getName() + " se protège !");
        isProtected = true;
    }

    public void special(Combattant combattant) {
        System.out.println(getName() + " lance une attaque spéciale !");
        combattant.loose(degatTotal*2);
    }

    // Implémentation de la méthode abstraite "take" par le Warrior :
    //   Le guerrier ne peut utiliser que les objets de type "Weapon"
    @Override
    public void take(Item item) {
        if (item instanceof Weapon) {
            weapon = (Weapon) item;
            System.out.println(getName() + " se voit confier l'arme " + item.getName() + " (+" + ((Weapon) item).getDamagePoints() + " dégats)");
            degatTotal = getDegat() + ((Weapon) item).getDamagePoints();
            currentWeaponList.add(weapon);
        } else {
            System.out.println("Oups ! " + item.getName() + " ne convient pas aux Warrior !");
        }
    }

    @Override
    public void changeWeapon(Weapon item) {
        System.out.println(getName() + " récupère " + item.getName()+ " (+" + ((Weapon) item).getDamagePoints() + " dégats)");
        if (currentWeaponList.size() == 0) {
            take(item);
        } else {
            Weapon currentWeapon = currentWeaponList.get(0);
            System.out.println("Mais " + getName() + " possède déjà " + currentWeapon.getName() + " (+" + currentWeapon.getDamagePoints() + " dégats)" );
            System.out.println("Souhaitez-vous changer l'équipement de " + getName() + " ? [y/n]");
            Scanner scanChoixWeapon = new Scanner(System.in);
            String choixWeapon = scanChoixWeapon.nextLine();
            if (Objects.equals(choixWeapon, "y")) {
                take(item);
            } else if (Objects.equals(choixWeapon, "n")) {
                System.out.println(getName() + " laisse " + item.getName());
            }else{
                changeWeapon(item);
            }
        }
    }
    ArrayList<Weapon> currentWeaponList = new ArrayList<>();
    public int degatTotal = degat;
    private Weapon weapon;

}
