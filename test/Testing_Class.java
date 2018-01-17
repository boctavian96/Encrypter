/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import octavian.filemanager.FileReader;
import octavian.config.setup.ApplicationSetup;
import octavian.statistics.DiskSpace;
import octavian.encrypter.MainApplication;
import octavian.statistics.LogGenerator;
import octavian.config.Config;

/**
 *
 * @author octavian.bodnariu
 */
public class Testing_Class {
    public static void main(String[] args)
    {
        //FileReader fr = new FileReader("C:\\Renamer_test\\test.txt", 'a', 'a');
        //fr.encrypt();
        ApplicationSetup as = new ApplicationSetup(Config.LINUX_PATH);
        DiskSpace ds = new DiskSpace();
        MainApplication ma = new MainApplication();
        LogGenerator lg = LogGenerator.getInstance();
        
        //ma.setVisible(true);
        
        
        as.configureApplication();
        
        ds.diskSpaceStatistics();
        ds.processorsStatistics();
        ds.ramStatistics();
        lg.writeLog('i', "Runned succesfully ! :)");
        lg.writeLog('w', "Something might break :|");
        lg.writeLog('e', "Severe error ! :(");
        lg.writeLog('f', "FYI");
        
        //Sistemul de operare
        System.out.println(System.getProperty("os.name"));
        
    }
}
