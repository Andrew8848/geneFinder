package MCS.search;

import edu.duke.*;
import java.io.*;

public class StringsThirdAssignments {

//	public int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
//		int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
//		while (currIndex != -1) {
//			int diff = currIndex - startIndex;
//			if (diff % 3 == 0) {
//				return currIndex;
//			} else {
//				currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
//			}
//		}
//		return -1;
//
//	}

//	public String findGene(String dna, int where) {
//		int startIndex = dna.indexOf("atg", where);
//
//		if (startIndex == -1) {
//			return "";
//		}
//
//		int taaIndexS = dna.indexOf("taa", startIndex + 3);
//		int tagIndexS = dna.indexOf("tag", startIndex + 3);
//		int tgaIndexS = dna.indexOf("tga", startIndex + 3);
//		
//		int taaIndex;
//		int tagIndex;
//		int tgaIndex;
//		
//		if ((taaIndexS-startIndex) % 3 == 1) {
//			taaIndex = taaIndexS;
//		}else {
//			taaIndex = -1;
//		}
//		
//		if ((tagIndexS-startIndex) % 3 == 1) {
//			tagIndex = tagIndexS;
//		}else {
//			tagIndex = -1;
//		}
//	
//		if ((tgaIndexS-startIndex) % 3 == 1) {
//			tgaIndex = tgaIndexS;
//		}else {
//			tgaIndex = -1;
//		}

//		
//		int minIndex = 0;
//
//		if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
//			minIndex = tgaIndex;
//		} else {
//			minIndex = taaIndex;
//		}
//		if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
//			minIndex = tagIndex;
//		}
//		if (minIndex == -1) {
//			return "";
//		}
//
//		return dna.substring(startIndex, minIndex + 3);
//	}

	public int chekGene(String dna, String stopCodon, int startIndex) {

		int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
		int geneInt = (stopIndex+3) - startIndex;
		String gene = dna.substring(startIndex, stopIndex+3);
		
		if (gene.length() % 3 == 0) {
			return stopIndex;
		}

			return -1;
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
		
		return dna.substring(startIndex, minIndex + 3);
	}

	public StorageResource printAllGenes(String dna) {
		StorageResource geneList = new StorageResource();
		int startIndex = 0;
	//	int zeroToOne = 0;
		//boolean toSwitchNum = false;
		while (true) {
			
//			if(toSwitchNum == true) {
//				 zeroToOne = 1;
//			}
			
			String currentGene = findGene(dna, startIndex);
			
			if (currentGene.isEmpty()) {
				break;
			}
			
			geneList.add(currentGene);
			startIndex = dna.indexOf("atg", (startIndex + currentGene.length()) + 1);
//            toSwitchNum = true;
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
			String fastTest = "tggcctadtgatggttcagtggattgatttaagttaggtgaggcttatggtcaaggtctaa";
			System.out.println("dnaFromDB: " + "read " + dnaFromDB.length() + " characters");
			testOn(dnaFromDB);
			String ans = findGene(fastTest, 0);
			System.out.println("     ");
			System.out.println("fastTest: " + ans);
		}

	}

	public static void main(String[] args) {
		StringsThirdAssignments tf = new StringsThirdAssignments();
		tf.realTesting();
	}
}
