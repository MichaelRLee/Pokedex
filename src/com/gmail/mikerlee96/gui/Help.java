/**
 * Michael Lee
 * June 15
 *
 * createAndShow	Create and shows the window
 */

package com.gmail.mikerlee96.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Help extends JFrame {
	private ArrayList <Image> logos;//array list for program icons

	public Help (ArrayList<Image> logos){
		String[] file = new String[3];//file locations
		JLabel[] images = new JLabel[3];//images
		JPanel pane = new JPanel();//main panel
		this.logos=logos;
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));//layout
		JScrollPane scroll;//makes scroll pane

		for (int i = 0; i<3; i++){//adds help pictures to frame
			file[i] = "Help"+ File.separator+"help "+(i+1)+".png";
			images[i]=new JLabel(new ImageIcon(file[i]));
			pane.add(images[i]);

		}

		scroll = new JScrollPane(pane);//initializes scroll pane
		scroll.getVerticalScrollBar().setUnitIncrement(15);
		add(scroll);//adds scroll pane to frame


	}

	public void createAndShow(){//creates and shows frame
		setTitle("Help");
		setSize(new Dimension(850, 600));
		setVisible(true);
		setIconImages(logos);

	}

}
