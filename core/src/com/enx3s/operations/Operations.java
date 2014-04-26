package com.enx3s.operations;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.enx3s.operations.screens.GameScreen;
import com.enx3s.operations.screens.GameSelectScreen;
import com.enx3s.operations.screens.MainMenuScreen;

public class Operations extends Game {
	
	public MainMenuScreen mainMenuScreen;
	public GameSelectScreen selectScreen;
	
	@Override
	public void create() {
		mainMenuScreen = new MainMenuScreen(this);
		selectScreen = new GameSelectScreen(this);
		this.setScreen(mainMenuScreen);
	}
	
	//creates a button
	public static TextButton makeButton(String text, float y, final boolean forwards, final Action action, float fontScale)
	{
		TextureRegion texture = new TextureRegion(new Texture("button_blank.png"));
		TextureRegionDrawable drawable = new TextureRegionDrawable(texture); 
		TextButtonStyle style = new TextButtonStyle(drawable, drawable, drawable, new BitmapFont(Gdx.files.internal("font.fnt"), false));
		TextButton button = new TextButton(text, style);
		button.setPosition(-button.getWidth(), y);
		button.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2 - button.getWidth()/2, y, 0.8f, Interpolation.pow5Out));
		button.getCells().get(0).padTop(40f);
		button.getLabel().setFontScale(fontScale);
		button.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				if(!forwards)
				{
					actor.addAction(Actions.sequence(Actions.moveTo(-actor.getWidth(), actor.getY(), 0.4F, Interpolation.pow5In), action));
				}
				else
				{
					actor.addAction(Actions.sequence(Actions.moveTo(Gdx.graphics.getWidth(), actor.getY(), 0.4f, Interpolation.pow5In), action));
				}
			}
		});
		return button;
	}
}
