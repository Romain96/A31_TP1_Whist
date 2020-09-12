package WhistView;
import javax.swing.*;
import java.awt.*;
import WhistControler.SignalJouerCarteJoueur;
import WhistControler.GestionnairePartie;

public class JoueurUI extends JFrame
{
	private int id;
	private GestionnairePartie gestionnaire;
	private JTextArea outputCartes;
	private JTextArea outputLevee;
	private JComboBox<String> inputCarteAJouer;
	private JButton inputJouerCarte;

	public JoueurUI(int id, GestionnairePartie gestionnaire)
	{
		this.id = id;
		this.gestionnaire = gestionnaire;
		this.outputCartes = new JTextArea("Cartes du joueur\nvide pour l'instant...");
		this.outputCartes.setEditable(false);
		this.outputLevee = new JTextArea("Levée en cours\nj1\nj2\nj3\nj4");
		this.outputLevee.setEditable(false);
		this.inputCarteAJouer = new JComboBox<String>();
		this.inputJouerCarte = new JButton("Jouer une carte");
		
		JPanel jp = new JPanel(new FlowLayout());
		jp.add(this.outputCartes);
		jp.add(this.outputLevee);
		jp.add(this.inputCarteAJouer);
		jp.add(this.inputJouerCarte);
		
		// écouteur (bouton jouer une carte)
		SignalJouerCarteJoueur ecouteurJouerCarte = new SignalJouerCarteJoueur(this, this.gestionnaire);
		this.inputJouerCarte.addActionListener(ecouteurJouerCarte);
		
		this.setContentPane(jp);
		this.setTitle("Fenêtre joueur");
		this.setSize(500, 500);
		this.setLocation(100, 100);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	// getters & setters
	public JTextArea getOutputCartes()
	{
		return this.outputCartes;
	}
	
	public JTextArea getOutputLevee()
	{
		return this.outputLevee;
	}
	
	public JComboBox<String> getInputCarteAJouer()
	{
		return this.inputCarteAJouer;
	}
	
	public JButton getInputJouerCarte()
	{
		return this.inputJouerCarte;
	}
	
	public int getId()
	{
		return this.id;
	}
	
	public GestionnairePartie getGestionnaire()
	{
		return this.gestionnaire;
	}
	
	public void setOutputCartes(JTextArea outputCartes)
	{
		this.outputCartes = outputCartes;
	}
	
	public void setOutputLevee(JTextArea outputLevee)
	{
		this.outputLevee = outputLevee;
	}
	
	public void setInputCarteAJouer(JComboBox<String> carteAJouer)
	{
		this.inputCarteAJouer = carteAJouer;
	}
	
	public void setInputJouerCarte(JButton jouerCarte)
	{
		this.inputJouerCarte = jouerCarte;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setGestionnaire(GestionnairePartie gestionnaire)
	{
		this.gestionnaire = gestionnaire;
	}
}
