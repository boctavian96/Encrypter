/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.filemanager;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * @author octavian
 */
public class FWriter {
    
	private final String filename;
        
        public FWriter(String filename){
            this.filename = filename;
        }
        
        public void write(String content){
            BufferedWriter bw = null;
            FileWriter fw = null;

            try {
		fw = new FileWriter(filename);
		bw = new BufferedWriter(fw);
		bw.write(content);
		} catch (IOException e) {
                    e.printStackTrace();
		} finally {
                    try {
			if (bw != null)
                            bw.close();
			if (fw != null)
                            fw.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
			}

		}
        }
        
        public void write(char[] content){
            write(String.copyValueOf(content));
        }
        
        public void write(byte[] content){
            
              FileOutputStream out = null;
              
              try{
                    out = new FileOutputStream(filename);
                    out.write(content);
                    out.close();
                }catch(IOException e){
                    e.printStackTrace();

                }
        }
        
        public void writeNumbers(byte[] numbers){
            StringBuilder result = new StringBuilder();
            
            for(byte i : numbers){
                result.append(i);
                result.append(' ');
            }
            
            write(result.toString());
        }

}
