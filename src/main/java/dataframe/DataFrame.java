package dataframe;

import myExceptions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class DataFrame {

    //Une dataframe est un ensemble de DataCol.
    LinkedHashMap<String, DataCol> setOfCol;
    private int nbLine;

    /**
     * Construction d'un objet Dataframe à partir d'un ensemble de colonnes.
     * @param cols liste de colonnes.
     * @param labels liste des labels respectifs des cols.
     */
    public DataFrame(ArrayList<String> labels, ArrayList<ArrayList<? extends Comparable>> cols) throws ExceptionNotSameSize{
        setOfCol = new LinkedHashMap<String, DataCol>();
        if(labels.size()!=cols.size())
            throw new ExceptionNotSameSize();

        int s = cols.get(0).size();
        for(int i = 1; i < cols.size(); i++){
            if(s!=cols.get(i).size()){
                throw new ExceptionNotSameSize();
            }
        }

        for(int i=0; i<cols.size(); i++){
            DataCol dc = new DataCol(labels.get(i), cols.get(i));
            setOfCol.put(labels.get(i),dc);
        }
    }

    /**
     * Construction d'un objet Dataframe à partir d'un fichier csv.
     * @param csvPath Chemin du fichier csv à parser.
     */
    public DataFrame(String csvPath) throws IOException, ExceptionNotSameSize {
        setOfCol = new LinkedHashMap<String, DataCol>();
        String line = "";
        String separator = ",";
        boolean firstPass = true;
        ArrayList<String> labels = new ArrayList<String>();
        ArrayList<Integer> types = new ArrayList<Integer>();
        ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();
        try  {
            BufferedReader br = new BufferedReader(new FileReader(csvPath));
            while ((line = br.readLine()) != null) {
                String[] lineContent = line.split(separator);
                for (int i=0; i<lineContent.length; i++){
                    //On part du principe que le premier élément de la colonne contient le label
                    if(firstPass){
                        labels.add(lineContent[i]);
                        datas.add(new ArrayList<String>());
                        types.add(0);
                    }else{//Puis on insert dans les bonnes colonnes.
                        if(labels.size()==lineContent.length){
                            datas.get(i).add(lineContent[i]);

                            if(parseInteger(lineContent[i])!=null){
                                types.set(i,changeType(types.get(i),1));
                            }
                            else if(parseDouble(lineContent[i])!=null){
                                types.set(i,changeType(types.get(i),2));
                            }
                            else{
                                types.set(i,3);
                            }
                        }else{
                            throw new ExceptionNotSameSize();
                        }
                    }
                }
                firstPass = false;
            }

            ArrayList<Comparable>  tempD;

            //On parcours nos colonnes de String
            //sameType correspond au type définit
            for (int i = 0; i<datas.size(); i++) {
                tempD = new ArrayList<Comparable>();
                //Aucun type n'est définit pour l'instant
                for(String s: datas.get(i)){//Pour chaque donnée de notre colonne
                    if(types.get(i)==3){
                        break;
                    }else if(types.get(i)==2){
                        tempD.add(parseDouble(s));
                    }else if(types.get(i)==1){
                        tempD.add(parseInteger(s));
                    }
                }
                if(types.get(i)==3){//Si on est sorti, on envoie les données String
                    setOfCol.put(labels.get(i),new DataCol(labels.get(i),datas.get(i)));
                }else{//Sinon, on envoie les données parsées
                    setOfCol.put(labels.get(i),new DataCol(labels.get(i),tempD));
                }
            }

        } catch (IOException e) {
            throw new IOException();
        }
    }

    public int changeType(int t1, int t2){
        if(t1>t2){
            return t1;
        }
        return t2;
    }

    /**
     * Troisième constructeur à partir d'une hashMap
     * @param setOfCol HashMap correspondant au DataFrame.
     */
    public DataFrame(LinkedHashMap<String, DataCol> setOfCol){
        this.setOfCol = setOfCol;
    }

    private Double parseDouble(String s){
        try {
            return  Double.parseDouble(s);
        }catch (Exception e){
            return null;
        }
    }

    private Integer parseInteger(String s){
        try {
            return  Integer.parseInt(s);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * Fonction permettant d'afficher l'entiereté du DataFrame
     * @param begin Indique à partir de quelle ligne afficher.
     * @param end   Indique quand arrêter l'affichage. Si end = -1, alors tout afficher.
     */
    public String print(int begin, int end) throws ExceptionOutOfRange {
        String s = "";
        if(end>=begin&&begin>=0&&end<(setOfCol.get(setOfCol.keySet().iterator().next()).getSize())&&end>=0)
        {
            s+="\t\t\t";
            Iterator i = setOfCol.keySet().iterator();
            while (i.hasNext()){
                s+=setOfCol.get(i.next()).getLabel()+"\t\t\t";
            }
            s+="\n";

                while(begin<=end ){
                    s+=begin+"\t\t\t";
                    i = setOfCol.keySet().iterator();
                    while (i.hasNext()){
                        s+=setOfCol.get(i.next()).print(begin);
                    }
                    s+="\n";
                    begin++;
                }
        }else{
            throw new ExceptionOutOfRange();
        }
        return s;
    }

    public String printAll() throws ExceptionOutOfRange {
       return print(0,  (setOfCol.get(setOfCol.keySet().iterator().next()).getSize())-1);
    }

    /**
     * Renvoie un sous-ensemble de notre DataFrame en sélectionnant les données par ligne.
     * @param begin Borne inférieure
     * @param end Borne supérieure
     * @return Le sous-ensemble du DataFrame sélectionné via les lignes
     */
    public DataFrame selectFromLine(ArrayList<Integer> linesToSelect) throws ExceptionOutOfRange {
        LinkedHashMap<String, DataCol> newSetOfCol = new LinkedHashMap<String, DataCol>();

        Iterator i = setOfCol.keySet().iterator();
        while(i.hasNext()){
            String s = (String) i.next();
            newSetOfCol.put(s,setOfCol.get(s).selectByLine(linesToSelect));
        }

        return new DataFrame(newSetOfCol);
    }

    /**
     * Renvoie un sous-ensemble de notre DataFrame en sélectionnant les données par colonne.
     * @param labels Nom des colonnes que l'on doit sélectionner
     * @return Le sous-ensemble du DataFrame sélectionné via les labels
     */
    public DataFrame selectFromLabel(ArrayList<String> labels) throws ExceptionNoSuchColumn {
        LinkedHashMap<String, DataCol> newSetOfCol = new LinkedHashMap<String, DataCol>();

        for(String s: labels){
            if(setOfCol.containsKey(s)){
                newSetOfCol.put(s,setOfCol.get(s));
            }else{
                throw new ExceptionNoSuchColumn();
            }
        }

        return new DataFrame(newSetOfCol);
    }

    /**
     * Permet de retourner la valeur maximale de la colonne
     * @param label colonne concernée
     * @return
     * @throws ExceptionNoSuchColumn si la colonne n'est pas dans le dataframe
     */
    public Comparable getMax(String label) throws ExceptionNoSuchColumn {
        if(setOfCol.containsKey(label)){
            return setOfCol.get(label).getMax();
        }else{
            throw new ExceptionNoSuchColumn();
        }
    }


    /**
     * Permet de retourner la valeur minimale de la colonne
     * @param label colonne concernée
     * @return
     * @throws ExceptionNoSuchColumn si la colonne n'est pas dans le dataframe
     */
    public Comparable getMin(String label) throws ExceptionNoSuchColumn {
        if(setOfCol.containsKey(label)){
            return setOfCol.get(label).getMin();
        }else{
            throw new ExceptionNoSuchColumn();
        }
    }

    /**
     * Permet de retourner la somme de la colonne
     * @param label colonne concernée
     * @return
     * @throws ExceptionNoSuchColumn si la colonne n'est pas dans le dataframe
     * @throws ExceptionString si la colonne est de type String
     */
    public Comparable getSum(String label) throws ExceptionString, ExceptionNoSuchColumn {
        if(setOfCol.containsKey(label)){
            return setOfCol.get(label).getSum();
        }else{
            throw new ExceptionNoSuchColumn();
        }
    }

    /**
     * Permet de retourner la valeur moyenne de la colonne
     * @param label colonne concernée
     * @return
     * @throws ExceptionNoSuchColumn si la colonne n'est pas dans le dataframe
     * @throws ExceptionString si la colonne est de type String
     */
    public Comparable getAvg(String label) throws ExceptionString, ExceptionNoSuchColumn {
        if(setOfCol.containsKey(label)){
            return setOfCol.get(label).getAvg();
        }else{
            throw new ExceptionNoSuchColumn();
        }
    }

    /**
     * Permet de compter les valeurs d'une colonne
     * @param label colonne concernée
     * @return
     * @throws ExceptionNoSuchColumn si la colonne n'est pas dans le dataframe
     */
    public int getCount(String label) throws ExceptionNoSuchColumn {
        if(setOfCol.containsKey(label)){
            return setOfCol.get(label).getSize();
        }else{
            throw new ExceptionNoSuchColumn();
        }
    }

    /**
     * Permet de générer un DataFrameGrouped résultant d'un group by sur ce dataframe
     * @param colToGroup les colonnes concernées par le group by
     * @return le DataFrameGrouped résultant
     * @throws ExceptionNoSuchColumn si les colonnes n'existent pas
     */
    public DataFrameGrouped groupBy(ArrayList<String> colToGroup) throws ExceptionNoSuchColumn {
        HashMap<String, ArrayList<Integer>> lineToFusion = new HashMap<String, ArrayList<Integer>>();


        Iterator i = setOfCol.keySet().iterator();
        ArrayList<String> labels = new ArrayList<String>();
        while(i.hasNext()){
            labels.add((String) i.next());
        }

        //Nos données groupés
        ArrayList<DataColGrouped> colGrouped = new ArrayList<DataColGrouped>();

        DataCol dc = setOfCol.get(colToGroup.get(0));

        for(int line= 0; line<dc.getData().size(); line++){
            String lineToCheck = "";

            for(int col = 0; col < colToGroup.size(); col++){
                lineToCheck+=String.valueOf(setOfCol.get(colToGroup.get(col)).getData().get(line));
            }
            if(lineToFusion.containsKey(lineToCheck)){
                lineToFusion.get(lineToCheck).add(line);
            }else{
                ArrayList<Integer> l = new ArrayList<Integer>();
                l.add(line);
                lineToFusion.put(lineToCheck, l);
            }

        }

        //lineToFusion contient alors toutes les lignes que l'on doit fusionner
        i = setOfCol.keySet().iterator();
        while(i.hasNext()){
                colGrouped.add(setOfCol.get(i.next()).group(lineToFusion));
        }

        return new DataFrameGrouped(labels,colToGroup,colGrouped);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof DataFrame){
            DataFrame dfComp = (DataFrame)o;
            if(dfComp.setOfCol.size()==setOfCol.size()){
                Iterator i = setOfCol.keySet().iterator();
                while(i.hasNext()){
                    String s = (String) i.next();
                    boolean eq=setOfCol.get(s).equals(dfComp.setOfCol.get(s));
                    if(eq==false){
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /**
     * @return la liste des colonnes
     */
    public ArrayList<String> getLabel(){
        ArrayList<String> labels = new ArrayList<String>();

        Iterator it = this.setOfCol.keySet().iterator();

        while(it.hasNext()){
            labels.add((String)it.next());
        }

        return labels;
    }
}
