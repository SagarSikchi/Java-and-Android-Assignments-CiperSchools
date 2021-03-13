/*
 * Author: Sagar Sikchi
 */
abstract class Pattern {
    public abstract void printPattern(int rowCount);
}
class StarPattern extends Pattern{
    @Override
    public void printPattern(int rowCount) {
        int spaceCount = rowCount;
        for(int starCount = 1; starCount <= rowCount; starCount++) {
            for(int j = 0; j < spaceCount; j++) {
                System.out.print(" ");
            }
            spaceCount--;
            for(int star = 0; star < starCount; star++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }
}
class LetterPattern extends Pattern {
    @Override
    public void printPattern(int rowCount) {
        int spaceCount = rowCount;
        char alphabet = 'A';
        for(int letterCount = 1; letterCount <= rowCount; letterCount++) {
            for(int j = 0; j < spaceCount; j++) {
                System.out.print("  ");
            }
            spaceCount--;
            for(int letter = 0; letter < letterCount; letter++) {
                System.out.print(alphabet++ + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }
}
public class Main {
    public static void main(String[] args) {
        int rowCount = 5;
        StarPattern starPattern = new StarPattern();
        LetterPattern letterPattern = new LetterPattern();
        starPattern.printPattern(rowCount);
        letterPattern.printPattern(rowCount);
    }
}