# 🚂 Train Yard – Java Multi-Threaded Simulation  

## 📖 Overview  
Train Yard is a **Java-based simulation** that models the operation of a railway switch yard under **multi-threaded programming principles**. The project demonstrates concurrency, synchronization, and deadlock avoidance while simulating trains moving through a yard with shared switches.  

Trains act as concurrent threads, acquiring and releasing switch locks in order to safely move from inbound to outbound tracks. Configurations are defined in input files for both the yard and the fleet.  

---

## ✨ Features  
- **Threaded Train Simulation**: Each train runs as a thread managed by an `ExecutorService`.  
- **Lock-Based Synchronization**: Switches are controlled with `ReentrantLock` to prevent race conditions.  
- **Deadlock Avoidance**: Trains acquire switches in strict order; if blocked, they release and retry later.  
- **Configurable Yard & Fleet**:  
  - `theYardFile.csv` defines valid track-to-switch mappings.  
  - `theFleetFile.csv` defines trains with inbound and outbound tracks.  
- **Event Logging**: Console output logs train actions such as acquiring/releasing switches, waiting on holds, and completing movements.  

---

## 📂 Files  
- **Source Files**  
  - `Train.java` – Represents a train as a thread (`Runnable`).  
  - `Switch.java` – Models a yard switch with locking functionality.  
  - `Yard.java` – Stores yard configurations and validates train paths.  
  - `Simulator.java` – Main driver, initializes yard, fleet, and runs simulation.  

- **Input Files**  
  - `theFleetFile.csv` – Train fleet (train number, inbound track, outbound track).  
  - `theYardFile.csv` – Yard configurations (inbound track, switch sequence, outbound track).  

---

## 🛠 Tech Stack  
- **Language**: Java  
- **Concurrency Tools**:  
  - `ExecutorService` (fixed thread pool, max 30 trains)  
  - `ReentrantLock` for synchronization  
- **Input Format**: CSV (`theFleetFile.csv`, `theYardFile.csv`)  
- **Output**: Console logs (can be redirected to file)  
