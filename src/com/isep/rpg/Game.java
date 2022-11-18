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

        System.out.println(" Veuillez choisir le nombre de héros partant à l'aventure.");
        Scanner scanner = new Scanner(System.in);
        int nb_Hero = scanner.nextInt();
        for (int i = 0; i < nb_Hero; i++) {
            System.out.println(" Quel sera votre héro n°" + (i+1) + " ?");
            //System.out.println("1- Warrior : Fort et courageux, ce combattant polyvalent allie une attaque et une défense modérée. \n" +
            //        "2- Hunter : Un manieur d'arme à distance ayant une faible résistance aux dégats mais une attaque spéciale efficace.\n" +
            //        "3- Mage : Un maître de sortilèges offensifs, caractérisé par ses puissantes attaques et sa faible défense. \n" +
            //        "4- Healer : Expert en sort de régénération. Malgré son manque de point de vie, il possède une très solide protection en mode défense et la capacité de soigner ses compagnons.");
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
                    Warrior w = new Warrior(nom_Hero1, 11, 6, false);
                    w.take( new Weapon("Couteau","commun", 1) );
                    heros.add(w);
                    break;

                case 2:
                    System.out.println("Choisissez le nom de votre Hunter : ");
                    Scanner scan2 = new Scanner(System.in);
                    String nom_Hero2 = scan2.nextLine();
                    Hunter hu = new Hunter(nom_Hero2, 12, 5, false);
                    hu.take( new Weapon("Arc","commun", 1) );
                    heros.add(hu);
                    break;

                case 3:
                    System.out.println("Choisissez le nom de votre Mage : ");
                    Scanner scan3 = new Scanner(System.in);
                    String nom_Hero3 = scan3.nextLine();
                    Mage m = new Mage(nom_Hero3, 13, 4, false);
                    m.take( new Weapon("Baguette d'apprenti","commun", 1) );
                    heros.add(m);
                    break;

                case 4:
                    System.out.println("Choisissez le nom de votre Healer : ");
                    Scanner scan4 = new Scanner(System.in);
                    String nom_Hero4 = scan4.nextLine();
                    Healer h = new Healer(nom_Hero4, 14, 3, false);
                    h.take( new Weapon("Bracelet de renforcement","commun", 1) );
                    heros.add(h);
                    break;

                default:
                    i--;
                    break;

            }

        }
        //##########################################################################################################
        // INITIALISATION VILAINS
        //##########################################################################################################

        ArrayList<Combattant> enemies = new ArrayList<>();

        String nomEnnemy = nommageDragon();
        Dragon d = new Dragon(nomEnnemy, 20, 7, false);
        enemies.add(d);

        nomEnnemy = nommageDragon();
        Goblin g = new Goblin(nomEnnemy, 20, 7, false);
        enemies.add(d);

        //##########################################################################################################
        // FIN INITIALISATION & DEBUT DE LA PARTIE
        //##########################################################################################################

        System.out.println(" Début de la partie ");
        userDelay();
        int idHero = 0;
        int idEnemy = 0;
        for (int manche = 0; manche < enemies.size(); manche++) {    // Compteur de manche
            displayStatus(heros,enemies);
            userDelay();

            //ATTAQUE DES HEROS
            while(true) {
                System.out.println("C' est au tour des héros d'agir ");
                Combattant goodOne = heros.get(idHero);
                Combattant badOne = enemies.get(idEnemy);

                for (int compteurListeHero = 0; compteurListeHero < heros.size(); compteurListeHero++) {
                    userDelay();
                    System.out.println(" Que va faire " + goodOne.getName() + "?");
                    goodOne.sayAction();
                    action(goodOne, badOne,heros);
                    userDelay();
 //TEST                   ((Hero) goodOne).changeWeapon(new Weapon("Epée","rare",8));
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
                    displayStatus(heros, enemies);
                    userDelay();

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
                userDelay();
                System.out.println(badOne.getName() + " attaque " + target.getName());
                badOne.fight(target);
                userDelay();
                displayStatus(heros,enemies);

                //VERIFICATION MORT DE LA CIBLE
                if (target.getHealthPoint() <= 0) {
                    System.out.println(target.getName() + " est mort !");
                    heros.remove(int_target);
                    if (heros.size() == 0) {
                        userDelay();
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

        System.out.println("Félicitation jeune aventurier, tu as vaincu l'ensemble de tes ennemies et sauvé l'humanité ! \n" +
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

    private static void action(Combattant c, Combattant combattant,List<Combattant> h) {
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
                    System.out.println(c.getName() + " inflige " + c.getDegat() + " points de dégât à " + combattant.getName()); // getDegat à modifier en degatTotal @TODO
                    break;

                case 2:
                    if (c instanceof Healer) {
                        System.out.println("Qui est-ce que " + c.getName() + " souhaite soigner ?");
                        int compteurid = 1;
                        for(Combattant ally :h) {
                            System.out.println(compteurid + "- " + ally.getName() + " : " + ally.getHealthPoint() + "PV");
                            compteurid++;
                        }
                        Scanner choixSoin = new Scanner(System.in);
                        int idSoin = choixSoin.nextInt();
                        c.special(h.get((idSoin-1)));
                    }else{
                        c.special(combattant);
                    }

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

    private static void userDelay() {
        System.out.println("v       PRESS ENTER TO SKIP");
        Scanner scan = new Scanner(System.in);
        String delay = scan.nextLine();
    }

    private static void finProtection(List<Combattant> h) {
        for (Combattant c: h) {
            c.isProtected = false;
            System.out.println(c.getName() + " n'est plus protégé(e) ");
        }
        System.out.println("#########################");
    }


//    #################################################################################################################
//                                                  LISTE
//    #################################################################################################################

    List<List<Weapon>> WeaponList = new ArrayList<List<Weapon>>();



    /*
    #################################################################################################################
                                                    UTILS
    #################################################################################################################
    if (goodOne instanceof Hunter) {
                        ((Hunter) goodOne).rechargeFleche(n);                   //Rajout de n flèche si le héro
                                                                                // est un Hunter
                    }
    System.out.println("\033[0;31m" + "RED COLORED" + "\033[0m" + " NORMAL");   Changement de couleur du txt
     */
}

