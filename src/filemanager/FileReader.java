/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filemanager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Public Functions">
    public void encrypt()
    {
        readFile();
    }
    
    public void decrypt()
    {
        
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
            e.printStackTrace();
        }
        
    }
    
    private void encryptFile()
    {
        
    }
    
    private void decryptFile()
    {
        
    }
    //</editor-fold>
}
