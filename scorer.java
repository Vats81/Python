package project;
import java.util.*;
import java.math.*;
class player{
	int runs;
	int fours;
	int sixes;
	int balls;
	int overs;
	int wickets;
	double economy;
	static player addplayer(int runs,int four,int sixes,int balls,int ballsbowled,int wickets,double runsgiven) {
		player a = new player();
		a.runs = runs;
		a.fours = four;
		a.sixes = sixes;
		a.balls = balls;
		a.overs = ballsbowled;
		a.wickets = wickets;
		a.economy = runsgiven;
		return a;
	 }
	 static player addruns(int runs,int four,int sixes,int balls,String bat) {
		 player a = new player();
		 a.runs += runs;
		 a.fours += four;
		 a.sixes += sixes;
		 a.balls += balls;
		 return a;
	 }
	 static player addwickets(int balls,int wickets,int economy,String bowl) {
		 player a = new player();
		 a.overs += balls;
		 a.wickets += wickets;
		 a.economy += economy;
		 return a;
	 }
}
public class scorer extends player{
	
	public static void main(String[] args)
	{		
		player p = new player();
		Scanner sc = new Scanner(System.in);
        double a = Math.random()*(9-1 + 1)+1;
        System.out.println("Toss Time.....M make a choice");
        String toss = sc.next();
        boolean A= false;
        teams t = new teams();
        if(toss == "head" && a <5)
        {
        	System.out.println("Team A won");
        	A =true;
        }else {
        	System.out.println("Team A lost");
        }
        System.out.println("what do you want to do");
        String option = sc.next();
        if(A)
        {
        	System.out.println("team A decides to " + option);
        	System.out.println("team A");
        	t.getteamA();
        	System.out.println("----------------");
        	System.out.println("team B");
        	t.getteamB();
        }else {
        	System.out.println("team B decides to " + option);
        	System.out.println("team B");
        	t.getteamB();
        	System.out.println("----------------");
        	System.out.println("team A");
        	t.getteamA();
        }
        System.out.println("----------------");
		if(A && option == "bat" || !A && option == "ball")
		{
			System.out.println("Innings of team A");
		}else 
		{
			System.out.println("Innings of Team B");
		
		}
		System.out.println("----------------");
		int innings =0;
		
		int target = Integer.MAX_VALUE;
		while(innings<2 )
		{
			HashMap<String,player > batsman = new HashMap<>();
			HashMap<String,player> bowler = new HashMap<>();
			int totalruns =0;
			int balls =0;
			System.out.println("Enter striker name");
			String striker = sc.next();
			System.out.println("Enter runner name");
			String runner = sc.next();
			batsman.put(striker,addplayer(0,0,0,0,0,0,0.0));
			batsman.put(runner,addplayer(0,0,0,0,0,0,0.0));
			String currentbatsman = striker;
			String other = runner;
			String currentbowler = "";
			int wicketsdown =0;
			while(balls<12 && wicketsdown<10 && totalruns< target )
			{
				if(balls%6 == 0)
				{
					System.out.println("Enter bowler name");
					String bowl = sc.next();
	                if(bowler.containsKey(bowl))	
	                {
	                	currentbowler = bowl;
	                }else {
	                	bowler.put(bowl, addplayer(0,0,0,0,0,0,0.0));
	                	currentbowler = bowl;
	                }
				}
				
				System.out.println("Enter choice"
						+ "1 for runs or dot"
						+ "2 for wicket"
						+ "3 for extra"
						+ "4 for abort");
				int op = sc.nextInt();
				switch(op)
				{
				case 1:
					System.out.println("enter runs");
					int runs = sc.nextInt();
					totalruns+=runs;
					balls+=1;
					if(runs == 4)
					{
					    batsman.put(currentbatsman,addruns(4,1,0,1,currentbatsman));
					   
					}
					else if(runs == 6)
					    batsman.put(currentbatsman,addruns(6,0,1,1,currentbatsman));
					else if(runs %2 == 0)
					{
						batsman.put(currentbatsman,addruns(runs,0,0,1,currentbatsman));
						
					}
					else {
						batsman.put(currentbatsman,addruns(runs,0,0,1,currentbatsman));
						String temp = currentbatsman;
						currentbatsman = other;
						other= temp;
					}
					bowler.put(currentbowler, addwickets(1,0,runs,currentbowler));
					if(totalruns > target)
					{
						System.out.println("Team batting second won");
					}
					break;
				case 2:
					bowler.put(currentbowler, addwickets(1,1,0,currentbowler));
					wicketsdown+=1;
					if(wicketsdown ==10 && innings == 0)
					{
						
						System.out.println("final score team batting first" + target +'/'+ wicketsdown);
						break;
					}
					if(wicketsdown ==10 && innings == 1)
					{
						System.out.println("Team batting first won");
					}
					System.out.println("Enter batsman name");
					String newb = sc.next();
					batsman.put(newb,addplayer(0,0,0,0,0,0,0.0));
					currentbatsman = newb;
					balls+=1;
					
					break;
				case 3:
					System.out.println("Enter extras");
					int extra = sc.nextInt();
					totalruns+= extra;
					break;
				case 4:
					break;
				default:
					System.out.println("Enter agin");
					break;
				}
			}
			if(totalruns> target)
			{
				System.out.println("team batting second won");
				break;
			}
			innings+=1;
			if(innings ==1)
			{
				target = totalruns;
				totalruns=0;
				System.out.println("final score team batting first" + target +'/'+ wicketsdown);
				target+=1;
				System.out.println("The target is" + target);
				wicketsdown =0;
			}
			if(innings==2)
			{
				System.out.println("Team batting first won");
			}
			
			
		}
	}

}

class teams{
	private String[] teamA = {"Virat","Rohit","Hardik","Yuzi","bhuvi","bumrah","JAddu","rahul","Rishabh","shami","Sky"};	
	private String[] teamB = {"Warner","Smith","Finch","starc","pat","josh","lyon","maxi","head","stoinis","zampa"};	
	public void setTeamA(int index,String value)
	{
		teamA[index] = value;
	}
	public void setTeamB(int index,String value)
	{
		teamB[index] = value;
	}
	public void getteamA()
	{
		for(int i=0;i<11;i++)
		{
			System.out.println(teamA[i]);
		}
	}
	public void getteamB()
	{
		for(int i=0;i<11;i++)
		{
			System.out.println(teamB[i]);
		}
	}
}