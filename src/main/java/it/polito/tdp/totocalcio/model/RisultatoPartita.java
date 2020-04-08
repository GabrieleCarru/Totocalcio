package it.polito.tdp.totocalcio.model;

// classe ENUM perchè può assumere solo 3 valori: '1', '2' o 'X'
public enum RisultatoPartita {
	UNO, DUE, ICS ;
	
	public static RisultatoPartita valueOf(char c) {
		switch(c) {
		case '1':
			return RisultatoPartita.UNO ;
		case '2':
			return RisultatoPartita.DUE ;
		case 'X':
		case 'x':
			return RisultatoPartita.ICS ;
		default:
			throw new IllegalArgumentException("Illegal character "+c+" in string") ;				
		}
	}
	
	public String toString() {
		switch(this) {
		case UNO:
			return "1" ;
		case DUE:
			return "2" ;
		case ICS:
			return "X" ;
		}
		return null ;
	}
	
	
}
