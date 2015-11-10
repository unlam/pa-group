package model;

public class Generador extends Grafo{
	
	public Generador(){}

	private int buscarGradoMax() {
		int grado, aux;

		grado = 0;
		for (int i = 0; i < cantidadNodos; i++) {
			aux = 0;
			for (int j = 0; j < cantidadNodos; j++)
				if (matrizAdyacencia.getValueVector(i, j) != inf)
					aux++;
			if (aux > grado)
				grado = aux;
		}
		return grado;
	}

	private int buscarGradoMin() {
		int grado, aux;

		grado = inf;
		for (int i = 0; i < cantidadNodos; i++) {
			aux = 0;
			for (int j = 0; j < cantidadNodos; j++)
				if (matrizAdyacencia.getValueVector(i, j) != inf)
					aux++;
			if (aux < grado)
				grado = aux;
		}
		return grado;
	}
	
	public void grafoDadoNYProbArista(int cantidad, double prob) { 
		// Prob va de 0 a 1.
		cantidadNodos = cantidad;
		cantidadAristas = 0;
		matrizAdyacencia = new MatrizSimetrica(cantidadNodos);
		
		for (int i = 0; i < cantidadNodos; i++){
			for (int j = i; j < cantidadNodos; j++) {
				matrizAdyacencia.setValueVector(i, j, inf);
				matrizAdyacencia.setValueVector(j, i, inf);
			}			
		}
		
		int costo = inf;
		double rand;
		for (int i = 0; i < cantidadNodos; i++){
			for (int j = i + 1; j < cantidadNodos; j++) {
				rand = Math.random();
				if (rand < prob) {
					costo = (int) (Math.random() * 100);
					matrizAdyacencia.setValueVector(i, j, costo);
					matrizAdyacencia.setValueVector(j, i, costo);
					cantidadAristas++;
				}
			}	
		}
			
		int aristasMax = (int) ((cantidadNodos * (cantidadNodos - 1)) / 2);
		porcentajeAdyacencia = (cantidadAristas * 100) / aristasMax;
		maximoGrado = buscarGradoMax();
		minimoGrado = buscarGradoMin();
	}
	
	public void grafoDadoNYPorcentajeAdy(int cantidad, int porcentaje) {
		cantidadNodos = cantidad;
		porcentajeAdyacencia = porcentaje;
		cantidadAristas = (int) ((cantidadNodos * (cantidadNodos - 1) / 2) * (porcentajeAdyacencia / 100));
		matrizAdyacencia = new MatrizSimetrica(cantidadNodos);

		//inicializo la matriz en 0
		for (int i = 0; i < cantidadNodos; i++){
			for (int j = i; j < cantidadNodos; j++) {
				matrizAdyacencia.setValueVector(i, j, 0);
				matrizAdyacencia.setValueVector(j, i, 0);
			}
		}
		
		int aristasAux = cantidadAristas;
		int filas, columnas, rand;

		while (aristasAux > 0) { // hacer dos for y recorrer matriz insertando
									// solo el valor rand
			filas = (int) (Math.random() * cantidadNodos);
			columnas = (int) (Math.random() * cantidadNodos);
			rand = (int) (Math.random() * 100);

			if (filas != columnas && matrizAdyacencia.getValueVector(filas, columnas) == 0) {
				matrizAdyacencia.setValueVector(filas, columnas, 1);
				matrizAdyacencia.setValueVector(columnas, filas, 1);
				aristasAux--;
			}
		}
		
		maximoGrado = buscarGradoMax();
		minimoGrado = buscarGradoMin();
	}
	
	public void grafoRegularDadoGradoYN(int cantidad, int grado) {

		cantidadNodos = cantidad;
		porcentajeAdyacencia = ((int) grado / (cantidadNodos - 1)) * 100;
		cantidadAristas = (cantidadNodos * grado) / 2;
		matrizAdyacencia = null;

		if ((cantidadNodos % 2) == 0 || (grado % 2) == 0) {
			matrizAdyacencia = new MatrizSimetrica(cantidadNodos);

			for (int i = 0; i < cantidadNodos; i++)
				for (int j = i; j < cantidadNodos; j++) {
					matrizAdyacencia.setValueVector(i, j, inf);
					matrizAdyacencia.setValueVector(j, i, inf);
				}

			int j, rand = inf;

			int saltoMax = (grado / 2) - 1;
			for (int salto = 0; salto <= saltoMax; salto++) {
				for (int i = 0; i < cantidadNodos; i++) {
					j = (i + 1 + salto) % cantidadNodos;
					rand = (int) ((Math.random() * 99) + 1);
					matrizAdyacencia.setValueVector(i, j, rand);
					matrizAdyacencia.setValueVector(j, i, rand);
				}
			}

			if ((grado % 2) != 0) {
				for (int i = 0; i < cantidadNodos / 2; i++) {
					j = i + cantidadNodos / 2;
					matrizAdyacencia.setValueVector(i, j, 1);
					matrizAdyacencia.setValueVector(j, i, 1);
				}
			}
		}

		maximoGrado = grado;
		minimoGrado = grado;

		if (matrizAdyacencia == null) {
			System.out
					.println("No se puede generar un grafo regular con esas caracteristicas");
			cantidadAristas = 0;
		}
	}

	public void grafoRegularDadoPorcYN(int cantidad, int porcentaje) {
		cantidadNodos = cantidad;
		porcentajeAdyacencia = porcentaje;

		int grado = (int) ((porcentajeAdyacencia * (cantidadNodos - 1)) / 100);

		maximoGrado = grado;
		minimoGrado = grado;

		cantidadNodos = cantidad;
		cantidadAristas = (cantidadNodos * grado) / 2;
		matrizAdyacencia = null;

		if ((cantidadNodos % 2) == 0 || (grado % 2) == 0) {
			matrizAdyacencia = new MatrizSimetrica(cantidadNodos);

			for (int i = 0; i < cantidadNodos; i++)
				for (int j = i; j < cantidadNodos; j++) {
					matrizAdyacencia.setValueVector(i, j, inf);
					matrizAdyacencia.setValueVector(j, i, inf);
				}

			int j, rand = inf;

			int saltoMax = (grado / 2) - 1;
			for (int salto = 0; salto <= saltoMax; salto++) {
				for (int i = 0; i < cantidadNodos; i++) {
					j = (i + 1 + salto) % cantidadNodos;
					rand = (int) ((Math.random() * 99) + 1);
					matrizAdyacencia.setValueVector(i, j, rand);
					matrizAdyacencia.setValueVector(j, i, rand);
				}
			}

			if ((grado % 2) != 0) {
				for (int i = 0; i < cantidadNodos / 2; i++) {
					j = i + cantidadNodos / 2;
					rand = (int) ((Math.random() * 99) + 1);
					matrizAdyacencia.setValueVector(i, j, rand);
					matrizAdyacencia.setValueVector(j, i, rand);
				}
			}
		}

		if (matrizAdyacencia == null) {
			System.out
					.println("No se puede generar un grafo regular con esas caracteristicas");
			cantidadAristas = 0;
		}
	}

	public void grafoNPartito(int cantidad, int n) {

		cantidadNodos = cantidad;
		matrizAdyacencia = new MatrizSimetrica(cantidadNodos);
		cantidadAristas = 0;

		for (int i = 0; i < cantidadNodos; i++)
			for (int j = i; j < cantidadNodos; j++) {
				matrizAdyacencia.setValueVector(i, j, inf);
				matrizAdyacencia.setValueVector(j, i, inf);
			}

		int[] cantidades = new int[n];

		cantidades = generarTamanioSubGrafos(cantidadNodos, n);

		int[] posiciones = new int[n + 1];
		posiciones[0] = 0;

		for (int i = 1; i < n + 1; i++)
			posiciones[i] = posiciones[i - 1] + cantidades[i - 1];

		int rand = inf;

		for (int i = 0; i < n; i++) {
			for (int j = posiciones[i]; j < posiciones[i + 1]; j++) {
				for (int k = j; k < posiciones[i + 1]; k++) {

					if (Math.random() < 0.7) {
						rand = (int) ((Math.random() * 99) + 1);
						matrizAdyacencia.setValueVector(j, k, rand);
						matrizAdyacencia.setValueVector(k, j, rand);
						cantidadAristas++;
					}
				}
			}
		}
		int aristasMax = (int) ((cantidadNodos * (cantidadNodos - 1)) / 2);
		porcentajeAdyacencia = (cantidadAristas * 100) / aristasMax;

		maximoGrado = buscarGradoMax();
		minimoGrado = buscarGradoMin();
	}
	
	private int[] generarTamanioSubGrafos(int cantNodos, int n) {

		int[] cantidades = new int[n];
		int auxNodos = cantNodos;

		for (int i = 0; i < n - 1; i++) {
			cantidades[i] = (int) (((Math.random() + 0.5) * auxNodos) / 6);
			auxNodos -= cantidades[i];
		}

		cantidades[n - 1] = auxNodos;

		int suma = 0;

		for (int i = 0; i < n; i++) {
			System.out.print(cantidades[i] + " ");
			suma += cantidades[i];
		}

		System.out.println(suma);

		return cantidades;
	}
}
