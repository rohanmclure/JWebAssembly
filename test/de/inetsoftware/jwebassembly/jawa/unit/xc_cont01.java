package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 0, output = "1100")
@RunTest(input = "", intInput = 1, output = "1211")
@RunTest(input = "", intInput = 5, output = "1655")
public class xc_cont01 {
    static int L;
    static int ci;
    static int cc;
    static int cu;
    static int cb;

    static int m(int a) {
        xc_cont01.L = a;
        for (int i = xc_cont01.xc_cont01(); i < xc_cont01.C(); i = i + xc_cont01.U()) {
            xc_cont01.B();
            continue;
        }
        return 1000 * xc_cont01.ci + 100 * xc_cont01.cc + 10 * xc_cont01.cu + xc_cont01.cb;
    }

    static int xc_cont01() {
        xc_cont01.ci = xc_cont01.ci + 1;
        return 0;
    }

    static int C() {
        xc_cont01.cc = xc_cont01.cc + 1;
        return xc_cont01.L;
    }

    static int U() {
        xc_cont01.cu = xc_cont01.cu + 1;
        return 1;
    }

    static void B() {
        xc_cont01.cb = xc_cont01.cb + 1;
    }


    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
