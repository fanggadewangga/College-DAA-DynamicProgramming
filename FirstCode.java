public class FirstCode {
    public static void main(String[] args) {
        int[] p = { 30, 35, 15, 5, 10, 20, 25 };
        matrixChainOrder(p);
    }
    static void matrixChainOrder(int[] arr) {
        int n = arr.length - 1;
        int[][] m = new int[arr.length + 1][arr.length + 1];
        int[][] s = new int[arr.length + 1][arr.length + 1];
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + arr[i - 1] * arr[k] * arr[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }
        //Ini Printnya Belum
    }
}