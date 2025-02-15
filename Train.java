/*
Name: Landon Morjal
Course: CNT 4714 Spring 2025
Assignment title: Project 2 – Multi-threaded programming in Java
Date: February 15, 2025
Class: Train
Description: Handles thread operations, including lock checks, sleeping, and output during program runtime.
*/
import java.util.HashMap;
import java.util.Random;

public class Train implements Runnable 
{
    Random rand = new Random();

    private static int dispatchCount = 1;

    private String trainNum;
    private String plan;
    private boolean hold;
    private boolean dispatched;
    private int dispatchNum;

    public Train(String trainNum, String plan)
    {
        this.trainNum = trainNum;
        this.plan = plan;

        this.hold = false;
        this.dispatchNum = 0;
        this.dispatched = false;
    }

    public void setDispatch(int dispatchNum)
    {
        this.dispatchNum = dispatchNum;
        this.dispatched = true;
    }

    @Override
    public void run()
    {
        Switch[] switchArr = Driver.getSwitches();
        HashMap<String, String> yardMap = Driver.getYard();

        if (!yardMap.containsKey(plan))
        {
            hold = true;

            System.out.println("*************\nTrain " + trainNum + " is on permanent hold and cannot be dispatched.\n*************");
            
            return;
        }

        String[] path = yardMap.get(plan).split(",");
        
        while (true)
        {
            if (switchArr[Integer.parseInt(path[0]) - 1].lock())
            {

                System.out.println("Train " + trainNum + ": HOLDS LOCK on Switch " + switchArr[Integer.parseInt(path[0]) - 1].getSwitchNum() + ".");

                if (switchArr[Integer.parseInt(path[1]) - 1].lock())
                {

                    System.out.println("Train " + trainNum + ": HOLDS LOCK on Switch " + switchArr[Integer.parseInt(path[1]) - 1].getSwitchNum() + ".");

                    if (switchArr[Integer.parseInt(path[2]) - 1].lock())
                    {
                        System.out.println("Train " + trainNum + ": HOLDS LOCK on Switch " + switchArr[Integer.parseInt(path[2]) - 1].getSwitchNum() + ".");

                        runTrain(path, switchArr);                        
                    }
                    else
                    {
                        System.out.println("Train " + trainNum + ": UNABLE TO LOCK third required switch: Switch " + (Integer.parseInt(path[2])) + ".");
                        System.out.println("Train " + trainNum + ": Releasing locks on first and second required switchs: Switch " + (Integer.parseInt(path[0])) + " and Switch " + (Integer.parseInt(path[1])) + ". Train will wait...");
                        switchArr[Integer.parseInt(path[0]) - 1].unlock();
                        switchArr[Integer.parseInt(path[1]) - 1].unlock();
                        trainWait();
                        continue;
                    }
                }
                else
                {
                    System.out.println("Train " + trainNum + ": UNABLE TO LOCK second required switch: Switch " + (Integer.parseInt(path[1])) + ".");
                    System.out.println("Train " + trainNum + ": Releasing lock on first required switch: Switch " + (Integer.parseInt(path[0])) + ". Train will wait..." );
                    switchArr[Integer.parseInt(path[0]) - 1].unlock();
                    trainWait();
                    continue;
                }
            }
            else
            {
                System.out.println("Train " + trainNum + ": UNABLE TO LOCK first required switch: Switch " + (Integer.parseInt(path[0])) + ". Train will wait..." );
                trainWait();
                continue;
            }

            break;
        }
    }

    private void runTrain(String[] path, Switch[] switchArr)
    {

        System.out.println("\nTrain " + trainNum + ": HOLDS ALL NEEDED SWITCH LOCKS - Train movement begins.\n");

        try 
        {
            Thread.sleep(2000);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }

        System.out.println("\nTrain " + trainNum + ": Clear of yard control.");
        System.out.println("Train " + trainNum + ": Releasing all switch locks.");
        System.out.println("Train " + trainNum + ": Unlocks/releases lock on Switch " + (Integer.parseInt(path[0])) + ".");
        switchArr[Integer.parseInt(path[0]) - 1].unlock();
        System.out.println("Train " + trainNum + ": Unlocks/releases lock on Switch " + (Integer.parseInt(path[1])) + ".");
        switchArr[Integer.parseInt(path[1]) - 1].unlock();
        System.out.println("Train " + trainNum + ": Unlocks/releases lock on Switch " + (Integer.parseInt(path[2])) + ".");
        switchArr[Integer.parseInt(path[2]) - 1].unlock();
        System.out.println("Train " + trainNum + ": Has been dispatched and moves on down the line out of yard control into CTC.");
        System.out.println("\n@ @ @ TRAIN " + trainNum + ": DISPATCHED @ @ @\n");
        dispatched = true;
        dispatchNum = dispatchCount;
        dispatchCount++;
    }

    private void trainWait()
    {
        try
        {
            Thread.sleep(rand.nextInt(3000) + 2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public String toString()
    {
        HashMap<String,String> yardMap = Driver.getYard();
        String[] inOut = plan.split(",");

        String stringOut;
    
        if (!yardMap.containsKey(plan))
        {
            stringOut =  "  " + trainNum + "\t\t\t  " + inOut[0] + "\t\t\t      " + inOut[1] + "\t\t\t   " + 0 + "\t\t\t   " + 0 + "\t\t\t   " + 0 + "\t\t\t" + hold + "\t\t" + dispatched + "\t\t\t  " + dispatchNum;

            return stringOut;
        }

        String[] switches = yardMap.get(plan).split(",");

        stringOut =  "  " + trainNum + "\t\t\t   " + inOut[0] + "\t\t\t   " + inOut[1] + "\t\t\t   " + switches[0] + "\t\t\t   " + switches[1] + "\t\t\t   " + switches[2] + "\t\t\t" + hold + "\t\t  " + dispatched + "\t\t\t" + dispatchNum;

        return stringOut;
    }

    public String getTrainNum()
    {
        return trainNum;
    }
}
