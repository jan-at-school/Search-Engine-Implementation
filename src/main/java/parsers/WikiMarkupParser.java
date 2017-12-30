/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

import Data.Word;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Arif
 */
public class WikiMarkupParser {

    public static String REGEX_LINK = "[\\[]{2}[a-zA-Z)0-9\\s]+|[a-zA-Z)0-9\\s]+[\\]]{2}";
    public static String REGEX_WORD = "\\w+";
    public static String REGEX_H1 = "[\\=]{1}[a-zA-Z)0-9\\s]+[\\=]{1}";
    public static String REGEX_H2 = "[\\=]{2}[a-zA-Z)0-9\\s]+[\\=]{2}";
    public static String REGEX_H3 = "[\\=]{3}[a-zA-Z)0-9\\s]+[\\=]{3}";
    public static String REGEX_TITLE = "[']{3}[a-zA-Z)0-9\\s]+[']{3}";
    
    
    private static WikiMarkupParser instance = null;

    String text;
    List<Word> wordsFound = new ArrayList<Word>();
    public WikiMarkupParser(String text) {
        this.text=text;
    }

    
    public void resetForOtherText(String text){
        this.text=text;
        this.wordsFound=new ArrayList<Word>();
    }

    
    
    public void process(){
        collectTitles();
        collectH3();
        collectH2();
        collectH1();
        collectWords();
        
        
    }
    
    
    public List<Word> getResult(){
        return wordsFound;
    }
    
    
    
    
    
    
    
    

    private void collectTitles() {

        Pattern pattern = Pattern.compile(REGEX_TITLE);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {

            String temp = matcher.group();
            temp=temp.replace("'", "");
            temp=temp.trim();
            temp = temp.toLowerCase();
            System.out.println(temp);
            wordsFound.add(new Word(wordsFound.size(), temp, Word.Type.TITLE));
        }
        text = text.replaceAll(REGEX_TITLE, "");
    }

    private void collectH1() {

        Pattern pattern = Pattern.compile(REGEX_H1);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {

            String temp = matcher.group();
            System.out.println(temp);
            temp=temp.replace("=", "");
            temp=temp.trim();
            temp = temp.toLowerCase();
            System.out.println(temp);
            wordsFound.add(new Word(wordsFound.size(), temp, Word.Type.H1));
        }
        
        text = text.replaceAll(REGEX_H1, "");
    }

    private void collectH2() {

        Pattern pattern = Pattern.compile(REGEX_H2);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {

            String temp = matcher.group();
            System.out.println(temp);
            temp=temp.replace("=", "");
            temp=temp.trim();
            temp = temp.toLowerCase();
            System.out.println(temp);
            wordsFound.add(new Word(wordsFound.size(), temp, Word.Type.H2));
        }
        
        text = text.replaceAll(REGEX_H2, "");
    }

    private void collectH3() {

        Pattern pattern = Pattern.compile(REGEX_H3);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {

            String temp = matcher.group();
            System.out.println(temp);
            temp=temp.replace("=", "");
            temp=temp.trim();
            temp = temp.toLowerCase();
            System.out.println(temp);
            wordsFound.add(new Word(wordsFound.size(), temp, Word.Type.H3));
        }
        text = text.replaceAll(REGEX_H3, "");
    }

    private void collectWords() {

        Pattern pattern = Pattern.compile(REGEX_WORD);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {

            String temp = matcher.group();
            temp = temp.toLowerCase();
            System.out.println(temp);
            wordsFound.add(new Word(wordsFound.size(), temp, Word.Type.SIMPLE));
        }
    }

}
