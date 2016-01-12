/**
 * Michael Lee
 * June 4, 2014
 *
 * organize:	groups identical entries together
 isSame:	Checks if two (or more) pokédex entries are the same
 createLabels:	Creates the version headings for the pokédex entries
 disposeDuplicateLabels: Works with createLabels to group headings together
 createTextArea: Creates a  JTextArea for the pokédex entry
 addSeparator:	Adds a separator between the pokédex entries based on generations
 The next section are methods from the implementation of scrollable (javax.swing)
 getPreferredScrollableViewportSize	Returns the preferred size of the viewport for a view component
 getScrollableUnitIncrement		Components that display logical rows or columns should compute the scroll increment
								that will completely expose one new row or column, depending on the value of orientation
 getScrollableBlockIncrement	Components that display logical rows or columns should compute the scroll increment
 								that will completely expose one block of rows or columns, depending on the value of
 								orientation
 getScrollableTracksViewportWidth	If the width should resize rather than adding a scroll bar
 getScrollableTracksViewportHeight	If the height should resize rather than adding a scroll bar


 */
package com.gmail.mikerlee96.gui;

import javax.swing.*;
import com.gmail.mikerlee96.main.Pokemon;
import com.gmail.mikerlee96.main.Reader;
import java.awt.*;

public class DexEntries extends JPanel implements Scrollable{
	private JTextArea [] pokedexEntries;//text areas for the pokedex entries
	private JLabel [] versions;//labels for versions

	public DexEntries(Pokemon selected){
		JLabel title = new JLabel("Pokedex Entries");//top label
		setBorder(BorderFactory.createEmptyBorder(0, 1, 1, 1));
		organize(selected);//organizes the entries for the selected pokemon
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//layout
		title.setFont(new Font("SansSerif", Font.BOLD, 20));//font
		title.setAlignmentX(Component.LEFT_ALIGNMENT);//alignment
		add(title);//adds title
		add (new JSeparator(SwingConstants.HORIZONTAL));//separator
		for (int i = 0; i<versions.length; i++){//adds labels and text areas, and separators if needed
			versions[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			pokedexEntries[i].setAlignmentX(Component.LEFT_ALIGNMENT);
			add (versions[i]);
			add(pokedexEntries[i]);
			addSeparator(i);
		}
		setBackground(new Color(224, 224, 224));//background colour
	}

	private void organize (Pokemon selected) {//organizes entries (checks for duplicates
		int count = 21;
		boolean RS=false, RSE, FRLG, DP=false, DPPt,HGSS, BW=false, B2W2=false;//duplicates
		Reader pokedexEntry = new Reader();//reads from files
		String [] entries;//pokedex entries
		entries=pokedexEntry.getPokedex(selected.info("Name"));//gets pokedex entries based on pokemon name
		//checks if version have the same entry
		if (RSE=isSame(3,entries[5],entries[6],entries[7])) {
			count -= 2;
		}else if (RS=isSame(2, entries[5], entries[6],"null")){
			count--;
		}
		if (FRLG=isSame(2,entries[8],entries[9],"null")) {
			count--;
		}
		if (DPPt=isSame(3,entries[10],entries[11],entries[12])) {
			count -= 2;
		}else if (DP=isSame(2, entries[10], entries[11],"null")){
			count--;
		}
		if (HGSS=isSame(2,entries[13],entries[14],"null")) count--;
		if (entries[15].equals(entries[16])){
			BW=true;
			count --;
		}
		if (entries[17].equals(entries[18])){
			B2W2=true;
			count--;
		}
		pokedexEntries=new JTextArea[count];//initializes array
		versions=new JLabel[count];//initializes labels
		createLabels(RS, RSE, FRLG, DP, DPPt, HGSS, BW, B2W2,entries);//creates labels
	}

	private boolean isSame (int num, String entry1, String entry2, String entry3){//checks if entries are the same
		return entry1.equals(entry2) && entry2.equals(entry3) && num == 3 || entry1.equals(entry2);
	}

	//creates the labels
	private void createLabels (boolean RS, boolean RSE, boolean FRLG, boolean DP, boolean DPPt, boolean HGSS,
							   boolean BW, boolean B2W2, String [] entries){
		int count =5;
		//all of the following are always used
		versions[0]=new JLabel("<html><b>Red/Blue</b></html>");
		versions[1]=new JLabel("<html><b>Yellow</b></html>");
		versions[2]=new JLabel("<html><b>Gold</b></html>");
		versions[3]=new JLabel("<html><b>Silver</b></html>");
		versions[4]=new JLabel("<html><b>Crystal</b></html>");
		//creates the text fields that are used
		for (int i = 0; i<5; i++) createTextArea(entries[i], i);
		//merges enteries for duplicates
		count = disposeDuplicatesLabels("Ruby", "Sapphire", "Emerald", RS, RSE, count, entries);
		count = disposeDuplicatesLabels("Fire Red","Leaf Green", "N/A",FRLG,false,count,entries);
		count = disposeDuplicatesLabels("Diamond", "Pearl", "Platinum", DP, DPPt, count,entries);
		count = disposeDuplicatesLabels("Heart Gold","Soul Silver", "N/A", HGSS, false, count, entries);
		count = disposeDuplicatesLabels("Black", "White", "N/A", BW, false, count,entries);
		count = disposeDuplicatesLabels("Black 2", "White 2", "N/A", B2W2, false, count,entries);
		versions[count] = new JLabel("<html><b>X</b></html>");
		createTextArea(entries[count], count);
		count ++;
		versions[count] = new JLabel("<html><b>Y</b></html>");
		createTextArea(entries[count], count);
	}

	//merges duplicates
	private int disposeDuplicatesLabels(String version1, String version2, String version3, boolean check1,
									   boolean check2, int counter, String [] entries){
		int count = counter;
		//checks for duplicates based on input version and number of entries
		if (check1) {
			versions[count] = new JLabel("<html><b>"+version1+"/"+version2+"</b></html>");
			createTextArea(entries[count], count);
			count++;
			if (!version3.equals("N/A")) {
				versions[count] = new JLabel("<html><b>"+version3+"</b></html>");
				createTextArea(entries[count], count);
				count++;
			}
		}else if (check2 && !version3.equals("N/A")){
			versions[count] = new JLabel("<html><b>"+version1+"/"+version2+"/"+version3+"</b></html>");
			createTextArea(entries[count], count);
			count ++;
		}else {
			versions [count] = new JLabel("<html><b>"+version1+"</b></html>");
			createTextArea(entries[count], count);
			count ++;
			versions [count] = new JLabel("<html><b>"+version2+"</b></html>");
			createTextArea(entries[count], count);
			count ++;
			if (!version3.equals("N/A")) {
				versions[count] = new JLabel("<html><b>" + version3 + "</b></html>");
				createTextArea(entries[count], count);
				count++;
			}
		}
		return count;
	}

	//sets the look for each text field
	private void createTextArea (String text, int count){
		pokedexEntries[count]=new JTextArea(text);
		pokedexEntries[count].setFont(new Font("Serif", Font.PLAIN, 13));
		pokedexEntries[count].setLineWrap(true);
		pokedexEntries[count].setWrapStyleWord(true);
		pokedexEntries[count].setEditable(false);
		pokedexEntries[count].setBackground(new Color(240, 240, 240));
		pokedexEntries[count].setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	//separates entries by version
	private void addSeparator (int i){
		JPanel spacer = new JPanel();
		String [] lastGen = new String [8];
		lastGen [0]="<html><b>Yellow</b></html>";
		lastGen [1]="<html><b>Crystal</b></html>";
		lastGen [2]="<html><b>Leaf Green</b></html>";
		lastGen [3]="<html><b>Soul Silver</b></html>";
		lastGen [4]="<html><b>White 2</b></html>";
		lastGen [5]="<html><b>Fire Red/Leaf Green</b></html>";
		lastGen [6]="<html><b>Soul Silver/Heart Gold</b></html>";
		lastGen [7]="<html><b>Black 2/White 2</b></html>";
		spacer.add(new JSeparator(SwingConstants.HORIZONTAL));
		spacer.setBackground(new Color(224, 224, 224));
		for (String aLastGen : lastGen) {
			if (versions[i].getText().equals(aLastGen)) {
				add(spacer);
				add(new JSeparator(SwingConstants.HORIZONTAL));
			} else if (i == versions.length - 1) {
				add(spacer);
			}
		}
	}

	//the following are from the implementation of scrollable, but only getScrollableTracksViewportWidth is used
	public Dimension getPreferredScrollableViewportSize() {
		return null;
	}

	public int getScrollableUnitIncrement(Rectangle rectangle, int i, int i2) {
		return 0;
	}

	public int getScrollableBlockIncrement(Rectangle rectangle, int i, int i2) {
		return 0;
	}

	//resizes component instead of adding a scroll bar
	public boolean getScrollableTracksViewportWidth() {
		return true;
	}

	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

}
