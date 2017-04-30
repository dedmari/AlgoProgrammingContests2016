package week4;

import java.util.*;
import java.lang.*;
import java.io.*;

class SnakeLadder
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		int n = 10;
		int[] moves = new int[n];
		for(int i=0;i<n;i++)
			moves[i] = -1;
		//		//ladder
		//moves[6] = 0;
		//		moves[4] = 7;
		//		moves[10] = 25;
		//		moves[19] = 28;
		//		
		//		//snake
		//moves[2] = 3;
		//		moves[20] = 8;
		//		moves[16] = 3;
		//		moves[18] = 6;

		System.out.println("the minimum throws is "+ getmindicethrow(moves,n));
	}

	public static int getmindicethrow(int[] moves, int n){
		for(int k=6;k<=6;k++){
			boolean[] visited = new boolean[n];
			for(int i=0;i<n;i++)
				visited[i] = false;
			Queue<queueEntry> q = new LinkedList<queueEntry>();
			visited[0] = true;
			queueEntry s = new queueEntry();
			s.v = 0;
			s.dist = 0;
			q.offer(s);
			queueEntry qe = new queueEntry();
			int j=k;
			while(!q.isEmpty()){
				qe = q.peek();
				int v = qe.v;
				if(v==n-1)
					break;
				q.poll();
				if(v+k<n){
					j=v+k;
				}
				else{
					j=n-1;
				}
				//if(!visited[j]){
					queueEntry a = new queueEntry();
					a.dist = qe.dist+1;
					//visited[j] = true;
					if(moves[j] == -1)
						a.v = j;
					else
						a.v = moves[j];
					q.offer(a);
				//}
			}
			System.out.println(qe.dist);
			//return qe.dist;
		}
		return 199;
	}
}

//class queueEntry{
//	int v;
//	int dist;
//}
