package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", output = "66")
@RunTest(input = "a", output = "55")
public class andand04 {
    static int f;

    static int p(int a) {
        andand04.f = 55;
        boolean x = (a == 0) && andand04.m();
        return andand04.f;
    }

    static boolean m() {
        andand04.f = 66;
        return true;
    }

    @Export
    public static void main(String[] args) {
        puti(p(args.length));
    }
}
