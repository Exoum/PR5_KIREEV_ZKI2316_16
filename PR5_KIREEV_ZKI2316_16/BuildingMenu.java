import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BuildingMenu {
    private static final Logger LOGGER = Logger.getLogger(BuildingMenu.class.getName());

    public static void main(String[] args) throws UnsupportedEncodingException {
        //Создание списка для хранения объектов здания
        ArrayList<Building> buildings = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        // Цикл для отображения меню и обработки выбора пользователя
        while (choice != 6) {
            System.out.println("Меню:");
            System.out.println("1. Добавить пустой объект");
            System.out.println("2. Добавить объект с данными");
            System.out.println("3. Редактировать поле объекта");
            System.out.println("4. Вывести информацию обо всех объектах");
            System.out.println("5. Отсортировать объекты");
            System.out.println("6. Завершить работу программы");
            System.out.print("Выберите действие: ");

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Некорректный ввод! Выберите действие из меню.");
                LOGGER.log(Level.WARNING, "Некорректный ввод в меню.", e); // Используем LOGGER
                scanner.nextLine(); // очистка буфера ввода
                continue;
            }

            // Обработка выбора пользователя с помощью оператора switch
            switch (choice) {
                case 1:
                    clearConsole();
                    buildings.add(new Building());
                    break;
                case 2:
                    clearConsole();
                    try {
                        System.out.print("Введите тип здания (жилое, не жилое и т.д): ");
                        String type = scanner.next();
                        System.out.print("Введите количество этажей: ");
                        int floors = scanner.nextInt();
                        System.out.print("Введите статус здания (новое, старое, реконструированное и т.д): ");
                        String status = scanner.next();
                        System.out.print("Введите площадь: ");
                        double area = scanner.nextDouble();
                        System.out.print("Является ли коммерческим (true/false): ");
                        boolean isCommercial = scanner.nextBoolean();
                        buildings.add(new Building(type, floors, status, area, isCommercial));
                    } catch (InvalidBuildingDataException e) {
                        System.out.println(e.getMessage());
                        LOGGER.log(Level.WARNING, "Некорректные данные здания.", e); // Используем LOGGER
                    } catch (Exception e) {
                        System.out.println("Некорректный ввод! Проверьте данные.");
                        LOGGER.log(Level.WARNING, "Некорректный ввод данных.", e); // Используем LOGGER
                        scanner.nextLine(); // очистка буфера ввода
                    }
                    break;
                case 3:
                    clearConsole();
                    try {
                        System.out.print("Введите индекс объекта для редактирования: ");
                        int index = scanner.nextInt();
                        if (index <= 0 || index > buildings.size()) {
                            throw new IndexOutOfBoundsException("Некорректный индекс объекта.");
                        }
                        System.out.print("Введите поле для редактирования (type/floors/status/area/isCommercial): ");
                        String fieldName = scanner.next();
                        System.out.print("Введите новое значение: ");
                        Object value;
                        if (fieldName.equals("isCommercial")) {
                            value = scanner.nextBoolean();
                        } else if (fieldName.equals("floors")) {
                            value = scanner.nextInt();
                        } else if (fieldName.equals("area")) {
                            value = scanner.nextDouble();
                        } else {
                            scanner.nextLine();
                            value = scanner.nextLine();
                        }
                        buildings.get(index - 1).editBuildingField(fieldName, value);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                        LOGGER.log(Level.WARNING, "Некорректный индекс.", e); // Используем LOGGER
                    } catch (Exception e) {
                        System.out.println("Некорректный ввод! Проверьте данные.");
                        LOGGER.log(Level.WARNING, "Некорректный ввод данных.", e); // Используем LOGGER
                        scanner.nextLine(); // очистка буфера ввода
                    }
                    break;
                case 4:
                    clearConsole();
                    Building.displayAllBuildings(buildings);
                    break;
                case 5:
                    clearConsole();
                    System.out.print("Выберите поле для сортировки (type/floors/status/area/isCommercial): ");
                    String sortField = scanner.next();
                    Building.sortBuildings(buildings, sortField);
                    break;
                case 6:
                    System.out.println("Программа завершена.");
                    break;
                default:
                    System.out.println("Некорректный выбор. Повторите попытку.");
            }
        }
        scanner.close();
    }

    // Метод отчистки консоли
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

class InvalidBuildingDataException extends Exception {
    public InvalidBuildingDataException(String message) {
        super(message);
    }
}

class BuildingDataValidationException extends RuntimeException {
    public BuildingDataValidationException(String message) {
        super(message);
    }
}

class BuildingOperationException extends Exception {
    public BuildingOperationException(String message) {
        super(message);
    }

    public BuildingOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}