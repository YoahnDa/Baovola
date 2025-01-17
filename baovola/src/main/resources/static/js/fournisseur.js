let suppliers = []; // Tableau pour stocker les fournisseurs

// Fonction pour rafraîchir la liste des fournisseurs
function refreshSupplierTable(filteredSuppliers = suppliers) {
    const tableBody = document.getElementById('supplierTable').querySelector('tbody');
    tableBody.innerHTML = ''; // Réinitialiser la table

    filteredSuppliers.forEach(supplier => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${supplier.id}</td>
            <td>${supplier.name}</td>
            <td>${supplier.email}</td>
            <td>${supplier.phone}</td>
            <td>${supplier.address}</td>
            <td>
                <button class="btn btn-warning" onclick="editSupplier(${supplier.id})">Modifier</button>
                <button class="btn btn-danger" onclick="deleteSupplier(${supplier.id})">Supprimer</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

// Fonction pour ajouter un fournisseur
document.getElementById('addSupplier').addEventListener('click', () => {
    const name = document.getElementById('supplierName').value;
    const email = document.getElementById('supplierEmail').value;
    const phone = document.getElementById('supplierPhone').value;
    const address = document.getElementById('supplierAddress').value;

    // Validation de l'email (doit être de la forme ...@gmail.com)
    const emailRegex = /^[a-zA-Z0-9._-]+@gmail\.com$/;
    if (!emailRegex.test(email)) {
        alert("L'email doit être de la forme ...@gmail.com");
        return; // Stopper l'exécution si l'email est invalide
    }

    // Validation du numéro de téléphone (doit être un numéro international commençant par +261)
    const phoneRegex = /^\+261\d{8}$/;  // Pour Madagascar, +261 suivi de 8 chiffres
    if (!phoneRegex.test(phone)) {
        alert("Le numéro de téléphone doit être un numéro international valide pour Madagascar (+261 suivi de 8 chiffres).");
        return; // Stopper l'exécution si le numéro est invalide
    }

    if (name && email && phone && address) {
        const supplier = {
            id: suppliers.length + 1, // ID unique
            name,
            email,
            phone,
            address
        };

        suppliers.push(supplier);
        refreshSupplierTable();
        document.getElementById('supplierForm').reset();
    } else {
        alert('Veuillez remplir tous les champs.');
    }
});

// Fonction de recherche des fournisseurs
document.getElementById('searchSupplierBtn').addEventListener('click', () => {
    const searchName = document.getElementById('searchSupplierName').value;
    const searchEmail = document.getElementById('searchSupplierEmail').value;

    const filteredSuppliers = suppliers.filter(supplier => {
        return (
            (searchName === '' || supplier.name.includes(searchName)) &&
            (searchEmail === '' || supplier.email.includes(searchEmail))
        );
    });

    refreshSupplierTable(filteredSuppliers);
});

// Fonction pour modifier un fournisseur
function editSupplier(id) {
    const supplier = suppliers.find(s => s.id === id);
    if (supplier) {
        document.getElementById('supplierName').value = supplier.name;
        document.getElementById('supplierEmail').value = supplier.email;
        document.getElementById('supplierPhone').value = supplier.phone;
        document.getElementById('supplierAddress').value = supplier.address;
    }
}

// Fonction pour supprimer un fournisseur
function deleteSupplier(id) {
    suppliers = suppliers.filter(s => s.id !== id);
    refreshSupplierTable();
}