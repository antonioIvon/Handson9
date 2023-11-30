/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handos9;

public class handos9 {

    public static void main(String[] args) {
        double[][] data = {
            {41.9, 29.1, 251.3},
            {43.4, 29.3, 251.3},
            {43.9, 29.5, 248.3},
            {44.5, 29.7, 267.5},
            {47.3, 29.9, 273.0},
            {47.5, 30.3, 276.5},
            {47.9, 30.5, 270.3},
            {50.2, 30.7, 274.9},
            {52.8, 30.8, 285.0},
            {53.2, 30.9, 290.0},
            {56.7, 31.5, 297.0},
            {57.0, 31.7, 302.5},
            {63.5, 31.9, 304.5},
            {65.3, 32.0, 309.3},
            {71.1, 32.1, 321.7},
            {77.0, 32.5, 330.7},
            {77.8, 32.9, 349.0}
        };

        printDataSet(data);
        System.out.println("Mean X1: " + meanx1(data));
        System.out.println("Mean X2: " + meanx2(data));
        System.out.println("Mean Y: " + meany(data));
        System.out.println("");
        System.out.println("Sumatoria X1: " + sumx1(data));
        System.out.println("Sumatoria X2: " + sumx2(data));
        System.out.println("Sumatoria Y: " + sumy(data));
        System.out.println("");
        System.out.println("Sum Of Squares X1: " + soqx1(data));
        System.out.println("Sum Of Squares X2: " + soqx2(data));
        System.out.println("");
        System.out.println("Sum Of Products X1Y: " + sopx1y(data));
        System.out.println("Sum Of Products X2Y: " + sopx2y(data));
        System.out.println("Sum Of Products X1X2: " + sopx1x2(data));
        System.out.println("");
        System.out.println("Beta1: " + b1(data));
        System.out.println("Beta2: " + b2(data));
        System.out.println("Beta0: " + b0(data));
        System.out.println("");
        System.out.println("ŷ = β0 " + b0(data) + " + β1 " + b1(data) + " + β2 " + b2(data));
        System.out.println("");
        double[] predictions = predict(data);
        System.out.println("");
        System.out.println("R²: " + calculateR2(data, predictions));
        int numPredictors = 2;
        System.out.println("Ajusted R²: " + calculateAdjustedR2(calculateR2(data, predictions), data.length, numPredictors));
    }
    
    public static void printDataSet(double[][] data) {

        int fila = data.length;

        System.out.println("========== Data Set ==========\t");
        System.out.println("==== Column X    Column X2    ==== Column Y ====");
        for (int i = 0; i < fila; i++) {
            System.out.println("     " + data[i][0] + "          " + data[i][1] + "          " + data[i][2]);
        }
    }

    public static double meanx1(double[][] data) {
        int filas = data.length;

        double meanx1 = 0;

        for (int i = 0; i < filas; i++) {
            meanx1 += data[i][0];
        }

        return meanx1 /= filas;

    }

    public static double meanx2(double[][] data) {
        int filas = data.length;

        double meanx2 = 0;

        for (int i = 0; i < filas; i++) {
            meanx2 += data[i][1];
        }

        return meanx2 /= filas;

    }

    public static double meany(double[][] data) {
        int filas = data.length;

        double meany = 0;

        for (int i = 0; i < filas; i++) {
            meany += data[i][2];
        }

        return meany /= filas;

    }

    public static double sumx1(double[][] data) {
        int filas = data.length;

        double sumx1 = 0;

        for (int i = 0; i < filas; i++) {
            sumx1 += data[i][0];
        }

        return sumx1;

    }

    public static double sumx2(double[][] data) {
        int filas = data.length;

        double sumx2 = 0;

        for (int i = 0; i < filas; i++) {
            sumx2 += data[i][1];
        }

        return sumx2;

    }

    public static double sumy(double[][] data) {
        int filas = data.length;

        double sumxy = 0;

        for (int i = 0; i < filas; i++) {
            sumxy += data[i][2];
        }

        return sumxy;

    }

    public static double soqx1(double[][] data) {
        int fila = data.length;

        double soqx1 = 0;

        for (int i = 0; i < fila; i++) {
            soqx1 += Math.pow((data[i][0] - meanx1(data)), 2);
        }

        return soqx1;

    }

    public static double soqx2(double[][] data) {
        int fila = data.length;

        double soqx2 = 0;

        for (int i = 0; i < fila; i++) {
            soqx2 += Math.pow((data[i][1] - meanx2(data)), 2);
        }

        return soqx2;

    }

    public static double sopx1y(double[][] data) {
        int fila = data.length;

        double sopx1y = 0;

        for (int i = 0; i < fila; i++) {
            sopx1y += (data[i][0] - meanx1(data)) * (data[i][2] - meany(data));
        }

        return sopx1y;

    }

    public static double sopx2y(double[][] data) {
        int fila = data.length;

        double sopx2y = 0;

        for (int i = 0; i < fila; i++) {
            sopx2y += (data[i][1] - meanx2(data)) * (data[i][2] - meany(data));
        }

        return sopx2y;

    }

    public static double sopx1x2(double[][] data) {
        int fila = data.length;

        double sopx1x2 = 0;

        for (int i = 0; i < fila; i++) {
            sopx1x2 += (data[i][0] - meanx1(data)) * (data[i][1] - meanx2(data));
        }

        return sopx1x2;

    }

    public static double b1(double[][] data) {

        double b1 = 0;

        b1 = (sopx1y(data) * soqx2(data) - sopx1x2(data) * sopx2y(data)) / (soqx1(data) * soqx2(data) - sopx1x2(data) * sopx1x2(data));

        return b1;
    }

    public static double b2(double[][] data) {

        double b2 = 0;

        b2 = (sopx2y(data) * soqx1(data) - sopx1x2(data) * sopx1y(data)) / (soqx1(data) * soqx2(data) - sopx1x2(data) * sopx1x2(data));

        return b2;
    }

    public static double b0(double[][] data) {

        double b0 = 0;

        b0 = meany(data) - (b1(data) * meanx1(data)) - ((b2(data) * meanx2(data)));

        return b0;
    }

    public static double[] predict(double[][] data) {
        int fila = data.length;
        double[] predictions = new double[fila];

        for (int i = 0; i < fila; i++) {
            predictions[i] = b0(data) + (b1(data) * data[i][0]) + (b2(data) * data[i][1]);
            System.out.println((i + 1) + ": " + predictions[i]);
        }

        return predictions;
    }

    public static double calculateR2(double[][] data, double[] predictions) {
        int fila = data.length;

        // Calcular la suma total de cuadrados
        double totalSumOfSquares = 0;
        for (int i = 0; i < fila; i++) {
            totalSumOfSquares += Math.pow((data[i][2] - meany(data)), 2);
        }

        // Calcular la suma de errores cuadráticos residuales
        double sumOfSquaredResiduals = 0;
        for (int i = 0; i < fila; i++) {
            sumOfSquaredResiduals += Math.pow((data[i][2] - predictions[i]), 2);
        }

        // Calcular R^2
        double rSquared = 1 - (sumOfSquaredResiduals / totalSumOfSquares);

        return rSquared;
    }
    
    public static double calculateAdjustedR2(double rSquared, int n, int k) {
        return 1 - ((1 - rSquared) * (n - 1) / (n - k - 1));
    }


}
