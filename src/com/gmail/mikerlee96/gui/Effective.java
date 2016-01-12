/**
 * Michael Lee
 * June 12
 */
package com.gmail.mikerlee96.gui;
import com.gmail.mikerlee96.main.*;
import javax.swing.*;
import java.awt.*;

public class Effective extends JPanel{
	JLabel headings[]=new JLabel[3];//labels for panels

	public Effective (Pokemon pokemon){
		JPanel weakness = new JPanel();//weaknesses
		JPanel resistance = new JPanel();//resistances
		JPanel immune=new JPanel();//immunities
		JPanel [] weakPanels;//panel for weaknesses
		JPanel [] resistPanels;//panel for resistances
		JPanel [] immunePanels;//panel for immunities
		JLabel [][] weakTypes;//weaknesses
		JLabel [][] resistTypes;//resistances
		JLabel [] immuneTypes;//immunities
		Type typing = new Type ("Colourless");//assorted uses

		GroupLayout layout = new GroupLayout(this);//creates layout
		setLayout(layout);//sets layout
		setBackground(new Color(224, 224, 224));//background
		//initializes panels and labels, and sets their colours
		weakTypes = new JLabel[pokemon.weaknesses().length][2];
		weakPanels = new JPanel[weakTypes.length];
		resistTypes = new JLabel[pokemon.resistance().length][2];
		resistPanels = new JPanel[resistTypes.length];
		immuneTypes = new JLabel[pokemon.immune().length];
		immunePanels = new JPanel[immuneTypes.length];
		weakness.setBackground(new Color(240, 240, 240));
		resistance.setBackground(new Color(240, 240, 240));
		immune.setBackground(new Color(240,240,240));

		//set size
		setPreferredSize(new Dimension(330,330 ));
		setMaximumSize(new Dimension(330, Short.MAX_VALUE));

		//sets layouts
		weakness.setLayout(new FlowLayout());
		resistance.setLayout(new FlowLayout());
		immune.setLayout(new FlowLayout());

		//if there are weaknesses
		if (weakTypes.length!=0) {
			for (int i = 0; i < weakTypes.length; i++) {//for each weakness, create label, panel, set colours, and add them to the frame
				weakTypes[i][0] = new JLabel(pokemon.weaknesses()[i][0]);
				weakTypes[i][1] = new JLabel(" "+pokemon.weaknesses()[i][1]+" ");
				weakTypes[i][1].setOpaque(true);
				weakTypes[i][1].setBackground(new Color(240, 240, 240));
				weakPanels[i] = new JPanel();
				weakPanels[i].setBackground(typing.colourOf(pokemon.weaknesses()[i][0]));
				weakPanels[i].add(weakTypes[i][0]);
				weakPanels[i].add(weakTypes[i][1]);
				weakness.add(weakPanels[i]);
			}
		}else{
			weakness.add(new JLabel("None"));//if there aren't any weaknesses
		}
		//same as above but for resistances
		if (resistTypes.length!=0){
			for (int i = 0; i<resistTypes.length;i++) {
				String text;
				text = pokemon.resistance()[i][1];
				if (text.equals("0.25"))text="\u00BC";
				else text="\u00BD";
				resistTypes [i][0]= new JLabel(pokemon.resistance()[i][0]);
				resistTypes [i][1]= new JLabel(" "+text+"\u00D7 ");
				resistTypes [i][1].setOpaque(true);
				resistTypes [i][1].setBackground(new Color(240, 240, 240));
				resistPanels[i]=new JPanel();
				resistPanels[i].setBackground(typing.colourOf(pokemon.resistance()[i][0]));
				resistPanels[i].add(resistTypes[i][0]);
				resistPanels[i].add(resistTypes[i][1]);
				resistance.add(resistPanels[i]);
			}
		}else{
			resistance.add(new JLabel("None"));
		}
		//same as above but for immunities
		if (immuneTypes.length!=0){
			for (int i = 0; i<immuneTypes.length;i++){
				immuneTypes [i]= new JLabel(pokemon.immune()[i][0]);
				immunePanels[i]=new JPanel();
				immunePanels[i].setBackground(typing.colourOf(pokemon.immune()[i][0]));
				immunePanels[i].add(immuneTypes[i]);
				immune.add(immunePanels[i]);
			}
		}else {
			immune.add(new JLabel("None"));
		}

		//labels for panels
		headings[0]=new JLabel("Weak to:");
		headings[0].setFont(new Font("SansSerif", Font.BOLD, 14));
		headings[1]=new JLabel("Resistant to:");
		headings[1].setFont(new Font("SansSerif",Font.BOLD,14));
		headings[2]=new JLabel("Immune to:");
		headings[2].setFont(new Font ("SansSerif",Font.BOLD,14));

		//Sets look of panels
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(headings[0])
										.addComponent(weakness, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(headings[1])
										.addComponent(resistance, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(headings[2])
										.addComponent(immune)
						)
		);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
						.addComponent(headings[0])
						.addComponent(weakness, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(headings[1])
						.addComponent(resistance, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(headings[2])
						.addComponent(immune)
		);

		//add the panels to panels
		add(resistance);
		add(weakness);
		add(immune);

	}
}
