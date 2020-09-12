package WhistControler;
import WhistModel.*;
import WhistView.*;
import WhistControler.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

public class GestionnairePartie 
{
	private Partie partie;
	private int leveeNum;	// numéro de la levée [1-13] 
	private Queue<Integer> ordre;	// ordre de distribution / ordre de jeu pendant la levée x
	private int dernierGagnant;	// indice du joueur devant jouer en premier (gagnat de la dernière levée)
	private JoueurDistributeurUI distributeurUI;	// joueur 1 (joueur + distributeur
	private ArrayList<JoueurUI> joueursUI;	// joueurs 2, 3 et 4
	private int numeroLevee;	// numéro de la levée 1-13
	
	public GestionnairePartie(Joueur joueur1, Joueur joueur2, Joueur joueur3, Joueur joueur4,
			Equipe equipe1, Equipe equipe2)
	{
		this.partie = new Partie();
		this.leveeNum = 0;	// partie non commencée
		this.dernierGagnant = 0;	// par défaut le distributeur est le joueur 1 et celui qui joue en premier
		this.leveeNum = 1;	// 1e levée
		
		// ordre de distribution : 123 - 0123 - ... - 0123 - 0 (termine par le distributeur)
		this.ordre = new LinkedList<Integer>();
		for (int i = 0; i < 13; i++)
		{
			for (int index = 0; index <= 3; index++)
			{
				this.ordre.add(index);
			}
		}
		this.ordre.remove();
		this.ordre.add(0);
		System.out.println(this.ordre);
		
		this.partie.ajouterJoueur(joueur1);
		this.partie.ajouterJoueur(joueur2);
		this.partie.ajouterJoueur(joueur3);
		this.partie.ajouterJoueur(joueur4);
		
		this.partie.ajouterEquipe(equipe1);
		this.partie.ajouterEquipe(equipe2);
		
		this.joueursUI = new ArrayList<JoueurUI>();
	}
	
	public void partieDeWhist()
	{		
		// setup
		this.interdireJouerCarte(0);
		this.interdireJouerCarte(1);
		this.interdireJouerCarte(2);
		this.interdireJouerCarte(3);
	}
	
	// ajouter un distributeur
	public void ajouterDistributeur(JoueurDistributeurUI ui)
	{
		this.distributeurUI = ui;
	}
	
	// ajouter un joueur
	public void ajouterJoueur(JoueurUI ui)
	{
		this.joueursUI.add(ui);
	}
	
	// interdit la distribution des cartes
	private void interdireDistribution()
	{
		this.distributeurUI.getInputDistribuerCarte().setEnabled(false);
	}
	
	// autorise la distribution des cartes
	private void autoriserDistribution()
	{
		this.distributeurUI.getInputDistribuerCarte().setEnabled(true);
	}
	
	// interdit de poser une carte (joueur specifié)
	private void interdireJouerCarte(int joueurNum)
	{
		if (joueurNum == 0)
		{
			this.distributeurUI.getInputCarteAJouer().setEnabled(false);
			this.distributeurUI.getInputJouerCarte().setEnabled(false);
		}
		else if (joueurNum >= 1 && joueurNum <= 3)
		{
			this.joueursUI.get(joueurNum - 1).getInputCarteAJouer().setEnabled(false);
			this.joueursUI.get(joueurNum - 1).getInputJouerCarte().setEnabled(false);
		}
	}
	
	// autorise à poser une carte (joueur specifié)
	private void autoriserJouerCarte(int joueurNum)
	{
		if (joueurNum == 0)
		{
			this.distributeurUI.getInputCarteAJouer().setEnabled(true);
			this.distributeurUI.getInputJouerCarte().setEnabled(true);
		}
		else if (joueurNum >= 1 && joueurNum <= 3)
		{
			this.joueursUI.get(joueurNum - 1).getInputCarteAJouer().setEnabled(true);
			this.joueursUI.get(joueurNum - 1).getInputJouerCarte().setEnabled(true);
		}
	}
	
	// mise à jour des informations du joueur - cartes de la main du joueur
	private void updateCartesDuJoueur(int joueurNum)
	{
		if (joueurNum == 0)
		{
			String text = "";
			for (int i = 0; i < this.partie.getJoueurs().get(joueurNum).getCartes().size(); i++)
			{
				String signature = this.partie.getJoueurs().get(joueurNum).getCartes().get(i).toString();
				text = text + "\n" + signature;
			}

			this.distributeurUI.getOutputCartes().setText(text);
		}
		else if (joueurNum >= 1 && joueurNum <= 3)
		{
			String text = "";
			for (int i = 0; i < this.partie.getJoueurs().get(joueurNum).getCartes().size(); i++)
			{
				String signature = this.partie.getJoueurs().get(joueurNum).getCartes().get(i).toString();
				text = text + "\n" + signature;
			}

			this.joueursUI.get(joueurNum - 1).getOutputCartes().setText(text);
		}
	}
	
	// distribue la prochaine carte du paquet au joueur devant recevoir la prochaine carte selon l'ordre
	public void distribuerCarte()
	{	
		int joueurNum = this.ordre.remove();
		this.partie.distribuerCarte(joueurNum);
		
		// si le paquet est vide
		if (this.partie.getPaquetDeCartes().getCartes().size() == 0)
		{
			// interdire de distribuer
			this.interdireDistribution();
			// passer à la levée 1
			System.out.println("Cartes distribuées !");
			this.setupPremiereLevee();
		}
		this.updateCartesDuJoueur(joueurNum);
		// mettre à jour la sélection de cartes pour le prochain joueur (1)
		String[] selection = this.selectionCartes(this.ordre.peek());
		// mettre à jour la selection dans l'interface
		this.updateSelectionCartes(this.ordre.peek(), selection);
	}
	
	// joue une carte (supprime la carte de la main du joueur pour l'ajouter au pli)
	public void jouerCarte(Carte carte, int idJoueur)
	{
		// si le joueur est le premier de la levée
		if (this.ordre.size() == 4)
		{
			// définir un nouveau pli
			Pli pli = new Pli(carte.getCouleur());
			this.partie.setPli(pli);
			this.partie.setAtout(carte);
		}
		
		// suppression de la carte
		int joueurNum = this.ordre.remove();
		this.partie.getJoueurs().get(joueurNum).jouerCarte(carte);
		// ajout de la carte dans le pli
		this.partie.getPli().ajouterCarte(carte, this.partie.getJoueurs().get(joueurNum));
		// mise à jour de la levée (affichage)
		this.updateCartesDuJoueur(joueurNum);
		this.updatePli();
		

		// si le joueur était le dernier à jouer (4e)
		if (this.ordre.size() == 0)
		{
			// si c'était le 13e levée -> fin de partie
			if (this.leveeNum == 13)
			{
				Equipe equipeGagnante = this.partie.calculerGagnantPartie();
				for (int i = 0; i < this.partie.getJoueurs().size(); i++)
				{
					if (this.partie.getJoueurs().get(i).getEquipe().equals(equipeGagnante))
					{
						System.out.println("Joueur " + this.partie.getJoueurs().get(i).getNom() + " gagne");
					}
				}
			}
			else // sinon fin de la levée
			{
				// attribuer le pli à l'équipe gagnante
				Joueur joueurGagnant = this.partie.CalculerGagnantLevee();
				joueurGagnant.getEquipe().ajouterPli(this.partie.getPli());
				// définir le numéro du joueur
				this.dernierGagnant = joueurGagnant.getId();
				// créer un nouvel ordre de partie
				this.definirOrdreProchainePartie();
				// levée suivant
				this.leveeNum++;
				// supprimer l'atout de cette partie
				this.partie.setAtout(null);
				// update des restrictions
				System.out.println("changement de levée");
				this.udpateRestrictionJouerCarte();
				// récupère la sélection de cartes à jouer pour le prochain joueur
				String[] selection = this.selectionCartes(this.ordre.peek());
				// mettre à jour la selection dans l'interface
				this.updateSelectionCartes(this.ordre.peek(), selection);
			}
		}
		else
		{
			// mise à jour des restrictions
			this.udpateRestrictionJouerCarte();
			// récupère la sélection de cartes à jouer pour le prochain joueur
			String[] selection = this.selectionCartes(this.ordre.peek());
			// mettre à jour la selection dans l'interface
			this.updateSelectionCartes(this.ordre.peek(), selection);
		}
	}
	
	// mets à jour la liste de sélection du joueur
	private void updateSelectionCartes(int joueurNum, String[] selection)
	{
		if (joueurNum == 0)
		{
			this.distributeurUI.getInputCarteAJouer().removeAllItems();
			for (int i = 0; i < selection.length; i++)
			{
				this.distributeurUI.getInputCarteAJouer().addItem(selection[i]);
			}
		}
		else if (joueurNum >= 1 && joueurNum <= 3)
		{
			this.joueursUI.get(joueurNum - 1).getInputCarteAJouer().removeAllItems();
			for (int i = 0; i < selection.length; i++)
			{
				this.joueursUI.get(joueurNum - 1).getInputCarteAJouer().addItem(selection[i]);
			}
		}
	}
	
	// définit l'odre de la partie à commencer en fonction du gagnant de la partie précédente
	private void definirOrdreProchainePartie()
	{
		this.ordre.clear();
		if (this.dernierGagnant == 0)
		{
			this.ordre.add(0);
			this.ordre.add(1);
			this.ordre.add(2);
			this.ordre.add(3);
		}
		else if (this.dernierGagnant == 1)
		{
			this.ordre.add(1);
			this.ordre.add(2);
			this.ordre.add(3);
			this.ordre.add(0);
		}
		else if (this.dernierGagnant == 2)
		{
			this.ordre.add(2);
			this.ordre.add(3);
			this.ordre.add(0);
			this.ordre.add(1);
		}
		else if (this.dernierGagnant == 3)
		{
			this.ordre.add(3);
			this.ordre.add(0);
			this.ordre.add(1);
			this.ordre.add(2);
		}
	}
	
	// interdit à tous les joueurs de jouer une carte sauf celui dont c'est le tour
	private void udpateRestrictionJouerCarte()
	{
		for (int i = 0; i < 4; i++)
		{
			this.interdireJouerCarte(i);
		}
		// autoriser le premier de la file
		this.autoriserJouerCarte(this.ordre.peek());
	}
	
	// mise à jour de la levée (pli en cours)
	private void updatePli()
	{
		String text = "Levée en cours :\n";
		for (int i = 0; i < this.partie.getPli().getCartes().size(); i++)
		{
			String signature = this.partie.getPli().getCartes().get(i).toString();
			text = text + "\n" + signature;
		}

		this.distributeurUI.getOutputLevee().setText(text);
		
		
		// joueurs
		for (int joueurNum = 0; joueurNum < 3; joueurNum++)
		{
			text = "Levée en cours :\n";
			for (int i = 0; i < this.partie.getPli().getCartes().size(); i++)
			{
				String signature = this.partie.getPli().getCartes().get(i).toString();
				text = text + "\n" + signature;
			}

			this.joueursUI.get(joueurNum).getOutputLevee().setText(text);
		}
	}
	
	// setup de la première levée
	private void setupPremiereLevee()
	{
		// retourner la dernière carte (dernière du distributeur) c'est l'atout de la levée
		Joueur joueur = this.partie.getJoueurs().get(0);	// distributeur (joueur 0)
		Carte carte = joueur.getCartes().get(joueur.getCartes().size() - 1);	// dernière carte reçue
		// définir le premier pli
		Pli pli = new Pli(carte.getCouleur());
		this.partie.setAtout(carte);
		this.partie.setPli(pli);
		this.partie.jouerCarte(joueur, carte);
		
		// ordre de jeu 0-1-2-3 (le joueur 0 a déjà joué)
		this.ordre.clear();
		this.ordre.add(1);
		this.ordre.add(2);
		this.ordre.add(3);
		
		// mise à jour de l'affichage
		this.updatePli();
		// mise à jour des restriction
		this.udpateRestrictionJouerCarte();
	}
	
	// récupère la liste des cartes pouvant être jouées par le joueur
	private String[] selectionCartes(int joueurNum)
	{
		String temp = "";
		// récupération des cartes du joueur
		ArrayList<Carte> cartes = this.partie.getJoueurs().get(joueurNum).getCartes();
		
		// premier joueur à jouer (pas encore d'atout donc pas de restriction)
		if (this.partie.getAtout() == null)
		{
			for (int i = 0 ; i < cartes.size(); i++)
			{
				temp = temp + " " + cartes.get(i).toString();
			}
		}
		else // parcours et récupération des cartes de la bonne couleur (celle de l'atout)
		{
			int nb = 0;
			for (int i = 0 ; i < cartes.size(); i++)
			{
				if (cartes.get(i).getCouleur() == this.partie.getAtout().getCouleur())
				{
					temp = temp + " " + cartes.get(i).toString();
					nb++;
				}
			}
			// si aucune alors tout est permis
			if (nb == 0)
			{
				temp = "";
				for (int i = 0 ; i < cartes.size(); i++)
				{
					temp = temp + " " + cartes.get(i).toString();
				}
			}
		}
		
		// tableau
		String[] selection = temp.split(Pattern.quote(" "));
		return selection;
	}
	
}
