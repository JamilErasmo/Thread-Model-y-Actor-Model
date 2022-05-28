package actors;

public class Matriz {
    private int[][] values;

    public Matriz(int[][] values) {
        this.values = values;
    }

    public int[][] getValues() {
        return values;
    }

    public int[] getRow(int rowIndex) {
        if( rowIndex >= 0 && rowIndex < values.length) {
            return values[rowIndex];
        } else {
            throw new IllegalArgumentException("Indice no válido");
        }
    }

    public int[] getColumn(int colIndex) {
        int[] output = new int[values.length];
        if( colIndex < values[0].length)  {
            for (var i = 0; i < values.length; i ++) {
                output[i] = values[i][colIndex];
            }
        }
        return output;
    }

    public String toString() {
        String output = "";
        for(var fila : values) {
            output += "{";
            for( var value : fila) {
                output += value + "\t";
            }
            output += "}\n";
        }
        return "{\n" + output + "}";
    }

    private int calcValue (int[] row, int[] col) {
        int element = 0;
        for(var i = 0; i < row.length; i ++ ) {
            element += row[i] * col[i];
        }
        return element;
    }
}