/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config.setup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


/*
======================================Ierarhia Fisierelor================================================
-----Encrypter
         |+config
            |
            |config.txt
         |+logs
            |
            |logs.txt
            |+reports 
                 |
                 |report.pdf
                 |
         |+exports
            |
            |exportSQL.sql
            |exportTable.csv

*/

/**
 *
 * @author octavian.bodnariu
 */
public class ApplicationSetup {
    
//==============================CONSTANTE============================================================================================================================================
    private static final String LOGS = "/logs";
    private static final String REPORTS = "/logs/reports";
    private static final String DB_EXPORT = "/exports";
    private static final String CONFIG_FILES = "/config";
    
//==============================VARIABILE============================================================================================================================================
    private final String installation_path;
    
    
//==============================CONSTRUCTOR==========================================================================================================================================
    public ApplicationSetup(String installation_path)
    {
        this.installation_path = installation_path;
    }
    
    /*
    public ApplicationSetup()
    {
        this.installation_path = "default"; 
    }
    */
    
//==============================FUNCTII PUBLICE======================================================================================================================================
    /**
     * Procedura creeaza fisierele pentru a stoca informatii
     */
    public void configureApplication()
    {
        if(!isFirstRun(installation_path))
        {
        } else {
            try
            {
                Path local_p = Paths.get(installation_path + LOGS);
                Files.createDirectories(local_p);
                
                local_p = Paths.get(installation_path + REPORTS);
                Files.createDirectories(local_p);
                
                local_p = Paths.get(installation_path + DB_EXPORT);
                Files.createDirectories(local_p);
                
                local_p = Paths.get(installation_path + CONFIG_FILES);
                Files.createDirectories(local_p);
                
                createConfigFile(installation_path);
            }
            catch(IOException e)
            {
                System.err.println("Cannot create directories - " + e);
            }
        }
    }

//==============================FUNCTII PRIVATE======================================================================================================================================
    /**
     * Functia verifica daca exista ierarhia de fisiere/foldere
     * @param path Calea 
     * @return True - Este prima rulare, False - Nu este prima rulare
     */
    private boolean isFirstRun(String path)
    {
        File f = new File(path);
//        System.out.println(f.list().length);
         //f.list()
        return (f.list().length == 0);
    }
    
    private void createConfigFile(String path)
    {
        BufferedWriter bw = null;
        FileWriter fw = null;
        
        try
        {
            StringBuilder sb = new StringBuilder();
            sb.append(installation_path + CONFIG_FILES + "/config.cfg");
            
            fw = new FileWriter(sb.toString());
            bw = new BufferedWriter(fw);
            bw.write(installation_path);
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
}
