# Profil développeur – Amélioration UI

## Description

Cette application Android affiche le profil d'un développeur sur un seul écran Jetpack Compose.
L'écran présente un avatar avec badge, un nom, un rôle, une description, une liste de
compétences, une liste de projets et une zone de contact avec un bouton « Contacter ».
Toutes les données sont locales et écrites directement dans le code.

## Composants Compose utilisés

- `Text` : titres, descriptions, informations
- `Button` : action principale « Contacter » (le clic appelle un callback `onContactClick`)
- `Icon` : icônes dans le badge de l'avatar et dans les champs de contact
- `Image` : avatar placeholder (icône vectorielle `Person`)
- `Card` : une carte par projet (`ProjectCard`)
- `OutlinedTextField` en lecture seule : email, localisation et disponibilité
## Layouts utilisés

- `Column` : structure verticale de l'en-tête et de la zone de contact
- `Row` : alignement de l'avatar et du texte dans l'en-tête
- `Box` : superposition du badge sur l'avatar
- `Spacer` : espaces entre les éléments
- `Surface` : zone de l'en-tête et fond de l'avatar et du badge
- `LazyColumn` et `LazyRow` : listes
## Listes avec LazyColumn et LazyRow

- **`LazyColumn`** contient tout l'écran. Elle mélange des éléments uniques (`item` : en-tête,
  description, titres de section, contact) et une collection (`items`) : la liste des projets.
  Chaque projet est affiché avec le composable réutilisable `ProjectCard`, et `key = { projet.id }`
  donne une identité stable à chaque élément.
- **`LazyRow`** affiche les compétences horizontalement. Chaque compétence est affichée avec
  le composable `TechnologyChip`.
## Choix de thème

- **Couleurs** : deux palettes (`lightColorScheme` et `darkColorScheme`) en vert, choisies selon
  `isSystemInDarkTheme()`. Les composants utilisent
  `MaterialTheme.colorScheme` et jamais de couleur écrite en dur.
- **Typographie** : quelques styles personnalisés (`headlineMedium`, `titleLarge`, `titleMedium`,
  `bodyMedium`, `labelLarge`).
- **Formes** : des arrondis personnalisés (`MaterialTheme.shapes`).
- **Clair / sombre** : deux previews permettent de comparer les deux modes.

## Notions du chapitre Compose UI réutilisées

Composants : (`Text`, `Button`, `Icon`, `Image`, `Card`,
`OutlinedTextField`), layouts (`Column`, `Row`, `Box`, `Spacer`, `Surface`), listes modernes
(`LazyColumn`, `LazyRow`, `items`, `key`), Material Design 3 (`ColorScheme`, `Typography`,
`Shapes`) et thème clair / sombre.
 