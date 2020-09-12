package WhistModel;
import java.util.ArrayList;
import java.util.Collections;

public class PaquetDeCartes 
{

	// attributs
	private ArrayList<Carte> cartes;
	
	// constructeur
	public PaquetDeCartes()
	{
		// crée un paquet de 52 cartes
		this.cartes = new ArrayList<Carte>();
		
		for (int i = 2; i <= 14; i++)
		{
			this.cartes.add(new Carte(i, Couleur.CARREAU));
			this.cartes.add(new Carte(i, Couleur.COEUR));
			this.cartes.add(new Carte(i, Couleur.PIQUE));
			this.cartes.add(new Carte(i, Couleur.TREFLE));
		}
		// mélange les cartes alétoirement
		Collections.shuffle(this.cartes);
	}
	
	// getters & setters
	public ArrayList<Carte> getCartes()
	{
		return this.cartes;
	}
	
	public void setCartes(ArrayList<Carte> cartes)
	{
		this.cartes = cartes;
	}
	
	// distribue une carte (supprime la carte du paquet)
	public Carte distribuerCarte()
	{
		// copie de la carte
		Carte carte = new Carte(this.cartes.get(0).getRang(), this.cartes.get(0).getCouleur());
		// suppression
		this.cartes.remove(0);
		
		return carte;
	}
	
}
