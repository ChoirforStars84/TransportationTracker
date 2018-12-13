package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class transitStopReader {
	static List<String> answerKey = new ArrayList<String>();

	public static void main(String[] args) {
		File inputFile = getInputFile();
		File outputFile = getOutputFile();
		Scanner input = new Scanner(System.in);
		List<String []> stops = new ArrayList<String[]>();
		List<String []> stopToRoute = new ArrayList<String[]>();



		
		
		try(Scanner fileScanner = new Scanner(inputFile)) {
			while(fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				String[] stopElements = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				stopElements[9] = stopElements[9].replace("\"", "");
				
				String[] busLines = stopElements[9].split(",");
				for(String busLine : busLines) {
					busLine = busLine.trim();
					String[] mergeElements = {stopElements[0] , busLine};
					stopToRoute.add(mergeElements);
				}
				String[] something = {stopElements[0] , stopElements[1] , stopElements[2] , stopElements[3] , stopElements[4] , stopElements[5] , stopElements[6] , stopElements[7] , stopElements[8] , stopElements[10] , stopElements[11] , stopElements[12]};
				
					stops.add(something);					
				}
			
			for(String[] stop : stops) {
				for(String stopElement : stop) {
					System.out.print(stopElement + " ");
				}
				System.out.println("");				
			}
			
			for(String[] n : stopToRoute) {
				try(PrintWriter appendWriter = new PrintWriter(new FileWriter(outputFile, true));) {
					appendWriter.println("('"+n[0]+"','"+n[1]+"'),");
				}catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
			
				System.out.println(stops.size());
				System.out.println(stopToRoute.size());
			}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}			
	}
		
	

		@SuppressWarnings("resource")
		private static File getInputFile() {			
			String path = "src/main/resources/transitStops.txt";
			
			File inputFile = new File(path);
			if(inputFile.exists() == false) { 
				System.out.println(path+" does not exist");
				System.exit(1); 
			} else if(inputFile.isFile() == false) {
				System.out.println(path+" is not a file");
				System.exit(1);
			}
			return inputFile;
		}
		
		@SuppressWarnings("resource")
		private static File getOutputFile() {
			
			String path = "src/main/resources/stopToRoute.txt";
			
			File outputFile = new File(path);
			if(outputFile.exists() == false) { 
				System.out.println(path+" does not exist");
				System.exit(1); 
			} else if(outputFile.isFile() == false) {
				System.out.println(path+" is not a file");
				System.exit(1);
			}
			return outputFile;
		}

}
