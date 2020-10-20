package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 1800;
    public static int bossDamage = 50;
    public static String bossDefenseType = "";
    public static int[] heroesHealth = {270, 260, 250, 250, 240, 280, 150, 300};
    public static int[] heroesDamage = {20, 25, 15, 0, 15, 20, 10, 5};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Lucky", "Berserk", "Thor", "Golem"};
    public static int roundCounter = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void bossAngryState() {
        if (bossHealth <= 200) {
            Random r = new Random();
            int healthRand = r.nextInt(31) + 20;
            int damageRand = r.nextInt(11) + 10;
            bossHealth = bossDamage + healthRand;
            bossDamage = bossDamage + damageRand;
            System.out.println("Boss bacame angry " + " increased health by " + healthRand + " increased damage be " + damageRand + "!!!");
        }
    }

    public static void changeBossDefense() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesHealth.length);
        bossDefenseType = heroesAttackType[randomIndex];
    }

    public static void round() {
        roundCounter++;
        changeBossDefense();
        bossAngryState();

        bossHits();
        heroesHits();
        printStatistics();

    }

    public static void heroesHits() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (heroesAttackType[i] == bossDefenseType) {
                    Random r = new Random();
                    int coeff = r.nextInt(7) + 2;
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println(heroesAttackType[i] + " Critical damage " + heroesDamage[i] * coeff + "!!!");
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
        for (int i = 0; i < heroesHealth.length; i++) {
            Random r = new Random();
            int hill = r.nextInt(20) + 15;
            if (heroesHealth[3] > 0) {
                if (heroesHealth[i] <= 100 && heroesHealth[i] > 0) {
                    heroesHealth[i] = heroesHealth[i] + hill;
                    System.out.println("Midic hilling " + heroesAttackType[i] + " hero for " + hill + "!!!");
                    break;
                }
            }
        }
    }

    public static void bossHits() {
        Random r = new Random();
        int evasion = r.nextInt(2);
        Random s = new Random();
        int stan = s.nextInt(2);
        Random b = new Random();
        int block = b.nextInt(10) + 5;
        if (evasion == 1) {
            if (heroesHealth[7] > 0 && stan != 1) {
                heroesHealth[4] = heroesHealth[4] + (bossDamage - (bossDamage / 5));
                System.out.println("Hero lucky evasioning!!!");
            } else if (stan == 0) {
                heroesHealth[4] = heroesHealth[4] + bossDamage;
                System.out.println("Hero lucky evasioning!!!");
            }
        }
        if (heroesHealth[5] > 0 && stan != 1) {
            heroesHealth[5] = heroesHealth[5] + block;
            bossHealth = bossHealth - block;
            System.out.println("Berserk blocked " + block + " boss`s damage and to return him " + (block + heroesDamage[5]));
        }
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[6] > 0) {
                if (stan == 1) {   //5 berserk
                    System.out.println("Thor staned Boss!!!");
                    System.out.println("Boss missed round!!!");
                    break;
                }
            }
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else if (heroesHealth[7] > 0) {
                    heroesHealth[i] = heroesHealth[i] - (bossDamage - (bossDamage / 5));
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }

            }

        }

    }

    public static void printStatistics() {
        System.out.println("ROUND-------" + roundCounter);
        if (bossHealth < 0) {
            bossHealth = 0;
        }
        System.out.println("Boss health: " + bossHealth);
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " Hero health: " + heroesHealth[i]);
        }
        System.out.println("_________________________");
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Heroes lose!!!");
        }

        return allHeroesDead;
    }
}


