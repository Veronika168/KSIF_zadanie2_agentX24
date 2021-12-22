package ksif.r2021.zadanie2.student;

import java.util.List;

public class Solver {

    public String solveVigenere(String ct1) {
        String retVal = null;
        // hint: hodnotit bez konca, t.j. kluca
        //   ...     
        //
        L1BigramDistance fit = new L1BigramDistance();
        double scoreBi = Double.MAX_VALUE;
        double scoreDict = 0;
        String resBi = "";
        String resDict = "";
        List<String> words = DictionaryNode.readDictionaryWords(/*path*/);
        DictionayNode root = DictionayNode.loadDictionary(words);

        for (String kStr: kSet) {
            VigenereCipher vc = new VigenereCipher(kStr);
            String tmp = vc.decrypt(ct1);
            double score1 = fit.evaluate(tmp);
            if (score1 < scoreBi) {
                scoreBi = score1;
                resBi = score1 + " key: " + kStr + ", text: " + tmp;
            }
            double score2 = root.evaluate(tmp, 3 , 8) // ??? magicke cisla
            if (score2 > scoreDict) {
                scoreDict = score2;
                resDict = score2 + " key: " + kStr + ", text: " + tmp;

            }
        }
        System.out.println("2-gram: " + resBi);
        System.out.println("Dictionary: " + resDict);
        return retVal;
    }

    public Integer[] getPermutation(String key) {
        Integer[] perm = new Integer[key.length()];
        //
        //   ...     
        //
        return perm;
    }

    public String solveTransposition(String ct2, Integer[] key) {
        String retVal = null;
        //
        //   ...     
        //
        return retVal;
    }

    public String solveSubstitution(String ct2WithoutT) {
        String retVal = null;
        //
        //   ...     
        //
        return retVal;
    }

    
}
