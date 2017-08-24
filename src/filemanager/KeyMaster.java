/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filemanager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

import config.Config;
import java.io.ObjectOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import statistics.LogGenerator;


/**
 *
 * @author octavian.bodnariu
 */
public class KeyMaster {
    
    private final File privateKeyFile;
    private final File publicKeyFile;
    
    //<editor-fold desc="Constructors">
    /**
     * Constructor for Keymaster
     * @param private_key_path Path to private keys file
     * @param public_key_path  Path to public keys file
     */
    public KeyMaster(String private_key_path, String public_key_path)
    {
        privateKeyFile = new File(private_key_path);
        publicKeyFile = new File(public_key_path);
    }
    //</editor-fold>
    
    public void storeKeys(PublicKey k1, PrivateKey k2)
    {
        if(privateKeyFile.getParentFile() != null)
        {
            privateKeyFile.getParentFile().mkdirs();
        }
        
        try
        {
            privateKeyFile.createNewFile();
        }
        catch(IOException e)
        {
            LogGenerator l = new LogGenerator();
            l.writeLog('e', e.getMessage());
            
        }
        
        if(publicKeyFile.getParentFile() != null)
        {
            publicKeyFile.getParentFile().mkdirs();
        }
        
        try
        {
            publicKeyFile.createNewFile();
        }
        catch(IOException e)
        {
            LogGenerator l = new LogGenerator();
            l.writeLog('e', e.getMessage());
        }
        
        //SAVE KEY
        try
        {
            ObjectOutputStream publicKey = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
            publicKey.writeObject(k1);
            publicKey.close();
        }
        catch(IOException e)
        {
            LogGenerator l = new LogGenerator();
            l.writeLog('e', e.getMessage());
        }
        
                try
        {
            ObjectOutputStream privateKey = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
            privateKey.writeObject(k2);
            privateKey.close();
        }
        catch(IOException e)
        {
            LogGenerator l = new LogGenerator();
            l.writeLog('e', e.getMessage());
        }
    }
}
