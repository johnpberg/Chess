
import java.awt.*;

/**
 * A white pawn chess piece
 */
public class WhitePawn extends Piece {
	
	public static final Color color = Color.WHITE; 

    public WhitePawn() {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(17,17,15,15);
    }
    
    @Override
    public boolean isValidMove(Piece[][] board, int[] current, int[] attempted) {
    	
    	if (current[0] == attempted[0] && current[1] == attempted[1]) {
    		return true; 
    	}
    	
    	Color destColor = board[attempted[1]][attempted[0]].getColor();
    	
    	// if the destination contains one of their own pieces 
    	if (destColor.equals(color)) {
    		return false; 
    	}
    	
    	// if it is an attempt to capture opponent's piece
    	if (destColor.equals(super.getOppositeColor())) {
        return ((Math.abs(attempted[0] - current[0]) == 1) && (attempted[1] - current[1] == -1));

    	}

    	// Only remaining situation: when the destination is empty 
    	if (current[0] != attempted[0]) {
    		return false; 
    	}
    	if (current[1] == 6) {
    		if ((attempted[1] - current[1] == -2)) {
    			return board[attempted[1] + 1][attempted[0]].getColor().equals(Color.GRAY);
    		}
    	}
    	return attempted[1] - current[1] == -1;
    }
    
    @Override
    public String name () {
    	return "whitepawn";
    }

}