package de.inetsoftware.jwebassembly.jawa;

import java.util.LinkedHashMap;

public class JawaClassInfo {

    JawaMethodInfo[] instanceMethods;
    JawaMethodInfo[] staticMethods;

    JawaFieldInfo[] instanceFields;
    JawaFieldInfo[] staticFields;

    class JawaMethodInfo {
        int accessFlags;
    }

    class JawaFieldInfo {
        int accessFlags;
    }

}