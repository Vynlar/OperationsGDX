package com.enx3s.operations;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Color;
import com.enx3s.operations.grid.Tile;
import com.enx3s.operations.grid.TileManager;

public class Combo {
	
	private ArrayList<Tile> tiles;
	TileManager manager;
	Color color;
	
	public Combo(TileManager manager, Tile tile)
	{
		tiles = new ArrayList<Tile>();
		color = Color.BLUE;
		tile.setColor(color);
		tiles.add(tile);
		this.manager = manager;
	}
	
	public void addTile(Tile tile)
	{
		Tile lastTile = getLastTile();
		if(tile == lastTile)
		{
			removeLastTile();
			return;
		}
		if(tiles.contains(tile))
		{
			return;
		}
		if(tile.getPosition().x >= lastTile.getPosition().x - 1 &&
				tile.getPosition().x <= lastTile.getPosition().x + 1 &&
				tile.getPosition().y >= lastTile.getPosition().y -1 &&
				tile.getPosition().y <= lastTile.getPosition().y + 1)
		{
			tile.setColor(color);
			tiles.add(tile);
		}
	}
	
	public boolean isValid()
	{
		Iterator<Tile> iterator = tiles.iterator();
		while(iterator.hasNext())
		{
			Tile tile = iterator.next();
		}
		return false;
	}
	
	public Tile getLastTile()
	{
		return tiles.get(tiles.size()-1);
	}
	
	public void removeLastTile()
	{
		tiles.get(tiles.size()-1).setColor(Color.WHITE);
		if(tiles.size()-1 != 0)
		{
			tiles.remove(tiles.size()-1);
		}
		else
		{
			manager.setSelecting(false);
		}
	}
}
