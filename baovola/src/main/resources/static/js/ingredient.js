// Initialisation des ingrédients
let ingredients = [];

// Ajouter un ingrédient
document.getElementById('addIngredient').addEventListener('click', () => {
    const name = document.getElementById('ingredientName').value;
    const quantity = document.getElementById('quantity').value;
    const unit = document.getElementById('unit').value;

    if (name && quantity && unit) {
        ingredients.push({ name, quantity, unit });
        refreshTable();
        document.getElementById('ingredientForm').reset();
    } else {
        alert('Veuillez remplir tous les champs.');
    }
});

// Recherche multicritère (par nom et unité seulement)
document.getElementById('searchBtn').addEventListener('click', () => {
    const searchName = document.getElementById('searchName').value.toLowerCase();
    const searchUnit = document.getElementById('searchUnit').value.toLowerCase();

    const filtered = ingredients.filter(ingredient =>
        (searchName === '' || ingredient.name.toLowerCase().includes(searchName)) &&
        (searchUnit === '' || ingredient.unit.toLowerCase().includes(searchUnit))
    );

    refreshTable(filtered);
});

// Rafraîchir la table des ingrédients
function refreshTable(data = ingredients) {
    const tbody = document.getElementById('ingredientsTable').querySelector('tbody');
    tbody.innerHTML = '';

    data.forEach((ingredient, index) => {
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
    const ingredient = ingredients[index];
    document.getElementById('ingredientName').value = ingredient.name;
    document.getElementById('quantity').value = ingredient.quantity;
    document.getElementById('unit').value = ingredient.unit;

    ingredients.splice(index, 1);
}

// Supprimer un ingrédient
function deleteIngredient(index) {
    if (confirm('Voulez-vous vraiment supprimer cet ingrédient ?')) {
        ingredients.splice(index, 1);
        refreshTable();
    }
}