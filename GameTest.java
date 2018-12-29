/**
 * By John P. Berg
 * 
 * JUnit Tests for isValidMove/ Piece[][] state
 * 
 * 
 * The testable component of my game that I will be using JUnit tests on is the Piece[][] within 
 * the GameBoard board that represents which pieces occupy which slots on the chess board. 
 * 
 * Testing changes in this state boils down to testing the "isValidMove" function for each
 *  type of pieces for every situation they could encounter, as the state is directly updated
 *  based on the result of this method. 
 */

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Color;

import javax.swing.JLabel;

public class GameTest {
	
	private Piece[][] array;
	
	@Before
    public void setUp() {
		final JLabel status = new JLabel("White's Turn");
        GameBoard board = new GameBoard(status); 
        board.reset(); 
        array = board.getBoard(); 
    }
	
	// White Pawn
	
	@Test
	public void testWhitePawnLegalMoveOne () {
		Piece pawn = array[6][0];
		int[] currentPos = {0, 6};
		int[] attemptedPos = {0, 5};
		
		assertTrue(pawn.isValidMove(array, currentPos, attemptedPos));
	}
	
	@Test
	public void testWhitePawnLegalMoveTwo() {
		Piece pawn = array[6][0];
		int[] currentPos = {0, 6};
		int[] attemptedPos = {0, 4};
		
		assertTrue(pawn.isValidMove(array, currentPos, attemptedPos));
	}
	
	@Test
	public void testWhitePawnIllegalMoveTwo() {
		array[6][0] = new Empty();
		array[5][0] = new WhitePawn(); 
		Piece pawn = array[5][0];
		int[] currentPos = {0, 5};
		int[] attemptedPos = {0, 3};
		
		assertFalse(pawn.isValidMove(array, currentPos, attemptedPos));
	}
	
	
	@Test
	public void testWhitePawnIllegalMoveOneSameTeam() {
		array[5][0] = new WhitePawn(); 
		Piece pawn = array[6][0];
		int[] currentPos = {0, 6};
		int[] attemptedPos = {0, 5};
		
		assertFalse(pawn.isValidMove(array, currentPos, attemptedPos));
	}
	
	@Test
	public void testWhitePawnIllegalMoveTwoSameTeam() {
		array[4][0] = new WhitePawn(); 
		Piece pawn = array[6][0];
		int[] currentPos = {0, 6};
		int[] attemptedPos = {0, 4};
		
		assertFalse(pawn.isValidMove(array, currentPos, attemptedPos));
	}
	
	@Test
	public void testWhitePawnTakeOpponentPiece() {
		array[5][1] = new BlackPawn();
		Piece pawn = array[6][0];
		int[] currentPos = {0, 6};
		int[] attemptedPos = {1, 5};
		
		assertTrue(pawn.isValidMove(array, currentPos, attemptedPos));
		
	}
	
	@Test
	public void testWhitePawnIllegalTakeOpponentPiece() {
		array[5][0] = new BlackPawn();
		Piece pawn = array[6][0];
		int[] currentPos = {0, 6};
		int[] attemptedPos = {0, 5};
		
		assertFalse(pawn.isValidMove(array, currentPos, attemptedPos));
		
	}
	
	@Test
	public void testWhitePawnIllegalTakeOpponentPieceTwo() {
		array[4][0] = new BlackPawn();
		Piece pawn = array[6][0];
		int[] currentPos = {0, 6};
		int[] attemptedPos = {0, 4};
		
		assertFalse(pawn.isValidMove(array, currentPos, attemptedPos));
		
	}
	
	
	
	// Black Pawn 
	
	@Test
	public void testBlackPawnLegalMoveOne () {
		Piece pawn = array[1][0];
		int[] currentPos = {0, 1};
		int[] attemptedPos = {0, 2};
		
		assertTrue(pawn.isValidMove(array, currentPos, attemptedPos));
	}
	
	@Test
	public void testBlackPawnLegalMoveTwo() {
		Piece pawn = array[1][0];
		int[] currentPos = {0, 1};
		int[] attemptedPos = {0, 3};
		
		assertTrue(pawn.isValidMove(array, currentPos, attemptedPos));
	}
	
	@Test
	public void testBlackPawnIllegalMoveTwo() {
		array[1][0] = new Empty();
		array[2][0] = new BlackPawn(); 
		Piece pawn = array[2][0];
		int[] currentPos = {0, 2};
		int[] attemptedPos = {0, 4};
		
		assertFalse(pawn.isValidMove(array, currentPos, attemptedPos));
	}
	
	
	@Test
	public void testBlackPawnIllegalMoveOneSameTeam() {
		array[2][0] = new BlackPawn(); 
		Piece pawn = array[1][0];
		int[] currentPos = {0, 1};
		int[] attemptedPos = {0, 2};
		
		assertFalse(pawn.isValidMove(array, currentPos, attemptedPos));
	}
	
	@Test
	public void testBlackPawnIllegalMoveTwoSameTeam() {
		array[3][0] = new BlackPawn(); 
		Piece pawn = array[1][0];
		int[] currentPos = {0, 1};
		int[] attemptedPos = {0, 3};
		
		assertFalse(pawn.isValidMove(array, currentPos, attemptedPos));
	}
	
	@Test
	public void testBlackPawnTakeOpponentPiece() {
		array[2][1] = new WhitePawn();
		Piece pawn = array[1][0];
		int[] currentPos = {0, 1};
		int[] attemptedPos = {1, 2};
		
		assertTrue(pawn.isValidMove(array, currentPos, attemptedPos));
		
	}
	
	@Test
	public void testBlackPawnIllegalTakeOpponentPiece() {
		array[2][0] = new WhitePawn();
		Piece pawn = array[1][0];
		int[] currentPos = {0, 1};
		int[] attemptedPos = {0, 2};
		
		assertFalse(pawn.isValidMove(array, currentPos, attemptedPos));
		
	}
	
	@Test
	public void testBlackPawnIllegalTakeOpponentPieceTwo() {
		array[3][0] = new WhitePawn();
		Piece pawn = array[1][0];
		int[] currentPos = {0, 1};
		int[] attemptedPos = {0, 3};
		
		assertFalse(pawn.isValidMove(array, currentPos, attemptedPos));
		
	}
	
	// Rook
	
	// legal vertical
	@Test
	public void testRookLegalVertical() {
		array[6][0] = new Empty(); 
		Piece rook = array[7][0];
		int[] currentPos = {0, 7};
		int[] attemptedPos = {0, 3};
		 
		assertTrue(rook.isValidMove(array, currentPos, attemptedPos)); 
	}
	
	// legal horizontal
	
	@Test
	public void testRookLegalHorizontal() {
		Piece rook = new Rook(Color.WHITE); 
		array[5][0] = rook; 
		int[] currentPos = {0, 5};
		int[] attemptedPos = {7, 5};
		 
		assertTrue(rook.isValidMove(array, currentPos, attemptedPos)); 
	}
	
	// illegal
	@Test
	public void testRookIllegal() {
		Piece rook = new Rook(Color.WHITE); 
		array[5][0] = rook; 
		int[] currentPos = {0, 5};
		int[] attemptedPos = {7, 4};
		 
		assertFalse(rook.isValidMove(array, currentPos, attemptedPos)); 
	}

	// opponent piece 
	@Test
	public void testRookOpponentPiece() {
		array[6][0] = new Empty(); 
		Piece rook = array[7][0];
		int[] currentPos = {0, 7};
		int[] attemptedPos = {0, 1};
		 
		assertTrue(rook.isValidMove(array, currentPos, attemptedPos)); 
	}
	
	// same team piece
	@Test
	public void testRookSameTeamPiece() {
		Piece rook = array[7][0];
		int[] currentPos = {0, 7};
		int[] attemptedPos = {0, 6};
		 
		assertFalse(rook.isValidMove(array, currentPos, attemptedPos)); 
	}
	
	
	
	// both teams of pieces in the way 
	@Test
	public void testRookPiecesInWay() {
		Piece rook = array[7][0];
		int[] currentPos = {0, 7};
		int[] attemptedPos = {0, 0};
		 
		assertFalse(rook.isValidMove(array, currentPos, attemptedPos)); 
	}
	
	
	// Knight
	
	  // legal (w/ pieces in way) 
	  @Test
	  public void testKnightLegalWithPiecesInWay() {
		  Piece knight = array[7][1];
		  int[] currentPos = {1, 7};
	      int[] attemptedPos = {2, 5};
		  
		  assertTrue(knight.isValidMove(array, currentPos, attemptedPos)); 
	  }
	  
	// legal other orientation of move 
		  @Test
		  public void testKnightLegalFlatOrientation() {
			  Piece knight = array[7][1];
			  array[6][3] = new Empty(); 
			  int[] currentPos = {1, 7};
		      int[] attemptedPos = {3, 6};
			  
			  assertTrue(knight.isValidMove(array, currentPos, attemptedPos)); 
		  }
	
	  // illegal
	  @Test
	  public void testKnightIllegal() {
		  Piece knight = array[7][1];
		  int[] currentPos = {1, 7};
	      int[] attemptedPos = {2, 6};
		  
		  assertFalse(knight.isValidMove(array, currentPos, attemptedPos)); 
	  }
		  
	  // opponent piece 
	  @Test
	  public void testKnightOpponentPiece() {
		  Piece knight = array[7][1];
		  array[5][2] = new BlackPawn();
		  int[] currentPos = {1, 7};
	      int[] attemptedPos = {2, 5};
		  
		  assertTrue(knight.isValidMove(array, currentPos, attemptedPos)); 
	  }
		
	  // same team piece 
	  @Test
	  public void testKnightSameTeamPiece() {
		  Piece knight = array[7][1];
		  int[] currentPos = {1, 7};
	      int[] attemptedPos = {3, 6};
		  
		  assertFalse(knight.isValidMove(array, currentPos, attemptedPos)); 
	  }
	
	
	// Bishop
	
	  // legal 
	  @Test
	  public void testBishopLegal() {
		  array[6][3] = new Empty(); 
		  Piece bishop = array[7][2];
		  int[] currentPos = {2, 7};
	      int[] attemptedPos = {4, 5};
		  
		  assertTrue(bishop.isValidMove(array, currentPos, attemptedPos)); 
	  }
		
      // illegal
	  @Test
	  public void testBishopIllegal() {
		  array[6][3] = new Empty(); 
		  Piece bishop = array[7][2];
		  int[] currentPos = {2, 7};
	      int[] attemptedPos = {3, 5};
		  
		  assertFalse(bishop.isValidMove(array, currentPos, attemptedPos)); 
	  }
		
      // opponent piece 
	  @Test
	  public void testBishopOpponent() {
		  array[6][3] = new Empty(); 
		  Piece bishop = array[7][2];
		  array[5][4] = new BlackPawn(); 
		  int[] currentPos = {2, 7};
	      int[] attemptedPos = {4, 5};
		  
		  assertTrue(bishop.isValidMove(array, currentPos, attemptedPos)); 
	  }
		
	  // same team piece
	  @Test
	  public void testBishopSameTeamPiece() {
		  array[6][3] = new Empty(); 
		  Piece bishop = array[7][2];
		  array[5][4] = new WhitePawn(); 
		  int[] currentPos = {2, 7};
	      int[] attemptedPos = {4, 5};
		  
		  assertFalse(bishop.isValidMove(array, currentPos, attemptedPos)); 
	  }
		
	  // both teams of pieces in the way 
	  @Test
	  public void testBishopPiecesInWay() {
		  Piece bishop = array[7][2];
		  array[5][4] = new BlackPawn(); 
		  int[] currentPos = {2, 7};
	      int[] attemptedPos = {5, 4};
		  
		  assertFalse(bishop.isValidMove(array, currentPos, attemptedPos)); 
	  }
	  
	  
	
	/* Queen -- the queen's isValid move function simply says that either the bishop or knight's
	 *  isValidMove function returns true. If both the bishop and knight's tests have passed, the
	 *  queen also functions correctly  */
	
	
	/* King- the king is the same as the queen except that it can move a maximum of one spot in any
	 * direction. Therefore we only need to test cases of greater than 1 spot movement
	 * and one spot movement for horizontal, vertical, and diagonal directions 
	 */
	  
	  @Test
	  public void testKingLegalVertical() {
		  Piece king = new King(Color.WHITE); 
		  array[5][4] = king;
		  int[] currentPos = {4, 5};
	      int[] attemptedPos = {4, 4};
		  
		  assertTrue(king.isValidMove(array, currentPos, attemptedPos)); 
	  }
	  
	  @Test
	  public void testKingLegalHorizontal() {
		  Piece king = new King(Color.WHITE); 
		  array[5][4] = king;
		  int[] currentPos = {4, 5};
	      int[] attemptedPos = {3, 5};
		  
		  assertTrue(king.isValidMove(array, currentPos, attemptedPos));  
	  }
	  
	  @Test
	  public void testKingLegalDiagonal() {
		  Piece king = new King(Color.WHITE); 
		  array[5][4] = king;
		  int[] currentPos = {4, 5};
	      int[] attemptedPos = {3, 4};
		  
		  assertTrue(king.isValidMove(array, currentPos, attemptedPos)); 
	  }
	  
	  
	  @Test
	  public void testKingIllegalVertical() {
		  Piece king = new King(Color.WHITE); 
		  array[5][4] = king;
		  int[] currentPos = {4, 5};
	      int[] attemptedPos = {4, 3};
		  
		  assertFalse(king.isValidMove(array, currentPos, attemptedPos)); 
	  }
	  
	  @Test
	  public void testKingIllegalHorizontal() {
		  Piece king = new King(Color.WHITE); 
		  array[5][4] = king;
		  int[] currentPos = {4, 5};
	      int[] attemptedPos = {2, 5};
		  
		  assertFalse(king.isValidMove(array, currentPos, attemptedPos));  
	  }
	  
	  @Test
	  public void testKingIllegalDiagonal() {
		  Piece king = new King(Color.WHITE); 
		  array[5][4] = king;
		  int[] currentPos = {5, 5};
	      int[] attemptedPos = {3, 3};
		  
		  assertFalse(king.isValidMove(array, currentPos, attemptedPos)); 
	  }
	  
	  
	// Empty 
	
	/* Trivial test, as this isValidMove method returns false every time. In fact, empty 
	   is not even allowed to be selected by the GUI/controller */
	
	@Test
	public void testEmpty() {
		Piece empty = array[3][0];
		int[] currentPos = {0, 1};
		int[] attemptedPos = {0, 2};
		assertFalse(empty.isValidMove(array, currentPos, attemptedPos));
		
	}
	
	
	

}