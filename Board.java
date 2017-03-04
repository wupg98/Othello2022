import java.util.*;
import javax.swing.JOptionPane;
class Board
{
	int grid[][];
	Board()
	{
		grid=new int[8][8];
		grid[3][3]=1;
		grid[4][4]=1;
		grid[3][4]=-1;
		grid[4][3]=-1;
	}

	Board(Board b)
	{
		int i=0,j=0;
		grid=new int[8][8];
		for(i=0;i<8;i++)
		{
			for(j=0;j<8;j++)
				grid[i][j]=b.grid[i][j];
		}
	}

	ArrayList<Integer> findPossibleMoves(int color)
	{
		ArrayList<Integer> ans=new ArrayList<>();
		int i=0,j=0,k=0,l=0,val=0;
		for(i=0;i<8;i++)
		{
			for(j=0;j<8;j++)
			{
				if(grid[i][j]!=0)
					continue;
				if(j>0&&grid[i][j-1]==-color)
				{
					k=j-2;
					while(k>=0&&grid[i][k]==-color)
						k--;
					if(k>=0&&grid[i][k]!=0)
					{
						ans.add(i*8+j);
						continue;
					}
				}
				if(j<7&&grid[i][j+1]==-color)
				{
					k=j+2;
					while(k<=7&&grid[i][k]==-color)
						k++;
					if(k<=7&&grid[i][k]!=0)
					{
						ans.add(i*8+j);
						continue;
					}
				}
				if(i>0&&grid[i-1][j]==-color)
				{
					k=i-2;
					while(k>=0&&grid[k][j]==-color)
						k--;
					if(k>=0&&grid[k][j]!=0)
					{
						ans.add(i*8+j);
						continue;
					}
				}
				if(i<7&&grid[i+1][j]==-color)
				{
					k=i+2;
					while(k<=7&&grid[k][j]==-color)
						k++;
					if(k<=7&&grid[k][j]!=0)
					{
						ans.add(i*8+j);
						continue;
					}
				}
				if(j<7&&i<7&&grid[i+1][j+1]==-color)
				{
					k=i+2;
					l=j+2;
					while(k<=7&&l<=7&&grid[k][l]==-color)
					{
						k++;
						l++;
					}
					if(k<=7&&l<=7&&grid[k][l]!=0)
					{
						ans.add(i*8+j);
						continue;
					}
				}
				if(j>0&&i>0&&grid[i-1][j-1]==-color)
				{
					k=i-2;
					l=j-2;
					while(k>=0&&l>=0&&grid[k][l]==-color)
					{
						k--;
						l--;
					}
					if(k>=0&&l>=0&&grid[k][l]!=0)
					{
						ans.add(i*8+j);
						continue;
					}
				}
				if(j<7&&i>0&&grid[i-1][j+1]==-color)
				{
					k=i-2;
					l=j+2;
					while(k>=0&&l<=7&&grid[k][l]==-color)
					{
						k--;
						l++;
					}
					if(k>=0&&l<=7&&grid[k][l]!=0)
					{
						ans.add(i*8+j);
						continue;
					}
				}
				if(j>0&&i<7&&grid[i+1][j-1]==-color)
				{
					k=i+2;
					l=j-2;
					while(k<=7&&l>=0&&grid[k][l]==-color)
					{
						k++;
						l--;
					}
					if(k<=7&&l>=0&&grid[k][l]!=0)
					{
						ans.add(i*8+j);
						continue;
					}
				}
			}
		}
		return ans;
	}

	void move(int color,int x,int y)throws Exception
	{
		if(x<0||x>7||y<0||y>7)
			throw new Exception("Out of bounds");
		if(grid[x][y]!=0)
			throw new Exception("Already a piece there");
		boolean flag=false;
		int i=0,j=0;
		if(x>0&&grid[x-1][y]==-color)
		{
			i=x-1;
			while(i>=0&&grid[i][y]==-color)
				i--;
			if(i>=0&&grid[i][y]!=0)
			{
				flag=true;
				int ii=x-1;
				while(ii>i)
					grid[ii--][y]=color;
			}
		}
		if(x<7&&grid[x+1][y]==-color)
		{
			i=x+1;
			while(i<=7&&grid[i][y]==-color)
				i++;
			if(i<=7&&grid[i][y]!=0)
			{
				flag=true;
				int ii=x+1;
				while(ii<i)
					grid[ii++][y]=color;
			}
		}
		if(y>0&&grid[x][y-1]==-color)
		{
			j=y-1;
			while(j>=0&&grid[x][j]==-color)
				j--;
			if(j>=0&&grid[x][j]!=0)
			{
				flag=true;
				int jj=y-1;
				while(jj>j)
					grid[x][jj--]=color;
			}
		}
		if(y<7&&grid[x][y+1]==-color)
		{
			j=y+1;
			while(j<=7&&grid[x][j]==-color)
				j++;
			if(j<=7&&grid[x][j]!=0)
			{
				flag=true;
				int jj=y+1;
				while(jj<j)
					grid[x][jj++]=color;
			}
		}
		if(x<7&&y<7&&grid[x+1][y+1]==-color)
		{
			i=x+1;
			j=y+1;
			while(i<=7&&j<=7&&grid[i][j]==-color)
			{
				i++;
				j++;
			}
			if(i<=7&&j<=7&&grid[i][j]!=0)
			{
				flag=true;
				int ii=x+1,jj=y+1;
				while(ii<i)
					grid[ii++][jj++]=color;
			}
		}
		if(x>0&&y>0&&grid[x-1][y-1]==-color)
		{
			i=x-1;
			j=y-1;
			while(i>=0&&j>=0&&grid[i][j]==-color)
			{
				i--;
				j--;
			}
			if(i>=0&&j>=0&&grid[i][j]!=0)
			{
				flag=true;
				int ii=x-1,jj=y-1;
				while(ii>i)
					grid[ii--][jj--]=color;
			}
		}
		if(x<7&&y>0&&grid[x+1][y-1]==-color)
		{
			i=x+1;
			j=y-1;
			while(i<=7&&j>=0&&grid[i][j]==-color)
			{
				i++;
				j--;
			}
			if(i<=7&&j>=0&&grid[i][j]!=0)
			{
				flag=true;
				int ii=x+1,jj=y-1;
				while(ii<i)
					grid[ii++][jj--]=color;
			}
		}
		if(x>0&&y<7&&grid[x-1][y+1]==-color)
		{
			i=x-1;
			j=y+1;
			while(i>=0&&j<=7&&grid[i][j]==-color)
			{
				i--;
				j++;
			}
			if(i>=0&&j<=7&&grid[i][j]!=0)
			{
				flag=true;
				int ii=x-1,jj=y+1;
				while(ii>i)
					grid[ii--][jj++]=color;
			}
		}
		if(!flag)
			throw new Exception("Not a legal square");
		grid[x][y]=color;
	}

	void displayResult()
	{
		int black=0;
		int white=0;
		int i=0,j=0;
		for(i=0;i<8;i++)
		{
			for(j=0;j<8;j++)
			{
				if(grid[i][j]==1)
					white++;
				else if(grid[i][j]==-1)
					black++;
			}
		}
		String str="";
		str=str+"White: "+white+'\n';
		str=str+"Black: "+black+'\n';
		if(white>black)
			str=str+"White wins!";
		else if(black>white)
			str=str+"Black wins!";
		else
			str=str+"It's a draw!";
		JOptionPane.showMessageDialog(null,str);
	}
}