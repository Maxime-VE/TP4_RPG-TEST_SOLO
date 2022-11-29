package com.isep.rpg;

import java.util.*;

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
            System.out.println("""
                    1- Warrior : Fort et courageux, ce combattant polyvalent allie une attaque et une défense modérée.\s
                    2- Hunter : Un manieur d'arme à distance ayant une faible résistance aux dégats mais une attaque spéciale efficace.
                    3- Mage : Un maître de sortilèges offensifs, caractérisé par ses puissantes attaques et sa faible défense.\s
                    4- Healer : Expert en sort de régénération. Malgré son manque de point de vie, il possède une très solide protection en mode défense et la capacité de soigner ses compagnons.""");
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
                    Warrior w = new Warrior("\033[1;32m"+nom_Hero1+"\033[0m", 37, 10, false, 5);
                    w.take( new Weapon("Couteau","Commun", 1) );
                    heros.add(w);
                    break;

                case 2:
                    System.out.println("Choisissez le nom de votre Hunter : ");
                    Scanner scan2 = new Scanner(System.in);
                    String nom_Hero2 = scan2.nextLine();
                    Hunter hu = new Hunter("\033[1;32m"+nom_Hero2+"\033[0m", 22, 5, false, 3);
                    hu.take( new Weapon("Arc","Commun", 1) );
                    heros.add(hu);
                    break;

                case 3:
                    System.out.println("Choisissez le nom de votre Mage : ");
                    Scanner scan3 = new Scanner(System.in);
                    String nom_Hero3 = scan3.nextLine();
                    Mage m = new Mage("\033[1;32m"+nom_Hero3+"\033[0m", 16, 3, false, 3, 40);
                    m.take( new Weapon("Baguette d'apprenti","Commun", 1) );
                    heros.add(m);
                    break;

                case 4:
                    System.out.println("Choisissez le nom de votre Healer : ");
                    Scanner scan4 = new Scanner(System.in);
                    String nom_Hero4 = scan4.nextLine();
                    Healer h = new Healer("\033[1;32m"+nom_Hero4+"\033[0m", 14, 2, false, 2, 50);
                    h.take( new Weapon("Bracelet de renforcement","Commun", 1) );
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

        ArrayList<Ennemy> manche1 = new ArrayList<>();
        ArrayList<Ennemy> manche2 = new ArrayList<>();
        ArrayList<Ennemy> manche3 = new ArrayList<>();
        ArrayList<Ennemy> manche4 = new ArrayList<>();
        ArrayList<Ennemy> manche5 = new ArrayList<>();
        ArrayList<ArrayList<Ennemy>> enemiesList = new ArrayList<>();
        String nomEnnemy;

        int nombreEnnemy = (int) ((heros.size()/2)+1);
        for (int i=0; i<nombreEnnemy ; i++) {
            nomEnnemy = nommageEnnemy(nomSlime);
            Slime s = new Slime("\033[1;31m"+nomEnnemy + ", Le Slime"+"\033[0m", 10, 2, false, 0, "Slime");
            manche1.add(s);
        }
        enemiesList.add(manche1);

        nomEnnemy =nommageEnnemy(nomGolem);
        Golem golem = new Golem("\033[1;31m"+nomEnnemy+"\033[0m", 15,3,false,0, "Golem");
        manche2.add(golem);
        enemiesList.add(manche2);


        for (int i=0; i<nombreEnnemy ; i++) {
            nomEnnemy = nommageEnnemy(nomGoblin);
            Goblin g = new Goblin("\033[1;31m"+nomEnnemy+"\033[0m", 15, 3, false, 0, "Goblin");
            manche3.add(g);
        }
        enemiesList.add(manche3);

        nomEnnemy = nommageEnnemy(nomDragon);
        Dragon d = new Dragon("\033[1;31m"+nomEnnemy+"\033[0m", 26, 20, false, 0, "Dragon");
        manche4.add(d);
        enemiesList.add(manche4);

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
        ArrayList<Ennemy> enemies;
        int idHero = 0;
        int idEnemy = 0;
        int manche = 0;
        while (enemiesList.size() != 0) {

            enemies = enemiesList.get(0);
            manche +=1; // Compteur de manche
            System.out.print("C'est le début de la manche n°" + manche + " et vous allez affronter ");
            if (enemies.size()!=1){
                for (Ennemy e : enemies){
                    System.out.print(e.getName() + ", ");
                }
                System.out.println("");
            }else {
                System.out.println(enemies.get(idEnemy).getName());
            }

            System.out.println("Il s'agit d'un ennemi de type :  " + enemies.get(idEnemy).getType());
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
                        attaqueEnnemie(heros, enemies);
                        displayStatus(heros,enemies);
                    }
                    firstTurn = true;
                }
                System.out.println("C' est au tour des héros d'agir ");
                for (int compteurListeHero = 0; compteurListeHero < heros.size(); compteurListeHero++) {
                    userDelay();
                    System.out.println(" Que va faire " + goodOne.getName() + "?");
                    action(goodOne, enemies,heros, p, f);
                    userDelay();
                    if (idHero == heros.size() - 1) {
                        idHero = 0;
                    } else {
                        idHero++;

                    }
                    goodOne = heros.get(idHero);

                    //@TODO FAIRE ICI VERIFICATION MORT VILAIN
                    if (enemies.size() == 0) {
                        System.out.println("Les Héros ont vaincu tous les monstres de la manche  !");
                        break;
                    }
                    displayStatus(heros, enemies);

                }

                //VERIFICATION MORT DU VILAIN
                if (enemies.size() == 0) {
                    idHero = 0;
                    userDelay();
                    break;
                }
                //ATTAQUE ENNEMIE
                userDelay();
                attaqueEnnemie(heros, enemies);
                displayStatus(heros,enemies);
                finProtection(heros);  // Annule la protection de tous les héros protégés de la manche
            }
            enemiesList.remove(0);
            if (enemiesList.size() == 0 ){
                break;
            }
            System.out.println("Vous tombez sur un trésor caché proche du lieu de votre précédent combat ");
            userDelay();
            recompenseFinDeManche(heros,manche,p,f,weaponList);
            upgradeFinDeManche(heros);

        }
        System.out.println("Félicitation jeune aventurier, tu as vaincu l'ensemble de tes ennemies et sauvé l'humanité ! \n" +
                "Tu es digne de devenir chevalier de notre royaume !");
    }



    private static String nommageEnnemy(String [] list) {
        Random randomNomEnemy = new Random();
        int int_random = randomNomEnemy.nextInt(list.length);
        return list[int_random];
    }

    private static String nommageWeapon(String[][] list, int i) {
        Random randomNomEnemy = new Random();
        int int_random = randomNomEnemy.nextInt(1, list[i].length);
        return list[i][int_random];
    }

    public static void attaqueEnnemie(List<Combattant> h, List<Ennemy> e) {
        System.out.println("C' est au tour des vilains d'attaquer ");
        userDelay();
        Random randomTarget = new Random();
        int int_target;
        for (Combattant c : e){
            int_target = randomTarget.nextInt(h.size());
            Combattant target = h.get(int_target);
            System.out.println(c.getName() + " attaque " + target.getName());
            c.fight(target);
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

    }

    public static void displayStatus(List<Combattant> h, List<Ennemy> e) {
        System.out.println("\n" +
                "#########################");
        System.out.println("Allies");
        for (Combattant c: h) {
            c.actualStatus();
        }
        System.out.println();
        System.out.println("Ennemy");
        for (Combattant c: e) {
            System.out.println(c.getName() + " : " + c.getHealthPoint() + " PV ");
        }


        System.out.println("#########################");
    }

    public static void recompenseFinDeManche(List<Combattant> h, int manche, Potion p, Food f, String[] [] [] list) {
        int [] heroPresent = {0,0,0,0};
        for (Combattant c : h){
            if(c instanceof Warrior){
                heroPresent[0] = 1;
            } else if (c instanceof Hunter){
                heroPresent[1] = 1;
            } else if (c instanceof Mage){
                heroPresent[2] = 1;
            } else {
                heroPresent[3] = 1;
            }
        }
        Random randomObjet = new Random();

        //INITIALISATION NOMBRE DE RECOMPENSE
        int nombreWeapon;
        if (h.size()> 3){
            nombreWeapon = (int) ((h.size()+manche-3)/2);
        }else {
            nombreWeapon = (int) ((h.size()+manche-1)/2);
        }
        int maxLvl3Consumable;
        int maxLvl2Consumable;
        int maxLvl1Consumable;
        if (manche <= 2) {
            maxLvl3Consumable = 1;
            maxLvl2Consumable = 3;
            maxLvl1Consumable = 4;
        } else {
            maxLvl3Consumable = (int) ((h.size()+manche-2)/2);
            maxLvl2Consumable = (int) ((h.size()+manche)/2);
            maxLvl1Consumable = (int) ((h.size()+manche+2)/2);
        }

        //RECOMPENSE POTION
        int miniPotionReward = randomObjet.nextInt(1,maxLvl1Consumable);
        p.rechargeMiniPotion(miniPotionReward);
        int PotionReward = randomObjet.nextInt(0,maxLvl2Consumable);
        p.rechargePotion(PotionReward);
        int maxiPotionReward = randomObjet.nextInt(0,maxLvl3Consumable);
        p.rechargeMaxiPotion(maxiPotionReward);

        //RECOMPENSE FOOD
        int nukaColaReward = randomObjet.nextInt(1,maxLvl1Consumable);
        f.rechargeNukaCola(nukaColaReward);
        int bentoReward = randomObjet.nextInt(0,maxLvl2Consumable);
        f.rechargeBento(bentoReward);
        int ragoutReward = randomObjet.nextInt(0,maxLvl3Consumable);
        f.rechargeRagout(ragoutReward);

        //RECOMPENSE FLECHE
        if (heroPresent[1] == 1){
            for (Combattant c : h){
                if (c instanceof Hunter) {
                    int flecheReward = randomObjet.nextInt(5,9);
                    ((Hunter) c).rechargeFleche(flecheReward);
                }
            }
        }

        //RECOMPENSE ARMES
        for (int i=0 ; i<nombreWeapon ; i++){
            int typeHero = randomObjet.nextInt(4);
            if (typeHero == 0 && heroPresent[0] ==1 ){
                createWeapon(h, list, "Warrior");
            } else if (typeHero == 1 && heroPresent[1] ==1 ){
                createWeapon(h, list, "Hunter");
            } else if (typeHero == 2 && heroPresent[2] ==1 ){
                createWeapon(h, list, "Mage");
            }else if (typeHero == 3 && heroPresent[3] ==1 ){
                createWeapon(h, list, "Healer");
            }else {
                i--;
            }
        }


    }

    private static void createWeapon(List<Combattant> h,  String[] [] [] listWeapon, String className){
        Random randomObjet = new Random();
        int typeHero;
        if (className.equals("Warrior") ){
            typeHero=0;
        }else if (className.equals("Hunter") ){
            typeHero=1;
        }else if (className.equals("Mage") ){
            typeHero=2;
        }else {
            typeHero=3;
        }
        String weaponName;
        int degatCommonWeapon;
        String description;
        int rareteWeapon = randomObjet.nextInt(101);
        if (rareteWeapon <= 40) {
            weaponName = nommageWeapon(listWeapon[typeHero], 0);
            degatCommonWeapon = randomObjet.nextInt(4,8);
            description = "\033[1;37m"+"Commun"+"\033[0m";
        } else if (rareteWeapon > 40 && rareteWeapon <= 70) {
            weaponName = nommageWeapon(listWeapon[typeHero], 1);
            degatCommonWeapon = randomObjet.nextInt(6, 10);
            description = "\033[1;34m"+"Rare"+"\033[0m";
        } else if (rareteWeapon > 70  && rareteWeapon <= 90) {
            weaponName = nommageWeapon(listWeapon[typeHero], 2);
            degatCommonWeapon = randomObjet.nextInt(9, 13);
            description = "\033[1;35m"+"Epique"+"\033[0m";
        } else {
            weaponName = nommageWeapon(listWeapon[typeHero], 3);
            degatCommonWeapon = randomObjet.nextInt(13, 19);
            description = "\033[1;33m"+"Légendaire"+"\033[0m";
        }
        Weapon w = new Weapon(weaponName, description, degatCommonWeapon);
        System.out.println("Vous venez de trouver " + w.getName() + " +" + w.getDamagePoints() + " ATK ("+ w.getDescription()+ ") !" );
        System.out.println("A qui souhaitez-vous donner " + w.getName() + " ?");
        int compteurid = 1;
        ArrayList<Combattant> cibleWeapon = new ArrayList<>();
            if (typeHero == 0){
                for(Combattant ally :h) {
                    if (ally instanceof Warrior) {
                        cibleWeapon.add(ally);
                        System.out.println(compteurid + "- " + ally.getName() + " : " + ally.getDegat() + " ATK");
                        compteurid++;
                        Scanner choixWeapon = new Scanner(System.in);
                        int idWeapon = choixWeapon.nextInt();
                        ((Hero) cibleWeapon.get((idWeapon-1))).changeWeapon(w);
                    }
                }
            } else if (typeHero == 1){
                for(Combattant ally :h) {
                    if (ally instanceof Hunter) {
                        cibleWeapon.add(ally);
                        System.out.println(compteurid + "- " + ally.getName() + " : " + ally.getDegat() + " ATK");
                        compteurid++;
                        Scanner choixWeapon = new Scanner(System.in);
                        int idWeapon = choixWeapon.nextInt();
                        ((Hero) cibleWeapon.get((idWeapon-1))).changeWeapon(w);
                    }
                }
            } else if (typeHero == 2){
                for(Combattant ally :h) {
                    if (ally instanceof Mage) {
                        cibleWeapon.add(ally);
                        System.out.println(compteurid + "- " + ally.getName() + " : " + ally.getDegat() + " ATK");
                        compteurid++;
                        Scanner choixWeapon = new Scanner(System.in);
                        int idWeapon = choixWeapon.nextInt();
                        ((Hero) cibleWeapon.get((idWeapon-1))).changeWeapon(w);
                    }
                }
            } else {
                for(Combattant ally :h) {
                    if (ally instanceof Healer) {
                        cibleWeapon.add(ally);
                        System.out.println(compteurid + "- " + ally.getName() + " : " + ally.getDegat() + " ATK");
                        compteurid++;
                        Scanner choixWeapon = new Scanner(System.in);
                        int idWeapon = choixWeapon.nextInt();
                        ((Hero) cibleWeapon.get((idWeapon-1))).changeWeapon(w);
                    }
                }
            }
    }

    public static void upgradeFinDeManche(List<Combattant> h){
        for (Combattant c : h){
            c.sayUpgrade();
            userDelay();
        }
    }

    private static void action(Combattant c, List<Ennemy> e,List<Combattant> h, Potion potion, Food food) {
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
                    ArrayList<Combattant> cibleAttack = new ArrayList<>();
                    int compteurId = 1;
                    for(Combattant combattant :e) {
                        cibleAttack.add(combattant);
                        System.out.println(compteurId + "- " + combattant.getName() + " : " + combattant.getHealthPoint() + " PV");
                        compteurId++;
                    }
                    Scanner choixAttack = new Scanner(System.in);
                    int idAttack = choixAttack.nextInt();
                    Ennemy ennemy = e.get((idAttack-1));
                    c.fight(ennemy);
                    if (ennemy.getHealthPoint() <= 0) {
                        e.remove((idAttack-1));
                        System.out.println("Les Héros ont vaincu " + ennemy.getName() + " !");
                    }
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
                        cibleAttack = new ArrayList<>();
                        compteurId = 1;
                        for(Combattant combattant :e) {
                            cibleAttack.add(combattant);
                            System.out.println(compteurId + "- " + combattant.getName() + " : " + combattant.getHealthPoint() + " PV");
                            compteurId++;
                        }
                        Scanner choixAttackSpe = new Scanner(System.in);
                        idAttack = choixAttackSpe.nextInt();
                        ennemy = e.get((idAttack-1));
                        c.special(ennemy);
                        if (ennemy.getHealthPoint() <= 0) {
                            e.remove((idAttack-1));
                            System.out.println("Les Héros ont vaincu " + ennemy.getName() + " !");
                        }

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

    private static void supprimeListe(List<Ennemy> e){
        if (e.size() > 0) {
            e.subList(0, e.size()).clear();
        }
    }


//    #################################################################################################################
//                                                  LISTE
//    #################################################################################################################

    //List<List<Weapon>> WeaponList = new ArrayList<List<Weapon>>();

    // NOMMAGE ARMES
    static String[] [] wWeapons = {{"Commun","Couteau","Lance","Epée","Machette","Dague","Hache"},
            {"Rare","Sabre","Katana","Dague magique"},
            {"Epique","Faux","Trident","Hallebarde"},
            {"Legendaire","Mjöllnir","Excalibur"}};

    static String[] [] hWeapons = {{"Commun","Arc court","Arc long","Arbalète"},
            {"Rare","Arc amélioré","Arc perforant"},
            {"Epique","Arc ensorcelé","Arbalète ensorcelée"},
            {"Legendaire","Arc d'Ulysse","Arc d'Artémis"}};

    static String[] [] mWeapons = {{"Commun","Baguette magique", "Bâton neutre","Grimoire interdit"},
            {"Rare","Bâton de feu","Bâton de vent","Bâton de la forêt","Bâton de glace"},
            {"Epique","Baguette Météore","Foudrekane"},
            {"Legendaire","Foudre de Zeus"}};

    static String[] [] heWeapons = {{"Commun","Diadème","Bague de régénération","Charme de soin","Pendentif magique","Couronne magique"},
            {"Rare","Bracelet d'Athéna","Bague de Prométhée","Brassard de Lion"},
            {"Epique","Collier d'Eir","Amulette merveilleuse"},
            {"Legendaire","Le Caducée","Graal","Plume de Phoenix"}};

    static String[] [] [] weaponList = {wWeapons,hWeapons,mWeapons,heWeapons};


    // BLOC NOMMAGE ENNEMIES
    static String[] nomDragon = {"Voinit, le Rédempteur",
            "Ziepeo, le Brillant",
            "Freghoar, Cœur de Feu",
            "Dyghug, le Mort",
            "Eimrei, Protecteur de la Montagne",
            "Zudreonth, le Tenace",
            "Veldra Tempest"};

    static String[] nomSlime = {"Lime",
            "Gumball",
            "Bounce",
            "Driblet",
            "Split",
            "Blobby",
            "Limule"};

    static String[] nomGoblin = {"Crocstal",
            "Minnug",
            "Ruinrak",
            "Dotraug",
            "Gazazak",
            "Ardnok"};

    static String[] nomGolem = {"Bruhn",
            "Dhukur",
            "Ruinrak",
            "Gozzlehn",
            "Garbom",
            "Ghok"};



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