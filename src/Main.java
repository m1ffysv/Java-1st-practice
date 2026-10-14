import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static Matrix inputMatrix() {
        System.out.print("Количество строк: ");
        int rows = scanner.nextInt();

        System.out.print("Количество столбцов: ");
        int cols = scanner.nextInt();

        Matrix matrix = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println(
                        "Элемент [" + (i + 1) + "][" + (j + 1) + "]:"
                );

                System.out.print("Действительная часть: ");
                double real = scanner.nextDouble();

                System.out.print("Мнимая часть: ");
                double imaginary = scanner.nextDouble();

                matrix.set(i, j, new Complex(real, imaginary));
            }
        }

        return matrix;
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nМАТРИЦЫ КОМПЛЕКСНЫХ ЧИСЕЛ");
            System.out.println("1. Сложение матриц");
            System.out.println("2. Умножение матриц");
            System.out.println("3. Деление матриц");
            System.out.println("4. Транспонирование матрицы");
            System.out.println("5. Вычисление определителя");
            System.out.println("0. Выход");
            System.out.print("Выберите операцию: ");

            int choice = scanner.nextInt();

            if (choice == 0) {
                System.out.println("Программа завершена");
                break;
            }

            try {
                Matrix a;
                Matrix b;
                Matrix result;

                switch (choice) {
                    case 1:
                        System.out.println("Первая матрица:");
                        a = inputMatrix();

                        System.out.println("Вторая матрица:");
                        b = inputMatrix();

                        result = a.add(b);
                        System.out.println("Результат:");
                        System.out.println(result);
                        break;

                    case 2:
                        System.out.println("Первая матрица:");
                        a = inputMatrix();

                        System.out.println("Вторая матрица:");
                        b = inputMatrix();

                        result = a.multiply(b);
                        System.out.println("Результат:");
                        System.out.println(result);
                        break;

                    case 3:
                        System.out.println("Первая матрица:");
                        a = inputMatrix();

                        System.out.println("Вторая матрица:");
                        b = inputMatrix();

                        result = a.divide(b);
                        System.out.println("Результат:");
                        System.out.println(result);
                        break;

                    case 4:
                        System.out.println("Матрица:");
                        a = inputMatrix();

                        result = a.transpose();
                        System.out.println("Результат:");
                        System.out.println(result);
                        break;

                    case 5:
                        System.out.println("Матрица:");
                        a = inputMatrix();

                        System.out.println(
                                "Определитель: " + a.determinant()
                        );
                        break;

                    default:
                        System.out.println("Неизвестная операция");
                }
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        scanner.close();
    }
}