package RealEightPuzzle;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EightPuzzle 
{
    int xPos;
    int yPos;

    int length;
    int[][] board;
    int distFromStart; 
    

    public EightPuzzle(int length, int distFromStart)
    {
        
        this.length=length;
        List <Integer> list = new ArrayList<>();
        for(int i =0;i<length*length;i++)
        {
            list.add(i);
        }
        
        Collections.shuffle(list);

        board = new int[length][length];
        int count=0;
        for(int i =0;i<length;i++)
        {
            for(int j=0;j<length;j++)
            {
                board[i][j]=list.get(count);
                count++;   
            }
        }
        findBlankZeroXPos();
        findBlankZeroYPos();
        
    }
    
    public EightPuzzle(int[][] Array, int distFromStart)
    {
        this.distFromStart=distFromStart;
        length=Array.length;
        board = new int[length][length];
       
        for(int i =0;i<length;i++)
        {
            for(int j=0;j<length;j++)
            {
                board[i][j]=Array[i][j];
               
            }
        }
    }


    @Override
    public String toString()
    {
        String list = "";
        for(int[]i:board)
        {
            list+="[";
            for(int j:i)
            {
                list+=j+", ";
            }
            list=list.substring(0,list.length()-2);
            list+="]\n";
        }
        return list;
    }

    public void findBlankZeroXPos()
    {
        for(int i =0;i<length;i++)
        {
            for(int j =0;j<length;j++)
            {
                if(board[i][j]==0)
                {
                    xPos=j;
                    break;
                }
            }
        }
    }

    public void findBlankZeroYPos()
    {
        for(int i =0;i<length;i++)
        {
            for(int j =0;j<length;j++)
            {
                if(board[i][j]==0)
                {
                    yPos=i;
                    break;
                }
            }
        }
    }
    
    public int findBlankYPos(int num)
    {
        for(int i =0;i<length;i++)
        {
            for(int j =0;j<length;j++)
            {
                if(board[i][j]==num)
                {
                    return i;
                }
            }
        }
        return 0;
    }
    public int findBlankXPos(int num)
    {
        for(int i =0;i<length;i++)
        {
            for(int j =0;j<length;j++)
            {
                if(board[i][j]==num)
                {
                    return j;
                }
            }
        }
        return 0;
    }

    public int getDistFromStart()
    {
        return distFromStart;
    }
    
    public String getXYPos()
    {
        return xPos+" "+yPos;
    }

    public int getXPos()
    {
        return xPos;
    }
    public int getYPos()
    {
        return yPos;
    }
    public int[][] getBoard()
    {
        return board;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EightPuzzle other = (EightPuzzle) obj;
        return Arrays.deepEquals(this.board, other.board);
    }

@Override
public int hashCode() {
    return Arrays.deepHashCode(this.board);
}

}
