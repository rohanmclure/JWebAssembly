package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 0, output = "1")
@RunTest(input = "", intInput = 1, output = "0")
@RunTest(input = "", intInput = 99, output = "0")
public class if01 {
    static int m(int x) {
        if (x == 0) return 1;
        else return 0;
    }


    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }

}
