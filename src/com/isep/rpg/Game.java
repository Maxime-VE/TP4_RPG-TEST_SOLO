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
        while (nb_Hero>6) {
            System.out.println(" Seulement 6 héros maximum peuvent partir à l'aventure !");
            nb_Hero = scanner.nextInt();
        }
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
                    Warrior w = new Warrior(nom_Hero1, 100, 8, false, 5);
                    w.take( new Weapon("Couteau","commun", 1) );
                    heros.add(w);
                    break;

                case 2:
                    System.out.println("Choisissez le nom de votre Hunter : ");
                    Scanner scan2 = new Scanner(System.in);
                    String nom_Hero2 = scan2.nextLine();
                    Hunter hu = new Hunter(nom_Hero2, 12, 5, false, 3);
                    hu.take( new Weapon("Arc","commun", 1) );
                    heros.add(hu);
                    break;

                case 3:
                    System.out.println("Choisissez le nom de votre Mage : ");
                    Scanner scan3 = new Scanner(System.in);
                    String nom_Hero3 = scan3.nextLine();
                    Mage m = new Mage(nom_Hero3, 13, 4, false, 3, 40);
                    m.take( new Weapon("Baguette d'apprenti","commun", 1) );
                    heros.add(m);
                    break;

                case 4:
                    System.out.println("Choisissez le nom de votre Healer : ");
                    Scanner scan4 = new Scanner(System.in);
                    String nom_Hero4 = scan4.nextLine();
                    Healer h = new Healer(nom_Hero4, 14, 3, false, 2, 50);
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

        ArrayList<Ennemy> enemies = new ArrayList<>();


        String nomEnnemy = nommageEnemy(nomSlime);
        Slime s = new Slime(nomEnnemy + ", Le roi Slime", 15, 10, false, 0, "Slime");
        enemies.add(s);

        nomEnnemy = nommageEnemy(nomGoblin);
        Goblin g = new Goblin(nomEnnemy, 15, 13, false, 0, "Goblin");
        enemies.add(g);

        nomEnnemy = nommageEnemy(nomDragon);
        Dragon d = new Dragon(nomEnnemy, 5, 20, false, 0, "Dragon");
        enemies.add(d);


        //##########################################################################################################
        // MISE EN PLACE DES CONSOMMABLES
        //##########################################################################################################
        Potion p = new Potion("Stock de potions", "plein");
        Food f = new Food("Stock de nourriture" , "plein");

        //##########################################################################################################
        // FIN INITIALISATION & DEBUT DE LA PARTIE
        //##########################################################################################################

        System.out.println(" Début de la partie ");
        userDelay();
        int idHero = 0;
        int idEnemy = 0;
        int manche = 0;
        while (enemies.size() != 0) {
            manche +=1; // Compteur de manche
            System.out.println("C'est le début de la manche n°" + manche + " et vous allez affronter " + enemies.get(idEnemy).getName());
            System.out.println("Il s'agit d'un " + enemies.get(idEnemy).getType());
            displayStatus(heros,enemies);
            userDelay();
            boolean firstTurn = false;

            //ATTAQUE DES HEROS
            while(true) {

                Combattant goodOne = heros.get(idHero);
                Ennemy badOne = enemies.get(idEnemy);

                if (!firstTurn) {
                    Random random = new Random();
                    int int_random = random.nextInt(2);
                    if (int_random == 1) {
                        attaqueEnnemie(heros, badOne);
                        displayStatus(heros,enemies);
                    }
                    firstTurn = true;
                }

                System.out.println("C' est au tour des héros d'agir ");
                for (int compteurListeHero = 0; compteurListeHero < heros.size(); compteurListeHero++) {
                    userDelay();
                    System.out.println(" Que va faire " + goodOne.getName() + "?");
                    action(goodOne, badOne,heros, p, f);
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
                    idHero = 0;
                    userDelay();
                    break;
                }
                //ATTAQUE ENNEMIE

                attaqueEnnemie(heros, badOne);
                displayStatus(heros,enemies);
                finProtection(heros);  // Annule la protection de tous les héros protégés de la manche
            }
            enemies.remove(0);

        }
        System.out.println("Félicitation jeune aventurier, tu as vaincu l'ensemble de tes ennemies et sauvé l'humanité ! \n" +
                "Tu es digne de devenir chevalier de notre royaume !");
    }



    private static String nommageEnemy(String [] list) {
        Random randomNomEnemy = new Random();
        int int_random = randomNomEnemy.nextInt(list.length);
        return list[int_random];
    }

    public static void attaqueEnnemie(List<Combattant> h, Ennemy e) {
        System.out.println("C' est au tour des vilains d'attaquer ");
        Random randomTarget = new Random();
        int int_target = randomTarget.nextInt(h.size());
        Combattant target = h.get(int_target);
        userDelay();
        System.out.println(e.getName() + " attaque " + target.getName());
        e.fight(target);
        userDelay();
        if (target.getHealthPoint() <= 0) {
            System.out.println(target.getName() + " est mort !");
            h.remove(int_target);
            if (h.size() == 0) {
                userDelay();
                System.out.println("Malgré leur courage, les Héros ont tous été vaincu par les monstres menaçant la paix. \n" +
                        "Plus rien désormais ne peut sauver l'humanité \n" +
                        "GAME OVER");
                System.exit(0);
            }
        }
    }



    public static void displayStatus(List<Combattant> h, List<Ennemy> e) {
        System.out.println("\n" +
                "#########################");
        System.out.println("Allies");
        for (Combattant c: h) {
            if (c instanceof SpellCaster) {
                System.out.println(c.getName() + " : " + c.getHealthPoint() + " PV  ,  " + c.getDegat() + " ATK  ,  " + c.getResistance() + " DEF  ,  " + ((SpellCaster) c).getMana() + " Mana" );
            }else {
                System.out.println(c.getName() + " : " + c.getHealthPoint() + " PV  ,  " + c.getDegat() + " ATK  ,  " + c.getResistance() + " DEF");
            }

        }
        System.out.println();
        Combattant c = e.get(0);
        System.out.println("Ennemy");
        System.out.println(c.getName() + " : " + c.getHealthPoint() + " PV ");

        System.out.println("#########################");
    }

    private static void action(Combattant c, Ennemy combattant,List<Combattant> h, Potion potion, Food food) {
        Scanner scanAction = new Scanner(System.in);
        for (int compteurAction = 0 ; compteurAction < 1 ; compteurAction++) {
            c.sayAction();
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

                    Scanner scanObjet = new Scanner(System.in);
                    for (int compteurObjet = 0 ; compteurObjet < 1 ; compteurObjet++) {

                        System.out.println("Quel objet souhaitez-vous consommer ? \n" +
                                "0- Retour \n" +
                                "Nourriture : \n" +
                                "1- Nuka-Cola : + " + food.puissanceNukaCola + " PV (" + food.compteurNukaCola + " en stock) \n" +
                                "2- Bento : + " + food.puissanceBento + " PV (" + food.compteurBento + " en stock) \n" +
                                "3- Ragoût : + " + food.puissanceRagout + " PV (" + food.compteurRagout + " en stock) \n" +
                                "Potion : (Uniquement pour des utilisateurs de sort : Mage & Healer) \n" +
                                "4- Mini Potion : +" + potion.puissanceMiniPotion + "Mana (" + potion.compteurMiniPotion +" en stock)\n" +
                                "5- Potion : +" + potion.puissancePotion + "Mana (" + potion.compteurPotion +" en stock) \n" +
                                "6- Maxi Potion : +" + potion.puissanceMaxiPotion + "Mana (" + potion.compteurMaxiPotion + " en stock)");

                        while (!scanObjet.hasNextInt()) {
                            scanObjet.nextLine(); //clear the invalid input before prompting again
                            System.out.println("Veuillez sélectionner le numéro de l'objet souhaité :  ");
                        }
                        int typeObjet = scanObjet.nextInt();
                        switch (typeObjet) {

                            case 1:
                                if(food.compteurNukaCola > 0) {
                                    System.out.println("Sur qui est-ce que " + c.getName() + " souhaite utiliser l'objet Nuka-Cola ?");
                                    int compteurid = 1;
                                    ArrayList<Combattant> cibleFood = new ArrayList<>();
                                    for(Combattant ally :h) {
                                        cibleFood.add(ally);
                                        System.out.println(compteurid + "- " + ally.getName() + " : " + ally.getHealthPoint() + " PV");
                                        compteurid++;
                                    }
                                    Scanner choixFood = new Scanner(System.in);
                                    int idFood = choixFood.nextInt();
                                    food.useNukaCola(cibleFood.get((idFood-1)));
                                    break;
                                } else {
                                    System.out.println("Vous n'avez plus de Nuka-Cola");
                                    compteurObjet--;
                                    break;
                                }

                            case 2:
                                if(food.compteurBento > 0) {
                                    System.out.println("Sur qui est-ce que " + c.getName() + " souhaite utiliser l'objet Bento ?");
                                    int compteurid = 1;
                                    ArrayList<Combattant> cibleFood = new ArrayList<>();
                                    for(Combattant ally :h) {
                                        cibleFood.add(ally);
                                        System.out.println(compteurid + "- " + ally.getName() + " : " + ally.getHealthPoint() + " PV");
                                        compteurid++;
                                    }
                                    Scanner choixFood = new Scanner(System.in);
                                    int idFood = choixFood.nextInt();
                                    food.useBento(cibleFood.get((idFood-1)));
                                    break;
                                } else {
                                    System.out.println("Vous n'avez plus de Bento");
                                    compteurObjet--;
                                    break;
                                }

                            case 3:
                                if(food.compteurRagout > 0) {
                                    System.out.println("Sur qui est-ce que " + c.getName() + " souhaite utiliser l'objet Ragoût ?");
                                    int compteurid = 1;
                                    ArrayList<Combattant> cibleFood = new ArrayList<>();
                                    for(Combattant ally :h) {
                                        cibleFood.add(ally);
                                        System.out.println(compteurid + "- " + ally.getName() + " : " + ally.getHealthPoint() + " PV");
                                        compteurid++;
                                    }
                                    Scanner choixFood = new Scanner(System.in);
                                    int idFood = choixFood.nextInt();
                                    food.useRagout(cibleFood.get((idFood-1)));
                                    break;
                                } else {
                                    System.out.println("Vous n'avez plus de Ragoût");
                                    compteurObjet--;
                                    break;
                                }

                            case 4:
                                if(potion.compteurMiniPotion > 0) {
                                    System.out.println("Sur qui est-ce que " + c.getName() + " souhaite utiliser l'objet Mini Potion ?");
                                    int compteurid = 1;
                                    ArrayList<Combattant> ciblePotion = new ArrayList<>();
                                    for(Combattant ally :h) {
                                        if (ally instanceof SpellCaster) {
                                            ciblePotion.add(ally);
                                            System.out.println(compteurid + "- " + ally.getName() + " : " + ((SpellCaster) ally).getMana() + " Mana");
                                            compteurid++;
                                        }
                                    }
                                    if (ciblePotion.size() == 0 ) {
                                        System.out.println("Aucun héro ne peut recevoir de potion !");
                                        compteurObjet--;
                                        break;
                                    } else{
                                        Scanner choixPotion = new Scanner(System.in);
                                        int idPotion = choixPotion.nextInt();
                                        potion.useMiniPotion((SpellCaster) ciblePotion.get((idPotion-1)));
                                        break;
                                    }

                                } else {
                                    System.out.println("Vous n'avez plus de Mini Potion");
                                    compteurObjet--;
                                    break;
                                }

                            case 5:
                                if(potion.compteurPotion > 0) {
                                    System.out.println("Sur qui est-ce que " + c.getName() + " souhaite utiliser l'objet Potion ?");
                                    int compteurid = 1;
                                    ArrayList<Combattant> ciblePotion = new ArrayList<>();
                                    for(Combattant ally :h) {
                                        if (ally instanceof SpellCaster) {
                                            ciblePotion.add(ally);
                                            System.out.println(compteurid + "- " + ally.getName() + " : " + ((SpellCaster) ally).getMana() + " Mana");
                                            compteurid++;
                                        }
                                    }
                                    if (ciblePotion.size() == 0 ) {
                                        System.out.println("Aucun héro ne peut recevoir de potion !");
                                        compteurObjet--;
                                        break;
                                    } else{
                                        Scanner choixPotion = new Scanner(System.in);
                                        int idPotion = choixPotion.nextInt();
                                        potion.usePotion((SpellCaster) ciblePotion.get((idPotion-1)));
                                        break;
                                    }

                                } else {
                                    System.out.println("Vous n'avez plus de Potion");
                                    compteurObjet--;
                                    break;
                                }

                            case 6:
                                if(potion.compteurMaxiPotion > 0) {
                                    System.out.println("Sur qui est-ce que " + c.getName() + " souhaite utiliser l'objet Maxi Potion ?");
                                    int compteurid = 1;
                                    ArrayList<Combattant> ciblePotion = new ArrayList<>();
                                    for(Combattant ally :h) {
                                        if (ally instanceof SpellCaster) {
                                            ciblePotion.add(ally);
                                            System.out.println(compteurid + "- " + ally.getName() + " : " + ((SpellCaster) ally).getMana() + " Mana");
                                            compteurid++;
                                        }
                                    }
                                    if (ciblePotion.size() == 0 ) {
                                        System.out.println("Aucun héro ne peut recevoir de potion !");
                                        compteurObjet--;
                                        break;
                                    } else{
                                        Scanner choixPotion = new Scanner(System.in);
                                        int idPotion = choixPotion.nextInt();
                                        potion.useMaxiPotion((SpellCaster) ciblePotion.get((idPotion-1)));
                                        break;
                                    }

                                } else {
                                    System.out.println("Vous n'avez plus de Maxi Potion");
                                    compteurObjet--;
                                    break;
                                }

                            case 0:
                                compteurAction--;
                                break;
                            default:
                                compteurObjet--;
                                break;
                        }
                    }

                    break;

                default:
                    compteurAction--;
                    break;

            }
        }
    }

    private static void userDelay() {
        System.out.println("\n" +
                "v       PRESS ENTER TO SKIP");
        Scanner scan = new Scanner(System.in);
        String delay = scan.nextLine();
    }

    private static void finProtection(List<Combattant> h) {
        int protege = 0;
        for (Combattant c: h) {
            if (c.isProtected){
                protege++;
                c.isProtected = false;
                System.out.println(c.getName() + " n'est plus protégé(e) ");
            }
        }if (protege == 0) {
            return;
        }
        System.out.println("#########################");
    }


//    #################################################################################################################
//                                                  LISTE
//    #################################################################################################################

    List<List<Weapon>> WeaponList = new ArrayList<List<Weapon>>();

    static String[] nomDragon = {"Voinit, le Rédempteur",
            "Ziepeo, le Brillant",
            "Freghoar, Cœur de Feu",
            "Dyghug, le Mort",
            "Eimrei, Protecteur de la Montagne",
            "Zudreonth, le Tenace"};

    static String[] nomSlime = {"Lime",
            "Gumball",
            "Bounce",
            "Driblet",
            "Split",
            "Blobby"};

    static String[] nomGoblin = {"Crocstal",
            "Minnug",
            "Ruinrak",
            "Dotraug",
            "Gazazak",
            "Ardnok"};


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
//TODO Java = CamelCase