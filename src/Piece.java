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
			char nork = end.charAt(2);
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
}
