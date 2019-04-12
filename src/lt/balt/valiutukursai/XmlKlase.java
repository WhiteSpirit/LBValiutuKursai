package lt.balt.valiutukursai;

import java.io.*;
import java.net.*;
import java.time.LocalDate;

import javax.xml.bind.*;

public class XmlKlase {

	private static File file = new File("currency.xml");
	
	static File atsisiustiXmlFaila(Uzklausa uzklausa) {
		try {
			URL url = new URL(uzklausa.getUrl());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("User-Agent", "");
			connection.setConnectTimeout(10000);
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer content = new StringBuffer("");
			String inputLine = "";
			while ((inputLine = in.readLine()) != null) {content.append(inputLine);}
			in.close();
			java.io.FileWriter fw = new java.io.FileWriter(file);
		    fw.write(content.toString());
		    fw.close();		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;	
	}
	
	static IrasaiFiksuotaiDatai gautiIrasusIsXml(Uzklausa uzklausa) {
		IrasaiFiksuotaiDatai irasai = new IrasaiFiksuotaiDatai();
		try {			
			JAXBContext jaxbContext = JAXBContext.newInstance(IrasaiFiksuotaiDatai.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			irasai = (IrasaiFiksuotaiDatai) jaxbUnmarshaller.unmarshal(file);
			}
		catch (JAXBException e) {
			e.printStackTrace();
		}
		return irasai;
	}
	
	static IrasaiFiksuotaiDatai gautiIrAtspausdintiIrasusIsXml(Uzklausa uzklausa) {
		IrasaiFiksuotaiDatai irasai = gautiIrasusIsXml(uzklausa);
		System.out.println("\n" + irasai);
		if (!"".equals(uzklausa.getValiutosKodas())) {
			irasai.skaiciuotiIrRodytiValiutosKursoPokyti();
		}
		return irasai;
	}
		
	static void spausdintiValiutuPavadinimusIrKodus() {
		Uzklausa naujausiuDuomenuUzklausa = new Uzklausa("https://www.lb.lt/lt/currency/daylyexport/?xml=1&class=Eu&type=day&date_day=" + LocalDate.now().toString());
		atsisiustiXmlFaila(naujausiuDuomenuUzklausa);
		IrasaiFiksuotaiDatai naujausiIrasai = gautiIrasusIsXml(naujausiuDuomenuUzklausa);
		System.out.println("Valiutos: ");
		for(IrasasFiksuotaiDatai irasas : naujausiIrasai.getIrasai()) {
			System.out.println(irasas.getValiutosPavadinimas() + " (" + irasas.getValiutosKodas() + ")\t");
		}
	}
	
}
