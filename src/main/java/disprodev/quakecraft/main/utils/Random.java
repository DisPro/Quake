package disprodev.quakecraft.main.utils;

import disprodev.quakecraft.main.Main;

public class Random {
    public int Random() {
        java.util.Random rap = new java.util.Random();
        int nbap;
        nbap = rap.nextInt(9);


        int listMax = Main.getInstance().getParticleColor().size();
        java.util.Random random = new java.util.Random();
        int choice = random.nextInt(listMax);
        return choice;

    }
    }
