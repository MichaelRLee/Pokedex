/**
 * Michael Lee
 * May 9, 2014
 *
 * typeInfo:	gets information for each type
 pokeInfo:	gets the Pokémon’s basic info
 getPokedex:	gets the pokédex entries
 theStats:	gets the Pokémon’s base stats
 getPokemon:	gets the list of Pokémon


 */

package com.gmail.mikerlee96.main;
import java.io.*;
import java.util.*;

public class Reader {

	public double [] [] typeInfo (String type){//returns pokemon information based on type
		double [] [] typeInfo=new double [18][2];//type information
		File file = new File ("Types" + File.separator +type+".pkmn");//gets type's file
		try {//gets info from file
			Scanner  scan = new Scanner( file );
			for (int i = 0; i<2; i++){
				for (int j = 0; j<18; j++){
					typeInfo [j][i]=scan.nextDouble();
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {//returns a default type if specified type isn't found
			System.out.println("Type not found.  Changing to \"Colourless\"");
			System.out.println("Error at \""+"Types" + File.separator +type+".pkmn\"");
			for (int i = 0; i<2; i++){
				for (int j = 0; j<18; j++){
					typeInfo [j][i]=1;
				}
			}
		}
		return typeInfo;//returns the type's info
	}

	public String [] pokeInfo (String pokemon){//gets the pokemon's info
		String theInfo[];
		File file = new File ("Pokemon"+ File.separator+pokemon+File.separator+pokemon+".pkmn");//file location
		try {//tries to get the pokemon's info
			Scanner scan = new Scanner (file);
			theInfo=new String[scan.nextInt()];
			for (int i = -1; i<theInfo.length;i++){
				if (i > -1) theInfo[i]=scan.nextLine();
				else theInfo[0]=scan.nextLine();
			}
			scan.close();
		} catch (FileNotFoundException e){//if file isn't found, return a default
			theInfo=new String[9];
			System.out.println("File not found");
			for (int i =0; i<9; i++){
				theInfo[i]="DATA ERROR";
			}
		} catch (NoSuchElementException e){//if file is formatted incorrect, return a default
			theInfo=new String[9];
			System.out.println("FILE FORMATTING ERROR(POKEMON)");
			for (int i =0; i<9; i++){
				theInfo[i]="DATA ERROR";
			}
		}
		return theInfo;//return info
	}

	public String [] getPokedex (String pokemon){//gets pokedex entries
		String [] entries=new String [21];
		File file;
		//mr.mime's name is wield
		if (pokemon.equals("Mr.Mime"))file = new File("Pokemon"+File.separator+"Mr_Mime"+File.separator+"Pokedex.pkmn");
		else file = new File("Pokemon"+File.separator+pokemon+File.separator+"Pokedex.pkmn");
		try{//gets pokemon's entry
			Scanner scan = new Scanner (file);
			for (int i = 0; i<entries.length; i++){
				entries[i]=scan.nextLine();
			}
			scan.close();
		}catch (FileNotFoundException e){//entries are errors if file isn't found
			System.out.println("Pokedex File Not Found");
			System.out.println("Error at: "+file.toString());
			for (int i = 0; i<entries.length; i++){
				entries[i]="File Error";
			}
		}catch (NoSuchElementException e){//entries are errors if the file is formatted incorrectly
			System.out.println("File Format Error (Pokedex)");
			for (int i = 0; i<entries.length; i++) {
				entries[i] = "File Error";
			}
		}
		return entries;
	}

	public int [] theStats (String pokemon){//gets the pokemon's stats
		int [] stats = new int [6];
		File file = new File("Pokemon"+File.separator+pokemon+File.separator+"Stats.pkmn");
		try {
			Scanner scan = new Scanner (file);
			for (int i = 0; i < 6; i++) {
				stats [i]=scan.nextInt();
			}
		}catch (FileNotFoundException e){//error if file isn't found, returns zeros
			System.out.println("Congratulations! Your "+pokemon+" is now $h|t");
			for (int i=0; i<6; i++){
				stats[i]=0;
			}
		}catch (NoSuchElementException e){//error if stats are formatted incorrectly. returns zeros
			System.out.println("FILE FORMATTING ERROR (STATS)");
			System.out.println("ERROR AT: "+pokemon);
			for (int i=0; i<6; i++){
				stats[i]=0;
			}
		}
		return stats;
	}

	public Pokemon [] getPokemon() {//gets list of pokemon
		File file = new File ("Pokemon"+ File.separator+"Pokemon.txt");
		String name;
		Pokemon [] list={new Pokemon ("MissingNo")};//initializes list
		boolean hasPokemon = true;
		int num = 0;
		try {//gets number of pokemon
			Scanner scan = new Scanner (file);
			while (hasPokemon){
				if (!scan.hasNext()) hasPokemon = false;
				else {
					scan.next();
					num ++;
				}
			}
			scan.close();
			scan = new Scanner (file);
			list = new Pokemon [num];//list is re-initialized
			for (int i = 0; i<num; i++){
				name=scan.next();
				list [i] = new Pokemon (name,i);//creates new pokemon to add to list
			}
			scan.close();
		} catch (FileNotFoundException e){//if the list isn't found
			System.out.println("Cannot find Pokemon (Importer)");
		}
		return list;
	}

}
