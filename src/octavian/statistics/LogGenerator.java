/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.statistics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author octavian.bodnariu
 */
public class LogGenerator {
    
    //<editor-fold defaultstate="collapsed" desc="Variables">
    private static final String LOG_FILE_NAME = "/log_";
    private DiskSpace diskSpace;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public LogGenerator()
    {
        diskSpace = new DiskSpace();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Functions">
    /**
     * Writes logs.txt with information
     * @param c (i - INFO, w - WARNING, e - ERROR f - FINAL)
     * @param message Event message
     */
    public void writeLog(char c, String message)
    {
        BufferedWriter bw = null;
        FileWriter fw = null;
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd"); //Numai data
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); //Data + ora 
        LocalDate localDate = LocalDate.now();
        LocalDateTime ldt = LocalDateTime.now();
        
        try
        {
            StringBuilder sb = new StringBuilder();
            sb.append(octavian.config.Config.LINUX_PATH + "/logs");
            sb.append(LOG_FILE_NAME);
            sb.append(dtf.format(localDate)); //Numai data
            sb.append(".txt");
            
            fw = new FileWriter(sb.toString(), true);
            bw = new BufferedWriter(fw);
            
            switch(c)         
            {
                case 'i':
                    bw.write("[INFO]" + dtf2.format(ldt)+ " : " + message + "\n");
                    break;
                    
                case 'w':
                    bw.write("[WARNING]" + dtf2.format(ldt)+ " : " + message + "\n");
                    break;
                    
                case 'e':
                    bw.write("[ERROR]" + dtf2.format(ldt)+ " : " + message + "\n");
                    break;
                    
                case 'f':
                    bw.write("OS : " + diskSpace.whatOS() + " RAM : " + diskSpace.ramInUse() + "\n");//OS
                    //bw.write(diskSpace.ramInUse().toString + " MB");//Ram
                    break;
                    
                default:
                    System.err.println("Wrong argument in log generator !");
            }
        }
        catch(IOException e)
        {
            System.err.println("Could not create config file - " + e);
        }
        finally
        {
            try
            {
                if(bw != null)  
                    bw.close();
                if(fw != null)
                    fw.close();
                
            }
            catch(IOException ex)
            {
                System.err.println("Something went wrong..." + ex);
            }
        }
    }
    //</editor-fold>
}
