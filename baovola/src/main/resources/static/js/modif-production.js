  const productionFilleList = document.getElementById("productionFilleList");
  const addProductBtn = document.getElementById("addProductBtn");
  const updateProductionBtn = document.getElementById("updateProductionBtn");
  const editQuantityInput = document.getElementById("editQuantity");
  const updateProductBtn = document.getElementById("updateProductBtn");

  const addProductForm = document.getElementById("addProductForm"); 
  const modifProductForm = document.getElementById("editProductionForm");
  const modifProduitForm = document.getElementById("editProductForm");

  const currentUrl = window.location.href;
  const url = new URL(currentUrl);
  const idProd = url.searchParams.get("id");

  // Add product button click
  addProductBtn.addEventListener("click", () => {
    const modal = new bootstrap.Modal(
      document.getElementById("addProductModal")
    );
    modal.show();
  });

  addProductForm.addEventListener("submit",(event)=>{
    event.preventDefault();
    addProduct(addProductForm);
  })

  modifProductForm.addEventListener("submit",(event)=>{
    event.preventDefault();
    modifProduction(modifProductForm);
  })

  modifProduitForm.addEventListener("submit",(event)=>{
    event.preventDefault();
    modifProduit(modifProduitForm);
  })

  function modifProduction(formulaire) {
    try {
      xhr = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
      try {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (e2) {
        try {
          xhr = new XMLHttpRequest();
        } catch (e3) {
          xhr = false;
        }
      }
    }
    // Liez l'objet FormData et l'élément form
    const formData = new FormData(formulaire);

    let produitData = {};

    formData.forEach((value, key) => {
      produitData[key] = value;
    });

    xhr.onreadystatechange = function () {
      if (xhr.readyState == 4) {
        if (xhr.status == 200) {
          alert("Production updated.")
        } else if (xhr.status == 409) {
          alert("Erreur : conflit dans les données");
        } else {
          alert("Error code " + xhr.status);
        }
      }
    };

    // Configurez la requête
    xhr.open("POST", "api/productions/modif-production", true);
    // Définir le type de contenu comme JSON
    xhr.setRequestHeader("Content-Type", "application/json");
    // Les données envoyées sont ce que l'utilisateur a mis dans le formulaire
    xhr.send(JSON.stringify(produitData));
  }

  function addProduct(formulaire) {
    var xhr;
    let indice = -1;
    try {
      xhr = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
      try {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (e2) {
        try {
          xhr = new XMLHttpRequest();
        } catch (e3) {
          xhr = false;
        }
      }
    }
    // Liez l'objet FormData et l'élément form
    const formData = new FormData(formulaire);

    let produitData = {};

    formData.forEach((value, key) => {
      if (key === "product") {
        indice = value;
      }
      produitData[key] = value;
    });

    xhr.onreadystatechange = function () {
      if (xhr.readyState == 4) {
        if (xhr.status == 201) {
          formulaire.reset();
          reloadData();
          updateSelect(indice,"add");
          const modal = new bootstrap.Modal(
            document.getElementById("addProductModal")
          );
          modal.hide();
        } else if (xhr.status == 409) {
          alert("Erreur : conflit dans les données");
        } else {
          alert("Error code " + xhr.status);
        }
      }
    };

    // Configurez la requête
    xhr.open("POST", "api/productions/add-produit", true);
    // Définir le type de contenu comme JSON
    xhr.setRequestHeader("Content-Type", "application/json");
    // Les données envoyées sont ce que l'utilisateur a mis dans le formulaire
    xhr.send(JSON.stringify(produitData));
  }

  function deleteProductionFilles(index,name) {
    var xhr;
    let indice = -1;
    try {
      xhr = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
      try {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (e2) {
        try {
          xhr = new XMLHttpRequest();
        } catch (e3) {
          xhr = false;
        }
      }
    }
    // Liez l'objet FormData et l'élément form
    xhr.onreadystatechange = function () {
      if (xhr.readyState == 4) {
        if (xhr.status == 200) {
          reloadData();
          updateSelect(index,"delete",name);
        } else if (xhr.status == 409) {
          alert("Erreur : conflit dans les données");
        } else {
          alert("Error code " + xhr.status);
        }
      }
    };

    // Configurez la requête
    xhr.open("DELETE", "api/productions/delete-fille?id="+index, true);
    // Les données envoyées sont ce que l'utilisateur a mis dans le formulaire
    xhr.send();
  }

  function loadData(data) {
    productionFilleList.innerHTML = "";
    data.forEach((productions) => {
      const row = document.createElement("tr");
      row.innerHTML = `
            <td>${productions.produit.nom}</td>
            <td>${productions.quantite}</td>
            <td>
                <button class="btn btn-warning btn-sm" onclick="editProductionFille(this, ${productions.quantite},${productions.id},${productions.produit.nom})">Modifier</button>
                <button class="btn btn-danger btn-sm" onclick="deleteProductionFille(this,${productions.id})">Supprimer</button>
            </td>
        `;
      row.setAttribute("data-id", productions.id);
      row.setAttribute("data-produit", productions.produit.id);
      productionFilleList.appendChild(row);
    });
  }

  function reloadData() {
    var xhr;
    try {
      xhr = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
      try {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (e2) {
        try {
          xhr = new XMLHttpRequest();
        } catch (e3) {
          xhr = false;
        }
      }
    }

    xhr.onreadystatechange = function () {
      if (xhr.readyState == 4) {
        if (xhr.status == 200) {
          var retour = JSON.parse(xhr.responseText);
          loadData(retour);
        } else {
          alert("Error code " + xhr.status);
        }
      }
    };
    xhr.open("GET", "api/productions/get-productions?id=" + idProd);
    xhr.send();
  }

  function updateSelect(index, etat, nom = null) {
    const selectParent = document.getElementById("newProduct");
    if (etat === "add") {
      const options = document.getElementById("produit-" + index);
      selectParent.removeChild(options);
    } else if (etat === "delete") {
      const newOptions = document.createElement("option");
      newOptions.value = index;
      newOptions.textContent = nom;
      newOptions.id = "produit-"+index;
      selectParent.appendChild(newOptions);
    }
  }

  function modifProduit(formulaire) {
    let indice = -1;
    let quantite = -1;
    try {
      xhr = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
      try {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (e2) {
        try {
          xhr = new XMLHttpRequest();
        } catch (e3) {
          xhr = false;
        }
      }
    }
    // Liez l'objet FormData et l'élément form
    const formData = new FormData(formulaire);

    let produitData = {};

    formData.forEach((value, key) => {
      produitData[key] = value;
    });

    xhr.onreadystatechange = function () {
      if (xhr.readyState == 4) {
        if (xhr.status == 200) {
          alert("Production modifié.")
          reloadData();
        } else if (xhr.status == 409) {
          alert("Erreur : conflit dans les données");
        } else {
          alert("Error code " + xhr.status);
        }
      }
    };

    // Configurez la requête
    xhr.open("POST", "api/productions/modif-produit", true);
    // Définir le type de contenu comme JSON
    xhr.setRequestHeader("Content-Type", "application/json");
    // Les données envoyées sont ce que l'utilisateur a mis dans le formulaire
    xhr.send(JSON.stringify(produitData));
  }

  function deleteProductionParent(){
    try {
      xhr = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
      try {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
      } catch (e2) {
        try {
          xhr = new XMLHttpRequest();
        } catch (e3) {
          xhr = false;
        }
      }
    }
    // Liez l'objet FormData et l'élément form
    xhr.onreadystatechange = function () {
      if (xhr.readyState == 4) {
        if (xhr.status == 200) {
          alert("Production supprimé.")
          window.location.href = "/production";
        } else if (xhr.status == 409) {
          alert("Erreur : conflit dans les données");
        } else {
          alert("Error code " + xhr.status);
        }
      }
    };

    // Configurez la requête
    xhr.open("DELETE", "api/productions/delete-production?id="+idProd, true);
    // Les données envoyées sont ce que l'utilisateur a mis dans le formulaire
    xhr.send();
  }

  // Edit production fille
  window.editProductionFille = (button, quantity,index) => {
    const modal = new bootstrap.Modal(
      document.getElementById("editProductModal")
    );
    modifProduitForm.reset();
    editQuantityInput.value = quantity;
    modifProduitForm.querySelector("#editProductFille").value =  index;
    modifProduitForm.querySelector("#nameProd").value =  button.closest("tr").querySelectorAll("td")[0].textContent;
    modal.show();
  };

  window.deleteProduction = (button,index)=>{
    if(confirm('Voulez-vous vraiment supprimer la production ?')){
        deleteProductionParent();
    }
  }
    window.deleteProductionFille = (button,index) => {
    const row = button.closest("tr");
    const nom =row.querySelectorAll("td")[0].textContent;
    deleteProductionFilles(index,nom);
  }
