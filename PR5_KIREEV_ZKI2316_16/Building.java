import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

// Класс, представляющий объект здания
class Building {
    private String type;
    private int floors;
    private String status;
    private double area;
    private boolean isCommercial;

    // Конструктор по умолчанию
    public Building() {
        this.type = "Жилое здание";
        this.floors = 1;
        this.status = "Новое";
        this.area = 0.0;
        this.isCommercial = false;
    }

    // Конструктор с параметрами
    public Building(String type, int floors, String status, double area, boolean isCommercial) throws InvalidBuildingDataException {
        if (floors <= 0) {
            throw new InvalidBuildingDataException("Количество этажей должно быть больше 0.");
        }
        if (area <= 0) {
            throw new InvalidBuildingDataException("Площадь здания должна быть больше 0.");
        }
        this.type = type;
        this.floors = floors;
        this.status = status;
        this.area = area;
        this.isCommercial = isCommercial;
    }

    // Геттеры и сеттеры для атрибутов
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) throws InvalidBuildingDataException {
        if (floors <= 0) {
            throw new InvalidBuildingDataException("Количество этажей должно быть больше 0.");
        }
        this.floors = floors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) throws InvalidBuildingDataException {
        if (area <= 0) {
            throw new InvalidBuildingDataException("Площадь здания должна быть больше 0.");
        }
        this.area = area;
    }

    public boolean isCommercial() {
        return isCommercial;
    }

    public void setCommercial(boolean commercial) {
        isCommercial = commercial;
    }

    public void editBuildingField(String fieldName, Object value) throws InvalidBuildingDataException, BuildingDataValidationException {
        try {
            switch (fieldName) {
                case "type":
                    setType((String) value);
                    break;
                case "floors":
                    setFloors((int) value);
                    break;
                case "status":
                    setStatus((String) value);
                    break;
                case "area":
                    setArea((double) value);
                    break;
                case "isCommercial":
                    setCommercial((boolean) value);
                    break;
                default:
                    System.out.println("Некорректное поле для редактирования.");
            }
        } catch (BuildingDataValidationException e) {
            new BuildingOperationException("Некорректные данные для редактирования.", e);
        }
    }

    // Метод для вывода информации обо всех объектах
    public static void displayAllBuildings(ArrayList<Building> buildings) {
        if (buildings.isEmpty()) {
            System.out.println("Список зданий пуст.");
            return;
        }
        for (Building building : buildings) {
            System.out.println(building.toString());
        }
    }

    // Метод для сортировки объектов по выбранному полю
    public static void sortBuildings(ArrayList<Building> buildings, String field) {
        switch (field) {
            case "type":
                Collections.sort(buildings, Comparator.comparing(Building::getType));
                break;
            case "floors":
                Collections.sort(buildings, Comparator.comparingInt(Building::getFloors));
                break;
            case "status":
                Collections.sort(buildings, Comparator.comparing(Building::getStatus));
                break;
            case "area":
                Collections.sort(buildings, Comparator.comparingDouble(Building::getArea));
                break;
            case "isCommercial":
                Collections.sort(buildings, Comparator.comparing(Building::isCommercial));
                break;
            default:
                System.out.println("Некорректное поле для сортировки.");
        }
    }

    @Override
    public String toString() {
        return "Building{" +
                "type='" + type + '\'' +
                ", floors=" + floors +
                ", status='" + status + '\'' +
                ", area=" + area +
                ", isCommercial=" + isCommercial +
                '}';
    }
}