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
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.enx3s.operations.Operations;

public class GameSelectScreen implements Screen{
	
	Operations game;
	Texture background;
	Stage stage;
	float fontScale = 1.7f;
	
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
		
		//back button
		Action changeScreen = new Action() {

			@Override
			public boolean act(float delta) {
				game.setScreen(game.mainMenuScreen);
				return false;
			}
		};
		
		float buttonOffset = Gdx.graphics.getHeight()/18;
		stage.addActor(Operations.makeButton("Back", 0 + buttonOffset, false, changeScreen, fontScale));
		
		//normal start game button
		stage.addActor(Operations.makeButton("Normal", ((Gdx.graphics.getHeight()/6)*3) + buttonOffset, true, changeScreen, fontScale));
		stage.addActor(Operations.makeButton("Infinite", ((Gdx.graphics.getHeight()/6)*2) + buttonOffset, true, changeScreen, fontScale));
		stage.addActor(Operations.makeButton("Tower", ((Gdx.graphics.getHeight()/6) + buttonOffset), true, changeScreen, fontScale));
		
		//title label
		Label title = new Label("Game Mode", new LabelStyle(new BitmapFont(Gdx.files.internal("font.fnt"), false), Color.WHITE));
		title.setFontScale(fontScale*1.2f);
		title.setScale(fontScale);
		title.setPosition(Gdx.graphics.getWidth()/2 - title.getTextBounds().width/2, (Gdx.graphics.getHeight()/5)*4); //centers text
		stage.addActor(title);
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
