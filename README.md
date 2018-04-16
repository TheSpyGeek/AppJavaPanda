# Application Java Panda sur fichier csv

### Réalisé par Maxime Isnel et Victor Baverel


## Docker

#####Assurez vous de disposer de la dernière version de dockers -> https://docs.docker.com/install/

sudo docker run --mount type=bind,source=FileTest,target=/DevOps_Project/ressources thespygeek/javapanda

Où FileTest est un dossier (chemin absolu) contenant un fichier "file.csv" sur lequel des stats vont être faites

Vous trouverez un exemple de ce à quoi peut ressembler "file.csv" dans le dossier FileTest/ au même niveau que src/

Enfin, notez que nous tentons aussi d'effectuer des opérations de Group By sur le fichier, ces opérations ne provoqueront
pas d'erreur dans le cas où l'utilisateur n'a pas réaliser son fichier csv en conséquence.

Ainsi, nous réalisons un GroupBy sur la première et la seconde colonne si elles existent.
Puis, nous réalisons (quand le type nous le permet) des opérations d'aggrégations sur la troisième colonne.

(A noter que les contraintes ci-dessus ne s'applique que dans le contexte de l'utilisation de la librairie avec Docker)
