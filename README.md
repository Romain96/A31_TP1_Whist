# A31 - TP1 - Jeu de Whist

Auteur : Romain PERRIN - Ingénieur de recherche - Cancéropôle Est / ICube équipe SDS (romain.perrin@etu.unistra.fr)\
Module : DUT S3 - A31 - Programmation et Conception Objet Avancées - IUT Robert SCHUMAN - 2020/21

## Description

### Composition du jeu

Le Whist se joue à **4** joueurs en **2** équipes croisées de deux joueurs.\
Il se joue avec **un** paquet de **52** cartes.\
Le paquet contient **4** couleurs : *trèfle*, *coeur*, *carreau* et *pique*.\
L'ordre décroissant des cartes pour chaque couleur est : *As*, *Roi*, *Dame*, *Valet*, *10*, *9*, *8*, *7*, *6*, *5*, *4*, *3*, *2*.\
Les cartes sont distribuées dans le sens **horaire**.

### Distribution des cartes

L'un des joueurs est le *distributeur* et il distribue *13* cartes une par une à chaque joueur suivant le sens **horaire**.\
Il retourne la **52e carte** qui lui appartient (il commence par le joueur à sa gauche et termine par lui).
Cette carte devient **l'atout** pour le tour en cours qui est appelé une **levée**.\
Chaque joueur à tour de rôle pose une carte.

### Première levée (premier tour de table)

Le joueur doit obligatoirement poser une carte de la **couleur** de l'**atout** sauf s'il ne dispose d'aucune carte de cette couleur.\
Il a alors le doit de poser **n'importe** quelle carte indépendamment de son rang et de sa couleur.\
Les joueurs ne sont pas obligés de jouer une carte gagnante (il peuvent donc jouer une carte inférieure à celles actuellement posées).

### Déroulement du jeu

Lorsque les **4** joueurs ont joués, on définit le gagnant de la levée.\
S'il n'y a **aucun atout** posé (As, Roi, Dame, Valet) alors le gagnant est celui ayant posé la carte de **rang le plus elevé** et de la **couleur demandée**.\
Sinon c'est celui ayant posé **l'atout de rang le plus élevé** indépendamment de la couleur qui remporte la levée.\
Le paquet de **4** cartes qui viennent d'être jouées est appelé un **pli** et il est remporté par **l'équipe du joueur gagnant**.\
Pour la levée suivante, c'est le **joueur ayant gagné la dernière levée** qui pose une carte en premier (atout de la levée).\
Les manches suivantes se déroulent toujours selon le même principe (premier à jouer = gagnant précédent, sens de jeu = sens horaire).

### Vainqueur(s)

En fin de partie (après la 13e levée) on définit l'équipe gagnante en comptant les points.\
Chaque **pli** gagné fait remporter **1 point** à son équipe; les **6 premiers plis** ne comptent pas.


## Note diverses

### Organisation du code

Le code est séparé en **3 packages** :
* *WhistModel* - contient les classes du projet (Joueur, Carte, Pli...)
* *WhistView* - contient les vues (interfaces graphiques)
* *WhistControler* - contient le gestionnaire de partie et la gestion des signaux (boutons)


### Gestion des écouteurs

Les écouteurs (écoutent les boutons *distribuer une carte* et *jouer une carte*) sont implmentés en utilisant une classe externe à la classe de l'interface joueur etou distributeur.\
Les classes contenant l'inplmentation des écouteurs sont **SignalDistribuerCarte** et **SignalJouerCrate**.\
Ces objets ont besoin de connaître à la fois l'instance de l'interface dans laquelle ils écoutent un composant spécifique (JButton en l'occurence) mais aussi un objet de type **GestionnairePartie** qui fait office de contrôleur principal (gestion de la partie, des levées etc).
