/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.filemanager.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

/**
 *
 * @author octavian
 */
public abstract class XmlAbstract {
    
    private DocumentBuilderFactory documentFactory;
    private DocumentBuilder documentBuilder;
    private Document document;
    
    public XmlAbstract(){
        try{
            this.documentFactory = DocumentBuilderFactory.newInstance();
            this.documentBuilder = documentFactory.newDocumentBuilder();
        }catch(ParserConfigurationException e){
            
        }
    }
    
    public abstract void execute();
}
