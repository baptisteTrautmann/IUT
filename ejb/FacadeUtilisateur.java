package ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;

import model.Image;
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
	
	public Utilisateur findByLogin(String login)
	{
		try
		{
			Query query = em.createQuery("SELECT OBJECT(u) FROM Utilisateur u WHERE u.login = :login");
			query.setParameter("login", login);
			return (Utilisateur)query.getSingleResult();
		}
		catch(NoResultException err)
		{
			return null;
		}
	}
}