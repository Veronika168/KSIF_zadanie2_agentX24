package ksif.r2021.zadanie2.student.GA;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Permutations {

    private static Random rnd = new Random(System.currentTimeMillis());

    public static Integer[] inverse(Integer[] perm) {
        Integer[] inv = new Integer[perm.length];
        for (int i = 0; i < perm.length; i++) {
            inv[perm[i]] = i;
        }
        return inv;
    }

    public static Character[] inverse(Character[] perm) {
        Character[] inv = new Character[perm.length];
        for (int i = 0; i < perm.length; i++) {
            inv[(int) (perm[i] - 'a')] = (char) (i + 'a');
        }
        return inv;
    }

    public static void rndPerm(Object input[]) {
        int size = input.length;
        for (int i = 0; i < (size - 1); i++) {
            int j = rnd.nextInt(size - i) + i;

            Object tmp = input[i];
            input[i] = input[j];
            input[j] = tmp;
        }
    }

    public static Set<String> allPasswords(int length){
        HashSet<String> out = new HashSet<>();
        char[] chars = new char[length];
        for(int i =0; i< length;i++)
            chars[i] ='a';
        allPasswords(0, length, chars, out);
        return out;
    }
    public static void allPasswords(int lvl, int maxLvl, char[] pwd, Set<String> res){
        if(lvl == maxLvl) {
            res.add(new String(pwd));
        } else {
            for (int i = 0; i < 26; i++) {
                pwd[lvl] = (char) (i + 'a');
                allPasswords(lvl + 1, maxLvl, pwd, res);
            }
        }
    }


}
