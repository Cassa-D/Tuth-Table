import com.bethecoder.ascii_table.ASCIITable;

import java.util.ArrayList;

public class TestTruthTable {
    public static void main(String[] args) {
        char[] vars = new char[2];
        vars[0] = 'p';
        vars[1] = 'q';

        TruthTable truthTable = new TruthTable(vars);

        ArrayList<String[]> baseTable = truthTable.getTable();

        String[] conjunctionTable = truthTable.conjunctionTable(baseTable.get(0), baseTable.get(1));
        String[] disjunctionTable = truthTable.disjunctionTable(baseTable.get(0), baseTable.get(1));
        String[] implicationTable = truthTable.implicationTable(baseTable.get(0), baseTable.get(1));
        String[] biconditionalTable = truthTable.biconditionalTable(baseTable.get(0), baseTable.get(1));

        truthTable.addTable(conjunctionTable);
        truthTable.addTable(disjunctionTable);
        truthTable.addTable(implicationTable);
        truthTable.addTable(biconditionalTable);

        printTable(truthTable);
    }

    public static void printTable(TruthTable table) {
        ArrayList<String[]> tableMatriz = table.getTable();

        String[] header = new String[tableMatriz.size()];
        String[][] matriz = new String[tableMatriz.get(0).length-1][tableMatriz.size()];

        for (int i = 0; i < tableMatriz.size(); i++) {
            String[] currVar = tableMatriz.get(i);

            header[i] = currVar[0];

            for (int j = 0; j < currVar.length-1; j++) {
                matriz[j][i] = currVar[j+1];
            }
        }

        ASCIITable.getInstance().printTable(header, matriz);
    }
}
