let recipes = []; // Tableau pour stocker les recettes

// Fonction pour rafraîchir la liste des recettes
function refreshRecipeTable(filteredRecipes = recipes) {
    const tableBody = document.getElementById('recipeTable').querySelector('tbody');
    tableBody.innerHTML = ''; // Réinitialiser la table

    filteredRecipes.forEach(recipe => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${recipe.id}</td>
            <td>${recipe.name}</td>
            <td>${recipe.ingredient}</td>
            <td>${recipe.quantity}</td>
            <td>${recipe.unit}</td>
            <td>
                <button class="btn btn-warning" onclick="editRecipe(${recipe.id})">Modifier</button>
                <button class="btn btn-danger" onclick="deleteRecipe(${recipe.id})">Supprimer</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

// Fonction pour ajouter une recette
document.getElementById('addRecipe').addEventListener('click', () => {
    const name = document.getElementById('recipeName').value;
    const ingredient = document.getElementById('ingredientName').value;
    const quantity = document.getElementById('ingredientQuantity').value;
    const unit = document.getElementById('ingredientUnit').value;

    if (name && ingredient && quantity && unit) {
        const recipe = {
            id: recipes.length + 1, // ID unique
            name,
            ingredient,
            quantity,
            unit
        };

        recipes.push(recipe);
        refreshRecipeTable();
        document.getElementById('recipeForm').reset();
    } else {
        alert('Veuillez remplir tous les champs.');
    }
});

// Fonction pour modifier une recette
function editRecipe(id) {
    const recipe = recipes.find(r => r.id === id);
    if (recipe) {
        document.getElementById('recipeName').value = recipe.name;
        document.getElementById('ingredientName').value = recipe.ingredient;
        document.getElementById('ingredientQuantity').value = recipe.quantity;
        document.getElementById('ingredientUnit').value = recipe.unit;
    }
}

// Fonction pour supprimer une recette
function deleteRecipe(id) {
    recipes = recipes.filter(r => r.id !== id);
    refreshRecipeTable();
}