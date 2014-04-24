package com.enx3s.operations.screens;

import java.io.File;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.enx3s.operations.Operations;

public class GameSelectScreen implements Screen{
	
	Operations game;
	Texture background;
	Stage stage;
	
	public GameSelectScreen(Operations game)
	{
		this.game = game;
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
		//adjust changing viewport
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		stage = new Stage(new ExtendViewport(1080,1920));
		Gdx.input.setInputProcessor(stage);
		
		//load background image
		background = new Texture("mainmenu_bg.png");
		Image image = new Image(background);
		image.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(image);
		
		//setup back button
		TextureRegion backTexture = new TextureRegion(new Texture("button_blank.png"));
		TextureRegionDrawable backDrawable = new TextureRegionDrawable(backTexture); 
		TextButtonStyle backStyle = new TextButtonStyle(backDrawable, backDrawable, backDrawable, new BitmapFont(Gdx.files.internal("font.fnt"), Gdx.files.internal("font.png"), false));
		TextButton backButton = new TextButton("Back", backStyle);
		backButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.setScreen(game.mainMenu);
			}
		});
		backButton.setTransform(true);
		backButton.setScale(0.5f);
		backButton.setPosition(30, 50);
		stage.addActor(backButton);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
