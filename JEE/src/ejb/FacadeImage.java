package ejb;

import javax.ejb.Stateless;
import javax.persistence.*;

import model.Image;

/**
 * EJB session pour la classe Categorie
 */
@Stateless
public class FacadeImage extends FacadeAbstraite {
	@PersistenceContext(unitName = "JEE")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public FacadeImage() {
		super(Image.class);
	}
}