import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Piece extends JLabel{
	
	public static final int WHITE = 0;
	public static final int BLACK = 1;
	
	public static final int PAWN = 0;
	public static final int KNIGHT = 1;
	public static final int BISHOP = 2;
	public static final int ROOK = 3;
	public static final int QUEEN = 4;
	public static final int KING = 5;
	
	public int type;
	public int color;
	
	public ImageIcon picture;
	
	private boolean pickedUp = false;
	
	public static final int NO_MOVE = 0;
	public static final int MOVE_ONLY = 1;
	public static final int CAPTURE = 2;
	public int[][] available = new int[8][8];		//0 = can't, 1 = move but not capture (pawn), 2 = capture/move
	
	
	Square square;
	
	Piece(String src, Square square){
		String end = src.substring(7);	//Assuming images/ folder is the first part of the path.
		char color = end.charAt(0);
		char type = end.charAt(1);
		
		if (color == 'w')
			this.color = this.WHITE;
		else
			this.color = this.BLACK;
		
		switch(type){
		case 'p':
			this.type = this.PAWN;
			break;
		case 'k':
			char nork = end.charAt(2);		//nork checks for knight or king.
			if (nork == 'n')
				this.type = this.KNIGHT;
			else
				this.type = this.KING;
			break;
		case 'b':
			this.type = this.BISHOP;
			break;
		case 'r':
			this.type = this.ROOK;
			break;
		case 'q':
			this.type = this.QUEEN;
			break;
		}
		
		this.picture = new ImageIcon(src);
		this.setIcon(this.picture);
		this.setSize(40, 40);
		
		this.square = square;
		this.square.piece = this;
	}
	
	public void updateMoves(){
		
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				available[i][j] = Piece.NO_MOVE;
			}
		}
		
		switch (this.type){
		case Piece.PAWN:
			for (int i = 0; i < 8; i++){
				for (int j = 0; j < 8; j++){
					Square testSquare = center.squares[i][j];
					int row = j + 1;
					int col = i + 1;
					if (this.color == Piece.WHITE){
						if (this.square.getRow() == 2){									//Second rank:  One or two up
							if (row == 3 || row == 4)
								if (this.square.getColumn() == col)
									if (testSquare.piece == null)
										this.available[i][j] = Piece.MOVE_ONLY;
									else
										this.available[i][j] = Piece.NO_MOVE;
								else
									if (Math.abs(this.square.getColumn() - col) == 1		//Check second rank caps
									&& testSquare.piece != null) 
										if (testSquare.piece.color != this.color)
											this.available[i][j] = Piece.CAPTURE;
										else
											this.available[i][j] = Piece.NO_MOVE;
									else
										this.available[i][j] = Piece.NO_MOVE;
							else
								this.available[i][j] = Piece.NO_MOVE;
						}
						else{
							if (row == this.square.getRow() + 1)							//Other rank:  One up
								if (this.square.getColumn() == col)
									if (testSquare.piece == null)
										this.available[i][j] = Piece.MOVE_ONLY;
									else
										this.available[i][j] = Piece.NO_MOVE;			
								else													//Check other rank caps
									if (Math.abs(this.square.getColumn() - col) == 1
									&& testSquare.piece != null)
										if (testSquare.piece.color != this.color)
											this.available[i][j] = Piece.CAPTURE;
										else
											this.available[i][j] = Piece.NO_MOVE;
									else
										this.available[i][j] = Piece.NO_MOVE;
							else
								this.available[i][j] = Piece.NO_MOVE;
						}
					}
					else{		//Black pawn
						if (this.square.getRow() == 7){									//Seventh rank:  One or two down
							if (row == 6 || row == 5)
								if (this.square.getColumn() == col)
									if (testSquare.piece == null)
										this.available[i][j] = Piece.MOVE_ONLY;
									else
										this.available[i][j] = Piece.NO_MOVE;
								else
									if (Math.abs(this.square.getColumn() - col) == 1		//Check seventh rank caps
									&& testSquare.piece != null) 
										if (testSquare.piece.color != this.color)
											this.available[i][j] = Piece.CAPTURE;
										else
											this.available[i][j] = Piece.NO_MOVE;
									else
										this.available[i][j] = Piece.NO_MOVE;
							else
								this.available[i][j] = Piece.NO_MOVE;
						}
						else{
							if (row == this.square.getRow() - 1)							//Other rank:  One down
								if (this.square.getColumn() == col)
									if (testSquare.piece == null)
										this.available[i][j] = Piece.MOVE_ONLY;
									else
										this.available[i][j] = Piece.NO_MOVE;			
								else													//Check other rank caps
									if (Math.abs(this.square.getColumn() - col) == 1
									&& testSquare.piece != null)
										if (testSquare.piece.color != this.color)
											this.available[i][j] = Piece.CAPTURE;
										else
											this.available[i][j] = Piece.NO_MOVE;
									else
										this.available[i][j] = Piece.NO_MOVE;
							else
								this.available[i][j] = Piece.NO_MOVE;
						}
					}
				}
			}
			break;
			
		case Piece.KNIGHT:
			for (int i = 1; i <= 8; i++){
				for (int j = 1; j <= 8; j++){
					Square testSquare = center.squares[i - 1][j - 1];
					if ((Math.abs(this.square.getRow() - j)
							  + Math.abs(this.square.getColumn() - i) == 3)
							  && this.square.getRow() != j
							  && this.square.getColumn() != i)
								if ((testSquare.piece != null) && (testSquare.piece.color == this.color))
									this.available[i - 1][j - 1] = Piece.NO_MOVE;
								else
									this.available[i - 1][j - 1] = Piece.CAPTURE;
							else
								this.available[i - 1][j - 1] = Piece.NO_MOVE;
				}
			}
			break;
			
		case Piece.BISHOP:
			int bi = this.square.getColumn();
			int bj = this.square.getRow();
			
			outerloop:
			while (bi <= 8 && bj <= 8){
				Square testSquare = center.squares[bi - 1][bj - 1];
				if (testSquare.piece == null)
					this.available[bi - 1][bj - 1] = Piece.CAPTURE;
				else{
					if (testSquare.piece.color == this.color){
						this.available[bi - 1][bj - 1] = Piece.NO_MOVE;
						if (testSquare.piece != this)
							break outerloop;
					}
					else{
						this.available[bi - 1][bj - 1] = Piece.CAPTURE;
						break outerloop;
					}
				}
				bi++;
				bj++;
			}
			
			bi = this.square.getColumn();
			bj = this.square.getRow();			
			outerloop:
			while (bi <= 8 && bj >= 1){
				Square testSquare = center.squares[bi - 1][bj - 1];
				if (testSquare.piece == null)
					this.available[bi - 1][bj - 1] = Piece.CAPTURE;
				else{
					if (testSquare.piece.color == this.color){
						this.available[bi - 1][bj - 1] = Piece.NO_MOVE;
						if (testSquare.piece != this)
							break outerloop;
					}
					else{
						this.available[bi - 1][bj - 1] = Piece.CAPTURE;
						break outerloop;
					}
				}
				bi++;
				bj--;
			}
			
			bi = this.square.getColumn();
			bj = this.square.getRow();
			outerloop:
			while (bi >= 1 && bj <= 8){
				Square testSquare = center.squares[bi - 1][bj - 1];
				if (testSquare.piece == null)
					this.available[bi - 1][bj - 1] = Piece.CAPTURE;
				else{
					if (testSquare.piece.color == this.color){
						this.available[bi - 1][bj - 1] = Piece.NO_MOVE;
						if (testSquare.piece != this)
							break outerloop;
					}
					else{
						this.available[bi - 1][bj - 1] = Piece.CAPTURE;
						break outerloop;
					}
				}
				bi--;
				bj++;
			}
			
			bi = this.square.getColumn();
			bj = this.square.getRow();
			outerloop:
			while (bi >= 1 && bj >= 1){
				Square testSquare = center.squares[bi - 1][bj - 1];
				if (testSquare.piece == null)
					this.available[bi - 1][bj - 1] = Piece.CAPTURE;
				else{
					if (testSquare.piece.color == this.color){
						this.available[bi - 1][bj - 1] = Piece.NO_MOVE;
						if (testSquare.piece != this)
							break outerloop;
					}
					else{
						this.available[bi - 1][bj - 1] = Piece.CAPTURE;
						break outerloop;
					}
				}
				bi--;
				bj--;
			}
			break;
					
		case Piece.ROOK:
			int ri = this.square.getColumn();
			int rj = this.square.getRow();
			outerloop:
			while (ri <= 8){
				Square testSquare = center.squares[ri - 1][rj - 1];
				if (testSquare.piece == null)
					this.available[ri - 1][rj - 1] = Piece.CAPTURE;
				else{
					if (testSquare.piece.color == this.color){
						this.available[ri - 1][rj - 1] = Piece.NO_MOVE;
						if (testSquare.piece != this)
							break outerloop;
					}
					else{
						this.available[ri - 1][rj - 1] = Piece.CAPTURE;
						break outerloop;
					}
				}
				ri++;
			}
			
			ri = this.square.getColumn();
			outerloop:
			while (ri >= 1){
				Square testSquare = center.squares[ri - 1][rj - 1];
				if (testSquare.piece == null)
					this.available[ri - 1][rj - 1] = Piece.CAPTURE;
				else{
					if (testSquare.piece.color == this.color){
						this.available[ri - 1][rj - 1] = Piece.NO_MOVE;
						if (testSquare.piece != this)
							break outerloop;
					}
					else{
						this.available[ri - 1][rj - 1] = Piece.CAPTURE;
						break outerloop;
					}
				}
				ri--;
			}
			
			ri = this.square.getColumn();
			rj = this.square.getRow();
			outerloop:
			while (rj <= 8){
				Square testSquare = center.squares[ri - 1][rj - 1];
				if (testSquare.piece == null)
					this.available[ri - 1][rj - 1] = Piece.CAPTURE;
				else{
					if (testSquare.piece.color == this.color){
						this.available[ri - 1][rj - 1] = Piece.NO_MOVE;
						if (testSquare.piece != this)
							break outerloop;
					}
					else{
						this.available[ri - 1][rj - 1] = Piece.CAPTURE;
						break outerloop;
					}
				}
				rj++;
			}
			
			rj = this.square.getRow();
			outerloop:
			while (rj >= 1){
				Square testSquare = center.squares[ri - 1][rj - 1];
				if (testSquare.piece == null)
					this.available[ri - 1][rj - 1] = Piece.CAPTURE;
				else{
					if (testSquare.piece.color == this.color){
						this.available[ri - 1][rj - 1] = Piece.NO_MOVE;
						if (testSquare.piece != this)
							break outerloop;
					}
					else{
						this.available[ri - 1][rj - 1] = Piece.CAPTURE;
						break outerloop;
					}
				}
				rj--;
			}
			break;
			
		case Piece.QUEEN:
			int qi = this.square.getColumn();
			int qj = this.square.getRow();
			
			outerloop:
			while (qi <= 8 && qj <= 8){
				Square testSquare = center.squares[qi - 1][qj - 1];
				if (testSquare.piece == null)
					this.available[qi - 1][qj - 1] = Piece.CAPTURE;
				else{
					if (testSquare.piece.color == this.color){
						this.available[qi - 1][qj - 1] = Piece.NO_MOVE;
						if (testSquare.piece != this)
							break outerloop;
					}
					else{
						this.available[qi - 1][qj - 1] = Piece.CAPTURE;
						break outerloop;
					}
				}
				qi++;
				qj++;
			}
			
			qi = this.square.getColumn();
			qj = this.square.getRow();			
			outerloop:
			while (qi <= 8 && qj >= 1){
				Square testSquare = center.squares[qi - 1][qj - 1];
				if (testSquare.piece == null)
					this.available[qi - 1][qj - 1] = Piece.CAPTURE;
				else{
					if (testSquare.piece.color == this.color){
						this.available[qi - 1][qj - 1] = Piece.NO_MOVE;
						if (testSquare.piece != this)
							break outerloop;
					}
					else{
						this.available[qi - 1][qj - 1] = Piece.CAPTURE;
						break outerloop;
					}
				}
				qi++;
				qj--;
			}
			
			qi = this.square.getColumn();
			qj = this.square.getRow();
			outerloop:
			while (qi >= 1 && qj <= 8){
				Square testSquare = center.squares[qi - 1][qj - 1];
				if (testSquare.piece == null)
					this.available[qi - 1][qj - 1] = Piece.CAPTURE;
				else{
					if (testSquare.piece.color == this.color){
						this.available[qi - 1][qj - 1] = Piece.NO_MOVE;
						if (testSquare.piece != this)
							break outerloop;
					}
					else{
						this.available[qi - 1][qj - 1] = Piece.CAPTURE;
						break outerloop;
					}
				}
				qi--;
				qj++;
			}
			
			qi = this.square.getColumn();
			qj = this.square.getRow();
			outerloop:
			while (qi >= 1 && qj >= 1){
				Square testSquare = center.squares[qi - 1][qj - 1];
				if (testSquare.piece == null)
					this.available[qi - 1][qj - 1] = Piece.CAPTURE;
				else{
					if (testSquare.piece.color == this.color){
						this.available[qi - 1][qj - 1] = Piece.NO_MOVE;
						if (testSquare.piece != this)
							break outerloop;
					}
					else{
						this.available[qi - 1][qj - 1] = Piece.CAPTURE;
						break outerloop;
					}
				}
				qi--;
				qj--;
			}
					
			qi = this.square.getColumn();
			qj = this.square.getRow();
			outerloop:
			while (qi <= 8){
				Square testSquare = center.squares[qi - 1][qj - 1];
				if (testSquare.piece == null)
					this.available[qi - 1][qj - 1] = Piece.CAPTURE;
				else{
					if (testSquare.piece.color == this.color){
						this.available[qi - 1][qj - 1] = Piece.NO_MOVE;
						if (testSquare.piece != this)
							break outerloop;
					}
					else{
						this.available[qi - 1][qj - 1] = Piece.CAPTURE;
						break outerloop;
					}
				}
				qi++;
			}
			
			qi = this.square.getColumn();
			outerloop:
			while (qi >= 1){
				Square testSquare = center.squares[qi - 1][qj - 1];
				if (testSquare.piece == null)
					this.available[qi - 1][qj - 1] = Piece.CAPTURE;
				else{
					if (testSquare.piece.color == this.color){
						this.available[qi - 1][qj - 1] = Piece.NO_MOVE;
						if (testSquare.piece != this)
							break outerloop;
					}
					else{
						this.available[qi - 1][qj - 1] = Piece.CAPTURE;
						break outerloop;
					}
				}
				qi--;
			}
			
			qi = this.square.getColumn();
			qj = this.square.getRow();
			outerloop:
			while (qj <= 8){
				Square testSquare = center.squares[qi - 1][qj - 1];
				if (testSquare.piece == null)
					this.available[qi - 1][qj - 1] = Piece.CAPTURE;
				else{
					if (testSquare.piece.color == this.color){
						this.available[qi - 1][qj - 1] = Piece.NO_MOVE;
						if (testSquare.piece != this)
							break outerloop;
					}
					else{
						this.available[qi - 1][qj - 1] = Piece.CAPTURE;
						break outerloop;
					}
				}
				qj++;
			}
			
			qj = this.square.getRow();
			outerloop:
			while (qj >= 1){
				Square testSquare = center.squares[qi - 1][qj - 1];
				if (testSquare.piece == null)
					this.available[qi - 1][qj - 1] = Piece.CAPTURE;
				else{
					if (testSquare.piece.color == this.color){
						this.available[qi - 1][qj - 1] = Piece.NO_MOVE;
						if (testSquare.piece != this)
							break outerloop;
					}
					else{
						this.available[qi - 1][qj - 1] = Piece.CAPTURE;
						break outerloop;
					}
				}
				qj--;
			}
			break;
			
		case Piece.KING:		
			for (int i = -1; i <= 1; i++){
				for (int j = -1; j <= 1; j++){
					int col = this.square.getColumn() + i;
					int row = this.square.getRow() + j;
					if ((1 <= col) && (col <= 8)
							&& (1 <= row) && (row <= 8)){
						if (center.squares[col - 1][row - 1].piece == null 
								|| center.squares[col - 1][row - 1].piece.color != this.color)
							this.available[col - 1][row - 1] = Piece.CAPTURE;
						else
							this.available[col - 1][row - 1] = Piece.NO_MOVE;
					}
				}
			}
		}
	}
}
