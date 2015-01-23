package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@Entity
@NamedQuery(name="image.findAll", query="SELECT i FROM Image i")
public class Image implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	private String nom;
	
	@ManyToOne
	private Categorie categorie;
	
	private String source;
	
	public Image(){
		
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}
