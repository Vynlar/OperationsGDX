package com.enx3s.operations;

import com.badlogic.gdx.Game;
import com.enx3s.operations.screens.GameSelectScreen;
import com.enx3s.operations.screens.MainMenuScreen;

public class Operations extends Game {
	
	public MainMenuScreen mainMenu;
	public GameSelectScreen selectScreen;
	
	@Override
	public void create() {
		mainMenu = new MainMenuScreen(this);
		selectScreen = new GameSelectScreen(this);
		this.setScreen(mainMenu);
	}
}
