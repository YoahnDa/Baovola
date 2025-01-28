const modalOverlay = document.getElementById('modalOverlay');
const ingredientModal = document.getElementById('ingredientModal');
const ingredientUpdate = document.getElementById('ingredientUpdate');
let currentEditRow = null;
const currentUrl = window.location.href;
const url = new URL(currentUrl);
const idRec = url.searchParams.get('id');

function openModal(mode, button = null) {
    modalOverlay.classList.add('active');

    if (mode === 'edit' && button) {
        ingredientUpdate.classList.add('active');
        currentEditRow = button.closest('tr');
        const idComposition = currentEditRow.dataset.id;
        console.log(idComposition)
        const inputIdComposition = document.createElement("input")
        inputIdComposition.value = idComposition
        inputIdComposition.type = 'text'
        inputIdComposition.name = 'idComposition'
        inputIdComposition.id = 'idComposition'
        inputIdComposition.style.display='none'
        inputIdComposition.readOnly=true
        document.getElementById("ingredientFormUpdate").appendChild(inputIdComposition);
        const cells = currentEditRow.querySelectorAll('td');
        document.getElementById('ingredientName').value = cells[0].textContent;
        document.getElementById('quantityUpdate').value = cells[1].textContent;
    } else if(mode === 'add') {
        ingredientModal.classList.add('active');
        document.getElementById('ingredientForm').reset();
        currentEditRow = null;
    }
}

function closeModal() {
    ingredientModal.classList.remove('active');
    modalOverlay.classList.remove('active');
}

function closeUpdate(){
    if(document.getElementById("ingredientFormUpdate").querySelector("#idComposition")!= null){
        document.getElementById("ingredientFormUpdate").removeChild(document.getElementById("ingredientFormUpdate").querySelector("#idComposition"));
    }
    ingredientUpdate.classList.remove('active');
    modalOverlay.classList.remove('active');
}

function loadData(data){
    const th = ['Ingredient','Quantité','Unité','Action']
    const tr = document.createElement("tr");
    tr.setAttribute('data-id',data.id);
    tr.setAttribute('data-ingredient',data.ingredient.id);
        const nom = document.createElement("td");
        const quantite = document.createElement("td");
        const unite = document.createElement("td");
        const action = document.createElement("td");
        nom.textContent = data.ingredient.nom;
        quantite.textContent = data.quantite;
        unite.textContent = data.ingredient.unite.nom;
        action.innerHTML = `
            <button class="edit" onclick="openModal('edit', this)">Modifier</button>
            <button class="delete" onclick="deleteIngredient(this,${data.id})">Supprimer</button>
        `
        tr.appendChild(nom);
        tr.appendChild(quantite);
        tr.appendChild(unite);
        tr.appendChild(action);
    if(document.getElementById("ingredientTable") === null){
        const header = document.getElementById("not_found");
        const parent = header.parentElement;
        parent.removeChild(header);
        const table = document.createElement('table');
        table.id = 'ingredientTable';
        const head = document.createElement("thead");
        const insideHead = document.createElement("tr");
        th.forEach((entete) =>{
            const td = document.createElement("td");
            td.textContent = entete;
            insideHead.appendChild(td);
        });
        head.appendChild(insideHead);
        table.appendChild(head);
        const body = document.createElement("tbody");
        body.appendChild(tr);
        table.appendChild(body);
        parent.appendChild(table);
    }else{
        const table = document.getElementById("ingredientTable");
        const body = table.querySelector("tbody");
        body.appendChild(tr);
    }
}

document.getElementById("ingredientForm").addEventListener('submit',(event)=>{
    event.preventDefault();
    ajouterByAjax(document.getElementById("ingredientForm"));
})

function updateByAjax(formulaire){
    let index = -1;
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
        if(key === 'idRecette'){
            index = value;
        }
    });
    xhr.onreadystatechange  = function() 
    { 
       if(xhr.readyState  == 4){
            if(xhr.status  == 202) {
                formulaire.reset();
                closeUpdate();
                reloadData(index);
            } else if(xhr.status == 409 ) {
                alert("Erreur : L'ingrédient existe déjà.");
            }else {
                alert("Error code " + xhr.status);
            }
		}
    }; 

    // Configurez la requête
    xhr.open("POST", "api/recettes/modifier-ingredient",true);
    // Définir le type de contenu comme JSON
    xhr.setRequestHeader("Content-Type", "application/json");
    // Les données envoyées sont ce que l'utilisateur a mis dans le formulaire
    xhr.send(JSON.stringify(ingredientData));
}

function ajouterByAjax(formulaire){
    let index = -1;
    let indexIngredient = -1;
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
        if(key === 'idRecette'){
            index = value;
        }else if(key === 'idIngredient'){
            indexIngredient = value
        }
    });
    xhr.onreadystatechange  = function() 
    { 
       if(xhr.readyState  == 4){
            if(xhr.status  == 201) {
                formulaire.reset();
                closeModal();
                updateIngredient(indexIngredient,'ajout');
                reloadData(index);
            } else if(xhr.status == 409 ) {
                alert("Erreur : L'ingrédient existe déjà.");
            }else {
                alert("Error code " + xhr.status);
            }
		}
    }; 

    // Configurez la requête
    xhr.open("POST", "api/recettes/ajout-ingredient",true);
    // Définir le type de contenu comme JSON
    xhr.setRequestHeader("Content-Type", "application/json");
    // Les données envoyées sont ce que l'utilisateur a mis dans le formulaire
    xhr.send(JSON.stringify(ingredientData));
}

function updateIngredient(index,etat,nom = null){
    const selectForm = document.getElementById("idIngredient")
    if(etat === 'ajout'){
        for (let i = 0; i < selectForm.options.length; i++) {
            console.log(selectForm.options[i].id)
            if (selectForm.options[i].value === index) {
                selectForm.remove(i);
                break; 
            }
        }
    }else if(etat === 'delete'){
        const options = document.createElement("option")
        options.text = nom
        options.value = index
        selectForm.appendChild(options)
    }
}

function reloadData(index){
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
                if(document.getElementById("ingredientTable")){
                    document.getElementById("ingredientTable").querySelector("tbody").innerHTML = "";
                    if(retour.length === 0){
                        const header = document.createElement("h3")
                        header.textContent = "Pas d'ingredient"
                        header.id='not_found'
                        const parent = document.getElementById("ingredientTable").parentElement
                        parent.removeChild(document.getElementById("ingredientTable"))
                        parent.appendChild(header)
                    }
                }
                retour.forEach((data)=>{
                    loadData(data);
                })
            }else {
                alert("Error code " + xhr.status);
            }
		}
    }; 
    xhr.open("GET", "api/recettes/get-composition?id="+index);
    xhr.send();
}

function deleteIngredient(button,index) {
    deleteByAjax(button,index)
}

function deleteByAjax(button,index){
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
                const row = button.closest('tr');
                const rowNow = row.querySelectorAll('td');
                updateIngredient(row.dataset.ingredient,'delete',rowNow[0].textContent);
                reloadData(idRec)
            } else if(xhr.status == 404 ) {
                alert("Erreur : L'ingrédient n'existe plus.");
            }else {
                alert("Error code " + xhr.status);
            }
		}
    }; 

    // Configurez la requête
    xhr.open("DELETE", "api/recettes/delete-composition?id="+index,true);
    // Définir le type de contenu comme JSON
    xhr.setRequestHeader("Content-Type", "application/json");
    // Les données envoyées sont ce que l'utilisateur a mis dans le formulaire
    xhr.send(null);
}

document.getElementById("ingredientFormUpdate").addEventListener("submit",(event)=>{
    event.preventDefault();
    updateByAjax(document.getElementById("ingredientFormUpdate"))
})

// Fermer le modal lorsque l'on clique en dehors
modalOverlay.addEventListener('click', ()=>{
    closeModal();
    closeUpdate();
});