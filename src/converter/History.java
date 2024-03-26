package converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
public class History extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8287924598577793003L;
	public static DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> list;
    
    // Method to add a new conversion to the list
    public static void addConversion(String newConversion) {
        listModel.addElement(newConversion);
    }
    public History(GUI gui) {
        setTitle("History");
        setSize(476, 200);
        setResizable(false);
        Dimension mainWindowSize = gui.getSize();
        int extraWindowY = gui.getLocation().y + mainWindowSize.height;
        setLocation(gui.getLocation().x, extraWindowY);; // Center the frame

        setUndecorated(true);
        // Create and add content to the window
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        list = new JList<>(listModel);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList<?> list = (JList<?>)evt.getSource();
                if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {

                    // Double-click detected (only left clicks)
                    int index = list.locationToIndex(evt.getPoint());
                    String selectString = listModel.get(index);
                    StringSelection selection = new StringSelection(selectString);
                    clipboard.setContents(selection, null);
                    listModel.setElementAt(selectString+" copied!", index);
                }
            }
        });
        // Add the JList to a JScrollPane to enable scrolling
        JScrollPane scrollPane = new JScrollPane(list);

        // Add the scroll pane to the center of the content pane
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        setContentPane(contentPane);
        setVisible(true); // Make the window visible
    }
    

}