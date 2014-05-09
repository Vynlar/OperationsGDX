package com.enx3s.operations.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.enx3s.operations.Combo;
import com.enx3s.operations.Operations;
import com.enx3s.operations.Point;
import com.enx3s.operations.grid.Tile;
import com.enx3s.operations.grid.TileManager;
import com.sun.jndi.ldap.ManageReferralControl;

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
		//fill with numbers
		for(int i = 0; i < gridWidth; i++)
			for(int j = 0; j < gridHeight; j++)
			{
				int random = (int) (Math.random()*9);
				Tile tile = new Tile(types[random], texture, new Point(i,j), this);
				tile.setPosition(i*width/8, j*width/8);
				stage.addActor(tile);
			}
		
		tileManager = new TileManager(this);
		
		//temporary
		TextureRegion tex = new TextureRegion(new Texture("button_blank.png"));
		TextureRegionDrawable drawable = new TextureRegionDrawable(tex); 
		ButtonStyle style = new ButtonStyle(drawable, drawable, drawable);
		
		Action action = new Action()
		{

			@Override
			public boolean act(float delta) {
				tileManager.setOperation(Combo.Operator.add);
				return false;
			}
		};
		stage.addActor(Operations.makeButton("Add", (Gdx.graphics.getHeight()/8)*7, true, action, 1.0f));
		
		Action action2 = new Action()
		{

			@Override
			public boolean act(float delta) {
				tileManager.setEquals();
				return false;
			}
		};
		stage.addActor(Operations.makeButton("Equals", (Gdx.graphics.getHeight()/8)*6, true, action2, 1.0f));
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
