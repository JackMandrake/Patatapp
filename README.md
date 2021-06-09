# Patatapp

## Config
Notre application utilise la base de données H2 et donc aucune configuration à faire de la part du client.

## Fonctionnalitées
Après avoir lancé le serveur TomCat se rendre à l'adresse suivante :  
`http://localhost:8080/patatapp/`  

Sur la page d'accueil on retrouve une navbar qui permet d'afficher la liste des potagers et la liste des plantes. 

### Sur la page `Mes Plantes`
* Liste de toutes les plantes
* Ajouter une nouvelle plante
* Modifier une plante
* Supprimer une plante

Pour l'instant les plantes ont comme caractéristique une surface (cm²) et un nom.  
Une seule contrainte : surface >= 1 cm².

### Sur la page `Mes Potagers`
* Liste de tous les potagers
* Ajouter un nouveau potager
* Modifier un potager
* Supprimer un potager
* Voir les détails d'un potager => liste des carrés

Pour l'instant les potagers ont comme caractéristique une surface (m²) et un nom.  
Une seule contrainte : surface >= 1 m².

### Dans le détail d'un potager
* Liste de tous les carrés
* Ajouter un nouveau carré
* Modifier un carré
* Supprimer un carré
* Voir les détails d'un carré => liste des plantations

Pour l'instant les carrés ont comme caractéristique une surface (m²), un type de sol et une exposision.  
Deux contraintes :
 - surface >= 1 m²
 - surface total des carrés <= surface du potager

### Dans le détail d'un carré
* Liste de toutes les plantations
* Ajouter une nouvelle plantation

Pour l'instant les plantatations ont comme caractéristiques une plante (déjat existante) et une quantité.  
Une seule contrainte : 
surface totale des plantation (surface plante * quantité * nb plantation) <= surface du carré

## TODO
* JavaDoc
* Tests unitaire
* Rajouter tous les champs au entitées
* Rajouter toutes les contraintes
* Mettre en place les nouvelles spécifications
