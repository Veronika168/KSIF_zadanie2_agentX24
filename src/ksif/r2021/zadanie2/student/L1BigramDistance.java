package ksif.r2021.zadanie2.student;

import ksif.r2021.zadanie2.student.GA.Fitness;

import java.io.*;

public class L1BigramDistance extends Fitness {
    private double ref[][];

    public L1BigramDistance() {
        ref = (double[][]) readFromFile("_bigrams"); // !!!!
    }

    @Override
    public double evaluateFitness(String in) {
        double m[][] = new double[26][26];
        for (int i = 0; i < in.length() - 1; i++) {
            char a = in.charAt(i);
            char b = in.charAt(i + 1);
            m[a - 'a'][b - 'a']++;
        }
        double sum = 0;
        double div = in.length() - 1;
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
