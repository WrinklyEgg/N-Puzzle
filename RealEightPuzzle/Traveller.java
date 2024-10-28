package RealEightPuzzle;

import java.awt.Point;
import java.util.ArrayList;
public class Traveller {
    int xPos;
    int yPos;
    int movesFromStart;
    int[][] board;
    EightPuzzle start;
    EightPuzzle end;
    String travelPath;
    EightPuzzle blank;
    //ArrayList<Point>  positionsStart= new ArrayList<>();
    ArrayList<Point>  positionsEnd= new ArrayList<>();
    public Traveller(EightPuzzle start, EightPuzzle end)
    {
        this.start=start;
        this.end=end;
        travelPath="";
        movesFromStart=0;
       
        for(int i =0;i<start.length*start.length; i++)
        {
            positionsEnd.add(new Point(end.findBlankXPos(i),(end.findBlankYPos(i))));
        }
        System.out.println(positionsEnd.toString());
        blank = new EightPuzzle(3);
    }

    
    public ArrayList<EightPuzzle> findAdjNodes(EightPuzzle puzzle)
    {
        xPos=puzzle.getXPos();
        yPos=puzzle.getYPos();
        board= puzzle.getBoard();

        ArrayList<EightPuzzle> adjNodes= new ArrayList<>();
        boolean leftPossible=true;
        boolean rightPossible=true;
        boolean upPossible=true;
        boolean downPossible=true;
     
        if(xPos==0)
        {
            leftPossible=false;
        }
        if(xPos==board[0].length-1)
        {
            rightPossible=false;
        }
        if(yPos==0)
        {
            upPossible=false;
        }
        if(yPos==board[0].length-1)
        {
            downPossible=false;
        }
        if(leftPossible)
        {

            int[][] adjPuzzle= new int[board.length][board.length];
            for(int i =0;i<board.length;i++)
            {
                for(int j=0;j<board.length;j++)
                {
                    adjPuzzle[i][j]=board[i][j];
                }
            }
            int temp=adjPuzzle[yPos][xPos-1];
            adjPuzzle[yPos][xPos-1]=0;
            adjPuzzle[yPos][xPos]=temp;
            EightPuzzle newPuzzle = new EightPuzzle(adjPuzzle);
            adjNodes.add(newPuzzle);
            //System.out.println("Board: \n" + Arrays.deepToString(board).replace("], ", "]\n")+"\n");
        }
        if(rightPossible)
        {
            int[][] adjPuzzle= new int[board.length][board.length];
            for(int i =0;i<board.length;i++)
            {
                for(int j=0;j<board.length;j++)
                {
                    adjPuzzle[i][j]=board[i][j];
                }
            }
            int temp=adjPuzzle[yPos][xPos+1];
            adjPuzzle[yPos][xPos+1]=0;
            adjPuzzle[yPos][xPos]=temp;
            EightPuzzle newPuzzle = new EightPuzzle(adjPuzzle);
            adjNodes.add(newPuzzle);
            //System.out.println("Board: \n" + Arrays.deepToString(board).replace("], ", "]\n")+"\n");
        }
        //System.out.println("Board: \n" + Arrays.deepToString(board).replace("], ", "]\n")+"\n");
        if(upPossible)
        {
            int[][] adjPuzzle= new int[board.length][board.length];
            for(int i =0;i<board.length;i++)
            {
                for(int j=0;j<board.length;j++)
                {
                    adjPuzzle[i][j]=board[i][j];
                }
            }
            int temp=adjPuzzle[yPos-1][xPos];
            adjPuzzle[yPos-1][xPos]=0;
            adjPuzzle[yPos][xPos]=temp;
            EightPuzzle newPuzzle = new EightPuzzle(adjPuzzle);
            adjNodes.add(newPuzzle);
            //System.out.println("Board: \n" + Arrays.deepToString(board).replace("], ", "]\n")+"\n");
        }
        if(downPossible)
        {
            int[][] adjPuzzle= new int[board.length][board.length];
            for(int i =0;i<board.length;i++)
            {
                for(int j=0;j<board.length;j++)
                {
                    adjPuzzle[i][j]=board[i][j];
                }
            }
            int temp=adjPuzzle[yPos+1][xPos];
            adjPuzzle[yPos+1][xPos]=0;
            adjPuzzle[yPos][xPos]=temp;
            EightPuzzle newPuzzle = new EightPuzzle(adjPuzzle);
            adjNodes.add(newPuzzle);
            //System.out.println("Board: \n" + Arrays.deepToString(board).replace("], ", "]\n")+"\n");
        }
        return adjNodes;
        
        
    }

    public int cost(EightPuzzle current)
    {
        int totalDist=0;
        for(int i=0;i<current.length;i++)
        {
            for(int j =0;j<current.length;j++)
            {
                int startPoint=current.getBoard()[i][j];

                int yDist= (int)Math.abs(i-(positionsEnd.get(startPoint).getY()));
                int xDist = (int)Math.abs(j-(positionsEnd.get(startPoint).getX()));
                totalDist+=yDist+xDist;
            }
        }

        return totalDist;
    }

    public EightPuzzle travel(EightPuzzle node)
    {
        ArrayList<EightPuzzle> adjNodes = findAdjNodes(node);
        
        Point lowestCost= new Point(0,cost(adjNodes.get(0)));
        if(cost(node)==0)
        {
            System.out.println("Goal Found!");
            return node;
        }
        else{ 
            for(int i =0;i<adjNodes.size();i++)
            {
                if(cost(adjNodes.get(i))==0)
                {
                    travel(adjNodes.get(i));
                }
                else if(cost(adjNodes.get(i))<lowestCost.getY())
                {
                    lowestCost.setLocation(i, cost(adjNodes.get(i)));
                } 
            }
            travel(adjNodes.get((int)lowestCost.getX()));
        }
            
            return blank;
        }
       
    }
