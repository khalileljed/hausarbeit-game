package student;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
	public int x ;
	public int y ;
	public ArrayList<String> Inventory = new ArrayList<String>();
	public Player() {
	}
	public Player(int x, int y) {
	this.x = x ;
	this.y = y ;
	}
	public String go(String direction,TextAdventure textAdventure) throws TextAdventureException {
		switch (direction) {
        case "N": {
            if (y> 0) {
            	y -=1 ;
            	return "You Go North";
            } else {
                return "Sorry, you are on the border.";
            }
        }
        case "S": {
        	if (y < (textAdventure.boardHeight -1)) {
            	y +=1 ;
            	return "You Go South";
            } else {
                return "Sorry, you are on the border.";
            }
        }
        case "E": {
        	if (x < (textAdventure.boardHeight -1)) {
            	x +=1 ;
            	return "You Go East";
            } else {
                return "Sorry, you are on the border."+textAdventure.boardWidth;
            }
        }
        case "W": {
        	if (x> 0) {
            	x -=1 ;
            	return "You Go West";
            } else {
                return "Sorry, you are on the border.";
            }
        }
        case "NW": {
        	if (y> 0 && x> 0) {
            	y -=1 ;
            	x -=1 ;
            	return "You Go North West";
            } else {
                return "Sorry, you are on the border.";
            }
        }
        case "NE": {
        	if (y> 0 &&x < (textAdventure.boardHeight -1)) {
            	y -=1 ;
            	x +=1 ;
            	return "You Go North East";
            } else {
                return "Sorry, you are on the border.";
            }
        }
        case "SW": {
        	if (y < (textAdventure.boardHeight -1) && x> 0) {
            	y +=1 ;
            	x -=1 ;
            	return "You Go South West";
            } else {
                return "Sorry, you are on the border.";
            }
        }
        case "SE": {
        	if (y < (textAdventure.boardHeight -1) && x < (textAdventure.boardHeight -1)) {
            	y +=1 ;
            	x +=1 ;
            	return "You Go South East";
            } else {
                return "Sorry, you are on the border.";
            }
        }
        default: {
            throw new TextAdventureException("Unknown Command: " + direction);
        }
        }
	}

	public void look(String[][] map) {
        System.out.println("you are in : ("+x+","+y+")");
		for (String[] row : map) 
      	  
            System.out.println(Arrays.toString(row));
	}

	public ArrayList<String> inventory() {
		return Inventory;
	}

	public String take(String item,TextAdventure textAdventure) {
		for (ItemType element : textAdventure.Items){
	         if (element.id.contains(item) && textAdventure.map[y][x].contains(item)){	       		
	       			Inventory.add(item) ;
	       			textAdventure.map[y][x] = textAdventure.map[y][x].replace(item, "");
	       			return "You pick up the "+ item + " item" ;
	       		
	         }
	         else
	        	 return "Sorry you can not pick it" ;
	      }
		return "Sorry error";
	}

	public String drop(String item,TextAdventure textAdventure) throws TextAdventureException {
		if (Inventory.toString().contains(item)){	       		
   			Inventory.remove(item) ;
   			textAdventure.placeItem(item, x, y);
   			return "You droped the "+ item + " item" ;
   		
     }
     else
    	 return "Sorry you can not drop it" ;
	}

	public String convert(String item1, String item2,TextAdventure textAdventure) throws TextAdventureException {
		String res ="";
		if ((Inventory.toString().contains(item1) || textAdventure.map[y][x].contains(item1)) && (Inventory.toString().contains(item2) || textAdventure.map[y][x].contains(item2))){
		for (Composition element : textAdventure.Compositions){
	         if ((element.in1.contains(item1) && element.in2.contains(item2)) || (element.in1.contains(item2) && element.in2.contains(item1))){	       		
	        	 for (ItemType element2 : textAdventure.Items){
	    	         if (element2.id.toString().equals(element.out)){	       		
	    	       			Inventory.add(element.out) ;
	    	       			res+= "\nYou composed the "+ element.out + " item in your inventory" ;
	    	         }
	    	         
	    	      }
	        	 for (SceneryType element3 : textAdventure.Sceneries){
		        		if (element3.id.toString().equals(element.out) && !textAdventure.map[y][x].contains(element.out)) {
		    	    			textAdventure.placeItem(element.out, x, y);
		    	       			res+= "\nYou composed the "+ element.out + " item in your current place" ;
		    	         }
	        	 return res;
	        	 }

		} 
		}
		for (Transformation element4 : textAdventure.Transformations){
	         if ((element4.in1.contains(item1) && element4.in2.contains(item2)) || (element4.in1.contains(item2) && element4.in2.contains(item1))){	       		
	        	 for (ItemType element3 : textAdventure.Items){
	        		 if (element3.id.toString().equals(element4.out1)){	       		
	    	       			Inventory.add(element4.out1) ;
	    	       			res+= "\nYou composed the "+ element4.out1 + " item in your inventory" ;
	    	         }
	        		
	    	         if (element3.id.toString().equals(element4.out2)){	       		
	    	       			Inventory.add(element4.out2) ;
	    	       			res+= "\nYou composed the "+ element4.out2 + " item in your inventory" ;
	    	         }
	    	         
	    	      }
	        	 for (SceneryType element3 : textAdventure.Sceneries){
	        		if (element3.id.toString().equals(element4.out1) && !textAdventure.map[y][x].contains(element4.out1)) {
	    	    			textAdventure.placeItem(element4.out1, x, y);
	    	       			res+= "\nYou composed the "+ element4.out1 + " item in your current place" ;
	    	         }
	        		
	        		if (element3.id.toString().equals(element4.out2) && !textAdventure.map[y][x].contains(element4.out2)) {
    	    			res += "\nYou composed the "+ element4.out2 + " item in your current place" ;
    	    			textAdventure.placeItem(element4.out2, x, y);
	        		}
	    	      }
	         }

	      }
		}
		else
		{	
		res= "Sorry error";
		}
		return res;
	}

	public String decompose(String item,TextAdventure textAdventure) throws TextAdventureException {
		String res ="";
		if (Inventory.toString().contains(item) || textAdventure.map[y][x].contains(item) ){
			for (Decomposition element : textAdventure.Decompositions){
		         if (element.in.contains(item)){	       		
		        	 for (ItemType element2 : textAdventure.Items){
		    	         if (element2.id.toString().equals(element.out1)){	       		
		    	       			Inventory.add(element.out1) ;
		    	       			res+= "\nYou composed the "+ element.out1 + " item in your inventory" ;
		    	         }
		    	         if (element2.id.toString().equals(element.out2)){	       		
		    	       			Inventory.add(element.out2) ;
		    	       			res+= "\nYou composed the "+ element.out2 + " item in your inventory" ;
		    	         }
		    	      }
		        	 for (SceneryType element3 : textAdventure.Sceneries){
			        		if (element3.id.toString().equals(element.out1) && !textAdventure.map[y][x].contains(element.out1)) {
			    	    			textAdventure.placeItem(element.out1, x, y);
			    	       			res+= "\nYou composed the "+ element.out1 + " item in your current place" ;
			    	         }
			        		if (element3.id.toString().equals(element.out2) && !textAdventure.map[y][x].contains(element.out2)) {
		    	    			textAdventure.placeItem(element.out2, x, y);
		    	       			res+= "\nYou composed the "+ element.out2 + " item in your current place" ;
		    	         }		        	 }

			} 
			}	}
		return res;

}
	}
