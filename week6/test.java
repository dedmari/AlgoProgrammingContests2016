package week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class test {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		
		Set<Integer> validSegments0= new HashSet<Integer>();
		validSegments0.add(1);
		validSegments0.add(2);
		validSegments0.add(3);
		validSegments0.add(4);
		validSegments0.add(5);
		validSegments0.add(6);
		Set<Integer> validSegments1= new HashSet<Integer>();
		validSegments1.add(2);
		validSegments1.add(3);
		Set<Integer> validSegments2= new HashSet<Integer>();
		validSegments2.add(1);
		validSegments2.add(2);
		validSegments2.add(4);
		validSegments2.add(5);
		validSegments2.add(7);
		Set<Integer> validSegments3= new HashSet<Integer>();
		validSegments3.add(1);
		validSegments3.add(2);
		validSegments3.add(3);
		validSegments3.add(4);
		validSegments3.add(7);
		Set<Integer> validSegments4= new HashSet<Integer>();
		validSegments4.add(2);
		validSegments4.add(3);
		validSegments4.add(6);
		validSegments4.add(7);
		Set<Integer> validSegments5= new HashSet<Integer>();
		validSegments5.add(1);
		validSegments5.add(3);
		validSegments5.add(4);
		validSegments5.add(6);
		validSegments5.add(7);
		Set<Integer> validSegments6= new HashSet<Integer>();
		validSegments6.add(1);
		validSegments6.add(3);
		validSegments6.add(4);
		validSegments6.add(5);
		validSegments6.add(6);
		validSegments6.add(7);
		Set<Integer> validSegments7= new HashSet<Integer>();
		validSegments7.add(1);
		validSegments7.add(2);
		validSegments7.add(3);
		Set<Integer> validSegments8= new HashSet<Integer>();
		validSegments8.add(1);
		validSegments8.add(2);
		validSegments8.add(3);
		validSegments8.add(4);
		validSegments8.add(5);
		validSegments8.add(6);
		validSegments8.add(7);
		Set<Integer> validSegments9= new HashSet<Integer>();
		validSegments9.add(1);
		validSegments9.add(2);
		validSegments9.add(3);
		validSegments9.add(4);
		validSegments9.add(6);
		validSegments9.add(7);
		Integer[][]  matrix1 = {{0,8},{0,1,3,4,7,8,9},{2,8},{3,8,9},{4,8,9},{5,6,8,9},{6,8},{0,3,7,8,9},{8},{8,9}};
		validSegments0.removeAll(validSegments7);
		validSegments3.removeAll(validSegments7);
//		validSegments4.removeAll(validSegments1);
//		validSegments7.removeAll(validSegments1);
		validSegments8.removeAll(validSegments7);
		validSegments9.removeAll(validSegments7);
		System.out.println(""+validSegments0+validSegments3+validSegments8+validSegments9);
		

	}

}
