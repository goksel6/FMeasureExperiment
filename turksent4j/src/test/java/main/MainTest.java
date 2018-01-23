package main;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

public class MainTest {

	@Test
	public void tokenArrayTest() throws Exception {

		InputStream modelIn = new FileInputStream("src/main/resources/en-pos-maxent.bin");
		POSModel model = new POSModel(modelIn);

		POSTaggerME tagger = new POSTaggerME(model);

		String sent[] = new String[] { "Most", "large", "cities", " ", "the", "US", "had", "morning", "and",
				"afternoon", "newspapers", ".",null,null };
		String tags[] = tagger.tag(sent);

		for (int i = 0; i < tags.length; i++) {
			System.out.println(sent[i] + "--" + tags[i]);

		}

		assertEquals(12, tags.length);

	}

}
