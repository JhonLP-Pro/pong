# Jeu de Pong en Java

## Description du jeu
Un jeu de Pong classique où deux joueurs s'affrontent avec des raquettes pour renvoyer une balle. Le premier joueur qui atteint 3 points gagne la partie.

## Comment jouer
- **Mode 1 joueur**: Jouez contre l'IA
- **Mode 2 joueurs**: Jouez contre un ami
- **Contrôles**:
  - Joueur 1 (gauche): W (haut) et S (bas)
  - Joueur 2 (droite): Flèches HAUT et BAS
  - L'IA contrôle automatiquement la raquette droite en mode 1 joueur

## Fonctionnalités
- Choix entre mode 1 joueur (vs IA) ou 2 joueurs
- La balle rebondit de façon aléatoire au contact des raquettes
- La vitesse de la balle augmente progressivement
- Score limité à 3 points pour gagner
- Option de rejouer après chaque partie

## Structure du code
Le jeu est divisé en 5 fichiers principaux :

1. **Pong.java**
   - Classe principale qui lance le jeu
   - Crée la fenêtre de jeu

2. **GamePanel.java**
   - Gère la logique principale du jeu
   - Contrôle les collisions et le score
   - Gère les entrées clavier

3. **Paddle.java**
   - Définit les raquettes des joueurs
   - Gère le mouvement des raquettes

4. **Ball.java**
   - Définit la balle
   - Gère le mouvement de la balle

5. **Score.java**
   - Gère l'affichage du score
   - Affiche le texte "Premier à 3 points"

## Comment lancer le jeu
1. Assurez-vous d'avoir Java installé
2. Ouvrez le terminal et naviguez jusqu'au répertoire contenant les fichiers Java:
   ```bash
   cd chemin/vers/le/repertoire
   ```
3. Compilez tous les fichiers:
   ```bash
   javac *.java
   ```
4. Lancez le jeu :
   ```bash
   java Pong
   ``` 