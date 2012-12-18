package model;

import java.awt.Color;
import java.awt.Graphics;

import data.Data;

public class Ball extends Fighter {

	public Ball(int life, int price) {
		super(life, price);
	}

	public void draw(Graphics g) {
		if (ice) {
			g.setColor(Color.blue);
		} else {
			g.setColor(Color.magenta);
		}
		g
				.fillOval(y + 10, x + 10, Data.squaresSize - 20,
						Data.squaresSize - 20);
		drawLifeStatus(g, x, y);
	}

}
