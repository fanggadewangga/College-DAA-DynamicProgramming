import java.util.Scanner;

public class LCS {
    static Scanner cin = new Scanner(System.in);
    static String x, y;
    static int m, n;

    public static void main(String[] args) {
        System.out.print("Masukkan String 1\t: ");
        x = cin.nextLine();
        System.out.print("Masukkan String 2\t: ");
        y = cin.nextLine();

        m = x.length();
        n = y.length();

        LCSTable lcsTable = lcsLength(x, y);

        lcsTable.printTable();

        System.out.print("\nPrint LCS\t\t\t: ");
        printLCS(lcsTable.b, x, m, n);
    }
    public static LCSTable lcsLength(String x, String y){

        LCSTable table = new LCSTable(new int[m+1][n+1], new int[m+1][n+1], x, y);

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

    public static void printLCS(int[][] b, String x, int i, int j){
        if (i == 0 || j == 0){
            return;
        }
        if (b[i][j] == 2){
            printLCS(b, x, i-1, j-1);
            System.out.print(x.charAt(i-1) + " ");
        }
        else if (b[i][j] == 3){
            printLCS(b, x, i-1, j);
        }
        else printLCS(b, x, i, j-1);
    }
}

class LCSTable{
    int [][] b; //Arrow
    int [][] c; //Value

    String x;
    String y;

    LCSTable(int[][] b, int[][] c, String x, String y){
        this.b = b;
        this.c = c;
        this.x = x;
        this.y = y;
    }
    public void printTable(int [][] arr){
        for (int i = -1; i < arr.length; i++) {
            if (i > 0){
                System.out.print(x.charAt(i-1) + "  ");
            }
            else if (i == 0){
                System.out.print("xi ");
            }
            else{
                System.out.print("  ");
            }
            for (int j = 0; j < arr.length-1; j++) {
                if (i == -1){
                    if (j == 0){
                        System.out.print("yj ");
                    }
                    if (j > 0) {
                        System.out.print(y.charAt(j - 1) + " ");
                    }
                    continue;
                }
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void printTable(){
        System.out.println("\nTable c: ");
        printTable(c);
        System.out.println("\nTable b: ");
        System.out.println("0 = Tidak ada panah\n1 = Panah kiri\n2 = Panah kiri atas\n3 = Panah atas\n");
        printTable(b);
    }
}