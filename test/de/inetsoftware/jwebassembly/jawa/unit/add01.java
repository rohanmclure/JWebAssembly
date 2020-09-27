package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", output = "1")
@RunTest(input = "a", output = "2")
@RunTest(input = "a b c", output = "4")
public class add01 {
    static int run(int a) {
        return a + 1;
    }

    @Export
    public static void main(String[] args) {
        puti(run(args.length));
    }
}
