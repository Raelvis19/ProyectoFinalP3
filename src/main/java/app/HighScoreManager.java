package main.java.app;

import java.io.*;

public class HighScoreManager {

    private static final String FILE_PATH = "highscore.dat";

    
    public static int cargarHighScore() {

        try (DataInputStream dis = new DataInputStream(new FileInputStream(FILE_PATH))) {
            return dis.readInt();
        } catch (IOException e) {
            return 0; 
        }
    }

    
    public static void guardarHighScore(int score) {

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_PATH))) {
            dos.writeInt(score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}