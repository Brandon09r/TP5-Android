package com.example.tp_05_rossi_brandon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tp_05_rossi_brandon.R
import com.example.tp_05_rossi_brandon.ui.theme.TP_05_Rossi_BrandonTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Calculer la taille de la fenêtre
            val windowSizeClass = calculateWindowSizeClass(activity = this)

            // Appeler le composable principal
            MyGameApp(windowSize = windowSizeClass)
        }
    }
}

@Composable
fun MyGameApp(windowSize: WindowSizeClass) {
    // Détermine la largeur de la fenêtre
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            // Mode portrait (Compact)
            MyGameAppPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
            // Mode paysage (Expanded)
            MyGameAppLandscape()
        }
        else -> {
            // Par défaut, utiliser la mise en page portrait
            MyGameAppLandscape()
        }
    }
}


// Prévisualisation en mode portrait (Compact)
@Preview(showBackground = true, widthDp = 360)
@Composable
fun MyGameAppPortraitPreview() {
    MyGameAppPortrait()
}

// Prévisualisation en mode paysage (Expanded)
@Preview(showBackground = true, widthDp = 720)
@Composable
fun MyGameAppLandscapePreview() {
    MyGameAppLandscape()
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        placeholder = { Text("Rechercher") },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun SearchBarPreview() {
    TP_05_Rossi_BrandonTheme {
        SearchBar(Modifier.padding(8.dp))
    }
}

@Composable
fun GameCategoryElement(
    imageRes: Int, // Ressource de l'image dynamique
    text: String,  // Texte dynamique
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(8.dp), // Ajout d'un padding global
        horizontalAlignment = Alignment.CenterHorizontally // Alignement horizontal
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null, // Pas de description pour les images décoratives
            modifier = Modifier
                .size(88.dp) // Taille de l'image
                .clip(CircleShape), // Forme circulaire
            contentScale = androidx.compose.ui.layout.ContentScale.Crop // Adapter l'image
        )
        Spacer(modifier = Modifier.height(24.dp)) // Espacement avec le texte
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium, // Style typographique
            modifier = Modifier.padding(bottom = 8.dp) // Espacement sous le texte
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun GameCategoryElementPreview() {
    GameCategoryElement(
        imageRes = R.drawable.moba_category, // Image codée en dur
        text = "MOBA Games", // Texte codé en dur
        modifier = Modifier.fillMaxWidth() // Utilisation de tout l'espace parent
    )
}

@Composable
fun FavoriteCollectionCard(
    imageRes: Int, // Ressource dynamique pour l'image
    text: String,  // Texte dynamique
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(8.dp), // Coins arrondis
        color = MaterialTheme.colorScheme.surfaceVariant, // Couleur de fond
        modifier = modifier
            .size(width = 255.dp, height = 80.dp) // Taille de la carte
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, // Alignement vertical des enfants
            modifier = Modifier.fillMaxSize().padding(8.dp) // Remplir et ajouter du padding
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = null, // Pas de description car purement visuel
                modifier = Modifier
                    .size(80.dp) // Taille de l'image
                    .clip(RoundedCornerShape(8.dp)) // Coins arrondis
            )
            Spacer(modifier = Modifier.width(16.dp)) // Espacement entre l'image et le texte
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium // Style du texte
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionCardPreview() {
    FavoriteCollectionCard(
        imageRes = R.drawable.lol_game, // Exemple d'image
        text = "League of Legends",     // Exemple de texte
        modifier = Modifier.padding(8.dp) // Ajout de padding pour l'aperçu
    )
}

data class GameCategory(val drawable: Int, val text: String)

@Composable
fun GameCategoriesRow(
    categories: List<GameCategory>, // Liste des catégories dynamiques
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(), // La ligne occupe toute la largeur
        contentPadding = PaddingValues(horizontal = 16.dp), // Marges internes à gauche et à droite
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp) // Espacement entre les éléments
    ) {
        items(categories) { category ->
            GameCategoryElement(
                imageRes = category.drawable,
                text = category.text,
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun GameCategoriesRowPreview() {
    val sampleCategories = listOf(
        GameCategory(drawable = R.drawable.moba_category, text = "MOBA"),
        GameCategory(drawable = R.drawable.fps_category, text = "FPS"),
        GameCategory(drawable = R.drawable.platform_category, text = "Platforme"),
        GameCategory(drawable = R.drawable.sports_category, text = "Sport"),
        GameCategory(drawable = R.drawable.adventure_category, text = "Aventure"),
        GameCategory(drawable = R.drawable.rpg_category, text = "RPG")
    )

    GameCategoriesRow(
        categories = sampleCategories,
        modifier = Modifier.padding(vertical = 8.dp) // Padding vertical pour l'aperçu
    )
}

data class FavoriteCollection(val drawable: Int, val text: String)

@Composable
fun FavoriteCollectionsGrid(
    collections: List<FavoriteCollection>, // Liste des collections dynamiques
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2), // 2 lignes fixes dans la grille
        modifier = modifier
            .height(168.dp), // Hauteur fixe de la grille
        contentPadding = PaddingValues(horizontal = 16.dp), // Padding avant et après la grille
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp), // Espacement horizontal
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp) // Espacement vertical
    ) {
        items(collections) { collection ->
            FavoriteCollectionCard(
                imageRes = collection.drawable,
                text = collection.text,
                modifier = Modifier.height(80.dp) // Hauteur maximale de chaque carte
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionsGridPreview() {
    val sampleCollections = listOf(
        FavoriteCollection(drawable = R.drawable.lol_game, text = "League of Legends"),
        FavoriteCollection(drawable = R.drawable.hades_2_game, text = "Hades"),
        FavoriteCollection(drawable = R.drawable.zelda_game, text = "Minecraft"),
        FavoriteCollection(drawable = R.drawable.diablo_4_game, text = "Diablo 4")
    )

    FavoriteCollectionsGrid(
        collections = sampleCollections,
        modifier = Modifier
    )
}

@Composable
fun HomeSection(
    title: String, // Titre de la section
    content: @Composable () -> Unit, // Contenu de la section
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp) // Marges horizontales
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium, // Style typographique
            modifier = Modifier.padding(bottom = 40.dp) // Espacement sous le titre
        )
        content() // Emplacement pour le contenu dynamique
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    HomeSection(
        title = "Game Categories",
        content = {
            Spacer(modifier = Modifier.size(100.dp)) // Exemple de contenu (placeholder)
        }
    )
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // Utilisation de verticalScroll pour permettre le défilement
    val scrollState = rememberScrollState()

    // Définir les données globales (par exemple en haut du fichier)
    val gameCategories = listOf(
        GameCategory(drawable = R.drawable.moba_category, text = "MOBA"),
        GameCategory(drawable = R.drawable.fps_category, text = "FPS"),
        GameCategory(drawable = R.drawable.platform_category, text = "Platforme"),
        GameCategory(drawable = R.drawable.sports_category, text = "Sport"),
        GameCategory(drawable = R.drawable.adventure_category, text = "Aventure"),
        GameCategory(drawable = R.drawable.rpg_category, text = "RPG")
    )

    val favoriteCollections = listOf(
        FavoriteCollection(drawable = R.drawable.lol_game, text = "League of Legends"),
        FavoriteCollection(drawable = R.drawable.hades_2_game, text = "Hades"),
        FavoriteCollection(drawable = R.drawable.zelda_game, text = "Zelda"),
        FavoriteCollection(drawable = R.drawable.diablo_4_game, text = "Diablo 4")
    )


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState) // Permet de scroller verticalement
    ) {
        Spacer(modifier = Modifier.height(16.dp)) // Espacement avant la barre de recherche

        // Barre de recherche
        SearchBar(modifier = Modifier.padding(horizontal = 16.dp))

        Spacer(modifier = Modifier.height(16.dp)) // Espacement après la barre de recherche

        // Section "Game Categories"
        HomeSection(
            title = "Game Categories",
            content = {
                GameCategoriesRow(categories = gameCategories) // Utilisation de GameCategoriesRow
            }
        )

        Spacer(modifier = Modifier.height(16.dp)) // Espacement entre les sections

        // Section "Favorite Collections"
        HomeSection(
            title = "Favorite Collections",
            content = {
                FavoriteCollectionsGrid(collections = favoriteCollections) // Utilisation de FavoriteCollectionsGrid
            }
        )

        Spacer(modifier = Modifier.height(16.dp)) // Espacement au bas de l'écran
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun ScreenContentPreview() {
    HomeScreen()
}

@Composable
private fun GameBottomNavigation(modifier: Modifier = Modifier) {
    // Utilisation de la couleur surfaceVariant pour l'arrière-plan de la barre de navigation
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant // Applique la couleur surfaceVariant
    ) {
        // Premier élément : Home
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_home), // Remplacez par une icône dans votre projet
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") },
            selected = true, // Cet élément est sélectionné
            onClick = {
                // Action lors du clic sur cet élément, ex: navigation vers l'écran "Home"
            }
        )

        // Deuxième élément : Search
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search), // Remplacez par une icône dans votre projet
                    contentDescription = "Search"
                )
            },
            label = { Text("Search") },
            selected = false, // Cet élément n'est pas sélectionné
            onClick = {
                // Action lors du clic sur cet élément, ex: navigation vers l'écran "Search"
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GameBottomNavigationPreview() {
    GameBottomNavigation() // Prévisualisation de la barre de navigation inférieure
}

@Composable
fun MyGameAppPortrait(modifier: Modifier = Modifier) {
    // Définir les données globales (par exemple en haut du fichier)
    val gameCategories = listOf(
        GameCategory(drawable = R.drawable.moba_category, text = "MOBA"),
        GameCategory(drawable = R.drawable.fps_category, text = "FPS"),
        GameCategory(drawable = R.drawable.platform_category, text = "Platforme"),
        GameCategory(drawable = R.drawable.sports_category, text = "Sport"),
        GameCategory(drawable = R.drawable.adventure_category, text = "Aventure"),
        GameCategory(drawable = R.drawable.rpg_category, text = "RPG")
    )

    val favoriteCollections = listOf(
        FavoriteCollection(drawable = R.drawable.lol_game, text = "League of Legends"),
        FavoriteCollection(drawable = R.drawable.hades_2_game, text = "Hades"),
        FavoriteCollection(drawable = R.drawable.zelda_game, text = "Zelda"),
        FavoriteCollection(drawable = R.drawable.diablo_4_game, text = "Diablo 4")
    )

    TP_05_Rossi_BrandonTheme { // Applique le thème de l'application
        Scaffold(
            bottomBar = {
                // Bar de navigation inférieure
                GameBottomNavigation(modifier = Modifier) // Utilisez votre composable GameBottomNavigation ici
            }
        ) { padding -> // padding est utilisé pour gérer les marges liées à la barre de statut et de navigation
            // Contenu principal de l'application
            Column(
                modifier = modifier.padding(padding) // Applique le padding pour éviter que le contenu soit masqué
            ) {
                // Barre de recherche en haut
                SearchBar(modifier = Modifier.padding(horizontal = 16.dp))

                Spacer(modifier = Modifier.height(16.dp)) // Espacement entre la barre de recherche et le contenu

                // Section "Game Categories"
                HomeSection(
                    title = "Game Categories",
                    content = {
                        GameCategoriesRow(categories = gameCategories) // Remplir avec la liste de catégories de jeux
                    }
                )

                Spacer(modifier = Modifier.height(16.dp)) // Espacement entre les sections

                // Section "Favorite Collections"
                HomeSection(
                    title = "Favorite Collections",
                    content = {
                        FavoriteCollectionsGrid(collections = favoriteCollections) // Remplir avec les collections favorites
                    }
                )
            }
        }
    }
}

@Composable
private fun GameNavigationRail(modifier: Modifier = Modifier) {
    NavigationRail(
        modifier = modifier.padding(vertical = 8.dp), // Marge intérieure de 8 dp
        containerColor = MaterialTheme.colorScheme.surfaceVariant // Applique la couleur surfaceVariant
    ) {
        // Utilisation de Column pour organiser les éléments du rail
        Column(
            modifier = Modifier.fillMaxHeight(), // La colonne occupe toute la hauteur disponible
            horizontalAlignment = Alignment.CenterHorizontally, // Centrer horizontalement
            verticalArrangement = Arrangement.Center // Centrer verticalement
        ) {
            // Premier élément : Home
            NavigationRailItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_home), // Remplacez par une icône de votre choix
                        contentDescription = "Home"
                    )
                },
                label = { Text("Home") },
                selected = true, // Cet élément est sélectionné
                onClick = {
                    // Action lors du clic sur cet élément, ex: navigation vers l'écran "Home"
                }
            )

            Spacer(modifier = Modifier.height(8.dp)) // Ajouter un espacement de 8 dp entre les éléments

            // Deuxième élément : Search
            NavigationRailItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search), // Remplacez par une icône de votre choix
                        contentDescription = "Search"
                    )
                },
                label = { Text("Search") },
                selected = false, // Cet élément n'est pas sélectionné
                onClick = {
                    // Action lors du clic sur cet élément, ex: navigation vers l'écran "Search"
                }
            )
        }
    }
}

@Composable
fun MyGameAppLandscape(modifier: Modifier = Modifier) {
    // Définir les données globales (par exemple en haut du fichier)
    val gameCategories = listOf(
        GameCategory(drawable = R.drawable.moba_category, text = "MOBA"),
        GameCategory(drawable = R.drawable.fps_category, text = "FPS"),
        GameCategory(drawable = R.drawable.platform_category, text = "Platforme"),
        GameCategory(drawable = R.drawable.sports_category, text = "Sport"),
        GameCategory(drawable = R.drawable.adventure_category, text = "Aventure"),
        GameCategory(drawable = R.drawable.rpg_category, text = "RPG")
    )

    val favoriteCollections = listOf(
        FavoriteCollection(drawable = R.drawable.lol_game, text = "League of Legends"),
        FavoriteCollection(drawable = R.drawable.hades_2_game, text = "Hades"),
        FavoriteCollection(drawable = R.drawable.zelda_game, text = "Zelda"),
        FavoriteCollection(drawable = R.drawable.diablo_4_game, text = "Diablo 4")
    )
    TP_05_Rossi_BrandonTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                // NavigationRail à gauche
                GameNavigationRail(
                    modifier = Modifier
                        .width(80.dp) // Largeur du rail
                        .fillMaxHeight() // Remplit toute la hauteur
                )

                // Contenu principal à droite
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp) // Padding autour du contenu
                ) {
                    // Barre de recherche
                    SearchBar(modifier = Modifier.padding(horizontal = 16.dp))

                    Spacer(modifier = Modifier.height(16.dp)) // Espacement

                    // Section "Game Categories"
                    HomeSection(
                        title = "Game Categories",
                        content = {
                            GameCategoriesRow(categories = gameCategories) // Liste des catégories
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp)) // Espacement

                    // Section "Favorite Collections"
                    HomeSection(
                        title = "Favorite Collections",
                        content = {
                            FavoriteCollectionsGrid(collections = favoriteCollections) // Grille des collections
                        }
                    )
                }
            }
        }
    }
}

