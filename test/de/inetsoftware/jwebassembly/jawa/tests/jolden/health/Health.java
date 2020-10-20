package de.inetsoftware.jwebassembly.jawa.tests.jolden.health;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.*;
import static de.inetsoftware.jwebassembly.jawa.util.Time.currentTimeMillis;

/**
 * A Java implementation of the <tt>health</tt> Olden benchmark.  The Olden
 * benchmark simulates the Columbian health-care system:
 * <p>
 * <cite>
 * G. Lomow , J. Cleary, B. Unger, and D. West. "A Performance Study of
 * Time Warp," In SCS Multiconference on Distributed Simulation, pages 50-55,
 * Feb. 1988.
 * </cite>
 **/
@RunTest(input = "-l 5 -t 500 -s 1 -m", exception = "1")
public class Health
{
  /**
   * The size of the health-care system.
   **/
  private static int maxLevel = 0;
  /**
   * The maximum amount of time to use in the simulation.
   **/
  private static int maxTime = 0;
  /**
   * A seed value for the random no. generator.
   **/
  private static int seed = 0;
  /**
   * Set to true to print the results.
   **/
  private static boolean printResult = false;
  /**
   * Set to true to print information messages.
   **/
  private static boolean printMsgs = false;

  /**
   * The main routnie which creates the data structures for the Columbian
   * health-care system and executes the simulation for a specified time.
   * @param args the command line arguments
   **/
  @Export
  public static final void main(String args[])
  {
    parseCmdLine(args);

    long start0 = currentTimeMillis();
    Village top = Village.createVillage(maxLevel, 0, null, (int)seed);
    long end0 = currentTimeMillis();

    if (printMsgs) 
      println("Columbian Health Care Simulator\nWorking...");

    long start1 = currentTimeMillis();
    for (int i=0; i < maxTime; i++) {
      if ((i % 50) == 0 && printMsgs) {
          puti(i);
          putln();
      }
      top.simulate();
    }

    Results r = top.getResults();
    
    long end1 = currentTimeMillis();

    if (printResult || printMsgs) {
      puts("# of people treated:            "); putd(r.totalPatients); puts(" people\n");
      puts("Average length of stay:         ");
			 putd(r.totalTime / r.totalPatients); puts(" time units\n");
      puts("Average # of hospitals visited: ");
			 putd(r.totalHospitals / r.totalPatients); putln();
    }
    if (printMsgs) {
      puts("Build Time "); putd((end0 - start0)/1000.0); putln();
      puts("Run Time "); putd((end1 - start1)/1000.0); putln();
      puts("Total Time "); putd((end1 - start0)/1000.0); putln();
    }

    puts("Done!\n");
  }

  private static final void parseCmdLine(String args[])
  {
    String arg;
    int i = 0;
    while (i < args.length) { // && args[i].startsWith("-")) {
      arg = args[i++];

      // check for options that require arguments
      if (arg.equals("-l")) {
	if (i < args.length) {
	  maxLevel = 3;//new Integer(args[i++]).intValue();
	} else {
//	  throw new Error("-l requires the number of levels");
	}
      } else if (arg.equals("-t")) {
	if (i < args.length) {
	  maxTime = 300;//new Integer(args[i++]).intValue();
	} else {
//	  throw new Error("-t requires the amount of time");
	}
      } else if (arg.equals("-s")) {
	if (i < args.length) {
	  seed = 1;//new Integer(args[i++]).intValue();
	} else {
//	  throw new Error("-s requires a seed value");
	}
      } else if (arg.equals("-p")) {
	printResult = true;
      } else if (arg.equals("-m")) {
	printMsgs = true;
      } else if (arg.equals("-h")) {
	usage();
      }
    }
    if (maxTime == 0 || maxLevel == 0 || seed == 0) usage();
  }

  /**
   * The usage routine which describes the program options.
   **/
  private static final void usage()
  {
    puts("usage: java Health -l <levels> -t <time> -s <seed> [-p] [-m] [-h]");
    puts("    -l the size of the health care system");
    puts("    -t the amount of simulation time");
    puts("    -s a random no. generator seed");
    puts("    -p (print results)");
    puts("    -m (print information messages");
    puts("    -h (this message)");
//    System.exit(0);
  }


}
