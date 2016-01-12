/**
 * Michael Lee
 * April 25, 2014
 *
 * displayName:	returns formatted name
 init:		initializes all of the Pokémon’s fields
 info:		returns specified information about the Pokémon
 hasElement:	returns if a Pokémon has a specified element
 weaknesses:	returns a Pokémon’s weaknesses
 resistance:	returns a Pokémon’s resistances
 immune:	returns a Pokémon’s immunities
 getTypeColour: returns  a colour based on the Pokémon’s primary type
 getType2Colour: returns  a colour based on the Pokémon’s primary type
 replaceSymbol: formats a Pokémon’s name when the name contains some oddity
 gettStats:	returns a Pokémon’s base stats


 */
package com.gmail.mikerlee96.main;
import java.awt.Color;
import java.util.ArrayList;

public class Pokemon {
	private int number;//pokemon's number
	private String name, species, height, weight, nameFix;//pokemon's name, species, height, weight, and formatted name
	public Type type1, type2;//pokemon's types
	private Ability ability1, ability2=null, hAbility=null;//pokemon's abilities
	private int [] stats;//pokemon's stats

	public Pokemon (String name){//sets name
		this.name=name;
		init();
	}

	public Pokemon (String name, int i){//sets name and overwrites number
		this.name=name;
		init();
		this.number=i+1;
	}

	public String displayName (){//returns formatted name
		return nameFix;
	}

	private void init (){//initializes information
		Reader get = new Reader();//gets info from file
		String [] info=get.pokeInfo(this.name);//array of information
		stats = get.theStats(this.name);//gets stats
		replaceSymbol();//formats text
		try{//tries to parse pokemon's number
			this.number=Integer.parseInt(info[0]);
		}catch(NumberFormatException e){//if file was formatted incorrectly
			this.number=0;
		}
		//sets the rest of the information
		this.species=info[1];
		this.type1= new Type (info[2]);
		this.type2= new Type (info[3]);
		this.height=info[4];
		this.weight=info[5];
		ability1 = new Ability(info[6]);
		if (info.length==8){//sts the information based on how many abilities the pokemon has
			hAbility = new Ability (info [7]);
			ability2 = new Ability();
		}else if (info.length!=7){
			ability2 = new Ability (info [7]);
			hAbility = new Ability (info [8]);
		}else{
			ability2 = new Ability();
			hAbility = new Ability();
		}
	}

	public String info (String whatInfo){//returns information based on input
		int looks;//used for look of number
		//formats look of pokemon number
		String zeros="00";
		if (whatInfo.equals("Name")){
			return name;
		}else if (whatInfo.equals("Number")){
			if(number!=0) {
				looks = (int) (Math.log10(number));
			}else{
				looks=0;
			}
			return zeros.substring(looks)+Integer.toString(number);
		}else if (whatInfo.equals("Species")){
			return species+" Pokemon";
		} else if (whatInfo.equals("Type 1")){
			return type1.pkmnType();
		} else if (whatInfo.equals ("Type 2")){
			return type2.pkmnType();
		} else if (whatInfo.equals("Height")){
			return height;
		} else if (whatInfo.equals("Weight")){
			return weight;
		} else if (whatInfo.equals("Ability 1")){
			return ability1.ability();
		} else if (whatInfo.equals("Ability 2")){
			return ability2.ability();
		} else if (whatInfo.equals("Hidden Ability")){
			return hAbility.ability();

		}
		else return "Missing File";//if specified information wasn't there
	}

	public boolean hasElement (String what){//returns if the pokemon has an element
		if (what.equals("Ability 2")){//if it has a second ability
			return !ability2.ability().equals("none");
		}else if (what.equals("Hidden Ability")){//if it has a hidden ability
			return !hAbility.ability().equals("none");
		}else{//if it has a second type
			return !type2.pkmnType().equals("Colourless");
		}
	}

	public String [][] weaknesses (){
		ArrayList <String> types = new ArrayList<String>();//weaknesses
		ArrayList <String> mult = new ArrayList<String>();//how many more times damage the attack will do
		String [][] weak;//weaknesses
		for (int i = 0; i<18;i++){//goes through the types and sees if the pokemon's typing is weak to it
			if (type1.weakness(i)*type2.weakness(i)>1) {
				types.add(type1.whatType(i));
				mult.add(Math.round(type1.weakness(i)*type2.weakness(i))+"\u00D7");//multiplier plus the × sign
			}
		}
		weak = new String[types.size()][2];//initializes array
		for (int i =0;i<types.size();i++){//move info from arrayLists to 2d array
			weak[i][0]=types.get(i);
			weak[i][1]=mult.get(i);
		}
		return weak;
	}

	//same as above but for weaknesses
	public String [][] resistance (){
		ArrayList <String> types = new ArrayList<String>();
		ArrayList <String> mult = new ArrayList<String>();
		String [][] resist;
		for (int i = 0; i<18;i++){
			if (type1.weakness(i)*type2.weakness(i)<1 && type1.weakness(i)*type2.weakness(i)>0){
				types.add(type1.whatType(i));
				mult.add(Double.toString(type1.weakness(i)*type2.weakness(i)));
			}
		}
		resist = new String[types.size()][2];
		for (int i =0;i<types.size();i++){
			resist[i][0]=types.get(i);
			resist[i][1]=mult.get(i);
		}
		return resist;
	}

	public String [][] immune (){//same as above but for immunities
		ArrayList <String> types = new ArrayList<String>();
		ArrayList <String> mult = new ArrayList<String>();
		String [][] nope;
		for (int i = 0; i<18;i++){
			if (type1.weakness(i)*type2.weakness(i)==0){
				types.add(type1.whatType(i));
				mult.add(Double.toString(type1.weakness(i)*type2.weakness(i)));
			}
		}
		nope = new String[types.size()][2];
		for (int i =0;i<types.size();i++){
			nope[i][0]=types.get(i);
			nope[i][1]=mult.get(i);
		}
		return nope;
	}

	public Color getTypeColour(){//returns type 1's associeated type colour
		return type1.getTypeColour();
	}
	public Color getType2Colour() { return  type2.getTypeColour();}//returns type 2's associated type colour

	public void replaceSymbol () {//formats text to display the correct symbol
		String fixed = name;
		if (name.contains("[m]")) {
			fixed = name.substring(0, name.length() - 3);
			fixed += "\u2642";
		} else if (name.contains("[f]")) {
			fixed = name.substring(0, name.length() - 3);
			fixed += "\u2640";
		}else if (name.contains("_")){
			fixed = name.substring(0, 2);
			fixed += "."+name.substring(3,name.length());
			name=fixed;
		}
		nameFix= fixed;
	}

	public int [] getStats (){//returns the pokemon's stats
		return stats;
	}

}
