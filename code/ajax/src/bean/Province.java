package bean;

import java.util.Objects;

public class Province {
    private int id;
    private int code;
    private String name;
    private int pcode;

    public Province() {
    }

    public Province(int id, int code, String name, int pcode) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.pcode = pcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPcode() {
        return pcode;
    }

    public void setPcode(int pcode) {
        this.pcode = pcode;
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", code=" + code +
                ", name='" + name + '\'' +
                ", pcode=" + pcode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Province province = (Province) o;
        return id == province.id && code == province.code && pcode == province.pcode && Objects.equals(name, province.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, pcode);
    }
}
