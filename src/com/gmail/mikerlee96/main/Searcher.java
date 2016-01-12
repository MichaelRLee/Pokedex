/**
 * Michael Lee
 * June 15, 2014
 *
 * Results:	returns search results
 getPokemon:	get’s the list of Pokémon
 */

package com.gmail.mikerlee96.main;

import java.util.ArrayList;

public class Searcher {
	private Pokemon[] list;//list for pokemon
	private ArrayList <Pokemon> found = new ArrayList<Pokemon>();//array list of search results

	public Searcher (String searchBy, String term){//searches query by query type
		getPokemon();
		for (Pokemon aList : list) {//for each pokemon
			if (searchBy.equals("Name") || searchBy.equals("Species")) {//if query type is name or species
				if (aList.info(searchBy).toLowerCase().contains(term.toLowerCase())) {//if it contains the search term
					found.add(aList);
				}
			} else if (searchBy.equals("Number")) {//if it's by number, find that numbered pokemon
				try {
					if (Integer.parseInt(aList.info(searchBy)) == Integer.parseInt(term)) {
						found.add(aList);
					}
				}catch(NumberFormatException ignored){}//ignores non-numerical search
			} else if (searchBy.equals("Type")) {//if it's by type, search both types for a match
				if (aList.info("Type 1").toLowerCase().contains(term.toLowerCase()) ||
						aList.info("Type 2").toLowerCase().contains(term.toLowerCase())) {
					found.add(aList);
				}
			}
		}
	}

	public Pokemon [] results (){
		return found.toArray(new Pokemon [found.size()]);
	}//returns array

	private void getPokemon(){//gets pokemon list
		Reader read = new Reader();
		list=read.getPokemon();
	}
}
