package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 0, output = "1100")
@RunTest(input = "", intInput = 1, output = "1211")
@RunTest(input = "", intInput = 5, output = "1653")
public class xc_cont03 {
    static int L;
    static int ci;
    static int cc;
    static int cu;
    static int cb;

    static int m(int a) {
        xc_cont03.L = a;
        for (int i = xc_cont03.xc_cont03(); i < xc_cont03.C(); i = i + xc_cont03.U()) {
            if (i >= 3) continue;
            xc_cont03.B();
        }
        return 1000 * xc_cont03.ci + 100 * xc_cont03.cc + 10 * xc_cont03.cu + xc_cont03.cb;
    }

    static int xc_cont03() {
        xc_cont03.ci = xc_cont03.ci + 1;
        return 0;
    }

    static int C() {
        xc_cont03.cc = xc_cont03.cc + 1;
        return xc_cont03.L;
    }

    static int U() {
        xc_cont03.cu = xc_cont03.cu + 1;
        return 1;
    }

    static void B() {
        xc_cont03.cb = xc_cont03.cb + 1;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
