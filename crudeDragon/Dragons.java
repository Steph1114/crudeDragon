package crudeDragon;


public class Dragons {
	private int id_Dragon;
	private String dragon;
	private String sexe;
	private int longueur;
	private int nombreEcailles;
	private boolean cracheDuFeu;
	private String comportementAmoureux;
	
	
	
	/**
	 * Getter et setter 
	 * @return param / actions
	 * */
	
	public int getId_Dragon() {
		return id_Dragon;
	}
	public void setId_Dragon(int id_Dragon) {
		this.id_Dragon = id_Dragon;
	}
	
	
	public String getDragon() {
		return dragon;
	}
	public void setDragon(String n) {
		dragon = n.toUpperCase();
	}
	
	
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
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
	
	/**
	 * Constructor TamagotchiV2 
	 * @param id_Dragon
	 * @param dragon
	 * @param sexe
	 * @param longueur
	 * @param nombreEcailles
	 * @param cracheDuFeu 
	 * @param comportementAmoureux
	 */
	public Dragons(int id_Dragon, String dragon, String sexe, int longueur, int nombreEcailles, Boolean cracheDuFeu, String comportementAmoureux) {
		this.setId_Dragon(id_Dragon);
		this.setDragon(dragon);
		this.setSexe(sexe);
		this.setLongueur(longueur);
		this.setNombreEcailles(nombreEcailles);
		this.setCracheDuFeu(cracheDuFeu);
		this.setComportementAmoureux(comportementAmoureux);
	}
	
	public Dragons() {
		// TODO Auto-generated constructor stub
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
