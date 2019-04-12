package lt.balt.valiutukursai;

import java.util.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "items")
class IrasaiFiksuotaiDatai {

	private List<IrasasFiksuotaiDatai> irasai = new ArrayList<IrasasFiksuotaiDatai>();

	IrasaiFiksuotaiDatai() {
	}

	IrasaiFiksuotaiDatai(List<IrasasFiksuotaiDatai> irasai) {
		this.irasai = irasai;
	}

	@XmlElement(name = "item")
	List<IrasasFiksuotaiDatai> getIrasai() {
		return irasai;
	}

	void setIrasai(List<IrasasFiksuotaiDatai> irasai) {
		this.irasai = irasai;
	}

	@Override
	public String toString() {
		String irasuVisuma = new String("");
		for (IrasasFiksuotaiDatai irasas : irasai) {
			irasuVisuma = irasuVisuma + irasas + "\n";
		}
		return irasuVisuma;
	}
	
	double skaiciuotiIrRodytiValiutosKursoPokyti() {
		double pokytis = 0;
		if(irasai.size()>1) {
		double valiutosKursasPabaigoje = Double.parseDouble(irasai.get(1).getSantykis().replace(',','.'));
		double valiutosKursasPradzioje = Double.parseDouble(irasai.get(irasai.size()-1).getSantykis().replace(',','.'));
		pokytis = valiutosKursasPabaigoje/valiutosKursasPradzioje - 1;
		System.out.println(irasai.get(1).getValiutosKodas() + " kurso pokytis per nurodytą laikotarpį: " + pokytis*100 + " proc.");
		}
		return pokytis;
		}
	
	String spausdintiValiutuPavadinimusIrKodus() {
		String sarasas = "";
		return sarasas;
		}

}
