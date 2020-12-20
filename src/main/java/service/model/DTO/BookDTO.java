package service.model.DTO;

import service.model.BookType;

public class BookDTO {

    //fields
    private BookType type;
    private int number;

    //constractures
    public BookDTO(BookType type, int number) {
        this.type = type;
        this.number = number;
    }

    public BookDTO() {
    }

    //getters and setters
    public BookType getType() {
        return type;
    }

    public void setType(BookType type) {
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    //ToString
    @Override
    public String toString() {
        return "BookDTO{" +
                "type=" + type +
                ", number=" + number +
                '}';
    }
}


