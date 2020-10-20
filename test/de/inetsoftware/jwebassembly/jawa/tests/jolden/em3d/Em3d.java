package de.inetsoftware.jwebassembly.jawa.tests.jolden.em3d;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.*;
import static de.inetsoftware.jwebassembly.jawa.util.Time.currentTimeMillis;

/** 
 * Java implementation of the <tt>em3d</tt> Olden benchmark.  This Olden
 * benchmark models the propagation of electromagnetic waves through
 * objects in 3 dimensions. It is a simple computation on an irregular
 * bipartite graph containing nodes representing electric and magnetic
 * field values.
 *
 * <p><cite>
 * D. Culler, A. Dusseau, S. Goldstein, A. Krishnamurthy, S. Lumetta, T. von 
 * Eicken and K. Yelick. "Parallel Programming in Split-C".  Supercomputing
 * 1993, pages 262-273.
 * </cite>
 **/
@RunTest(input = "-n 2000 -d 100 -m", output = "")
public class Em3d 
{
  /**
   * The number of nodes (E and H) 
   **/
  private static int numNodes = 0;
  /**
   * The out-degree of each node.
   **/
  private static int numDegree = 0;
  /**
   * The number of compute iterations 
   **/
  private static int numIter = 1;
  /**
   * Should we print the results and other runtime messages
   **/
  private static boolean printResult = false;
  /**
   * Print information messages?
   **/
  private static boolean printMsgs = false;

  /**
   * The main roitine that creates the irregular, linked data structure
   * that represents the electric and magnetic fields and propagates the
   * waves through the graph.
   * @param args the command line arguments
   **/
  @Export
  public static final void main(String args[])
  {
    parseCmdLine(args);

    if (printMsgs) 
      println("Initializing em3d random graph...");
    long start0 = currentTimeMillis();
    BiGraph graph = BiGraph.create(numNodes, numDegree, printResult);
    long end0 = currentTimeMillis();

    // compute a single iteration of electro-magnetic propagation
    if (printMsgs) 
      puts("Propagating field values for "); puti(numIter);
			 puts(" iteration(s)...\n");
    long start1 = currentTimeMillis();
    for (int i = 0; i < numIter; i++) {
      graph.compute();
    }
    long end1 = currentTimeMillis();

    // print current field values
    if (printResult)
      println(graph.toString());

    if (printMsgs) {
      puts("EM3D build time "); putd((end0 - start0)/1000.0); putln();
      puts("EM3D compute time "); putd((end1 - start1)/1000.0); putln();
      puts("EM3D total time "); putd((end1 - start0)/1000.0); putln();
    }
    println("Done!");
  }


  /**
   * Parse the command line options.
   * @param args the command line options.
   **/
  private static final void parseCmdLine(String args[])
  {
    int i = 0;
    String arg;

    while (i < args.length) { // && args[i].startsWith("-")) {
      arg = args[i++];

      // check for options that require arguments
      if (arg.equals("-n")) {
        if (i < args.length) {
          numNodes = 1000; //new Integer(args[i++]).intValue();
        }
//        else throw new Error("-n requires the number of nodes");
      } else if (arg.equals("-d")) {
	if (i < args.length) {
	  numDegree = 50; //new Integer(args[i++]).intValue();
	}
//	else throw new Error("-d requires the out degree");
      } else if (arg.equals("-i")) {
	if (i < args.length) {
	  numIter = 1;//new Integer(args[i++]).intValue();
	}
//	else throw new Error("-i requires the number of iterations");
      } else if (arg.equals("-p")) {
        printResult = true;
      } else if (arg.equals("-m")) {
        printMsgs = true;
      } else if (arg.equals("-h")) {
	usage();
      }
    }
    if (numNodes == 0 || numDegree == 0) usage();
  }

  /**
   * The usage routine which describes the program options.
   **/
  private static  final void usage()
  {
    println("usage: java Em3d -n <nodes> -d <degree> [-p] [-m] [-h]");
    println("    -n the number of nodes");
    println("    -d the out-degree of each node");
    println("    -i the number of iterations");
    println("    -p (print detailed results)");
    println("    -m (print informative messages)");
    println("    -h (this message)");
//    System.exit(0);
  }

}
