package service.command.performer;

import model.MapVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class used to write a number to a given position of a map.
 */
public class DeathShipPerformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeathShipPerformer.class);

    public MapVO perform(MapVO map, int row, int column){

        int[][] resultmap = map.getMap();
        int i;
        for(i=1;row+i!=map.getNumberOfRows()+1 && map.getMap()[row+i][column]!=0 && map.getMap()[row+i][column]!=1;i++){
            resultmap[row+i][column]=3;
            if(column!= 0){
                resultmap[row+i][column-1]=1;
            }
            if(column!= map.getNumberOfColumns()){
                resultmap[row+i][column+1]=1;
            }
        }
        if(row+i<= map.getNumberOfRows()){
            if(column!= 0){
                resultmap[row+i][column-1]=1;
            }
            resultmap[row+i][column]=1;
            if(column!= map.getNumberOfColumns()){
                resultmap[row+i][column+1]=1;
            }
        }
        for(i=1;row-i!=-1 && map.getMap()[row-i][column]!=0 && map.getMap()[row-i][column]!=1;i++){
            resultmap[row-i][column]=3;
            if(column!= 0){
                resultmap[row-i][column-1]=1;
            }
            if(column!= map.getNumberOfColumns()){
                resultmap[row-i][column+1]=1;
            }
        }
        if(row-i>=0){
            if(column!= 0){
                resultmap[row-i][column-1]=1;
            }
            resultmap[row-i][column]=1;
            if(column!= map.getNumberOfColumns()){
                resultmap[row-i][column+1]=1;
            }
        }
        for(i=1;column+i!=map.getNumberOfColumns()+1 && map.getMap()[row][column+i]!=0 && map.getMap()[row][column+i]!=1;i++){
            resultmap[row][column+i]=3;
            if(row!= 0){
                resultmap[row-1][column+i]=1;
            }
            if(row!= map.getNumberOfRows()){
                resultmap[row+1][column+i]=1;
            }
        }
        if(column+i<= map.getNumberOfColumns()){
            if(row!= 0){
                resultmap[row-1][column+i]=1;
            }
            resultmap[row][column+i]=1;
            if(row!= map.getNumberOfRows()){
                resultmap[row+1][column+i]=1;
            }
        }
        for(i=1;column-i!=-1 &&map.getMap()[row][column-i]!=0 && map.getMap()[row][column-i]!=1;i++){
            resultmap[row][column-i]=3;
            if(row!= 0){
                resultmap[row-1][column-i]=1;
            }
            if(row!= map.getNumberOfRows()){
                resultmap[row+1][column-i]=1;
            }
        }
        if(column-i>=0){
            if(row!= 0){
                resultmap[row-1][column-i]=1;
            }
            resultmap[row][column-i]=1;
            if(row!= map.getNumberOfRows()){
                resultmap[row+1][column-i]=1;
            }
        }

        return new MapVO(map.getNumberOfRows(), map.getNumberOfColumns(), resultmap);
    }

}
