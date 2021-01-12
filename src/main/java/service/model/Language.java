package service.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class Language {
    //fields
    private String code; // language's code
    private String name; // language's name

    //constractures
    public Language(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Language() {
    }

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
        Language l = (Language) o;
        return Objects.equals(code, l.code);
    }
}
