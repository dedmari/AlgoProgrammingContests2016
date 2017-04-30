package week12;
//code inspired from http://www.geeksforgeeks.org/longest-prefix-matching-a-trie-based-solution-in-java/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class TrieNode {
	char ch;
	HashMap<Character, TrieNode> child = new HashMap<Character, TrieNode>();
	boolean isLeaf;

	public TrieNode() {}

	public TrieNode(char ch){
		this.ch = ch;
	}
}

public class ContactList {
	private TrieNode root;
	public ContactList() {
		root = new TrieNode();
	}

	public void insert(String word) {
		HashMap<Character, TrieNode> child = root.child;
		for(int i=0; i<word.length(); i++){
			char c = word.charAt(i);
			TrieNode t;
			if(child.containsKey(c)){
				t = child.get(c);
			}else{
				t = new TrieNode(c);
				child.put(c, t);
			}
			child = t.child;
			if(i==word.length()-1)
				t.isLeaf = true;    
		}
	}

	public boolean searchNode(String str){
		Map<Character, TrieNode> child = root.child; 
		TrieNode t = null;
		for(int i=0; i<str.length(); i++){
			char c = str.charAt(i);
			if(child.containsKey(c)){
				t = child.get(c);
				child = t.child;
			}
		}
		return t.child.isEmpty();
	}
	public static void main(String args[]) throws Exception
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		int i,j,k;
		for(i=1;i<=t;i++)
		{
			ContactList st=new ContactList();
			int n=Integer.parseInt(br.readLine());
			String names[]=new String[n];
			for(j=0;j<n;j++)
			{
				names[j]=br.readLine();
				st.insert(names[j]);
			}
			int res=0;
			for(k=0;k<n;k++)
			{
				if(!st.searchNode(names[k]))
					res++;

			}
			System.out.println("Case #"+i+": "+res);
			br.readLine();
		}
	}
}
