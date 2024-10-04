import java.util.Scanner;

/** Launcher class that contains main method and loop, as well as a brief descriptor
 * of the program and an exit message.
 * @author Cailean Bernard.
 */
public class Launcher {

	/** Main method. Contains loop for running/exiting the program and objects 
	 * for user input and access to password generation methods.
	 * @param args - An array of String arguments to be parsed by the compiler.
	 */
	public static void main(String[] args) {

		/** PasswordGenerator object for accessing password generation methods.
		 */
		PasswordGenerator passGen = new PasswordGenerator();

		/** Scanner object for reading input from the user via keyboard.
		 */
		Scanner userIn = new Scanner(System.in);

		System.out.printf("This program generates a password between 12 and 64 "
				+ "characters in length.\nThe password is comprised of upper/"
				+ "lowercase letters, numbers, and special characters.\n");

		/* Core program loop. Prompts the user with a length to enter, generates
		 * a password, and then re-prompts the user
		 */
		do {
			long startTimeMilliS = System.currentTimeMillis();
			System.out.println(passGen.generatePassword(userIn));
			long endTimeMilliS = System.currentTimeMillis();
			System.out.printf("Elapsed time: %d milliseconds.\n", endTimeMilliS - startTimeMilliS);
			if (passGen.goAgain(userIn)) {
				continue;
			} else {
				break;
			}
		} while (true);

		System.out.printf("Exiting...\nAuthor: Cailean Bernard, 2024.");

		// Close Scanner to prevent memory leaks
		userIn.close();
	}
}