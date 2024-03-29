    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataIndexed;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import org.dizitart.no2.objects.Id;

/**
 *
 * @author Arif
 */
public class XPage implements Serializable{
    
    @Id
    long pageId;

    public XPage() {
    }
    
    
    
    
    String title;
    double importanceFactor;//out of 10
    HashMap<String,List<LocationAndType>> wordInstances;//for a word the page will have multiple instances represented by location and type

    public XPage(int pageId, String title, int importanceFactor, HashMap<String, List<LocationAndType>> wordInstances) {
        this.pageId = pageId;
        this.title = title;
        this.importanceFactor = importanceFactor;
        this.wordInstances = wordInstances;
    }
    
    
    
    public long getPageId() {
        return pageId;
    }

    public HashMap<String, List<LocationAndType>> getWords() {
        return wordInstances;
    }

    public void setImportanceFactor(double importanceFactor) {
        this.importanceFactor = importanceFactor;
    }

    public String getTitle() {
        return title;
    }

    public double getImportanceFactor() {
        return importanceFactor;
    }

    public HashMap<String, List<LocationAndType>> getWordInstances() {
        return wordInstances;
    }

    @Override
    public String toString() {
        return "XPage{" + "pageId=" + pageId + ", title=" + title + ", importanceFactor=" + importanceFactor + ", wordInstances=" + wordInstances + '}';
    }

    
    
    
    
}
