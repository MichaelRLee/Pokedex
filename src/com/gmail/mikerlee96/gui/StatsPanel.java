/**
 * Michael Lee
 * June 10, 2014
 *
 * setStatColour:		sets the foreground and background for the base stat
 createLayout:		sets the layout of the elements
 */

package com.gmail.mikerlee96.gui;

import com.gmail.mikerlee96.main.Pokemon;
import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel{
	private JLabel [][] statLabel = new JLabel[6][3];//labels for stats
	private JPanel [] statBars  = new JPanel[6];//panel for each base stat
	private GroupLayout [] layout= new GroupLayout[6];//layout for each stat's panel

	public StatsPanel(Pokemon pokemon) {
		GroupLayout gl = new GroupLayout(this);//layout
		Effective damageMult = new Effective(pokemon);//adds effective panel
		setLayout(gl);
		setBackground(new Color(224,224,224));
		int[] stats = pokemon.getStats();
		JPanel visualStats = new JPanel();
		visualStats.setLayout(new GridLayout(0, 1));
		visualStats.setBackground(new Color(204, 204, 204));

		//label for each stat
		statLabel[0][0]=new JLabel("HP \u00A0");
		statLabel[1][0]=new JLabel("ATK ");
		statLabel[2][0]=new JLabel("DEF ");
		statLabel[3][0]=new JLabel("SpA ");
		statLabel[4][0]=new JLabel("SpD ");
		statLabel[5][0]=new JLabel("SPE ");

		for (int i = 0; i<6; i++){//each panel is given it's layout and size
			statBars[i] = new JPanel();
			statBars[i].setPreferredSize(new Dimension(330, 26));
			layout [i]= new GroupLayout(statBars[i]);
			statBars[i].setLayout(layout[i]);

			statLabel[i][1]=new JLabel("");//initial value
			for (int j=0; j< stats[i]/4;j++){//spaces are added to create the illusion of a bar graph type look
				statLabel[i][1].setText(statLabel[i][1].getText()+"\u00a0");
			}
			statLabel[i][2]=new JLabel(Integer.toString(stats[i]));//text version of above (shows number instead of bar)

			//sets font for each label
			statLabel[i][0].setFont(new Font("MONOSPACED", Font.PLAIN, 18));
			statLabel[i][1].setFont(new Font("SansSerif", Font.PLAIN, 12));
			statLabel[i][2].setFont(new Font("MONOSPACED", Font.PLAIN, 18));

			setStatColour(i);



			statBars[i].setBorder(BorderFactory.createEmptyBorder(-12, 0, 12, 0));
			createLayout(i);
			visualStats.add(statBars[i]);
		}
		//sets look of components
		JLabel bs = new JLabel("Base Stats");
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		gl.setHorizontalGroup(
				gl.createSequentialGroup()
						.addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(bs)
										.addComponent(visualStats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(damageMult)
						)
		);
		gl.setVerticalGroup(
				gl.createSequentialGroup()
						.addComponent(bs)
						.addComponent(visualStats, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(damageMult)
		);
		//add(visualStats);
		//add(damageMult);
	}

	//sets colours for each stat
	private void setStatColour(int i){
		Color [] colour= new Color [2];
		if (i==0) {colour[0] = new Color(255, 96, 96);  colour[1]=new Color(255,32,32);}
		if (i==1) {colour[0] = new Color(245,172,120);  colour[1]=new Color(255,128,0);}
		if (i==2) {colour[0] = new Color(250, 224, 120);  colour[1]=new Color(255,255,64);}
		if (i==3) {colour[0] = new Color(157, 183, 245);  colour[1]=new Color(64,64,255);}
		if (i==4) {colour[0] = new Color(167, 219, 141);  colour[1]=new Color(96,255,96);}
		if (i==5) {colour[0] = new Color(250, 146, 178);  colour[1]=new Color(248,88,136);}
		statBars[i].setBackground(colour[0]);
		statLabel[i][1].setBackground(colour[1]);
		statLabel[i][1].setOpaque(true);
	}

	//sets layout for panel
	private void createLayout(int i){
		layout[i].setAutoCreateGaps(true);
		layout[i].setAutoCreateContainerGaps(true);
		layout[i].setHorizontalGroup(
				layout[i].createSequentialGroup()
						.addComponent(statLabel[i][0])
						.addComponent(statLabel[i][1])
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 200, Short.MAX_VALUE)
						.addComponent(statLabel[i][2])
		);
		layout[i].setVerticalGroup(
				layout[i].createSequentialGroup()
						.addGroup(layout[i].createParallelGroup(GroupLayout.Alignment.CENTER)
										.addComponent(statLabel[i][0])
										.addComponent(statLabel[i][1])
										.addComponent(statLabel[i][2])
						)
		);
	}


}
