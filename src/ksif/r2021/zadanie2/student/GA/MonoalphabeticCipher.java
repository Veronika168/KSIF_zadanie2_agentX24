package ksif.r2021.zadanie2.student.GA;

public class MonoalphabeticCipher implements Cipher {
    public MonoalphabeticCipher() {
    }

    @Override
    public Key randomKey() {
        Character abc[] = new Character[26];
        for (int i = 0; i < abc.length; i++) {
            abc[i] = (char) (i + 'a');
        }
        Permutations.rndPerm(abc);
        return new MonoalphabeticKey(abc);
    }

    @Override
    public String encrypt(Key k, String pt) {
        MonoalphabeticKey key = (MonoalphabeticKey) k;
        StringBuilder ct = new StringBuilder();
        for(char c : pt.toCharArray()) {
            ct.append(key.key[c - 'a']);
        }
        return ct.toString();
    }

    @Override
    public String decrypt(Key k, String ct) {
        MonoalphabeticKey key = (MonoalphabeticKey) k;
        StringBuilder pt = new StringBuilder();
        for(char c : ct.toCharArray()) {
            pt.append(key.key[c - 'a']);
        }
        return pt.toString();
    }


}
