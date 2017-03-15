package projlab;

public class Valto extends Sin {
	
	Valto(Palya p, Sin sz0, Sin sz1,Sin sz2) {
		super(p, sz0, sz1);
		szomszedok[2] = sz2;
	}
	
	public void setAlagut() {}
	
	public void atallit(){
		for (int i = 0; i < 3; i++) {
			Sin temp = szomszedok[0];
			szomszedok[0] = szomszedok[1];
			szomszedok[1] = szomszedok[2];
			szomszedok[2] = temp;
		}			
	}
	
}