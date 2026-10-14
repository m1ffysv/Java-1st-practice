public class Matrix {
    private Complex[][] data;
    private int rows;
    private int cols;

    public Matrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Размеры должны быть положительными");
        }

        this.rows = rows;
        this.cols = cols;
        data = new Complex[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = new Complex(0, 0);
            }
        }
    }

    public void set(int row, int col, Complex value) {
        data[row][col] = value;
    }

    public Complex get(int row, int col) {
        return data[row][col];
    }

    public Matrix add(Matrix other) {
        if (rows != other.rows || cols != other.cols) {
            throw new IllegalArgumentException(
                    "Размеры матриц должны совпадать"
            );
        }

        Matrix result = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[i][j] = data[i][j].add(other.data[i][j]);
            }
        }

        return result;
    }

    public Matrix multiply(Matrix other) {
        if (cols != other.rows) {
            throw new IllegalArgumentException(
                    "Количество столбцов первой матрицы должно совпадать "
                            + "с количеством строк второй"
            );
        }

        Matrix result = new Matrix(rows, other.cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                Complex sum = new Complex(0, 0);

                for (int k = 0; k < cols; k++) {
                    sum = sum.add(data[i][k].multiply(other.data[k][j]));
                }

                result.data[i][j] = sum;
            }
        }

        return result;
    }

    public Matrix transpose() {
        Matrix result = new Matrix(cols, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.data[j][i] = data[i][j];
            }
        }

        return result;
    }

    public Complex determinant() {
        if (rows != cols) {
            throw new IllegalArgumentException(
                    "Определитель можно вычислить только для квадратной матрицы"
            );
        }

        Complex[][] temp = copyData();
        Complex det = new Complex(1, 0);

        for (int i = 0; i < rows; i++) {
            int pivot = i;

            for (int j = i + 1; j < rows; j++) {
                if (temp[j][i].abs() > temp[pivot][i].abs()) {
                    pivot = j;
                }
            }

            if (temp[pivot][i].isZero()) {
                return new Complex(0, 0);
            }

            if (pivot != i) {
                Complex[] row = temp[i];
                temp[i] = temp[pivot];
                temp[pivot] = row;
                det = det.multiply(new Complex(-1, 0));
            }

            Complex value = temp[i][i];
            det = det.multiply(value);

            for (int j = i + 1; j < rows; j++) {
                Complex factor = temp[j][i].divide(value);

                for (int k = i + 1; k < rows; k++) {
                    temp[j][k] = temp[j][k].subtract(
                            factor.multiply(temp[i][k])
                    );
                }

                temp[j][i] = new Complex(0, 0);
            }
        }

        return det;
    }

    public Matrix inverse() {
        if (rows != cols) {
            throw new IllegalArgumentException(
                    "Обратная матрица существует только для квадратной матрицы"
            );
        }

        int n = rows;
        Complex[][] temp = new Complex[n][2 * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = data[i][j];
                temp[i][j + n] = new Complex(i == j ? 1 : 0, 0);
            }
        }

        for (int i = 0; i < n; i++) {
            int pivot = i;

            for (int j = i + 1; j < n; j++) {
                if (temp[j][i].abs() > temp[pivot][i].abs()) {
                    pivot = j;
                }
            }

            if (temp[pivot][i].isZero()) {
                throw new ArithmeticException(
                        "Обратной матрицы не существует"
                );
            }

            Complex[] row = temp[i];
            temp[i] = temp[pivot];
            temp[pivot] = row;

            Complex divisor = temp[i][i];

            for (int j = 0; j < 2 * n; j++) {
                temp[i][j] = temp[i][j].divide(divisor);
            }

            for (int k = 0; k < n; k++) {
                if (k == i) {
                    continue;
                }

                Complex factor = temp[k][i];

                for (int j = 0; j < 2 * n; j++) {
                    temp[k][j] = temp[k][j].subtract(
                            factor.multiply(temp[i][j])
                    );
                }
            }
        }

        Matrix result = new Matrix(n, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result.data[i][j] = temp[i][j + n];
            }
        }

        return result;
    }

    public Matrix divide(Matrix other) {
        return multiply(other.inverse());
    }

    private Complex[][] copyData() {
        Complex[][] copy = new Complex[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copy[i][j] = data[i][j];
            }
        }

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.append(String.format("%18s", data[i][j]));

            }
            result.append("\n");
        }

        return result.toString();
    }
}