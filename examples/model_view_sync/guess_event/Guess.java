import java.util.*;

// Guess class
public class Guess {
    private int secret;
    private int maxSecret;

    private HintEventHandler onHint;

    private Guess() {
        
    }

    public void pickSecretNumber() {
        secret = new Random().nextInt(maxSecret) + 1;
    }

    public void check(int guess) {
        String hint;
        if (guess < secret) {
            hint = "Too Low.";
        } else if (guess > secret) {
            hint = "Too High.";
        } else {
            hint = "Correct!";
        }

        onHint.handleHint(hint);
    }

    public int getSecret() {
        return secret;
    }

    public void setSecret(int newSecret) {
        if (newSecret > maxSecret)
            throw new IllegalArgumentException();
        secret = newSecret;
    }

    public void setMaxSecret(int maxSecret) {
        this.maxSecret = maxSecret;
    }

    public int getMaxSecret() {
        return maxSecret;
    }

    public void setOnHint(HintEventHandler onHint) {
        this.onHint = onHint;
    }

    private static Guess instance = new Guess();

    public static Guess getInstance() { 
        return instance;
    }
}
