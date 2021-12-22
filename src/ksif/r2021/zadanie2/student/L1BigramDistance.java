package ksif.r2021.zadanie2.student;

import java.io.*;

public class L1BigramDistance {
    private double ref[][];

    public L1BigramDistance() {
        ref = (double[][]) readFromFile("_bigrams"); // !!!!
    }

    public double evaluate(String decrypted) {
        double m[][] = new double[26][26];
        for (int i = 0; i < decrypted.length() - 1; i++) {
            char a = decrypted.charAt(i);
            char b = decrypted.charAt(i + 1);
            m[a - 'a'][b - 'a']++;
        }
        double sum = 0;
        double div = decrypted.length() - 1;
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                double nv = ref[i][j] - (m[i][j] / div);
                sum += java.lang.Math.abs(nv);
            }
        }
        return sum;
    }

    public static Object readFromFile(String path) {
        try {

            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            return o;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
