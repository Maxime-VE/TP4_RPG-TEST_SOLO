package com.isep.rpg;

public class Food extends Consumable {
    public Food(String name, String etat) {
        super(name, etat);
    }

    public int compteurRagout = 0;
    public int compteurBento = 1;
    public int compteurNukaCola = 3;

    public int puissanceRagout = 30;
    public int puissanceBento = 20;
    public int puissanceNukaCola = 10;




    public void useBento(Combattant combattant) {
        System.out.println(combattant.getName() + " utilise un Bento +" + puissanceBento + " PV et récupère de la vie");
        compteurBento -=1;
        combattant.loose(-puissanceBento);
    }

    public void useNukaCola(Combattant combattant) {
        System.out.println(combattant.getName() + " utilise un NukaCola +" + puissanceNukaCola + " PV et récupère de la vie");
        compteurNukaCola -=1;
        combattant.loose(-puissanceNukaCola);
    }

    public void useRagout(Combattant combattant) {
        System.out.println(combattant.getName() + " utilise un Ragoût +" + puissanceRagout + " PV et récupère de la vie");
        compteurRagout -=1;
        combattant.loose(-puissanceRagout);
    }


}