package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;


public class GroupOfCardsPage extends JFrame implements GlobalDesign {
	private static final long serialVersionUID = 1L;	
	private JPanel contentPane;
	
	private final int ARC_WIDTH = 30;
    private final int ARC_HEIGHT = 30;
    
    private final int START_X = 80;
    private final int START_Y = 150;
    
    private final int HORIZONTAL_GAP_PERCENTAGE = 3; 
    private final int VERTICAL_GAP_PERCENTAGE = 3; 
    
    private final int MAX_RECTANGLE_HEIGHT = 180;
    private final int MAX_RECTANGLE_WIDTH = 320;
	
	private int currentPage = 0;
	private int groupsPerPage = 10;

    public GroupOfCardsPage() {
        this.setTitle("GROUP OF CARDS PAGE");
        this.setSize(900, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);	//to center the frame on screen
        
        //main panel
        contentPane = new JPanel();
        contentPane.setBackground(backgroundColor);		
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		//toolbar on top of the window:
		JPanel toolbarPanel = new JPanel(new BorderLayout());
		toolbarPanel.setBackground(toolbarColor);
		toolbarPanel.setBounds(0, 0, getWidth(), 80);
		toolbarPanel.setLayout(new BorderLayout());
		contentPane.add(toolbarPanel, BorderLayout.NORTH);
		
		//toolbar label (name of page)
		JLabel mainTitleLabel = new JLabel("My groups of cards");      
		mainTitleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		mainTitleLabel.setForeground(Color.WHITE);
		mainTitleLabel.setBorder(new EmptyBorder(15, 15, 15, 15));
		toolbarPanel.add(mainTitleLabel, BorderLayout.WEST);
		
		// panel with all required buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT);
		flowLayout.setHgap(10); 
		buttonPanel.setLayout(flowLayout);

		//edit button in toolbar
		RoundButton editButton = new RoundButton("", 35, 35);
		editButton.setButtonIcon("edit.png", 27, 27);
		editButton.setForeground(backgroundColor);
		buttonPanel.add(editButton);

		//add new group button in toolbar
		RoundButton addGroupButton = new RoundButton("", 35, 35);
		addGroupButton.setButtonIcon("add.png", 27, 27);
		addGroupButton.setForeground(backgroundColor);
		buttonPanel.add(addGroupButton);
		
		//settings button in toolbar
		RoundButton settingsButton = new RoundButton("", 35, 35);
		settingsButton.setButtonIcon("settings.png", 27, 27);
		settingsButton.setForeground(backgroundColor);
		buttonPanel.add(settingsButton);
		
		//user icon / button in toolbar
		RoundButton userIcon = new RoundButton("", 50, 50);
		userIcon.setButtonIcon("user.png", 27, 27);
		userIcon.setForeground(backgroundColor);
		buttonPanel.add(userIcon);
		
		toolbarPanel.add(buttonPanel, BorderLayout.EAST);
		 
		//rectangles representing groups of cards
        DrawGroupRectangles drawRectangles = new DrawGroupRectangles();
        contentPane.add(drawRectangles, BorderLayout.CENTER);
        
        
        //panel with buttons to navigate through pages
        JPanel navigationPanel = new JPanel();
        navigationPanel.setOpaque(false);
		FlowLayout navFlowLayout = new FlowLayout(FlowLayout.CENTER);
		navFlowLayout.setHgap(10); 
		navigationPanel.setLayout(navFlowLayout);
		
        RoundButton arrowLeft = new RoundButton("", 40, 40);
        arrowLeft.setButtonIcon("left.png", 25, 25);
        arrowLeft.setForeground(Color.black);
        navigationPanel.add(arrowLeft);
        
        JLabel currentPageLabel = new JLabel("1");
        currentPageLabel.setFont(secFont);
        currentPageLabel.setForeground(Color.black);
        navigationPanel.add(currentPageLabel);
        
        RoundButton arrowRight = new RoundButton("", 35, 35);
        arrowRight.setButtonIcon("right.png", 25, 25);
        arrowRight.setForeground(Color.black);
        navigationPanel.add(arrowRight);
        
        contentPane.add(navigationPanel, BorderLayout.SOUTH);

        //mouse listener to detect when a group rectangle is clicked
        drawRectangles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickedIndex = getClickedRectangleIndex(e.getX(), e.getY());
                if (clickedIndex != -1) {
                    // Opens a new window with the corresponding group name
                    new GroupPage(groupNames[clickedIndex], groupColors[clickedIndex]).setVisible(true);
                }
            }
        });
        
        
        //mouse listeners for arrows (navigation through pages):
        arrowLeft.addActionListener(e -> {
            if (currentPage > 0) {
                currentPage--;
                currentPageLabel.setText(Integer.toString(currentPage + 1));
                drawRectangles.repaint(); // Repaint the component to reflect the updated current page
            }
        });
        
        arrowRight.addActionListener(e -> {
            int totalPages = (int) Math.ceil((double) groupNames.length / groupsPerPage );
            if (currentPage < totalPages - 1) {
                currentPage++;
                currentPageLabel.setText(Integer.toString(currentPage + 1));
                drawRectangles.repaint(); // Repaint the component to reflect the updated current page
            }
        });
        
        
    }

    class DrawGroupRectangles extends JComponent {
        
        
        

        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(backgroundColor);
            g2.fillRect(0, 0, getWidth(), getHeight());

            Font font = secFont;
            g2.setFont(font);

            int frameWidth = getWidth();
            int frameHeight = getHeight();
            
            int horizontalGap = (int) (frameWidth * HORIZONTAL_GAP_PERCENTAGE / 100.0);
            int verticalGap = (int) (frameHeight * VERTICAL_GAP_PERCENTAGE / 100.0);


            // Calculate the index range for the current page
            int startIndex = currentPage * groupsPerPage;
            int endIndex = Math.min(startIndex + groupsPerPage, groupNames.length);

            // Calculate the number of rows and columns for the current page
            int numCols = Math.min(groupNames.length - startIndex, 5);
            int numRows = (numCols - 1) / 5 + 1;

            // Calculate the width and height of each rectangle
            int rectangleWidth = Math.min((frameWidth - START_X * 2 - (numCols - 1) * horizontalGap) / numCols, MAX_RECTANGLE_WIDTH);
            int rectangleHeight = Math.min((frameHeight - START_Y * 2 - (numRows - 1) * verticalGap) / numRows, MAX_RECTANGLE_HEIGHT);


            for (int i = startIndex; i < endIndex; i++) {
                int row = (i - startIndex) / 5;
                int col = (i - startIndex) % 5;

                int x = START_X + col * (rectangleWidth + horizontalGap);
                int y = START_Y + row * (rectangleHeight + verticalGap);

                // To make the rectangles have rounded edges
                RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(x, y, rectangleWidth, rectangleHeight, ARC_WIDTH, ARC_HEIGHT);

                g2.setColor(groupColors[i]);
                g2.fill(roundedRectangle);

                g2.setColor(Color.BLACK);

                String text = groupNames[i];
                drawCenteredString(g2, text, x, y, rectangleWidth, rectangleHeight);
               }     
        	}
        }

        //writes out name of group (centered within the rectangle)
        private void drawCenteredString(Graphics2D g2, String text, int x, int y, int width, int height) {
            FontMetrics metrics = g2.getFontMetrics();
            int textWidth = metrics.stringWidth(text);
            int textHeight = metrics.getHeight();
            int centerX = x + (width - textWidth) / 2;
            int centerY = y + (height - textHeight) / 2 + metrics.getAscent();
            g2.drawString(text, centerX, centerY);
        }

      // gets index of clicked group, to use it when displaying group name in new window
      // makes sure mouse is inside rectangle coordinates (outside clicks don't count)
      private int getClickedRectangleIndex(int mouseX, int mouseY) {
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int horizontalGap = (int) (frameWidth * HORIZONTAL_GAP_PERCENTAGE / 100.0);
        int verticalGap = (int) (frameHeight * VERTICAL_GAP_PERCENTAGE / 100.0);

        // Calculate the index range for the current page
        int startIndex = currentPage * groupsPerPage;
        int endIndex = Math.min(startIndex + groupsPerPage, groupNames.length);

        // Calculate the number of rows and columns for the current page
       int numCols = Math.min(groupNames.length - startIndex, 5);
       int numRows = (numCols - 1) / 5 + 1;

       // Calculate the width and height of each rectangle
        int rectangleWidth = Math.min((frameWidth - START_X * 2 - (numCols - 1) * horizontalGap) / numCols, MAX_RECTANGLE_WIDTH);
        int rectangleHeight = Math.min((frameHeight - START_Y * 2 - (numRows - 1) * verticalGap) / numRows, MAX_RECTANGLE_HEIGHT);

        for (int i = startIndex; i < endIndex; i++) {
        	int row = (i - startIndex) / 5;
        	int col = (i - startIndex) % 5;

        	int x = START_X + col * (rectangleWidth + horizontalGap);
        	int y = START_Y + row * (rectangleHeight + verticalGap);

        	if (mouseX >= x && mouseX <= x + rectangleWidth && mouseY >= y && mouseY <= y + rectangleHeight) {
        	    return i;
        	}
        }
       return -1; // if clicked outside the rectangles
     }
        	


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GroupOfCardsPage frame = new GroupOfCardsPage();
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
