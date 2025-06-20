document.addEventListener('DOMContentLoaded', () => {
    if (typeof summaryData === 'undefined') {
        console.warn("summaryData is not available");
        return;
    }

    const rawData = summaryData;

    let productMap = {}; // { productId: {date1: value, date2: value, ...} }
    let dateSet = new Set();

    rawData.forEach(row => {
        let date = row[0];
        let productId = row[1];
        let count = row[2];

        dateSet.add(date);

        if (!productMap[productId]) {
            productMap[productId] = {};
        }
        productMap[productId][date] = count;
    });

    let dates = Array.from(dateSet).sort();
    let datasets = [];

    for (let productId in productMap) {
        let data = dates.map(d => productMap[productId][d] || 0);
        datasets.push({
            label: '商品ID ' + productId,
            data: data,
            backgroundColor: 'rgba(' + (50 + productId * 30) + ', 99, 132, 0.5)',
            stack: 'stack1'
        });
    }

    const ctx = document.getElementById('salesChart');
    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: dates,
            datasets: datasets
        },
        options: {
            responsive: true,
            plugins: {
                title: {
                    display: true,
                    text: '日別・商品別売上実績（過去7日間）'
                }
            },
            scales: {
                x: { stacked: true },
                y: { stacked: true, beginAtZero: true }
            }
        }
    });
});
