// Initialisation des ingrédients
let ingredients = [];

const formulaire = document.querySelector('form');

function ajoutIngredient(formulaire){
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
                alert("Ingredient créer avec succés.");
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
}

function updateIngredient(formulaire){
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
            if(xhr.status  == 200) {
                alert("Ingredient modifié.");
            } else if(xhr.status == 409 ) {
                alert("Erreur : Le nom d'ingredient existe déjà.");
            }else {
                alert("Error code " + xhr.status);
            }
		}
    }; 

    // Configurez la requête
    xhr.open("POST", "api/ingredients/modification",true);
    // Définir le type de contenu comme JSON
    xhr.setRequestHeader("Content-Type", "application/json");
    // Les données envoyées sont ce que l'utilisateur a mis dans le formulaire
    xhr.send(JSON.stringify(ingredientData));
}
// Ajouter un ingrédient
formulaire.addEventListener('submit', (event) => {
    event.preventDefault();
    if(formulaire.id === 'updateForm'){
        updateIngredient(formulaire);
    }else if(formulaire.id === 'ingredientForm'){
        ajoutIngredient(formulaire);
    }else if(formulaire.id === 'searchForm'){
        searchByAjax(formulaire);
    }
});

function loadData(data){
    const tbody = document.getElementById('ingredientsTable').querySelector('tbody');
    tbody.innerHTML = '';
    if(data == null || data.length === 0){
        const header = document.createElement("h3")
        header.textContent = 'Pas de correspondance';
        tbody.appendChild(header);
    }else{
        data.forEach((ingredient) => {
            const row = `
                <tr id='ingredient-${ingredient.id}'>
                    <td>${ingredient.nom}</td>
                    <td>0</td>
                    <td>${ingredient.unite.nom}</td>
                    <td>
                        <button class="btn btn-warning btn-sm" onclick="editIngredient(${ingredient.id})">Modifier</button>
                        <button class="btn btn-danger btn-sm" onclick="deleteIngredient(${ingredient.id})">Supprimer</button>
                    </td>
                </tr>
            `;
            tbody.innerHTML += row;
        });
    }
}

function searchByAjax(formulaire){
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

    xhr.onreadystatechange  = function() 
    { 
       if(xhr.readyState  == 4){
            if(xhr.status  == 200) {
                var retour = JSON.parse(xhr.responseText);
                loadData(retour);
            }else {
                alert("Error code " + xhr.status);
            }
		}
    }; 
    var queryString = new URLSearchParams(new FormData(formulaire)).toString();
    xhr.open("GET", "api/ingredients/search?" + queryString, true);
    xhr.send();
}

// Modifier un ingrédient
function editIngredient(index) {
    window.location.href = 'modif-ingredient?id='+index
}


function deleteByAjax(index){
    var tableTr = document.getElementById('ingredient-'+index);
    var parent = tableTr.parentElement;
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

    xhr.onreadystatechange  = function() 
    { 
       if(xhr.readyState  == 4){
            if(xhr.status  == 200) {
                parent.removeChild(tableTr);
            } else if(xhr.status == 404 ) {
                alert("Erreur : L'ingrédient n'existe plus.");
            }else {
                alert("Error code " + xhr.status);
            }
		}
    }; 

    // Configurez la requête
    xhr.open("DELETE", "api/ingredients/supprimer-ingredient?id="+index,true);
    // Définir le type de contenu comme JSON
    xhr.setRequestHeader("Content-Type", "application/json");
    // Les données envoyées sont ce que l'utilisateur a mis dans le formulaire
    xhr.send(null);
}
// Supprimer un ingrédient
function deleteIngredient(index) {
    if (confirm('Voulez-vous vraiment supprimer cet ingrédient ?')) {
        deleteByAjax(index);
    }
}