package projlab;

public class Valto extends Sin {
	
	public void setSzomszedok(Sin sz0, Sin sz1, Sin sz2) {
		szomszedok[0] = sz0;
		szomszedok[1] = sz1;
		szomszedok[2] = sz2;
	}
	
	public void setAlagut() {}
	
	public void atallit(){
		Sin temp = szomszedok[0];
		szomszedok[0] = szomszedok[1];
		szomszedok[1] = szomszedok[2];
		szomszedok[2] = temp;		
	}
	
}