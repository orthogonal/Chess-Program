import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class chessboard extends JPanel{
	
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
		if (checkValidMove(piece, center.squares[column - 1][row - 1])){
			Square oldSquare = piece.square;
			piece.square.piece = null;
			piece.square = center.squares[column - 1][row - 1];
			if (piece.square.piece != null){
				if (piece.square.piece.color == piece.color){
					piece.square = oldSquare;
					piece.square.piece = piece;
					piece.setLocation(piece.square.getPoint());
				}
				else
					capture(piece, piece.square.piece);
			}
			else{
				piece.square.piece = piece;
				piece.setLocation(piece.square.getPoint());
				center.frame.repaint();
			}
		}
		else
			piece.setLocation(piece.square.getPoint());
	}
	
	public static Square getSquare(Point p){
		int column = ((int)Math.ceil(((p.x + 1) / 40) + 0.1) - 1);
		int row = 8 - (int)Math.ceil(((p.y + 1) / 40) + 0.1);
		return center.squares[column][row];
	}
	
	public void capture(Piece winner, Piece loser){
		loser.square.piece = null;
		loser.square = null;
		remove(loser);
		winner.square.piece = winner;
		winner.setLocation(winner.square.getPoint());
		center.frame.repaint();
	}
	
	public boolean checkValidMove(Piece piece, Square square){
		piece.updateMoves();
		for (int j = 7; j >= 0; j--){
			for (int i = 0; i < 8; i++){
				System.out.print(piece.available[i][j] + " ");
			}
			System.out.println();
		}
		if (piece.available[square.getColumn() - 1][square.getRow() - 1] != 0)
			return true;
		else
			return false;
	}
}
