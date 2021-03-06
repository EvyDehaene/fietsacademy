package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import be.vdab.dao.DocentDAO;
import be.vdab.entities.Docent;
import be.vdab.filters.JPAFilter;
import be.vdab.valueobjects.AantalDocentenPerWedde;
import be.vdab.valueobjects.VoornaamEnId;

public class DocentService {
	private final DocentDAO docentDAO = new DocentDAO();
	public Docent read(long id) {
		return docentDAO.read(id);
	}
	public void create (Docent docent){
		docentDAO.beginTransaction();
		docentDAO.create(docent);
		docentDAO.commit();
	}
	public void delete(long id){
		docentDAO.beginTransaction();
		docentDAO.delete(id);
		docentDAO.commit();
	}
	public void opslag (long id, BigDecimal percentage){
		docentDAO.beginTransaction();
		docentDAO.read(id).opslag(percentage);
		docentDAO.commit();
	}
	public List<Docent> findByWeddeBetween(BigDecimal van, BigDecimal tot, int vanafRij, int aantalRijen){
		return docentDAO.findByWeddeBetween(van, tot, vanafRij, aantalRijen);
	}
	public Iterable<VoornaamEnId> findVoornamen() {
		  return docentDAO.findVoornamen();
		}
	public BigDecimal findMaxWedde(){
		return docentDAO.findMaxWedde();
	}
	public Iterable<AantalDocentenPerWedde> findAantalDocentenPerWedde(){
		return docentDAO.findAantalDocentenPerWedde();
	}
	public void algemeneOpslag(BigDecimal percentage){
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(new BigDecimal(100)));
		docentDAO.beginTransaction();
		docentDAO.algemenOpslag(factor);
		docentDAO.commit();
	}
}
