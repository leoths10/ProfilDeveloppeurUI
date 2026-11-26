package com.example.profildeveloppeur

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDeveloppeurTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfilScreen(
                        profil = sampleProfil(),
                        onContactClick = {},
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


data class ProjetUi(
    val id: Int,
    val titre: String,
    val description: String,
    val technologies: String
)

data class ProfilUi(
    val nom: String,
    val role: String,
    val description: String,
    val competences: List<String>,
    val projets: List<ProjetUi>,
    val email: String,
    val localisation: String,
    val disponibilite: String
)

fun sampleProfil(): ProfilUi{
    return ProfilUi(
        nom = "Léo THOMAS",
        role = "Developpeur Full-Stack",
        description = "Etudiant en Master 1 Ingénierie du web à l'ESGI de Tours, " +
                "actuellement en alternance",
        competences = listOf("Typescript", "PHP", "Python", "Kotlin", "Git"),
        projets = listOf(
            ProjetUi(
                id = 1,
                titre = "Site e-commerce",
                description = "Boutique en ligne avec panier et espace client",
                technologies = "PHP -- MySQL"
            ),
            ProjetUi(
                id = 2,
                titre = "Application de tâches",
                description = "Gestion de tâches avec filtres et rappels",
                technologies = "TypeScript -- React"
            ),
            ProjetUi(
                id = 3,
                titre = "Profil développeur",
                description = "Application Android réalisée avec Jetpack Compose.",
                technologies = "Kotlin • Compose"
            )
        ),
        email = "leo.thomas@example.com",
        localisation = "Tours, France",
        disponibilite = "Actuellement en alternance"
    )
}

@Composable
fun TechnologyChip(label: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun ProjetCard(projet: ProjetUi, modifier: Modifier = Modifier){
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = projet.titre,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = projet.technologies,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = projet.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TechnologyChipPreview(){
    ProfilDeveloppeurTheme {
        TechnologyChip(label = "Kotlin")
    }
}

@Preview(showBackground = true)
@Composable
fun ProjetCardPreview(){
    ProfilDeveloppeurTheme {
        ProjetCard(projet = sampleProfil().projets.first())
    }
}

@Composable
fun ProfilAvatar(modifier: Modifier = Modifier){
    Box(modifier = modifier.size(96.dp)){
        Surface(
            modifier = Modifier.fillMaxSize(),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.secondary
        ) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = "Avatar du développeur",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondary),
                modifier = Modifier.padding(16.dp)
            )
        }
        Surface(
            modifier = Modifier
                .size(28.dp)
                .align(Alignment.BottomEnd),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilAvatarPreview(){
    ProfilDeveloppeurTheme {
        ProfilAvatar()
    }
}

@Composable
fun ContactField(
    label: String,
    valeur: String,
    icone: ImageVector,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = valeur,
        onValueChange = {},
        readOnly = true,
        label = { Text(text = label)},
        leadingIcon = {
            Icon(imageVector = icone, contentDescription = null)
        },
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun ContactSection(
    profil : ProfilUi,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Contact",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        ContactField(label = "Email", valeur = profil.email, icone = Icons.Default.Email)
        Spacer(modifier = Modifier.height(8.dp))
        ContactField(label = "Localisation", valeur = profil.localisation, icone = Icons.Default.LocationOn)
        Spacer(modifier = Modifier.height(8.dp))
        ContactField(label = "Disponibilité", valeur = profil.disponibilite, icone = Icons.Default.Check)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onContactClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Contacter")
        }
    }
}

@Preview
@Composable
fun ContactSectionPreview(){
    ProfilDeveloppeurTheme {
        ContactSection(profil = sampleProfil(), onContactClick = {})
    }
}

@Composable
fun SectionTitle(texte: String, modifier: Modifier = Modifier){
    Text(
        text = texte,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    )
}

@Composable
fun ProfilHeader(profil: ProfilUi, modifier: Modifier = Modifier){
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 3.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfilAvatar()
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = profil.nom,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = profil.role,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
fun ProfilScreen(
    profil: ProfilUi,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item { ProfilHeader(profil = profil) }
        item {
            Text(
                text = profil.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
        item { SectionTitle(texte = "Compétences") }
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(profil.competences) {competence ->
                    TechnologyChip(label = competence)
                }
            }
        }
        item { SectionTitle(texte = "Projets") }
        items(profil.projets, key = { projet -> projet.id }) { projet ->
            ProjetCard(projet = projet)
        }
        item {
            ContactSection(profil = profil, onContactClick = onContactClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilScreenPreview() {
    ProfilDeveloppeurTheme {
        ProfilScreen(profil = sampleProfil(), onContactClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilScreenDarkPreview(){
    ProfilDeveloppeurTheme(darkTheme = true) {
        ProfilScreen(profil = sampleProfil(), onContactClick = {})
    }
}