package WhistModel;
import java.util.ArrayList;

public class Partie 
{
	// attributs
	private ArrayList<Joueur> joueurs;
	private ArrayList<Equipe> equipes;
	private PaquetDeCartes cartes;
	private Carte atout;	// la première carte jouée/retournée
	private Pli pli;	// le pli en cours
	
	// constructeur
	public Partie()
	{
		this.joueurs = new ArrayList<Joueur>();
		this.equipes = new ArrayList<Equipe>();
		this.cartes = new PaquetDeCartes();
		this.atout = null;
		this.pli = null;
	}
	
	// getters & setters
	public ArrayList<Joueur> getJoueurs()
	{
		return this.joueurs;
	}
	
	public ArrayList<Equipe> getEquipes()
	{
		return this.equipes;
	}
	
	public PaquetDeCartes getPaquetDeCartes()
	{
		return this.cartes;
	}
	
	public Carte getAtout()
	{
		return this.atout;
	}
	
	public Pli getPli()
	{
		return this.pli;
	}
	
	public void setJoueurs(ArrayList<Joueur> joueurs)
	{
		this.joueurs = joueurs;
	}
	
	public void setEquipes(ArrayList<Equipe> equipes)
	{
		this.equipes = equipes;
	}
	
	public void setPaquetDeCartes(PaquetDeCartes cartes)
	{
		this.cartes = cartes;
	}
	
	public void setAtout(Carte atout)
	{
		this.atout = atout;
	}
	
	public void setPli(Pli pli)
	{
		this.pli = pli;
	}
	
	// ajouter un joueur à la partie
	public void ajouterJoueur(Joueur joueur)
	{
		this.joueurs.add(joueur);
	}
	
	// ajouter une équipe à la partie
	public void ajouterEquipe(Equipe equipe)
	{
		this.equipes.add(equipe);
	}
	
	// distribuer une carte (supprime la carte du paquet et l'ajoute au joueur)
	public void distribuerCarte(int index)
	{
		// copie
		Carte toRemove = this.getPaquetDeCartes().getCartes().get(0);
		Carte carte = new Carte(toRemove.getRang(), toRemove.getCouleur());
		this.getPaquetDeCartes().getCartes().remove(0);
		this.getJoueurs().get(index).recevoirCarte(carte);
	}
	
	// jouer une carte (ajoute une carte au pli en cours et la supprime de la main du joueur)
	public void jouerCarte(Joueur joueur, Carte carte)
	{
		joueur.jouerCarte(carte);	// supprime la carte de la main du joueur
		this.pli.ajouterCarte(carte, joueur);	// ajoute la carte au pli
	}
	
	// calculer le gagnant de la levée (joueur)
	public Joueur CalculerGagnantLevee()
	{
		return this.pli.getGagnant();
	}
	
	// calculer le gagnant de la partie (équipe)
	public Equipe calculerGagnantPartie()
	{
		int maxPoints = 0;
		Equipe gagnante = null;
		
		for (int i = 0; i < this.equipes.size(); i++)
		{
			int points = this.equipes.get(i).calculerPoints();
			if (points > maxPoints)
			{
				maxPoints = points;
				gagnante = this.equipes.get(i);
			}
		}
		return gagnante;
	}
}
