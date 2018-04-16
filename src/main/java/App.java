import dataframe.DataFrame;
import dataframe.DataFrameGrouped;
import myExceptions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class App {
	public static void main(String args[]){
        try {
            DataFrame df = new DataFrame("/FileTest/file.csv");

            ArrayList<String> labels = df.getLabel();

            try {
                System.out.println("--AFFICHAGE COMPLET--\n");
                System.out.print(df.printAll());
            } catch (ExceptionOutOfRange exceptionOutOfRange) {
                exceptionOutOfRange.printStackTrace();
            }
            System.out.println("\n--OPERATIONS SUR LE DATAFRAME--\n");
            System.out.println();
            System.out.println("Average:");
            printLabel(labels);
            for(String label: labels){
                try {
                    System.out.print(df.getAvg(label)+"\t");
                } catch (ExceptionString exceptionString) {
                    System.out.print("NULL\t");
                } catch (ExceptionNoSuchColumn exceptionNoSuchColumn) {
                    System.out.print("NULL\t");
                }
            }
            System.out.println("\n");

            System.out.println("Maximum:");
            printLabel(labels);
            for(String label: labels){
                try {
                    System.out.print(df.getMax(label)+"\t");
                } catch (ExceptionNoSuchColumn exceptionNoSuchColumn) {
                    System.out.print("NULL\t");
                }
            }
            System.out.println("\n");

            System.out.println("Minimum:");
            printLabel(labels);
            for(String label: labels){
                try {
                    System.out.print(df.getMax(label)+"\t");
                } catch (ExceptionNoSuchColumn exceptionNoSuchColumn) {
                    System.out.print("NULL\t");
                }
            }
            System.out.println("\n");

            System.out.println("Count:");
            printLabel(labels);
            for(String label: labels){
                try {
                    System.out.print(df.getCount(label)+"\t");
                } catch (ExceptionNoSuchColumn exceptionNoSuchColumn) {
                    System.out.print("NULL\t");
                }
            }
            System.out.println("\n");

            System.out.println("Sum:");
            printLabel(labels);
            for(String label: labels){
                try {
                    System.out.print(df.getSum(label)+"\t");
                } catch (ExceptionNoSuchColumn exceptionNoSuchColumn) {
                    System.out.print("NULL\t");
                } catch (ExceptionString exceptionString) {
                    System.out.print("NULL\t");
                }
            }
            System.out.println();
            System.out.println("\n--GROUP BY--\n");
            if(df.getLabel().size()>2){
                try {
                    ArrayList<String> listGroupLabel = new ArrayList<String>();
                    listGroupLabel.add(labels.get(0));
                    listGroupLabel.add(labels.get(1));
                    DataFrameGrouped dfG = df.groupBy(listGroupLabel);


                    System.out.println("Average (Troisième Colonne):");
                    try {
                        System.out.print(dfG.avgPrint(labels.get(2)));
                    } catch (ExceptionString exceptionString) {
                        System.out.print("Mauvais Type.\t");
                    } catch (ExceptionColIsGrouped exceptionColIsGrouped) {
                        exceptionColIsGrouped.printStackTrace();
                    }
                    System.out.println("\n");

                    System.out.println("Maximum (Troisième Colonne):");
                    try {
                        System.out.print(dfG.maxPrint(labels.get(2)));
                    } catch (ExceptionColIsGrouped exceptionColIsGrouped) {
                        exceptionColIsGrouped.printStackTrace();
                    }
                    System.out.println("\n");

                    System.out.println("Minimum (Troisième Colonne):");
                    try {
                        System.out.print(dfG.minPrint(labels.get(2)));
                    } catch (ExceptionColIsGrouped exceptionColIsGrouped) {
                        exceptionColIsGrouped.printStackTrace();
                    }
                    System.out.println("\n");

                    System.out.println("Count:");
                    System.out.print(dfG.countPrint()+"\t");
                    System.out.println("\n");

                    System.out.println("Sum (Troisième Colonne):");
                    try {
                        System.out.print(dfG.sumPrint(labels.get(2)));
                    } catch (ExceptionString exceptionString) {
                        System.out.print("Mauvais Type.\t");
                    } catch (ExceptionColIsGrouped exceptionColIsGrouped) {
                        exceptionColIsGrouped.printStackTrace();
                    }


                } catch (ExceptionNoSuchColumn exceptionNoSuchColumn) {
                }
            }else{
                System.out.println("Impossible de réaliser des opérations de Group By sur ce DataFrame. Voir README si vous le souhaitez.");
            }

        } catch (IOException e) {
            System.err.println("Nous n'avons pas pu ouvrir le fichier \"file.csv\"");
        } catch (ExceptionNotSameSize exceptionNotSameSize) {
            System.err.println("Erreur les colonnes ne sont pas de la même taille");
        }


    }

    static public void printLabel(ArrayList<String> array){
	    for(String s: array){
            System.out.print(s + "\t");
        }
        System.out.println();
    }
}