/**
 * Michael Lee
 * April 25, 2014
 *
 * ability:		returns the formatted ability
 */

package com.gmail.mikerlee96.main;

public class Ability {
	private String name;

	public Ability (){
		name = "none";
	}

	public Ability (String name) {//if the string contains a space, add an html break
		String construct ="";
		for (int i = 0; i < name.length(); i++) {
			if (Character.toString(name.charAt(i)).equals(" ")) {
				construct += "<br>";
			} else{
				construct += name.charAt(i);
			}
		}
		this.name = construct;
	}

	public String ability (){//returns formatted name
		return name;
	}
}
