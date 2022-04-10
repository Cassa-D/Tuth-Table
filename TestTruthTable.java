import com.bethecoder.ascii_table.ASCIITable;

import java.util.ArrayList;

public class TestTruthTable {
    public static void main(String[] args) {
        TruthTable truthTable = new TruthTable(new char[]{ 'p', 'q' });

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
