package lt.balt.valiutukursai;

import java.time.LocalDate;
import java.util.Scanner;

public class Uzklausa {

	private String valiutosKodas = "";
	private String dataNuo = LocalDate.now().toString();
	private String dataIki = LocalDate.now().toString();
	private String url = "";
	
	private Scanner scanner = new Scanner(System.in);
	
	Uzklausa() {
		this.valiutosKodas = gautiValiutosKoda();
		gautiVisasDatas();
		setUrl();
	}
	
	Uzklausa(String url) {
		this.url = url;
	}
		
	private String gautiValiutosKoda() {
		System.out.println("\nJeigu norėtumėte informacijos apie visas valiutas, prašome įvesti žodį: VISOS.\n"
				+ "Jeigu norėtumėte informacijos apie vieną valiutą, prašome įvesti trijų raidžių valiutos kodą.");
		valiutosKodas = scanner.next();
		valiutosKodas = valiutosKodas.trim().toUpperCase();
		if ("VISOS".equals(valiutosKodas)) {valiutosKodas = "";}
		if (!"".equals(valiutosKodas) && (valiutosKodas.length() < 3 || valiutosKodas.length() > 3)) {
			System.out.println("Klaidingai įvestas valiutos kodas.");
			gautiValiutosKoda();
		}
		return valiutosKodas;
	}
	
	private String gautiData() {
		String data = scanner.next();
		return data.trim();
	}
	
	private void gautiVisasDatas() {
		if ("".equals(valiutosKodas)) {
			System.out.println("\nPrašome įvesti norimą datą. Datos formatas: yyyy-MM-dd.");
			dataNuo = gautiData();
			dataIki = dataNuo;
			} else {
				System.out.println("\nPrašome įvesti laikotarpio pradžios datą (nuo kada?). Datos formatas: yyyy-MM-dd.");
				dataNuo = gautiData();
				System.out.println("\nPrašome įvesti laikotarpio pabaigos datą (iki kada?). Datos formatas: yyyy-MM-dd.");
				dataIki = gautiData();
			}
	}
	
	void vykdytiUzklausa() {
		XmlKlase.atsisiustiXmlFaila(this);
		XmlKlase.gautiIrAtspausdintiIrasusIsXml(this);
	}

	String getValiutosKodas() {
		return valiutosKodas;
	}

	void setValiutosKodas(String valiutosKodas) {
		this.valiutosKodas = valiutosKodas;
	}

	String getDataNuo() {
		return dataNuo;
	}

	void setDataNuo(String dataNuo) {
		this.dataNuo = dataNuo;
	}

	String getDataIki() {
		return dataIki;
	}

	void setDataIki(String dataIki) {
		this.dataIki = dataIki;
	}

	String getUrl() {
		return url;
	}

	void setUrl(String url) {
		this.url = url;
	}
	
	private void setUrl() {
		String url = new String("https://www.lb.lt/lt/currency/");
		if ("".equals(valiutosKodas)) {
			url = url + "daylyexport/?xml=1&class=Eu&type=day&date_day=" + dataNuo;
		} else {
			url = url + "exportlist/?xml=1&currency=" + valiutosKodas + "&ff=1&class=Eu&type=day&date_from_day=" + dataNuo + "&date_to_day=" + dataIki;
		}
		setUrl(url);
	}
	
}
