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
	int input1, input2, result;
	int cutoff;
	Operator operator;
	Step step;
	
	public enum Operator {
		add, subtract, multiply, divide
	}
	
	public enum Step {
		input1, operator, input2, equals, result
	}
	
	public Combo(TileManager manager, Tile tile)
	{
		tiles = new ArrayList<Tile>();
		color = Color.BLUE;
		tile.setColor(color);
		tiles.add(tile);
		this.manager = manager;
		step = Step.input1;
		input1 = 0;
		input2 = 0;
		result = 0;
		cutoff = 0;
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
		isValid();
	}
	
	public boolean isValid()
	{
		switch(step) {
		case input1: 
			input1 *= 10;
			input1 += Integer.parseInt(getLastTile().getText().toString());
			break;
		case operator:
			
			break;	
		case input2:
			input2 *= 10;
			input2 += Integer.parseInt(getLastTile().getText().toString());
			break;
		case equals:
			
			break;
		case result:
			result *= 10;
			result += Integer.parseInt(getLastTile().getText().toString());
			break;
		}
		return false;
	}
	
	public Tile getLastTile()
	{
		return tiles.get(tiles.size()-1);
	}
	
	public void removeLastTile()
	{
		if(step == Step.input1 || step == Step.input2 || step == Step.result && tiles.size() - 1 > cutoff)
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
}
