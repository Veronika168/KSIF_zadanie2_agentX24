// original source: https://www.geeksforgeeks.org/print-all-combinations-of-given-length/

package ksif.r2021.zadanie2.student;

// Java program to print all
// possible strings of length k

import java.util.List;

class GFG {

    // The method that prints all
// possible strings of length k.
// It is mainly a wrapper over
// recursive function printAllKLengthRec()
    static void printAllKLength(char[] set, int k, List<String> dest)
    {
        int n = set.length;
        printAllKLengthRec(set, "", n, k, dest);
    }

    // The main recursive method
// to print all possible
// strings of length k
    static void printAllKLengthRec(char[] set,
                                   String prefix,
                                   int n, int k,
                                   List<String> dest)
    {

        // Base case: k is 0,
        // print prefix
        if (k == 0)
        {
            //System.out.println(prefix);
            dest.add(prefix);
            return;
        }

        // One by one add all characters
        // from set and recursively
        // call for k equals to k-1
        for (int i = 0; i < n; ++i)
        {

            // Next character of input added
            String newPrefix = prefix + set[i];

            // k is decreased, because
            // we have added a new character
            printAllKLengthRec(set, newPrefix,
                    n, k - 1, dest);
        }
    }


}
