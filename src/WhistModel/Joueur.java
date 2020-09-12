package WhistModel;
import java.util.ArrayList;

public class Joueur
{
	private static int idGenerator = 0;
	
	// attributs
	private int id;
	private String nom;
	private Equipe equipe;
	private ArrayList<Carte> cartes;
	
	// constructeur
	public Joueur(String nom, Equipe equipe)
	{
		this.id = this.idGenerator++;
		this.nom = nom;
		this.equipe = equipe;
		this.cartes = new ArrayList<Carte>();
	}
	
	// getters & setters
	public int getId()
	{
		return this.id;
	}
	
	public String getNom()
	{
		return this.nom;
	}
	
	public Equipe getEquipe()
	{
		return this.equipe;
	}
	
	public ArrayList<Carte> getCartes()
	{
		return this.cartes;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setNom(String nom)
	{
		this.nom = nom;
	}
	
	public void setEquipe(Equipe equipe)
	{
		this.equipe = equipe;
	}
	
	public void setCartes(ArrayList<Carte> cartes)
	{
		this.cartes = cartes;
	}
	
	// recevoir une carte (ajouter une carte dans la main du joueur)
	public void recevoirCarte(Carte carte)
	{
		this.cartes.add(carte);
	}
	
	// jouer une carte (supprimer une carte de la main du joueur)
	public void jouerCarte(Carte carte)
	{
		// recherche de la carte en question dans la main du joueur
		for (int i = 0; i < this.cartes.size(); i++)
		{
			Carte local = this.cartes.get(i);
			if (local.getCouleur() == carte.getCouleur() && local.getRang() == carte.getRang())
			{
				this.cartes.remove(i);
			}
		}
	}
}
