// Initialisation des ingrédients
let ingredients = [];

// Ajouter un ingrédient
document.getElementById('addIngredient').addEventListener('click', () => {
    const selectedIngredients = [];
    
    // Vérifier quelles cases sont sélectionnées
    if (document.getElementById('ingredient1').checked) selectedIngredients.push(document.getElementById('ingredient1').value);
    if (document.getElementById('ingredient2').checked) selectedIngredients.push(document.getElementById('ingredient2').value);
    if (document.getElementById('ingredient3').checked) selectedIngredients.push(document.getElementById('ingredient3').value);
    if (document.getElementById('ingredient4').checked) selectedIngredients.push(document.getElementById('ingredient4').value);

    const quantity = document.getElementById('quantity').value;
    const unit = document.getElementById('unit').value;

    // Ajouter uniquement si des ingrédients sont sélectionnés
    if (selectedIngredients.length > 0 && quantity && unit) {
        selectedIngredients.forEach(ingredient => {
            ingredients.push({ name: ingredient, quantity, unit });
        });
        refreshTable();
        document.getElementById('ingredientForm').reset();
    } else {
        alert('Veuillez sélectionner des ingrédients et remplir tous les champs.');
    }
});

// Rafraîchir la table des ingrédients
function refreshTable() {
    const tbody = document.getElementById('ingredientsTable').querySelector('tbody');
    tbody.innerHTML = '';

    ingredients.forEach((ingredient, index) => {
        const row = `
            <tr>
                <td>${ingredient.name}</td>
                <td>${ingredient.quantity}</td>
                <td>${ingredient.unit}</td>
                <td>
                    <button class="btn btn-warning btn-sm" onclick="editIngredient(${index})">Modifier</button>
                    <button class="btn btn-danger btn-sm" onclick="deleteIngredient(${index})">Supprimer</button>
                </td>
            </tr>
        `;
        tbody.innerHTML += row;
    });
}

// Modifier un ingrédient
function editIngredient(index) {
    window.location.href = "/modif-production?id="+index
}

// Supprimer un ingrédient
function deleteIngredient(index) {
    if (confirm('Voulez-vous vraiment supprimer cet ingrédient ?')) {
        ingredients.splice(index, 1);
        refreshTable();
    }
}
