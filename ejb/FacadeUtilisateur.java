package ejb;

import javax.ejb.Stateless;
import javax.persistence.*;

import model.Utilisateur;

/**
 * EJB session pour la classe Utilisateur
 */
@Stateless
public class FacadeUtilisateur extends FacadeAbstraite {
	@PersistenceContext(unitName = "JEE")
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public FacadeUtilisateur() {
		super(Utilisateur.class);
	}
}