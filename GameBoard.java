
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



/**
 * GameCourt
 * 
 * This class holds the primary game logic. 
 */
@SuppressWarnings("serial")
public class GameBoard extends JPanel {

    // the state of the game logic
	private Piece[][] board; 
    private Mode mode = null;

    boolean whitesTurn = true; // whether it is the white team's turn or not 
    private JLabel status; // Current status text - whose turn it is 
    

    /* Creating modes for selecting a piece to move and placing it on the board, updating the 
    state accordingly */ 
    
    interface Mode extends MouseListener {
    }
    
    class SelectMode extends MouseAdapter implements Mode {
    	public void mousePressed(MouseEvent e) {
    		Point p = e.getPoint();
    		int[] indices = translatePoint(p);
    		if (board[indices[1]][indices[0]].getColor().equals(Color.WHITE) && whitesTurn) {
    			mode = new PlaceMode(indices);
    		} else if (board[indices[1]][indices[0]].getColor().equals(Color.BLACK) && !whitesTurn){
    			mode = new PlaceMode(indices); 
    		} else {
    			return;
    		}
    	}
    }

    class PlaceMode extends MouseAdapter implements Mode {
    	int[] currInd;
    	
    	PlaceMode(int[] indices) {
			currInd = indices;
		}
    	
    	public void mousePressed(MouseEvent e) {
    		Point p = e.getPoint();
    		int[] attemptedInd = translatePoint(p);
    		if (board[currInd[1]][currInd[0]].isValidMove(board, currInd, attemptedInd)) {
    			Piece save = board[currInd[1]][currInd[0]];
    			board[currInd[1]][currInd[0]] = new Empty();
    			
    			boolean isKing = board[attemptedInd[1]][attemptedInd[0]].name().equals("king");
    			board[attemptedInd[1]][attemptedInd[0]] = save;
    			mode = new SelectMode(); 
    			repaint();
    			
    			if (isKing) {
    				if (whitesTurn) {
        				status.setText("White Wins! Click Reset To Play Again");
        			} else {
        				status.setText("Black Wins! Click Reset To Play Again"); 
        			}
    				return;
    			}
    			
    			whitesTurn = !whitesTurn;
    			if (whitesTurn) {
    				status.setText("White's Turn");
    			} else {
    				status.setText("Black's Turn"); 
    			}
    		} else {
    			return;
    		}
    	}
    }

    // Game constants
    public static final int COURT_WIDTH = 400;
    public static final int COURT_HEIGHT = 400;
    public static final int NUM_SPACES = 8;
    
    /* helper method - translates points from mouse clicks to the correct x-y index in the 2D array 
     of pieces */
    public int[] translatePoint(Point p) {
    	int[] indices = new int[2];
    	// reading clicks off the board (if window is resized) as being in last row 
    	indices[0] = (int) Math.min(7, (p.getX() / 50)); 
    	indices[1] = (int)  Math.min(7,(p.getY() / 50)); 
    	return indices;
    }


    // Constructor
    public GameBoard(JLabel status) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        
        addMouseListener(new MouseAdapter() {
        		public void mousePressed(MouseEvent e) {
        			mode.mousePressed(e);
        			repaint();
        		}
        });
        
        this.status = status; 
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
    	
    	whitesTurn = true; 
    	status.setText("White's Turn");
        this.mode = new SelectMode();
        
        // layout the game with appropriate pieces
        board = new Piece[NUM_SPACES][NUM_SPACES];
        
        	for (int j = 0; j < NUM_SPACES; j++) {
        		board[1][j] = new BlackPawn();
        	}
        
        	for (int j = 0; j < NUM_SPACES; j++) {
        		board[6][j] = new WhitePawn();
        	}
        	
        	board[0][4] = new King(Color.BLACK);
        	board[0][3] = new Queen(Color.BLACK); 
        	board[7][4] = new King(Color.WHITE);
        	board[7][3] = new Queen(Color.WHITE);
        	
        	board[0][2] = new Bishop(Color.BLACK);
        	board[0][5] = new Bishop(Color.BLACK); 
        	board[7][2] = new Bishop(Color.WHITE);
        	board[7][5] = new Bishop(Color.WHITE);
        	
        	board[0][1] = new Knight(Color.BLACK);
        	board[0][6] = new Knight(Color.BLACK); 
        	board[7][1] = new Knight(Color.WHITE);
        	board[7][6] = new Knight(Color.WHITE);
        	
        	
        	board[0][0] = new Rook(Color.BLACK);
        	board[0][7] = new Rook(Color.BLACK); 
        	board[7][0] = new Rook(Color.WHITE);
        	board[7][7] = new Rook(Color.WHITE);
        	
        
        for (int i = 2; i < 6; i++) {
        	for (int j = 0; j < NUM_SPACES; j++) {
        		board[i][j] = new Empty();
        	}
        } 
        
        repaint();
    	
    }
    
    
    /**
     * Set the game according to the information stored in a file that was read in.
     */
    public void set(Piece[][] setup, boolean whitesTurn) {
    	this.whitesTurn = whitesTurn;
    	board = setup;
    	if (whitesTurn) {
			status.setText("White's Turn");
		} else {
			status.setText("Black's Turn"); 
		}
    	repaint();
    }
    
    public Piece[][] getBoard() {
    	return board; 
    }
    
    public boolean getWhitesTurn() {
    	return whitesTurn; 
    }
    
    
    

    @Override
    public void paintComponent(Graphics g) {
    	// painting background board
    	
        super.paintComponent(g);
        for (int row = 0; row < NUM_SPACES; row+=2) {
        	g.setColor(Color.LIGHT_GRAY);
        	for (int i = 0; i < NUM_SPACES; i+= 2) {
        		g.fillRect((50 * i), (50 * row), 50, 50);
        	}
        	g.setColor(new Color(86,76,65));
        	for (int i = 1; i < NUM_SPACES; i+= 2) {
        		g.fillRect((50 * i), (50 * row), 50, 50);
        	}
        }
        for (int row = 1; row < NUM_SPACES; row+=2) {
        	g.setColor(new Color(86,76,65));
        	for (int i = 0; i < 8; i+= 2) {
        		g.fillRect((50 * i), (50 * row), 50, 50);
        	}
        	g.setColor(Color.LIGHT_GRAY);
        	for (int i = 1; i < NUM_SPACES; i+= 2) {
        		g.fillRect((50 * i), (50 * row), 50, 50);
        	}
        }
        
        // painting each piece 
        for (int i = 0; i < NUM_SPACES; i++) {
        	for (int j = 0; j < NUM_SPACES; j++) {
        		g.translate((j * 50), (i * 50)); 
        		board[i][j].draw(g);
        		g.translate(-(j * 50), -(i * 50));
        	}
        }
        
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}

