/**
 * Michael Lee
 * June 4, 2014
 *
 * paintComponent:	Used to change the background colour to a gradient
 *	fixName:		deals with Unicode characters

 */
package com.gmail.mikerlee96.gui;

import com.gmail.mikerlee96.main.Pokemon;
import javax.swing.*;
import java.awt.*;

public class PokemonHeader extends JPanel{
	Pokemon pokeColour;//used for colours

	protected void paintComponent(Graphics g) {//when the panel is initialized, background colour is changed
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		//creates a gradient based on the pokemon's type
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		int w = getWidth();
		int h = getHeight();
		Color colour1=pokeColour.getTypeColour();
		Color colour2;
		if (pokeColour.hasElement("Type 2")) colour2=pokeColour.getType2Colour();
		else colour2=colour1;//if the pokemon doesn't have a second type
		GradientPaint gp = new GradientPaint(0, 0, colour1, w, 0, colour2,true);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, w, h);
	}

	public PokemonHeader (Pokemon selectPoke){
		pokeColour=selectPoke;
		GroupLayout layout = new GroupLayout(this);
		setBorder(BorderFactory.createEmptyBorder(-10, 0, -10, 0));
		setLayout(layout);

		//sets the look of the labels
		JLabel[] pokeInfo = new JLabel[10];
		pokeInfo[0] = new JLabel("#"+selectPoke.info("Number"));
		pokeInfo[0].setFont(new Font("MonoSpaced", Font.PLAIN, 16));

		pokeInfo[1] = new JLabel(fixName(selectPoke),JLabel.LEFT);
		pokeInfo[1].setVerticalAlignment(JLabel.BOTTOM);

		pokeInfo[2] = new JLabel (selectPoke.info("Species"));
		pokeInfo[2].setFont(new Font("SansSerif", Font.ITALIC, 12));
		pokeInfo[2].setBorder(BorderFactory.createEmptyBorder(0, 0, 7, 0));

		//sets look of components
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
						.addComponent(pokeInfo[0])
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 200, Short.MAX_VALUE)
						.addComponent(pokeInfo[1])
						.addComponent(pokeInfo[2])
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 200, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addComponent(pokeInfo[0])
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
														.addComponent(pokeInfo[1])
														.addComponent(pokeInfo[2])
										)
						)
		);
	}

	private String fixName (Pokemon pokemon){//formats text if the name contains unicode characters
		String name = pokemon.displayName();
		String fixed = "<html><b><span style=\"font-family: cambria; font-size: 42pt;\">";
		if (!pokemon.displayName().equals(pokemon.info("Name"))) {
			fixed += name.substring(0, name.length() - 1);
			fixed += "</span><span Style=\"font-family: arial; font-size: 28;\">"+name.substring(name.length() - 1) ;
		} else {
			fixed += name;
		}
		fixed += "</span></b></html>";
		return fixed;
	}

}
