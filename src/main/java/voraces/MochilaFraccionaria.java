package voraces;

import java.util.*;

public class MochilaFraccionaria {
    public static class Item {
        int w; double v;
        public Item(int w, double v) { this.w = w; this.v = v; }
    }
    static class ItemRatio {
        double ratio; int w; double v;
        ItemRatio(double r, int w, double v) { this.ratio = r; this.w = w; this.v = v; }
    }

    public static double fractionalKnapsack(Item[] items, int W) {
        int n = items.length;
        List<ItemRatio> list = new ArrayList<>();
        for (Item it : items) list.add(new ItemRatio(it.v / it.w, it.w, it.v));
        list.sort((a,b) -> Double.compare(b.ratio, a.ratio)); // descendente
        double totalValue = 0.0;
        int capacity = W;
        for (ItemRatio it : list) {
            if (capacity == 0) break;
            if (it.w <= capacity) {
                totalValue += it.v;
                capacity -= it.w;
            } else {
                // tomar fracción
                totalValue += it.ratio * capacity;
                capacity = 0;
            }
        }
        return totalValue;
    }

    // Método solicitado en el main
    public static double resolverMochila(Item[] items, int capacidad) {
        // Ordenar por valor/peso de mayor a menor
        Arrays.sort(items, (a, b) -> Double.compare(b.v / b.w, a.v / a.w));

        double valorTotal = 0.0;
        int pesoActual = 0;

        for (Item it : items) {
            if (pesoActual + it.w <= capacidad) {
                // Tomar el ítem completo
                valorTotal += it.v;
                pesoActual += it.w;
            } else {
                // Tomar una fracción del ítem
                int restante = capacidad - pesoActual;
                valorTotal += it.v * ((double) restante / it.w);
                break;
            }
        }
        return valorTotal;
    }

    public static void main(String[] args) {
        Item[] items = { new Item(10,60), new Item(20,100), new Item(30,120) };
        int W = 50;
        System.out.println("Valor máximo fraccionario: " + fractionalKnapsack(items, W)); // espera 240.0
    }
}

