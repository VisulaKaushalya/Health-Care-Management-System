import view.MainFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // launch a Swing GUI
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}