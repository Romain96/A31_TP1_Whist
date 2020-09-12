package WhistControler;
import WhistControler.*;
import WhistModel.*;
import WhistView.*;

public class WhistMain 
{
	
	public static void main(String[] args)
	{
		System.out.println("Jeu de Whist");
		
		// création des équipes (2)
		Equipe equipe1 = new Equipe();
		Equipe equipe2 = new Equipe();
		
		// création des joueurs (4)
		Joueur joueur1 = new Joueur("Antoine", equipe1);
		Joueur joueur2 = new Joueur("Pierre", equipe2);
		Joueur joueur3 = new Joueur("Juliette", equipe1);
		Joueur joueur4 = new Joueur("Sarah", equipe2);
		
		// création de la partie (crée le paquet de 52 cartes)
		Partie partie = new Partie();
		partie.ajouterEquipe(equipe1);
		partie.ajouterEquipe(equipe2);
		partie.ajouterJoueur(joueur1);
		partie.ajouterJoueur(joueur2);
		partie.ajouterJoueur(joueur3);
		partie.ajouterJoueur(joueur4);
		
		// création du gestionnaire de partie
		GestionnairePartie gestionnaire = new GestionnairePartie(joueur1, joueur2, joueur3, joueur4, equipe2, equipe2);
		
		// création des interfaces (1 distributeur + 3 joueurs)
		JoueurDistributeurUI ui1 = new JoueurDistributeurUI(1, gestionnaire);
		JoueurUI ui2 = new JoueurUI(2, gestionnaire);
		JoueurUI ui3 = new JoueurUI(3, gestionnaire);
		JoueurUI ui4 = new JoueurUI(4, gestionnaire);
		
		// personnalisation des interfaces
		ui1.setTitle("Joueur 1 : " + joueur1.getNom());
		ui2.setTitle("Joueur 2 : " + joueur2.getNom());
		ui3.setTitle("Joueur 3 : " + joueur3.getNom());
		ui4.setTitle("Joueur 4 : " + joueur4.getNom());
		
		// ajout des interfaces au gestionnaire
		gestionnaire.ajouterDistributeur(ui1);
		gestionnaire.ajouterJoueur(ui2);
		gestionnaire.ajouterJoueur(ui3);
		gestionnaire.ajouterJoueur(ui4);
		
		gestionnaire.partieDeWhist();
	}

}
