import java.util.Scanner;

public class Main {
	
	static void locateShip(String[][] grid,int x,int y,String orient) {
		String boat[]=new String[] {"X","X"};
		
		grid[x][y]=boat[0];
		if ( orient == "h" ) {  	// horizontal;
			grid[x][y+1]=boat[1];
			display(grid);
		}
		else if ( orient == "v" ) { // vertical
			grid[x+1][y]=boat[0];
			display(grid);
		}
	
		
	}
	
	static void display(String[][] grid) {
		System.out.println("    A B C D E");
		System.out.println("    ---------");
		for(int i=0;i<5;i++) {
			System.out.print(i+" | ");
			for(int j=0;j<5;j++) {
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

		public static void main(String args[]) {
			String[][] grid=new String[10][10];
			
			System.out.println("    A B C D E");
			System.out.println("    ---------");
			for(int i=0;i<5;i++) {
				System.out.print(i+"  |");
				for(int j=0;j<5;j++) {
					grid[i][j]="~";
					System.out.print(grid[i][j]+" ");
				}
				System.out.println();
			}
			int x=0,y=3;
			String orient="v";
			locateShip(grid,x,y,orient);
			
			while(true) {
				 System.out.println("Where to hit?");

				 Scanner obj=new Scanner(System.in);
				 String s=obj.nextLine();
				 if ( s.length() != 2 ) { 
					 System.out.println("Incorrect cell"); 
					 return;
				 }
				 
				 char[] a=s.toCharArray();
				 int cellno = a[1] - '0';  
//				 System.out.println("cellno: "+cellno);  
				  // a - 0 , b - 1  , c - 2  , d - 3  , e - 4
	             int key=0;
	             switch(a[0]) {
	             case 'a': key=0; break;
	             case 'b': key=1; break;
	             case 'c': key=2; break;
	             case 'd': key=3; break;
	             case 'e': key=4; break;
	             default: System.out.println("Invalid type");
	             }
	            
				 if ( grid[cellno-1][key] == "X" ) System.out.println("HIT!");
				 else  {
					 grid[cellno-1][key]="M";
					 System.out.println("MISS");
				 }
				 System.out.println();
				 display(grid);
				 
			}			 
		}
}
