package WhistModel;

import java.util.regex.Pattern;

public class Carte 
{

	// attributs
	private int rang;	// rang de la carte 2 à 14 (2...10 Valet Dame Roi As)
	private Couleur couleur;	// couleur de la carte
	
	// constructeur
	public Carte(int rang, Couleur couleur) 
	{
		this.rang = rang;
		this.couleur = couleur;
	}
	
	// retourne une carte
	public static Carte carteDepuisSignature(String signature)
	{
		String[] parts = signature.split(Pattern.quote("_"));
		Couleur couleur = Couleur.CARREAU;
		
		if (parts[0].equals("CARREAU"))
		{
			couleur = Couleur.CARREAU;
		} 
		else if (parts[0].equals("PIQUE"))
		{
			couleur = Couleur.PIQUE;
		} 
		else if (parts[0].equals("COEUR"))
		{
			couleur = Couleur.COEUR;
		} 
		else if (parts[0].equals("TREFLE"))
		{
			couleur = Couleur.TREFLE;
		} else
		{
			System.out.println("ERROR !");
		}
		
		int rang = 0;
		if (parts[1].equals("AS"))
		{
			rang = 14;
		}
		else if (parts[1].equals("ROI"))
		{
			rang = 13;
		}
		else if (parts[1].equals("DAME"))
		{
			rang = 12;
		}
		else if (parts[1].equals("VALET"))
		{
			rang = 11;
		}
		else
		{
			rang = Integer.parseInt(parts[1]);
		}
		
		return new Carte(rang, couleur);
	}
	
	// getters
	public int getRang()
	{
		return this.rang;
	}
	
	public Couleur getCouleur()
	{
		return this.couleur;
	}
	
	// setters
	public void setRang(int rang)
	{
		this.rang = rang;
	}
	
	public void setCouleur(Couleur couleur)
	{
		this.couleur = couleur;
	}
	
	// to string avec pour code
	// (S)pade = pique | (D)iamond = carreau | (H)eart = coeur | (C)lub = trèfle
	// 2-E en héxadécimale pour les cartes de 2-10 puis Valet Dame Roi As
	public String toString()
	{
		String couleur = "";
		String rang = "";
		String signature = "";
		
		if (this.couleur == Couleur.PIQUE)
		{
			couleur = "PIQUE";
		} 
		else if (this.couleur == Couleur.CARREAU)
		{
			couleur = "CARREAU";
		} 
		else if (this.couleur == Couleur.COEUR)
		{
			couleur = "COEUR";
		} 
		else 
		{
			couleur = "TREFLE";
		}
		
		if (this.rang <= 10)
		{
			rang = Integer.toString(this.rang);
		} 
		else if (this.rang == 11)
		{
			rang = "VALET";
		}
		else if (this.rang == 12)
		{
			rang = "DAME";
		}
		else if (this.rang == 13)
		{
			rang = "ROI";
		}
		else if (this.rang == 14)
		{
			rang = "AS";
		}
		
		signature = couleur + "_" + rang;
		return signature;
	}
}
