package menjacnica.gui;

import java.awt.EventQueue;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import menjacnica.Menjacnica;
import menjacnica.MenjacnicaInterface;
import menjacnica.Valuta;

public class GUIKontroler {
	public static MenjacnicaGUI menjacnicaGui;
	public static MenjacnicaInterface menjacnicaInterfejs;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menjacnicaGui = new MenjacnicaGUI();
					menjacnicaGui.setVisible(true);
					menjacnicaGui.setLocationRelativeTo(null);
					menjacnicaInterfejs = new Menjacnica();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void izadjiIzAplikacije() {
		int opcija = JOptionPane.showConfirmDialog(menjacnicaGui.getContentPane(), "Da li zelite da izadjete iz aplikacije?", "Izlazak iz aplikacije", JOptionPane.YES_NO_OPTION);
		if(opcija == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	public static void otvoriProzorDodajKursGUI() {
		DodajKursGUI prozor = new DodajKursGUI();
		prozor.setLocationRelativeTo(null);
		prozor.setVisible(true);
	}
	
	public static void unesiValutu(String naziv, String skraceniNaziv, int sifra, String prodajni, String srednji, String kupovni) {
		Valuta novaValuta = new Valuta();
		novaValuta.setNaziv(naziv);
		novaValuta.setSkraceniNaziv(skraceniNaziv);
		novaValuta.setSifra(sifra);
		novaValuta.setProdajni(Double.parseDouble(prodajni));
		novaValuta.setSrednji(Double.parseDouble(srednji));
		novaValuta.setKupovni(Double.parseDouble(kupovni));
		menjacnicaInterfejs.dodajValutu(novaValuta);
		menjacnicaGui.osveziTabelu();
	}
	
	public static LinkedList<Valuta> getKursnaLista() {
		return menjacnicaInterfejs.vratiKursnuListu();
	}
	
	public static void otvoriProzorObrisiKursGUI() {
		ObrisiKursGUI prozor = new ObrisiKursGUI();
		prozor.setLocationRelativeTo(null);
		prozor.setVisible(true);
	}
	
	public static Valuta prikazIzabraneValute() {
		return menjacnicaGui.vratiIzabranuValutu();
	}
	
	public static void obrisiValutu() {
		menjacnicaInterfejs.obrisiValutu(menjacnicaGui.vratiIzabranuValutu());
		menjacnicaGui.osveziTabelu();
	}
	
	public static void otvoriProzorIzvrsiZamenuGUI() {
		IzvrsiZamenuGUI prozor = new IzvrsiZamenuGUI();
		prozor.setLocationRelativeTo(null);
		prozor.setVisible(true);
	}
	
	public static void ucitajIzFajla() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnValuta = fc.showOpenDialog(menjacnicaGui.getContentPane());
			if(returnValuta == JFileChooser.APPROVE_OPTION) {
				File fajl = fc.getSelectedFile();
				menjacnicaInterfejs.ucitajIzFajla(fajl.getAbsolutePath());
				menjacnicaGui.osveziTabelu();
			}
		} catch(Exception e1) {
			JOptionPane.showMessageDialog(menjacnicaGui.getContentPane(), e1.getMessage(), "Greska!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void sacuvajUFajl() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnValuta = fc.showSaveDialog(menjacnicaGui.getContentPane());
			if(returnValuta == JFileChooser.APPROVE_OPTION) {
				File fajl = fc.getSelectedFile();
				menjacnicaInterfejs.sacuvajUFajl(fajl.getAbsolutePath());
			}
		} catch(Exception e1) {
			JOptionPane.showMessageDialog(menjacnicaGui.getContentPane(), e1.getMessage(), "Greska!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void prikazAbout() {
		JOptionPane.showMessageDialog(menjacnicaGui.getContentPane(), "Autor programa je Bojan Tomic, verzija programa je 1.0, fakultet: FON.", "Informacije", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static double izvrsiTransakciju(boolean prodaja, String broj) {
		Valuta novaValuta = menjacnicaGui.vratiIzabranuValutu();
		double iznos = Double.parseDouble(broj);
		return menjacnicaInterfejs.izvrsiTransakciju(novaValuta, prodaja, iznos);
	}
}
