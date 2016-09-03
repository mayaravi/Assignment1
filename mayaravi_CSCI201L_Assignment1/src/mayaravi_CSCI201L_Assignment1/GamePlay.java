package mayaravi_CSCI201L_Assignment1;

import java.util.Scanner; 
import java.util.Random;

public class GamePlay {
	/***variables***/
	protected Team [] teams;
	//number of teams
	protected int numberOfTeams;
	//counter that keeps track of number of questions answered
	protected int counter = 0;
	Scanner scan = new Scanner(System.in);
	
	//reset everything
	public void reset(){
		//reset answeredQuestions
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				FileCheck.answeredQuestions[i][j] = false;
			}
		}
		
		//reset pointTotals
		for(int i = 0; i < teams.length; i++){
			teams[i].setPoints(0);
		}
		
	}
	
	public void welcomeScreen(){
		System.out.println("Welcome to Jeopardy!");
		System.out.println("Please enter the number of teams that will be playing in this game: ");
				
		//variables initialized
		/*teams[0] = new Team();
		teams[1] = new Team();
		teams[2] = new Team();
		teams[3] = new Team();*/
		
		//Scanner scan = new Scanner(System.in);
		numberOfTeams  = scan.nextInt();
		while(!(numberOfTeams >= 1 && numberOfTeams <= 4)){
			System.out.println("Invalid entry; Please try again.");
			numberOfTeams = scan.nextInt();
		}
		
		Team [] temp = new Team[numberOfTeams];

		//delete unused teams
		for(int i = 0; i < temp.length; i++){
			temp[i] = new Team();
		}
		
		teams = temp;
		scan.nextLine();
		for(int i = 0; i < numberOfTeams; i++){
			System.out.println("Please choose a name for Team " + (i+ 1));
			String name = scan.nextLine();
			teams[i].setName(name);
		}
		
		System.out.println("Thank you! Setting up game for you...");
		//scan.nextLine();
		//scan.close();
	}
	
	public void printPoints(){
		System.out.println("Here are the updated scores:");
		System.out.println("------------------------------------------------------------");
		for(int i = 0; i < teams.length; i++){
			System.out.print("|     ");
			System.out.print(teams[i].getName());
			System.out.print("      ");
		}
		System.out.println("|");
		System.out.println("------------------------------------------------------------");
		for(int j = 0; j < teams.length; j++){
			System.out.print("|     ");
			System.out.print(teams[j].getPoints());
			System.out.print("      ");
		}
		System.out.println("     |");
		System.out.println("------------------------------------------------------------");
	}
		
	public int validCategoryInput(String line){
		if(line.equalsIgnoreCase("Category 1")){
			return 0;
		} else if(line.equalsIgnoreCase("Category 2")){
			return 1;
		} else if(line.equalsIgnoreCase("Category 3")){
			return 2;
		} else if(line.equalsIgnoreCase("Category 4")){
			return 3;
		} else if(line.equalsIgnoreCase("Category 5")){
			return 4;
		} else {
			return -1;
		}
	}
	
	
	public int validPointInput(int num){
		for(int i = 0; i < (FileCheck.points).length; i++){
			if(FileCheck.points[i] == num){
				return i;
			}
		}
		return -1;
	}
	
	
	public void askQuestion(Team team, boolean repeat){
		//Scanner scan2 = new Scanner(System.in);
		if(!repeat){
			System.out.println("It is team " + team.getName() + "'s turn to answer.");
		}
		System.out.println("Please choose a category: ");
		if(repeat){
			scan.nextLine();
		}
		String cat;
		//scan.nextLine();
		cat = scan.nextLine();
		//check if valid
		if(cat.equalsIgnoreCase("exit") || cat.equalsIgnoreCase("replay")){
			if(cat.equalsIgnoreCase("exit")){
				System.exit(0);
			} else {
				System.out.println("Restarting game.");
				play();
				return;
			}
		}
		if(validCategoryInput(cat) == -1){
			while(validCategoryInput(cat) == -1){
				System.out.println("Invalid category; Please enter a valid category: ");
				cat = scan.nextLine();
			}
		}
		
		//once user has inputed a valid category
		//repeat with points
		System.out.println("Please enter the dollar value of the question you wish to answer: ");
		String strpt;
		strpt = scan.next();
		if(strpt.equalsIgnoreCase("exit") || strpt.equalsIgnoreCase("replay")){
			if(strpt.equalsIgnoreCase("exit")){
				System.exit(0);
			} else {
				System.out.println("Restarting game.");
				play();
				return;
			}
		}
		int pt = Integer.parseInt(strpt);
		//check if valid
		if(validPointInput(pt) == -1){
			while(validPointInput(pt) == -1){
				System.out.println("Invalid point value; Please enter a valid point value: ");
				pt = scan.nextInt();
			}
		}
		
		//once you have valid indexes
		int catIndex = validCategoryInput(cat);
		int ptIndex = validPointInput(pt);
		//check if the question has not been answered before printing
		if(!FileCheck.answeredQuestions[catIndex][ptIndex]){
			System.out.println(FileCheck.questions[catIndex][ptIndex]);
			FileCheck.answeredQuestions[catIndex][ptIndex] = true;
			counter++;
		} else {
			System.out.println("This question has been answered already. Please choose another question: ");
			askQuestion(team, true);
			//scan.nextLine();
			return;
		}
		
		System.out.println("Please enter your answer. Remember to pose it as a question:");
		String correctAnswer = FileCheck.answers[catIndex][ptIndex];
		scan.nextLine();
		String ans = scan.nextLine();
		
		if(ans.equalsIgnoreCase("exit") || ans.equalsIgnoreCase("replay")){
			if(ans.equalsIgnoreCase("exit")){
				System.exit(0);
			} else {
				System.out.println("Restarting game.");
				play();
				return;
			}
		}
		
		if(ans.equalsIgnoreCase("who is " + correctAnswer)){
			team.addPoints(pt);
			System.out.println("You got the correct answer! " + pt + " will be added to your score.");
		} else if(ans.equalsIgnoreCase("who are " + correctAnswer)){
			team.addPoints(pt);
			System.out.println("You got the correct answer! " + pt + " will be added to your score.");
		} else if(!ans.substring(0, 2).equalsIgnoreCase("wh")){
			System.out.println("Please enter your answer in the form of a question.");
			ans = scan.nextLine();
			
			if(ans.equalsIgnoreCase("who is " + correctAnswer) || 
					ans.equalsIgnoreCase("who are " + correctAnswer)){
				team.addPoints(pt);
				System.out.println("You got the correct answer! " + pt + " will be added to your score.");
			} else {
				team.subtractPoints(pt);
				System.out.println("You got the wrong answer! " + pt + " will be subtracted from your score. " +
						"The correct answer was " + correctAnswer);
			}
		} else {
			team.subtractPoints(pt);
			System.out.println("You got the wrong answer! " + pt + " will be subtracted from your score. " +
					"The correct answer was " + correctAnswer);
		}
		repeat = false;
	}//end askQuestion
	
	
	public void placeBet(Team team){
		if(team.getPoints() < 0){
			System.out.println("Team " + team.getName() + " cannot participate in Final Jeopardy.");
		} else {
			System.out.println("Team " + team.getName() + ", please give a dollar amount from "
					+ "your total that you would like to bet.");
			String value = scan.next();
			if(value.equalsIgnoreCase("exit") || value.equalsIgnoreCase("replay")){
				if(value.equalsIgnoreCase("exit")){
					System.exit(0);
				} else {
					System.out.println("Restarting game.");
					play();
					return;
				}
			}
			int intVal = Integer.parseInt(value);
			if(intVal <= team.getPoints() && intVal >= 0){
				team.setBet(intVal);
			} else {
				while(!(intVal <= team.getPoints() && intVal >= 0)){
					System.out.println("This bet is not valid. Please enter a valid amount:");
					intVal = scan.nextInt();
				}
				team.setBet(intVal);
			}
		}
	}
	
	public void writeAnswers(){
		for(int i = 0; i < teams.length; i++){
			if(teams[i].getPoints() > 0){
				System.out.println("Team " + teams[i].getName() + ", please enter your answer:");
				String ans = scan.nextLine();
				if(ans.equalsIgnoreCase("exit") || ans.equalsIgnoreCase("replay")){
					if(ans.equalsIgnoreCase("exit")){
						System.exit(0);
					} else {
						System.out.println("Restarting game.");
						play();
						return;
					}
				}
				teams[i].setAnswer(ans);
			}
		}
		for(int j = 0; j < teams.length; j++){
			if(teams[j].getPoints() > 0){
				if(teams[j].getAnswer().equalsIgnoreCase(FileCheck.finalQuestion[1])){
					System.out.println("Team " + teams[j].getName() + " got the answer correct! "
							+ teams[j].getBet() + " will be added to your score!");
					teams[j].addPoints(teams[j].getBet());
				} else {
					System.out.println("Team " + teams[j].getName() + " got the answer wrong! The correct"
							+ " answer is " + FileCheck.finalQuestion[1] + "."
							+ teams[j].getBet() + " will be subtracted from your score!");
					teams[j].subtractPoints(teams[j].getBet());
				}
			}
		}
	}
	
	public void calculateFinalScore(){
		int highestScore = teams[0].getPoints();
		Team winningTeam = teams[0];
		for(int i = 1; i < teams.length; i++){
			if(teams[i].getPoints() >= highestScore){
				highestScore = teams[i].getPoints();
				winningTeam = teams[i];
			}
		}
		System.out.println("And the winner is " + winningTeam.getName() + " with " + highestScore);
		printPoints();
	}
	
	/**
	 * Adapted from http://www.programcreek.com/2015/03/rotate-array-in-java/
	 * Date: Sept. 1, 2016
	 * I used it to rotate an array with a new starting element. This will be
	 * ultimately used as my round robin format.
	 */
	public void rotate(Team [] arr, int firstTeam){
		for(int i = 0; i < firstTeam; i++){
			for(int j = arr.length - 1; j > 0; j--){
				Team temp = arr[j];
				arr[j] = arr[j-1];
				arr[j-1] = temp;
			}
		}
	}//end rotate
	
	
	public void finalJeopardy(){
		System.out.println("Now that all questions have been answered, it's time "
				+ "for Final Jeopardy!");
		System.out.println("Only teams that have a positive dollar value can participate.");
		for(int i = 0; i < teams.length; i++){
			placeBet(teams[i]);
		}
		System.out.println("The question is: ");
		System.out.println(FileCheck.finalQuestion[0]);
		writeAnswers();
		calculateFinalScore();
	}
	
	public void play(){
		//reset everything
		reset();
		
		//generate random number to pick which team goes first
		Random rand = new Random();
		int randomNum = rand.nextInt(numberOfTeams);
		
		System.out.println("The team to go first will be " + teams[randomNum].getName() + ".");
		rotate(teams, teams.length - randomNum);
		
		while(counter < 25){
			for(int i = 0; i < teams.length; i++){
				if(counter < 25){
					askQuestion(teams[i], false);
				}
			}
			printPoints();
		}
		
		finalJeopardy();
		System.out.print("finished all questions");
		//terminate program
		System.exit(0);
		scan.close();
	}//end play
}//end class
