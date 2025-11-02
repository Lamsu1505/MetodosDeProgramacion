package Voraces;
import java.util.*;

public class MochilaFraccionaria {

    public static class Item {
        int peso;
        double valor;
        int index;
        public Item(int peso, double valor) {
            this.peso = peso;
            this.valor = valor;
            this.index = -1;
        }
    }

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
            return String.format("Ítem %d | Peso: %d | Valor: %.2f | Fracción: %.2f | Valor obtenido: %.2f",
                    index + 1, peso, valor, fraccion, valorObtenido);
        }
    }

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
            for (SelectedItem s : seleccion)
                sb.append("  ").append(s).append("\n");
            return sb.toString();
        }
    }

    public static Result resolverMochila(Item[] items, int capacidad) {
        for (int i = 0; i < items.length; i++) items[i].index = i;
        Arrays.sort(items, (a, b) -> Double.compare(b.valor / b.peso, a.valor / a.peso));

        double valorTotal = 0;
        int pesoActual = 0;
        List<SelectedItem> seleccion = new ArrayList<>();

        for (Item it : items) {
            if (pesoActual >= capacidad) break;
            if (pesoActual + it.peso <= capacidad) {
                valorTotal += it.valor;
                pesoActual += it.peso;
                seleccion.add(new SelectedItem(it.index, it.peso, it.valor, 1.0, it.valor));
            } else {
                int restante = capacidad - pesoActual;
                double fraccion = ((double) restante) / it.peso;
                double valorObtenido = it.valor * fraccion;
                valorTotal += valorObtenido;
                seleccion.add(new SelectedItem(it.index, it.peso, it.valor, fraccion, valorObtenido));
                break;
            }
        }

        return new Result(valorTotal, seleccion);
    }
}
