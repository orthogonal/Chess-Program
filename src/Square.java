import java.awt.Point;


public class Square {
	private int row;
	private int column;
	
	public Piece piece;
	
	public static final Point a1 = new Point(0, 280);
	public static final Point a2 = new Point(0, 240);
	public static final Point a3 = new Point(0, 200);
	public static final Point a4 = new Point(0, 160);
	public static final Point a5 = new Point(0, 120);
	public static final Point a6 = new Point(0, 80);
	public static final Point a7 = new Point(0, 40);
	public static final Point a8 = new Point(0, 0);
	
	public static final Point b1 = new Point(40, 280);
	public static final Point b2 = new Point(40, 240);
	public static final Point b3 = new Point(40, 200);
	public static final Point b4 = new Point(40, 160);
	public static final Point b5 = new Point(40, 120);
	public static final Point b6 = new Point(40, 80);
	public static final Point b7 = new Point(40, 40);
	public static final Point b8 = new Point(40, 0);
	
	public static final Point c1 = new Point(80, 280);
	public static final Point c2 = new Point(80, 240);
	public static final Point c3 = new Point(80, 200);
	public static final Point c4 = new Point(80, 160);
	public static final Point c5 = new Point(80, 120);
	public static final Point c6 = new Point(80, 80);
	public static final Point c7 = new Point(80, 40);
	public static final Point c8 = new Point(80, 0);
	
	public static final Point d1 = new Point(120, 280);
	public static final Point d2 = new Point(120, 240);
	public static final Point d3 = new Point(120, 200);
	public static final Point d4 = new Point(120, 160);
	public static final Point d5 = new Point(120, 120);
	public static final Point d6 = new Point(120, 80);
	public static final Point d7 = new Point(120, 40);
	public static final Point d8 = new Point(120, 0);
	
	public static final Point e1 = new Point(160, 280);
	public static final Point e2 = new Point(160, 240);
	public static final Point e3 = new Point(160, 200);
	public static final Point e4 = new Point(160, 160);
	public static final Point e5 = new Point(160, 120);
	public static final Point e6 = new Point(160, 80);
	public static final Point e7 = new Point(160, 40);
	public static final Point e8 = new Point(160, 0);
	
	public static final Point f1 = new Point(200, 280);
	public static final Point f2 = new Point(200, 240);
	public static final Point f3 = new Point(200, 200);
	public static final Point f4 = new Point(200, 160);
	public static final Point f5 = new Point(200, 120);
	public static final Point f6 = new Point(200, 80);
	public static final Point f7 = new Point(200, 40);
	public static final Point f8 = new Point(200, 0);
	
	public static final Point g1 = new Point(240, 280);
	public static final Point g2 = new Point(240, 240);
	public static final Point g3 = new Point(240, 200);
	public static final Point g4 = new Point(240, 160);
	public static final Point g5 = new Point(240, 120);
	public static final Point g6 = new Point(240, 80);
	public static final Point g7 = new Point(240, 40);
	public static final Point g8 = new Point(240, 0);
	
	public static final Point h1 = new Point(280, 280);
	public static final Point h2 = new Point(280, 240);
	public static final Point h3 = new Point(280, 200);
	public static final Point h4 = new Point(280, 160);
	public static final Point h5 = new Point(280, 120);
	public static final Point h6 = new Point(280, 80);
	public static final Point h7 = new Point(280, 40);
	public static final Point h8 = new Point(280, 0);
	
	public static final Point[][] pointsList = {
		{a1, a2, a3, a4, a5, a6, a7, a8},
		{b1, b2, b3, b4, b5, b6, b7, b8},
		{c1, c2, c3, c4, c5, c6, c7, c8},
		{d1, d2, d3, d4, d5, d6, d7, d8},
		{e1, e2, e3, e4, e5, e6, e7, e8},
		{f1, f2, f3, f4, f5, f6, f7, f8},
		{g1, g2, g3, g4, g5, g6, g7, g8},
		{h1, h2, h3, h4, h5, h6, h7, h8}	
	};
	
	public static final String[][] pointStringList = {
		{"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8"},
		{"b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8"},
		{"c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8"},
		{"d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8"},
		{"e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8"},
		{"f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8"},
		{"g1", "g2", "g3", "g4", "g5", "g6", "g7", "g8"},
		{"h1", "h2", "h3", "h4", "h5", "h6", "h7", "h8"} 
	};
	
	private Point point;
	private String pointString;
	
	public Square(int column, int row){
		this.row = row;
		this.column = column;
		this.point = pointsList[column - 1][row - 1];
		this.pointString = pointStringList[column - 1][row - 1];
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getColumn(){
		return this.column;
	}
	
	public Point getPoint(){
		return this.point;
	}
	
	public String getPointString(){
		return this.pointString;
	}
	
	public void setRow(int row){
		this.row = row;
	}
	
	public void setColumn(int column){
		this.column = column;
	}
	
	public void setPoint(int column, int row){
		this.row = row;
		this.column = column;
		this.point = pointsList[column - 1][row - 1];
		this.pointString = pointStringList[column - 1][row - 1];
	}
}
