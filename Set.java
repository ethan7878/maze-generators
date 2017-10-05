package common;

import java.awt.*;
import java.util.*;

public class Set {
	
	public int id;
	Random rng = new Random();
	Color c;
	ArrayList<Square> inSet = new ArrayList<Square>();
	
	public Set(Square s, int id) {
		this.inSet.add(s);
		this.id = id;
		
		this.createColor();
	}
	
	private void createColor() {
		String index = "0123456789ABCDEF";
		String color = "#";
		
		for (int i=0; i<6; i++) {
			char next = index.charAt(rng.nextInt(index.length()));
			color = color + next;
		}
		
		this.c = Color.decode(color);
	}
	
	public void merge(Set s) {
		
		int size1 = this.inSet.size();
		int size2 = s.inSet.size();
		
		if (size1 < size2) {
			this.c = s.c;
		}
		
		for (int i=0; i<s.inSet.size(); i++) {
			s.inSet.get(i).setId = this.id;
			this.inSet.add(s.inSet.get(i));
		}
	}
	
	public void drawSet(Graphics g) {
		for (int i=0; i<this.inSet.size(); i++)
			this.inSet.get(i).draw(g, this.c);
	}
	
}