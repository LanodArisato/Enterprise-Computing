/*
Name: Landon Morjal
Course: CNT 4714 Spring 2025
Assignment title: Project 2 – Multi-threaded programming in Java
Date: February 15, 2025
Class: Switch
Description: Contains locks for each switch for use in thread operations.
*/
import java.util.concurrent.locks.ReentrantLock;

public class Switch { 

    private int switchNum;
    private ReentrantLock lock = new ReentrantLock();

    public Switch(int switchNum) //assign switch number from driver setup
    {
        this.switchNum = switchNum;
    }

    public int getSwitchNum()
    {
        return this.switchNum;
    }

    public boolean lock() //lock switch
    {
        return lock.tryLock();
    }

    public void unlock() //unlock switch
    {
        lock.unlock();
    }
}
