package com.enx3s.operations.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Tile extends ImageTextButton{
	
	boolean selected;
	Character character;
	
	public enum Character
	{
		zero, one, two, three, four, five, six, seven, eight, nine, add, subtract, multiply, divide, equals 
	}
	
	public Tile(Character character)
	{	
		super("", makeButtonStyle());
		this.character = character;
		if(character.ordinal() > 9)
			this.setText(Integer.toString(character.ordinal()));
		else
		{
			switch (character){
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
	}
	
	public static ImageTextButtonStyle makeButtonStyle()
	{
		TextureRegion texture = new TextureRegion(new Texture("tile.png"));
		TextureRegionDrawable up = new TextureRegionDrawable(texture); 
		ImageTextButtonStyle style = new ImageTextButtonStyle(up, up, up, new BitmapFont(Gdx.files.internal("font.fnt"), false));
		return style;
	}
	
	public void toggle()
	{
		if(selected)
			selected = false;
		else
			selected = true;
	}
}