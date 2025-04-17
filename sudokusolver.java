package sudokusolver;

public class sudokusolver {
	public static void main(String[] args)
	{
		int[][] sudoku = {
				{7,0,2,0,5,0,6,0,0},
				{0,0,0,0,0,3,0,0,0},
				{1,0,0,0,0,9,5,0,0},
				{8,0,0,0,0,0,0,9,0},
				{0,4,3,0,0,0,7,5,0},
				{0,9,0,0,0,0,0,0,8},
				{0,0,9,7,0,0,0,0,5},
				{0,0,0,2,0,0,0,0,0},
				{0,0,7,0,4,0,2,0,3}
		};
		System.out.print("Before solving");
		print(sudoku);
		if(solve(sudoku))
		{
			System.out.print("Solved successfully");
			print(sudoku);
		}else {
			System.out.println("Unsolvable");
		}
	}
	static void print(int[][] board)
	{
		System.out.println("");
			for(int i=0;i<9;i++)
			{
				if(i%3 == 0 && i!=0)
					System.out.println("------------------");
				for(int j=0;j<9;j++)
				{
					if(j%3 == 0 && j!=0)
						System.out.print("|");
					System.out.print(board[i][j]+" ");
				}
				
				System.out.println("");
			}
	}
	static boolean  solve(int[][] board)
	{
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)
			{  
				if(board[i][j] == 0)
				{
					for(int no =1;no<=9;no++)
					{
						if(safe(board,no,i,j))
						{
							board[i][j] = no;
						    if(solve(board))
							    return true;
						    else {
							   board[i][j] = 0;
						   }
						}
					}
					return false;
				}
			}
			
		}
		return true;
	}
	static boolean safe(int[][] board,int no,int row,int col)
	{
		for(int i =0; i<9;i++)
		{
			if(board[row][i] == no )
				return false;
			if(board[i][col] == no)
				return false;
			if(board[3*(row/3) + i/3][3*(col/3) + i%3] == no)
				return false;
		}
		return true;
	}

	

}
