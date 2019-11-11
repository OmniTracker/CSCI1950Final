package engine.ui;

public class EngineFonts {
	private static final String START = "Minisystem"; 
	private static final String ALC   = "Ethnocentric"; 
	private static final String WIZ   = "Unsteady Oversteer"; 
	private static final String TIC   = "Biohazard Participants"; 
	private static final String NIN   = "Quicksand Medium"; 
	private static final String FINAL = "AR PL KaitiM GB"; 
	public EngineFonts () {

	}
	@SuppressWarnings("static-access")
	public
	String getFontString (String name ) {
		if (name == "Tic") {
			return this.getTic();
		} else if (name == "Alc") {
			return this.getAlc();		
		}  else if (name == "Wiz") {
			return this.getWiz();		
		}  else if (name == "Nin") {
			return this.getNin();		
		}  else if (name == "Final") {
			return this.getFinal();		
		} 
		return "";
	}
	public static String getStart() {
		return START;
	}
	public static String getAlc() {
		return ALC;
	}
	public static String getWiz() {
		return WIZ;
	}
	public static String getTic() {
		return TIC;
	}
	public static String getNin() {
		return NIN;
	}
	public static String getFinal() {
		return FINAL;
	}
}
