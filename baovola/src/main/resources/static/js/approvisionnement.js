$(document).ready(function() {
    // Tableau des approvisionnements
    var supplyList = [];

    // Fonction d'ajout d'approvisionnement
    $('#addSupply').on('click', function() {
        // Récupérer les valeurs des champs du formulaire
        var ingredientName = $('#ingredientName').val();
        var supplyQuantity = $('#supplyQuantity').val();
        var supplierName = $('#supplierName').val();
        var supplyDate = $('#supplyDate').val();

        // Vérifier si tous les champs sont remplis
        if (ingredientName && supplyQuantity && supplierName && supplyDate) {
            // Créer un objet approvisionnement
            var newSupply = {
                id: supplyList.length + 1,
                ingredient: ingredientName,
                quantity: supplyQuantity,
                quantityInStock: Math.floor(Math.random() * 100), // Par exemple, une valeur aléatoire pour la quantité en stock
                supplier: supplierName,
                date: supplyDate
            };

            // Ajouter l'approvisionnement à la liste
            supplyList.push(newSupply);

            // Rafraîchir la table avec la nouvelle donnée
            updateSupplyTable();
        } else {
            alert("Veuillez remplir tous les champs !");
        }
    });

    // Fonction pour mettre à jour la table avec les approvisionnements
    function updateSupplyTable() {
        var tbody = $('#supplyTable tbody');
        tbody.empty(); // Effacer le contenu actuel de la table

        // Ajouter chaque approvisionnement à la table
        supplyList.forEach(function(supply) {
            var row = `<tr>
                <td>${supply.id}</td>
                <td>${supply.ingredient}</td>
                <td>${supply.quantity}</td>
                <td>${supply.quantityInStock}</td>
                <td>${supply.supplier}</td>
                <td>${supply.date}</td>
                <td>
                    <button class="btn btn-danger btn-sm" onclick="deleteSupply(${supply.id})">Supprimer</button>
                </td>
            </tr>`;
            tbody.append(row);
        });
    }

    // Fonction pour supprimer un approvisionnement
    window.deleteSupply = function(id) {
        // Filtrer l'élément à supprimer
        supplyList = supplyList.filter(function(supply) {
            return supply.id !== id;
        });

        // Rafraîchir la table après suppression
        updateSupplyTable();
    };

    // Fonction de recherche des approvisionnements (si nécessaire)
    $('#searchSupplyBtn').on('click', function() {
        var date = $('#searchSupplyDate').val();
        var ingredientName = $('#searchIngredientName').val().toLowerCase();
        var supplierName = $('#searchSupplierName').val().toLowerCase();

        // Filtrer la liste des approvisionnements selon les critères
        var filteredList = supplyList.filter(function(supply) {
            return (date === "" || supply.date === date) &&
                   (ingredientName === "" || supply.ingredient.toLowerCase().includes(ingredientName)) &&
                   (supplierName === "" || supply.supplier.toLowerCase().includes(supplierName));
        });

        // Mettre à jour la table avec la liste filtrée
        updateFilteredTable(filteredList);
    });

    // Fonction pour mettre à jour la table après filtrage
    function updateFilteredTable(filteredList) {
        var tbody = $('#supplyTable tbody');
        tbody.empty();

        filteredList.forEach(function(supply) {
            var row = `<tr>
                <td>${supply.id}</td>
                <td>${supply.ingredient}</td>
                <td>${supply.quantity}</td>
                <td>${supply.quantityInStock}</td>
                <td>${supply.supplier}</td>
                <td>${supply.date}</td>
                <td>
                    <button class="btn btn-danger btn-sm" onclick="deleteSupply(${supply.id})">Supprimer</button>
                </td>
            </tr>`;
            tbody.append(row);
        });
    }
});
