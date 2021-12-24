package ksif.r2021.zadanie2.student.GA;

import java.util.List;

public interface Key {

    public String getKey();

    public void setScore(double score);

    public double getScore();

    public Key swapGen();

    public Key swapPart();

    public Key invOrd();

    public List<Key> crossOrd(Key parent2);

}
