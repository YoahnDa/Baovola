// Fonction pour calculer le pourcentage et mettre à jour l'affichage du pourcentage
function updatePercentage(percentage, elementId) {
    document.getElementById(elementId).textContent = `${percentage}%`;
}

// Graphique circulaire de la Production (Nouveau Graphique)
var ctx1 = document.getElementById('productionPieChart').getContext('2d');
var productionPieChart = new Chart(ctx1, {
    type: 'pie',
    data: {
        labels: ['Produits fabriqués', 'Autres'],
        datasets: [{
            label: 'Production du jour',
            data: [200, 800], // 200 produits fabriqués, 800 autres
            backgroundColor: ['#28a745', '#ddd'],
            borderColor: ['#28a745', '#ddd'],
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                position: 'top'
            },
            datalabels: {
                formatter: function (value, ctx) {
                    var sum = ctx.dataset.data.reduce(function (a, b) {
                        return a + b;
                    }, 0);
                    var percentage = ((value / sum) * 100).toFixed(1) + '%';
                    return percentage;
                },
                color: '#fff',
                anchor: 'end',
                align: 'center',
                font: {
                    weight: 'bold',
                    size: 14
                },
                offset: 10
            }
        }
    },
    // Mise à jour du pourcentage sous le graphique
    plugins: [{
        afterRender: function (chart) {
            var total = chart.data.datasets[0].data.reduce((acc, value) => acc + value, 0);
            var productValue = chart.data.datasets[0].data[0];
            var percentage = ((productValue / total) * 100).toFixed(1);
            updatePercentage(percentage, 'productionPiePercentage');
        }
    }]
});

// Graphique circulaire des Ventes (Nouveau Graphique)
var ctx2 = document.getElementById('ventesPieChart').getContext('2d');
var ventesPieChart = new Chart(ctx2, {
    type: 'pie',
    data: {
        labels: ['Ventes Totales', 'Autres'],
        datasets: [{
            label: 'Ventes totales',
            data: [1500, 500], // 1500 € de ventes, 500 € autres
            backgroundColor: ['#17a2b8', '#ddd'],
            borderColor: ['#17a2b8', '#ddd'],
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                position: 'top'
            },
            datalabels: {
                formatter: function (value, ctx) {
                    var sum = ctx.dataset.data.reduce(function (a, b) {
                        return a + b;
                    }, 0);
                    var percentage = ((value / sum) * 100).toFixed(1) + '%';
                    return percentage;
                },
                color: '#fff',
                anchor: 'end',
                align: 'center',
                font: {
                    weight: 'bold',
                    size: 14
                },
                offset: 10
            }
        }
    },
    // Mise à jour du pourcentage sous le graphique
    plugins: [{
        afterRender: function (chart) {
            var total = chart.data.datasets[0].data.reduce((acc, value) => acc + value, 0);
            var ventesValue = chart.data.datasets[0].data[0];
            var percentage = ((ventesValue / total) * 100).toFixed(1);
            updatePercentage(percentage, 'ventesPiePercentage');
        }
    }]
});

// Graphique circulaire des Stocks (Nouveau Graphique)
var ctx3 = document.getElementById('stocksPieChart').getContext('2d');
var stocksPieChart = new Chart(ctx3, {
    type: 'pie',
    data: {
        labels: ['Stocks disponibles', 'Stocks épuisés'],
        datasets: [{
            label: 'Stocks disponibles',
            data: [80, 20], // 80% de stock disponible, 20% non disponible
            backgroundColor: ['#ff9933', '#ddd'],
            borderColor: ['#ff9933', '#ddd'],
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                position: 'top'
            },
            datalabels: {
                formatter: function (value, ctx) {
                    var sum = ctx.dataset.data.reduce(function (a, b) {
                        return a + b;
                    }, 0);
                    var percentage = ((value / sum) * 100).toFixed(1) + '%';
                    return percentage;
                },
                color: '#fff',
                anchor: 'end',
                align: 'center',
                font: {
                    weight: 'bold',
                    size: 14
                },
                offset: 10
            }
        }
    },
    // Mise à jour du pourcentage sous le graphique
    plugins: [{
        afterRender: function (chart) {
            var total = chart.data.datasets[0].data.reduce((acc, value) => acc + value, 0);
            var stockValue = chart.data.datasets[0].data[0];
            var percentage = ((stockValue / total) * 100).toFixed(1);
            updatePercentage(percentage, 'stocksPiePercentage');
        }
    }]
});

// Graphique de Production (Graphique linéaire - original)
var ctx4 = document.getElementById('productionChart').getContext('2d');
var productionChart = new Chart(ctx4, {
    type: 'line',
    data: {
        labels: ['Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi', 'Dimanche'],
        datasets: [{
            label: 'Produits fabriqués',
            data: [200, 250, 300, 150, 200, 450, 500],
            borderColor: 'rgb(75, 192, 192)',
            fill: false
        }]
    },
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

// Graphique des Ventes (Graphique linéaire - original)
var ctx5 = document.getElementById('ventesChart').getContext('2d');
var ventesChart = new Chart(ctx5, {
    type: 'line',
    data: {
        labels: ['Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi', 'Dimanche'],
        datasets: [{
            label: 'Ventes (€)',
            data: [1000, 1200, 1500, 1100, 1300, 1700, 1800],
            borderColor: 'rgb(54, 162, 235)',
            fill: false
        }]
    },
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

// Graphique des Stocks (Graphique à barres - original)
var ctx6 = document.getElementById('stocksChart').getContext('2d');
var stocksChart = new Chart(ctx6, {
    type: 'bar',
    data: {
        labels: ['Farine', 'Levure', 'Sucre', 'Beurre', 'Lait'],
        datasets: [{
            label: 'Stock d\'ingrédients (%)',
            data: [80, 60, 90, 75, 85],
            backgroundColor: 'rgba(255, 159, 64, 0.2)',
            borderColor: 'rgba(255, 159, 64, 1)',
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        scales: {
            y: {
                beginAtZero: true,
                max: 100
            }
        }
    }
});