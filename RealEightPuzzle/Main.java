package RealEightPuzzle;

import java.util.Scanner;

public class Main {
    public static void main(String[]args)
    {
        Scanner scanner = new Scanner(System.in);
      

        int noCount=0;
        int yesCount=0;
        System.out.println("Type the number of 8 games you wanna solve: ");
        int numToSolve = scanner.nextInt();
        scanner.nextLine();
        double totalExeTime=0;
        for(int b =0;b<numToSolve;b++)
        {

            int userPuzzleLen=3;
           // int userPuzzleLen=2;
            EightPuzzle puzzle=new EightPuzzle(userPuzzleLen,0);
            //System.out.println("Your Puzzle:\n"+puzzle.toString());
            /* 
            int fact = userPuzzleLen*userPuzzleLen;
            for(int i =fact-1;i>0;i--)
            {
                fact*=i;
            }
            System.out.println("The number of possible states is: "+ fact);
            */
            EightPuzzle endPuzzle = new EightPuzzle(userPuzzleLen,0);
            System.out.println("Start: ");
            System.out.println(puzzle.toString());
            System.out.println("End: ");
            System.out.println(endPuzzle.toString());
           
           // CostFinder cFinder = new CostFinder()
            NGameNode firstNode = new NGameNode(puzzle,puzzle, endPuzzle);
            Traveller traveller = new Traveller(puzzle, endPuzzle);
            //System.out.println("cost: "+firstNode.totalCost(firstNode));
            if(traveller.findPossible())
            {
                System.out.println("Path possible");
                ReturnInfo rInfo = new ReturnInfo(traveller.travel(firstNode));
                System.out.println(rInfo.toString());
                totalExeTime+=rInfo.getExeTimeTruncated();

                yesCount++;
            }
            else{
                System.out.println("There is no path between the start and end, generating new puzzles: ");
                noCount++;
            }
            //System.out.println(traveller.travel(firstNode).toString());
            if(!(b==(numToSolve-1)))
            {
                System.out.println("Number Solved: " + (b+1));
                System.out.println("Type anything to run the next round: ");
                scanner.nextLine();
            }
            else{
                System.out.println("Number Solved: " + (b+1));
                System.out.println("-----------------------------------------");
            System.out.println("That's all the rounds, hope you had fun! :)");
            }
            
            
        }
        System.out.println("Number passed: "+ yesCount+"\nNumber not Passed: "+noCount);
        System.out.println("Total Execution Time: "+ totalExeTime+" milliseconds\nAverage Execution Time: "+ ((double)((int)((totalExeTime/numToSolve)*10)))/10+" milliseconds");               
        /* 
        ArrayList<EightPuzzle> adjNodes2= traveller.findAdjNodes(adjNodes.get(0));
        System.out.println("Start: ");
        System.out.println(adjNodes.get(0).toString());
        for(int i =0;i<adjNodes2.size();i++)
        {
            System.out.println("adjacent #"+(i+1));
            System.out.println(adjNodes2.get(i).toString());
        }
        System.out.println();
     */
       
    
    }

   
}
