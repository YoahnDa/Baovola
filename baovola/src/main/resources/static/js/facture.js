// Données des factures (une facture peut contenir plusieurs commandes)
const invoices = [
    {
        id: 1,
        cashRegister: 'Caisse 1',
        date: '2025-01-12',
        customer: 'Client A',
        items: [
            {
                product: 'Produit 1',
                supplement: ['Supp 1', 'Supp 3'],
                quantity: 2,
                price: 10,
                total: 20
            },
            {
                product: 'Produit 2',
                supplement: ['Supp 2'],
                quantity: 1,
                price: 15,
                total: 15
            }
        ]
    },
    {
        id: 2,
        cashRegister: 'Caisse 2',
        date: '2025-01-13',
        customer: 'Client B',
        items: [
            {
                product: 'Produit 3',
                supplement: ['Supp 2'],
                quantity: 3,
                price: 12,
                total: 36
            },
            {
                product: 'Produit 4',
                supplement: [],
                quantity: 1,
                price: 8,
                total: 8
            }
        ]
    }
];

// Fonction pour récupérer l'ID de la facture depuis l'URL
function getInvoiceIdFromURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return parseInt(urlParams.get('id'), 10); // Extrait l'ID de la facture de l'URL
}

// Fonction pour afficher les détails de la facture
function displayInvoiceDetails(invoiceId) {
    const invoice = invoices.find(i => i.id === invoiceId); // Trouver la facture correspondant à l'ID

    if (invoice) {
        const orderDetails = document.getElementById('orderDetails');
        orderDetails.innerHTML = ''; // Vider le tableau avant de remplir

        // Boucle sur les items (produits) de la facture
        invoice.items.forEach(item => {
            const row = `
                <tr>
                    <td>${invoice.cashRegister}</td>
                    <td>${invoice.id}</td>
                    <td>
                        ${item.product} 
                        ${item.supplement.length > 0 ? `<br><strong>Suppléments:</strong> ${item.supplement.join(', ')}` : ''}
                    </td>
                    <td>${item.quantity}</td>
                    <td>${item.price} €</td>
                    <td>${item.total} €</td>
                </tr>
            `;
            orderDetails.innerHTML += row; // Ajouter une ligne pour chaque produit
        });
    } else {
        alert('Facture non trouvée');
    }
}

// Récupérer l'ID de la facture à partir de l'URL
const invoiceId = getInvoiceIdFromURL();

// Afficher les détails de la facture
if (invoiceId) {
    displayInvoiceDetails(invoiceId);
} else {
    alert('ID de facture invalide');
}
