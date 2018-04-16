# Application Java Panda sur fichier csv

### Réalisé par Maxime Isnel et Victor Baverel

## Requis

* Java 8
* Maven

## L'application java Panda

L'application utilise la librairie Java Panda (https://github.com/TheSpyGeek/DevOps_Project) à un moment donné. Elle n'utilise pas forcément la dernière version. L'application donne un exemple des fonctionnalités offertes par la librairie sur un fichier.

Pour l'utiliser il faut compiler l'application avec ```mvn package``` à la racine du projet, cela va vous créer un jar executable que vous pouvez executer avec la commande;
java -jar target/AppJavaPanda-1.0-jar-with-dependencies.jar

L'application va aller chercher le fichier "file.csv" dans le dossier courant pour faire les statistiques dessus.

Vous trouverez un exemple de ce à quoi peut ressembler "file.csv" dans le dossier FileTest/ au même niveau que src/

## Docker

##### Assurez vous de disposer de la dernière version de dockers -> https://docs.docker.com/install/

```sudo docker run --mount type=bind,source="$(pwd)"/FileTest,target=/AppJavaPanda/ressources thespygeek/javapanda```

Où FileTest est un dossier (chemin absolu) contenant un fichier "file.csv" sur lequel des stats vont être faites

Enfin, notez que nous tentons aussi d'effectuer des opérations de Group By sur le fichier, ces opérations ne provoqueront
pas d'erreur dans le cas où l'utilisateur n'a pas réaliser son fichier csv en conséquence.

Ainsi, nous réalisons un GroupBy sur la première et la seconde colonne si elles existent.
Puis, nous réalisons (quand le type nous le permet) des opérations d'aggrégations sur la troisième colonne.

(A noter que les contraintes ci-dessus ne s'applique que dans le contexte de l'utilisation de la librairie avec Docker)
