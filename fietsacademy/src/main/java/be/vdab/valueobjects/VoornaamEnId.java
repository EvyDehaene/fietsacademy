package be.vdab.valueobjects;

public class VoornaamEnId {
	private final long id;
	private final String voornaam;
	public long getId() {
		return id;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public VoornaamEnId(long id, String voornaam){
		this.id=id;
		this.voornaam=voornaam;
	}
}
