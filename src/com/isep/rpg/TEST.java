package com.isep.rpg;

import java.util.Random;
import java.util.*;

/*public class TEST {
    public static void main(String[] args) {
        String nomDragon[] = {"Voinit, le Rédempteur",
                "Ziepeo, le Brillant\n",
                "Freghoar, Cœur de Feu\n",
                "Dyghug, le Mort\n",
                "Eimrei, Protecteur de la Montagne\n",
                "Zudreonth, le Tenace"};
        Random rand = new Random();
        int int_random = rand.nextInt(nomDragon.length);
        System.out.print(nomDragon[int_random]);
    }
}*/

/*public class TEST {
    public static void main(String[] args) {
        Warrior w = new Warrior("po",5,2);
        Hunter hu = new Hunter("pi",5,2);
        System.out.println(w instanceof Warrior);

    }*/

/*public class TEST {
    public static void main(String[] args) {
        Scanner scanAction = new Scanner(System.in);
        for (int compteurAction = 0; compteurAction < 1; compteurAction++) {
            while (!scanAction.hasNextInt()) {
                scanAction.nextLine(); //clear the invalid input before prompting again
                System.out.println("Veuillez sélectionner le numéro de l'action souhaitée :  ");
            }
            int typeAction = scanAction.nextInt();
            switch (typeAction) {

                case 1:
                    System.out.println("cas 1");
                    break;

                case 2:
                    System.out.println("cas 2");
                    break;

                case 3:
                    System.out.println("cas 3");
                    break;

                case 4:
                    System.out.println("cas 4");
                    break;

                default:
                    compteurAction--;
                    break;

            }
        }
    }*/
    public class TEST {
        public static void main(String[] args) {
            int i =0;
            while(true){

                System.out.println(i);
                if (i==10) {
                    System.out.println("On est a 11 mon reuf");
                    break;

                }
                i++;
            }
            System.out.println("fini");
        }
}
