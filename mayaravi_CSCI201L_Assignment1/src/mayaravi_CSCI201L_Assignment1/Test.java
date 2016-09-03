package mayaravi_CSCI201L_Assignment1;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileNotFoundException;
//import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Test {
	public static void main(String [] args){
		FileCheck phil = new FileCheck();
		File file = new File(args[0]); 
		//System.out.println(args[0]);
		if(!file.canRead() || !file.exists()){
			System.out.println("failed");
			return;
		}
		//file checking
		phil.checkValidFile(file);
		
		//gameplay
		GamePlay game = new GamePlay();
		game.welcomeScreen();
		game.play();
		
	}
}
