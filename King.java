
import java.awt.*;

/**
 * A king chess piece
 */
public class King extends Piece {

    /**
    * Constructor
    */
    public King(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
    	g.fillRect(10,10,30,30); 
    }
    
    @Override
    public boolean isValidMove (Piece[][] board, int[] current, int[] attempted) {
    	
    	// king combines rook and bishop movement but can only move one square in any direction
    	Piece rookMovement = new Rook(this.color); 
    	Piece bishopMovement = new Bishop(this.color);
    	boolean properDirection = (rookMovement.isValidMove(board, current, attempted) || 
                                             bishopMovement.isValidMove(board, current, attempted));
    	
    	int xDist = Math.abs(attempted[0] - current[0]);
    	int yDist = Math.abs(attempted[1] - current[1]);
    	boolean maxDist = (xDist <= 1) && (yDist <= 1);
    	
    	return (properDirection && maxDist); 
    }
    
    @Override
    public String name () {
    	return "king";
    }
}
