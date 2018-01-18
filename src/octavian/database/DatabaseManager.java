/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.database;

import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;


/**
 * Class used to manage connections to mongoDB
 * @author octavian
 */
public class DatabaseManager {
    private static DatabaseManager instance = null;
    
    private MongoClient mongo;
    
    private DatabaseManager(){
        mongo = new MongoClient();
    }
    
    public DatabaseManager getInstance(){
        if(instance == null){
            instance = new DatabaseManager();
            return instance;
        }else{
            return instance;
        }
    }
}
