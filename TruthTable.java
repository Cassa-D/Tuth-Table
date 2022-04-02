import com.bethecoder.ascii_table.ASCIITable;

import java.util.ArrayList;

public class TruthTable {
    public static ArrayList<String[]> generateBaseTable(char[] vars) {
        int n = vars.length;

        int varsPow = (int) Math.pow(2, n);

        ArrayList<String[]> matriz = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int maxCount = 1;

            int a = n - i;
            if (a != 1) {
                maxCount = (int) Math.pow(a, 2) / 2;
            }

            String[] column = new String[varsPow+1];

            column[0] = "" + vars[i];

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

    public static String[] generatePropositionTable (String type, String[] var1, String[] var2) {
        String[] row = new String[var1.length];

        switch (type) {
            case "conjunction" -> {
                row[0] = var1[0] + " ^ " + var2[0];
                for (int i = 1; i < var1.length; i++) {
                    if (var1[i].equals("V") && var2[i].equals("V")) {
                        row[i] = "V";
                    } else {
                        row[i] = "F";
                    }
                }
            }
            case "disjunction" -> {
                row[0] = var1[0] + " v " + var2[0];
                for (int i = 1; i < var1.length; i++) {
                    if (var1[i].equals("V") || var2[i].equals("V")) {
                        row[i] = "V";
                    } else {
                        row[i] = "F";
                    }
                }
            }
            case "implication" -> {
                row[0] = var1[0] + " -> " + var2[0];
                for (int i = 1; i < var1.length; i++) {
                    if (var1[i].equals("V") && var2[i].equals("F")) {
                        row[i] = "F";
                    } else {
                        row[i] = "V";
                    }
                }
            }
            case "biconditional" -> {
                row[0] = var1[0] + " <-> " + var2[0];
                for (int i = 1; i < var1.length; i++) {
                    if (var1[i].equals(var2[i])) {
                        row[i] = "V";
                    } else {
                        row[i] = "F";
                    }
                }
            }
            default -> {
            }
        }

        return row;
    }

    public static void main(String[] args) {
        char[] vars = new char[2];
        vars[0] = 'p';
        vars[1] = 'q';
        ArrayList<String[]> baseTable = generateBaseTable(vars);

        String[] conjunctionTable = generatePropositionTable("conjunction", baseTable.get(0), baseTable.get(1));
        String[] disjunctionTable = generatePropositionTable("disjunction", baseTable.get(0), baseTable.get(1));
        String[] implicationTable = generatePropositionTable("implication", baseTable.get(0), baseTable.get(1));
        String[] biconditionalTable = generatePropositionTable("biconditional", baseTable.get(0), baseTable.get(1));

        baseTable.add(conjunctionTable);
        baseTable.add(disjunctionTable);
        baseTable.add(implicationTable);
        baseTable.add(biconditionalTable);

        printTable(baseTable);
    }

    public static void printTable(ArrayList<String[]> table) {
        String[] header = new String[table.size()];
        String[][] matriz = new String[table.get(0).length-1][table.size()];

        for (int i = 0; i < table.size(); i++) {
            String[] currVar = table.get(i);

            header[i] = currVar[0];

            for (int j = 0; j < currVar.length-1; j++) {
                matriz[j][i] = currVar[j+1];
            }
        }

        ASCIITable.getInstance().printTable(header, matriz);
    }
}
