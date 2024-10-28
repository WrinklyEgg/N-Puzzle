import java.io.IOException;
import java.util.Scanner;

/**
 * Hello Java developer welcome to the Grader Than  IDE! To run the code in this file
 * press the 'Run' below button between line 10 and 11 or press the play
 * button in the upper right corner of the screen.
 *
 * Please read the ReadMe.ipynb file to get started.
 */

class Welcome {
    public static void main(String[] args) throws IOException {
        System.out.print("\033\143");

        System.out.println("Hello fellow developer 🤓!\n\nPlease type your name:");

        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();

        System.out.println("\nCongratulations on running your first java program 🥳 "+name+"!\nLet's build something amazing!");
        scan.close();
    }
}
