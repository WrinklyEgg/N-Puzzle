package RealEightPuzzle;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Traveller {
    int xPos;
    int yPos;
    
    int[][] board;
    EightPuzzle start;
    EightPuzzle end;
    EightPuzzle blank;
    HashSet<EightPuzzle> visitedNodes;
    
    ArrayList<Point>  positionsEnd= new ArrayList<>();

    public Traveller(EightPuzzle start, EightPuzzle end)
    {
        this.start=start;
        this.end=end;
        
        visitedNodes = new HashSet<>();
        visitedNodes.add(start);
        for(int i =0;i<start.length*start.length; i++)
        {
            positionsEnd.add(new Point(end.findBlankXPos(i),(end.findBlankYPos(i))));
        }
      
        //System.out.println(positionsEnd.toString());
        int[][] blankList = new int[start.length][start.length];
        blank = new EightPuzzle(blankList,0);
    }

    
    
    public ArrayList<EightPuzzle> findAdjNodes(EightPuzzle puzzle)
    {
        puzzle.findBlankZeroXPos();
        puzzle.findBlankZeroYPos();
        xPos=puzzle.getXPos();
        yPos=puzzle.getYPos();
        board= puzzle.getBoard();
        int adjDistFromStart = puzzle.getDistFromStart()+1;
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
            EightPuzzle newPuzzle = new EightPuzzle(adjPuzzle,adjDistFromStart);
            if(!visitedNodes.contains(newPuzzle))
            {
                adjNodes.add(newPuzzle);
            }
            
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
            EightPuzzle newPuzzle = new EightPuzzle(adjPuzzle, adjDistFromStart);
            if(!visitedNodes.contains(newPuzzle))
            {
                adjNodes.add(newPuzzle);
            }
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
            EightPuzzle newPuzzle = new EightPuzzle(adjPuzzle, adjDistFromStart);

            if(!visitedNodes.contains(newPuzzle))
            {
                adjNodes.add(newPuzzle);
            }
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
            EightPuzzle newPuzzle = new EightPuzzle(adjPuzzle, adjDistFromStart);
            if(!visitedNodes.contains(newPuzzle))
            {
                adjNodes.add(newPuzzle);
            }
            //System.out.println("Board: \n" + Arrays.deepToString(board).replace("], ", "]\n")+"\n");
        }
        return adjNodes;
        
    }

    public ReturnInfo travel(NGameNode startNode)
    {
        long startTime = System.nanoTime();
        PriorityQueue<NGameNode> queue = new PriorityQueue<>(1, NGameNode.costComparator);
        
        //adds starting node to queue
        queue.add(startNode);
        //Might be unnecessary since added start to queue in constructor
        visitedNodes.add(startNode.getCurrent());
        
        //to see how many times it's run
        int workingCount=1;
        //While the queue is not empty
        while(!queue.isEmpty())
        {
            //removes the lowest cost node and sets start2 to it
            NGameNode start2 = queue.poll();
            
            visitedNodes.add(start2.getCurrent());
            
            if(start2.getCurrent().equals(start2.getEnd()))
            {
                System.out.println("Goal Found!");
                return new ReturnInfo(System.nanoTime()-startTime, workingCount);
            }
            ArrayList<EightPuzzle>  adjNodes = findAdjNodes(start2.getCurrent());
            for(int i =0;i<adjNodes.size();i++)
            {
                if(!visitedNodes.contains(adjNodes.get(i)))
                {
                    queue.add(new NGameNode(adjNodes.get(i),startNode.getStart(),startNode.getEnd()));
                }
            }
            if(adjNodes.isEmpty()&&queue.isEmpty())
            {
                System.out.println("Unfindable cause no adjacent");
                return new ReturnInfo(System.nanoTime()-startTime, workingCount);
            }
            
            //
            //System.out.println("working "+workingCount+"\n"+start2.getCurrent().toString());
            workingCount++;
            /*
            if(workingCount>180000)
        {
            System.out.println("Unfindable cause ran for too long");
            return blank;
        }
             */
        }
        
        System.out.println("Unfindable cause queue empty");
        return new ReturnInfo(System.nanoTime()-startTime, workingCount);
    }

    public int getInversionCount(EightPuzzle puzzle)
    {
        int linearPuzzle[];
        linearPuzzle = new int[puzzle.length*puzzle.length];
        int k = 0;
        for(int i=0; i<puzzle.length; i++)
        {
            for(int j=0; j<puzzle.length; j++)
            {
                linearPuzzle[k++] = puzzle.getBoard()[i][j];
            }
        }
       
        int inv_count = 0;
        for (int i = 0; i < puzzle.length*puzzle.length; i++)
        {
            for (int j = i + 1; j < puzzle.length*puzzle.length; j++)
            {
                if (linearPuzzle[i] > 0 && linearPuzzle[j] > 0 && linearPuzzle[i] > linearPuzzle[j])
                            
                inv_count++;
            }
        
        }
        return inv_count;
    }

    public boolean findPossible()
    {
        int invCountStart= getInversionCount(start);
        int invCountEnd= getInversionCount(end);

        return (invCountStart%2==0 && invCountEnd%2==0)||(invCountStart%2==1 && invCountEnd%2==1);
    }
    //travel method but doesn't work yet
     /* 
    public EightPuzzle travel(EightPuzzle node)
    {
        if(node==null)
        {
            System.out.println("couldn't find goal");
            return blank;
        }
        visitedNodes.add(node);
        ArrayList<EightPuzzle> adjNodes = findAdjNodes(node);
        if(adjNodes.isEmpty())
        {
            return null;
        }
        Point lowestCost= new Point(0,cost(adjNodes.get(0)));
        if(cost(node)==0)
        {
            System.out.println("Goal Found!");
            return node;
        }
        else{ 
            for(int i =0;i<adjNodes.size();i++)
            {
                if(visitedNodes.contains(adjNodes.get(i)))
                {
                    continue;
                }
                if(cost(adjNodes.get(i))==0)
                {
                    System.out.println("Goal Found!");
                    return adjNodes.get(i);
                }
                else if(cost(adjNodes.get(i))<lowestCost.getY())
                {
                    lowestCost.setLocation(i, cost(adjNodes.get(i)));
                    //System.out.println(lowestCost.getX()+" "+lowestCost.getY());
                } 
            }
            //System.out.println(adjNodes.get((int)(lowestCost.getX())));
            if(lowestCost.getX()==0)
            {
                System.out.println("Couldn't find goal");
                return null;
            }
            if(!visitedNodes.contains(adjNodes.get((int)(lowestCost.getX()))))
            {
                return travel(adjNodes.get((int)(lowestCost.getX())));
            }
            
            return blank;
        
        }
            
       
    }
        */


        
 }
