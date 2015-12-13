import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;


public class Reklamacja extends Entity{

	int ileReklamacji;
	
	public int getIleReklamacji() {
		return ileReklamacji;
	}

	public void setIleReklamacji(int ileReklamacji) {
		this.ileReklamacji = ileReklamacji;
	}

	public Reklamacja(Model owner, String name, boolean showTrace) {
		super(owner, name, showTrace);
		
	}

}
