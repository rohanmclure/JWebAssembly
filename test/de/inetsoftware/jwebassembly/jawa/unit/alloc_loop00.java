package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "1", output = "33")
@RunTest(input = "1 2", output = "33")
public class alloc_loop00 {
    int f;
    static int m(int x) {
		alloc_loop00 c = new alloc_loop00();
		for (int j = 0; j < x; j = j + 1) {
			c.f = 33;
		}
		return c.f;
    }

    @Export
	public static void main(String[] args) {
		puti(m(args.length));
	}
}
