package com.gmail.mikerlee96.other;

import com.gmail.mikerlee96.main.Pokemon;
import com.gmail.mikerlee96.main.Type;

public class Test {
	public static void main (String [] args){
		Pokemon tester = new Pokemon("Bulbasaur");
		Type t = new Type("Colourless");
		for (int i = 0; i<18; i++){
			System.out.print(t.whatType(i)+" ");
			System.out.println(tester.type1.weakness(i)*tester.type2.weakness(i)<1 && tester.type1.weakness(i)*tester.type2.weakness(i)>0);

		}
	}
	
}
