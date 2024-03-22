package converter;

import java.awt.EventQueue;
import javax.swing.UIManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import mdlaf.MaterialLookAndFeel;
import io.materialtheme.darkstackoverflow.DarkStackOverflowTheme;
import mdlaf.utils.*;


import javax.swing.JComboBox;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JTextField;

import java.text.DecimalFormat;



import calculations.Calcs;


import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;



import java.awt.Color;
import java.awt.Dimension;


public class GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private Map<String, ArrayList<String>> conversionMap;
    private JComboBox<String> combo1; // Change JComboBox to specify type String
    private JComboBox<String> combo2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	JFrame.setDefaultLookAndFeelDecorated(true);
                	MaterialLookAndFeel material = new MaterialLookAndFeel(new DarkStackOverflowTheme());
                	UIManager.put("TitlePane.background", MaterialColors.BLACK);
                    UIManager.setLookAndFeel(material);
                    GUI frame = new GUI(); 
                    frame.setVisible(true);
                    frame.setMinimumSize(new Dimension(476, 83));
                    frame.pack();
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    @SuppressWarnings("serial")
    public GUI() {
    	setFont(new Font("PragmataPro Mono Liga", Font.PLAIN, 14));
    	setBackground(new Color(0, 255, 255));
    	setTitle("Unit Converter");
    	setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 476, 83);
        contentPane = new JPanel();

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        textField = new JTextField();
        textField.setFont(new Font("PragmataPro Mono Liga", Font.PLAIN, 14));
        textField.setBounds(10, 15, 86, 20);
        contentPane.add(textField);
        textField.setColumns(10);
        
        combo1 = new JComboBox<String>();
        combo1.setFont(new Font("PragmataPro Mono Liga", Font.PLAIN, 14));
        combo1.setBounds(106, 15, 72, 22);
        contentPane.add(combo1);
        combo1.addItem("mph");
        combo1.addItem("kmh");
        combo1.addItem("F");
        combo1.addItem("C");
        combo1.addItem("g");
        combo1.addItem("oz");
        combo1.addItem("fl. oz");
        combo1.addItem("ml");
        combo1.addItem("gal");
        combo1.addItem("L");
        
        combo2 = new JComboBox<String>(); 
        combo2.setFont(new Font("PragmataPro Mono Liga", Font.PLAIN, 14));
        combo2.setBounds(363, 15, 87, 22);
        contentPane.add(combo2);
        
        JLabel lblRes = new JLabel("0.000");
        lblRes.setFont(new Font("PragmataPro Mono Liga", Font.PLAIN, 14));
        lblRes.setHorizontalAlignment(SwingConstants.RIGHT);
        lblRes.setBounds(243, 15, 110, 20);
        contentPane.add(lblRes);
        
        conversionMap = new HashMap<>();
        conversionMap.put("mph", new ArrayList<String>() {{
        	add("kmh"); 
        }});
        conversionMap.put("kmh", new ArrayList<String>() {{
        	add("mph"); 
        }});
        conversionMap.put("F", new ArrayList<String>() {{
        	add("C"); 
        }});
        conversionMap.put("C", new ArrayList<String>() {{
        	add("F"); 
        }});
        conversionMap.put("g", new ArrayList<String>() {{
        	add("oz"); 
        }});
        conversionMap.put("oz", new ArrayList<String>() {{
        	add("g"); 
        }});
        conversionMap.put("fl. oz", new ArrayList<String>() {{
        	add("ml"); 
        	add("L");
        }});
        conversionMap.put("ml", new ArrayList<String>() {{
        	add("fl. oz"); 
        	add("gal"); 
        }});
        conversionMap.put("gal", new ArrayList<String>() {{
        	add("ml"); 
        	add("L");
        }});
        conversionMap.put("L", new ArrayList<String>() {{
        	add("fl. oz"); 
        	add("gal");
        }});
        // Set the selected index of combo1 to 0 (first item)
        combo1.setSelectedIndex(0);
        
        JButton btnConvert = new JButton("->");
        btnConvert.setFont(new Font("PragmataPro Mono Liga", Font.PLAIN, 14));
        btnConvert.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String fromUnit = (String) combo1.getSelectedItem();
                String toUnit = (String) combo2.getSelectedItem();
                double inputValue = Double.parseDouble(textField.getText());
                double result;
                
                result = convertUnits(inputValue, fromUnit, toUnit);
                
                DecimalFormat df = new DecimalFormat("#.###");
                String formattedResult = df.format(result);
                // Display the result or do further processing
                // For example, you can set the result in another text field
                lblRes.setText(formattedResult);
        	}
        });
        btnConvert.setBounds(188, 15 , 45, 23);
        contentPane.add(btnConvert);

        // update combo2 based on the default selection of combo1
        updateUnitComboBox2();
        combo1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUnitComboBox2();
            }
        });
        getRootPane().setDefaultButton(btnConvert);
        contentPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
    }
    private double convertUnits(double inputValue, String fromUnit, String toUnit) {
    	double result;
    	if (fromUnit.equals("kmh") && toUnit.equals("mph")) {
            result = Calcs.kmhToMph(inputValue);
        } else if (fromUnit.equals("mph") && toUnit.equals("kmh")) {
            result = Calcs.mphToKmh(inputValue);
        } else if (fromUnit.equals("C") && toUnit.equals("F")) {
            result = Calcs.cToF(inputValue);
        } else if (fromUnit.equals("F") && toUnit.equals("C")) {
            result = Calcs.fToC(inputValue);
        } else if (fromUnit.equals("g") && toUnit.equals("oz")) {
            result = Calcs.gToOz(inputValue);
        } else if (fromUnit.equals("oz") && toUnit.equals("g")) {
            result = Calcs.ozToG(inputValue);
        } else if (fromUnit.equals("ml") && toUnit.equals("fl. oz")) {
            result = Calcs.mlToFloz(inputValue);
        } else if (fromUnit.equals("ml") && toUnit.equals("gal")) {
            result = Calcs.mlToGal(inputValue);
        } else if (fromUnit.equals("fl. oz") && toUnit.equals("ml")) {
            result = Calcs.flozToMl(inputValue);
        } else if (fromUnit.equals("fl. oz") && toUnit.equals("L")) {
            result = Calcs.flozToMl(inputValue)/1000;
        } else if (fromUnit.equals("gal") && toUnit.equals("ml")) {
            result = Calcs.galToMl(inputValue);
        } else if (fromUnit.equals("gal") && toUnit.equals("L")) {
            result = Calcs.galToMl(inputValue)/1000;
        } else if (fromUnit.equals("L") && toUnit.equals("fl. oz")) {
            result = Calcs.mlToFloz(inputValue)*1000;
        } else if (fromUnit.equals("L") && toUnit.equals("gal")) {
            result = Calcs.mlToGal(inputValue)*1000;
        } else {
            // Handle other conversions
            result = 0.0; // Default value or error handling
        }
		return result;
	}

	private void updateUnitComboBox2() {
        combo2.removeAllItems();
        String selectedUnit = (String) combo1.getSelectedItem();
        if (selectedUnit != null && conversionMap.containsKey(selectedUnit)) {
            ArrayList<String> compatibleUnits = conversionMap.get(selectedUnit);
            for (String unit : compatibleUnits) {
                combo2.addItem(unit);
            }
        }
    }
}
