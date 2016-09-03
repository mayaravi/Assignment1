package mayaravi_CSCI201L_Assignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;


public class FileCheck {
	/****variables***/
	//categories 1-5
	protected static String [] categories = new String[5];
	//point values 1-5
	protected static int [] points = new int[5];
	//[category][point total] = question
	protected static String [][] questions = new String[5][5];
	//[category][point total] = answer
	protected static String [][] answers = new String[5][5];
	//if question is answered, it is marked false
	protected static boolean [][] answeredQuestions = new boolean[5][5];
	protected static String [] finalQuestion = new String[2];
	
	/***returns true if all the categories are valid and added***/
	public boolean addCategories(String line){
		//split categories into a temporary array
		String [] temp = line.split("::");
		//System.out.println(temp.length);
		
		//return false if there are not 5 categories (less or more)
		if(temp.length != 5){
			return false;
		}
		
		//return false if there are duplicate categories
		for(int i = 0; i < temp.length; i++){
			//System.out.println(temp[i]);	
			for(int j = i + 1; j < temp.length; j++){
				//System.out.println("temp[i] " + temp[i] + " temp[j] " + temp[j] + " ");
				if(i != j && temp[i].equals(temp[j])){//if they are not the same element but same value
					//System.out.println("Duplicate categories");
					return false;
				}
			}
		}
		
		//assign temp to categories and return true
		categories = temp; 
		return true;
	}
	
	/***returns true if all the points are valid and added***/
	public boolean addPoints(String line){
		//split points into a temporary array
		String [] tempString = line.split("::");
		int [] tempInt = new int[tempString.length];
		for(int k = 0; k < tempString.length; k++) {
		    tempInt[k] = Integer.parseInt(tempString[k]);
		}
		
		//return false if there are not 5 points inputed (less or more)
		if(tempInt.length != 5){
			return false;
		}
		
		//return false if there are duplicate point values
		for(int i = 0; i < tempInt.length; i++){
			for(int j = i+1; j < tempInt.length; j++){
				if(i != j && tempInt[i] == tempInt[j]){//if they are not the same element but same value
					return false;
				}
			}
		}
		
		//assign temp to categories and return true
		points = tempInt; 
		return true;
	}
	
	/***returns true if all the categories are valid and added***/
	public boolean checkValidLine(String line){
		String [] temp = line.split("::");
		for(int i = 0; i < temp.length; i++){
			//System.out.print(temp[i] + " . ");
		}
		//System.out.println();
		//if everything is on one line
		if(temp.length == 4){
			int cat = checkCategory(temp[0]);
			int pt = checkPoints(Integer.parseInt(temp[1]));
			if(cat != -1 && pt != -1){
				questions[cat][pt] = temp[2];
				answers[cat][pt] = temp[3];
			} else {
				System.out.println("invalid point or category in file");
				return false; 
			}
		} else if(temp.length == 3){
			if(temp[0].equals("FJ")){
				finalQuestion[0] = temp[1];
				finalQuestion[1] = temp[2];
			}
		} else {
			//System.out.println("line: " + line);
			//System.out.println("temp.length " + temp.length);
			//System.out.println("Too many '::' in a line.");
			return false;
		}
		return true;
	}

	/***returns the number of the category and -1 if DNE***/
	public int checkCategory(String line){
		for(int i = 0; i < categories.length; i++){
			if(categories[i].equals(line)){
				return i;
			}
		}
		return -1;
	}
	
	/***returns the number of the points and -1 if DNE***/
	public int checkPoints(int num){
		for(int i = 0; i < points.length; i++){
			if(points[i] == num){
				return i;
			}
		}
		return -1;
	}
	
	/****assuming file can be opened*****/
	public boolean checkValidFile(File file){
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			//add categories
			String line = br.readLine();
			//check if the categories are valid; return false if not
			if(!addCategories(line)){
				System.out.println("invalid categories");
				return false;
			}
			
			//add points
			line = br.readLine();
			//check if the point values are valid; return false if not
			if(!addPoints(line)){
				System.out.println("invalid points");
				return false;
			}
			
			while(line != null){
				line = br.readLine();
				//System.out.println(line);
				if(line != null){
					if(line.substring(0, 2).equals("::")){
						checkValidLine(line.substring(2));
					}
				}
			}
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException ioe) {
					System.out.print(ioe.getMessage());
				}
			}
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException ioe) {
					System.out.print(ioe.getMessage());
				}
			}
		}//end finally
		return true;
	}//end checkValidFile
}//end class
