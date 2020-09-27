package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 0, output = "11")
@RunTest(input = "", intInput = 1, output = "13")
@RunTest(input = "", intInput = 99, output = "13")
public class if02 {
    static int m(int x) {
        if (x == 0) {
            if (true) return 11;
            else return 12;
        }
        return 13;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }

}
