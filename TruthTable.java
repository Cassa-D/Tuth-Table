import java.util.ArrayList;

public class TruthTable {
    private final ArrayList<Character> vars;
    private ArrayList<String[]> table;

    public TruthTable(char[] vars) {
        this.vars = new ArrayList<>();
        for (char var : vars) {
            if (!Character.toString(var).equals("")) {
                this.vars.add(var);
            }
        }
        this.generateTable();
    }

    public TruthTable(char var1, char var2) {
        this(new char[]{ var1, var2 });
    }

    public ArrayList<String[]> getTable() {
        return this.table;
    }

    public boolean verifySize() {
        return this.vars.size() >= 2;
    }

    public ArrayList<String[]> baseTable() {
        if (!this.verifySize()) {
            return null;
        }

        int n = this.vars.size();

        int varsPow = (int) Math.pow(2, n);

        ArrayList<String[]> matriz = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int maxCount = 1;

            int a = n - i;
            if (a != 1) {
                maxCount = (int) Math.pow(a, 2) / 2;
            }

            String[] column = new String[varsPow+1];

            column[0] = "" + vars.get(i);

            String currLetter = "V";
            int count = 0;

            for (int j = 1; j < varsPow+1; j++) {
                if (count >= maxCount) {
                    count = 0;
                    if (currLetter.equals("V")) {
                        currLetter = "F";
                    } else {
                        currLetter =  "V";
                    }
                }

                count++;

                column[j] = currLetter;
            }

            matriz.add(column);
        }

        return matriz;
    }

    public static String[] conjunctionTable(String[] var1, String[] var2) {
        String[] row = new String[var1.length];

        row[0] = var1[0] + " ^ " + var2[0];
        for (int i = 1; i < var1.length; i++) {
            if (var1[i].equals("V") && var2[i].equals("V")) {
                row[i] = "V";
            } else {
                row[i] = "F";
            }
        }

        return row;
    }

    public static String[] disjunctionTable(String[] var1, String[] var2) {
        String[] row = new String[var1.length];

        row[0] = var1[0] + " v " + var2[0];
        for (int i = 1; i < var1.length; i++) {
            if (var1[i].equals("V") || var2[i].equals("V")) {
                row[i] = "V";
            } else {
                row[i] = "F";
            }
        }

        return row;
    }

    public static String[] implicationTable(String[] var1, String[] var2) {
        String[] row = new String[var1.length];

        row[0] = var1[0] + " -> " + var2[0];
        for (int i = 1; i < var1.length; i++) {
            if (var1[i].equals("V") && var2[i].equals("F")) {
                row[i] = "F";
            } else {
                row[i] = "V";
            }
        }

        return row;
    }

    public static String[] biconditionalTable(String[] var1, String[] var2) {
        String[] row = new String[var1.length];

        row[0] = var1[0] + " <-> " + var2[0];
        for (int i = 1; i < var1.length; i++) {
            if (var1[i].equals(var2[i])) {
                row[i] = "V";
            } else {
                row[i] = "F";
            }
        }

        return row;
    }

    private void generateTable() {
        this.table = this.baseTable();

        StringBuilder possibleCombinations = new StringBuilder();
        for (int i = 0; i < this.vars.size(); i++) {
            char var1 = this.vars.get(i);
            for(int j = 0; j < this.vars.size(); j++) {
                char var2 = this.vars.get(j);

                String combination1 = "" + var1 + var2 + ";";
                String combination2 = "" + var2 + var1 + ";";
                if (var1 == var2 || possibleCombinations.toString().contains(combination1) || possibleCombinations.toString().contains(combination2)) {
                    continue;
                }

                possibleCombinations.append(combination1);

                String[] conjunctionTable = TruthTable.conjunctionTable(this.table.get(i), this.table.get(j));
                String[] disjunctionTable = TruthTable.disjunctionTable(this.table.get(i), this.table.get(j));
                String[] implicationTable = TruthTable.implicationTable(this.table.get(i), this.table.get(j));
                String[] biconditionalTable = TruthTable.biconditionalTable(this.table.get(i), this.table.get(j));

                this.table.add(conjunctionTable);
                this.table.add(disjunctionTable);
                this.table.add(implicationTable);
                this.table.add(biconditionalTable);
            }
        }
    }
}
