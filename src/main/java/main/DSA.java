/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Data.Page;
import Data.Word;
import DataIndexed.Index;
import DataIndexed.XPage;
import DataIndexed.XWord;
import Engine.Indexer;
import Engine.Indexer.IndexingCallbacks;
import Engine.SearchHelper;
import java.io.File;
import java.util.List;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.WriteResult;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import parsers.WikiMarkupParser;
import parsers.WikiXMLParser;
import parsers.WikiXMLParser.WikiXMLParserCallbackReciever;

/**
 *
 * @author Arif
 */
public class DSA implements IndexingCallbacks{
    
    
    
    
    public static final String EXAMPLE_TEST = "A '''Vaudeville''' was a [[France|French]] [[satire|satirical]] [[poem]] or [[song]] in the [[17th century|17th]] and [[18th century|18th centuries]]. It was used a lot in French [[theatre]] where entertainments using such songs were called \"com�die en vaudeville\". Late in the 19th century, a type of [[variety show]] done in American theaters came to be called \"vaudeville\" which was popular from the early 1880s to the early 1930s. The typical American vaudeville performance was made up of a several different, unrelated performances which were grouped together into a single show. Entertainers typically traveled from town to town as arranged by a \"vaudeville circuit\". It became less popular after the rise of [[movie]]s, [[cinema]], and [[radio]], which eventually took over as the main forms of public entertainment.\n" +
"\n" +
"== History And Mistory ==\n" +
"The earliest vaudeville was the ''vau de vire'', a [[Normandy|Norman]] song of the [[15th century]], named after the valley of Vire. During the [[16th century]] a style grew in the towns in France called the ''voix de ville'' ( ''city voices''), whose name may have been a [[pun]] on ''vau de vire''. These were also [[satirical]]. The two styles came together and in the 17th and 18th century. The term 'vaudeville' came to be used for songs satirizing [[political]] and [[court]] events. \n" +
"\n" +
"== Growth ==";
    
    
    
    public static void main(String[] args){
        
        System.out.println("hiahahia");
        
//        List<Word> temp = WikiMarkupParser.getWords(EXAMPLE_TEST);



        DSA dsa = new DSA();
        //dsa.initIndexing();
        
        dsa.searching();
        
        
    }
    
    void searching(){
        Nitrite db = Nitrite.builder()
        .compressed()
        .filePath("test2.db")
        .openOrCreate("user", "password");
        
        ObjectRepository<XPage> pageMapRepository = db.getRepository(XPage.class);
        ObjectRepository<XWord> invertedIndex = db.getRepository(XWord.class);
        
        SearchHelper searchHelper  = new SearchHelper(pageMapRepository,invertedIndex);
        
        List<XPage> result =searchHelper.search("april");
        for(int i = 0; i<result.size();i++){
            System.out.println("https://simple.wikipedia.org/wiki/"+result.get(i).getTitle());
            System.out.println(result.get(i).getWordInstances().get("april").size());
        }
        
        
        
//        
//        Cursor<XWord> cursor = invertedIndex.find(ObjectFilters.eq("word", "a"));
//
//        
//        XWord xword = cursor.firstOrDefault();
////                
//        System.out.println(xword.getPagesContainingThis().size());
        
        
        
        
    }
    
    void initIndexing(){
        Nitrite db = Nitrite.builder()
        .compressed()
        .filePath("test2.db")
        .openOrCreate("user", "password");
        
        ObjectRepository<XPage> pageMapRepository = db.getRepository(XPage.class);
        ObjectRepository<XWord> invertedIndex = db.getRepository(XWord.class);
        
        final Indexer indexer = new Indexer(pageMapRepository,invertedIndex,this);//TODO: temporary...
        
        WikiXMLParser xmlParser = new WikiXMLParser(new File("path to file"), (WikiXMLParserCallbackReciever)indexer);
        
        xmlParser.init();
        
        
        

        
               
    }
    
    
    
    int count=0;
    public void onProgress(int id) {
        System.out.println(id);
    }

    public void done() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
