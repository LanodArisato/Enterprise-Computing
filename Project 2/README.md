# 🚂 Project 2: Multi-threaded Programming in Java  

## 📖 Project Overview  
This project is a **Java-based multi-threaded simulation** of a railroad switch yard, designed to demonstrate **multi-threaded programming, synchronization, and resource management**.  

The simulation models **Precision Scheduled Railroading (PSR)**, where trains focus on moving cars efficiently rather than waiting for long trains to form. Each train must acquire control of specific switches to move from its inbound track to its outbound track.  

To avoid deadlock and ensure fairness, trains acquire switches in order. If a required switch is unavailable, the train releases all currently held switches, waits a random time, and retries.  

---

## ✨ Features  
- **Multi-threaded Simulation**  
  - Each train is represented as a separate thread.  
  - Threads are managed by an `ExecutorService` with a fixed thread pool (max 30 trains).  

- **Synchronization with Locks**  
  - Uses `ReentrantLock` from `java.util.concurrent.locks`.  
  - Prevents simultaneous train movements through the yard.  
  - Ensures orderly switch acquisition/release.  

- **Configurable Yard & Fleet**  
  - **`theFleetFile.csv`**: Defines train fleet (train number, inbound track, outbound track).  
  - **`theYardFile.csv`**: Defines valid switch alignments for inbound/outbound paths.  

- **Deadlock Avoidance**  
  - Trains acquire switches in a strict order (first, second, third).  
  - If acquisition fails, trains release all locks and retry after waiting.  

- **Simulation Output**  
  - Logs every significant event:  
    - Switch acquisition/release  
    - Train dispatch  
    - Permanent holds for invalid paths  
    - Simulation start and end messages  

---

## 📂 Files  
- **`theFleetFile.csv`** – Defines train fleet (train number, inbound track, outbound track).  
- **`theYardFile.csv`** – Defines switch alignment configurations (inbound track, switches required, outbound track).  
- **`*.java`** – Source files implementing yard, trains, and synchronization logic.  
- **`output.txt`** – Console output redirected to file, showing a complete simulation run.  

---

## 📸 Simulation Events Logged  
The simulator produces detailed output for:  

1. **Train acquires a switch**  
