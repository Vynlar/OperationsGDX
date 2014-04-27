package com.enx3s.operations.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.enx3s.operations.Point;
import com.enx3s.operations.grid.Tile;
import com.enx3s.operations.grid.TileManager;

public class GameScreen implements Screen{
	
	public enum Type{
		NORMAL, INFINITE, TOWER
	}
	
	Type type;
	Stage stage;
	TileManager tileManager;
	int gridWidth = 8;
	int gridHeight = 13;
	
	public GameScreen(Type type)
	{
		this.type = type;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		int width = Gdx.graphics.getWidth();
		Tile.Type[] types = Tile.Type.values();
		TextureRegion texture = new TextureRegion(new Texture("tile.png"));
		Tile.Type[][] tiles = new Tile.Type[gridWidth][gridHeight];
		int operationCount = 0;
		//fill with numbers
		for(int i = 0; i < gridWidth; i++)
			for(int j = 0; j < gridHeight; j++)
			{
				int random = (int) (Math.random()*9);
				Tile.Type type = types[random];
				tiles[i][j] = type;
			}
		
		for(int i = 0; i < gridWidth; i++)
			for(int j = 0; j < gridHeight; j++)
			{
				if(Math.random() < 0.1)
				{
					tiles[i][j] = types[(int)((Math.random()*4)+10)];
					operationCount++;
				}
			}

		for(int i = 0; i < gridWidth; i++)
			for(int j = 0; j < gridHeight; j++)
			{
				Tile tile = new Tile(tiles[i][j], texture, new Point(i,j), this);
				tile.setPosition(i*width/8, j*width/8);
				stage.addActor(tile);
			}
		
		tileManager = new TileManager();
	}
	
	public void selectTile(Tile tile)
	{
		tileManager.select(tile);
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

}
