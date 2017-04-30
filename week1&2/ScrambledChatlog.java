import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ScrambledChatlog {

	public static void main(String[] args) {
		int t;
		int i= 0;
		String input=null;
		ArrayList<String> mesgs = new ArrayList<String>();
		String[] parts = new String[2];
		int x=0;
		String first = null;
		String second =null;
		try {
			   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			   t= Integer.parseInt(br.readLine());
			   for(int j=1;(j<=t && j<=200000);j++){
				   input  = br.readLine();
				   parts = input.split("#");
				   if(parts[1].length() >=2 && parts[1].length() <=160){
					   x = Integer.parseInt(parts[0]);
					   if(x >=1 && x < parts[1].length()){
						   if(parts[1].matches("[a-zA-Z\\s\\.\\,\\?\\!\\*\\;\\:\\-\\/\\(\\)\\[\\]]+")){
							   mesgs.add(input);   
						   } 
					   } 
				   }
				   input = null;
			   }
			   for(String mes : mesgs){
				   i++;
				   parts = mes.split("#");
				   x = Integer.parseInt(parts[0]);
				   first = parts[1].substring(0, x);
				   second = parts[1].substring(x);
				   System.out.println("Case #"+i+": "+second+first);
			   }
			 } catch (Exception e) {
			    System.err.println("Error: " + e);
			 }
	}

}
