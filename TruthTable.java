import java.util.ArrayList;

public class TruthTable {
    private final ArrayList<Character> vars;
    private final ArrayList<String[]> table;

    public TruthTable(char[] vars) {
        this.vars = new ArrayList<>();
        for (char var : vars) {
            if (!Character.toString(var).equals("")) {
                this.vars.add(var);
            }
        }
        this.table = this.baseTable();
    }

    public TruthTable(char var1, char var2) {
        this(new char[]{ var1, var2 });
    }

    public ArrayList<Character> getVars() {
        return this.vars;
    }

    public ArrayList<String[]> getTable() {
        return this.table;
    }

    public void addTable(String[] table) {
        this.table.add(table);
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

    public String[] conjunctionTable(String[] var1, String[] var2) {
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

    public String[] disjunctionTable(String[] var1, String[] var2) {
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

    public String[] implicationTable(String[] var1, String[] var2) {
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

    public String[] biconditionalTable(String[] var1, String[] var2) {
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
}
