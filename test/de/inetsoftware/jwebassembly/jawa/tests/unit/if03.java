package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 11, output = "-10")
@RunTest(input = "", intInput = 9, output = "-1")
@RunTest(input = "", intInput = 3, output = "-1")
@RunTest(input = "", intInput = 1, output = "-1")
@RunTest(input = "", intInput = 0, output = "1")
@RunTest(input = "", intInput = 2, output = "1")
@RunTest(input = "", intInput = 4, output = "1")
@RunTest(input = "", intInput = 10, output = "1")
@RunTest(input = "", intInput = 16, output = "10")
public class if03 {
    static int m(int a) {
        if (a < 0) {
            if (a <= -10) return -10;
            else return -1;
        } else {
            if (a > 10) return 10;
            else return 1;
        }
    }

    @Export
    public static void main(String[] args) {
        int m = args.length;
        if (m % 2 != 0) m = -m;
        puti(m(m));
    }

}
