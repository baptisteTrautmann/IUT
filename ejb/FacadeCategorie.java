package ejb;

import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.List;

import model.Categorie;

/**
 * EJB session pour la classe Categorie
 */
@Stateless
public class FacadeCategorie extends FacadeAbstraite {
	@PersistenceContext(unitName = "JEE")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public FacadeCategorie() {
		super(Categorie.class);
	}
}