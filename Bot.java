//https://gamedev.net/forums/topic/706443-othello-heuristic-method/5424386
//https://github.com/archilk/Othello
import java.util.*;
class Bot
{
	int color;
	int depth;
	static int weights[][]={
                {10, 2, 7, 7, 7, 7, 2, 10},
                {2, -4, 1, 1, 1, 1, -4, 2},
                {7, 1, 1, 1, 1, 1, 1, 7},
                {7, 1, 1, 1, 1, 1, 1, 7},
                {7, 1, 1, 1, 1, 1, 1, 7},
                {7, 1, 1, 1, 1, 1, 1, 7},
                {2, -4, 1, 1, 1, 1, -4, 2},
                {10, 2, 7, 7, 7, 7, 2, 10}};

	Bot(int c,int d)
	{
		color=c;
		depth=d;
	}

	int think(Board board,ArrayList<Integer> moves)
	{
		Board mindBoard;
		int i=0,l=moves.size();
		int max=Integer.MIN_VALUE,val=0;
		int finalMove=moves.get(0);
		int alpha=Integer.MIN_VALUE,beta=Integer.MAX_VALUE;
		for(;i<l;i++)
		{
			mindBoard=new Board(board);
			try
			{
				mindBoard.move(color,moves.get(i)/8,moves.get(i)%8);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			val=mini(mindBoard,1,alpha,beta);
			if(val>max)
			{
				max=val;
				finalMove=moves.get(i);
			}
			if(max>=beta)
				return finalMove;
			alpha=alpha>max?alpha:max;
		}
		return finalMove;
	}

	int mini(Board board,int d,int alpha,int beta)
	{
		if(d==depth)
			return heuristicValue(board);
		Board mindBoard;
		ArrayList<Integer> moves=board.findPossibleMoves(-color);
		if(moves.size()==0)
			return maxi(board,d+1,alpha,beta);
		int i=0,l=moves.size();
		int min=Integer.MAX_VALUE,val=0;
		for(;i<l;i++)
		{
			mindBoard=new Board(board);
			try
			{
				mindBoard.move(-color,moves.get(i)/8,moves.get(i)%8);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			val=maxi(mindBoard,d+1,alpha,beta);
			if(val<min)
				min=val;
			if(min<=alpha)
				return min;
			beta=beta<min?beta:min;
		}
		return min;
	}

	int maxi(Board board,int d,int alpha,int beta)
	{
		if(d==depth)
			return heuristicValue(board);
		Board mindBoard;
		ArrayList<Integer> moves=board.findPossibleMoves(color);
		if(moves.size()==0)
			return mini(board,d+1,alpha,beta);
		int i=0,l=moves.size();
		int max=Integer.MIN_VALUE,val=0;
		for(;i<l;i++)
		{
			mindBoard=new Board(board);
			try
			{
				mindBoard.move(color,moves.get(i)/8,moves.get(i)%8);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			val=mini(mindBoard,d+1,alpha,beta);
			if(val>max)
				max=val;
			if(max>=beta)
				return max;
			alpha=alpha>max?alpha:max;
		}
		return max;
	}

	int heuristicValue(Board board)    
	{
		return (5*mobilityHeuristic(board))+(25*coinParityHeuristic(board))+(50*stabilityHeuristic(board));
	}

		int stabilityHeuristic(Board board){ /*getHeuristic*/
		int theHeuristic = 0;
	
		 for (int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++) {
      
      if (board.grid[x][y] == color)  
      theHeuristic += weights[y][x];
      if (board.grid[x][y] == -color) 
      theHeuristic -= weights[y][x];
            
     }
    }
		    return theHeuristic;
	}

	int coinParityHeuristic(Board board)
	{
		int countMax=0,countMin=0;
		int i=0,j=0;
		for(;i<8;i++)
		{
			for(j=0;j<8;j++)
			{
				if(board.grid[i][j]==color)
					countMax++;
				else if(board.grid[i][j]==-color)
					countMin++;
			}
		}
		if(countMax+countMin!=0)
			return 100*(countMax-countMin)/(countMax+countMin);
		return 0;
	}

	int mobilityHeuristic(Board board)
	{
		ArrayList<Integer> max=board.findPossibleMoves(color);
		ArrayList<Integer> min=board.findPossibleMoves(-color);
		if(max.size()+min.size()!=0)
			return 100*(max.size()-min.size())/(max.size()+min.size());
		return 0;
	}
}
