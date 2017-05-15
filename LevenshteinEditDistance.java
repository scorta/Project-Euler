import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Scorta on 09/05/2017.
 * About Levenshtein distance: https://en.wikipedia.org/wiki/Levenshtein_distance
 */
class LevenshteinEditDistance {
    private enum Operation {COPY, DELETE, INSERT, REPLACE}

    private static ArrayList<String> operationSequence = new ArrayList<>();
    private static int[][] costs;
    private static String x = "saturday", y = "sunday";

    public static void main(String[] args) {
        System.out.println("The Levenshtein Distance between " + x + " and " + y + " is: " + distance(x, y));
        System.out.println(trackBack());
    }

    private static int distance(String x, String y) {
        costs = new int[x.length() + 1][];
        for (int row = 0; row < x.length() + 1; row++) {
            costs[row] = new int[y.length() + 1];
        }
        if (x.length() == 0) {
            return y.length();
        }
        if (y.length() == 0) {
            return x.length();
        }

        for (int row = 0; row <= x.length(); row++) {
            costs[row][0] = row;
        }
        for (int column = 0; column <= y.length(); column++) {
            costs[0][column] = column;
        }

        for (int target = 1; target <= y.length(); target++)
            for (int source = 1; source <= x.length(); source++) {
                if (x.charAt(source - 1) == y.charAt(target - 1)) {
                    costs[source][target] = costs[source - 1][target - 1];
                } else {
                    costs[source][target] = min(
                            costs[source - 1][target] + 1,
                            costs[source][target - 1] + 1,
                            costs[source - 1][target - 1] + 1
                    );
                }
            }

        return costs[x.length()][y.length()];
    }

    private static int min(int... numbers) {
        int minTmp = numbers[0];
        for (int index = 1; index < numbers.length; index++) {
            if (minTmp > numbers[index]) {
                minTmp = numbers[index];
            }
        }

        return minTmp;
    }

    private static String trackBack() {
        Cell currentCell = new Cell(x.length(), y.length());
        do
            operationSequence.add(previous(currentCell));
        while (currentCell.getRow() != 0 || currentCell.getColumn() != 0);

        Collections.reverse(operationSequence);
        return "Optimal operation: " + operationSequence.toString();
    }

    private static String previous(Cell c) {
        String operation = "";
        int newRow = 0;
        int newColumn = 0;
        int costTmp = Integer.MAX_VALUE;

        if (c.getColumn() > 0 && c.getRow() > 0 && x.charAt(c.getRow() - 1) == y.charAt(c.getColumn() - 1)) {
            operation = Operation.COPY.toString();

            newRow = c.getRow() - 1;
            newColumn = c.getColumn() - 1;

            c.set(newRow, newColumn);
            return operation;
        }

        if (c.getRow() > 0 && costs[c.getRow() - 1][c.getColumn()] <= costTmp) {
            costTmp = costs[c.getRow() - 1][c.getColumn()];
            operation = Operation.DELETE.toString();

            newRow = c.getRow() - 1;
            newColumn = c.getColumn();
        }

        if (c.getColumn() > 0 && costs[c.getRow()][c.getColumn() - 1] <= costTmp) {
            costTmp = costs[c.getRow()][c.getColumn() - 1];
            operation = Operation.INSERT.toString();

            newRow = c.getRow();
            newColumn = c.getColumn() - 1;
        }

        if (c.getColumn() > 0 && c.getRow() > 0 && costs[c.getRow() - 1][c.getColumn() - 1] <= costTmp) {
            operation = Operation.REPLACE.toString();

            newRow = c.getRow() - 1;
            newColumn = c.getColumn() - 1;
        }

        c.set(newRow, newColumn);

        return operation;
    }
}

class Cell {
    private int row;
    private int column;

    public Cell(int row, int column) {
        set(row, column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void set(int row, int column) {
        this.row = row;
        this.column = column;
    }
}
