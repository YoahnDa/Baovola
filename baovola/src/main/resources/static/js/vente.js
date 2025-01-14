let sales = [];

// Fonction pour ajouter une ligne de vente dans la table
function addSaleToTable(sale) {
    const tableBody = document.getElementById('salesTable').querySelector('tbody');
    
    // Construire une ligne pour chaque vente
    const row = document.createElement('tr');
    
    // Ajouter une cellule pour l'ID de la facture
    const idCell = document.createElement('td');
    idCell.innerHTML = `<a href="facture.html?id=${sale.invoiceId}" target="_blank">Facture ${sale.invoiceId}</a>`;
    row.appendChild(idCell);

    // Ajouter une cellule pour le produit
    const productCell = document.createElement('td');
    productCell.textContent = sale.product;
    row.appendChild(productCell);

    // Ajouter une cellule pour la quantité
    const quantityCell = document.createElement('td');
    quantityCell.textContent = sale.quantity;
    row.appendChild(quantityCell);

    // Ajouter une cellule pour les suppléments
    const supplementCell = document.createElement('td');
    supplementCell.textContent = sale.supplement.join(', ') || 'Aucun';
    row.appendChild(supplementCell);

    // Ajouter une cellule pour la catégorie
    const categoryCell = document.createElement('td');
    categoryCell.textContent = sale.category;
    row.appendChild(categoryCell);

    // Ajouter une cellule pour la date
    const dateCell = document.createElement('td');
    dateCell.textContent = sale.date;
    row.appendChild(dateCell);

    // Ajouter une cellule pour la caisse
    const cashRegisterCell = document.createElement('td');
    cashRegisterCell.textContent = sale.cashRegister;
    row.appendChild(cashRegisterCell);

    // Ajouter la ligne au tableau
    tableBody.appendChild(row);
}

// Fonction pour rafraîchir uniquement les lignes de la table
function refreshSalesTable(filteredSales = sales) {
    const tableBody = document.getElementById('salesTable').querySelector('tbody');
    
    // Vider le tableau de toutes les lignes actuelles
    tableBody.innerHTML = ''; 

    // Ajouter toutes les ventes dans la table (filteredSales contient les ventes à afficher)
    filteredSales.forEach(sale => addSaleToTable(sale));
}

// Fonction d'ajout d'une vente
document.getElementById('addSale').addEventListener('click', () => {
    const category = document.getElementById('category').value;
    const product = document.getElementById('product').value;
    const quantity = document.getElementById('quantity').value;
    const supplement = [];
    if (document.getElementById('supplement1').checked) supplement.push('Supp 1');
    if (document.getElementById('supplement2').checked) supplement.push('Supp 2');
    if (document.getElementById('supplement3').checked) supplement.push('Supp 3');
    const cashRegister = document.getElementById('searchCashRegister').value;

    if (product && quantity && category && cashRegister) {
        const sale = {
            invoiceId: sales.length + 1,  // Créer un ID unique pour la facture
            category,
            product,
            quantity,
            supplement,
            cashRegister,
            date: new Date().toLocaleDateString()
        };

        // Ajouter la vente à notre tableau de ventes
        sales.push(sale);

        // Ajouter la vente à la table sans rafraîchir toute la table
        addSaleToTable(sale);

        // Réinitialiser le formulaire
        document.getElementById('saleForm').reset();
    } else {
        alert('Veuillez remplir tous les champs.');
    }
});

// Filtrage des ventes
document.getElementById('searchBtn').addEventListener('click', () => {
    const searchCategory = document.getElementById('searchCategory').value;
    const searchProduct = document.getElementById('searchProduct').value;
    const searchQuantity = document.getElementById('searchQuantity').value;
    const searchSupplement = [];
    if (document.getElementById('searchSupplement1').checked) searchSupplement.push('Supp 1');
    if (document.getElementById('searchSupplement2').checked) searchSupplement.push('Supp 2');
    if (document.getElementById('searchSupplement3').checked) searchSupplement.push('Supp 3');
    const searchDate = document.getElementById('searchDate').value;
    const searchCashRegister = document.getElementById('searchCashRegister').value;

    const filteredSales = sales.filter(sale => {
        return (
            (searchCategory === '' || sale.category === searchCategory) &&
            (searchProduct === '' || sale.product === searchProduct) &&
            (searchQuantity === '' || sale.quantity >= searchQuantity) &&
            (searchSupplement.length === 0 || searchSupplement.every(supp => sale.supplement.includes(supp))) &&
            (searchDate === '' || sale.date === searchDate) &&
            (searchCashRegister === '' || sale.cashRegister === searchCashRegister)
        );
    });

    // Rafraîchir la table avec les ventes filtrées
    refreshSalesTable(filteredSales);
});
