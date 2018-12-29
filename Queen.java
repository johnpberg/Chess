
import java.awt.*;

/**
 * A queen chess piece
 */
public class Queen extends Piece {

    /**
    * Constructor
    */
    public Queen(Color color) {
        super(color);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
    	g.fillOval(10,10,30,30); 
    }
    
    @Override
    public boolean isValidMove (Piece[][] board, int[] current, int[] attempted) {
    	
    	// A queen combines the movement of a rook and bishop
    	Piece rookMovement = new Rook(this.color); 
    	Piece bishopMovement = new Bishop(this.color);
    	return (rookMovement.isValidMove(board, current, attempted) || 
    			                             bishopMovement.isValidMove(board, current, attempted));
    }
    
    @Override
    public String name () {
    	return "queen";
    }
}
