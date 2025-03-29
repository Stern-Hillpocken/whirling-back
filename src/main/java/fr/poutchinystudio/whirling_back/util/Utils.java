package fr.poutchinystudio.whirling_back.util;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Random;

public abstract class Utils {

    public static String letterShuffler(int times) {
        String shuffled = "";
        for (int i = 0; i < times; i++) {
            shuffled += (char)(new Random().nextInt(26) + 'a');
        }
        return shuffled;
    }

    public static String jwtUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static int random(int min, int max) {
        int range = max - min + 1;
        return (int)(Math.random() * range) + min;
    }

}
