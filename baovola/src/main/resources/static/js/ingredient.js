// Initialisation des ingrédients
let ingredients = [];

const formulaire = document.getElementById('ingredientForm');

// Ajouter un ingrédient
formulaire.addEventListener('submit', (event) => {
    event.preventDefault();
    var xhr; 
    try {  xhr = new ActiveXObject('Msxml2.XMLHTTP');   }
    catch (e) 
    {
        try {   xhr = new ActiveXObject('Microsoft.XMLHTTP'); }
        catch (e2) 
        {
           try {  xhr = new XMLHttpRequest();  }
           catch (e3) {  xhr = false;   }
         }
    }
    // Liez l'objet FormData et l'élément form
    var formData = new FormData(formulaire);

    var ingredientData = {};
    formData.forEach((value, key) => {
        ingredientData[key] = value;
    });
    xhr.onreadystatechange  = function() 
    { 
       if(xhr.readyState  == 4){
            if(xhr.status  == 201) {
                formulaire.reset();
                alert("Nouveau ingrédient créer.");
            } else if(xhr.status == 409 ) {
                alert("Erreur : L'ingrédient existe déjà.");
            }else {
                alert("Error code " + xhr.status);
            }
		}
    }; 

    // Configurez la requête
    xhr.open("POST", "api/ingredients/ajout-ingredient",true);
    // Définir le type de contenu comme JSON
    xhr.setRequestHeader("Content-Type", "application/json");
    // Les données envoyées sont ce que l'utilisateur a mis dans le formulaire
    xhr.send(JSON.stringify(ingredientData));
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