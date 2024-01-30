import java.util.Random;

public class Main {
 
    public static void main(String[] args) {
        Random random = new Random();
	int run = random.nextInt(8)-1;
	if (run == -1) {
		System.out.println("Wicket");
	} else {
		System.out.println("Runs scored: " + run);
	}
    }
}