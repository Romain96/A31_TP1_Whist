package WhistModel;
import java.util.ArrayList;

public class Pli 
{
	
	// attributs
	private ArrayList<Carte> cartes;
	private Joueur gagnant;
	private int rangMax;
	
	// constructeur
	public Pli(Couleur couleur)
	{
		this.cartes = new ArrayList<Carte>();
		this.gagnant = null;
		this.rangMax = 0;
	}
	
	// getters & setters
	public ArrayList<Carte> getCartes()
	{
		return this.cartes;
	}
	
	public Joueur getGagnant()
	{
		return this.gagnant;
	}
	
	public int getRangMax()
	{
		return this.rangMax;
	}
	
	public void setCartes(ArrayList<Carte> cartes)
	{
		this.cartes = cartes;
	}
	
	public void setGagnant(Joueur joueur)
	{
		this.gagnant = joueur;
	}
	
	public void setRangMax(int rangMax)
	{
		this.rangMax = rangMax;
	}
	
	// ajouter une carte au pli et mets à jour le gagnant du pli/de la levée
	public void ajouterCarte(Carte carte, Joueur joueur)
	{
		this.cartes.add(carte);
		int rang = carte.getRang();
		if (rang > this.rangMax)
		{
			this.rangMax = rang;
			this.gagnant = joueur;
		}
	}

}
