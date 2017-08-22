/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics;

import java.io.File;
/**
 *
 * @author octavian.bodnariu
 */
public class DiskSpace {

    public long ramInUse()
    {
        return (Runtime.getRuntime().totalMemory() /1024 /1024);
    }
    
    public String getOs()
    {
        return System.getProperty("os.name");
    }
    
    public void diskSpaceStatistics()
    {
        File file = new File("c:");
    	long totalSpace = file.getTotalSpace(); //total disk space in bytes.
    	long usableSpace = file.getUsableSpace(); ///unallocated / free disk space in bytes.
    	long freeSpace = file.getFreeSpace(); //unallocated / free disk space in bytes.

    	System.out.println(" === Partition Detail ===");

    	System.out.println(" === bytes ===");
    	System.out.println("Total size : " + totalSpace + " bytes");
    	System.out.println("Space free : " + usableSpace + " bytes");
    	System.out.println("Space free : " + freeSpace + " bytes");

    	System.out.println(" === mega bytes ===");
    	System.out.println("Total size : " + totalSpace /1024 /1024 + " mb");
    	System.out.println("Space free : " + usableSpace /1024 /1024 + " mb");
    	System.out.println("Space free : " + freeSpace /1024 /1024 + " mb");
        
        System.out.println(" === giga bytes ===");
    	System.out.println("Total size : " + totalSpace /1024 /1024 /1024 + " gb");
    	System.out.println("Space free : " + usableSpace /1024 /1024 /1024 + " gb");
    	System.out.println("Space free : " + freeSpace /1024 /1024 /1024 + " gb");
    }
    
    public void processorsStatistics()
    {
        System.out.println(" === Processor === \nAvailable processors(cores) : "  + Runtime.getRuntime().availableProcessors());
    }
    
    public void ramStatistics()
    {
        System.out.println(" === Ram === \nRAM in use : "  + Runtime.getRuntime().totalMemory() /1024 /1024 + " mb");
    }
}
