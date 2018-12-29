
import java.awt.*;

/**
 * A Bishop chess piece 
 */
public class Bishop extends Piece {

    /**
    * Constructor
    */
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
    	g.fillOval(17,17,15,15); 
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
    	
    	// if the attempted movement was not diagonal
    	int xDist = attempted[0] - current[0];
    	int yDist = attempted[1] - current[1];
    	int absXDist = Math.abs(xDist);
    	int absYDist = Math.abs(yDist);
    	
    	if ((absXDist / absYDist) != 1 ) {
    		return false; 
    	}
    	
    	
    	// positive slope in terms of the top-left origin coordinate system -- appears negative 
    	// slope in terms of normal Cartesian system 
    	boolean isPositiveSlope = ((xDist > 0) && (yDist > 0)) || ((xDist < 0) && (yDist < 0)) ;
    	
    	
    	// if the destination is either empty or an oppponent's piece 
    	
    	// movement along a line with a positive slope 
    	if (isPositiveSlope) {
    		boolean posTravel = (attempted[0] - current[0]) > 0;
    		if (posTravel) {
    			for (int i = current[0] + 1, j = current[1] + 1 ; i < attempted[0]; i++, j++) {
        			if (!board[j][i].isEmpty()) {
        				return false; 
        			}
        		}
    		} else { 
    			for (int i = attempted[0] + 1, j = attempted[1] + 1; i < current[0]; i++, j++) {
        			if (!board[j][i].isEmpty()) {
        				return false; 
        			}
        		}
    		}
    	// movement along a line with a negative slope
    	} else {
    		boolean posTravel = (attempted[0] - current[0]) > 0;
    		if (posTravel) {
    			for (int i = current[0] + 1, j = current[1] - 1 ; i < attempted[0]; i++, j--) {
        			if (!board[j][i].isEmpty()) {
        				return false; 
        			}
        		}
    		} else { 
    			for (int i = attempted[0] + 1, j = attempted[1] - 1; i < current[0]; i++, j--) {
        			if (!board[j][i].isEmpty()) {
        				return false; 
        			}
        		}
    		}
    		
    	}
    	return true; 
    }
    
    
    @Override
    public String name () {
    	return "bishop";
    }
}