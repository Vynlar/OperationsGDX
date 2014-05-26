package com.enx3s.operations.grid;

import java.util.ArrayList;

public class Combo {
	
	ArrayList<Piece> pieces;
	TileManager manager;
	
	public Combo(TileManager manager)
	{
		this.manager = manager;
		pieces = new ArrayList<Piece>();
	}

	public void addPiece(Piece piece) {
		if(!pieces.contains(piece))
		{
			if(piece instanceof Tile)
			{
				if(pieces.size() == 0)
				{
					pieces.add(piece);
					((Tile) piece).setSelected(true);
					return;
				}
				Tile tile = (Tile) piece;
				Tile lastTile;
				int i = pieces.size()-1;
				if(pieces.get(i) instanceof Tile)
				{
					lastTile = (Tile)pieces.get(i);
					System.out.println("OPER: found last tile" + tile);
				}
				else
				{
					lastTile = (Tile)pieces.get(i-1);
				}
				
				Tile k = tile;
				
				if(tile.pos.x >= lastTile.pos.x - 1 &&
						tile.pos.x <= lastTile.pos.x + 1 &&
						tile.pos.y >= lastTile.pos.y - 1 &&
						tile.pos.y <= lastTile.pos.y + 1)
				{
					tile.setSelected(true);
					pieces.add(piece);
				}
				return;
			}
			//Make sure that no two operators can go in a row
			pieces.add(piece);
		}
		else if(piece == pieces.get(pieces.size()-1))
		{
			if(piece instanceof Tile)
			{
				Tile tile = (Tile) piece;
				tile.setSelected(false);
			}
			removeLastPiece();
		}
	}

	public void removeLastPiece() {
		if(pieces.size() > 1)
		{
			pieces.remove(pieces.size()-1);
		}
		else
		{
			manager.setSelecting(false);
		}
	}
}