/*
 @author Shimaa Ibrahim
 */

package OntologyEnrichment;

import CLOE_GUI.EnrichmentUI;
import java.util.*;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.Writer;
import java.io.BufferedWriter;
import java.lang.Object;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shimaa
 */
public class MasterAgent {
    public static String [][] BuildLookUpTable()
    {
        //Map<String, String> lookUpTable = new HashMap<String, String>();
        String [][]lookUpTable= new String[3][3];
        //DA1 = Tourism
        lookUpTable[0][0] = "DA";
        lookUpTable[0][1] = "Information Technology";
        lookUpTable[0][2] = " ";
        lookUpTable[1][0] = "LA";
        lookUpTable[1][1] = "Information Technology";
        lookUpTable[1][2] = "en";
        lookUpTable[2][0] = "LA";
        lookUpTable[2][1] = "Information Technology";
        lookUpTable[2][2] = "German";
                
        return lookUpTable;
    }
    public static void OntologyEnrichment(String[] s, String domain, String language, String[][] lookUpTable)
    {
        int domainAgentIndex = FindDomainAgent(lookUpTable, domain);
        int languageAgentIndex = FindLanguageAgent(lookUpTable, language);
        System.out.println("domain index "+ domainAgentIndex);
        System.out.println("language index "+ languageAgentIndex);
        
        if (domainAgentIndex == 0 && languageAgentIndex == 1)
        {
            for(int i = 0; i < s.length; i++)
            {
                try 
                {
                    OntologyEnrichment.MasterAgent.LanguageAgent_English(s[i], "enrich");
                } 
                catch (IOException ex) {
                    Logger.getLogger(EnrichmentUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        }
    }
    public static void AskDomainAgent(String concept, String language) throws IOException
    {   String DV[];
        if(language.equalsIgnoreCase("en"))
        {
            DV = OntologyEnrichment.MasterAgent.LanguageAgent_German(concept);
            for(int i = 0; i < DV.length; i++)
            {
               
                    OntologyEnrichment.MasterAgent.LanguageAgent_English(DV[i], "enrich");
            }
        }
        else if(language.equalsIgnoreCase("de"))
        {
            //translate the given text to english then ask LanguageAgent_English
            String translatedGermanConcept = concept; //should be translated first
            
        }
    }
    public static Integer FindDomainAgent(String[][] lookUpTable, String domain)
    {
        for(int i = 0; i< 3; i++)
        {
            if(lookUpTable[i][0].equals("DA") && lookUpTable[i][1].equals(domain))
                return i;
        }
        return null;      
    }
    public static Integer FindLanguageAgent(String[][] lookUpTable, String language)
    {
        for(int i = 0; i< 3; i++)
        {
            if(lookUpTable[i][0].equals("LA") && lookUpTable[i][2].equals(language))
                return i;
        }
        return null;
            
    }
    public static Boolean ReadOntology (String str, File file)
    {
        //File file = new File("/home/shimaa/NetBeansProjects/MultilingualOntologyEnrichment/resources/Ontology.txt");
        String line = "";
        try {
            //
            // Create a new Scanner object which will read the data from the 
            // file passed in. To check if there are more line to read from it
            // we check by calling the scanner.hasNextLine() method. We then
            // read line one by one till all line is read.
            //
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                System.out.println(line);
                if(line.contains(str)) { 
                // a match!
                    //System.out.println("I found " +str+ " in file " +file.getName());
                    //break;
                    return true;
                }
                else
                    return false;
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void WriteToFile(String data[], File file){
	        BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			//String data = " This is new content mmmmmmmmmmmmmmmm";
			//File file = new File(fileName);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(Arrays.toString(data));
                        bw.newLine();
			System.out.println("Done writing to file");

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
    public static void LanguageAgent_English(String Inputdata, String flag) throws IOException
    {
        String tokens[] = PreProcessing.OpenNLP.Tokenize(Inputdata);
        File file = new File("/home/shimaa/NetBeansProjects/MultilingualOntologyEnrichment/resources/LanguageAgent1(English).txt");
        if(flag.equalsIgnoreCase("enrich"))
        {
            WriteToFile(tokens, file);
            //Printing the tokens 
            //for(String token : tokens)     
            //  System.out.println(token);
            
        }
        else 
        { 
            boolean found = false;
            for(int i = 0; i < tokens.length; i++)
            {
                found = ReadOntology(tokens[i], file);
                System.out.println("Found value "+found);
                if(found)
                System.out.println("The concept "+ tokens[i] +" is found");
                else
                {
                    System.out.println(tokens[2] + " This concept "+ tokens[i] +" not found");
                    //String DV[] = LanguageAgent_German(tokens[i]);
                    //WriteToFile(DV, file);
                }
            }
            //List<String> lines = Files.readAllLines(file);
            //String[] arr = lines.toArray(new String[lines.size()]);
        }
        
   }
    
        

    public static String[] LanguageAgent_German(String unknown) throws IOException
    {
        File file = new File("/home/shimaa/NetBeansProjects/MultilingualOntologyEnrichment/resources/LanguageAgent2(German).txt");
        String [][]translationTableGerman= new String[5][2];

        translationTableGerman[0][0] = "Professor";
        translationTableGerman[0][1] = "Professor";
        
        translationTableGerman[1][0] = "teaches";
        translationTableGerman[1][1] = "lehrt";
        
        translationTableGerman[2][0] = "courses";
        translationTableGerman[2][1] = "Kurse";
        
        translationTableGerman[3][0] = "Student";
        translationTableGerman[3][1] = "Schüler";
        
        translationTableGerman[4][0] = "take";
        translationTableGerman[4][1] = "nehmen";
        
        String tokens[] = new String[3];
        String[] DV = new String[tokens.length];
        
        String germanTxt = "";
        for(int i = 0; i < translationTableGerman.length; i++)
        {
            if(translationTableGerman[i][0].equals(unknown))
                germanTxt = translationTableGerman[i][1];
        }
        System.out.println("germanTxt is " + germanTxt);
        String line = "";
        try {
            Scanner scanner = new Scanner(file);
            
            List<String> lines = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                //line = scanner.nextLine().trim();
                lines.add(scanner.nextLine());
            }
            System.out.println(lines.get(1).contains(germanTxt));
            for(int i = 0; i < lines.size(); i++)
            {
                if(lines.get(i).contains(germanTxt))
                {
                    System.out.println(lines.get(i));
                    tokens = PreProcessing.OpenNLP.Tokenize(lines.get(i));
                    
                }
                else
                    System.out.println("not found");
                    
            }
            for(String token : tokens)     
                System.out.println(token);
            
            for(int i = 0; i < translationTableGerman.length; i++)
            {
                for(int j = 0; j < tokens.length; j++)
                if(translationTableGerman[i][1].equals(tokens[j]))
                    DV[j] = translationTableGerman[i][0];
            }
            for(String etoken : DV)     
                System.out.println(etoken);
      
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return DV;

    }
    
    
}
