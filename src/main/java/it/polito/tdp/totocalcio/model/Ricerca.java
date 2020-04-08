package it.polito.tdp.totocalcio.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	
	private Pronostico pronostico;
	private int N;
	private List<Risultato> soluzione;

	/*
	 * METODO RICORSIVO: 
	 *  Livello della ricorsione indica il numero di partite che sto considerando
	 *  - le partite da-a livello-1 sono già state decise;
	 *  - la partita di indice livello la devo decidere io;
	 *  - le partite da livello+1 in poi le decideranno le prcedure ricorsive sottostanti.
	 *  Impostazione:
	 * 
	 *  Soluzione parziale: un elenco di RisultatoPartita di lunghezza pari al livello.
	 *  
	 *  Soluzione totale: ho N risultati.
	 *  
	 *  Condizione di terminazione: livello == N.
	 *  
	 *  Generazione delle soluzioni del livello: provando tutti i pronostici definiti da quel livello.
	 */
	
	// 1. Funzione di interfaccia: dammi il problema e ti restituisco la soluzione
	public List<Risultato> cerca(Pronostico pronostico) {
		
		// 1.1 prepara la struttura dati
		this.pronostico = pronostico;
		this.N = pronostico.size();		
		
		List<RisultatoPartita> parziale = new ArrayList<>();
		int livello = 0;
		
		this.soluzione = new ArrayList<Risultato>();
		
		// 1.2 chiamata alla funzione ricorsiva
		ricorsiva(parziale, livello);
		
		// 1.3 quando la funzione esce dalla ricorsione ho la soluzione pronta!
		return this.soluzione; 
		
	}
	
	// 2. Funziona ricorsiva privata, riceve come parametri la soluzione parziale (inizialmente sarà vuoto) e
	// 	  il livello a cui deve operare (inizialmente sarà 0)
	private void ricorsiva(List<RisultatoPartita> parziale, int livello) {
		
		// 2.1 caso terminale?
		if(livello == N) {
			// questa soluzione parziale è in realtà quella finale, perchè livello == N
			this.soluzione.add(new Risultato(parziale));
		} else {
			// 2.2 Caso generale
			
			// 2.2.1 [parizale da 0 a livello-1] [livello] [livello+1 in poi]
			PronosticoPartita pp = pronostico.get(livello);
			// pp sono i sotto problemi da provare
			
			// 2.2.2 Scorro i risultati
			for(RisultatoPartita ris : pp.getRisultati()) {
				// provo a mettere 'ris' nella posizione 'livello' della soluzione parziale
				
				// 2.2.3 Costruzione della soluzione parziale (sottoproblema)
				parziale.add(ris);
				// 2.2.4 Chiamata ricorsiva!
				ricorsiva(parziale, livello+1);
				// 2.2.5 Backtracking: l'ultimo che ho messo lo tolgo, per far procedere la ricorsione
				parziale.remove(parziale.size()-1);
			}
		}
		
	}

}
