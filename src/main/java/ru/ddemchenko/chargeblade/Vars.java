package ru.ddemchenko.chargeblade;

public class Vars {
    public static String[][] flasksTexture = new String[4][7];
    public static int maxWarmth = 6;
    public static void init() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                flasksTexture[i][j] = "textures/gui/" + i + "_" + j + ".png";
            }
        }
    }

}
