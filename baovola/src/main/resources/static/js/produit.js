// Initialisation des produits et recettes
let products = [];

// Ajouter un produit
document.getElementById('addProduct').addEventListener('click', () => {
    const name = document.getElementById('productName').value;
    const price = document.getElementById('productPrice').value;
    const category = document.getElementById('productCategory').value;

    if (name && price && category) {
        products.push({ name, price, category });
        refreshProductTable();
        document.getElementById('productForm').reset();
    } else {
        alert('Veuillez remplir tous les champs.');
    }
});

// Rafraîchir la table des produits
function refreshProductTable(data = products) {
    const tbody = document.getElementById('productsTable').querySelector('tbody');
    tbody.innerHTML = '';

    data.forEach((product, index) => {
        const row = `
            <tr>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.category}</td>
                <td>
                    <button class="btn btn-warning btn-sm" onclick="editProduct(${index})">Modifier</button>
                    <button class="btn btn-danger btn-sm" onclick="deleteProduct(${index})">Supprimer</button>
                </td>
            </tr>
        `;
        tbody.innerHTML += row;
    });
}

// Modifier un produit
function editProduct(index) {
    const product = products[index];
    document.getElementById('productName').value = product.name;
    document.getElementById('productPrice').value = product.price;
    document.getElementById('productCategory').value = product.category;

    products.splice(index, 1);
}

// Supprimer un produit
function deleteProduct(index) {
    if (confirm('Voulez-vous vraiment supprimer ce produit ?')) {
        products.splice(index, 1);
        refreshProductTable();
    }
}

// Recherche et filtrage des produits
document.getElementById('searchBtn').addEventListener('click', () => {
    const filterCategory = document.getElementById('filterCategory').value.toLowerCase();
    const minPrice = parseFloat(document.getElementById('minPrice').value) || 0;
    const maxPrice = parseFloat(document.getElementById('maxPrice').value) || Infinity;

    const filtered = products.filter(product =>
        (filterCategory === '' || product.category.toLowerCase().includes(filterCategory)) &&
        product.price >= minPrice &&
        product.price <= maxPrice
    );

    refreshProductTable(filtered);
});