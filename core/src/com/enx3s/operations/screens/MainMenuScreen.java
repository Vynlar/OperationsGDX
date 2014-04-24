package com.enx3s.operations.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.enx3s.operations.Operations;

public class MainMenuScreen implements Screen{
	
	Operations game;
	Texture background;
	Stage stage;
	float fontScale = 1.7f;
	
	public MainMenuScreen(final Operations game)
	{
		this.game = game;
	}
	
	public void init() {
		stage = new Stage(new ExtendViewport(1080,1920));
		Gdx.input.setInputProcessor(stage);
		
		//load background image
		background = new Texture("mainmenu_bg.png");
		Image image = new Image(background);
		image.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(image);

		//startGame
		Action changeScreen = new Action() {

			@Override
			public boolean act(float delta) {
				game.setScreen(game.selectScreen);
				return false;
			}
		};
		stage.addActor(Operations.makeButton("Start Game", (Gdx.graphics.getHeight()/5)*2.5F, true, changeScreen, fontScale));
		
		//title label
		Label title = new Label("Operations", new LabelStyle(new BitmapFont(Gdx.files.internal("font.fnt"), false), Color.WHITE));
		//title.setAlignment(Align.center);
		title.setFontScale(fontScale*1.2f);
		title.setScale(fontScale);
		title.setPosition(Gdx.graphics.getWidth()/2 - title.getTextBounds().width/2, (Gdx.graphics.getHeight()/5)*4);
		stage.addActor(title);
	}

	@Override
	public void render(float delta) {
		//clear screen manually
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//update and render stage
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
		// TODO Auto-generated method stub
		init();
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
