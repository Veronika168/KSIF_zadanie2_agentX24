package ksif.r2021.zadanie2.student.GA;

public interface Cipher {
    public String encrypt(Key k, String input);

    public String decrypt(Key k, String input);

    public Key randomKey();
}
