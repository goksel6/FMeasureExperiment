package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.Sequence;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		String sentence = "Hi. How are you? Welcome to Tutorialspoint. "
				+ "We provide free tutorials on various technologies";

		// Instantiating SimpleTokenizer class
		SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;

		// Tokenizing the given sentence
		String tokens[] = simpleTokenizer.tokenize(sentence);

		// Printing the tokens
		for (String token : tokens) {
			System.out.println(token);
		}

		InputStream modelIn = new FileInputStream("src/main/resources/en-pos-maxent.bin");
		POSModel model = new POSModel(modelIn);

		POSTaggerME tagger = new POSTaggerME(model);

//		String sent[] = new String[] { "Most", "large", "cities", "in", "the", "US", "had", "morning", "and",
//				"afternoon", "newspapers", "." };
//		String tags[] = tagger.tag(sent);
//		
//		for (int i = 0; i < tags.length; i++) {
//			System.out.println(sent[i] + "--"+ tags[i]);
//			
//		}
		 String sent[] = new String[tokens.length];
		 for(int i=0; i<tokens.length;i++) {
		 sent[i]=tokens[i].trim();
		 
		
		 }
		 String tags[] = tagger.tag(sent);
		for (int i = 0; i < tags.length; i++) {
		System.out.println(sent[i] + "--"+ tags[i]);
		
	}
		
		

		// POSTaggerME tagger = new POSTaggerME(model);
		
		// double probs[] = tagger.probs();
		// opennlp.tools.util.Sequence[] topSequences = tagger.topKSequences(sent);

	}

}
