package mayaravi_CSCI201L_Assignment1;

public class Team {
	/***team variables***/
	protected String name;
	protected int totalPoints;
	protected int bet;
	protected String answer;
	
	public Team(){
		
	}
	
	public Team(String n){
		name = n;
		totalPoints = 0;
		bet = 0;
		answer = "";
	}
	
	public void setAnswer(String ans){
		answer = ans;
	}
	
	public String getAnswer(){
		return answer;
	}
	
	public void setBet(int num){
		bet = num;
	}
	
	public int getBet(){
		return bet;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public int getPoints(){
		return totalPoints;
	}
	
	public void setPoints(int num){
		totalPoints = num;
	}
	
	public void addPoints(int value){
		totalPoints = totalPoints + value;
	}
	
	public void subtractPoints(int value){
		totalPoints = totalPoints - value;
	}
}
