package RealEightPuzzle;
import java.util.ArrayList;

public class Main {
    public static void main(String[]args)
    {
        EightPuzzle puzzle = new EightPuzzle(3);
        EightPuzzle endPuzzle = new EightPuzzle(3);
        System.out.println("Start: ");
        System.out.println(puzzle.toString());
        System.out.println("End: ");
        System.out.println(endPuzzle.toString());
        //System.out.println(puzzle.getXYPos());
        int[][] testArray= {{0,1,2},{3,4,5},{6,7,8}}; 
        EightPuzzle testPuzzle = new EightPuzzle(testArray);
        //System.out.println(testPuzzle.toString());

        Traveller traveller = new Traveller(puzzle, endPuzzle);
        ArrayList<EightPuzzle> adjNodes= traveller.findAdjNodes(puzzle);
        for(int i =0;i<adjNodes.size();i++)
        {
            System.out.println(adjNodes.get(i).toString());
        }

        System.out.println();
        //System.out.println(traveller.findAdjNodes().get(0).toString());
        System.out.println(traveller.cost(puzzle));

        //System.out.println(traveller.travel(puzzle).toString());
    }
}
