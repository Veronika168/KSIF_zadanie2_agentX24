package ksif.r2021.zadanie2.student.GA;

public abstract class Fitness {
    public double evaluate(Cipher c, Key k, String ct) {
        String decrypted = c.decrypt(k, ct);
        return evaluateFitness(decrypted);
    }

    public abstract double evaluateFitness(String in);
}
