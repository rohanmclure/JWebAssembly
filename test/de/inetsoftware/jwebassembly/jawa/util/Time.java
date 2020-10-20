package de.inetsoftware.jwebassembly.jawa.util;

import de.inetsoftware.jwebassembly.api.annotation.Import;

public class Time {

    @Import( module = "jawa", name = "\u003B\u0006\u0000millis",  js = "(x) => x")
    public static int millis() { return 0; }

    @Import( module = "jawa", name = "\u003B\u0006\u0000micros",  js = "(x) => x")
    public static int micros() { return 0; }

    public static int currentTimeMillis() {
        return millis();
    }

}
