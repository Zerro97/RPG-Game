package screen;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class TileMap {
	// Position
	private double x;
	private double y;

	// Map Bounds
	private int xmin;
	private int xmax;
	private int ymin;
	private int ymax;

	// Map
	private ArrayList<int[][]> map;	// arrayList holding all maps info
	private int mapNum;	// stores which map to draw
	
	/*private int[][] firstLayer;	// int array containing map info
	private int[][] secondLayer;	// int array containing map info
	 */	
	
	private int[][] layer;	// Array containing map info
	private int numRows; // Number of tile rows in a map
	private int numCols; // Number of tile columns in a map
	private int width; // Total width of a map
	private int height; // Total height of a map

	// TileSet
	private BufferedImage tileSet; // Single image containing all tiles
	private int numTilesRow; // Number of tile rows in tileSet
	private int numTilesCol; // Number of tile columns in tileSet
	private int gap;		// Gap between tiles
	
	// Tile
	private Tile[][] tiles; // Individual tiles from tileSet
	private int tileSize; // Individual tile size (n * n)

	public TileMap(int tileSize, int gap) {
		this.tileSize = tileSize;
		this.gap = gap;
		map = new ArrayList<int[][]>();
	}

	public void loadTiles(String s) {
		int xLocation = 0;
		int yLocation = 0;
		
		try {
			tileSet = ImageIO.read(getClass().getResourceAsStream(s));
			numTilesRow = tileSet.getHeight() / tileSize;
			numTilesCol = tileSet.getWidth() / tileSize;
			tiles = new Tile[numTilesRow][numTilesCol];

			BufferedImage subImage;
			for (int row = 0; row < numTilesRow; row++) {
				for (int col = 0; col < numTilesCol; col++) {
					xLocation = col * (tileSize + gap);
					yLocation = row * (tileSize + gap);
					subImage = tileSet.getSubimage(xLocation+1, yLocation+1, tileSize, tileSize);
					tiles[row][col] = new Tile(subImage, Tile.NORMAL);
				}
			}
			tiles[120 / numTilesCol][120 % numTilesRow].setType(Tile.BLOCKED);
			tiles[201 / numTilesCol][201 % numTilesRow].setType(Tile.BLOCKED);
			tiles[202 / numTilesCol][202 % numTilesRow].setType(Tile.BLOCKED);
			tiles[44 / numTilesCol][44 % numTilesRow].setType(Tile.BLOCKED);
			tiles[64 / numTilesCol][64 % numTilesRow].setType(Tile.BLOCKED);
			tiles[4 / numTilesCol][4 % numTilesRow].setType(Tile.BLOCKED);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String s) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(s));
			numRows = 15;
			numCols = 20;
			
			/*if(s.substring(s.length()-1).equals("1") ) {
				firstLayer = new int[numRows][numCols];
			} else if (s.substring(s.length()-1).equals("2") ) {
				secondLayer = new int[numRows][numCols];
			}*/
			
			layer = new int[numRows][numCols];
			
			for(int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(",");
				for(int col = 0; col < numCols; col++) {
					layer[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			map.add(layer);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		drawFirst(g);
		drawSecond(g);
	}
	
	public int getType(int row, int col) {
		int rc = map.get(mapNum*2-1)[row][col];
		int r = rc / numTilesCol;
		int c = rc % numTilesRow;
		
		// Check if second layer is normal, if it is, check first layer
		if(rc == -1 || tiles[r][c].getType() == Tile.NORMAL) {
			rc = map.get(mapNum*2-2)[row][col];
			r = rc / numTilesCol;
			c = rc % numTilesRow;
		}
		
		return tiles[r][c].getType();
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
	public int getTileNumCol() {
		return map.get(mapNum*2-2)[0].length;
	}
	
	public int getTileNumRow() {
		return map.get(mapNum*2-2).length;
	}
	
	/*
	 * This method sets which map to draw when render method is called
	 */
	public void setMapNumber(int mapNum) {
		this.mapNum = mapNum;
	}
	
	private void drawFirst(Graphics g) {
		int[][] tempLayer1 = map.get(mapNum*2 - 2);
		for(int row = 0; row < tempLayer1.length; row++) {
			for(int col = 0; col < tempLayer1[row].length; col++) {
				int rc = tempLayer1[row][col];	// rc stores number representing specific tile
				int r = rc / numTilesCol;	// r stores row number for tiles array
				int c = rc % numTilesRow;	// c stores col number for tiles array
				
				g.drawImage(tiles[r][c].getImage(), col * tileSize, row * tileSize, null);
			}
		}
	}
	
	private void drawSecond(Graphics g) {
		int[][] tempLayer2 = map.get(mapNum*2 - 1);
		for(int row = 0; row < tempLayer2.length; row++) {
			for(int col = 0; col < tempLayer2[row].length; col++) {
				if(tempLayer2[row][col] == -1) {
					continue;
				}
				int rc = tempLayer2[row][col];	// rc stores number representing specific tile
				int r = rc / numTilesCol;	// r stores row number for tiles array
				int c = rc % numTilesRow;	// c stores col number for tiles array
				
				g.drawImage(tiles[r][c].getImage(), col * tileSize, row * tileSize, null);
			}
		}
	}
}
