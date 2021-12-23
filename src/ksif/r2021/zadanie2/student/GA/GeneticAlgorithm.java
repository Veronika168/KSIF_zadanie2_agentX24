package ksif.r2021.zadanie2.student.GA;

import java.util.*;

public class GeneticAlgorithm {

    int populationSize;
    int iterations;
    String ct;
    Cipher c;
    Fitness fitness = null;
    public List<Key> bestResults;
    private static Random rnd = new Random(System.currentTimeMillis());

    public GeneticAlgorithm(int populationSize, int iterations, String ct, Cipher c, Fitness f, List<Key> bestResults) {
        this.populationSize = populationSize;
        this.iterations = iterations;
        this.ct = ct;
        this.c = c;
        this.fitness = f;
        this.bestResults = bestResults;
    }

    public void start() {
        List<Key> pop = initPop();
        int part = populationSize / 3;

        for (int i = 0; i < iterations; i++) {
            // no change
            List<Key> noChange = new ArrayList<>();
            noChange.addAll(selBest(pop, Arrays.asList(1, 1)));
            noChange.addAll(selTourn(pop, part - 2));

            // to change
            List<Key> work = new ArrayList<>();
            work.addAll(selBest(pop, Arrays.asList(2, 1, 1)));
            work.addAll(selTourn(pop, (2 * part) - 4));
            work = crossOrd(work, 0.5f, true);
            work = swapGen(work, 0.25f);
            work = invOrd(work, 0.1f);
            work = swapPart(work, 0.25f);

            // new pop
            pop.clear();
            pop.addAll(noChange);
            pop.addAll(work);

            //evaluation

            for (Key k : work) {
                double score = fitness.evaluate(c, k, ct);
                k.setScore(score);
            }
        }
        bestResults.addAll(selBest(pop, Arrays.asList(1, 1, 1)));
    }

    private List<Key> initPop() {
        List<Key> pop = new ArrayList<>();
        for (int p = 0; p < populationSize; p++) {
            Key k = c.randomKey();
            double score = fitness.evaluate(c, k, ct);
            k.setScore(score);
            pop.add(k);
        }
        return pop;
    }

    private List<Key> selBest(List<Key> pop, List<Integer> nums) {
        Collections.sort(pop, (Key o1, Key o2) -> Double.compare(o1.getScore(), o2.getScore()));
        List<Key> newPop = new ArrayList<>();
        for (int nIdx = 0; nIdx < nums.size(); nIdx++) {
            int times = nums.get(nIdx);
            Key k = pop.get(nIdx);
            for (int i = 0; i < times; i++) {
                newPop.add(k);
            }
        }
        return newPop;
    }

    private List<Key> selTourn(List<Key> pop, int count) {
        List<Key> newPop = new ArrayList<>();
        for (int t = 0; t < count; t++) {
            int a = rnd.nextInt(pop.size());
            int b = rnd.nextInt(pop.size());

            Key aKey = pop.get(a);
            Key bKey = pop.get(b);

            if(aKey.getScore() < bKey.getScore()) {
                newPop.add(aKey);
            } else {
                newPop.add(bKey);
            }
        }
        return newPop;
    }

    private List<Key> swapGen(List<Key> pop, float rate) {
        if (rate < 0) { rate = 0; }
        if (rate > 1) { rate = 1; }

        List<Key> newPop = new ArrayList<>();
        for (Key key : pop) {
            float rndVal = rnd.nextFloat();
            if (rndVal < rate) { // passed, swap is called
               Key newKey = key.swapGen();
               double score = fitness.evaluate(c, newKey, ct);
               newKey.setScore(score);
               newPop.add(newKey);
            } else {
                newPop.add(key);
            }
        }

        return newPop;
    }

    private List<Key> swapPart(List<Key> pop, float rate) {
        if (rate < 0) { rate = 0; }
        if (rate > 1) { rate = 1; }

        List<Key> newPop = new ArrayList<>();
        for (Key key : pop) {
            float rndVal = rnd.nextFloat();
            if (rndVal < rate) { // passed, swap is called
                Key newKey = key.swapPart();
                double score = fitness.evaluate(c, newKey, ct);
                newKey.setScore(score);
                newPop.add(newKey);
            } else {
                newPop.add(key);
            }
        }

        return newPop;
    }

    private List<Key> invOrd(List<Key> pop, float rate) {
        if (rate < 0) { rate = 0; }
        if (rate > 1) { rate = 1; }

        List<Key> newPop = new ArrayList<>();
        for (Key key : pop) {
            float rndVal = rnd.nextFloat();
            if (rndVal < rate) { // passed, inv is called
                Key newKey = key.invOrd();
                double score = fitness.evaluate(c, newKey, ct);
                newKey.setScore(score);
                newPop.add(newKey);
            } else {
                newPop.add(key);
            }
        }

        return newPop;
    }

    private List<Key> crossOrd(List<Key> pop, float rate, boolean rndParents) {
        if (rate < 0) { rate = 0; }
        if (rate > 1) { rate = 1; }

        List<Key> newPop = new ArrayList<>();
        for (int i = 0, j = 0; i <pop.size() - 1; i++) {
            float rndVal = rnd.nextFloat();
            if(rndVal < rate) { // passed, X is called
                if (rndParents) {
                    j = i + rnd.nextInt(pop.size() - i);
                } else {
                    j = i + 1;
                    i++;
                }
                List<Key> children = newPop.get(i).crossOrd(newPop.get(j));
                newPop.set(i, children.get(0));
                newPop.set(j, children.get(1));
            }
        }
        return newPop;
    }
}
