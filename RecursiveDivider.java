
import java.applet.*;
import java.awt.*;
import java.util.*;

public class RecursiveDivider extends Applet {
	
	Graphics g;
	
	int w = 32, h = 18, scale = 25;
	
	Random rng = new Random();
	
	boolean showSetup = true;
	
	Square[][] grid = new Square[w][h];
	
	public void init() {
		setSize(this.w * this.scale, this.h * this.scale);
		setBackground(Color.BLACK);
		this.createGrid();
	}
	
	private void delay() {
		if (showSetup) {
			int delay = 100;
			this.drawMaze();
			try {
				Thread.sleep(delay);
			} catch (Exception e) {
				e.toString();
			}
		}
	}
	
	private void createGrid() {
		for (int i=0; i<w; i++) {
			for (int j=0; j<h; j++) {
				this.grid[i][j] = new Square(i, j, this.scale);
				this.grid[i][j].walls = "0000";
			}
		}
	}
	
	private void createMaze() {
		if (!this.grid[0][0].visited)
			createWall(0, 0, this.w, this.h);
	}
	
	private void createWall(int xMin, int yMin, int xMax, int yMax) {
		int dx = xMax - xMin;
		int dy = yMax - yMin;
		
		if (dx > dy || (dx == dy && rng.nextBoolean()))
			createVertWall(xMin, yMin, xMax, yMax);
		else
			createHorWall(xMin, yMin, xMax, yMax);
	}
	
	private void createVertWall(int xMin, int yMin, int xMax, int yMax) {
		
		if (xMin == xMax - 1 || yMin == yMax - 1)
			return;
		
		int xWall = rng.nextInt(xMax - xMin - 1) + xMin + 1;
		int gap = rng.nextInt(yMax - yMin) + yMin;
		
		this.delay();
		
		boolean addLeft = (xWall == this.w - 1);
		
		for (int i=yMin; i < yMax; i++) {
			boolean v = this.grid[xWall][i].visited;
			this.grid[xWall][i].changeWall('w', 1);
			this.grid[xWall][i].visited = addLeft || v;
			this.grid[xWall - 1][i].changeWall('e', 1);
		}
		
		boolean v = this.grid[xWall][gap].visited;
		this.grid[xWall][gap].changeWall('w', 0);
		this.grid[xWall - 1][gap].changeWall('e', 0);
		this.grid[xWall][gap].visited = addLeft || v;
		
		this.createWall(xMin, yMin, xWall, yMax);
		this.createWall(xWall, yMin, xMax, yMax);
		
	}
	
	private void createHorWall(int xMin, int yMin, int xMax, int yMax) {
		
		if (xMin == xMax - 1 || yMin == yMax - 1)
			return;
		
		int yWall = rng.nextInt(yMax - yMin - 1) + yMin + 1;
		int gap = rng.nextInt(xMax - xMin) + xMin;
		
		this.delay();
		
		boolean addTop = (yWall == this.h - 1);
		
		for (int i=xMin; i<xMax; i++) {
			boolean v = this.grid[i][yWall].visited;
			this.grid[i][yWall].changeWall('n', 1);
			this.grid[i][yWall].visited = addTop || v;
			this.grid[i][yWall - 1].changeWall('s', 1);
		}
		
		// Knock out the gap
		boolean v = this.grid[gap][yWall].visited;
		this.grid[gap][yWall].changeWall('n', 0);
		this.grid[gap][yWall - 1].changeWall('s', 0);
		this.grid[gap][yWall].visited = addTop || v;
		
		this.createWall(xMin, yMin, xMax, yWall);
		this.createWall(xMin, yWall, xMax, yMax);
		
	}
	
	public void paint(Graphics g) { this.g = g;
		
		this.createMaze();
		this.drawMaze();
		
	}
	
	private void drawMaze() {
		for (int i = 0; i < this.w; i++) {
			for (int j = 0; j < this.h; j++) {
				if (this.grid[i][j].visited)
					this.grid[i][j].draw(g, Color.GRAY);
				else
					this.grid[i][j].draw(g, Color.BLACK);
			}
		}
	}

}
