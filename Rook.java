import java.awt.*;

/**
 * A rook chess piece
 */
public class Rook extends Piece {

    /**
    * Constructor
    */
    public Rook(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawRect(12,12,25,25);
    }
    
    @Override
    public boolean isValidMove (Piece[][] board, int[] current, int[] attempted) {
    	
    	if (current[0] == attempted[0] && current[1] == attempted[1]) {
    		return true; 
    	}
    	
    	Color destColor = board[attempted[1]][attempted[0]].getColor();
    	
    	// if the destination contains one of their own pieces 
    	if (destColor.equals(color)) {
    		return false; 
    	}
    	
    	// if the attempted movement was not horizontal or vertical 
    	if (!(current[1] == attempted[1] || current[0] == attempted[0])) {
    		return false; 
    	}
    	
    	boolean isHoriz = current[1] == attempted[1];
    	
    	
    	// if the destination is either empty or an oppponent's piece 
    	
    	//horizontal movement
    	if (isHoriz) {
    		boolean posTravel = (attempted[0] - current[0]) > 0;
    		if (posTravel) {
    			for (int i = current[0] + 1; i < attempted[0]; i++) {
        			if (!board[current[1]][i].isEmpty()) {
        				return false; 
        			}
        		}
    		} else { 
    			for (int i = attempted[0] + 1; i < current[0]; i++) {
        			if (!board[current[1]][i].isEmpty()) {
        				return false; 
        			}
        		}
    		}
    	//vertical movement 
    	} else {
    		boolean posTravel = (attempted[1] - current[1]) > 0;
    		if (posTravel) {
    			for (int i = current[1] + 1; i < attempted[1]; i++) {
        			if (!board[i][current[0]].isEmpty()) {
        				return false; 
        			}
        		}
    		} else { 
    			for (int i = attempted[1] + 1; i < current[1]; i++) {
        			if (!board[i][current[0]].isEmpty()) {
        				return false; 
        			}
        		}
    		}
    		
    	}
    	return true; 
    }
    
    @Override
    public String name () {
    	return "rook";
    }
}