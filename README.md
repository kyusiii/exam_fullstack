# Examen de la formation FullStack

**Lisez bien l'énoncé avant de vous lancer.**

## Introduction

Le guide Michelin a décidé de mettre à jour son application web permettant de gérer les
restaurants visités et leur classement.

Grâce à vos compétences, vous avez été choisis pour effectuer la partie backend en Springboot 3 de ce projet.

## Définitions

Un **restaurant** est caractérisé par :

- un identifiant unique (un nombre entier positif)
- Un nom (longueur max de 90 caractères)
- Une adresse (longueur max de 255 caractères)
- Une liste d'**évaluations**
- La moyenne de ses evaluations
- Zéro ou une évaluation finale
- Zéro ou une image d'illustration
- Zéro ou plusieurs tags

Une **évaluation** est caractérisée par :

- un identifiant unique (un nombre entier positif)
- Le nom de l'évaluateur (longueur max de 50 caractères)
- Le commentaire (longueur max de 255 caractères)
- Le nombre d'étoiles recommandé (0,1,2 ou 3) appelée "note"
- Une date de création
- Une date de mise à jour
- Zéro ou plusieurs photos qui permettent de mettre en avant les plats (délicieux ou pas)

Une **évaluation finale** qui correspond au résultat du restaurant qui sera publié dans le fameux guide

- Le nom du décideur final (longueur max de 90 caractères)
- le nombre final d'étoiles retenu (0,1,2 ou 3) appelée note 
- un texte descriptif des qualités du restaurant (taille virtuellement illimitée, pas un VARCHAR donc)

Les tags sont à choisir parmis une liste prédéfinie :
- Gastronomique
- Bistrot
- Bistronomique (oui, je me suis pas trop pris la tête sur celui-là)
- Brasserie
- Fastfood

Vous disposez de deux buckets :

- examillustrations pour stocker les illustrations des restaurants
- examphotos pour stocker les photos des plats

Les credentials du S3 :
- endpoint: http://92.222.10.101:9000
- accesskey: hI2na8J1hHFEN7u5ijvl
- secretkey: sUVdSRgYfwnstqsPFh9S4gE7qVyd59BqRI4dP9LE
- buckets

## Cahier des charges

L'application doit être constituée comme suit :

- Une page principale :

  - un tableau des **restaurants** avec, pour chaque :
    - son nom
    - son adresse
    - la moyenne du nombre d'étoiles recommandé pour ce restaurant (Si le restaurant n'a pas encore d'évaluation afficher "/" à la place)
    - un bouton permettant d'accéder à sa page de détails
    - les lignes dont le restaurant à un nombre d'étoiles recommandé moyen supérieur à 2 doivent s'afficher en jaune
    - les lignes dont le restaurant à un nombre d'étoiles recommandé moyen inférieur à 1 doivent s'afficher en rouge
    - Les lignes dont le restaurant n'a pas d'évaluation ne doivent pas être colorées
    - Ce tableau doit pouvoir être filtré par nom de restaurant (match exact sur le nom)
  - un formulaire permettant d'ajouter un **restaurant**
    - Nom : Obligatoire
    - Adresse (sur un seul champ) : Obligatoire

- Une page de détail (accessible depuis le tableau décrit précédemment) :
  - le nom du restaurant
  - son adresse
  - sa photo d'illustration
  - la liste de ses évaluations avec pour chaque :
    - L'auteur
    - Le commentaire
    - Le nombre d'étoiles
    - la liste des toutes les photos des plats
    - un bouton permettant de supprimer l'évaluation
  - un formulaire permettant d'ajouter une **évaluation** :
    - Nom: Obligatoire, 1 caractère minimum, 30 maximum
    - Commentaire : 255 caractères maximum
    - Nombre d'étoiles recommandé : Obligatoire, la valeur doit être 0, 1, 2 ou 3
  - un formulaire (identique au formulaire d'ajout) permettant d'éditer le restaurant

La liste initiale des **restaurants** doit être chargée via requête HTTP. Chaque changement sur un restaurant doit déclencher une requête HTTP appropriée :
POST pour l'ajout d'un nouveau restaurant, PUT pour une mise à jour.

#### Bonus

En vue d'améliorer la sécurité de son système d'information, le service CyberSecurité voudrait que vous **interceptiez** (ce mot est important) automatiquement
toutes les requêtes HTTP et vérifier que le header suivant est présent à chaque requête (envoi du header côté front, vérification que le header existe côté back) :
`Authorization: SUPER_MOT_DE_PASSE_TOP_SECRET`

(https://developer.mozilla.org/fr/docs/Web/HTTP/Headers/Authorization)

## Questions

L'équipe architecture aimerait que vous répondiez à quelques questions afin de valider vos choix techniques (avec une question implicite à chaque fois : pourquoi ? répondre juste "oui" ou "non" ne se sera pas considéré comme une réponse. La justification n'a pas nécessairement besoin d'être compliquée ou très longue) : 

- Concernant la possibilité de modifier une image d'illustration, avez-vous fait une route dédiée au fait de remplacer la photo ou bien considérez-vous qu'il faille simplement appeler la route de suppression puis la route d'ajout ? 
- Concernant le stockage des images, est-il préférable de mettre d'avoir une seule table contenant tous les IDs de toutes les images (et faire une jointure systématique donc) ou bien vaut-il mieux mettre l'ID de la photo d'illustration dans la table restaurant et ne pas mettre d'ID en DB pour l'image d'illustration d'un restaurant ?
- Lorsque vous retournez un restaurant, est-ce une bonne idée de retourner la photo systématiquement (via un lien bien sûr) ?
- Lorsque vous retournez un restaurant, est-ce une bonne idée de retourner l'évaluation finale systématiquement ?
- Expliquez l'implémentation que vous avez fait des tags (quelles solutions avez-vous envisagées et pourquoi avoir retenu la vôtre en particulier)

## Conseils supplémentaires

- La modélisation interne (DB) de votre application ne correspond pas forcément aux données en sortie de votre API.
- Pour éviter que vous ecrasiez les images les uns des autres, préfixez le nom de vos images avec un texte.
- Planifiez bien les tâches que vous devrez faire afin d'organiser au mieux vos dossiers et votre code dès le début.
- Le style de l'application ne sera bien sûr pas noté. Vous serez évalué sur les concepts que vous implémenterez dans votre application, pas sur le CSS.
- Il vaut mieux faire peu correctement que beaucoup salement.
- Vous ne faites pas du code seulement pour vous-même mais aussi pour qu'il puisse être compris par quelqu'un d'autre, notamment moi.
- Les commentaires expliquant ce que vous faites sont bien sûr les bienvenus.
- Vous avez accès à votre précédent travail et à internet.
- Le sujet est long et je le sais, pas de panique si vous n'arrivez pas à tout finir
- La question bonus est, comme son nom l'indique, un bonus. Ne la faites que si vous avez terminé tout le reste.