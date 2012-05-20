import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class chessboard extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static boolean pickedUp = false;
	private static Piece pickedUpPiece = null;
	
	protected void paintComponent(Graphics pen){
		Color white = new Color(255, 255, 255);
		Color black = new Color(0, 0, 0);
		Boolean flag = false;			//true if last was white
		pen.setColor(white);
		for (int x = 0; x < 8; x++){
			for (int y = 0; y < 8; y++){
				pen.fillRect(40 * x, 40 * y, 39, 39);
				flag = !flag;
				pen = switchPen(pen, flag, white, black);
			}
			flag = !flag;
			pen = switchPen(pen, flag, white, black);
		}
	}
	
	private Graphics switchPen(Graphics pen, Boolean flag, Color white, Color black){
		if (flag){
			pen.setColor(black);
			flag = false;
		}
		else{
			pen.setColor(white);
			flag = true;
		}
		return pen;
	}
	
	public chessboard(){
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent evt){
				Point p = evt.getPoint();
				Square clicked = getSquare(p);
				System.out.println(clicked.getPointString());
				if (clicked.piece != null){
					pickedUp = true;
					pickedUpPiece = clicked.piece;
					System.out.println(pickedUpPiece.type);
				}
			}
			public void mouseReleased(MouseEvent evt){
				if (pickedUp){
					Point p = evt.getPoint();
					movePiece(pickedUpPiece, getSquare(p).getColumn(), getSquare(p).getRow());
					pickedUp = false;
					pickedUpPiece = null;
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent evt){
				if (pickedUp){
					if (pickedUpPiece != null){
						pickedUpPiece.setLocation(evt.getX() - 20, evt.getY() - 20);
					}
				}
			}
		});
	}
	
	public void movePiece(Piece piece, int column, int row){
		/*  The piece will be non-physically moved in checkValidMove, so save its old squares */
		Piece oldPiece = center.squares[column - 1][row - 1].piece;
		Square oldSquare = piece.square;
		
		if (checkValidMove(piece, center.squares[column - 1][row - 1])){		//If the move is valid
			/* Put the piece back on its original square, first */
			piece.square = oldSquare;
			center.squares[column - 1][row - 1].piece = oldPiece;
			
			/* Move it to a new square */
			piece.square.piece = null;
			piece.square = center.squares[column - 1][row - 1];
			
			if (piece.square.piece != null){					//If the square is occupied
				if (piece.square.piece.color == piece.color){	//If it's a piece of the same color, move back.
					piece.square = oldSquare;
					piece.square.piece = piece;
					piece.setLocation(piece.square.getPoint());
					piece.hasMoved = false;
				}
				else											//If it's a piece of a different color, capture it.
					capture(piece, piece.square.piece);
			}
			else{												//If the square is unoccupied, move there.
				piece.square.piece = piece;
				piece.setLocation(piece.square.getPoint());
				if (!piece.hasMoved && piece.type == Piece.KING && (piece.square.getColumn() == 7 || piece.square.getColumn() == 3)){
					if (piece.square.getColumn() == 3){			//Queenside Castle
						if (piece.color == Piece.WHITE){
							center.wr1.square.piece = null;
							center.wr1.square = center.squares[3][0];
							center.wr1.square.piece = center.wr1;
							center.wr1.hasMoved = true;
							center.wr1.setLocation(center.wr1.square.getPoint());
						}
						else if (piece.color == Piece.BLACK){
							center.br1.square.piece = null;
							center.br1.square = center.squares[3][7];
							center.br1.square.piece = center.br1;
							center.br1.hasMoved = true;
							center.br1.setLocation(center.br1.square.getPoint());
						}
					}
					if (piece.square.getColumn() == 7){			//Kingside Castle
						if (piece.color == Piece.WHITE){
							center.wr2.square.piece = null;
							center.wr2.square = center.squares[5][0];
							center.wr2.square.piece = center.wr2;
							center.wr2.hasMoved = true;
							center.wr2.setLocation(center.wr2.square.getPoint());
						}
						else if (piece.color == Piece.BLACK){
							center.br2.square.piece = null;
							center.br2.square = center.squares[5][7];
							center.br2.square.piece = center.br2;
							center.br2.hasMoved = true;
							center.br2.setLocation(center.br2.square.getPoint());
						}
					}
				}
				piece.hasMoved = true;
			}
		}
		else
			piece.setLocation(piece.square.getPoint());			//If the move is invalid, draw the piece back on its original square.
		
		center.frame.repaint();
	}
	
	public static Square getSquare(Point p){
		int column = ((int)Math.ceil(((p.x + 1) / 40) + 0.1) - 1);
		int row = 8 - (int)Math.ceil(((p.y + 1) / 40) + 0.1);
		return center.squares[column][row];
	}
	
	public void capture(Piece winner, Piece loser){
		winner.hasMoved = true;
		loser.square.piece = null;
		loser.square = null;
		if (loser.color == Piece.WHITE)
			center.whitePieces.remove(loser);
		else
			center.blackPieces.remove(loser);
		remove(loser);
		winner.square.piece = winner;
		winner.setLocation(winner.square.getPoint());
		center.frame.repaint();
	}
	
	public boolean checkValidMove(Piece piece, Square square){
		
		/*  First, find what squares are available to the piece at this time */
		piece.updateMoves();
		
		/* For testing purposes only:  Draw the board as the piece sees it before moving. */
		for (int j = 7; j >= 0; j--){
			for (int i = 0; i < 8; i++){
				System.out.print(piece.available[i][j] + " ");
			}
			System.out.println();
		}
		
		/*  If the piece can go to the square it's trying to go to */
		if (piece.available[square.getColumn() - 1][square.getRow() - 1] != 0){
			/*  Save its old location */
			Square oldSquare = piece.square;
			Piece oldPiece = square.piece;
			
			/* Completely change the board so that it moves it to its new location, ignoring captures */
			piece.square = square;
			square.piece = piece;
			oldSquare.piece = null;
			
			/* If this move does not result in your king being in check, finalize everything and we're done */
			if (finalize(piece.color))
				return true;
			else{
				piece.square.piece = oldPiece;		// If the king would be in check, put everything back where it belongs and re-finalize.
				piece.square = oldSquare;
				oldSquare.piece = piece;
				finalize(100);						// Call finalize with a non-color number to avoid looking for checks again.
				return false;
			}
		}
		else
			return false;
	}
	
	public boolean finalize(int color){
		
		/* Figure out all the squares available to all the pieces now that the move has been made */
		for (int i = 0; i < center.whitePieces.size(); i++){
			center.whitePieces.get(i).updateMoves();
		}
		for (int i = 0; i < center.blackPieces.size(); i++){
			center.blackPieces.get(i).updateMoves();
		}
		
		/* Check all the squares available to all the enemy pieces.  If any of them matches the king's square, it's an illegal move. 
		 * Also, if the player is trying to castle, check the blank square as well as the square he/she is trying to move the king to.*/
		if (color == Piece.WHITE){		
			for (int i = 0; i < center.blackPieces.size(); i++){
				if (center.blackPieces.get(i).available[center.wk.square.getColumn() - 1][center.wk.square.getRow() - 1] == 2
						|| ((center.wk.available[center.wk.square.getColumn() - 1][center.wk.square.getRow() - 1] == 3)	// If kingside castled
							&& center.blackPieces.get(i).available[6][0] == 2)
						|| ((center.wk.available[center.wk.square.getColumn() - 1][center.wk.square.getRow() - 1] == 4)	// If queenside castled
							&& center.blackPieces.get(i).available[3][0] == 2)){
					System.out.println("Illegal move - the king would be in check");
					return false;
				}
			}
			return true;
		}
		else if (color == Piece.BLACK){
			for (int i = 0; i < center.whitePieces.size(); i++){
				if (center.whitePieces.get(i).available[center.bk.square.getColumn() - 1][center.bk.square.getRow() - 1] == 2
						|| ((center.bk.available[center.bk.square.getColumn() - 1][center.bk.square.getRow() - 1] == 3)	// If kingside castled
							&& center.whitePieces.get(i).available[6][7] == 2)
						|| ((center.bk.available[center.bk.square.getColumn() - 1][center.bk.square.getRow() - 1] == 4)	// If queenside castled
							&& center.whitePieces.get(i).available[3][7] == 2)){
					System.out.println("Illegal move - the king would be in check");
					return false;
				}
			}
			return true;
		}
		return true;
	}
}
