package com.enx3s.operations.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.enx3s.operations.Point;
import com.enx3s.operations.screens.GameScreen;

public class Tile extends ImageTextButton{
	
	boolean selected;
	Type type;
	Point pos;
	GameScreen screen;
	
	public enum Type
	{
		zero, one, two, three, four, five, six, seven, eight, nine, add, subtract, multiply, divide, equals 
	}
	
	public Tile(Type type, TextureRegion texture, Point pos, GameScreen screen)
	{	
		super("", makeButtonStyle(texture));
		this.screen = screen;
		this.pos = pos;
		this.type = type;
		if(type.ordinal() < 9)
			this.setText(Integer.toString(type.ordinal()));
		else
		{
			switch (type){
			case add: this.setText("+");
			break;
			
			case subtract: this.setText("-");
			break;
			
			case multiply: this.setText("x");
			break;
			
			case divide: this.setText("/");
			break;
			
			case equals: this.setText("=");
			break;
			}
		}
		selected = false;
		
		this.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				select();
			}
		});
	}
	
	public void select()
	{
		screen.selectTile(this);
	}
	
	public static ImageTextButtonStyle makeButtonStyle(TextureRegion texture)
	{
		TextureRegionDrawable up = new TextureRegionDrawable(texture); 
		ImageTextButtonStyle style = new ImageTextButtonStyle(up, up, up, new BitmapFont(Gdx.files.internal("font.fnt"), false));
		style.font.setScale(1.3f);
		style.font.setColor(Color.BLUE);
		return style;
	}
	
	public void toggle()
	{
		if(selected)
			selected = false;
		else
			selected = true;
	}
	
	public Point getPosition()
	{
		return pos;
	}
}