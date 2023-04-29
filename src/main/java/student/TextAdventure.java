package student;

import java.util.ArrayList;

public class TextAdventure {
	
	public ArrayList<ItemType> Items = new ArrayList<ItemType>( );
	public ArrayList<SceneryType> Sceneries = new ArrayList<SceneryType>( );
	public ArrayList<Transformation> Transformations= new ArrayList<Transformation>();
	public ArrayList<Composition> Compositions = new ArrayList<Composition>();
	public ArrayList<Decomposition> Decompositions = new ArrayList<Decomposition>();

	public int boardHeight;
	public int boardWidth ;
	public String name;
	public String[][] map ;
	public TextAdventure(String name, int x, int y) {
		this.boardHeight = x;
		this.boardWidth = y ;
		this.name = name ;
		initialiser();
	}
	public void initialiser() {
	 map = new String[boardHeight][boardWidth];

		for (int i = 0; i < boardHeight; i++) 
		{
			for (int j = 0; j < boardWidth; j++) 
			{ 
				map[i][j] ="("+j+","+i+") "; 
			} 
		}

	}
	public void addItemType(String id, String description) throws TextAdventureException {
		try {ItemType IT = new ItemType(id, description);
		Items.add(IT);
		}catch(Exception e) {
			throw new TextAdventureException(e.toString());
		}
	}

	public void addSceneryType(String id, String description) throws TextAdventureException{
		SceneryType ST = new SceneryType(id,description);
		Sceneries.add(ST);

	}

	public void placeItem(String type, int x, int y) throws TextAdventureException {
		map[y][x] =map[y][x] +" " +type ;

	}

	public void addComposition(String in1, String in2, String out, String description) throws TextAdventureException {
		Composition T = new Composition(in1,in2,out,description);
		Compositions.add(T);
	}

	public void addDecomposition(String in, String out1, String out2, String description)
			throws TextAdventureException {
		Decomposition D = new Decomposition(in,out1,out2,description);
		Decompositions.add(D);
	}

	public void addTransformation(String in1, String in2, String out1, String out2, String description)
			throws TextAdventureException {
		Transformation T = new Transformation(in1,in2,out1,out2,description);
		Transformations.add(T);
	}

	
	public Player startGame(int x, int y) throws TextAdventureException {
		Player P = new Player(x,y);
		return P;
	}

	public String getName() {
		return name;
	}

}
