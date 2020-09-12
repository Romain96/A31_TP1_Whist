package WhistControler;
import java.awt.event.*;
import java.util.regex.Pattern;

import WhistView.JoueurDistributeurUI;
import WhistModel.Carte;

public class SignalJouerCarteDistributeur implements ActionListener
{
	JoueurDistributeurUI obj;
	GestionnairePartie gestionnaire;
	
	public SignalJouerCarteDistributeur(JoueurDistributeurUI obj, GestionnairePartie gestionnaire)
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
		this.gestionnaire.jouerCarte(carte, this.obj.getId());
	}
}
