package com.enx3s.operations.grid;

import com.enx3s.operations.Combo;

public class TileManager {
	
	Combo combo;
	boolean selecting;
	
	public TileManager()
	{
		selecting = false;
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
}
