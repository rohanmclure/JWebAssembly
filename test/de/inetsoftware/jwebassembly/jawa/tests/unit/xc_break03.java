package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "1100")
@RunTest(input = "", intInput = 1, output = "1211")
@RunTest(input = "", intInput = 5, output = "1544")
public class xc_break03 {
    static int L;
    static int ci;
    static int cc;
    static int cu;
    static int cb;

    static int m(int a) {
        xc_break03.L = a;
        while (true) {
            for (int i = xc_break03.xc_break03(); i < xc_break03.C(); i = i + xc_break03.U()) {
                if (i > 3) break;
                xc_break03.B();
            }
            return 1000 * xc_break03.ci + 100 * xc_break03.cc + 10 * xc_break03.cu + xc_break03.cb;
        }
    }

    static int xc_break03() {
        xc_break03.ci = xc_break03.ci + 1;
        return 0;
    }

    static int C() {
        xc_break03.cc = xc_break03.cc + 1;
        return xc_break03.L;
    }

    static int U() {
        xc_break03.cu = xc_break03.cu + 1;
        return 1;
    }

    static void B() {
        xc_break03.cb = xc_break03.cb + 1;
    }


    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
