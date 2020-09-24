package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "1", output = "123")
public class for02 {
    static int t(int a) {
		for (int i = 0; i < 1000; i++) {
			return 123;
		}
		return a;
    }

    @Export
	public static void main(String[] args) {
		puti(t(args.length));
	}
}
