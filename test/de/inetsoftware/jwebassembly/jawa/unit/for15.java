package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 0, output = "1100")
@RunTest(input = "", intInput = 1, output = "1211")
@RunTest(input = "", intInput = 5, output = "1655")
public class for15 {
    static int L;
    static int ci;
    static int cc;
    static int cu;
    static int cb;

    static int m(int a) {
        for15.L = a;
        for (int i = for15.for15(); i < for15.C(); i = i + for15.U()) {
            for15.B();
        }
        return 1000 * for15.ci + 100 * for15.cc + 10 * for15.cu + for15.cb;
    }

    static int for15() {
        for15.ci = for15.ci + 1;
        return 0;
    }

    static int C() {
        for15.cc = for15.cc + 1;
        return for15.L;
    }

    static int U() {
        for15.cu = for15.cu + 1;
        return 1;
    }

    static void B() {
        for15.cb = for15.cb + 1;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }

}
