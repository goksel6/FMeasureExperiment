package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.midi.Sequence;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;

public class Main {
	static Double[][] pointMatrix = new Double[2][5];

	public static void main(String[] args) throws IOException {

//		for (int i = 0; i < 2; i++) {
//			for (int m = 0; m < 5; m++) {
//				pointMatrix[i][m] = 0.0;
//			}
//		}
		// neg
		System.out.println("File 1");
		readFile("C:\\Users\\MONSTER\\Desktop\\neg\\cv000_29416.txt");
		System.out.println("File 2");
		readFile("C:\\Users\\MONSTER\\Desktop\\neg\\cv001_19502.txt");
		System.out.println("File 3");
		readFile("C:\\Users\\MONSTER\\Desktop\\neg\\cv002_17424.txt");
		System.out.println("File 4");
		readFile("C:\\Users\\MONSTER\\Desktop\\neg\\cv003_12683.txt");
		System.out.println("File 5");
		readFile("C:\\Users\\MONSTER\\Desktop\\neg\\cv004_12641.txt");
		// pos
		System.out.println("pos 1-----------------------------");
		readFile("C:\\Users\\MONSTER\\Desktop\\pos\\cv000_29590.txt");
		System.out.println("pos 2--------------------------------");
		readFile("C:\\Users\\MONSTER\\Desktop\\pos\\cv001_18431.txt");
		System.out.println("pos 3----------------------------------");
		readFile("C:\\Users\\MONSTER\\Desktop\\pos\\cv002_15918.txt");
		System.out.println("pos 4-----------------------");
		readFile("C:\\Users\\MONSTER\\Desktop\\pos\\cv003_11664.txt");
		System.out.println("pos 5-----------------------------");
		readFile("C:\\Users\\MONSTER\\Desktop\\pos\\cv004_11636.txt");

		for (int i = 0; i < 2; i++) {
			for (int m = 0; m < 5; m++) {
				System.out.print("  " + pointMatrix[i][m]);
			}
			System.out.println(" ");
		}

	}

	public static void readFile(String FILENAME) {
		ArrayList<String> arrList = new ArrayList<>();
		String[] words = null;

		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				arrList.add(sCurrentLine);
			}
			words = tokenizer(arrList);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String[] tokenizer(ArrayList<String> arrList) throws IOException {

		SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
		System.out.println("-----------------------------------------");
		String[] words = null;

		for (String item : arrList) {
			words = simpleTokenizer.tokenize(item); /* boþluk referansýna gore parçalýyor */
			for (int i = 0; i < words.length; i++) {
				if (words[i].length() != 1) { // sayýlarý da yazdýrmýyoruz.

					// System.out.println(words[i]);
				}

			}
		}
		etkinsiz(words);
		return words;
	}

	public static void etkinsiz(String[] words) throws IOException {
		int k = 0;

		SenticNetSingleton sing = FillHashTable();;
	
		InputStream modelIn = new FileInputStream("src/main/resources/en-pos-maxent.bin");
		POSModel model = new POSModel(modelIn);

		POSTaggerME tagger = new POSTaggerME(model);

		String tags[] = tagger.tag(words);

		for (int i = 0; i < tags.length; i++) {
			if (words[i].length() != 1) {
				System.out.println(words[i] + "--" + tags[i]);
				if (tags[i] == "JJ") {
					pointMatrix[0][k] += Double.parseDouble(sing.get(words[i]));

				}

			}

		}
		k++;

	}

	public static SenticNetSingleton FillHashTable() throws FileNotFoundException, IOException {

		// GET INSTANCE ARACILIÐIYLA SenticNetSingleton SINIFINA AÝT BÝR NESNE
		// OLUÞTURUYORUZ.
		SenticNetSingleton aslýndaNesne = SenticNetSingleton.getInstance();

		FileInputStream FS = new FileInputStream("C:\\Users\\MONSTER\\Desktop\\Sentic.txt");

		BufferedReader br = new BufferedReader(new InputStreamReader(FS));
		String Satir = br.readLine();

		String[] cumle;

		while (Satir != null) {
			cumle = Satir.split("\t");
			aslýndaNesne.put(cumle[0], cumle[2]);
			Satir = br.readLine();

		}
		br.close();

		return (aslýndaNesne);
	}
}
