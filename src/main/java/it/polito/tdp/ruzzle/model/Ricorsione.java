package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricorsione {
	
	public List<Pos> trovaParola (String parola, Board board) {
		
		 for(Pos p : board.getPositions()) {
			 //if(lettera alla pos p è la prima lettera della parola)
			 if(board.getCellValueProperty(p).get().charAt(0) == parola.charAt(0)) {
				 //metto la prima parola nella soluz parziale e chiamo metodo ricorsivo
				 List<Pos>parziale = new ArrayList<>();
				 parziale.add(p);
				 if(cerca(parola, board, parziale, 1)) {//se la ricorsione da valore positivo vuol dire che ho finito la ricorsione
					 return parziale;
				 }
			 }
		 }
		 return null;
	}

	private boolean cerca(String parola, Board board, List<Pos> parziale, int livello) {
		if(livello == parola.length()) {//caso termnazione(A)
			return true;
		}
		//altrimenti si va avanti a costruire la soluzione parziale
		Pos ultima = parziale.get(parziale.size()-1);//prendo l'ultima lettera in parziale e cerco le sue adiacenti(c'è gia la funzione)
		List<Pos> adiacenti = board.getAdjacencies(ultima);
		for(Pos a : adiacenti) {
			if(!parziale.contains(a) && board.getCellValueProperty(a).get().charAt(0) == parola.charAt(livello)) {//qui verifico di non selezionare una lettera gia visitata e controllo una per una se tra le adiacenti c'è la successiva per la parola che sto cercando
				parziale.add(a);//B
				if(cerca(parola, board, parziale, livello+1)) {//C
					return true;
				}//D
				parziale.remove(parziale.size()-1);//parziale.size()-1 sarebbe l'ultima posizione occupata in parziale
			}
		}//E
		return false;
		
	}
}
