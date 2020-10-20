package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 77, output = "78")
public class return00 {
    static int run(int a) {

        if (true)
            return a + 1;

        int x = 3;

        return x + 3;
    }

    @Export
    public static void main(String[] args) {
        puti(run(args.length));
    }
}
