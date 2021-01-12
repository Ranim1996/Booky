package service.model.DTO;

public class StatisticsType {

    private String label;
    private int y;

    public StatisticsType(String label, int y) {
        this.label = label;
        this.y = y;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "label='" + label + '\'' +
                ", y=" + y +
                '}';
    }
}
