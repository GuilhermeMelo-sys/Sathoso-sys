/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaria.Model.TableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Pichau
 */
public abstract class TableModelo extends AbstractTableModel{
        private String[] colunas;
    private List<Object[]> valores;
    
    public TableModelo(String[] colunas, List<Object[]> valores){
        this.colunas = colunas;
        this.valores = valores;
    }
    
    @Override
    public String getColumnName(int indice){
        return String.valueOf(colunas[indice]);
    }
    
    public void addRow(Object[] valor){
        valores.add(valor);
        for(int i = 0; i < valor.length; i++){
            fireTableCellUpdated(getRowCount(), i);
        }
        fireTableRowsInserted(getRowCount(), getRowCount());
    }
    
    @Override
    public int getRowCount() {
        return valores.size();
    }
    
    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return valores.get(linha)[coluna];
    }
    
    public void setValueAt(Object[] objeto, int linha, int coluna){
        valores.add(objeto);
        fireTableRowsUpdated(linha, coluna);
    }
    
    public void delete(Object objeto, int linha, int coluna){
        valores.remove(objeto);
        this.fireTableRowsDeleted(linha, coluna);
    }
    
    public void deleteAll(){
        valores.clear();
        fireTableDataChanged();
    }
    
    public void updateCell(Object objeto, int linha, int coluna){
        valores.get(linha)[coluna] = objeto;
        fireTableCellUpdated(linha, coluna);
    }
}
