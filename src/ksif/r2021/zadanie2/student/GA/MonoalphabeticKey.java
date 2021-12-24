package ksif.r2021.zadanie2.student.GA;

import java.lang.reflect.Array;
import java.util.*;

public class MonoalphabeticKey implements Key {
    static final Random rnd = new Random(System.currentTimeMillis());
    Character[] key;
    Character[] inverseKey;
    double score;

    public MonoalphabeticKey() {
    }

    public MonoalphabeticKey(Character[] key) {
        this.key = key;
        this.inverseKey = Permutations.inverse(key); //not done yet
    }

    @Override
    public Key swapGen() {
        Character[] cloned = this.key.clone();
        int i = rnd.nextInt(cloned.length);
        int j = rnd.nextInt(cloned.length);
        Character tmp = cloned[i];
        cloned[i] = cloned[j];
        cloned[j] = tmp;
        return new MonoalphabeticKey(cloned);
    }

    @Override
    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public double getScore() {
        return this.score;
    }

    @Override
    public String getKey() {
        return Arrays.toString(key);
    }

    @Override
    public Key swapPart() {
        int len = key.length;
        Character[] out = new Character[len];
        int pos = rnd.nextInt(len);
        if (pos == len - 1) {
            pos--;
        }
        System.arraycopy(key, pos, out, 0, len - pos);
        System.arraycopy(key, 0, out, len - pos, pos);
        return new MonoalphabeticKey(out);
    }

    @Override
    public Key invOrd() {
        int len = key.length;
        int i = rnd.nextInt(len);
        int j = rnd.nextInt(len - i) + i;
        if (i == len - 1) { i--; }
        if (j >= len) {
            j = len - 1;
        }
        Character[] out = key.clone();
        for (int s = i, e = j; s <= j; s++, e--) {
            out[s] = key[e];
        }
        return new MonoalphabeticKey(out);
    }

    @Override
    public List<Key> crossOrd(Key parent2) {
        MonoalphabeticKey in1 = this;
        MonoalphabeticKey in2 = (MonoalphabeticKey) parent2;
        int len = key.length;
        int i = rnd.nextInt(len);

        int j = rnd.nextInt(len - i) + i;
        if (i == len - 1) { i--; }
        if (j >= len) { j = len - 1; }
        if (i == j) {
            return Arrays.asList(in1, in2);
        }

        Character[] xPart1 = new Character[j - i + 1];
        Character[] xPart2 = new Character[j - i + 1];

        Set<Character> used1 = new HashSet<>();
        Set<Character> used2 = new HashSet<>();

        for (int c = i, k = 0; c <= j; c++, k++) {
            char toCopy1 = in1.key[c];
            xPart1[k] = toCopy1;
            used1.add(toCopy1);
            char toCopy2 = in2.key[c];
            xPart2[k] = toCopy2;
            used2.add(toCopy2);
        }

        Character[] restPart1 = new Character[len - xPart1.length];
        Character[] restPart2 =  new Character[len - xPart1.length];

        int idx1 = 0;
        int idx2 = 0;
        for (int c = i; c <= j; c++) {
            char toCopy1 = in2.key[c];
            if(used1.contains(toCopy1) == false) {
                restPart1[idx1++] = toCopy1;
                used1.add(toCopy1);
            }
            char toCopy2 = in1.key[c];
            if(used2.contains(toCopy2) == false) {
                restPart2[idx2++] = toCopy2;
                used2.add(toCopy2);
            }
        }

        for (int c = 0; c < len; c++) {
            char toCopy = in1.key[c];
            if (used1.contains(toCopy) == false) {
                restPart1[idx1++] = toCopy;
                used1.add(toCopy);
            }
            if (used2.contains(toCopy) == false) {
                restPart2[idx2++] = toCopy;
                used2.add(toCopy);
            }

        }
        Character[] out1 = new Character[len];
        Character[] out2 = new Character[len];
        System.arraycopy(restPart1, 0, out1, 0, i);
        System.arraycopy(restPart2, 0, out2, 0, i);
        System.arraycopy(xPart1, 0, out1, i,j - i + 1);
        System.arraycopy(xPart2, 0, out2, i,j - i + 1);
        System.arraycopy(restPart1, i, out1, j + 1,(len - j) - 1);
        System.arraycopy(restPart2, i, out2, j + 1,(len - j) - 1);

        return Arrays.asList(new MonoalphabeticKey(out1), new MonoalphabeticKey(out2));

    }
}
