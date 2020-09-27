package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 0, output = "0")
@RunTest(input = "", intInput = 1, output = "111")
@RunTest(input = "", intInput = 5, output = "455")
@RunTest(input = "", intInput = 9, output = "456")
public class xc_break04 {
    static int m(int a) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < a) {
            if (i >= 4) break;
            while (j < a) {
                if (j >= 5) break;
                while (k < a) {
                    if (k >= 6) break;
                    k = k + 1;
                }
                j = j + 1;
            }
            i = i + 1;
        }
        return i * 100 + j * 10 + k;
    }


    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
