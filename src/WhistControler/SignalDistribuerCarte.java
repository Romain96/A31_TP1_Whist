package WhistControler;
import java.awt.event.*;
import WhistView.JoueurDistributeurUI;

public class SignalDistribuerCarte implements ActionListener
{

	JoueurDistributeurUI obj;
	GestionnairePartie gestionnaire;
	
	public SignalDistribuerCarte(JoueurDistributeurUI obj, GestionnairePartie gestionnaire)
	{
		this.obj = obj;
		this.gestionnaire = gestionnaire;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		this.gestionnaire.distribuerCarte();
	}
	
}
