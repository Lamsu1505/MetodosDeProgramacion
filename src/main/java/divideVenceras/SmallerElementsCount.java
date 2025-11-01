package divideVenceras;

public class SmallerElementsCount {

    // Método principal: recibe el arreglo y devuelve otro con los conteos
    public static int[] contarMenores(int[] nums) {
        int n = nums.length;

        // Arreglo que almacenará los resultados finales (conteo de menores a la derecha)
        int[] conteos = new int[n];

        // Arreglo auxiliar que almacena los índices originales
        // (sirve para mantener la relación entre posiciones durante la mezcla)
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) indices[i] = i;

        // Llamada recursiva al método que aplica divide y vencerás
        dividir(nums, indices, conteos, 0, n - 1);

        return conteos;
    }

    // Método recursivo que divide el arreglo en mitades
    private static void dividir(int[] nums, int[] indices, int[] conteos, int izquierda, int derecha) {
        // Caso base: si el subarreglo tiene un solo elemento, no hay nada que dividir
        if (izquierda >= derecha) return;

        // Calcular el punto medio
        int medio = (izquierda + derecha) / 2;

        // Llamadas recursivas para cada mitad
        dividir(nums, indices, conteos, izquierda, medio);
        dividir(nums, indices, conteos, medio + 1, derecha);

        // Combinar los resultados de ambas mitades contando los menores a la derecha
        mezclarYContar(nums, indices, conteos, izquierda, medio, derecha);
    }

    // Método que mezcla las mitades y cuenta cuántos elementos de la derecha son menores
    private static void mezclarYContar(int[] nums, int[] indices, int[] conteos, int izquierda, int medio, int derecha) {
        // Arreglo temporal para almacenar el orden de índices durante la mezcla
        int[] temporal = new int[derecha - izquierda + 1];

        int i = izquierda;     // puntero para la mitad izquierda
        int j = medio + 1;     // puntero para la mitad derecha
        int k = 0;             // posición en el arreglo temporal
        int menoresDerecha = 0; // contador de elementos menores en la mitad derecha

        while (i <= medio && j <= derecha) {
            // Si el elemento de la derecha es menor al de la izquierda
            if (nums[indices[j]] < nums[indices[i]]) {
                menoresDerecha++;
                temporal[k++] = indices[j++];
            } else {
                conteos[indices[i]] += menoresDerecha;
                temporal[k++] = indices[i++];
            }
        }

        while (i <= medio) {
            conteos[indices[i]] += menoresDerecha;
            temporal[k++] = indices[i++];
        }


        while (j <= derecha) {
            temporal[k++] = indices[j++];
        }

        // Copiar de vuelta los índices ordenados al arreglo original
        for (int t = 0; t < temporal.length; t++) {
            indices[izquierda + t] = temporal[t];
        }
    }


}
