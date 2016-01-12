/**
 * Michael Lee
 * June 4, 2014
 *
 * createLabels:		creates the labels and sets text appearance
 bottomSpacer:		creates a filler to add to the bottom of the panel
 */

package com.gmail.mikerlee96.gui;

import javax.swing.*;

import com.gmail.mikerlee96.main.Pokemon;
import java.awt.*;
import java.io.File;

public class PokeSideBar extends JPanel{
	private ImageIcon picture;//icons
	private JLabel [][] information = new JLabel[10][2];//labels
	private boolean hasType2 = false;//has a second type
	private boolean hasAbility2 = false;//has a second ability
	private boolean hasHAbility = false;//has a hidden ability

	public PokeSideBar(Pokemon selectedPoke){
		setBackground(new Color(224, 224, 224));
		picture = new ImageIcon("Resources"+ File.separator+"Image"+File.separator+selectedPoke.info("Number")+
				" (1).gif"); //animated gif of he pokemon
		createLabels(selectedPoke);//creates the pokemon's labels

		JPanel[] layout = new JPanel[20];//initializes and sets the look of the panels
		for (int i = 0; i< layout.length; i++){
			layout[i] = new JPanel();
			layout[i].setAlignmentY(Component.CENTER_ALIGNMENT);
			layout[i].setBackground(new Color(224,224,224));
			layout[i].setLayout(new FlowLayout());
		}

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//layout

		//Pokemon Picture
		layout[0].setPreferredSize(new Dimension(150, 150));
		layout[0].setLayout(new GridLayout(1,1));
		layout[0].add(information[0][0]);
		add(layout[0]);

		add (new JSeparator(SwingConstants.HORIZONTAL));

		//sets look of types
		layout[1].setLayout(new BoxLayout(layout[1],BoxLayout.Y_AXIS));
		layout[1].add(information[8][0]);
		if (hasType2){
			layout[2].setLayout(new BoxLayout(layout[2], BoxLayout.X_AXIS));
			layout[3].setMaximumSize(new Dimension(75, 25));
			layout[3].setBackground(selectedPoke.getTypeColour());
			layout[4].setMaximumSize(new Dimension(75, 25));
			layout[4].setBackground(selectedPoke.getType2Colour());
			layout[3].add(information[1][0]);
			layout[4].add(information[2][0]);
			layout[2].add(layout[3]);
			layout[2].add(layout[4]);
		}else {
			layout[2].setMaximumSize(new Dimension(75, 25));
			layout[2].setBackground(selectedPoke.getTypeColour());
			layout[2].add(information[1][0]);
		}
		layout[1].add(layout[2]);
		add(layout[1]);

		add (new JSeparator(SwingConstants.HORIZONTAL));

		//sets size of panels
		layout[5].setLayout(new BoxLayout(layout[5], BoxLayout.Y_AXIS));
		layout[5].setMaximumSize(new Dimension(100,50));
		layout[6].setLayout(new BoxLayout(layout[6], BoxLayout.Y_AXIS));
		layout[6].setMaximumSize(new Dimension(100,50));
		layout[7].setLayout(new BoxLayout(layout[7], BoxLayout.X_AXIS));
		layout[5].add(information[3][1]);
		layout[5].add(information[3][0]);
		layout[6].add(information[4][1]);
		layout[6].add(information[4][0]);
		layout[7].add(layout[5]);
		layout[7].add(layout[6]);
		add(layout[7]);

		add (new JSeparator(SwingConstants.HORIZONTAL));

		//sets look of how abilities is displayed
		layout[8].setLayout(new BoxLayout(layout[8], BoxLayout.Y_AXIS));
		layout[8].add(information[8][1]);
		if (!hasAbility2 && !hasHAbility){//one ability
			layout[9].add (information[5][0]);
			layout[9].setMaximumSize(new Dimension(200,50));
			layout[8].add(layout[9]);
		}else if (!hasAbility2){//one ability and a hidden ability
			layout[9].add(information[5][0]);
			layout[9].setMaximumSize(new Dimension(200, 50));
			layout[10].setLayout(new BoxLayout(layout[10], BoxLayout.X_AXIS));
			layout[11].add(information[7][1]);
			layout[11].setMaximumSize(new Dimension(100, 50));
			layout[12].setLayout(new BorderLayout(1,1));
			layout[12].add(information[7][0]);
			layout[12].setMaximumSize(new Dimension(100, 50));
			layout[10].add(layout[11]);
			layout[10].add(layout[12]);
			layout[8].add(layout[9]);
			layout[8].add(layout[10]);
		}else{//all abilities
			layout[9].setLayout(new BoxLayout(layout[9],BoxLayout.X_AXIS));
			layout[10].setLayout(new BoxLayout(layout[10], BoxLayout.X_AXIS));
			layout[11].setLayout(new BoxLayout(layout[11], BoxLayout.Y_AXIS));
			layout[12].setLayout(new GridLayout(1,1));
			layout[12].add(information[5][0]);
			layout[12].setMaximumSize(new Dimension(100, 50));
			layout[13].setLayout(new GridLayout(1, 1));
			layout[13].add(information[6][0]);
			layout[13].setMaximumSize(new Dimension(100, 50));
			layout[14].add(information[7][1]);
			layout[14].setMaximumSize(new Dimension(100, 50));
			layout[15].setLayout(new GridLayout(1,1));
			layout[15].add(information[7][0]);
			layout[15].setMaximumSize(new Dimension(100, 50));
			layout[9].add(layout[12]);
			layout[9].add(layout[13]);
			layout[10].add(layout[14]);
			layout[10].add(layout[15]);
			layout[11].add(layout[9]);
			layout[11].add(layout[10]);
			layout[8].add(layout[11]);
		}
		add(layout[8]);
		add(new JSeparator(SwingConstants.HORIZONTAL));
		add(bottomSpacer());
	}

	//labels for pokemon elements
	private void createLabels (Pokemon selectedPoke){
		String [] infoName = new String [information.length];
		hasType2=selectedPoke.hasElement("Type 2");
		hasAbility2 = selectedPoke.hasElement("Ability 2");
		hasHAbility= selectedPoke.hasElement("Hidden Ability");
		infoName[1] = "Type 1";
		infoName[2] = "Type 2";
		infoName[3] = "Height";
		infoName[4] = "Weight";
		infoName[5] = "Ability 1";
		infoName[6] = "Ability 2";
		infoName[7] = "Hidden Ability";
		information [0][0] = new JLabel(picture, JLabel.CENTER);
		information[0][0].setAlignmentX(Component.CENTER_ALIGNMENT);
		information[0][0].setAlignmentY(Component.CENTER_ALIGNMENT);

		//sets the look for the different lables
		for (int i =1; i<7; i++) {
			information[i][0] = new JLabel("<html><div style=\"text-align: center;\">" +
					selectedPoke.info(infoName[i]) + "</html>", JLabel.CENTER);
			information[i][0].setAlignmentX(Component.CENTER_ALIGNMENT);
			information[i][1] = new JLabel("<html><div style=\"text-align: center;\"><b>"+
					infoName[i]+"</b></html>", JLabel.CENTER);
			information[i][1].setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		information[8][0]=new JLabel("<html><div style=\"text-align: center;\"><b>Type</b>" +
				"</html>", JLabel.CENTER);
		information[8][0].setAlignmentX(Component.CENTER_ALIGNMENT);

		information[8][1]=new JLabel("<html><div style=\"text-align: center;\"><b>Ability" +
				"</b></html>", JLabel.CENTER);
		information[8][1].setAlignmentX(Component.CENTER_ALIGNMENT);

		information[7][0] = new JLabel("<html><div style=\"text-align: center;\">" +
				selectedPoke.info(infoName[7]) + "</html>", JLabel.CENTER);
		information[7][0].setAlignmentX(Component.CENTER_ALIGNMENT);
		information[7][0].setAlignmentY(Component.BOTTOM_ALIGNMENT);

		information[7][1]=new JLabel("<html><div style=\"text-align: center;\"><i>" +
				"\u00A0Hidden\u00A0<br\u00A0>Ability\u00A0</i></html>",JLabel.CENTER);
		information[7][1].setAlignmentX(Component.CENTER_ALIGNMENT);
	}

	//adds a spacer to the bottom
	private Box.Filler bottomSpacer () {
		Dimension minSize = new Dimension (0,10);
		Dimension prefSize = new Dimension(0,25);
		Dimension maxSize = new Dimension(0,Short.MAX_VALUE);
		return new Box.Filler(minSize, prefSize, maxSize);
	}

}
