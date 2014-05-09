package com.enx3s.operations;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
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
		in1, operator, in2, equals, result
	}
	
	public Combo(TileManager manager, Tile tile)
	{	
		tiles = new ArrayList<Tile>();
		color = Color.BLUE;
		tile.setColor(color);
		tiles.add(tile);
		this.manager = manager;
		input1 = 0;
		input2 = 0;
		result = 0;
		cutoff = 0;
		step = Step.in1;
		update();
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
			update();
		}
	}
	
	public void update()
	{
		Gdx.app.log("oper", "updated");
		switch(step) {
		case in1: 
			input1 *= 10;
			input1 += Integer.parseInt(getLastTile().getText().toString());
			Gdx.app.log("oper", "input1");
			break;
		case operator:
			step = Step.in2;
			cutoff = tiles.size();
			Gdx.app.log("oper", "operator: " + input1);
			break;	
		case in2:
			input2 *= 10;
			input2 += Integer.parseInt(getLastTile().getText().toString());
			Gdx.app.log("oper", "input2");
			break;
		case equals:
			step = Step.result;
			cutoff = tiles.size();
			Gdx.app.log("oper", "equals");
			break;
		case result:
			result *= 10;
			result += Integer.parseInt(getLastTile().getText().toString());
			Gdx.app.log("oper", "result");
			if(isValid())
			{
				manager.destroyTiles();
			}
			break;
		}
	}
	
	public boolean isValid()
	{
		switch(operator)
		{
		case add:
			if(input1 + input2 == result)
				return true;
			break;
		case divide:
			if(input1 / input2 == result)
				return true;
			break;
		case multiply:
			if(input1 * input2 == result)
				return true;
			break;
		case subtract:
			if(input1 - input2 == result)
				return true;
			break;
		default:
			return false;
		}
		return false;
	}
	
	public void setOperator(Operator operator)
	{
		this.operator = operator;
		step = Step.operator;
		update();
	}
	
	public void setEquals()
	{
		step = Step.equals;
		update();
	}
	
	public Tile getLastTile()
	{
		return tiles.get(tiles.size()-1);
	}
	
	public void removeLastTile()
	{
		if((step == Step.in1 || step == Step.in2 || step == Step.result) && (tiles.size() - 1 > cutoff || tiles.size() == 1))
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
