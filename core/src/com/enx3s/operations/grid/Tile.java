package com.enx3s.operations.grid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.enx3s.operations.Point;

public class Tile extends Piece{
	
	Sprite sprite, spriteSelected;
	BitmapFont font;
	boolean selected;
	int number;
	Point pos;
	
	public Tile(Texture sprite, Texture spriteSelected, BitmapFont font, Point pos)
	{
		this.font = font;
		font.setScale(1.4f);
		this.sprite = new Sprite(sprite);
		this.spriteSelected = new Sprite(spriteSelected);
		number = (int) (Math.random()*10);
		this.pos = pos;
		selected = false;
	}
	
	public void render(SpriteBatch batch, float delta, Point pos)
	{
		if(!selected)
		{
			sprite.setPosition(pos.x, pos.y);
			sprite.draw(batch);
		}
		else
		{
			spriteSelected.setPosition(pos.x, pos.y);
			spriteSelected.draw(batch);
		}
		font.draw(batch, Integer.toString(number), pos.x + sprite.getWidth()/2 - 25, pos.y + sprite.getHeight()/2 + 20);
	}
	
	public void setSelected(boolean _selected)
	{
		selected = _selected;
		System.out.println("OPER: Set not selected");
	}
	
	public Point getPosition()
	{
		return pos;
	}
	
	public void setPosition(Point pos)
	{
		this.pos = pos;
	}
}