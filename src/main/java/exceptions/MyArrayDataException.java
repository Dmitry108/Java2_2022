package exceptions;

public class MyArrayDataException extends NumberFormatException {
    private final int row;
    private final int col;

    public <T> MyArrayDataException(int row, int col, T a) {
        super(String.format("Элемент массива [%d][%d] %s не является целым числом", row, col, a));
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}