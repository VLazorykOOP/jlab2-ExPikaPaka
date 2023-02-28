package MyMatrix;

public class Matrix {
    private double[][] matrix = new double[2][2];

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public Matrix(double a, double b, double c, double d) {
        this.matrix[0][0] = a;
        this.matrix[0][1] = b;
        this.matrix[1][0] = c;
        this.matrix[1][1] = d;
    }


    public double getIJ(int i, int j) {
        return matrix[i][j];
    }

    public double getDeterminant() {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    public Matrix getInverse() throws Exception {
        double determinant = getDeterminant();
        if (determinant == 0) {
            throw new Exception("Детермінант = 0. Матриця вироджена!");
        }
        double[][] inverseMatrix = new double[2][2];
        inverseMatrix[0][0] = matrix[1][1] / determinant;
        inverseMatrix[0][1] = -matrix[0][1] / determinant;
        inverseMatrix[1][0] = -matrix[1][0] / determinant;
        inverseMatrix[1][1] = matrix[0][0] / determinant;
        return new Matrix(inverseMatrix);
    }

    public Matrix multiply(Matrix other) {
        double[][] resultMatrix = new double[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    resultMatrix[i][j] += matrix[i][k] * other.getIJ(k, j);
                }
            }
        }
        return new Matrix(resultMatrix);
    }

    public Matrix add(Matrix other) {
        double[][] resultMatrix = new double[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                resultMatrix[i][j] = matrix[i][j] + other.getIJ(i, j);
            }
        }
        return new Matrix(resultMatrix);
    }

    public Matrix multiply(double scalar) {
        double[][] resultMatrix = new double[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                resultMatrix[i][j] = matrix[i][j] * scalar;
            }
        }
        return new Matrix(resultMatrix);
    }

    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                out.append(matrix[i][j]).append(" ");
            }
            out.append("\n");
        }
        return out.toString();
    }
}