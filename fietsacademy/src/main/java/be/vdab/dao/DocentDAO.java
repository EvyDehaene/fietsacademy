package be.vdab.dao;

import java.math.BigDecimal;
import java.util.List;

import be.vdab.entities.Docent;
import be.vdab.valueobjects.AantalDocentenPerWedde;
import be.vdab.valueobjects.VoornaamEnId;

public class DocentDAO extends AbstractDAO {
	public Docent read (long id) {
		return getEntityManager().find(Docent.class, id);
	}
	public void create (Docent docent){
		getEntityManager().persist(docent);
	}
	public void delete (long id){
		Docent docent = getEntityManager().find(Docent.class, id);
		if (docent != null){
			getEntityManager().remove(docent);
		}
	}
	public List<Docent> findByWeddeBetween(BigDecimal van, BigDecimal tot, int vanafRij, int aantalRijen){
		//je gebruikt van en tot later...
		return getEntityManager().createNamedQuery("Docent.findByWeddeBetween", Docent.class).setParameter("van", van).setParameter("tot", tot).setFirstResult(vanafRij).setMaxResults(aantalRijen).getResultList();
	}
	public Iterable<VoornaamEnId> findVoornamen() {
		  return getEntityManager().createQuery(
		    "select new be.vdab.valueobjects.VoornaamEnId(d.id, d.voornaam) from Docent d",VoornaamEnId.class).getResultList();
		}
	public BigDecimal findMaxWedde(){
		return getEntityManager().createQuery("select max(d.wedde) from Docent d", BigDecimal.class).getSingleResult();
	}
	public Iterable<AantalDocentenPerWedde> findAantalDocentenPerWedde(){
		return getEntityManager().createQuery("select new be.vdab.valueobjects.AantalDocentenPerWedde(d.wedde, count(d)) "
				+ "from Docent d group by d.wedde",AantalDocentenPerWedde.class).getResultList(); 
	}
	public void algemenOpslag(BigDecimal factor) {
		getEntityManager().createNamedQuery("Docent.algemeneOpslag").setParameter("factor", factor).executeUpdate();
	}
	
}
