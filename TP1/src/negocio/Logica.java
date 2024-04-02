package negocio;


import java.util.Random;
import java.util.random.*;

public class Logica {
	
	private int[][] tablero;
	
	public static final int FIL = 4;// Defino una constante
	public static final int COL = 4;// Defino una constante
	private int puntaje;
	
	public Logica() {
		tablero = new int[4][4];
		puntaje = 0;
		for(int FIL=0;FIL<4;FIL++) {
			for(int COL=0; COL<4;COL++) {
				tablero[FIL][COL] = 0;
			}
			
		}
		
		ponerDos();
		ponerDos();

	}
	
	private int vaciasEnFila(int f) {
		int n=0;
		for(int c=0;c<COL;c++) {
			if(tablero[f][c]==0) {
				n++;
			}
		}
		return n;
	}
	
	private int vaciasEnColumna(int c) {
		int n=0;
		for(int f=0;f<FIL;f++) {
			if(tablero[f][c]==0) {
				n++;
			}
		}
		return n;
	}
	
	public int vacias() {
		int n=0;
		for(int f=0;f<FIL;f++) {
			for(int c=0;c<COL;c++) {
				if(tablero[f][c]==0)
					n++;
			}
		}
		return n;
	}
	
	private void ponerDos() {
		int f;
		int c;
		Random random = new Random();
		
		do {
			f = random.nextInt(FIL);
		} while(vaciasEnFila(f) == 0);
		
		do {
			c = random.nextInt(COL);
		} while(tablero[f][c]!= 0);
		
		tablero[f][c] = 2;
		
	}
	
	//Método mostrar
	
	public void mostrar() {
		for(int FIL=0;FIL<4;FIL++) {
			System.out.print("|");
			for(int COL=0; COL<4;COL++) {
				if(tablero[FIL][COL]==0) {
					System.out.print(" "+"|");
				}else {
					System.out.print(tablero[FIL][COL]+"|");
				}
			}
			System.out.print("\n");
		}
	}
	
	public boolean ganador() {
		for(int f=0;f<FIL;f++) {
			for(int c=0;c<COL;c++) {
				if(tablero[f][c] == 2048) {
					return true;
				}
			}	
		}
		return false;
	}
	
	public boolean finPartida() {
		return (ganador() || vacias()==0);
	}

	
	public void moverArriba() { //Mueve todas las columnas arriba
		for(int c=0; c<COL;c++) {
			moverArribaColumna(c);
		}
		if(!finPartida()) {
			ponerDos();
		}
		
	}
	
	public void moverArribaColumna(int c) {
		colocarArriba(c);
		sumarArriba(c);
		colocarArriba(c);
	}
	
	public void colocarArriba(int c) {
		if(vaciasEnColumna(c) < COL ) {
			for(int veces = 0; veces < COL-1; veces++) {
				for(int f=0; f<FIL-1;f++) {
					if(tablero[f][c]==0) {
						tablero[f][c] = tablero[f+1][c];
						tablero[f+1][c] = 0;
					}
					
				}
			}
		}
	}
	
	public void sumarArriba(int c) {
		if(vaciasEnColumna(c)<3) {
			for(int f=0;f<FIL-1;f++) {
				if(tablero[f][c] == tablero[f+1][c]) {
					tablero[f][c] *= 2;
					tablero[f+1][c] = 0;
					puntaje += tablero[f][c];
				}
			}
		}
	}
	
	// Mover abajo
	
	public void moverAbajo() { //Mueve todas las columnas arriba
		for(int c=0; c<COL;c++) {
			moverAbajoColumna(c);
		}
		if(!finPartida()) {
			ponerDos();
		}
		
	}
	
	public void moverAbajoColumna(int c) {
		colocarAbajo(c);
		sumarAbajo(c);
		colocarAbajo(c);
	}
	
	public void colocarAbajo(int c) {
		if(vaciasEnColumna(c) < COL ) {
			for(int veces = 0; veces < COL-1; veces++) {
				for(int f=FIL-1; f>0;f--) {
					if(tablero[f][c]==0) {
						tablero[f][c] = tablero[f-1][c];
						tablero[f-1][c] = 0;
					}
					
				}
			}
		}
	}
	
	public void sumarAbajo(int c) {
		if(vaciasEnColumna(c)<FIL-1) {
			for(int f=FIL-1;f>0;f--) {
				if(tablero[f][c] == tablero[f-1][c]) {
					tablero[f][c] *= 2;
					tablero[f-1][c] = 0;
					puntaje += tablero[f][c];
				}
			}
		}
	}
	
	// Mover Derecha 
	
    public void moverDerecha() {
        for(int f=0; f<COL; f++) {
            moverDerechaFila(f);
        }
        if(!finPartida())
                ponerDos();
    }
    
    private void moverDerechaFila(int f) {
        colocarDerecha(f);
        sumarDerecha(f);
        colocarDerecha(f);
    }
    
    private void colocarDerecha(int f) {
        
        if(vaciasEnFila(f) < FIL) {
            for(int veces = 0;veces < COL-1;veces++) {
                for(int c=COL-1;c>0;c--) {
                    if(tablero[f][c]==0) {
                        tablero[f][c] = tablero[f][c-1];
                        tablero[f][c-1] = 0;
                    }
                }
            }
        }
        
    }
    
    
    private void sumarDerecha(int f) {
        if(vaciasEnFila(f)<FIL-1) {
            for(int c=COL-1;c>0;c--) {
                if(tablero[f][c] == tablero[f][c-1]) {
                    tablero[f][c] *= 2;
                    tablero[f][c-1] = 0;
                    puntaje += tablero[f][c];
                }
            }
        }
    }
    
    //Mover Izquierda
    
    public void moverIzquierda() {
        for(int f=0; f<FIL; f++) {
            moverIzquierdaFila(f);
        }
        if(!finPartida())
                ponerDos();
    }
    
    private void moverIzquierdaFila(int f) {
        colocarIzquierda(f);
        sumarIzquierda(f);
        colocarIzquierda(f);
    }
    
    private void colocarIzquierda(int f) {
        
        if(vaciasEnFila(f) < FIL) {
            for(int veces = 0;veces < COL-1;veces++) {
                for(int c=0;c<COL-1;c++) {
                    if(tablero[f][c]==0) {
                        tablero[f][c] = tablero[f][c+1];
                        tablero[f][c+1] = 0;
                    }
                }
            }
        }
        
    }
    
    
    private void sumarIzquierda(int f) {
        if(vaciasEnFila(f)<FIL-1) {
            for(int c=0;c<COL-1;c++) {
                if(tablero[f][c] == tablero[f][c+1]) {
                    tablero[f][c] *= 2;
                    tablero[f][c+1] = 0;
                    puntaje += tablero[f][c];
                }
            }
        }
    }
    
    
    // Método para obtener la matriz del tablero
    public int[][] obtenerTablero() {
        return tablero;
    }
    
    public int obtenerPuntaje() {
    	return puntaje;
    }
}