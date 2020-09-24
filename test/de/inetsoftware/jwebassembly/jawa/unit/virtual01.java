package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "1", output = "11")
public class virtual01 {
    static int m(int x) {
        virtual01 a = new virtual01();
        return a.p();
    }

    int p() {
        return 11;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
