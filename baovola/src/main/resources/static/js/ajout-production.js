document.addEventListener('DOMContentLoaded', function() {
    const productions = []; // Tableau pour stocker les productions filles
    let editIndex = null; // Index pour l'édition

    // Références des éléments
    const productSelect = document.getElementById('productSelect');
    const quantityInput = document.getElementById('quantityInput');
    const productionForm = document.getElementById('productionForm');
    const tableBody = document.getElementById('productionTableBody');

    // Ajouter une production au tableau
    productionForm.addEventListener('submit', function(event) {
        event.preventDefault();

        const product = productSelect.value;
        const quantity = quantityInput.value;
        const name = productSelect[productSelect.selectedIndex].textContent

        productions.push({ product, quantity , name });
        updateSelect();
        updateTable();
        productionForm.reset();
    });

    // Mettre à jour le tableau
    function updateTable() {
        tableBody.innerHTML = '';

        productions.forEach((prod, index) => {
            const row = document.createElement('tr');
            row.setAttribute('data-idProduit',prod.product)
            row.innerHTML = `
                <td>${prod.name}</td>
                <td>${prod.quantity}</td>
                <td>
                    <button class="btn btn-warning btn-sm" onclick="editRow(${index})">Modifier</button>
                    <button class="btn btn-danger btn-sm" onclick="deleteRow(${index})">Supprimer</button>
                </td>
            `;

            tableBody.appendChild(row);
        });
    }

    // Mettre à jour le select des produits
    function updateSelect() {
        const selectedProducts = productions.map(p => p.product);
        const allOptions = Array.from(productSelect.options).map(opt => ({
            valeur: opt.value,
            nom: opt.textContent
        }));

        productSelect.innerHTML = allOptions
            .filter(opt => !selectedProducts.includes(opt.valeur))
            .map(opt => `<option value="${opt.valeur}">${opt.nom}</option>`)
            .join('');
    }

    // Supprimer une ligne
    window.deleteRow = function(index) {
        const deletedProduct = productions[index].product;
        const nomProduit = productions[index].name
        productions.splice(index, 1);
        updateSelect();
        updateTable();

        // Réajouter le produit dans le select
        const option = document.createElement('option');
        option.value = deletedProduct;
        option.textContent = nomProduit;
        productSelect.appendChild(option);
    };

    // Modifier une ligne
    window.editRow = function(index) {
        editIndex = index;
        const prod = productions[index];
        document.getElementById('editProductSelect').value = prod.name;  
        document.getElementById('editQuantityInput').value = prod.quantity; 
        document.getElementById('idProduit').value = prod.product;
        new bootstrap.Modal(document.getElementById('editModal')).show();
    };

    // Enregistrer les modifications
    document.getElementById('editForm').addEventListener('submit', function(event) {
        event.preventDefault();

        if (editIndex !== null) {
            productions[editIndex] = {
                name: document.getElementById('editProductSelect').value,
                quantity: document.getElementById('editQuantityInput').value,
                product: document.getElementById('idProduit').value
            };
            updateSelect();
            updateTable();
            bootstrap.Modal.getInstance(document.getElementById('editModal')).hide();
        }
    });

    // Envoyer les productions au serveur
    document.getElementById('sendProductionsBtn').addEventListener('click', function() {
        if (productions.length === 0) {
            alert('Le tableau des productions est vide. Veuillez ajouter des données avant l\'envoi.');
            return;
        }

        fetch('/api/productions/insert-liste-production', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(productions)
        })
        .then(response => {
            if (response.ok) {
                alert('Productions envoyées avec succès !');
                window.location.href='/production'
            } else {
                alert('Erreur lors de l\'envoi des productions'+response.status);
            }
        })
        .catch(() => {
            alert('Erreur lors de l\'envoi des productions');
        });
    });

    // Rendre les champs obligatoires
    productSelect.setAttribute('required', 'required');
    quantityInput.setAttribute('required', 'required');
});
