package service.model.DTO;

public class StatisticsLanguage {

    private String name;
    private int y;

    public StatisticsLanguage(String name, int y) {
        this.name = name;
        this.y = y;
    }

    public StatisticsLanguage() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "StatisticsLanguage{" +
                "name='" + name + '\'' +
                ", y=" + y +
                '}';
    }
}
