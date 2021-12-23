package ksif.r2021.zadanie2.student;

import java.util.ArrayList;
import java.util.List;

public class SingleColumnarTransposition {

    Integer[] key;

    private SingleColumnarTransposition() {
        //disable constructor
    }

    public SingleColumnarTransposition(Integer[] key) {
        this.key = key;
    }


    public String encrypt(String input) {
        int columns = key.length;
        int rows = input.length() / columns;
        if(input.length() % columns > 0)  rows++;

        char[][] matrix = new char[rows][columns];
        int i = 0;
        int j = 0;
        for (char s : input.toCharArray()) {
            matrix[i][j++] = s;
            if (j == columns) {
                i++;
            }
            j %= columns;
        }
        List<Integer> permList = new ArrayList<>();
        for (int c = 0; c < key.length; c++) {
            permList.add(key[c]);
        }

        StringBuilder retVal = new StringBuilder();
        for (int c = 0; c < columns; c++) {
            int colIdx = permList.indexOf(c);
            for (int r = 0; r < rows; r++) {
                if (matrix[r][colIdx] != '\u0000') {
                    retVal.append(matrix[r][colIdx]);
                }
            }
        }
        return retVal.toString();
    }

    public String decrypt(String input) {
        int columns = key.length;
        int rows = input.length() / columns;

        int incompleteCols = input.length() % columns;
        if(incompleteCols > 0)  rows++;

        List<Integer> permList = new ArrayList<>();
        for (int c = 0; c < key.length; c++) {
            permList.add(key[c]);
        }


        char[][] matrix = new char[rows][columns];
        int idx = 0;
        for (int c = 0; c < columns; c++) {
            int colIdx = permList.indexOf(c);
            int maxRowIdx = rows;
            if(incompleteCols > 0 && (colIdx >= incompleteCols)) {
                maxRowIdx--;
            }
            for (int r = 0;  r < maxRowIdx; r++) {
                matrix[r][colIdx] = input.charAt(idx++);
            }

        }


        StringBuilder retVal = new StringBuilder();
       for (int r = 0; r < rows; r++) {
           for (int c = 0; c < columns; c++) {
               if (matrix[r][c] != '\u0000') {
                   retVal.append(matrix[r][c]);
               }
           }
       }
        return retVal.toString();
    }
}
