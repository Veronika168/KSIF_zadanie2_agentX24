package ksif.r2021.zadanie2.student;

import ksif.r2021.zadanie2.student.GA.GeneticAlgorithm;
import ksif.r2021.zadanie2.student.GA.Key;
import ksif.r2021.zadanie2.student.GA.MonoalphabeticCipher;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static ksif.r2021.zadanie2.student.GA.Permutations.allPasswords;
import static ksif.r2021.zadanie2.student.GFG.printAllKLength;

public class Solver {

    public String solveVigenere(String ct1) {
        String retVal = null;

        double scoreDict = 0;
        String resDict = "";


        List<String> words = DictionaryNode.readDictionaryWords("dictionary_5000.txt");
        DictionaryNode root = DictionaryNode.loadDictionary(words);

        Set<String> kSet;
        kSet = allPasswords(4);

        for (String kStr: kSet) {
            VigenereCipher vc = new VigenereCipher(kStr);
            String tmp = vc.decrypt(ct1);

            double score2 = root.evaluate(tmp, 3 , 8);
            if (score2 > scoreDict) {
                scoreDict = score2;
                resDict = tmp;

            }
        }
        // System.out.println("2-gram: " + resBi);
        // System.out.println("Dictionary: " + resDict);

        retVal = resDict;
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
        GeneticAlgorithm g = new GeneticAlgorithm(20,2000, ct2WithoutT, mc,
                new L1BigramDistance(), bestKeys);
        g.start();
        // System.out.println(bestKeys.get(0).getKey());

        // for (Key key : bestKeys) {System.out.println("key: " +  key.getKey() + " score: "+ key.getScore()+ " pt2cand: " + mc.decrypt(key, ct2WithoutT));}
        retVal = mc.decrypt(bestKeys.get(0), ct2WithoutT);
        return retVal;

}
   /*
    // volanie HC algorimu s 10 generaciami
    public String solveSubstitution(String ct2WithoutT) {
        String retVal = null;
        MonoalphabeticCipher cipher = new MonoalphabeticCipher();
        L1BigramDistance f = new L1BigramDistance();
        HillClimbing hc = new HillClimbing(ct2WithoutT, cipher, f);
        hc.start(1000);
        // x(10) generacii testovacieho HC algoritmu
        for (int x = 0; x < 10; x++) {
            HillClimbing test = new HillClimbing(ct2WithoutT, cipher, f);
            test.start(10000);
            if (test.actual.getScore() < hc.actual.getScore()) {
                hc.actual = test.actual;
            }
        }
        retVal = cipher.decrypt(hc.actual, ct2WithoutT);
        return retVal;
    } */
}
