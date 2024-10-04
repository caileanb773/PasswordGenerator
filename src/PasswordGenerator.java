import java.security.SecureRandom;
import java.util.InputMismatchException;
import java.util.Scanner;

/** Class PasswordGenerator. Contains methods required to generate a password
 * based on a provided length from a pool of predefined characters.
 * @author Cailean Bernard
 */
public class PasswordGenerator {

	/** Special characters to be used in password generation.
	 */
	private final String specChar = "~!@#$%^&*()_+{}|:<>?/[],.;'-=`";

	/** Lowercase characters to be used in password generation.
	 */
	private final String lowerChar = "abcdefghijklmnopqrstuvwxyz";

	/** Capital characters to be used in password generation.
	 */
	private final String capChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/** Numbers to be used in password generation.
	 */
	private final String nums = "0123456789";

	/** Iterates through a passed array, concatenating the elements into a string.
	 * @param array - An array of randomized characters.
	 * @return String - A String version of the generated password.
	 */
	public String savePasswordAsString(String[] array) {

		// Blank String, to be concatenated at each index
		String result = "";

		// Iterating over array, concatenating "result" with each index
		for (int i = 0; i < array.length; i++) {
			result += array[i];
		}

		// Return the completed String
		return result;
	}

	/** Returns a randomly selected character from the selection pools. Concatenates
	 * all numbers, special, upper, and lowercase chars into one String and returns
	 * one at random.
	 * @return String - The randomly selected element that was selected from the
	 * pool.
	 */
	public String getRandomElement() {

		// Concatenate character pools into one master pool
		String charSelect = capChar + lowerChar + nums + specChar;

		// Initializing String which will be a single character selected at random
		String randomChar = "";

		// SecureRandom object for selecting a random character from charSelect
		SecureRandom r = new SecureRandom();

		/* Seed is set as a random integer, the maximum value of which is the
		 * total length of charSelect after concatenation.
		 */
		int seed = r.nextInt(charSelect.length());

		/* Setting randomChar as the character located at the randomly generated
		 * index.
		 */
		randomChar = String.valueOf(charSelect.charAt(seed));

		// Return the generated character (as a String).
		return randomChar;
	}

	/** Retrieves a length from the user via keyboard input. Filters out numbers
	 * less than/equal to 0 as well as all non-integer input.
	 * @param userInput - Scanner to accept input via keyboard.
	 * @return int - The password length that will be used in generation.
	 */
	public int getPassLength(Scanner userInput) {

		// Initialize new password length as 0.
		int passLength = 0;

		// Continually prompt the user to enter a valid length until they do
		while (true) {
			System.out.printf("What is your desired password length?\n>");

			// Try-catch for non-integer values
			try {
				passLength = userInput.nextInt();

				// Clear input buffer
				userInput.nextLine();

				// Filter out lengths less than or equal to 0 chars.
				if (passLength <=0 || passLength > 64) {
					System.out.printf("Please enter an integer between 12 and 64.\n");

					// Go to the top of the loop.
					continue;

					// Length check passed, return length.
				} else {
					return passLength;
				}
			} catch (InputMismatchException e) {
				System.err.printf("You did not enter an integer. [%s]\n", e);

				// Clear input buffer.
				userInput.nextLine();

				// Go to the top of the loop.
				continue;
			}
		}
	}

	/** Iterates over a passed array and sets each index to a random character
	 * by calling getRandomElement().
	 * @param array - The array to be filled with random characters.
	 */
	public void fillArray(String[] array) { 
		for (int i = 0; i < array.length; i++) {
			array[i] = getRandomElement();
		}
	}

	/** Generates a new password. Calls all methods in PasswordGenerator class
	 * to retrieve a password length from user, create a new array with that
	 * length, fill the array with random characters, and return a String
	 * representation of the generated password to the user.
	 * @param userInput - Scanner to accept input via keyboard.
	 * @return String - String representation of the generated password.*/
	public String generatePassword(Scanner userInput) {

		// Initialize a new password Array and password String.
		String[] passwordArray;
		String passwordString = "";

		// Retrieve password length.
		int passLength = getPassLength(userInput);

		// Set length of passwordArray to the user's desired length.
		passwordArray = new String[passLength];

		// Fill array with randomly generated characters.
		fillArray(passwordArray);

		// Convert the array to a String.
		passwordString = savePasswordAsString(passwordArray);

		// Return the converted String.
		return "Your new password is: " + passwordString;
	}

	/** Prompts the user to generate another password. If they enter Y or y, the
	 * program restarts. If they enter anything else, the program exits.
	 * @param userInput - Scanner to accept input via keyboard.
	 * @return boolean - If true, the program will run again. If false, exits.
	 */
	public boolean goAgain(Scanner userInput) {
		System.out.printf("Would you like to generate another password? Y to do"
				+ " so, anything else to quit.\n>");
		String response = userInput.nextLine();
		if (response.equalsIgnoreCase("y")) {
			return true;
		} else {
			return false;
		}
	}
}