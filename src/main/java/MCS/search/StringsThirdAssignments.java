package MCS.search;

import edu.duke.*;
import java.io.*;

public class StringsThirdAssignments {

	public int newPosOfStopCodon() {
		return 0;
	}

	public int chekGene(String dna, String stopCodon, int startIndex) {
		int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
		// int geneInt = (stopIndex+3) - startIndex;
		while (true) {
			String gene = dna.substring(startIndex, stopIndex + 3);
			if (gene.length() % 3 == 0) {
				return stopIndex;

			} else {
				stopIndex = dna.indexOf(stopCodon, startIndex + gene.length());
			}
		}
	}

	public String findGene(String dna, int posStartIndex) {
		int startIndex = dna.indexOf("atg", posStartIndex);

		if (startIndex == -1) {
			return null;
		}
		int stopCodonOfTaa = chekGene(dna, "taa", startIndex);
		int stopCodonOfTag = chekGene(dna, "tag", startIndex);
		int stopCodonOfTga = chekGene(dna, "tga", startIndex);

		int minIndex = 0;

		if (stopCodonOfTaa == -1 && stopCodonOfTag == -1 && stopCodonOfTga == -1) {

		}

		if (stopCodonOfTaa == -1 || (stopCodonOfTga != -1 && stopCodonOfTga < stopCodonOfTaa)) {
			minIndex = stopCodonOfTga;
		} else {
			minIndex = stopCodonOfTaa;
		}
		if (minIndex == -1 || (stopCodonOfTag != -1 && stopCodonOfTag < minIndex)) {
			minIndex = stopCodonOfTag;
		}
		if (minIndex == -1) {
			return "";
		}

		return dna.substring(startIndex, minIndex+3);
	}

	public StorageResource printAllGenes(String dna) {
		StorageResource geneList = new StorageResource();
		int startIndex = 0;

		while (true) {

			String currentGene = findGene(dna, startIndex);

			if (currentGene.isEmpty()) {
				break;
			}

			geneList.add(currentGene);
			startIndex = dna.indexOf("atg", startIndex + currentGene.length());

		}
		return geneList;
	}

	public void testOn(String dna) {
		StorageResource genes = printAllGenes(dna);

		for (String s : genes.data()) {
			float result = cgRatio(s);
			System.out.println("cgRatio: " + result);
			System.out.println("genes: " + s);

		}
	}

	private float cgRatio(String gene) {
		int cCount = numContains(gene, "c");
		int gCount = numContains(gene, "g");
		return ((float) cCount + gCount) / gene.length();
	}

	private int numContains(String str, String subString) {
		int count = 0;
		int index = str.indexOf(subString);
		while (index != -1) {
			count += 1;
			index = str.indexOf(subString, index + subString.length());
		}

		return count;
	}

	public void realTesting() {
		DirectoryResource dr = new DirectoryResource();

		for (File f : dr.selectedFiles()) {

			FileResource fr = new FileResource(f);
			String dnaFromDB = fr.asString();
			System.out.println("dnaFromDB: " + "read " + dnaFromDB.length() + " characters");
			testOn(dnaFromDB);
			
		}

	}

	public static void main(String[] args) {
		StringsThirdAssignments tf = new StringsThirdAssignments();
		tf.realTesting();
	}
}
