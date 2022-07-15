import java.util.Scanner;

public class LCS {

    static Scanner cin = new Scanner(System.in);
    static String x, y;
    static int m, n;

    public static void main(String[] args) {
        x = cin.nextLine();
        y = cin.nextLine();

        LCSTable lcsTable = lcsLength(x, y);

        printLCS(lcsTable.b, x, m, n);

    }
    public static LCSTable lcsLength(String x, String y){

        m = x.length();
        n = y.length();

        LCSTable table = new LCSTable(new int[m+1][n+1], new int[m+1][n+1]);

        for (int i = 1; i <= m; i++) {
            table.c[i][0] = 0;
        }
        for (int i = 0; i <= n; i++) {
            table.c[0][i] = 0;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (x.charAt(i-1) == y.charAt(j-1)){
                    table.c[i][j] = table.c[i-1][j-1] + 1;
                    table.b[i][j] = 2;
                }
                else if (table.c[i-1][j] >= table.c[i][j-1]){
                    table.c[i][j] = table.c[i-1][j];
                    table.b[i][j] = 3;
                }
                else {
                    table.c[i][j] = table.c[i] [j-1];
                    table.b[i][j] = 1;
                }
            }
        }
        return table;
    }

    public static void printLCS(int[][] b, String X, int i, int j){
        if (i == 0 || j == 0){
            return;
        }

        if (b[i][j] == 2){
            printLCS(b, X, i-1, j-1);
            System.out.print(x.charAt(i-1));
        }
        else if (b[i][j] == 3){
            printLCS(b, X, i-1, j);
        }
        else printLCS(b, X, i, j-1);
    }
}

class LCSTable{
    int [][] b; //Arrow
    int [][] c; //Value

    LCSTable(int[][] b, int[][] c){
        this.b = b;
        this.c = c;
    }
}