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
	
	private String lien;
	
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
	
	public String getLien() {
		return this.lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}
	
}
