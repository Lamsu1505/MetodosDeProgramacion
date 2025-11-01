package voraces;

import java.util.*;

public class MochilaFraccionaria {
    static class Item {
        int w; double v;
        Item(int w, double v) { this.w = w; this.v = v; }
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

    public static void main(String[] args) {
        Item[] items = { new Item(10,60), new Item(20,100), new Item(30,120) };
        int W = 50;
        System.out.println("Valor máximo fraccionario: " + fractionalKnapsack(items, W)); // espera 240.0
    }
}

