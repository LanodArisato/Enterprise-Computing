/*
Name: Landon Morjal
Course: CNT 4714 Spring 2025
Assignment title: Project 2 – Multi-threaded programming in Java
Date: February 15, 2025
Class: Driver
Description: Main control of program. Reads input files, initiates threads and thread pool, and handles final output report.
*/
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver 
{
    private static final int MAXTRAINS = 30;
    private static final int MAXSWITCHES = 10;
    private static final int MAXPLANS = 60;

    private static HashMap<String, String> yardMap = new HashMap<>();
    private static ArrayList<Train> fleetList = new ArrayList<>();

    private static Switch[] switchArr = new Switch[MAXSWITCHES];
    public static void main(String[] args) throws IOException
    {
        scanIn(); //read inputs files

        for (int i = 0; i < MAXSWITCHES; i++) //setup all switches and store for easy access
        {
            switchArr[i] = new Switch(i + 1);
        }

        ExecutorService trainPool = Executors.newFixedThreadPool(MAXTRAINS); //create thread pool for thread operation

        System.out.println("\n\n$ $ $ TRAIN MOVEMENT SIMULATION BEGINS........... $ $ $\n\n");
        for (int i = 0; i < fleetList.size(); i++)  //begins thread execution for every train in fleet 
        {
            trainPool.execute(fleetList.get(i));
        }
    
        trainPool.shutdown(); //close down pool

        while (!trainPool.isTerminated()) //wait till all thread operations are complete
        {

        }

        System.out.println("\n\n$ $ $ SIMULATION ENDS $ $ $\n\n");

        for (int i = 0; i < fleetList.size(); i++) //print each individual train's data 
        {
            System.out.println("Train Number " + fleetList.get(i).getTrainNum() + " assigned.");
            System.out.println("Train Number\t\tInbound Track\t\tOutbound Track\t\tSwitch 1\t\tSwitch 2\t\tSwitch 3\t\tHold\t\tDipatched\t\tDispatch Sequence\t");
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(fleetList.get(i).toString() + "\n");

        }
    }

    private static void scanIn() throws IOException //reads in all input files for yard and fleet
    {
        int yardCounter = 0;
        int fleetCounter = 0;

        Scanner yardIn = new Scanner(new File("theYardFile.csv"));
        Scanner fleetIn = new Scanner(new File("theFleetFile.csv"));

        while (yardIn.hasNextLine() && yardCounter < MAXPLANS)
        {
            String curLine = yardIn.nextLine();
            String[] pathArr = curLine.split(",");

            yardMap.put(pathArr[0] + "," + pathArr[4], pathArr[1] + "," + pathArr[2] + "," + pathArr[3]); //put each valid path into a hashmap, key = start and end point, value = required switches
            
            yardCounter++;
        }

        while (fleetIn.hasNextLine() && fleetCounter < MAXTRAINS)
        {
            String curLine = fleetIn.nextLine();
            String[] planArr = curLine.split(",");

            fleetList.add(new Train(planArr[0], planArr[1] + "," + planArr[2])); //create train objects for each train in fleet
            
            fleetCounter++;
        }
    }

    public static Switch[] getSwitches()
    {
        return switchArr;
    }

    public static HashMap<String, String> getYard()
    {
        return yardMap;
    }
}
