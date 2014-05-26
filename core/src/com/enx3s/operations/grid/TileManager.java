package com.enx3s.operations.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.enx3s.operations.Point;

public class TileManager {
	
	//grid
	Tile[][] tiles;
	int width = 8,
		height = 12;
	final int tileSize = Gdx.graphics.getWidth() / width;
	SpriteBatch batch;
	//combo
	Combo combo;
	boolean selecting;
	
	public TileManager()
	{
		tiles = new Tile[width][height];
		batch = new SpriteBatch();
		generateTiles();
	}
	
	public void generateTiles()
	{
		Texture sprite = new Texture("tile.png");
		Texture spriteSelected = new Texture("tileSelected.png");
		BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false);
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				tiles[i][j] = new Tile(sprite, spriteSelected, font, new Point(i,j));
			}
		}
	}
	
	public void addPiece(Piece piece)
	{
		if(selecting)
			combo.addPiece(piece);
		else
		{
			combo = new Combo(this);
			combo.addPiece(piece);
			selecting = true;
		}
	}
	
	public void removeLastPiece()
	{
		combo.removeLastPiece();
	}
	
	public void touchUpdate(int x, int y)
	{
		addPiece(tiles[x/tileSize][(-y + Gdx.graphics.getHeight())/tileSize]);
	}
	
	public void render(float delta)
	{
		//render
		batch.begin();
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j ++)
			{
				tiles[i][j].render(batch, delta, new Point(tileSize*i, tileSize*j));
			}
		}
		batch.end();
	}
	
	public void setSelecting(boolean selecting)
	{
		this.selecting = selecting;
	}
}
