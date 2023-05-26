import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.HashMap;
import java.util.Map;

public class ConversorDeColoresJava {

    private static final Map<String, Color> colors = new HashMap<>();

    static {
        colors.put("rojo", new Color(255, 0, 0));
        colors.put("rojo claro", new Color(255, 102, 102));
        colors.put("rojo oscuro", new Color(204, 0, 0));
        colors.put("verde", new Color(0, 255, 0));
        colors.put("verde claro", new Color(102, 255, 102));
        colors.put("verde oscuro", new Color(0, 204, 0));
        colors.put("azul", new Color(0, 0, 255));
        colors.put("azul claro", new Color(102, 102, 255));
        colors.put("azul oscuro", new Color(0, 0, 204));
        // ... and so on for all the colors
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Conversor de Colores");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Color:");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        JComboBox<String> colorCombo = new JComboBox<>(colors.keySet().toArray(new String[0]));
        colorCombo.setBounds(100, 10, 160, 25);
        panel.add(colorCombo);

        JTextField rgbField = new JTextField(20);
        rgbField.setBounds(100, 50, 160, 25);
        panel.add(rgbField);

        JTextField hexField = new JTextField(20);
        hexField.setBounds(100, 90, 160, 25);
        panel.add(hexField);

        JPanel colorPanel = new JPanel();
        colorPanel.setBounds(100, 130, 160, 100);
        panel.add(colorPanel);

        colorCombo.addActionListener(e -> {
            String selectedColor = (String) colorCombo.getSelectedItem();
            Color color = colors.get(selectedColor);

            rgbField.setText(color.getRed() + "," + color.getGreen() + "," + color.getBlue());
            hexField.setText("#" + Integer.toHexString(color.getRGB()).substring(2));

            colorPanel.setBackground(color);
        });

        JButton rgbCopyButton = new JButton("Copiar RGB");
        rgbCopyButton.setBounds(10, 240, 130, 25);
        panel.add(rgbCopyButton);

        rgbCopyButton.addActionListener(e -> {
            StringSelection selection = new StringSelection(rgbField.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        });

        JButton hexCopyButton = new JButton("Copiar Hexadecimal");
        hexCopyButton.setBounds(10, 270, 130, 25);
        panel.add(hexCopyButton);

        hexCopyButton.addActionListener(e -> {
            StringSelection selection = new StringSelection(hexField.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        });
    }
}
