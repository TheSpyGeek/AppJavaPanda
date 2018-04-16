package dataframe;

import myExceptions.ExceptionOutOfRange;
import myExceptions.ExceptionString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * DataCol représente les colonnes de notre DataFrame.
 *  Test z
 * CETTE CLASSE N'EST ACCESSIBLE QUE DEPUIS LA CLASSE DATAFRAME
 */
public class DataCol
{
    //Contient les données de toute une colonne
    private ArrayList<? extends Comparable> data;

    //Contient le label de notre colonne
    private String label;

    public DataCol(String label, ArrayList<? extends Comparable> data){
        this.label = label;
        this.data= data;
    }

    /**
     * Affiche l'élément demandé à l'index indiqué.
     * @param i index de l'élément à afficher.
     */
    public String print(int i) {
        return data.get(i).toString()+"\t\t\t";
    }

    /**
     * @return le nombre de donnée dans la colonne.
     */
    public int getSize(){
        return this.data.size();
    }

    /**
     * @return le nom de la colonne.
     */
    public String getLabel(){
        return this.label;
    }

    /**
     *
     * @return une nouvelle colonne avec les lignes demandées.
     */
    public DataCol selectByLine(ArrayList<Integer> linesSelect) throws ExceptionOutOfRange {

        ArrayList<Comparable> newCol = new ArrayList<Comparable>();
        for(int i = 0; i < linesSelect.size(); i++){
            if(linesSelect.get(i) < 0 || linesSelect.get(i) >= getSize()){
                throw new ExceptionOutOfRange();
            }
            newCol.add(data.get(linesSelect.get(i)));
        }
        return new DataCol(this.label,newCol);
    }

    /**
     *
     * @return L'élément maximum du tableau.
     */
    public Comparable getMax(){
        return Collections.max(data);
    }

    /**
     *
     * @return L'élément minimum de la colonne.
     */
    public Comparable getMin(){
        return Collections.min(data);
    }

    /**
     * La colonne doit-être de type Numérique!
     * @return La somme des éléments de la colonne.
     */
    public Comparable getSum() throws ExceptionString {
        if(data.get(0) instanceof Double){
            Double sum = 0.0;
            for(Comparable c: data){
                sum+=(Double)c;
            }
            return sum;
        }else if( data.get(0) instanceof Integer){
            Integer sum = 0;
            for(Comparable c: data){
                sum+=(Integer)c;
            }
            return sum;
        } else {
            throw new ExceptionString();
        }
    }

    /**
     * La colonne doit-être de type Numérique!
     * @return La moyenne des éléments de la colonne.
     */
    public Comparable getAvg() throws ExceptionString {
        if(data.get(0) instanceof Double)
            return ((Double)getSum()/data.size());
        else if(data.get(0) instanceof Integer)
            return (Integer)getSum()/data.size();
        else
            throw new ExceptionString();
    }

    public ArrayList<? extends Comparable> getData() {
        return data;
    }

    public DataColGrouped group(HashMap<String, ArrayList<Integer>> lineToFusion) {
        Iterator i = lineToFusion.keySet().iterator();
        int count = 0;
        ArrayList<ArrayList<Comparable>> newCell = new ArrayList<ArrayList<Comparable>>();

        for(int x = 0; x < lineToFusion.keySet().size(); x++){
            newCell.add(new ArrayList<Comparable>());
        }

        while(i.hasNext()){
            ArrayList<Integer> numberLines = lineToFusion.get(i.next());
            for(Integer it: numberLines){
                newCell.get(count).add(data.get(it));
            }
            count++;
        }
        return new DataColGrouped(newCell);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof DataCol){
            DataCol dc = (DataCol)o;
            if(dc.getData().size()==data.size()){
                for(int i = 0; i<data.size(); i++){
                    if(data.get(i).equals(dc.getData().get(i))==false){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}
