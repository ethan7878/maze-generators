package common;

import java.awt.*;

public class Square {
	int x, y, scale;
	public String walls = "1111";
	public int setId;
	public boolean visited = false;
	
	public Square(int x, int y, int scale) {
		this.x = x;
		this.y = y;
		this.scale = scale;
	}
	
	public Square(int x, int y, int scale, int setId) {
		this.x = x;
		this.y = y;
		this.scale = scale;
		this.setId = setId;
	}
	
	public void changeWall(char dir, int openClose) {
		switch (dir) {
			case 'n': this.walls = openClose + walls.substring(1);
					break;
			case 'e': this.walls = walls.substring(0, 1) + openClose + walls.substring(2);
					break;
			case 's': this.walls = walls.substring(0, 2) + openClose + walls.substring(3);
					break;
			case 'w': this.walls = walls.substring(0, 3) + openClose;
					break;
		}
		this.visited = true;
	}
	
	public void changeWall(int dir, int openClose) {
		String index = "nesw";
		this.changeWall(index.charAt(dir), openClose);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(this.x * this.scale, this.y * this.scale, this.scale, this.scale);
		this.drawWalls(g);
	}
	
	public void draw(Graphics g, Color c) {
		g.setColor(c);
		g.fillRect(this.x * this.scale, this.y * this.scale, this.scale, this.scale);
		this.drawWalls(g);
	}
	
	private void drawWalls(Graphics g) {

		g.setColor(Color.BLUE);
		
		if (this.walls.charAt(0) == '1')
			g.fillRect(this.x * this.scale, this.y * this.scale, this.scale, (int) (this.scale * 0.1));
		if (this.walls.charAt(1) == '1')
			g.fillRect((int) ((this.x + 0.9) * this.scale), this.y * this.scale, (int) (this.scale * 0.1), this.scale);
		if (this.walls.charAt(2) == '1') 
			g.fillRect(this.x * this.scale, (int) ((this.y + 0.9) * this.scale), this.scale, (int) (this.scale * 0.1));
		if (this.walls.charAt(3) == '1')
			g.fillRect(this.x * this.scale, this.y * this.scale, (int) (this.scale * 0.1), this.scale);
		
	}
	
}