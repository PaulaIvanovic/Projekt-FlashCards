package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import views.AddCardsManual.ColorfulButtons;

public class AddCardsManual extends JFrame implements GlobalDesign{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	 private JTextField qInput, aInput;
	
	  ScreenDimensions dimensions = new ScreenDimensions();


	public AddCardsManual() {
		//set icon for app
    	java.net.URL IconURL = getClass().getResource("Pictures/AppIcon.png");
	    ImageIcon Icon = new ImageIcon(IconURL);
		setIconImage(Icon.getImage());
		
		//calculated variables for window height and width
	    int desiredHeight = (int) (dimensions.screenHeight * 0.435);
	    int desiredWidth = (int) (dimensions.screenWidth * 0.4);
		
	  
        // Set the custom title bar
        setUndecorated(true);
        setTitle("NEW CARD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, desiredWidth, desiredHeight);
        setResizable(false); 
        setLocationRelativeTo(null);
        setVisible(true);  
     
	    //main panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setBackground(backgroundColor);
		setLocationRelativeTo(null); //middle of the screen
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		
		//toolbar panel on top of the window
		JPanel toolbarPanel = new JPanel(new BorderLayout());
		toolbarPanel.setBackground(toolbarColor);
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
					
		//class for font size
		WindowElementResize.getFontForWindowSize(desiredHeight);
		
		//toolbar label (name of page)
		JLabel mainTitleLabel = new JLabel(" New card");      
		mainTitleLabel.setFont(WindowElementResize.mainTitle);
		mainTitleLabel.setForeground(Color.WHITE);
		toolbarPanel.add(mainTitleLabel, BorderLayout.WEST);
		
		// panel for buttons in toolbar
		JPanel tbPane = new JPanel();
		tbPane.setOpaque(false);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		tbPane.setLayout(flowLayout);
				
		//exit button in toolbar
		RoundButton exitButton = new RoundButton("",(int)(desiredWidth*0.05) ,(int)(desiredHeight*0.085));
		exitButton.setButtonIcon("icons/CloseIcon.png", (int)(desiredWidth*0.05), (int)(desiredHeight*0.085));
		exitButton.setBackground(toolbarColor);
		exitButton.setBorder(null);
		exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
		tbPane.add(exitButton);
		
		toolbarPanel.add(tbPane, BorderLayout.EAST);
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
		
		//panel for labels and buttons
        JPanel centerPanel = new JPanel();
        centerPanel.setBounds(0, 0, desiredWidth, desiredHeight);
        contentPane.add(centerPanel);
        centerPanel.setBackground(new Color(69, 62, 130));
        centerPanel.setLayout(null);
        
        //adding label for q
        JLabel lblQ = new JLabel("Question:");
        lblQ.setFont(secFont);
        lblQ.setForeground(Color.WHITE);
        lblQ.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.03), (int)(desiredWidth*0.3), (int)(desiredHeight*0.085));
        centerPanel.add(lblQ);
        
        //question input
        qInput = new RoundTextField(0);
        qInput.setFont(inputText);
        qInput.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.13), (int)(desiredWidth*0.9), (int)(desiredHeight*0.15));
        qInput.setText("Enter your question");
        
        //text inside of username field
        qInput.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (qInput.getText().equals("Enter your question")) {
                	qInput.setText(""); 
                }
            }

            public void focusLost(FocusEvent e) {
                if (qInput.getText().isEmpty()) {
                	qInput.setText("Enter your question");
                }
            }
        });
        centerPanel.add(qInput);
        
        //adding label for a
        JLabel lblA = new JLabel("Answer:");
        lblA.setFont(secFont);
        lblA.setForeground(Color.WHITE);
        lblA.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.30), (int)(desiredWidth*0.3), (int)(desiredHeight*0.085));
        centerPanel.add(lblA);
        
        //answer input
        aInput = new RoundTextField(0);
        aInput.setFont(inputText);
        aInput.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.40), (int)(desiredWidth*0.9), (int)(desiredHeight*0.2));
        aInput.setText("Enter your answer");
        
        //text inside of username field
        aInput.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (aInput.getText().equals("Enter your answer")) {
                	aInput.setText(""); 
                }
            }

            public void focusLost(FocusEvent e) {
                if (aInput.getText().isEmpty()) {
                	aInput.setText("Enter your answer");
                }
            }
        });
        centerPanel.add(aInput);
        
        //adding label for color
        JLabel lblColor = new JLabel("Color:");
        lblColor.setFont(secFont);
        lblColor.setForeground(Color.WHITE);
        lblColor.setBounds((int)(desiredWidth*0.03), (int)(desiredHeight*0.665), (int)(desiredWidth*0.3), (int)(desiredHeight*0.085));
        centerPanel.add(lblColor);
        
        //adding button for choosing color
        // Create an instance of ColorfulButtons
        ColorfulButtons colorfulButtons = new ColorfulButtons();
        colorfulButtons.setBounds((int)(desiredWidth*0.14), (int)(desiredHeight*0.65), (int)(desiredWidth*0.93), (int)(desiredHeight*0.17)); // Adjust the bounds as needed
        centerPanel.add(colorfulButtons);
        
        //add button to change color
        RoundedButton btnChange = new RoundedButton("Change color");
        btnChange.setFont(smallFont);
        btnChange.setForeground(Color.BLACK);
        btnChange.setBackground(new Color(248, 248, 255));
        btnChange.setBounds((int)(desiredWidth*0.24), (int)(desiredHeight*0.67), (int)(desiredWidth*0.25), (int)(desiredHeight*0.085));
        centerPanel.add(btnChange);
        
        
        // Add Save and Cancel buttons
        RoundedButton btnSave = new RoundedButton("Save");
        btnSave.setFont(smallFont);
        btnSave.setForeground(Color.BLACK);
        btnSave.setBackground(new Color(248, 248, 255));
        btnSave.setBounds((int)(desiredWidth*0.62), (int)(desiredHeight*0.76), (int)(desiredWidth*0.17), (int)(desiredHeight*0.085));
        centerPanel.add(btnSave);

        RoundedButton btnCancel = new RoundedButton("Cancel");
        btnCancel.setFont(smallFont);
        btnCancel.setForeground(Color.BLACK);
        btnCancel.setBackground(new Color(248, 248, 255));
        btnCancel.setBounds((int)(desiredWidth*0.8), (int)(desiredHeight*0.76), (int)(desiredWidth*0.17), (int)(desiredHeight*0.085));
        centerPanel.add(btnCancel);
		
	}
	
	
	class ColorfulButtons extends JPanel {

	    public ColorfulButtons() {
	        setLayout(new FlowLayout(FlowLayout.LEFT)); // Buttons will be aligned to the left
	        
	      //calculated variables for window height and width
		    int desiredHeight = (int) (dimensions.screenHeight * 0.435);
		    int desiredWidth = (int) (dimensions.screenWidth * 0.4);
			

	        // Array of colors for buttons
	        Color[] colors = {Color.WHITE};

	        // Create buttons with different colors
	        for (int i = 0; i < 1; i++) {
	            RoundButton button = new RoundButton(" ", (int)(desiredWidth*0.063), (int)(desiredHeight*0.103)); // Adjust button size as needed
	            button.setBackground(colors[i]);
	            button.addActionListener(new ButtonClickListener());
	            add(button);
	        }

	        setOpaque(false); // Make the panel transparent
	    }

	    private class ButtonClickListener implements ActionListener {
	        public void actionPerformed(ActionEvent e) {
	            RoundButton source = (RoundButton) e.getSource();
	            //JOptionPane.showMessageDialog(null, "You clicked a color button", "Button Clicked", JOptionPane.INFORMATION_MESSAGE);
	        }
	    }
	}
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCardsManual frame = new AddCardsManual();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}


