# inf7210lucene

Exemple de création d'un index Lucene basé sur des données de films avec acteurs et synopsis écrit avec Kotlin.

## Installation

Le plus simple pour executer le projet est d'installer l'IDE IntelliJ IDEA qui offre le meilleur support pour Kotlin
à l'heure actuelle. L'édition communautaire gratuite est suffisante.

https://www.jetbrains.com/idea/download/

Si vous ne désirez pas installer IntelliJ alors d'autres moyens existent pour éxecuter l'application:

Depuis la ligne de commande pour compiler:

```mvn clean install``` (Suppose que Maven est installé) puis:

https://stackoverflow.com/questions/9355690/how-to-run-compiled-class-file-in-kotlin/26402542#26402542

Le projet utilise les fichiers textes présent dans le répertoire datasource pour créer l'index Lucene.
L'index sera créé dans le répertoire du même nom.

## LUKE

L'application offre la possibilité d'explorer un index Lucene à travers une interface graphique permettant
d'effectuer des requêtes, se balader sur les documents et toutes autres opérations de maintenance.

Télécharger une version ici: https://github.com/DmitryKey/luke/releases

Décompresser l'Archive puis:

```./luke.sh```(OS/X, Linux) ou ```luke.bat```(Windows) pour démarrer. Choisir le répertoire dans lequel se situe l'index créé à l'étape précédente.


