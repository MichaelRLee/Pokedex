/**
 * Michael Lee
 * April 25, 2014
 */

package com.gmail.mikerlee96.main;
import java.awt.Color;

public class Type {
	private double [] [] effective = new double [18] [2];//type damage info
	private String [] numType = new String [18];//type categorized numerically
	private String typing;//what type the class takes on
	private Color typeColour;//associated type colour
	private Color [] typeColours = new Color [19];//all type colours
	
	public Type (String typing){//initializes types
		this.typing=typing;
		Reader read = new Reader ();
		numType [0]="Normal";
		numType [1]="Fire";
		numType [2]="Water";
		numType [3]="Electric";
		numType [4]="Grass";
		numType [5]="Ice";
		numType [6]="Fighting";
		numType [7]="Poison";
		numType [8]="Ground";
		numType [9]="Flying";
		numType [10]="Psychic";
		numType [11]="Bug";
		numType [12]="Rock";
		numType [13]="Ghost";
		numType [14]="Dragon";
		numType [15]="Dark";
		numType [16]="Steel";
		numType [17]="Fairy";
		effective=read.typeInfo(typing);//effectiveness
		setColour();//sets colours
	}

	public double weakness(int i){return this.effective[i][1];}//returns types defensive multipliers
	
	public String whatType (int i){return this.numType [i];}//returns specified type's name
	
	public String pkmnType (){return typing;}//returns current type

	private void setColour (){//sets types colours
		typeColours[0] = new Color (168, 168, 120);
		typeColours[1] = new Color (240, 128, 48);
		typeColours[2] = new Color (104, 144, 240);
		typeColours[3] = new Color (248, 208, 48);
		typeColours[4] = new Color (120, 200, 80);
		typeColours[5] = new Color (152, 216, 216);
		typeColours[6] = new Color (192, 48, 40);
		typeColours[7] = new Color (160, 64, 160);
		typeColours[8] = new Color (224, 192, 104);
		typeColours[9] = new Color (168, 144, 240);
		typeColours[10] = new Color (248, 88, 136);
		typeColours[11] = new Color (168, 184, 32);
		typeColours[12] = new Color (184, 160, 56);
		typeColours[13] = new Color (112, 88, 152);
		typeColours[14] = new Color (112, 56, 248);
		typeColours[15] = new Color (112, 88, 72);
		typeColours[16] = new Color (184, 184, 208);
		typeColours[17] = new Color (238, 152, 172);
		typeColours[18] = new Color (104,160,144);
		typeColour = typeColours[18];//sets default types colours
		for (int i = 0; i<18;i++){//
			if (typing.equals(numType[i])){
				typeColour=typeColours[i];//sets types actual colour
			}
		}
	}

	public Color getTypeColour (){return typeColour;}//returns type's colour

	public Color colourOf (String typeName){//returns specified type's colour
		for (int i = 0; i<18; i++){
			System.out.println();
			if (typeName.equals(numType[i])){
				return typeColours[i];
			}
		}
		return typeColours[18];//if no match is found, return default
	}
}