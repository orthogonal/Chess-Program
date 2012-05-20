import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.LinkedList;

import javax.swing.*;

public class center extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String bpawn = "images/bpawn.png";
	private static String wpawn = "images/wpawn.png";
	private static String bknight = "images/bknight.png";
	private static String wknight = "images/wknight.png";
	private static String bbishop = "images/bbishop.png";
	private static String wbishop = "images/wbishop.png";
	private static String brook = "images/brook.png";
	private static String wrook = "images/wrook.png";
	private static String bqueen = "images/bqueen.png";
	private static String wqueen = "images/wqueen.png";
	private static String bking = "images/bking.png";
	private static String wking = "images/wking.png";
	

	public static JFrame frame = new center();
	
	public static Square a1 = new Square(1, 1);
	public static Square a2 = new Square(1, 2);
	public static Square a3 = new Square(1, 3);
	public static Square a4 = new Square(1, 4);
	public static Square a5 = new Square(1, 5);
	public static Square a6 = new Square(1, 6);
	public static Square a7 = new Square(1, 7);
	public static Square a8 = new Square(1, 8);
	
	public static Square b1 = new Square(2, 1);
	public static Square b2 = new Square(2, 2);
	public static Square b3 = new Square(2, 3);
	public static Square b4 = new Square(2, 4);
	public static Square b5 = new Square(2, 5);
	public static Square b6 = new Square(2, 6);
	public static Square b7 = new Square(2, 7);
	public static Square b8 = new Square(2, 8);
	
	public static Square c1 = new Square(3, 1);
	public static Square c2 = new Square(3, 2);
	public static Square c3 = new Square(3, 3);
	public static Square c4 = new Square(3, 4);
	public static Square c5 = new Square(3, 5);
	public static Square c6 = new Square(3, 6);
	public static Square c7 = new Square(3, 7);
	public static Square c8 = new Square(3, 8);
	
	public static Square d1 = new Square(4, 1);
	public static Square d2 = new Square(4, 2);
	public static Square d3 = new Square(4, 3);
	public static Square d4 = new Square(4, 4);
	public static Square d5 = new Square(4, 5);
	public static Square d6 = new Square(4, 6);
	public static Square d7 = new Square(4, 7);
	public static Square d8 = new Square(4, 8);
	
	public static Square e1 = new Square(5, 1);
	public static Square e2 = new Square(5, 2);
	public static Square e3 = new Square(5, 3);
	public static Square e4 = new Square(5, 4);
	public static Square e5 = new Square(5, 5);
	public static Square e6 = new Square(5, 6);
	public static Square e7 = new Square(5, 7);
	public static Square e8 = new Square(5, 8);
	
	public static Square f1 = new Square(6, 1);
	public static Square f2 = new Square(6, 2);
	public static Square f3 = new Square(6, 3);
	public static Square f4 = new Square(6, 4);
	public static Square f5 = new Square(6, 5);
	public static Square f6 = new Square(6, 6);
	public static Square f7 = new Square(6, 7);
	public static Square f8 = new Square(6, 8);
	
	public static Square g1 = new Square(7, 1);
	public static Square g2 = new Square(7, 2);
	public static Square g3 = new Square(7, 3);
	public static Square g4 = new Square(7, 4);
	public static Square g5 = new Square(7, 5);
	public static Square g6 = new Square(7, 6);
	public static Square g7 = new Square(7, 7);
	public static Square g8 = new Square(7, 8);
	
	public static Square h1 = new Square(8, 1);
	public static Square h2 = new Square(8, 2);
	public static Square h3 = new Square(8, 3);
	public static Square h4 = new Square(8, 4);
	public static Square h5 = new Square(8, 5);
	public static Square h6 = new Square(8, 6);
	public static Square h7 = new Square(8, 7);
	public static Square h8 = new Square(8, 8);
	
	public static Square[][] squares = {
			{a1, a2, a3, a4, a5, a6, a7, a8},
			{b1, b2, b3, b4, b5, b6 ,b7, b8},
			{c1, c2, c3, c4, c5, c6, c7, c8},
			{d1, d2, d3, d4, d5, d6, d7, d8},
			{e1, e2, e3, e4, e5, e6, e7, e8},
			{f1, f2, f3, f4, f5, f6, f7, f8},
			{g1, g2, g3, g4, g5, g6, g7, g8},
			{h1, h2, h3, h4, h5, h6, h7, h8}
	};

	public static LinkedList<Piece> blackPieces = new LinkedList<Piece>();
	public static LinkedList<Piece> whitePieces = new LinkedList<Piece>();
	
	public static Piece wp1 = new Piece(wpawn, a2);
	public static Piece wp2 = new Piece(wpawn, b2);
	public static Piece wp3 = new Piece(wpawn, c2);
	public static Piece wp4 = new Piece(wpawn, d2);
	public static Piece wp5 = new Piece(wpawn, e2);
	public static Piece wp6 = new Piece(wpawn, f2);
	public static Piece wp7 = new Piece(wpawn, g2);
	public static Piece wp8 = new Piece(wpawn, h2);
	public static Piece wr1 = new Piece(wrook, a1);
	public static Piece wr2 = new Piece(wrook, h1);
	public static Piece wn1 = new Piece(wknight, b1);
	public static Piece wn2 = new Piece(wknight, g1);
	public static Piece wb1 = new Piece(wbishop, c1);
	public static Piece wb2 = new Piece(wbishop, f1);
	public static Piece wq = new Piece(wqueen, d1);
	public static Piece wk = new Piece(wking, e1);
	
	public static Piece bp1 = new Piece(bpawn, a7);
	public static Piece bp2 = new Piece(bpawn, b7);
	public static Piece bp3 = new Piece(bpawn, c7);
	public static Piece bp4 = new Piece(bpawn, d7);
	public static Piece bp5 = new Piece(bpawn, e7);
	public static Piece bp6 = new Piece(bpawn, f7);
	public static Piece bp7 = new Piece(bpawn, g7);
	public static Piece bp8 = new Piece(bpawn, h7);
	public static Piece br1 = new Piece(brook, a8);
	public static Piece br2 = new Piece(brook, h8);
	public static Piece bn1 = new Piece(bknight, b8);
	public static Piece bn2 = new Piece(bknight, g8);
	public static Piece bb1 = new Piece(bbishop, c8);
	public static Piece bb2 = new Piece(bbishop, f8);
	public static Piece bq = new Piece(bqueen, d8);
	public static Piece bk = new Piece(bking, e8);
	
	public static void main(String[] args){
		frame.setTitle("Test");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel board = new chessboard();
		board.setSize(320, 320);
		frame.setSize(340, 340);
		board.setLocation(0, 0);
	
		
		board.add(wp1);
		board.add(wp2);
		board.add(wp3);
		board.add(wp4);
		board.add(wp5);
		board.add(wp6);
		board.add(wp7);
		board.add(wp8);
		board.add(wr1);
		board.add(wr2);
		board.add(wn1);
		board.add(wn2);
		board.add(wb1);
		board.add(wb2);
		board.add(wq);
		board.add(wk);
		
		board.add(bp1);
		board.add(bp2);
		board.add(bp3);
		board.add(bp4);
		board.add(bp5);
		board.add(bp6);
		board.add(bp7);
		board.add(bp8);
		board.add(br1);
		board.add(br2);
		board.add(bn1);
		board.add(bn2);
		board.add(bb1);
		board.add(bb2);
		board.add(bq);
		board.add(bk);
		
		
		wp1.setLocation(wp1.square.getPoint());
		wp2.setLocation(wp2.square.getPoint());
		wp3.setLocation(wp3.square.getPoint());
		wp4.setLocation(wp4.square.getPoint());
		wp5.setLocation(wp5.square.getPoint());
		wp6.setLocation(wp6.square.getPoint());
		wp7.setLocation(wp7.square.getPoint());
		wp8.setLocation(wp8.square.getPoint());
		wr1.setLocation(wr1.square.getPoint());
		wr2.setLocation(wr2.square.getPoint());
		wn1.setLocation(wn1.square.getPoint());
		wn2.setLocation(wn2.square.getPoint());
		wb1.setLocation(wb1.square.getPoint());
		wb2.setLocation(wb2.square.getPoint());
		wq.setLocation(wq.square.getPoint());
		wk.setLocation(wk.square.getPoint());
		
		bp1.setLocation(bp1.square.getPoint());
		bp2.setLocation(bp2.square.getPoint());
		bp3.setLocation(bp3.square.getPoint());
		bp4.setLocation(bp4.square.getPoint());
		bp5.setLocation(bp5.square.getPoint());
		bp6.setLocation(bp6.square.getPoint());
		bp7.setLocation(bp7.square.getPoint());
		bp8.setLocation(bp8.square.getPoint());
		br1.setLocation(br1.square.getPoint());
		br2.setLocation(br2.square.getPoint());
		bn1.setLocation(bn1.square.getPoint());
		bn2.setLocation(bn2.square.getPoint());
		bb1.setLocation(bb1.square.getPoint());
		bb2.setLocation(bb2.square.getPoint());
		bq.setLocation(bq.square.getPoint());
		bk.setLocation(bk.square.getPoint());
		
		whitePieces.add(wp1);
		whitePieces.add(wp2);
		whitePieces.add(wp3);
		whitePieces.add(wp4);
		whitePieces.add(wp5);
		whitePieces.add(wp6);
		whitePieces.add(wp7);
		whitePieces.add(wp8);
		whitePieces.add(wr1);
		whitePieces.add(wr2);
		whitePieces.add(wn1);
		whitePieces.add(wn2);
		whitePieces.add(wb1);
		whitePieces.add(wb2);
		whitePieces.add(wq);
		whitePieces.add(wk);
		
		blackPieces.add(bp1);
		blackPieces.add(bp2);
		blackPieces.add(bp3);
		blackPieces.add(bp4);
		blackPieces.add(bp5);
		blackPieces.add(bp6);
		blackPieces.add(bp7);
		blackPieces.add(bp8);
		blackPieces.add(br1);
		blackPieces.add(br2);
		blackPieces.add(bn1);
		blackPieces.add(bn2);
		blackPieces.add(bb1);
		blackPieces.add(bb2);
		blackPieces.add(bq);
		blackPieces.add(bk);

		frame.add(board);
		frame.repaint();
		frame.addComponentListener(new ComponentAdapter(){
				public void componentResized(ComponentEvent evt){
					wp1.setLocation(wp1.square.getPoint());
					wp2.setLocation(wp2.square.getPoint());
					wp3.setLocation(wp3.square.getPoint());
					wp4.setLocation(wp4.square.getPoint());
					wp5.setLocation(wp5.square.getPoint());
					wp6.setLocation(wp6.square.getPoint());
					wp7.setLocation(wp7.square.getPoint());
					wp8.setLocation(wp8.square.getPoint());
					wr1.setLocation(wr1.square.getPoint());
					wr2.setLocation(wr2.square.getPoint());
					wn1.setLocation(wn1.square.getPoint());
					wn2.setLocation(wn2.square.getPoint());
					wb1.setLocation(wb1.square.getPoint());
					wb2.setLocation(wb2.square.getPoint());
					wq.setLocation(wq.square.getPoint());
					wk.setLocation(wk.square.getPoint());
					
					bp1.setLocation(bp1.square.getPoint());
					bp2.setLocation(bp2.square.getPoint());
					bp3.setLocation(bp3.square.getPoint());
					bp4.setLocation(bp4.square.getPoint());
					bp5.setLocation(bp5.square.getPoint());
					bp6.setLocation(bp6.square.getPoint());
					bp7.setLocation(bp7.square.getPoint());
					bp8.setLocation(bp8.square.getPoint());
					br1.setLocation(br1.square.getPoint());
					br2.setLocation(br2.square.getPoint());
					bn1.setLocation(bn1.square.getPoint());
					bn2.setLocation(bn2.square.getPoint());
					bb1.setLocation(bb1.square.getPoint());
					bb2.setLocation(bb2.square.getPoint());
					bq.setLocation(bq.square.getPoint());
					bk.setLocation(bk.square.getPoint());
				}
			});
	}
	
	public center(){

	}
	
}
