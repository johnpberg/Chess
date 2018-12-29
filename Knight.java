import java.awt.*;

/**
 * A knight chess piece
 */
public class Knight extends Piece {

    /**
    * Constructor
    */
    public Knight(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(17,10,15,30);
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
    	
    	
    	// if the move meets the Knight's ratio of 2 to 1 
    	
    	int xRatio = Math.abs(attempted[0] - current[0]);
    	int yRatio = Math.abs(attempted[1] - current[1]); 
    	
    	if (xRatio == 2 && yRatio == 1) {
    		return true; 
    	}
    	if (xRatio == 1 && yRatio == 2) {
    		return true; 
    	}
    	
    	return false; 

    }	
    
    @Override
    public String name () {
    	return "knight";
    }
   
}
    