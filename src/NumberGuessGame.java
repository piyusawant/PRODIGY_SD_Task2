import java.util.Random;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import java.net.URL;


public class NumberGuessGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int randomNumber = random.nextInt(100) + 1; // 1 - 100
        int attempts = 0;
        int guess = -1;

        System.out.println("🎯 Welcome to the Number Guessing Game!");
        System.out.println("I have chosen a number between 1 and 100.");
        System.out.println("Try to guess it!");

        while (guess != randomNumber) {
            System.out.print("Enter your guess: ");
            // safe input handling
            if (!scanner.hasNextInt()) {
                String bad = scanner.next(); // consume bad token
                System.out.println("Invalid input '" + bad + "'. Please enter an integer.");
                continue;
            }

            guess = scanner.nextInt();
            attempts++;

            if (guess < randomNumber) {
                System.out.println("Too low! 📉 Try again.");
            } else if (guess > randomNumber) {
                System.out.println("Too high! 📈 Try again.");
            } else {
                System.out.println("🎉 Congratulations! You guessed the number.");
                System.out.println("✅ The number was: " + randomNumber);
                System.out.println("📌 You took " + attempts + " attempts.");
                // Show the congratulations GIF window on correct guess
                showCongratsGif("Congratulations! You Won The Game!", attempts);
            }
        }

        scanner.close();
    }

    /**
     * Displays a small Swing window with a congratulatory message and an animated GIF.
     * The GIF is loaded from a URL. If you prefer a local file, replace the URL logic with:
     *    new ImageIcon("path/to/your/local/fire.gif")
     */
    private static void showCongratsGif(String message, int attempts) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("You Win!");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(420, 360);
            frame.setLocationRelativeTo(null); // center on screen

            // Panel to hold message and gif
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout(10, 10));
            panel.setBackground(Color.DARK_GRAY);

            // Message label
            JLabel textLabel = new JLabel("<html><div style='text-align:center;'>"
                    + "<span style='color: #FFD700; font-size:18px; font-weight:bold;'>"
                    + message + "</span><br>"
                    + "<span style='color: #FFFFFF; font-size:12px;'>Attempts: " + attempts + "</span>"
                    + "</div></html>", SwingConstants.CENTER);
            textLabel.setOpaque(false);
            panel.add(textLabel, BorderLayout.NORTH);

            // GIF label
            JLabel gifLabel = new JLabel();
            gifLabel.setHorizontalAlignment(SwingConstants.CENTER);

            try {
                // Example animated fire GIF URL.
                // You may replace this with your own GIF URL or a local path (see comment above).
            //    URL gifUrl = new URL("https://media.giphy.com/media/3oEjI6SIIHBdRxXI40/giphy.gif");
              //  ImageIcon icon = new ImageIcon(gifUrl);
              //  gifLabel.setIcon(icon);
            } catch (Exception e) {
                // Fallback: if GIF can't be loaded, show a simple emoji or text
                gifLabel.setText("🔥");
                gifLabel.setFont(new Font("SansSerif", Font.PLAIN, 72));
                gifLabel.setForeground(Color.ORANGE);
            }

            panel.add(gifLabel, BorderLayout.CENTER);

            // Small dismiss button
            JButton closeBtn = new JButton("Close");
            closeBtn.addActionListener(ae -> frame.dispose());
            JPanel south = new JPanel();
            south.setOpaque(false);
            south.add(closeBtn);
            panel.add(south, BorderLayout.SOUTH);

            frame.setContentPane(panel);
            frame.setVisible(true);
        });
    }

}
