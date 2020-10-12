package de.inetsoftware.jwebassembly.jawa.jolden.treeadd;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.*;

/**
 * A Java implementation of the <tt>treeadd</tt> Olden benchmark.
 * <p>
 * Treeadd is a very simple program that performs a recursive depth
 * first traversal of a binary tree and sums the value of each element
 * in the tree.  We initialize the elements in the tree to contain
 * '1'.
 **/
@RunTest(input = "-l 20 -m", output = "")
public class TreeAdd {
    /**
     * The number of levels in the tree.
     **/
    private static int levels = 0;
    /**
     * Set to true to print the final result.
     **/
    private static boolean printResult = false;
    /**
     * Set to true to print informative messages
     **/
    private static boolean printMsgs = false;

    /**
     * The main routine which creates a tree and traverses it.
     *
     * @param args the arguments to the program
     **/
    @Export
    public static void main(String[] args) {
        parseCmdLine(args);

        long start0 = 1;//System.currentTimeMillis();
        TreeNode root = new TreeNode(levels);
        long end0 = 2;//System.currentTimeMillis();

        long start1 = 3;//System.currentTimeMillis();
        int result = root.addTree();
        long end1 = 4;//System.currentTimeMillis();

        if (printResult || printMsgs) {
            puts("Received results of ");
            puti(result);
            putln();
        }

        if (printMsgs) {
            puts("Treeadd alloc time ");
            putd((end0 - start0) / 1000.0);
            putln();
            puts("Treeadd add time ");
            putd((end1 - start1) / 1000.0);
            putln();
            puts("Treeadd total time ");
            putd((end1 - start0) / 1000.0);
            putln();
        }
        puts("Done!\n");
    }

    /**
     * Parse the command line options.
     *
     * @param args the command line options.
     **/
    private static final void parseCmdLine(String[] args) {
        int i = 0;
        String arg;

        while (i < args.length) { // && args[i].startsWith("-")) {
            arg = args[i++];

            if (arg.equals("-l")) {
                if (i < args.length) {
                    levels = 20;//new Integer(args[i++]).intValue();
                }
//                else throw new RuntimeException("-l requires the number of levels");
            } else if (arg.equals("-p")) {
                printResult = true;
            } else if (arg.equals("-m")) {
                printMsgs = true;
            } else if (arg.equals("-h")) {
                usage();
            }
        }
        if (levels == 0) usage();
    }

    /**
     * The usage routine which describes the program options.
     **/
    private static final void usage() {
        puts("usage: java TreeAdd -l <levels> [-p] [-m] [-h]");
        putln();
        puts("    -l the number of levels in the tree");
        putln();
        puts("    -m (print informative messages)");
        putln();
        puts("    -p (print the result>)");
        putln();
        puts("    -h (this message)");
        putln();
//    System.exit(0);
    }

}
