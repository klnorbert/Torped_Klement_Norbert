package service.util;

import model.MapVO;

/**
 * Util class containing useful collection related operations.
 */
public class CollectionUtil {

    public int CordinateToInt(String cordinate){
        switch(cordinate) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            case "E":
                return 4;
            case "F":
                return 5;
            case "G":
                return 6;
            case "H":
                return 7;
            case "I":
                return 8;
            case "J":
                return 9;
            default:
                return -1;
        }
    }

    public String IntToCordinate(int cordinate){
        switch(cordinate) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";
            case 8:
                return "I";
            case 9:
                return "J";
            default:
                return "Error";
        }
    }

    public boolean shipDeath(MapVO map, int row, int colum) {
        boolean result= true;
        for(int i=0;row+i!=map.getNumberOfRows()+1 && map.getMap()[row+i][colum]!=0 && map.getMap()[row+i][colum]!=1;i++){
            if(map.getMap()[row+i][colum]==4){
                result= false;
            }
        }
        for(int i=0;row-i!=-1 && map.getMap()[row-i][colum]!=0 && map.getMap()[row-i][colum]!=1;i++){
            if(map.getMap()[row-i][colum]==4){
                result= false;
            }
        }
        for(int i=0;colum+i!=map.getNumberOfRows()+1 && map.getMap()[row][colum+i]!=0 && map.getMap()[row][colum+i]!=1;i++){
            if(map.getMap()[row][colum+i]==4){
                result= false;
            }
        }
        for(int i=0;colum-i!=-1 && map.getMap()[row][colum-i]!=0 && map.getMap()[row][colum-i]!=1;i++){
            if(map.getMap()[row][colum-i]==4){
                result= false;
            }
        }
        return result;
    }

}
