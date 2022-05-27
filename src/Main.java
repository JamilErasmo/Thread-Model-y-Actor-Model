Scanner l = new Scanner(System.in);

public class Main {
    Scanner l = new Scanner(System.in);
        System.out.println("Ingrese el numero de matriz cuadrada:");
    int n = l.nextInt();

    int a[][] = new int[n][n];
    int b[][] = new int[n][n];
    int res[][] = new int[n][n];

        System.out.println("Matriz A:");
        for (int i = 0; i < a.length; i++) {
        for (int j = 0; j < a.length; j++) {
            a[i][j] = (int) (Math.random() * 100);

            System.out.print("|" + a[i][j]);

        }
        System.out.println("");
    }

        System.out.println("Matriz B:");
        for (int i = 0; i < b.length; i++) {
        for (int j = 0; j < b.length; j++) {

            b[i][j] = (int) (Math.random() * 100);
            System.out.print("|" + b[i][j]);

        }
        System.out.println("");
    }

    // Crear hilos, repartir filas y ejecutarlos.
        System.out.println("Ingrese el numero de hilos:");
    int numHilos = l.nextInt();
    int resto = n % numHilos;
    Suma[] hilos = new Suma[numHilos];
    int fila = 0;
    int sigFila;
        for (int h = 0; h < numHilos; ++h) {
        sigFila = fila + n / numHilos;
        if (h < resto) {
            ++sigFila;
        }
        hilos[h] = new Suma(res, a, b, fila, sigFila);
        hilos[h].start();
        fila = sigFila;
    }

    // Esperar que acaben los hilos;
        for (int h = 0; h < numHilos; ++h) {
        hilos[h].join();
    }

        System.out.println("");
        System.out.println("Suma:");
        for (int i = 0; i < res.length; ++i) {
        for (int j = 0; j < res[i].length; ++j) {
            System.out.print(res[i][j] + "\t");
        }
        System.out.println("");
    }
}