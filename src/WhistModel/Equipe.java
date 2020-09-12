package WhistModel;
import java.util.ArrayList;

public class Equipe 
{
	
	// attributs
	private ArrayList<Pli> plis;
	
	// constructeur
	public Equipe()
	{
		this.plis = new ArrayList<Pli>();
	}
	
	// getters & setters
	public ArrayList<Pli> getPlis()
	{
		return this.plis;
	}
	
	public void setPlis(ArrayList<Pli> plis)
	{
		this.plis = plis;
	}
	
	// ajouter un pli à l'équipe (gagnante)
	public void ajouterPli(Pli pli)
	{
		this.plis.add(pli);
	}
	
	// supprimer tous les plis
	public void supprimerPlis()
	{
		this.plis.clear();
	}
	
	// calcule le nombre de points (6 premiers plis = 0, 7e et plus = +1 par pli)
	public int calculerPoints()
	{
		return Integer.max(0, this.plis.size() - 6);
	}
}
