# SimpleCashV4_Bunlon_Marvin

SimpleCash est une application bancaire réalisée en Spring Boot permettant de gérer :

les clients,
leurs comptes bancaires (courant / épargne),
les virements,
les conseillers,
les gérants,
les agences

Elle expose une API REST documentée via Swagger et utilise une Base de données H2 en mémoire pour une exécution immédiate sans installation supplémentaire.

Dans ce README, vous allez être accompagné pour voir la documentation, tester l'application, ainsi que d'autres lien seront disponible pour favoriser la compréhension du projet. Bonne lecture ! :)

✅ **1. Installation & Lancement**

Prérequis :

Java 17+
Maven 3+
Un IDE (IntelliJ recommandé)

Commençons par cloner le projet : 
Ouvrez un terminal dans l'IDE

git clone https://github.com/MarvinBunlon/SimpleCashV4_Bunlon_Marvin.git

cd SimpleCashV4_Bunlon_Marvin

Et cliquer sur l'icone pour build le projet.

ou alors taper la commande : 

mvn spring-boot:run


BIEN, maintenant l'application tourne ! Félicitations.
Pour les deux étapes suivantes qui contiennent des liens, je vous conseille de les ouvrir l'un à coté de l'autre.

✅ 2.Documentation API (Swagger)

La documentation Swagger est automatiquement générée et accessible à :

http://localhost:8080/swagger-ui.html

Vous y trouverez :

toutes les routes de l’API
les modèles JSON
la possibilité de tester les requêtes directement dans le navigateur

✅ 3. Base de données H2 (type adminer)

Une base H2 embarquée est utilisée.
Console accessible ici :

http://localhost:8080/h2-console

Champ	    Valeur
JDBC URL	jdbc:h2:mem:testdb
User	    sa
Password	


Pour comprendre les différentes User Stories du projet, un lien Trello est disponible juste ici :
Trello Link for US : https://trello.com/invite/b/69295c1996063c526a8235c5/ATTId61124248a084b52985bcf77191f627dAE63736E/simplecashv4

Et si avant de tester nos fonctionnalités, nous résumions ce que le projet peut apporter ?

**----- BILAN GENERAL DU PROJET -----**

**Objectif général du système**

SimpleCash est un système bancaire simplifié permettant de gérer les interactions entre les clients, leurs comptes bancaires et les acteurs de gestion interne (conseillers, gérants, agences).
Il met à disposition une API REST complète permettant d’effectuer toutes les opérations bancaires classiques, ainsi que la gestion organisationnelle d’une agence bancaire.

✅ 1. Gestion des Clients

L’application permet de gérer le cycle de vie complet d’un client :

Fonctionnalités disponibles lié au client :

Créer un client
Consulter un client
Modifier les informations d’un client
Supprimer un client
Lister tous les clients
Dépendances client → banque

Un client peut posséder :

0 ou 1 Compte Courant
0 ou 1 Compte Épargne
Un seul Conseiller

Les comptes sont liés au client automatiquement lors de leur création.

✅ 2. Gestion des Comptes Bancaires

Deux types de comptes sont disponibles :

Compte Courant --- 1000€ de découvert disponible.
Compte Epargne --- taux de base à 3%


Fonctionnalités :

Créer un compte pour un client (via un paramètre type)

Consulter tous les comptes d’un client
Consulter un compte
Créditer un compte
Débiter un compte
Effectuer un virement entre deux comptes

Règles métier intégrées :

Crédit :
Ajoute un montant au solde.

Débit :

Compte Courant → peut descendre jusqu’à -1000 €
Compte Épargne → solde jamais négatif

Virement :
Assure automatiquement :

le débit du compte source (avec validation du découvert / épargne)
le crédit du compte cible
le tout dans une transaction unique (@Transactional)
Si une opération échoue, le virement entier est annulé (atomicité bancaire).

✅ 3. Gestion des Conseillers

Un conseiller bancaire est responsable d’un portefeuille de clients et dépend d’une agence.

Fonctionnalités :

Créer / lire / mettre à jour / supprimer un conseiller

Affecter un conseiller à :
une agence
un gérant

Associer un client à un conseiller (gestion du portefeuille)
Retirer un client du conseiller (optionnel si tu veux l’ajouter)
Changer le conseiller d’un client

- Relations
Un conseiller appartient à une agence
Un conseiller dépend d’un gérant
Un conseiller a plusieurs clients

✅ 4. Gestion des Gérants

Le gérant supervise un ensemble de conseillers au sein d’une agence.

Fonctionnalités :

Créer / lire / modifier / supprimer un gérant
Associer un gérant à une agence
Associer un gérant à un conseiller
Modifier les informations du gérant

Relations :

Un gérant gère plusieurs conseillers
Une agence possède un seul gérant
Les conseillers peuvent être réassignés à un autre gérant


✅ 5. Gestion des Agences

L’agence est l’entité centrale regroupant gérant, conseillers et clients indirectement.

Fonctionnalités :

Créer / lire / modifier / supprimer une agence
Assigner un gérant à une agence
Assigner ou retirer un conseiller d’une agence
Lister les conseillers de l’agence
Lister le gérant

Relations :

Une agence possède un gérant
Une agence possède plusieurs conseillers
Les clients sont liés aux conseillers → donc indirectement à l’agence

✅ 6. Relation globale du système (résumé)

Gérant
Plusieurs Conseillers
1 Agence

Conseiller
Plusieurs Clients
1 Agence
1 Gérant

Client
0/1 Compte Courant
0/1 Compte Épargne
1 Conseiller

Compte
appartient à 1 seul Client (logique me direz vous, mais important de le souligner)


**Voici le Diagramme UML :**

https://drive.google.com/file/d/1B2U6-wk7X5ku8ac-H4_uZrjVXRinOPly/view?usp=drive_link

**Et le diagramme de l'architecture :**

https://drive.google.com/file/d/1u3jANI8LGTQb5_ILbXHLenZQTnyhdy7S/view?usp=drive_link


Si vous voulez tester les routes voici quelques exemples que vous pouvez rentrer :
Dans swagger, choissisez votre route, cliquez dessus pour l'ouvrir, bouton "try it out", et en fonction mettez les données demandés.

🚀 Créer un client :

{
"nom": "Dupont",
"prenom": "Jean",
"adresse": "12 rue de la République",
"codePostal": "75001",
"ville": "Paris",
"telephone": "0601020304"
}

🚀 Modifier un client : 

{
"nom": "Durand",
"prenom": "Paul",
"adresse": "20 avenue de Lyon",
"codePostal": "69000",
"ville": "Lyon",
"telephone": "0605050505"
}

PENSEZ A RECUPERER L'ID CLIENT DANS LA BASE DE DONNEES : PAGE H2 : Cliquez sur la table voulu (client ici), puis RUN.
Vous verrez les clients que vous avez crée, et donc récupérer l'id pour créer des comptes liés à des conseillers etc ...
Amusez-vous !

🚀 Créer un compte pour un client :

type = 1 (compte courant)
type = 2 (compte épargne)

🚀 Voir les comptes d’un client :

Rien, rentrez juste l'id de votre client !

Vous avez normalement tous les types de texte à mettre dans les routes pour les tester.
Bon tests !


**Bilan du projet**

✅ Fonctionnalités réalisées

Clients : création, modification, suppression, récupération + attribution à un conseiller.
Comptes : création via une route unique (courant/épargne), crédit, débit, virement, règles métier (découvert, solde positif).
Conseillers : CRUD + affectation clients, agence et gérant.
Gérants : CRUD + gestion d’une agence et supervisation de conseillers.
Agences : CRUD + affectation/ retrait de conseillers + association d’un gérant.
Swagger activé avec toutes les routes documentées.
UML représentant clairement l’architecture métier.

⚠️ Difficultés rencontrées
Boucles JSON liées aux relations bidirectionnelles
→ Résolu avec @JsonIgnore / @JsonBackReference.

Conflits JPA sur Compte / Client
→ Refonte des relations OneToOne pour éviter les erreurs Hibernate.

Routes doublons pour les comptes
→ Simplifié avec une seule route : /clients/{id}/comptes?type=1|2.

Relations complexes Agence ↔ Gerant ↔ Conseiller
→ Clarification des cardinalités et mise à jour des services.

🚀 Reste à faire
Ajouter Spring Security (rôles : admin, gérant, conseiller, client).
Historique des opérations bancaires.
Tests unitaires (JUnit + MockMvc).
Eventuelle interface front (Angular/React).

Merci d'avoir lu et testé !