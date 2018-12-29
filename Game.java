/**
 * Chess Game created by John P. Berg
 */

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

import javax.swing.*;


/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {

        // Top-level frame in which game components live
        final JFrame frame = new JFrame("CHESS");
        frame.setLocation(200, 100);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.NORTH);
        final JLabel status = new JLabel("White's Turn");
        status_panel.add(status);

        // Main playing area
        final GameBoard board = new GameBoard(status);
        frame.add(board, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.SOUTH);

        // Add an action listener to the reset button
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.reset();
            }
        });
        control_panel.add(reset);
        
     
        // Save button
        final JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// action listener saves the state of the GameBoard to a .txt file 
            	try {
            	PrintWriter out = new PrintWriter("files/lastgame.txt");
            	out.println(board.getWhitesTurn());
            	Piece[][] array = board.getBoard(); 
            	for (int i = 0; i < 8; i++) {
            		for (int j = 0; j < 8; j++) {
            			String name = array[i][j].name(); 
            			Color colorValue = array[i][j].getColor();
            			String color; 
            			if (colorValue.equals(Color.WHITE)) {
            				color = "white";
            			} else if (colorValue.equals(Color.BLACK)) {
            				color = "black";
            			} else {
            				color = "gray"; 
            			}
            			
            			out.println(name + "^" + color); 
            			
            		}
            	}
            	out.close();
            	} catch (Exception FileNotFoundException) {
            	  System.out.println("Unable to save game"); 	
            	}
            }
        });
        control_panel.add(save);
        
        // Load button
        final JButton load = new JButton("Load");
        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	/* action listener loads information form a .txt file to set up the state
            	of the GameBoard. If the .txt file is improperly formatted in any way, 
            	the board is simply set up in a new game.  */
            	Piece[][] setup = new Piece[8][8];
            	boolean whitesTurn = true;  
            	try {
            	BufferedReader in = new BufferedReader(new FileReader("files/lastgame.txt"));
            	String line = in.readLine();
            	if (line == null) {
            		board.reset();
            		in.close();
            		return; 
            	}
            	line = line.trim();
            	whitesTurn = line.equals("true");
            	for (int i = 0; i < 8; i++) {
            		for (int j = 0; j < 8; j++) {
            			String data = in.readLine(); 
            			if (data == null) {
            				board.reset();
            				in.close();
            				return;
            			}
            			data = data.trim();
            			int breakPoint = data.indexOf('^');
            			String name = data.substring(0, breakPoint);
            			name = name.trim(); 
            			String color = data.substring(breakPoint + 1, data.length());
            			color = color.trim();
            			Color colorValue; 
            			
            			if (color.equals("white")) {
            				colorValue = Color.WHITE;
            			} else if (color.equals("black")) {
            				colorValue =  Color.BLACK;	
            			} else if (color.equals("gray")) {
            				colorValue =  Color.GRAY;	
            			} else {
            				board.reset();
            				in.close();
            				return; 
            			}
            			
            			switch (name) {
            			case "blackpawn" : setup[i][j] = new BlackPawn(); break;
            			case "whitepawn" : setup[i][j] = new WhitePawn(); break;
            			case "king"      : setup[i][j] = new King(colorValue); break;
            			case "queen"     : setup[i][j] = new Queen(colorValue); break;
            			case "bishop"    : setup[i][j] = new Bishop(colorValue); break;
            			case "knight"    : setup[i][j] = new Knight(colorValue); break;
            			case "rook"      : setup[i][j] = new Rook(colorValue); break;
            			case "empty"     : setup[i][j] = new Empty(); break;
            			default : board.reset(); in.close(); return;
            			}
            			
            			
            		}
            	}
            	in.close();
            	
            	} catch (Exception FileNotFoundException) {
            		board.reset();
            		return;
            		
            	}
            	
            	
            	board.set(setup, whitesTurn);
            }
        });
        control_panel.add(load);
        
        
        

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        board.reset();
        
        
        // Instructions frame 
        
        // Top-level frame
        final JFrame frame2 = new JFrame("Instructions");
        frame2.setLocation(600, 100);

        // Status panel
        final JPanel text_panel = new JPanel();
        frame2.add(text_panel, BorderLayout.NORTH);
        final JTextArea text = new JTextArea("Instructions:\nThe game I implemented was a "
        		+ "simplified version of Chess.\nHow to play:\nUse the mouse to click on a piece"
        		+ " when it is your color's turn.\nThen click on the spot on the board where you "
        		+ "wish to move the piece.\nThe game will not do anything until you have selected "
        		+ "one \nof your own pieces and clicked on a valid location to move it\n(may only "
        		+ "be its own square if you have nowhere else to move).\n\nThe label will "
        		+ "display the color of the winner once the opposing king is captured.\n\n"
        		+ "To save the current game, click the save button\n"
        		+ "To load the last game you saved, click the load button\n"
        		+ "To reset the game, click the reset button");
        text_panel.add(text);

        // Put the frame on the screen
        frame2.pack();
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}