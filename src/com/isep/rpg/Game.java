package com.isep.rpg;

import org.junit.jupiter.api.ClassOrderer;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game {

    public static void main(String[] args) throws InterruptedException {

        //this.inputParser = inputParser;

        System.out.println(" Txt début : Intro ");

        //##########################################################################################################
        // INITIALISATION HEROS
        //##########################################################################################################

        // Liste qui contient les Héros en vie
        List<Combattant> heros = new ArrayList<>();

        System.out.println(" Veuillez choisir le nombre de héros partant à l'aventure ? ");
        Scanner scanner = new Scanner(System.in);
        int nb_Hero = scanner.nextInt();
        for (int i = 0; i < nb_Hero; i++) {
            System.out.println(" Quel type de héro ... ?");
            while (!scanner.hasNextInt()) {
                scanner.nextLine(); //clear the invalid input before prompting again
                System.out.println("Veuillez sélectionner le numéro du héro souhaité :  ");
            }
            int type_Hero = scanner.nextInt();
            switch (type_Hero) {

                case 1:
                    System.out.println("Choisissez le nom de votre Warrior : ");
                    Scanner scan1 = new Scanner(System.in);
                    String nom_Hero1 = scan1.nextLine();
                    Warrior w = new Warrior(nom_Hero1, 10, 4, false);
                    heros.add(w);
                    break;

                case 2:
                    System.out.println("Choisissez le nom de votre Hunter : ");
                    Scanner scan2 = new Scanner(System.in);
                    String nom_Hero2 = scan2.nextLine();
                    Hunter hu = new Hunter(nom_Hero2, 10, 3, false);
                    heros.add(hu);
                    break;

                case 3:
                    System.out.println("Choisissez le nom de votre Mage : ");
                    Scanner scan3 = new Scanner(System.in);
                    String nom_Hero3 = scan3.nextLine();
                    Mage m = new Mage(nom_Hero3, 10, 3, false);
                    heros.add(m);
                    break;

                case 4:
                    System.out.println("Choisissez le nom de votre Healer : ");
                    Scanner scan4 = new Scanner(System.in);
                    String nom_Hero4 = scan4.nextLine();
                    Healer h = new Healer(nom_Hero4, 10, 3, false);
                    heros.add(h);
                    break;

                default:
                    i--;
                    break;

            }

            /*for (int manche = 0; manche < 4; manche++) {

            }*/


        }
        //##########################################################################################################
        // INITIALISATION VILAINS
        //##########################################################################################################

        ArrayList<Combattant> enemies = new ArrayList<>();

        String nomEnnemy = nommageDragon();
        Dragon d = new Dragon(nomEnnemy, 20, 7, false);
        enemies.add(d);
        //##########################################################################################################
        // FIN INITIALISATION & DEBUT DE LA PARTIE
        //##########################################################################################################
        System.out.println(" Début de la partie ");
        int idHero = 0;
        int idEnemy = 0;
        for (int manche = 0; manche < 1; manche++) {    // Compteur de manche
            displayStatus(heros,enemies);
            TimeUnit.SECONDS.sleep(5);

            //ATTAQUE DES HEROS
            while(true) {
                System.out.println("C' est au tour des héros d'agir ");
                Combattant goodOne = heros.get(idHero);
                Combattant badOne = enemies.get(idEnemy);

                for (int compteurListeHero = 0; compteurListeHero < heros.size(); compteurListeHero++) {
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(" Que va faire " + goodOne.getName() + "?");
                    goodOne.sayAction();
                    TimeUnit.SECONDS.sleep(3);
                    action(goodOne, badOne);
                    TimeUnit.SECONDS.sleep(3);
                    if (idHero == heros.size() - 1) {
                        idHero = 0;
                    } else {
                        idHero++;

                    }
                    goodOne = heros.get(idHero);
                    if (badOne.getHealthPoint() <= 0) {
                        System.out.println("Les Héros ont vaincu " + badOne.getName() + " !");
                        break;
                    }
                    TimeUnit.SECONDS.sleep(3);
                    displayStatus(heros, enemies);
                    TimeUnit.SECONDS.sleep(5);

                }
                //VERIFICATION MORT DU VILAIN
                if (badOne.getHealthPoint() <= 0) {
                    System.out.println("Les Héros ont vaincu " + badOne.getName() + " !");
                    break;
                }
                //ATTAQUE ENNEMIE

                TimeUnit.SECONDS.sleep(3);
                System.out.println("C' est au tour des vilains d'attaquer ");
                Random randomTarget = new Random();
                int int_target = randomTarget.nextInt(heros.size());
                Combattant target = heros.get(int_target);
                TimeUnit.SECONDS.sleep(4);
                System.out.println(badOne.getName() + " attaque " + target.getName());
                badOne.fight(target);
                TimeUnit.SECONDS.sleep(4);
                displayStatus(heros,enemies);

                //VERIFICATION MORT DE LA CIBLE
                if (target.getHealthPoint() <= 0) {
                    System.out.println(target.getName() + " est mort !");
                    heros.remove(int_target);
                    if (heros.size() == 0) {
                        TimeUnit.SECONDS.sleep(4);
                        System.out.println("Malgré leur courage, les Héros ont tous été vaincu par les monstres menaçant la paix. \n" +
                                "Plus rien désormais ne peut sauver l'humanité \n" +
                                "GAME OVER");
                        System.exit(0);
                    }
                }
                finProtection(heros);  // Annule la protection de tous les héros
            }
            enemies.remove(0);

        }

        System.out.println("Félicitation jeune aventurier, tu as vaincu l'ensemble de tes ennemies et sauver l'humanité ! \n" +
                "Tu es digne de devenir chevalier de notre royaume !");


    }



    private static String nommageDragon() {
        String nomDragon[] = {"Voinit, le Rédempteur",
                "Ziepeo, le Brillant",
                "Freghoar, Cœur de Feu",
                "Dyghug, le Mort",
                "Eimrei, Protecteur de la Montagne",
                "Zudreonth, le Tenace"};
        Random randomNomDragon = new Random();
        int int_random = randomNomDragon.nextInt(nomDragon.length);
        return nomDragon[int_random];
    }


    public static void displayStatus(List<Combattant> h, List<Combattant> e) {
        System.out.println("#########################");
        System.out.println("Allies");
        for (Combattant c: h) {
            System.out.println(c.getName() + " : " + c.getHealthPoint() + " PV ");
        }
        System.out.println();
        for (Combattant c: e) {
            System.out.println("Enemy");
            System.out.println(c.getName() + " : " + c.getHealthPoint() + " PV ");
        }
        System.out.println("#########################");
    }

    private static void action(Combattant c, Combattant combattant) {
        Scanner scanAction = new Scanner(System.in);
        for (int compteurAction = 0 ; compteurAction < 1 ; compteurAction++) {
            while (!scanAction.hasNextInt()) {
                scanAction.nextLine(); //clear the invalid input before prompting again
                System.out.println("Veuillez sélectionner le numéro de l'action souhaitée :  ");
            }
            int typeAction = scanAction.nextInt();
            switch (typeAction) {

                case 1:
                    c.fight(combattant);
                    System.out.println(c.getName() + " inflige " + c.getDegat() + " points de dégât à " + combattant.getName());
                    break;

                case 2:
                    c.special(combattant);
                    break;

                case 3:
                    c.protection();
                    break;

                case 4:
                    System.out.println("En cours d'implémentation");
                    break;

                default:
                    compteurAction--;
                    break;

            }
        }
    }

    private static void finProtection(List<Combattant> h) {
        for (Combattant c: h) {
            c.isProtected = false;
            System.out.println(c.getName() + " n'est plus protégé(e) ");
        }
        System.out.println("#########################");
    }
}

