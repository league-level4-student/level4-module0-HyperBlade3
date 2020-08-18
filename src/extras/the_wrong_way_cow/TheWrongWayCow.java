//https://www.codewars.com/kata/the-wrong-way-cow
//
//Task
//Given a field of cows find which one is the Wrong-Way Cow and return her position.
//
//Notes:
//
//There are always at least 3 cows in a herd
//There is only 1 Wrong-Way Cow!
//Fields are rectangular
//The cow position is zero-based [x,y] of her head (i.e. the letter c)
//Examples
//Ex1
//
//cow.cow.cow.cow.cow
//cow.cow.cow.cow.cow
//cow.woc.cow.cow.cow
//cow.cow.cow.cow.cow
//Answer: [6,2]
//
//Ex2
//
//c..........
//o...c......
//w...o.c....
//....w.o....
//......w.cow
//Answer: [8,4]
//
//Notes
//The test cases will NOT test any situations where there are "imaginary" cows, so your solution does not need to worry about such things!
//
//To explain - Yes, I recognize that there are certain configurations where an "imaginary" cow may appear that in fact is just made of three other "real" cows.
//In the following field you can see there are 4 real cows (3 are facing south and 1 is facing north). There are also 2 imaginary cows (facing east and west).
//
//...w...
//..cow..
//.woco..
//.ow.c..
//.c.....

package extras.the_wrong_way_cow;

import java.util.HashMap;

public class TheWrongWayCow {

	public static int[] findWrongWayCow(final char[][] field) {
		// head (letter 'c') of the wrong way cow!\

		HashMap<String, int[]> map = new HashMap<String, int[]>(4);
		
		int[] rowColumn = null;
		
		
		
		int northCount = 0;
		int southCount = 0;
		int eastCount = 0;
		int westCount = 0;
		

		for (int i = 0; i < field.length-2; i++) {
			for (int j = 0; j < field[i].length-2; j++) {

				if (map.size() > 1 && (northCount + southCount + eastCount + westCount)  > 2) {
					i = field.length;
					break;
				}
				
				if (field[i][j] == 'c') {
					int numRows = field.length;
					int numCols = field[i].length;
					
					//North
					if (northCount < 2) {
						if (i+2 < numRows && field[i+1][j] == 'o' && field[i+2][j] == 'w') {
							map.put("north", new int[] {j, i});
							northCount++;
							continue;
						}
					}
					//South
					if (southCount < 2) {
						if (i-2 >= 0 && field[i-1][j] == 'o' && field[i-2][j] == 'w') {
							map.put("south", new int[] {j, i});
							southCount++;
							continue;
						}
					}
					//West
					if (westCount < 2) {
						if (j + 2 < numCols && field[i][j+1] == 'o' && field[i][j+2] == 'w') {
							map.put("west", new int[] {j, i});
							westCount++;
							continue;
						}
					}
					//East
					if (eastCount < 2) {
						if (j-2 >= 0 && field[i][j-1] == 'o' && field[i][j-2] == 'w') {
							map.put("east", new int[] {j, i});
							eastCount++;
							continue;
						}
					}
				}
				
			}

		}
		
		if (eastCount == 1) {
			rowColumn = map.get("east");
			System.out.println("east");
			System.out.println(map.get("east"));
		}
		else if (westCount == 1) {
			rowColumn = map.get("west");
			System.out.println("west");
			System.out.println(map.get("weset"));
		}
		else if (southCount == 1) {
			rowColumn = map.get("south");
			System.out.println("south");
			System.out.println(map.get("south"));
		}
		else {
			rowColumn = map.get("north");
			System.out.println("north");
			System.out.println(map.get("north"));
		}
		
		return rowColumn;
	}
}