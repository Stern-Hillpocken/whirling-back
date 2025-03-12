package fr.poutchinystudio.whirling_back.util;

import java.util.Random;

public abstract class Utils {

    public static String letterShuffler(int times) {
        String shuffled = "";
        for (int i = 0; i < times; i++) {
            shuffled += (char)(new Random().nextInt(26) + 'a');
        }
        return shuffled;
    }

}
