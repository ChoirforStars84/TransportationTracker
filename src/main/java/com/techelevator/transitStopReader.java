package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class transitStopReader {
	static List<String> answerKey = new ArrayList<String>();

	public static void main(String[] args) {
		File inputFile = getInputFileFromUser();
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
				for(String e : n) {
					System.out.print(e + " ");
				}
				System.out.println("");
			}
			
				System.out.println(stops.size());
				System.out.println(stopToRoute.size());
			}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}			
	}
		
	

		@SuppressWarnings("resource")
		private static File getInputFileFromUser() {
			Scanner userInput = new Scanner(System.in);
			
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

}
