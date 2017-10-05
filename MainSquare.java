package recursive_backtracker;

import common.*;

import java.applet.*;
import java.awt.*;
import java.util.*;

public class MainSquare extends Applet {
	
	Graphics g;
	
	int w = 32, h = 18, scale = 25;
	
	boolean showSetup = true;
	
	Square[][] grid = new Square[w][h];

	public void init() {
		setSize(w * scale, h * scale);
		setBackground(Color.BLACK);
		this.createGrid();
	}
	
	private void createGrid() {
		
		for (int i = 0; i < this.w; i++) {
			for (int j = 0; j < this.h; j++) {
				this.grid[i][j] = new Square(i, j, scale);
			}
		}
		
	}
	
	private void createMaze() {
		Random rng = new Random();
		doSquare(rng.nextInt(w), rng.nextInt(h));
	}
	
	private void doSquare(int x, int y) {
		
		if (showSetup) {
			int delay = 10;
			if (hasBeenVisitedOnce(x, y))
				delay *= 3;
			this.drawMaze();
			this.grid[x][y].draw(g, Color.RED);
			try {
				Thread.sleep(delay);
			} catch (Exception e) {
				e.toString();
			}
		}
		
		ArrayList<Character> dirs = new ArrayList<Character>();
		
		if (isAvailable(x, y - 1))
			dirs.add('n');
		if (isAvailable(x + 1, y))
			dirs.add('e');
		if (isAvailable(x, y + 1))
			dirs.add('s');
		if (isAvailable(x - 1, y)) 
			dirs.add('w');
		
		if (dirs.size() == 0)
			return;
		
		char dir = dirs.get((new Random()).nextInt(dirs.size()));
		
		moveTo(x, y, dir);
		
	}
	
	private void moveTo(int x, int y, char dir) {
		switch (dir) {
			case 'n': {
				this.grid[x][y].changeWall(dir, 0);
				this.grid[x][y - 1].changeWall('s', 0);
				doSquare(x, y - 1);
			} break;
			case 'e': {
				this.grid[x][y].changeWall(dir, 0);
				this.grid[x + 1][y].changeWall('w', 0);
				doSquare(x + 1, y);
			} break;
			case 's': {
				this.grid[x][y].changeWall(dir, 0);
				this.grid[x][y + 1].changeWall('n', 0);
				doSquare(x, y + 1);
			} break;
			case 'w': {
				this.grid[x][y].changeWall(dir, 0);
				this.grid[x - 1][y].changeWall('e', 0);
				doSquare(x - 1, y);
			} break;
		}
		doSquare(x, y);
	}
	
	private boolean isAvailable(int x, int y) {
		if (x == -1 || x == w || y == -1 || y == h || hasBeenVisited(x, y))
			return false;
		return true;
	}
	
	private boolean hasBeenVisited(int x, int y) {
		return this.grid[x][y].visited;
	}
	
	private boolean hasBeenVisitedOnce(int x, int y) {
		String walls = this.grid[x][y].walls;
		int count = walls.length() - walls.replace("0", "").length();
		return (count == 1);
	}
	
	public void paint(Graphics g) { this.g = g;
		
		createMaze();
		drawMaze();
		
	}
	
	private void drawMaze() {
		for (int i = 0; i < this.w; i++) {
			for (int j = 0; j < this.h; j++) {
				if (this.hasBeenVisited(i, j))
					this.grid[i][j].draw(g, Color.GRAY);
				else
					this.grid[i][j].draw(g, Color.BLACK);
			}
		}
	}

}