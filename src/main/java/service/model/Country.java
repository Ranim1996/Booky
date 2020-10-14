package service.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class Country {
    //fields
    private String code; // country's code
    private String name; // country's name

    //constractures
    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Country() {
    }

    //alt insert to list the getters and setters.....etc

    //getters and setters


    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    //methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country c = (Country) o;
        return Objects.equals(code, c.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

//    @Override
//    public String toString() {
//        return "Country{" +
//                "country code='" + countryCode + '\'' +
//                ", country name='" + countryName + '\'' +
//                '}';
//    }
}
