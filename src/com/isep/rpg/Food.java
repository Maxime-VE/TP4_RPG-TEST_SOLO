package com.isep.rpg;

public abstract class Food extends Consumable {
    public Food(String name, String etat) {
        super(name, etat);
    }
    public int compteurFood = 2;

    public int getStat() {
        return 25;}

    
    public void useFood(Combattant combattant) {
        if (compteurFood > 0) {
            System.out.println(combattant.getName() + " utilise une potion +" + getStat() + " Mana et récupère du mana");
            compteurFood -=1;
        }else {
            System.out.println("Oh non ! Vous n'avez plus de nourriture.");
        }
    }


}