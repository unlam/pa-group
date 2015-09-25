package test;

import java.util.Arrays;

import model.elementales.Burbuja;
import model.elementales.Insercion;
import model.elementales.Seleccion;
import model.noelementales.Fusion;
import model.noelementales.QuickSort;
import model.noelementales.Shell;

import org.junit.Assert;
import org.junit.Test;

public class OrdenadoraPalabrasYNumeros {
	
	private final static String[] arrayIn = {"hola","5","perro","chau","2"};
	private final static String[] arraySorted = {"2", "5", "chau", "hola", "perro"};
	
    @Test
    public void insersion() {
        String[] elements = arrayIn;
        new Insercion().ordenar(elements);
        Assert.assertArrayEquals(arraySorted, elements);
    }

    @Test
    public void insersionConCentinla() {
        String[] elements = arrayIn;
        new Insercion().ordenarConCentinela(elements);
        Assert.assertArrayEquals(arraySorted, elements);
    }
    
    @Test
    public void seleccion() {
        String[] elements = arrayIn;
        new Seleccion().ordenar(elements);
        Assert.assertArrayEquals(arraySorted, elements);
    }

    @Test
    public void burbuja() {
        String[] elements = arrayIn;
        new Burbuja().ordenar(elements);
        Assert.assertArrayEquals(arraySorted, elements);
    }
    
    @Test
    public void shell() {
        String[] elements = arrayIn;
        new Shell().ordenar(elements);
        Assert.assertArrayEquals(arraySorted, elements);
    }

    @Test
    public void quicksort() {
        String[] elements = arrayIn;
        new QuickSort().ordenar(elements);
        Assert.assertArrayEquals(arraySorted, elements);
        
    }

    @Test
    public void fusion() {
        String[] elements = arrayIn;
        new Fusion().ordenar(elements);
        Arrays.toString(elements);
        Assert.assertArrayEquals(arraySorted, elements);
    }
}