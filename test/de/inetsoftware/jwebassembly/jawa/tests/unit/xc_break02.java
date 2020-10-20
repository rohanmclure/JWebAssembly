package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "1100")
@RunTest(input = "", intInput = 1, output = "1211")
@RunTest(input = "", intInput = 5, output = "1544")
public class xc_break02 {
    static int L;
    static int ci;
    static int cc;
    static int cu;
    static int cb;

    static int m(int a) {
        xc_break02.L = a;
        for (int i = xc_break02.xc_break02(); i < xc_break02.C(); i = i + xc_break02.U()) {
            if (i > 3) break;
            xc_break02.B();
        }
        return 1000 * xc_break02.ci + 100 * xc_break02.cc + 10 * xc_break02.cu + xc_break02.cb;
    }

    static int xc_break02() {
        xc_break02.ci = xc_break02.ci + 1;
        return 0;
    }

    static int C() {
        xc_break02.cc = xc_break02.cc + 1;
        return xc_break02.L;
    }

    static int U() {
        xc_break02.cu = xc_break02.cu + 1;
        return 1;
    }

    static void B() {
        xc_break02.cb = xc_break02.cb + 1;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
