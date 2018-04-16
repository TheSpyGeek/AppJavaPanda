package dataframe;

import myExceptions.ExceptionString;

import java.util.ArrayList;
import java.util.Collections;

class DataColGrouped {
    //Chaque case contient à présent une liste de comparable
    private ArrayList<ArrayList<Comparable>> data;

    private int nbLine = 0;

    public DataColGrouped(ArrayList<ArrayList<Comparable>> data){
        this.data  = data;
        nbLine = this.data.size();
    }

    public int getNbLine(){
        return nbLine;
    }

    public ArrayList<Integer> getCount(){
        ArrayList<Integer> countByLine = new ArrayList<Integer>();
        for (int i = 0; i < nbLine; i++){
            countByLine.add(this.data.get(i).size());
        }
        return countByLine;
    }


    public ArrayList<? extends Comparable> getSum() throws ExceptionString {
        if(data.get(0).get(0) instanceof Integer){
            ArrayList<Integer> sumVal = new ArrayList<Integer>();
            for(int i = 0; i<data.size(); i++){
                Integer sum = 0;
                for(int j = 0; j<data.get(i).size(); j++){
                    sum+= (Integer)data.get(i).get(j);
                }
                sumVal.add(sum);
            }
            return sumVal;
        }else if(data.get(0).get(0) instanceof Double){
            ArrayList<Double> sumVal = new ArrayList<Double>();
            for(int i = 0; i<data.size(); i++){
                Double sum = 0.0;
                for(int j = 0; j<data.get(i).size(); j++){
                    sum+= (Double)data.get(i).get(j);
                }
                sumVal.add(sum);
            }
            return sumVal;
        }else {
            throw new ExceptionString();
        }
    }

    public ArrayList<? extends Comparable> getAvg() throws ExceptionString {
        if(data.get(0).get(0) instanceof Double){
            ArrayList<Double> sumToAvg = (ArrayList<Double>) getSum();
            for(int i =0; i<sumToAvg.size(); i++){
                sumToAvg.set(i, sumToAvg.get(i)/data.get(i).size());
            }
            return sumToAvg;
        }else if(data.get(0).get(0) instanceof Integer){
            ArrayList<Integer> sumToAvg = (ArrayList<Integer>) getSum();
            for(int i =0; i<sumToAvg.size(); i++){
                sumToAvg.set(i, sumToAvg.get(i)/data.get(i).size());
            }
            return sumToAvg;
        }else{
            throw new ExceptionString();
        }
    }

    public ArrayList<? extends Comparable> getMax() {
        if(data.get(0).get(0) instanceof Double){
            ArrayList<Double> max = new ArrayList<Double>();
            for(int i = 0; i<data.size(); i++){
                max.add((Double) Collections.max(data.get(i)));
            }
            return max;
        }else if(data.get(0).get(0) instanceof Integer){
            ArrayList<Integer> max = new ArrayList<Integer>();
            for(int i = 0; i<data.size(); i++){
                max.add((Integer) Collections.max(data.get(i)));
            }
            return max;
        }else{//C'est une colonne de String
            ArrayList<String> max = new ArrayList<String>();
            for(int i = 0; i<data.size(); i++){
                max.add((String) Collections.max(data.get(i)));
            }
            return max;
        }
    }

    public ArrayList<? extends Comparable> getMin() {
        if(data.get(0).get(0) instanceof Double){
            ArrayList<Double> min = new ArrayList<Double>();
            for(int i = 0; i<data.size(); i++){
                min.add((Double) Collections.min(data.get(i)));
            }
            return min;
        }else if(data.get(0).get(0) instanceof Integer){
            ArrayList<Integer> min = new ArrayList<Integer>();
            for(int i = 0; i<data.size(); i++){
                min.add((Integer) Collections.min(data.get(i)));
            }
            return min;
        }else{//C'est une colonne de String
            ArrayList<String> min = new ArrayList<String>();
            for(int i = 0; i<data.size(); i++){
                min.add((String) Collections.min(data.get(i)));
            }
            return min;
        }
    }

    public String printGrouped(int i) {
        return String.valueOf(data.get(i).get(0));
    }
}
