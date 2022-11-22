package com.isep.rpg;

public class Potion extends Consumable {
    public Potion(String name, String etat) {
        super(name, etat);
    }


    public int compteurMaxiPotion = 1;
    public int compteurPotion = 2;
    public int compteurMiniPotion = 1;

    public int puissanceMaxiPotion = 30;
    public int puissancePotion = 20;
    public int puissanceMiniPotion = 10;




    public void usePotion(SpellCaster combattant) {
            System.out.println(combattant.getName() + " utilise une Potion +20 Mana et récupère du mana");
            compteurPotion -=1;
            combattant.setMana(puissancePotion);
    }

    public void useMiniPotion(SpellCaster combattant) {
        System.out.println(combattant.getName() + " utilise une mini Potion +10 Mana et récupère du mana");
        compteurMiniPotion -=1;
        combattant.setMana(puissanceMiniPotion);
    }

    public void useMaxiPotion(SpellCaster combattant) {
        System.out.println(combattant.getName() + " utilise une maxi Potion +30 Mana et récupère du mana");
        compteurMaxiPotion -=1;
        combattant.setMana(puissanceMaxiPotion);
    }
}