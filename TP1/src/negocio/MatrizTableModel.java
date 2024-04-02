package negocio;

import javax.swing.table.AbstractTableModel;

public class MatrizTableModel extends AbstractTableModel {
    private int[][] matrix;

    public MatrizTableModel(int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public int getRowCount() {
        return matrix.length;
    }

    @Override
    public int getColumnCount() {
        return matrix[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return matrix[rowIndex][columnIndex];
    }
}
