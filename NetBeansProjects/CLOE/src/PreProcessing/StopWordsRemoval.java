/*
 @author Shimaa Ibrahim
 */


package PreProcessing;

import java.io.*;
import java.util.*;

public class StopWordsRemoval {
    
    
   public static String removeStopWordsFromSentence(String sentence){
        
        String[] ENGLISH_STOP_WORDS = {"i", "a", "about", "an", "as", "at", "be", "and", "but", "if",
            "by", "com", "for", "from", "how", "in", "it", "into", "no", "not","their",
            "of", "on", "or", "that", "the", "this", "to", "was","such","then","there","these",
            "what", "when", "where", "who", "will", "with","without", "above","they", "my",
            "all", "already", "also", "always", "am", "he", "about", "above", "anybody", "anyhow", "anyone",
            "anything", "anyway", "at", "be", "before", "being", "can", "been", "many", "much", "more",
            "cannot", "cant", "did", "do", "does", "doing", "done", "each",
            "else", "even", "ever", "everybody", "everyone", "had", "during", ". ", "?", "!", "(",")", 
            "dear", "regards", "Mr", "Mrs", "is", "has", "have", "most", "must", "often", "best", "kindly", "please", 
            "note", "new", "find", "informed", "are", "now", "properly", "end","week",
        "latest", "then", "finally", "final","should", "broad", "overview", "very", "ein", "eine", "einer", "einen", "als", "zu", "mit",
        "dem", "der", "die", "das", "durch", "bis", "für", "ohne", "entlang", "gegen", "um", "bitte", "alle", "neue", "alt", "beachten",
        "bis", "ende", "dieser", "Woche", "auf", "den", "neuen", "one", "dann", "sollte", "neueste", "ab", "sehr", "von", "und","über",
        "dieses", "dieser", "wurde", "werden", "Schließlich", "gegen", "nun", "ordnungsgemäß", "sie", "dass", "wird", "wurde", "im"};

        StringBuffer clean = new StringBuffer();
        StringTokenizer tokens = new StringTokenizer(sentence.replaceAll("[^ßüöäa-zA-Z0-9\\s]", ""), " ");
        //StringTokenizer tokens = new StringTokenizer(sentence);
        

        int i = 0;
        while (tokens.hasMoreTokens()) {
            String tkn = tokens.nextToken();
            int flag = 0;
            for (i = 0; i < ENGLISH_STOP_WORDS.length; i++) {
                if (tkn.equalsIgnoreCase(ENGLISH_STOP_WORDS[i])) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                clean.append(tkn);
                clean.append(" ");
            }
        }
       
        return clean.toString();
    }
    
}
