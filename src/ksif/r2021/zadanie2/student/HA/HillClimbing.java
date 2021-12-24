package ksif.r2021.zadanie2.student.HA;

import ksif.r2021.zadanie2.student.GA.MonoalphabeticCipher;
import ksif.r2021.zadanie2.student.GA.MonoalphabeticKey;
import ksif.r2021.zadanie2.student.L1BigramDistance;

public class HillClimbing {
    public MonoalphabeticKey actual;
    L1BigramDistance f;
    MonoalphabeticCipher c;
    String ct;

    public HillClimbing(String ct, MonoalphabeticCipher c, L1BigramDistance f) {
        this.c = c;
        this.ct = ct;
        this.f = f;
        this.actual = (MonoalphabeticKey) c.randomKey();
        double score = f.evaluate(c, actual, ct);
        this.actual.setScore(score);
    }

    public void start(int iterations) {
        for (int i = 0; i < iterations; i++) {
            MonoalphabeticKey candidate = (MonoalphabeticKey) actual.swapGen();
            double score = f.evaluate(c, candidate, ct);
            candidate.setScore(score);
            if (candidate.getScore() < actual.getScore()) {
                actual = candidate;
            }
        }
    }
}