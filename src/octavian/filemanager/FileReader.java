/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.filemanager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import octavian.statistics.LogGenerator;

/**
 *
 * @author octavian.bodnariu
 */
public class FileReader {
    
    //<editor-fold defaultstate="collapsed" desc="Variabiles">
    private FileInputStream in = null;
    private FileOutputStream out = null;
    private char mode;
    private char algo;
    private String File_Path = null;
    private String Output_File = null;
    private final int n = 1;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * 
     * @param File_Path Calea catre fisierul/folderul care trebuie criptat
     * @param mode Mod file sau folder
     * @param algo Algoritmul folosit
     */
    public FileReader(String File_Path, char mode, char algo)
    {
        this.File_Path = File_Path;
        this.mode = mode;
        this.algo = algo;
    }
    
    public FileReader(String File_Path, String Output_File, char mode, char algo)
    {
        this.File_Path = File_Path;
        this.Output_File = Output_File;
        this.mode = mode;
        this.algo = algo;
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Functions">
    /**
     * Reads input from a file
     */
    public void read()
    {
        readFile();
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Private Functions">
    private void readFile()
    {
        int c;
        try
        {
            in = new FileInputStream(File_Path);
            out = new FileOutputStream("test.txt");
            
            switch(n)
            {
                case 1 :
                    while((c = in.read()) != -1)
                    {
                        System.out.println("Ich bin hier !");
                        out.write(c + 1);
                    }
                break;
                default: 
                    System.out.println("kein input !");
            }
        }
        catch(IOException e)
        {
            System.out.println("Fehler !");
            LogGenerator l = new LogGenerator();
            l.writeLog('e', e.getMessage());
        }
        
    }
    
    //</editor-fold>
}
