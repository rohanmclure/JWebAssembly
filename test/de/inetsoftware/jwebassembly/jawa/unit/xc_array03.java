package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 0, output = "0")
@RunTest(input = "", intInput = 5, output = "14988570")
public class xc_array03 {
    static int a(int l) {
        int[][] x = xc_array03.alloc(3, l);
        xc_array03.fill(x);
        return xc_array03.count(x);
    }

    static int[][] alloc(int a, int b) {
        int[][] x = new int[a][];
        for (int i = 0; i < x.length; i = i + 1) {
            x[i] = new int[b];
        }
        return x;
    }

    static void fill(int[][] x) {
        int r = 999231;
        for (int i = 0; i < x.length; i = i + 1) {
            int[] y = x[i];
            for (int j = 0; j < y.length; j = j + 1) {
                y[j] = r;
                r = r + 1;
            }
        }
    }

    static int count(int[][] x) {
        int sum = 0;
        for (int i = 0; i < x.length; i = i + 1) {
            int[] y = x[i];
            for (int j = 0; j < y.length; j = j + 1) {
                sum = sum + y[j];
            }
        }
        return sum;
    }

    @Export
    public static void main(String[] args) {
        puti(a(args.length));
    }
}
