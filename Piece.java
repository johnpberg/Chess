
import java.awt.Color;
import java.awt.Graphics;

/** 
 * An piece in the chess game.  
 *
 *Each piece has a color, methods for getting this color and the opposite one, and whether the 
 *piece is empty or not. 
 *Each subtype of Piece must implement methods name, isValidMove, and draw. 
 */
public abstract class Piece {
    
    // white or black if a piece, gray if Empty piece 
    protected Color color; 


    
    public Piece(Color color) {
    	this.color = color; 
  
    }

    public Color getColor() {
    	return this.color;
    }
    
    public Color getOppositeColor() {
    	if (this.color.equals(Color.WHITE)) {
    		return Color.BLACK;
    	} else if (this.color.equals(Color.BLACK)) {
    		return Color.WHITE;
    	} else {
    	return Color.GRAY;
    	}
    }
    
    public boolean isEmpty() {
    	return (this.color.equals(Color.GRAY));
    }


    public abstract void draw(Graphics g);
    
    public abstract boolean isValidMove (Piece[][] board, int[] currentIndices, 
    		                                                                int[] attemptedIndices);
    
    public abstract String name ();
}