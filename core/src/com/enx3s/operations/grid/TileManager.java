package com.enx3s.operations.grid;

import com.enx3s.operations.Combo;
import com.enx3s.operations.screens.GameScreen;

public class TileManager {
	
	Combo combo;
	boolean selecting;
	GameScreen gameScreen;
	
	public TileManager(GameScreen gameScreen)
	{
		selecting = false;
		this.gameScreen = gameScreen;
	}
	
	public void select(Tile tile)
	{
		if(selecting)
		{
			combo.addTile(tile);
		}
		else
		{
			combo = new Combo(this, tile);
			selecting = true;
		}
	}
	
	public void setSelecting(boolean selecting)
	{
		this.selecting = selecting;
	}
	
	public void destroyTiles()
	{
		gameScreen.hide();
	}
	
	public void setOperation(Combo.Operator operator)
	{
		combo.setOperator(operator);
	}
	
	public void setEquals()
	{
		combo.setEquals();
	}
}
