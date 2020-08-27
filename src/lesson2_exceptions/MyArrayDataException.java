package lesson2_exceptions;

public class MyArrayDataException extends NumberFormatException {
    private Integer row;
    private Integer column;
    private String value;

    public MyArrayDataException(Integer row, Integer column, String value) {
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public MyArrayDataException(String s, Integer row, Integer column, String value) {
        super(s);
        this.row = row;
        this.column = column;
        this.value = value;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public String getValue() {
        return value;
    }
}
