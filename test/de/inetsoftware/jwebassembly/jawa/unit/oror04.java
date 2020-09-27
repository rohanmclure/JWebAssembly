package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 0, output = "55")
@RunTest(input = "", intInput = 1, output = "66")
public class oror04 {
    static int f;

    static int p(int a) {
        oror04.f = 55;
        boolean x = (a == 0) || oror04.m();
        return oror04.f;
    }

    static boolean m() {
        oror04.f = 66;
        return true;
    }

    @Export
    public static void main(String[] args) {
        puti(p(args.length));
    }
}
