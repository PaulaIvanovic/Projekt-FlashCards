package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class GroupOfCardsPage extends JFrame implements GlobalDesign {
	private static final long serialVersionUID = 1L;	
	private JPanel contentPane;

    public GroupOfCardsPage() {
        this.setTitle("GROUP OF CARDS PAGE");
        this.setSize(900, 700);
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
		editButton.setButtonIcon("edit.png");
		editButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		editButton.setForeground(backgroundColor);
		buttonPanel.add(editButton);

		//add new group button in toolbar
		RoundButton addGroupButton = new RoundButton("", 35, 35);
		addGroupButton.setButtonIcon("add.png");
		addGroupButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		addGroupButton.setForeground(backgroundColor);
		buttonPanel.add(addGroupButton);
		
		//settings button in toolbar
		RoundButton settingsButton = new RoundButton("", 35, 35);
		settingsButton.setButtonIcon("settings.png");
		settingsButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		settingsButton.setForeground(backgroundColor);
		buttonPanel.add(settingsButton);
		
		//user icon / button in toolbar
		RoundButton userIcon = new RoundButton("", 50, 50);
		userIcon.setButtonIcon("user.png");
		userIcon.setFont(new Font("Tahoma", Font.BOLD, 10));
		userIcon.setForeground(backgroundColor);
		buttonPanel.add(userIcon);
		
		toolbarPanel.add(buttonPanel, BorderLayout.EAST);
		 
		//rectangles representing groups of cards
        DrawGroupRectangles drawRectangles = new DrawGroupRectangles();
        contentPane.add(drawRectangles);

        // mouse listener to detect when a group rectangle is clicked
        drawRectangles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            	int clickedIndex = drawRectangles.getClickedRectangleIndex(e.getX(), e.getY());
                if (clickedIndex != -1) {
                    //opens a new window with the corresponding group name
                    new GroupPage(groupNames[clickedIndex], groupColors[clickedIndex] ).setVisible(true);
                }           
            }
        });
    }

    //class to draw group rectangles (5 rectangles in 1 row)
    class DrawGroupRectangles extends JComponent {
        private final int RECTANGLES_PER_ROW = 5;
        private final int RECT_WIDTH = 230;
        private final int RECT_HEIGHT = 140;
        private final int GAP = 50;
        private final int ARC_WIDTH = 30;
        private final int ARC_HEIGHT = 30;
        private final int START_X = 100;
        private final int START_Y = 150;

        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;

            Font font = secFont;
            g2.setFont(font);

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < RECTANGLES_PER_ROW; j++) {
                    int x = START_X + j * (RECT_WIDTH + GAP);
                    int y = START_Y + i * (RECT_HEIGHT + GAP);
                    
                    int index = i * RECTANGLES_PER_ROW + j;		//index of groupName
                    
                    //to make the rectangles have rounded edges
                    RoundRectangle2D roundedRectangle = new RoundRectangle2D.Double(x, y, RECT_WIDTH, RECT_HEIGHT, ARC_WIDTH, ARC_HEIGHT);

                    g2.setColor(groupColors[index]);
                    g2.fill(roundedRectangle);

                    g2.setColor(Color.BLACK);

                    String text = groupNames[index];
                    drawCenteredString(g2, text, x, y, RECT_WIDTH, RECT_HEIGHT);
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
        
        //gets index of clicked group, to use it when displaying group name in new window 
        //makes sure mouse is inside rectangle coordinates (outside clicks don't count)
        public int getClickedRectangleIndex(int mouseX, int mouseY) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < RECTANGLES_PER_ROW; j++) {
                    int x = START_X + j * (RECT_WIDTH + GAP);
                    int y = START_Y + i * (RECT_HEIGHT + GAP);

                    if (mouseX >= x && mouseX <= x + RECT_WIDTH && mouseY >= y && mouseY <= y + RECT_HEIGHT) {
                        return i * RECTANGLES_PER_ROW + j;
                    }
                }
            }
            return -1; // if clicked outside the rectangles
        }
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
