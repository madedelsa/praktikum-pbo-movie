package model;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * @author Made Delsa
 */
public class ModelTabelDataMovie extends AbstractTableModel{
    
    List<DataMovie> dm;
    public ModelTabelDataMovie(List<DataMovie>dm){
        this.dm = dm;
    }
    
    @Override
    public int getRowCount() {
        return dm.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return "judul";
            case 1:
                return "alur";
            case 2:
                return "penokohan";
            case 3:
                return "akting";
            case 4:
                return "nilai";
            default:
                return null;
        }
    }
    
    @Override
    public Object getValueAt(int row, int column) {
        switch(column){
            case 0:
                return dm.get(row).getJudul();
            case 1:
                return dm.get(row).getAlur();
            case 2:
                return dm.get(row).getPenokohan();
            case 3:
                return dm.get(row).getAkting();
            case 4:
                return dm.get(row).getRating();
            default:
                return null;
        }
    }
    
}
