package Voraces;
import java.util.*;

public class MochilaFraccionaria {

    public static class Item {
        int peso;
        double valor;
        int index; // identificador
        public Item(int peso, double valor) {
            this.peso = peso;
            this.valor = valor;
            this.index = -1; // se asignará más tarde
        }
    }

    // Clase para describir un ítem seleccionado y la fracción tomada
    public static class SelectedItem {
        public int index;
        public int peso;
        public double valor;
        public double fraccion;
        public double valorObtenido;

        public SelectedItem(int index, int peso, double valor, double fraccion, double valorObtenido) {
            this.index = index;
            this.peso = peso;
            this.valor = valor;
            this.fraccion = fraccion;
            this.valorObtenido = valorObtenido;
        }

        @Override
        public String toString() {
            return String.format("Ítem %d | peso: %d | valor: %.2f | fracción: %.2f | valor obtenido: %.2f",
                    index + 1, peso, valor, fraccion, valorObtenido);
        }
    }

    // Resultado con valor total y lista de ítems seleccionados
    public static class Result {
        public double valorTotal;
        public List<SelectedItem> seleccion;

        public Result(double valorTotal, List<SelectedItem> seleccion) {
            this.valorTotal = valorTotal;
            this.seleccion = seleccion;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Valor total obtenido: %.2f\n", valorTotal));
            sb.append("Ítems seleccionados:\n");
            for (SelectedItem s : seleccion) {
                sb.append("  ").append(s.toString()).append("\n");
            }
            return sb.toString();
        }
    }

    public static Result resolverMochila(Item[] items, int capacidad) {
        // asignar índices para que se impriman correctamente
        for (int i = 0; i < items.length; i++) {
            items[i].index = i;
        }

        // ordenar por valor/peso descendente
        Arrays.sort(items, (a, b) -> Double.compare(b.valor / b.peso, a.valor / a.peso));

        double valorTotal = 0;
        int pesoActual = 0;
        List<SelectedItem> seleccion = new ArrayList<>();

        for (Item it : items) {
            if (pesoActual >= capacidad) break;
            if (pesoActual + it.peso <= capacidad) {
                // tomar ítem completo
                valorTotal += it.valor;
                pesoActual += it.peso;
                seleccion.add(new SelectedItem(it.index, it.peso, it.valor, 1.0, it.valor));
            } else {
                // tomar una fracción del ítem
                int restante = capacidad - pesoActual;
                double fraccion = ((double) restante) / it.peso;
                double valorObtenido = it.valor * fraccion;
                valorTotal += valorObtenido;
                pesoActual += restante;
                seleccion.add(new SelectedItem(it.index, it.peso, it.valor, fraccion, valorObtenido));
                break;
            }
        }

        return new Result(valorTotal, seleccion);
    }
}
