package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 0, output = "0")
@RunTest(input = "", intInput = 5, output = "25")
public class xc_array02 {
    static int a(int l) {
        int[][] x = new int[l][];
        for (int i = 0; i < x.length; i = i + 1) {
            x[i] = new int[l];
        }
        int sum = 0;
        for (int i = 0; i < x.length; i = i + 1) {
            int[] y = x[i];
            for (int j = 0; j < y.length; j = j + 1) {
                x[i][j] = 99;
                sum = sum + 1;
            }
        }
        return sum;
    }

    @Export
    public static void main(String[] args) {
        puti(a(args.length));
    }
}
