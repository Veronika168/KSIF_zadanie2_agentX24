package ksif.r2021.zadanie2.student;

public class VigenereCipher {
    static final int modulo = 26;
    String vk;

    public VigenereCipher(String key) {
        this.vk = key;
    }

    public String encrypt(String pt) {
        StringBuilder ct = new StringBuilder();
        for(int i =0; i < pt.length(); i++) {
            int inNum = (pt.charAt(i) - 'a');
            inNum += vk.charAt(i % vk.length()) - 'a';
            inNum %= modulo;
            char inChar = (char) (inNum + 'a');
            ct.append(inChar);
        }
        return ct.toString();
    }

    public String decrypt(String ct) {
        StringBuilder pt = new StringBuilder();
        for(int i = 0; i < ct.length(); i++) {
            int inNum = (ct.charAt(i) - 'a');
            inNum -= vk.charAt(i % vk.length()) - 'a';
            inNum += modulo;
            inNum %= modulo;
            char inChar = (char) (inNum + 'a');
            pt.append(inChar);
        }
        return pt.toString();
    }
}
