/*
 @author Shimaa Ibrahim
 */

package PreProcessing;

//import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.io.StringReader;
//import java.util.StringTokenizer;

//import opennlp.tools.langdetect.*;

//import opennlp.tools.chunker.ChunkerME;
//import opennlp.tools.chunker.ChunkerModel;
//import opennlp.tools.cmdline.PerformanceMonitor;
//import opennlp.tools.cmdline.parser.ParserTool;
//import opennlp.tools.cmdline.postag.POSModelLoader;
//import opennlp.tools.namefind.NameFinderME;
//import opennlp.tools.namefind.TokenNameFinderModel;
//import opennlp.tools.parser.ParserFactory;
//import opennlp.tools.parser.ParserModel;
//import opennlp.tools.parser.chunking.Parser;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
//import opennlp.tools.tokenize.Tokenizer;
//import opennlp.tools.tokenize.TokenizerME;
//import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
//import opennlp.tools.util.ObjectStream;
//import opennlp.tools.util.PlainTextByLineStream;
//import opennlp.tools.util.Span;
//import opennlp.tools.parser.Parse;

/**
 *
 * @author shimaa
 */
public class OpenNLP {
    public static String RESOURCES = "/home/shimaa/NetBeansProjects/MultilingualOntologyEnrichment/resources/";
    public static String TagTokens[] = null; 
    public static String tags[]= null;
    public static String[] SentenceDetect(String paragraph) throws InvalidFormatException,IOException {
		
                //Loading sentence detector model
		InputStream inputStream = new FileInputStream( OpenNLP.RESOURCES+"en-sent.bin" );
		
		SentenceModel model = new SentenceModel(inputStream);
		SentenceDetectorME sdetector = new SentenceDetectorME(model);
		
		String sentences[] = sdetector.sentDetect(paragraph);
		inputStream.close();
		return sentences;
	}
	
	public static String[] Tokenize(String paragraph) throws InvalidFormatException, IOException {
                //Using WhitespaceTokenizer − This class uses whitespaces to tokenize the given text.
                //Instantiating whitespaceTokenizer class 
                WhitespaceTokenizer whitespaceTokenizer = WhitespaceTokenizer.INSTANCE;  

               //Tokenizing the given paragraph 
               String tokens[] = whitespaceTokenizer.tokenize(paragraph);  
               return tokens;               
	}
	
	public static String POSTag(String input) throws IOException {
		/*
                NN - Noun, singular or mass
                DT	Determiner
                VB	Verb, base form
                VBD	Verb, past tense
                VBZ	Verb, third person singular present
                IN	Preposition or subordinating conjunction
                NNP	Proper noun, singular
                TO	to
                JJ	Adjective
                */
		//Loading Parts of speech-maxent model       
                InputStream inputStream = new FileInputStream(OpenNLP.RESOURCES+"en-pos-maxent.bin"); 
                POSModel model = new POSModel(inputStream); 
                //Instantiating POSTaggerME class 
                POSTaggerME tagger = new POSTaggerME(model); 
                
                //Tokenizing the sentence using WhitespaceTokenizer class  
                TagTokens = Tokenize(input);
                //Generating tags 
                tags = tagger.tag(TagTokens);
                
                //Instantiating the POSSample class 
                POSSample sample = new POSSample(TagTokens, tags); 
                //System.out.println(sample.toString()); 
                return sample.toString();
	}
}
