package ksif.r2021.zadanie2.student;

import ksif.r2021.zadanie2.student.GA.GeneticAlgorithm;
import ksif.r2021.zadanie2.student.GA.Key;
import ksif.r2021.zadanie2.student.GA.MonoalphabeticCipher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ksif.r2021.zadanie2.student.GFG.printAllKLength;

public class Solver {

    public String solveVigenere(String ct1) {

        String retVal = null;

        L1BigramDistance fit = new L1BigramDistance();
        double scoreBi = Double.MAX_VALUE;
        double scoreDict = 0;
        String resBi = "";
        String resDict = "";


        List<String> words = DictionaryNode.readDictionaryWords("dictionary_5000.txt");
        DictionaryNode root = DictionaryNode.loadDictionary(words);

        List<String> kSet = new ArrayList<>();

        char[] set1 = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int k = 4;
        printAllKLength(set1, k, kSet);


        for (String kStr: kSet) {
            VigenereCipher vc = new VigenereCipher(kStr);
            String tmp = vc.decrypt(ct1);
            double score1 = fit.evaluateFitness(tmp);
            if (score1 < scoreBi) {
                scoreBi = score1;
                resBi = tmp;
            }
            double score2 = root.evaluate(tmp, 3 , 8); // ??? magicke cisla
            if (score2 > scoreDict) {
                scoreDict = score2;
                resDict = tmp;

            }
        }
        // System.out.println("2-gram: " + resBi);
        // System.out.println("Dictionary: " + resDict);
        if(resBi == resDict)
            retVal = resBi;
        // System.out.println("RetVal: " + retVal);
        return retVal;
    }

    public Integer[] getPermutation(String key) {
        Integer[] perm = new Integer[key.length()];

        for (int i = 0; i < key.length(); i++) {
            perm[i] = key.charAt(i) - 'a';
        }

        return perm;
    }

    public String solveTransposition(String ct2, Integer[] key) {
        String retVal = null;
        SingleColumnarTransposition sct = new SingleColumnarTransposition(key);
        retVal = sct.decrypt(ct2);
        return retVal;
    }

    public String solveSubstitution(String ct2WithoutT) {
        String retVal = null;
        List<Key> bestKeys = new ArrayList<>();
        MonoalphabeticCipher mc = new MonoalphabeticCipher();
        GeneticAlgorithm g = new GeneticAlgorithm(20,200, ct2WithoutT, mc,
                new L1BigramDistance(), bestKeys);
        g.start();
        System.out.println(bestKeys.get(0).getKey());

        for (Key key : bestKeys) {System.out.println("key: " +  key.getKey() + " pt2cand: " + mc.decrypt(key, ct2WithoutT));}
        retVal = mc.decrypt(bestKeys.get(0), ct2WithoutT);
        return retVal;
}


}
