// Initialisation des produits et recettes
let products = [];

const formulaire = document.querySelector("form");

formulaire.addEventListener('submit',(event)=>{
    event.preventDefault();
    if(formulaire.id === 'searchForm'){
        searchByAjax(formulaire);
    }else if(formulaire.id === 'productForm'){
        createByAjax(formulaire);
    }else if(formulaire.id === 'productUpdate'){
        updateByAjax(formulaire);
    }
})

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
    const queryString = new URLSearchParams(new FormData(formulaire)).toString();
    xhr.open("GET", "api/produits/search?" + queryString, true);
    xhr.send();
}

function updateByAjax(formulaire){
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
    const formData = new FormData(formulaire);

    let productData = {};
    formData.forEach((value, key) => {
        productData[key] = value;
    });
    xhr.onreadystatechange  = function() 
    { 
       if(xhr.readyState  == 4){
            if(xhr.status  === 202 || xhr.status === 200) {
                alert("Produit modifié.");
            }else if(xhr.status === 404){
                alert("Produit non trouvé");
            } else if(xhr.status == 409 ) {
                alert("Erreur : Le nom de produit existe déjà.");
            }else {
                alert("Error code " + xhr.status);
            }
		}
    }; 

    // Configurez la requête
    xhr.open("POST", "api/produits/modifier-produit",true);
    // Définir le type de contenu comme JSON
    xhr.setRequestHeader("Content-Type", "application/json");
    // Les données envoyées sont ce que l'utilisateur a mis dans le formulaire
    xhr.send(JSON.stringify(productData));
}

function createByAjax(forms){
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
    const formData = new FormData(formulaire);

    let produitData = {};

    formData.forEach((value, key) => {
        produitData[key] = value;
    });
    xhr.onreadystatechange  = function() 
    { 
       if(xhr.readyState  == 4){
            if(xhr.status  == 201) {
                formulaire.reset();
                alert("Produit créer avec succés.");
            } else if(xhr.status == 409 ) {
                alert("Erreur : conflit dans les données");
            }else {
                alert("Error code " + xhr.status);
            }
		}
    }; 

    // Configurez la requête
    xhr.open("POST", "api/produits/create-produit",true);
    // Définir le type de contenu comme JSON
    xhr.setRequestHeader("Content-Type", "application/json");
    // Les données envoyées sont ce que l'utilisateur a mis dans le formulaire
    xhr.send(JSON.stringify(produitData));
}

// Rafraîchir la table des produits
function loadData(data) {
    const tbody = document.getElementById('productsTable').querySelector('tbody');
    tbody.innerHTML = '';
    if(data == null || data.length === 0){
        const header = document.createElement("h3")
        header.textContent = 'Pas de correspondance';
        tbody.appendChild(header);
    }else{
        data.forEach((product) => {
            const row = `
                <tr id='produit-${product.id}>
                    <td>${product.nom}</td>
                    <td>${product.prixUnitaire}</td>
                    <td>${product.categorie.nom}</td>
                    <td>
                        <button class="btn btn-warning btn-sm" onclick="editProduct(${product.id})">Modifier</button>
                        <button class="btn btn-danger btn-sm" onclick="deleteProduct(${product.id})">Supprimer</button>
                    </td>
                </tr>
            `;
            tbody.innerHTML += row;
        });
    }
}

function deleteByAjax(index){
    var tableTr = document.getElementById('produit-'+index);
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
                alert("Erreur : Le produit n'existe plus.");
            }else {
                alert("Error code " + xhr.status);
            }
		}
    }; 

    // Configurez la requête
    xhr.open("DELETE", "api/produits/supprimer-produit?id="+index,true);
    // Définir le type de contenu comme JSON
    xhr.setRequestHeader("Content-Type", "application/json");
    // Les données envoyées sont ce que l'utilisateur a mis dans le formulaire
    xhr.send(null);
}

function editProduct(index){
    window.location.href='modif-produit?id='+index; 
}

// Supprimer un produit
function deleteProduct(index) {
    if (confirm('Voulez-vous vraiment supprimer ce produit ?')) {
        deleteByAjax(index);
    }
}