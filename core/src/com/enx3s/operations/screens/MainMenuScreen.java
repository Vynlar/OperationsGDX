package com.enx3s.operations.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.enx3s.operations.Operations;

public class MainMenuScreen implements Screen{
	
	Operations game;
	Texture background;
	Stage stage;
	
	float buttonScale = 1.7F;
	
	public MainMenuScreen(final Operations game)
	{
		this.game = game;
		
		stage = new Stage(new ExtendViewport(1080,1920));
		Gdx.input.setInputProcessor(stage);
		
		//load background image
		background = new Texture("mainmenu_bg.png");
		Image image = new Image(background);
		image.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage.addActor(image);
		
		//load and add start game button
		TextureRegion startGame_tex = new TextureRegion(new Texture("mainmenu_startgame.png"));
		Button startGame = new Button(new TextureRegionDrawable(startGame_tex));
		startGame.setTransform(true);
		startGame.setScale(buttonScale);
		startGame.setPosition((Gdx.graphics.getWidth()/2)-(startGame.getWidth()*buttonScale/2), (Gdx.graphics.getHeight()/5)*2);
		//listener on the start game button
		startGame.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Action changeScreen = new Action() {

					@Override
					public boolean act(float delta) {
						game.setScreen(game.selectScreen);
						return false;
					}
				};
				actor.addAction(Actions.sequence(Actions.moveBy(Gdx.graphics.getWidth(), 0F, 0.8F, Interpolation.exp5), changeScreen));
			}
			
		});
		stage.addActor(startGame);
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
