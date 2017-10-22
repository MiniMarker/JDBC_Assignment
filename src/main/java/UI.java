import javax.swing.*; //GUI-komponenter
import java.awt.*; //Layout
import java.awt.event.*; //Action handling
import java.util.Scanner; //Scanner
import java.io.*; //Input/output
import static javax.swing.JOptionPane.*; //Dialogbokser

/**
 * Created by Christian on 29.09.2017.
 */

public class UI extends JFrame {

	private JButton btnInitDB;
	private JButton btnSave;
	private JButton btnExit;
	private JTextArea txaTextArea;
	DBConnection dbConnection;

	public UI() {
		super("program");

		//Tekstområde
		txaTextArea = new JTextArea();
		JScrollPane jsc = new JScrollPane(txaTextArea);
		add(jsc, BorderLayout.CENTER);
		txaTextArea.setEditable(false);
		txaTextArea.setLineWrap(true);
		txaTextArea.setWrapStyleWord(true);

		txaTextArea.setText("Velkommen til mitt kodeprosjekt for å lese data fra fil og legge dataen inn i en database ved bruk av JDBC");


		JPanel pnlEast = new JPanel(new GridLayout(3, 1)); // 1 rad og 3 kolonner
		add(pnlEast, BorderLayout.EAST);

		//Lagreknapp
		JButton btnSave = new JButton("Save");
		pnlEast.add(btnSave);
		btnSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dbConnection = new DBConnection();
				dbConnection.setupCheck();
			}
		 });



		/*
		System.out.println("");
		System.out.println(" ");
		System.out.println("");
		System.out.println("For å starte prosjetet så får du nå en rekke valg:");
		System.out.println("Trykk 1 for å koble deg opp mot databasen");
		System.out.println("Alle andre knapper vil avslutte programmet");
		*/

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Avslutt ved lukking av vindu
		setSize(600, 1000); //Vinduets størrelse
		setVisible(true); //Gjør vinduet synlig
		setResizable(true); //Gjør mulig å endre størrelse på vinduet


	}

	public void dbConnectionChoice(int choice){

		/*
		boolean t = false;
		do {
			if (answer.equalsIgnoreCase("y")) {
				TextIO.writeFile("Knights_tour.txt");
				TextIO.putln(path);
				TextIO.writeStandardOutput();
				loop1 = false;

			} else if (answer.equalsIgnoreCase("n")) {
				TextIO.putln("Ok. Thanks for playing. ");
				loop1 = false;

			} else {
				TextIO.putln("Please enter Y for yes or N for no.");
				t = true;
			}
		} while (t);
		*/


	}



}
