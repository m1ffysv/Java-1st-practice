
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static Matrix lastResult = null;

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

    public static Matrix chooseMatrix() {
        if (lastResult == null) {
            System.out.println("Сохраненного результата нет.");
            return inputMatrix();
        }

        System.out.println("1. Создать новую матрицу");
        System.out.println("2. Использовать последний результат");
        System.out.print("Выберите вариант: ");

        int choice = scanner.nextInt();

        if (choice == 2) {
            return lastResult;
        }

        return inputMatrix();
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nМАТРИЦЫ КОМПЛЕКСНЫХ ЧИСЕЛ");
            System.out.println("1. Сложение матриц");
            System.out.println("2. Умножение матриц");
            System.out.println("3. Деление матриц");
            System.out.println("4. Транспонирование матрицы");
            System.out.println("5. Вычисление определителя");
            System.out.println("6. Показать последний результат");
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
                        a = chooseMatrix();

                        System.out.println("Вторая матрица:");
                        b = chooseMatrix();

                        result = a.add(b);
                        lastResult = result;

                        System.out.println("Результат:");
                        System.out.println(lastResult);
                        break;

                    case 2:
                        System.out.println("Первая матрица:");
                        a = chooseMatrix();

                        System.out.println("Вторая матрица:");
                        b = chooseMatrix();

                        result = a.multiply(b);
                        lastResult = result;

                        System.out.println("Результат:");
                        System.out.println(lastResult);
                        break;

                    case 3:
                        System.out.println("Первая матрица:");
                        a = chooseMatrix();

                        System.out.println("Вторая матрица:");
                        b = chooseMatrix();

                        result = a.divide(b);
                        lastResult = result;

                        System.out.println("Результат:");
                        System.out.println(lastResult);
                        break;

                    case 4:
                        System.out.println("Матрица:");
                        a = chooseMatrix();

                        result = a.transpose();
                        lastResult = result;

                        System.out.println("Результат:");
                        System.out.println(lastResult);
                        break;

                    case 5:
                        System.out.println("Матрица:");
                        a = chooseMatrix();

                        System.out.println(
                                "Определитель: " + a.determinant()
                        );
                        break;

                    case 6:
                        if (lastResult == null) {
                            System.out.println(
                                    "Последнего результата пока нет"
                            );
                        } else {
                            System.out.println("Последний результат:");
                            System.out.println(lastResult);
                        }
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
