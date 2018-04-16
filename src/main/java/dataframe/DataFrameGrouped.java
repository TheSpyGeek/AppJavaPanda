package dataframe;

import myExceptions.ExceptionColIsGrouped;
import myExceptions.ExceptionString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class DataFrameGrouped {
    private LinkedHashMap<String,DataColGrouped> setOfCol;
    private ArrayList<String> colGroupedName;
    private int nbLine;

    public DataFrameGrouped(ArrayList<String> labels, ArrayList<String> colGroupedName, ArrayList<DataColGrouped> data){
        this.colGroupedName = colGroupedName;
        setOfCol = new LinkedHashMap<String, DataColGrouped>();
        for(int i=0; i<data.size(); i++){
            setOfCol.put(labels.get(i),data.get(i));
        }
        this.colGroupedName = colGroupedName;

        Iterator i = setOfCol.keySet().iterator();
        nbLine = setOfCol.get(i.next()).getNbLine();
    }

    public ArrayList<Integer> count(){
        Iterator i = setOfCol.keySet().iterator();
        return setOfCol.get(i.next()).getCount();
    }

    public ArrayList<? extends Comparable> sum(String label) throws ExceptionColIsGrouped, ExceptionString {
        if(colGroupedName.contains(label)){
            throw  new ExceptionColIsGrouped();
        }else{
            return setOfCol.get(label).getSum();
        }
    }

    public ArrayList<? extends Comparable> avg(String label) throws ExceptionColIsGrouped, ExceptionString {
        if(colGroupedName.contains(label)){
            throw  new ExceptionColIsGrouped();
        }else{
            return setOfCol.get(label).getAvg();
        }
    }

    public ArrayList<? extends Comparable> max(String label) throws ExceptionColIsGrouped {
        if(colGroupedName.contains(label)){
            throw  new ExceptionColIsGrouped();
        }else{
            return setOfCol.get(label).getMax();
        }
    }

    public ArrayList<? extends Comparable> min(String label) throws ExceptionColIsGrouped {
        if(colGroupedName.contains(label)){
            throw  new ExceptionColIsGrouped();
        }else{
            return setOfCol.get(label).getMin();
        }
    }


    public String printColGrouped(){
        String colGrouped = "";
        for(int i = 0; i<colGroupedName.size(); i++){
            colGrouped += colGroupedName.get(i)+"\t\t\t";
        }
        return colGrouped;
    }

    public String printValuesGrouped(ArrayList<? extends Comparable> values){
        String valuesGrouped = "";
        for(int i = 0; i<nbLine; i++){
            for(int j = 0; j<colGroupedName.size(); j++){
                valuesGrouped += setOfCol.get(colGroupedName.get(j)).printGrouped(i)+"\t\t\t";
            }
            valuesGrouped += values.get(i) + "\n";
        }
        return valuesGrouped;
    }


    public String countPrint(){
        String count = "";
        ArrayList<Integer> values = count();
        count += printColGrouped();
        count += "COUNT\n";
        count += printValuesGrouped(values);
        return count;
    }



    public String sumPrint(String label) throws ExceptionColIsGrouped, ExceptionString {
        ArrayList<? extends Comparable> values = sum(label);
        String sumP = printColGrouped();
        sumP += "SUM "+label+"\n";
        sumP += printValuesGrouped(values);
        return sumP;
    }

    public String avgPrint(String label) throws ExceptionColIsGrouped, ExceptionString {
        ArrayList<? extends Comparable> values = avg(label);
        String avgP = printColGrouped();
        avgP += "AVG "+label+"\n";
        avgP += printValuesGrouped(values);
        return avgP;
    }

    public String maxPrint(String label) throws ExceptionColIsGrouped {
        ArrayList<? extends Comparable> values = max(label);
        String maxP = printColGrouped();
        maxP += "MAX "+label+"\n";
        maxP += printValuesGrouped(values);
        return maxP;
    }

    public String minPrint(String label) throws ExceptionColIsGrouped {
        ArrayList<? extends Comparable> values = min(label);
        String minP = printColGrouped();
        minP += "MIN "+label +"\n";
        minP += printValuesGrouped(values);
        return minP;
    }

}
