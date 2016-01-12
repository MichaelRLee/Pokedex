package com.gmail.mikerlee96.other;
import java.io.*;
import java.util.*;

public class Lazy {
	public static void main (String [] args) throws IOException, InterruptedException {

		/**new pokedex*/
		/*
		File pokedex, pokemon;
		Scanner in = new Scanner(System.in);
		Scanner open;
		String text="", name;
		PrintWriter print;
		boolean blank = true;

		pokemon = new File("Pokemon" + File.separator + "Pokemon.txt");
		open = new Scanner(pokemon);
		for (int n = 0; n<50; n++) {
			name = open.next();
			pokedex = new File("Pokemon" + File.separator + name + File.separator + "pokedex.pkmn");
			print = new PrintWriter(pokedex);
			System.out.println(name);
			//print.print("");
			System.out.println(pokedex.getPath());
			try {
				pokedex.delete();
			}catch (Exception e){
				System.out.println("nope");
			}
			print.close();
			//Thread.sleep(1000);
		}*/


		/**new Pokemon*/
/*
		String fileName;
		File folder, file;
		Scanner scan= new Scanner(System.in);
	    PrintStream  print;
	    String pokemon;

		System.out.println("Are you sure?");
		scan.next();
		
		fileName = "Pokemon"+File.separator+"Lazy.txt";
		file = new File (fileName);
		try {
			scan = new Scanner (file);
			while (scan.hasNext()){
				pokemon = scan.next();
				folder = new File ("Pokemon"+File.separator+pokemon);
				folder.mkdir();
				print=new PrintStream(new File ("Pokemon/"+pokemon+"/"+pokemon+".pkmn"));
				Thread.sleep(166);
				print=new PrintStream(new File ("Pokemon/"+pokemon+"/Stats.pkmn"));
				Thread.sleep(166);
				print=new PrintStream(new File ("Pokemon/"+pokemon+"/Pokedex.pkmn"));
				Thread.sleep(166);
				System.out.println(pokemon);
				print.close();
				Thread.sleep(500);
			}
			scan.close();
		}catch (FileNotFoundException e){
			System.out.println("bad");
		}
		System.out.println("Done!");
*/


		/**serebii sprites*/

				int looks;
		String zeros="00";
		
		for (int i=61; i<=151; i++){
			looks = (int) (Math.log10(i));
			//System.out.println(looks);
			System.out.print("<a href=\"");
			System.out.print ("http://www.serebii.net/pokedex-xy/icon/"+(zeros.substring(looks)+Integer.toString(i))+".png");
			System.out.println ("\">"+(zeros.substring(looks)+Integer.toString(i))+"</a>  <br>");
		}

	}
}