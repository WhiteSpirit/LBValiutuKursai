package lt.balt.valiutukursai;

import javax.xml.bind.annotation.XmlElement;

class IrasasFiksuotaiDatai {

	private String valiutosPavadinimas;
	private String valiutosKodas;
	private String data;
	private String santykis;

	String getValiutosPavadinimas() {
		return valiutosPavadinimas;
	}

	@XmlElement(name = "pavadinimas")
	void setValiutosPavadinimas(String valiutosPavadinimas) {
		this.valiutosPavadinimas = valiutosPavadinimas;
	}

	String getValiutosKodas() {
		return valiutosKodas;
	}

	@XmlElement(name = "valiutos_kodas")
	void setValiutosKodas(String valiutosKodas) {
		this.valiutosKodas = valiutosKodas;
	}

	String getData() {
		return data;
	}

	@XmlElement(name = "data")
	void setData(String data) {
		this.data = data;
	}

	String getSantykis() {
		return santykis;
	}

	@XmlElement(name = "santykis")
	void setSantykis(String santykis) {
		this.santykis = santykis;
	}

	@Override
	public String toString() {
		String eilute = new String(data + "\t" + santykis + "\t\t" + valiutosPavadinimas + " (" + valiutosKodas + ")");
		return eilute;
	}

}
