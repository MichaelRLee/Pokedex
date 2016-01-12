/**
 * Michael Lee
 * June 2, 2014
 * This program is a pokedex useful for providing a quick reference for people
 * while playing the Pokemon video game.  It includes information on the first
 * 151 pokemon, including base stats, pokedex entries, and more.
 *
 * Fields and methods:
 *  makeHeader:		Deals with the look of the search area
 *	setIcons:		Import and sets the program’s icon
 *	makeButton:		Makes the button for a given Pokémon
 *	getPokemon:		Imports the images of the Pokémon and then calls makeButton
 *	search:			Handles searches using the Search class
 *	actionPerformed:for when a user interacts with a component.  Executes a command if the user clicks a Pokémon button,
 					searches or resets search, or changes the search category
 *	makeDexScroll:	creates and sets the scroll increment the JScrollPanes for the pokédex entries and stats panels
 *	makeInfoTab:	creates and sets initializes the central information panel
 *	main:			runs the program
 */

package com.gmail.mikerlee96.gui;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.gmail.mikerlee96.main.Reader;
import com.gmail.mikerlee96.main.Pokemon;
import com.gmail.mikerlee96.main.Searcher;


public class PokedexGUI extends JFrame implements ActionListener{
	private JPanel header = new JPanel (); //Holds searchTools and formatting
	private JPanel pokemon = new JPanel (); //panel for all pokemon information
	private JPanel searchTools = new JPanel (); //panel for searching
	private JPanel listArea = new JPanel (new CardLayout());//area where lists are displayed
	private JPanel searchList = new JPanel ();//stores search results
	private PokemonHeader pokeTitle;//title for pokemon info
	private PokeSideBar summary;//side bar containing basic pokemon info
	private ImageIcon [][] sprites;//stores sprites (images) for pokemon
	private JButton [] pokeButton; //buttons for list of pokemon
	private File[] icons = new File[]{new File("16.png"), new File("24.png"), //array of program icons
			new File("32.png"), new File("48.png"), new File("64.png"), new File("72.png"), new File("96.png"),
			new File("128.png"), new File("180.png"), new File("256.png")};
	private JScrollPane searchScroll; //scroll pane for search results
	private JScrollPane entryScroll; //scroll pane for pokedex entries
	private JScrollPane statScroll; //scroll pane for pokemon stats
	private JTextField searchInput; //scroll pane for default pokemon list
	private Pokemon [] pokeList; //list of pokemon
	private DexEntries entries; //panel for pokedex entries
	private String searchBy = "Name"; //what to search by when searching
	private JTabbedPane infoTab = new JTabbedPane();//tabs for pokemon information
	private ArrayList <Image> logos = new ArrayList<Image>(); //arraylist of logos
	private StatsPanel pokemonStats; //panel for the pokemon's stats
	private JLabel optionsLabel = new JLabel("Search By: ");//A label
	private String [] searchCategories = {"Name","Number","Type","Species"};//different search categories

	public PokedexGUI (){
		JPanel list = new JPanel();//list of pokemon
		setTitle("Pokedex");//sets the frame's title
		setIcons();//sets the icons for the picture
		getPokemon();//creates the list of pokemon
		makeHeader();//makes the search area section

		list.setLayout(new GridLayout(0, 1));//adds the list of pokemon buttons to the panel
		for (JButton aPokeLabel : pokeButton) list.add(aPokeLabel);
		listArea.setPreferredSize(new Dimension(200, 600));//sets the size of the list

		JScrollPane scrollList = new JScrollPane(list);//initializes scrollPane
		scrollList.getVerticalScrollBar().setUnitIncrement(11);//sets the scroll increment
		listArea.add(scrollList, "default list");//adds the pane to the card list layout panel

		search("MissingNo");//initializes search
		searchScroll = new JScrollPane(searchList);//adds search to scrollPane
		searchScroll.getVerticalScrollBar().setUnitIncrement(11);//sets scroll incriment
		listArea.add(searchScroll,"search list");//adds to card layout

		//initializes pokemon information for side bar
		pokeTitle = new PokemonHeader(pokeList[0]);
		entries = new DexEntries(pokeList [0]);
		pokemonStats = new StatsPanel(pokeList[0]);
		makeDexScroll();
		summary = new PokeSideBar(pokeList[0]);
		summary.setPreferredSize(new Dimension(200,600));

		//adds tabs for pokemon information
		infoTab.addTab("Entries", entryScroll);
		infoTab.addTab("Stats", statScroll);

		//creates area for pokemon's information
		pokemon.setBackground(new Color(224, 224, 224));
		pokemon.setLayout(new BorderLayout());
		pokemon.add(summary, BorderLayout.WEST);
		pokemon.add(infoTab, BorderLayout.CENTER);
		pokemon.add(pokeTitle, BorderLayout.NORTH);

		//creates general layout of frame
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(header, BorderLayout.NORTH);
		getContentPane().add(listArea, BorderLayout.WEST);
		getContentPane().add(pokemon, BorderLayout.CENTER);
	}

	//sets layout of search area
	private void makeHeader (){
		//initializes search field
		searchInput = new JTextField (15);
		searchInput.setActionCommand("search");
		searchInput.addActionListener(this);

		//initializes search button
		JButton searchButton = new JButton("Search");
		searchButton.setActionCommand("search");
		searchButton.addActionListener(this);

		//initializes help button
		JButton help = new JButton("?");
		help.setActionCommand("help");
		help.addActionListener(this);

		//initializes drop down menu for search options
		JComboBox<String> optionsList = new JComboBox<String>(searchCategories);
		optionsList.setActionCommand("category");
		optionsList.addActionListener(this);

		//initializes reset button
		JButton secondaryButton = new JButton("Reset");
		secondaryButton.setActionCommand("reset");
		secondaryButton.addActionListener(this);

		//sets layout. adds components and spaces them out as well
		searchTools.setLayout(new BoxLayout(searchTools, BoxLayout.X_AXIS));
		searchTools.add(help);
		searchTools.add(Box.createRigidArea(new Dimension(25, 0)));
		searchTools.add(secondaryButton);
		searchTools.add(Box.createRigidArea(new Dimension(25,0)));
		searchTools.add(new JSeparator(SwingConstants.VERTICAL));
		searchTools.add(Box.createRigidArea(new Dimension(20,0)));
		searchTools.add(optionsLabel);
		searchTools.add(optionsList);
		searchTools.add(searchInput);
		searchTools.add(Box.createRigidArea(new Dimension(5,0)));
		searchTools.add(searchButton);
		searchTools.setBorder(BorderFactory.createEmptyBorder(5,5,3,3));
		header.setLayout(new BoxLayout (header, BoxLayout.Y_AXIS));
		header.add(searchTools);
		header.add(new JSeparator(SwingConstants.HORIZONTAL), BorderLayout.NORTH);
	}

	//sets logos
	private void setIcons (){
		for (int i=0; i<10; i++){
			//turns files into images
			try {
				logos.add(ImageIO.read(icons[i]));
			} catch (IOException e) {
				System.out.println("ERROR!  Program icon not found!");
			}
		}
		//sets logo based on best size
		setIconImages(logos);
	}

	//creates pokemon buttons based on number
	private JButton makeButton (int i){
		//Initializes button, sets spacing, and returns created button
		JButton button = new JButton (" "+pokeList[i].info("Number")+"   "+pokeList[i].displayName(), sprites[i][0]);
		button.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setActionCommand(pokeList[i].info("Number"));
		button.addActionListener(this);
		return button;
	}

	//creates list of pokemon and sets the images for each pokemon
	private void getPokemon(){
		Reader read = new Reader();//used to gets list from file
		pokeList=read.getPokemon();
		sprites = new ImageIcon [pokeList.length][2];
		pokeButton = new JButton [pokeList.length];
		//creates sprites and initializes button
		for (int i = 0; i<pokeList.length; i++){
			sprites [i][0] = new ImageIcon("Resources"+File.separator+"Sprites"+File.separator+pokeList[i].info("Number")+".png");
			sprites [i][1] = new ImageIcon("Resources"+File.separator+"Image"+File.separator+pokeList[i].info("Number")+" (1).gif");
			pokeButton[i] = makeButton(i);
		}
	}

	//searches based on user input
	private void search(String term){
		Searcher finder = new Searcher(searchBy,term);//used to search
		Pokemon [] results = finder.results();//pokemon array of search results
		JButton[] searchResults = new JButton[results.length];//buttons for search results
		searchList = new JPanel(); //resets panel
		searchList.setLayout(new GridLayout(0, 1));//resets the layout
		int num;
		if (results.length> 0) {//if there are any results
			for (int i = 0; i < results.length; i++) {//creates pokemon buttons and adds them to the list
				num = Integer.parseInt(results[i].info("Number"))-1;
				searchResults[i]=makeButton(num);
				searchList.add(searchResults[i]);
			}
		}else if (results.length==0){//No results
			JLabel nope = new JLabel("No Results",JLabel.CENTER);
			searchList.add(nope);
		}
	}

	public void actionPerformed(ActionEvent e) {//GUI user input
		if (e.getActionCommand().equals("search")){//if the user is searching
			String searchTerm = searchInput.getText(); //what they are searching
			CardLayout cl = (CardLayout)(listArea.getLayout());
			if (searchTerm.equals("")){//if the search is blank, return default list
				cl.show(listArea,"default list");
			}else{//gets search results and shows them
				search(searchTerm);
				searchScroll = new JScrollPane(searchList);
				searchScroll.getVerticalScrollBar().setUnitIncrement(11);
				listArea.remove(searchScroll);
				listArea.add(searchScroll, "search list");
				cl.show(listArea,"search list");
				revalidate();
			}
		}else if (e.getActionCommand().equals("reset")){ //if the user is reseting the list
			CardLayout cl = (CardLayout)(listArea.getLayout());//makes the list the default one
			cl.show(listArea,"default list");
			searchInput.setText("");
		}else if (e.getActionCommand().equals("category")) {//changes the search category
			JComboBox cb = (JComboBox) e.getSource();
			searchBy = (String) cb.getSelectedItem();
		}else if (e.getActionCommand().equals("help")){//user needs help
			Help helpWindow = new Help(logos);//creates and shows help window
			helpWindow.createAndShow();
		}else {//if the user selected a pokemon
			int pokeNum = Integer.parseInt(e.getActionCommand()) - 1;//which pokemon
			//resets panel
			pokemon.remove(pokeTitle);
			pokemon.remove(summary);
			pokemon.remove(infoTab);
			revalidate();

			//resets the pokemon information
			pokeTitle = new PokemonHeader(pokeList[pokeNum]);
			summary = new PokeSideBar(pokeList[pokeNum]);

			//creates tabs for pokemon information
			makeInfotab(pokeNum);

			//resets sidebar's look
			summary.setPreferredSize(new Dimension(200, 600));
			summary.setAlignmentY(SwingConstants.TOP);

			//re-adds the pokemon information
			pokemon.add(pokeTitle, BorderLayout.NORTH);
			pokemon.add(summary, BorderLayout.WEST);
			pokemon.add(infoTab, BorderLayout.CENTER);
			revalidate();
		}
	}

	//initializes and sets up scroll panes
	private void makeDexScroll(){
		entryScroll= new JScrollPane(entries);
		entryScroll.getVerticalScrollBar().setUnitIncrement(15);
		statScroll = new JScrollPane(pokemonStats);
		statScroll.getVerticalScrollBar().setUnitIncrement(15);
	}

	//creates the tabs
	private void makeInfotab(int pokeNum){
		int index = infoTab.getSelectedIndex();//gets which tab is selected
		infoTab.removeAll();//resets tab
		entries = new DexEntries(pokeList[pokeNum]);//resets tabs
		pokemonStats = new StatsPanel(pokeList[pokeNum]);
		makeDexScroll();//makes scroll
		infoTab.addTab("Entries", entryScroll);//re-adds tabs
		infoTab.addTab("Stats", statScroll);
		infoTab.setSelectedIndex(index);//goes back to selected tab
		revalidate();
	}

	public static void main (String [] args){//main
		//sets the GUI look based on OS
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Unable to apply OS theme to GUI.  Switching to default Java.");
		}
		JFrame gui = new PokedexGUI ();
		gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//process finishes on close
		gui.setSize(new Dimension(800,600));//set default size
		gui.setVisible(true);//shows frame
	}
}
