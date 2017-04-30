import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class HelloWorld {
	public static void main(String[] args) {
		int t;
		int i= 0;
		String input=null;
		ArrayList<String> names = new ArrayList<String>();
		try {
			   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			   t= Integer.parseInt(br.readLine());
			   for(int j=1;(j<=t && j<=20);j++){
				   input  = br.readLine();
				   if(input.length() >=1 && input.length() <=100)
					   names.add(input);  
			   }
			   for(String name : names){
				   i++;
				   System.out.println("Case #"+i+": Hello "+name+"!");
			   }
			 } catch (Exception e) {
			    System.err.println("Error: " + e);
			 }
	}

}
