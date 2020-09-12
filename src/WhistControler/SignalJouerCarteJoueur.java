package WhistControler;
import java.awt.event.*;
import java.util.regex.Pattern;

import WhistView.JoueurUI;
import WhistControler.GestionnairePartie;
import WhistModel.Carte;

public class SignalJouerCarteJoueur implements ActionListener
{
	JoueurUI obj;
	GestionnairePartie gestionnaire;
	
	public SignalJouerCarteJoueur(JoueurUI obj, GestionnairePartie gestionnaire)
	{
		this.obj = obj;
		this.gestionnaire = gestionnaire;
	}
	
	public void actionPerformed(ActionEvent e)
	{		
		String input = this.obj.getInputCarteAJouer().getSelectedItem().toString();
		String[] parts = input.split(Pattern.quote(" "));
		String signature = parts[0];
		Carte carte = Carte.carteDepuisSignature(signature);
		System.out.println("carte à jouer " + carte);
		this.gestionnaire.jouerCarte(carte, this.obj.getId());
	}
}