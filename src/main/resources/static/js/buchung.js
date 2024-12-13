document.addEventListener("DOMContentLoaded", () => {
    const urlParams = new URLSearchParams(window.location.search);
    const apiUrl = buildApiUrl(urlParams);

    fetchData(apiUrl)
        .then(data => renderConnectionDetailsBox(data))
        .catch(error => console.error('Fehler beim Abrufen der Daten:', error));
});

function buildApiUrl(params) {
    const routeId = params.get('routeId');
    const departureTime = params.get('departureTime');
    const departureDate = params.get('date');
    const quantity = params.get('quantity');
    return `http://localhost:9000/connectionDetails?routeId=${routeId}&departureTime=${departureTime}&departureDate=${departureDate}&quantity=${quantity}`;
}

async function fetchData(url) {
    const response = await fetch(url);
    if (!response.ok) {
        throw new Error(`HTTP-Fehler: ${response.status}`);
    }
    return response.json();
}

function renderConnectionDetailsBox(data) {
    const container = document.querySelector('.verbindungsContainer');
    container.innerHTML = createConnectionDetailsBox(data);
}

function createConnectionDetailsBox(data) {
    return `
        <div class="überschrift">
            <h1>Deine Verbindung</h1>
        </div>
        <div class="main-container">
            <div class="route-Container">
                <div class="Uhrzeit">
                    <p class="von-uhr">${data.departureTime}</p>
                    <p class="nach-uhr">${data.destinationTime}</p>
                </div>
                <div class="bild-route">
                    <img src="../static/img/von-nach_BildNew.jpg" alt="Route Bild">
                </div>
                <div class="Stadt">
                    <p class="von-stadt">${data.departureCity}</p>
                    <p class="nach-stadt">${data.destinationCity}</p>
                </div>
                <div class="alleDatum">
                    <p class="von-datum">${data.departureDate}</p>
                    <p class="nach-datum">${data.destinationDate}</p>
                </div>
            </div>
            <div class="busBild">
                <img src="../static/img/Fast-BusZahlung.png" alt="Bus Bild">
            </div>
        </div>
        <div class="personen">
            <h3>Personen: <span>${data.quantity}</span></h3>
        </div>
        <div class="gesamtpreis">
            <h3>Preis (inkl. MwSt.)</h3>
            <h3 class="summe">${data.totalPrice.toFixed(2)} €</h3>
        </div>
    `;
}
