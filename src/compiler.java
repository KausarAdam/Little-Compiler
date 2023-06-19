import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class compiler {
	// Initialize register with value 1
	private static int register = 1;
	// Initialize cycle counter with value 0
	private static int cycleCounter = 0;
	// Initialize the variable for displaying value every 20th cycle with value 1
	private static int every20 = 1;
	// Initialize sum of 20th cycle with value 0
	private static int sum20 = 0;

	// Main function calls the readfile function and prints the sum once the entire processing is done
	public static void main(String[] args) {
		System.out.println("By Kawthar Adam");
		// input.csv is the file that contains the data
		readFile("input.csv");
		// To print sum of the value printed after every 20 cycles
		System.out.println("Sum: " + sum20);
	}

	// Reads the csv file
	public static void readFile(String fileName) {
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			// While there is data
			while (scanner.hasNextLine()) {
				// To read the current line and process it
				String line = scanner.nextLine();

				// If the line is not empty
				if (line.length() != 0) {
					// To process the line
					processLine(line, fileName);
				}
			}
			scanner.close();
		}
		// If the csv file is not found, normal exit the system
		catch (FileNotFoundException fnf) {
			String message = fileName
					+ " not found. Cannot process the data from the file. Please rename the file to \"input.csv\"\n";
			System.out.println(message);
			System.exit(0);
		}
	}

	// To process the data read from the file
	public static void processLine(String line, String fileName) {
		try {
			// If the line begins with addx
			if (line.startsWith("addx")) {
				// Split the line when ' ' is read and store number in array
				int number = Integer.parseInt(line.split(" ")[1]);
				// Addnumber to register
				register += number;
				// Consume 1st CPU cycle
				cpuCycle();
				// Increment counter for cpu cycle
				cycleCounter++;
				// Check whether the cycle number is a multiple of 20
				check();
				// Consume 2nd CPU cycle
				cpuCycle();
				// Increment counter for cpu cycle
				cycleCounter++;
				// Check whether the cycle number is a multiple of 20
				check();
				// For testing
//				System.out.println("result -- " + register);
			} 
			// If the line begins with noop
			else if (line.equals("noop")) {
				// Consume 1 CPU cycle
				cpuCycle();
				// Increment counter for cpu cycle
				cycleCounter++;
				// Check whether the cycle number is a multiple of 20
				check();
			}
			// For tetsing
//			System.out.println("cpu counter: " + cycleCounter);
		}
		// Exception
		catch (Exception e) {
			String message = "Error in the line: " + line + " - " + e.getMessage() + "\n";
			System.out.println(message);
		}
	}

	// Each cpu cycle is 50 ms (random value)
	public static void cpuCycle() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// to check whethe rthe cycle number is a multiple of 20
	public static void check() {
		// If modulus is 0, multiple of 20
		if (cycleCounter % 20 == 0) {
			// As instructed in the pdf
			every20 = register * cycleCounter;
			System.out.println("Cycle Number " + cycleCounter + ": " + every20);
			// To store the valye printed every 20 cycles
			sum20 = sum20 + every20;
		} else { // do nothing :D
		}
	}

}
