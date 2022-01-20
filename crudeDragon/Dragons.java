package crudeDragon;


public class Dragons {
	private int id_Dragon;
	private String dragon;
	private char sexe;
	private int longueur;
	private int nombreEcailles;
	private boolean cracheDuFeu;
	private String comportementAmoureux;
	
	
	public int getId_Dragon() {
		return id_Dragon;
	}
	public void setId_Dragon(int id_Dragon) {
		this.id_Dragon = id_Dragon;
	}
	
	
	public String getDragon() {
		return dragon;
	}
	public void setDragon(String dragon) {
		this.dragon = dragon;
	}
	
	
	public char getSexe() {
		return sexe;
	}
	public void setSexe(char sexe) {
		this.sexe = sexe;
	}
	
	
	public int getLongueur() {
		return longueur;
	}
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	
	
	public int getNombreEcailles() {
		return nombreEcailles;
	}
	public void setNombreEcailles(int nombreEcailles) {
		this.nombreEcailles = nombreEcailles;
	}
	
	
	public boolean isCracheDuFeu() {
		return cracheDuFeu;
	}
	public void setCracheDuFeu(boolean cracheDuFeu) {
		this.cracheDuFeu = cracheDuFeu;
	}
	
	
	public String getComportementAmoureux() {
		return comportementAmoureux;
	}
	public void setComportementAmoureux(String comportementAmoureux) {
		this.comportementAmoureux = comportementAmoureux;
	}
	
	public Dragons(int Id_Dragon, String dragon, char sexe, int nombreEcailles, Boolean cracheDuFeu, String comportementAmoureux) {
		this.setId_Dragon(Id_Dragon);
		this.setDragon(dragon);
		this.setSexe(sexe);
		this.setNombreEcailles(nombreEcailles);
		this.setCracheDuFeu(cracheDuFeu);
		this.setComportementAmoureux(comportementAmoureux);
	}
	
	@Override
	public String toString() {
		return "Dragons [getId_Dragon()=" + getId_Dragon() + ", getDragon()=" + getDragon() + ", getSexe()=" + getSexe()
				+ ", getLongueur()=" + getLongueur() + ", getNombreEcailles()=" + getNombreEcailles()
				+ ", isCracheDuFeu()=" + isCracheDuFeu() + ", getComportementAmoureux()=" + getComportementAmoureux()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	

}
