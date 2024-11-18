package RealEightPuzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.awt.Point;

public class NGameNode { 
    int cost;
    EightPuzzle current;
    EightPuzzle start;
    EightPuzzle end;
   
    
    ArrayList<Point>  positionsStart= new ArrayList<>();
    ArrayList<Point>  positionsEnd= new ArrayList<>();
    public NGameNode(EightPuzzle current, EightPuzzle start, EightPuzzle end)
    {

        this.current=current;
        this.start=start;
        this.end=end;
        
        for(int i =0;i<start.length*start.length; i++)
        {
            positionsEnd.add(new Point(end.findBlankXPos(i),(end.findBlankYPos(i))));
        }
        for(int i =0;i<start.length*start.length; i++)
        {
            positionsStart.add(new Point(start.findBlankXPos(i),(start.findBlankYPos(i))));
        }
        
            
    }

    public int cost(EightPuzzle current,EightPuzzle start, EightPuzzle end)
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
    public int cost2(EightPuzzle current)
    {
        return current.getDistFromStart();
    }
    public int totalCost(NGameNode current)
    {
        return cost(current.getCurrent(), current.getStart(), current.getEnd()) + cost2(current.getCurrent());
    }
/* 
    @Override
    public int compare(NGameNode puzzle, NGameNode puzzle2)
    {
        return Integer.compare(totalCost(puzzle.getCurrent()), totalCost(puzzle2.getCurrent()));
    }*/
    public static final Comparator<NGameNode> costComparator = new Comparator<NGameNode>() {
        @Override
        public int compare(NGameNode node1, NGameNode node2) {
            return Integer.compare(node1.totalCost(node1), node2.totalCost(node2));
        }
    };
public EightPuzzle getCurrent()
{
    return current;
}
public EightPuzzle getStart()
{
    return start;
}
public EightPuzzle getEnd()
{
    return end;
}
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    NGameNode other = (NGameNode) obj;

    return Arrays.deepEquals(this.getCurrent().getBoard(), other.getCurrent().getBoard());
}

@Override
public int hashCode() {
    return Arrays.deepHashCode(this.getCurrent().getBoard());
}
}
