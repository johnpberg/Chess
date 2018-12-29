
import java.awt.*;

/**
 * An "empty" chess piece -- an empty slot on the Chess board (Piece[][])
 */
public class Empty extends Piece {
    public static final Color color = Color.GRAY; 

    public Empty() {
        super(color);
    }


    @Override
    public void draw(Graphics g) {

    }
    
    @Override
    public boolean isValidMove (Piece[][] board, int[] currentIndices, 
            int[] attemptedIndices) {
    	return false;
    }
    
    @Override
    public String name () {
    	return "empty";
    }
}
