/*
 @author Shimaa Ibrahim
 */

package multilingualontologyenrichment;

import java.util.*;
//import PreProcessing.StopWordsRemoval;
import java.io.IOException;
//import java.util.StringTokenizer;
//import org.apache.tika.exception.TikaException;
import org.apache.tika.language.LanguageIdentifier;

import com.google.api.translate.Language; 
import com.google.api.translate.Translator;


/**
 *
 * @author shimaa
 */
public class MultilingualOntologyEnrichment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        //###################################################################################################################
       //###################################################################################################################
       /*
        //String sentence = "Hi. How are you? Welcome to Tutorialspoint. We provide free tutorials on various technologies";
        String Paragraph_1 = "For classification of documents based on the language they are written in a multilingual "
                + "website, a language detection tool is needed. This tool should accept documents without language "
                + "annotation (metadata) and add that information in the metadata of the document by detecting "
                + "the language.";
        //String paragraph_Tourism = "Pyramids have been built by civilizations in many parts of the world. The largest "
          //      + "pyramid by volume is the Great Pyramid of Cholula, in the Mexican state of Puebla. For thousands "
            //    + "of years, the largest structures on Earth were pyramids—first the Red Pyramid in the Dashur "
             //   + "Necropolis and then the Great Pyramid of Khufu, both of Egypt, the latter is the only one of the "
              //  + "Seven Wonders of the Ancient World still remaining.";
        String paragraph_Tourism = "Pyramids have been runs built by civilizations. The runs largest "
                + "pyramid by volume is the Great Pyramid of Cholula.";
        String domain_1 = "Tourism";
        
        //String paragraph_Economy = "The initial public offering of stocks and bonds to investors is by definition "
         //       + "done in the primary market and subsequent trading is done in the secondary market. A stock "
           //     + "exchange is often the most important component of a stock market. Supply and demand in stock "
             //   + "markets are driven by various factors that, as in all free markets, affect the price of stocks egypt Egypt.";
        
        String paragraph_Economy = "A stock runs exchange is often the most runs important component of a stock market, Cholula civilizations civilizations.";
        String domain_2 = "Economy";
        
        String Paragraph_IT = "Dear Mr Fathallah,\n" +
        "My important computer has no Internet connection. My important computer runs windows. My Windows 7 is 32-bit.\n" +
        "Regards,\n" +
        "Hassan";
        String domain_3 = "Information Technology";
        
        String testParagraph = "Pyramids Internet important civilizations. Stock market Great Pyramid of Cholula largest. With my best regards Shimaa";
        //String sentence = "Zu meiner Familie gehören vier Personen. Die Mutter bin ich und dann gehört natürlich mein Mann dazu. Wir haben zwei Kinder, einen Sohn, der sechs Jahre alt ist und eine dreijährige Tochter.";
 //######################### Language Detection #####################################
        //Domain 1 Tourism
        System.out.println("Domain 1: " + domain_1);
        LanguageIdentifier identifier = new LanguageIdentifier(paragraph_Tourism);
        String language = identifier.getLanguage();
        System.out.println("Language of the given content is : " + language);
        //Domain 2 Economy
        System.out.println("Domain 2: " + domain_2);
        LanguageIdentifier identifier2 = new LanguageIdentifier(paragraph_Economy);
        String language2 = identifier2.getLanguage();
        System.out.println("Language of the given content is : " + language2);
        //Domain 3 IT
        System.out.println("Domain 3: " + domain_3);
        LanguageIdentifier identifier3 = new LanguageIdentifier(Paragraph_IT);
        String language3 = identifier3.getLanguage();
        System.out.println("Language of the given content is : " + language3);

//######################### Sentence Splittet #####################################
        System.out.println("######################## Sentence Detection #########################");
        //Domain 1
        System.out.println("Domain 1: " + domain_1);
        String[] sentences_Tourism = PreProcessing.OpenNLP.SentenceDetect(paragraph_Tourism);
        System.out.println("Number of sentences = "+ sentences_Tourism.length);
        for(int i=0; i<sentences_Tourism.length; i++){
            System.out.println("Sentence number "+ i + ": "+ sentences_Tourism[i]);
        }
        //Domain 2
        System.out.println("Domain 2: " + domain_2);
        String[] sentences_Economy = PreProcessing.OpenNLP.SentenceDetect(paragraph_Economy);
        System.out.println("Number of sentences = "+ sentences_Economy.length);
        for(int i=0; i<sentences_Economy.length; i++){
            System.out.println("Sentence number "+ i + ": "+ sentences_Economy[i]);
        }
        //Domain 3
        System.out.println("Domain 3: " + domain_3);
        String[] sentences_IT = PreProcessing.OpenNLP.SentenceDetect(Paragraph_IT);
        System.out.println("Number of sentences = "+ sentences_IT.length);
        for(int i=0; i<sentences_IT.length; i++){
            System.out.println("Sentence number "+ i + ": "+ sentences_IT[i]);
        }
              
        //############### Tokenization ###############
        //System.out.println("############ Tokens by white space #############");
        //String tokens[] = PreProcessing.OpenNLP.Tokenize(Paragraph);        
        //Printing the tokens 
        //for(String token : tokens)     
        //   System.out.println(token);
        //############### Removing Stop Wrods from tokens ###############
        System.out.println("######################## Tokens after removing stop words #########################");
        //Domain 1
        System.out.println("Domain 1: " + domain_1);
        String s[] = new String[sentences_Tourism.length];
        for(int i=0; i<sentences_Tourism.length; i++){
            s[i] = PreProcessing.StopWordsRemoval.removeStopWordsFromSentence(sentences_Tourism[i]);
        }
        for(int i=0; i<s.length; i++){
            System.out.println("Sentence number "+ i + ": "+ s[i]);
        }  
        //Domain 2
        System.out.println("Domain 2: " + domain_2);
        String s2[] = new String[sentences_Economy.length];
        for(int i=0; i<sentences_Economy.length; i++){
            s2[i] = PreProcessing.StopWordsRemoval.removeStopWordsFromSentence(sentences_Economy[i]);
        }
        for(int i=0; i<s2.length; i++){
            System.out.println("Sentence number "+ i + ": "+ s2[i]);
        }
        //Domain 3
        System.out.println("Domain 3: " + domain_3);
        String s3[] = new String[sentences_IT.length];
        for(int i=0; i<sentences_IT.length; i++){
            s3[i] = PreProcessing.StopWordsRemoval.removeStopWordsFromSentence(sentences_IT[i]);
        }
        for(int i=0; i<s3.length; i++){
            System.out.println("Sentence number "+ i + ": "+ s3[i]);
        }
        // ############### POS Tagging ###############        
        System.out.println("######################## POSTag #########################");        
        //Domain 1
        System.out.println("Domain 1: " + domain_1);
        String sentences_POSTag[]= new String[s.length];
        ArrayList<String> words_d1 = new ArrayList<String>();
        for(int i=0; i<s.length; i++){
            sentences_POSTag[i] = PreProcessing.OpenNLP.POSTag(s[i]);
            System.out.println("Sentence number "+ i + ": "+ sentences_POSTag[i]);
            //System.out.println("Number of tokens: "+ PreProcessing.OpenNLP.TagTokens.length);
            //System.out.println("Number of tags: "+ PreProcessing.OpenNLP.tags.length);
            //System.out.println("Tokens and tags in sentence "+ i +":" );
            for(int j=0; j<PreProcessing.OpenNLP.TagTokens.length; j++){
                //System.out.println(PreProcessing.OpenNLP.TagTokens[j]+" "+ PreProcessing.OpenNLP.tags[j]);
                words_d1.add(PreProcessing.OpenNLP.TagTokens[j].toLowerCase());
                
            }
        }
        //Domain 2
        System.out.println("Domain 2: " + domain_2);
        String sentences_POSTag2[]= new String[s2.length];
        ArrayList<String> words_d2 = new ArrayList<String>();
        for(int i=0; i<s2.length; i++){
            sentences_POSTag2[i] = PreProcessing.OpenNLP.POSTag(s2[i]);
            System.out.println("Sentence number "+ i + ": "+ sentences_POSTag2[i]);
            //System.out.println("Number of tokens: "+ PreProcessing.OpenNLP.TagTokens.length);
            //System.out.println("Number of tags: "+ PreProcessing.OpenNLP.tags.length);
            //System.out.println("Tokens and tags in sentence "+ i +":" );
            for(int j=0; j<PreProcessing.OpenNLP.TagTokens.length; j++){
                //System.out.println(PreProcessing.OpenNLP.TagTokens[j]+" "+ PreProcessing.OpenNLP.tags[j]);
                words_d2.add(PreProcessing.OpenNLP.TagTokens[j].toLowerCase());
                
            }
        }
        //Domain 3
        System.out.println("Domain 3: " + domain_3);
        String sentences_POSTag3[]= new String[s3.length];
        ArrayList<String> words_d3 = new ArrayList<String>();
        for(int i=0; i<s3.length; i++){
            sentences_POSTag3[i] = PreProcessing.OpenNLP.POSTag(s3[i]);
            System.out.println("Sentence number "+ i + ": "+ sentences_POSTag3[i]);
            System.out.println("Number of tokens: "+ PreProcessing.OpenNLP.TagTokens.length);
            System.out.println("Number of tags: "+ PreProcessing.OpenNLP.tags.length);
            System.out.println("Tokens and tags in sentence "+ i +":" );
            for(int j=0; j<PreProcessing.OpenNLP.TagTokens.length; j++){
                System.out.println(PreProcessing.OpenNLP.TagTokens[j]+" "+ PreProcessing.OpenNLP.tags[j]);
                words_d3.add(PreProcessing.OpenNLP.TagTokens[j].toLowerCase());
                
            }
        }
        
        //####################Testing##############################
        System.out.println("################# Testing #############");
        //Testing
        LanguageIdentifier identifierTesting = new LanguageIdentifier(testParagraph);
        String languageTesting = identifierTesting.getLanguage();
        System.out.println("Language of the given content is : " + languageTesting);
        
        //Split sentences
        String[] sentences_test = PreProcessing.OpenNLP.SentenceDetect(testParagraph);
        System.out.println("Number of sentences = "+ sentences_test.length);
        for(int i=0; i<sentences_test.length; i++){
            System.out.println("Sentence number "+ i + ": "+ sentences_test[i]);
        }
        
        //Tokens
        String sTest[] = new String[sentences_test.length];
        for(int i=0; i<sentences_test.length; i++){
            sTest[i] = PreProcessing.StopWordsRemoval.removeStopWordsFromSentence(sentences_test[i]);
        }
        for(int i=0; i<sTest.length; i++){
            System.out.println("Sentence number "+ i + " after removing stop words: "+ sTest[i]);
        }  
        //POS tagging
        String sentencesTest_POSTag[]= new String[sTest.length];
        ArrayList<String> words_test = new ArrayList<String>();
        Integer numberOfNouns[] = new Integer[sentences_test.length];
        for(int i=0; i<sTest.length; i++){
            sentencesTest_POSTag[i] = PreProcessing.OpenNLP.POSTag(sTest[i]);
            System.out.println("Sentence number "+ i + "with tags: "+ sentencesTest_POSTag[i]);
            System.out.println("Number of tokens: "+ PreProcessing.OpenNLP.TagTokens.length);
            System.out.println("Number of tags: "+ PreProcessing.OpenNLP.tags.length);
            //System.out.println("Tokens and tags in sentence "+ i +":" );
            numberOfNouns[i] = 0;
            for(int j=0; j<PreProcessing.OpenNLP.TagTokens.length; j++){
                //System.out.println(PreProcessing.OpenNLP.TagTokens[j]+" "+ PreProcessing.OpenNLP.tags[j]);
                words_test.add(PreProcessing.OpenNLP.TagTokens[j].toLowerCase());
                if(PreProcessing.OpenNLP.tags[j].equals("NN") || PreProcessing.OpenNLP.tags[j].equals("NNP") || PreProcessing.OpenNLP.tags[j].equals("NNS"))
                    numberOfNouns[i] ++;
            }
            System.out.println("Number of nouns = "+ numberOfNouns[i]);
        }
        //################## Classification ################
        System.out.println("################## Membership Classification ################");        
        
        DomainIdentification.Classification.Classification_text_to_Domain_training(words_d1, words_d2, words_d3);
        String dominantClassification = DomainIdentification.Classification.Classification_text_to_Domain_testing(words_d1, words_d2, words_d3, words_test);
        System.out.println("The domain for the tested text is "+ dominantClassification);

        //################## Sentence Selection ################
        List<String> sentencesSelected = new ArrayList<>(Arrays.asList(sTest));
        
        System.out.println("################## Candidate Sentence Selection ################" + sentencesSelected.size());
        System.out.println("All sentences in the text: ");
        for(int i = 0; i<sTest.length; i++)
            System.out.println("Sentence "+i+" "+sTest[i]);
        for(int i = 0; i<sentencesSelected.size(); i++)
            if(numberOfNouns[i] < 2)
            {
                sentencesSelected.remove(i);
            }
        System.out.println("Candidate Sentences: ");
        System.out.println(sentencesSelected);
        */
       //###################################################################################################################
       //###################################################################################################################
        //############ Learning ########################
        System.out.println("Ontology Learning");
        //System.out.println("The look-up table in the master Agent");
        String [][]lookUpTable= new String[3][3];
        lookUpTable = OntologyEnrichment.MasterAgent.BuildLookUpTable();
        /*for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
                System.out.print(lookUpTable[i][j]+ " ");
            System.out.println("");
        }*/
        
        //int domainAgentID = OntologyEnrichment.MasterAgent.FindDomainAgent(lookUpTable, dominantClassification);
        //System.out.println("Domain ID for "+ dominantClassification+ " is "+ domainAgentID);
        
        //int languageAgentID = OntologyEnrichment.MasterAgent.FindLanguageAgent(lookUpTable, "English");
        //System.out.println("Language Agent ID for English is "+ languageAgentID);
        
       // String Input = "Professor teaches courses";
                //OntologyEnrichment.MasterAgent.ReadOntology();
      //  String InputPOS = PreProcessing.OpenNLP.POSTag(Input);
        //System.out.println(InputPOS);
        //OntologyEnrichment.MasterAgent.LanguageAgent_English(Input);
        //OntologyEnrichment.MasterAgent.writeFile3();
        
        //Translator translate = Translator.getInstance();
        //String text = translate.translate("Hello!", Language.ENGLISH, Language.ARABIC);
        //System.out.println(text); 
        
        // Set the Google Translate API key
        // See: http://code.google.com/apis/language/translate/v2/getting_started.html
        //GoogleAPI.setKey(/* Enter your API key here */);

        //String translatedText = Translator.execute("Bonjour le monde", Language.FRENCH, Language.ENGLISH);
                //Translate.DEFAULT.execute("Bonjour le monde", Language.FRENCH, Language.ENGLISH);

        //System.out.println(translatedText);
    }
}
