document.addEventListener("DOMContentLoaded", () => {
    const urlParams = new URLSearchParams(window.location.search);
    const apiUrl = buildApiUrl(urlParams);

    fetchData(apiUrl)
        .then(data => renderVerbindungen(data, urlParams.get('quantity'), urlParams.get('date')))
        .catch(error => handleError(error));
});
// API-URL bauen und Parameter validieren
function buildApiUrl(params) {
    const departure = validateQueryParam(params.get('departure'), '^[a-zA-ZäöüÄÖÜß\\-\\s]+$');
    const destination = validateQueryParam(params.get('destination'), '^[a-zA-ZäöüÄÖÜß\\-\\s]+$');
    const date = validateQueryParam(params.get('date'), '^\\d{4}-\\d{2}-\\d{2}$');
    const quantity = validateQueryParam(params.get('quantity'), '^\\d+$');

    return `http://localhost:9000/routes?
    departure=${encodeURIComponent(departure)}&
    destination=${encodeURIComponent(destination)}&
    date=${encodeURIComponent(date)}&
    quantity=${encodeURIComponent(quantity)}`;
}

// Eingabewerte validieren
function validateQueryParam(param, pattern) {
    if (typeof param !== 'string' || param.trim() === '') {
        throw new Error('Fehlender oder ungültiger Parameter: Der Wert darf nicht leer sein.');
    }
    const regex = new RegExp(pattern);
    if (!regex.test(param)) {
        throw new Error(`Ungültiger Parameter: "${param}" entspricht nicht dem erwarteten Muster.`);
    }
    return param.trim(); // Entfernt unnötige Leerzeichen
}

// API-Daten abrufen
async function fetchData(url) {
    const response = await fetch(url);
    if (!response.ok) {
        throw new Error(`HTTP-Fehler: ${response.status}`);
    }
    return response.json();
}

// Verbindungen rendern
function renderVerbindungen(verbindungen, quantity, date) {
    const container = document.querySelector('.verbindungen-container');
    const fragment = document.createDocumentFragment();

    fragment.appendChild(createDateSection(verbindungen));
    verbindungen.routeOverview.forEach(route => {
        fragment.appendChild(createRouteHTML(route, quantity, date));
    });

    container.innerHTML = ''; // Alte Inhalte löschen
    container.appendChild(fragment);
}

// Datumsauswahl-Abschnitt erstellen
function createDateSection(verbindungen) {
    const section = document.createElement('div');
    section.classList.add('vornachtagbox');
    section.innerHTML = `
        <div class="vornachtag">
            ${createCalendarLink(verbindungen.dayBeforeSelectedDate, 'tagDavor')}
            ${createCalendarLink(verbindungen.selectedDate, 'ausgewaehlterTag')}
            ${createCalendarLink(verbindungen.dayAfterSelectedDate, 'tagDanach')}
        </div>
    `;
    return section;
}

// Kalender-Link erstellen
function createCalendarLink(date, id) {
    return `
        <div>
            <img class="calendar" src="../static/img/calendar.png" alt="Kalendersymbol">
            <a href="#" id="${sanitizeHTML(id)}">${sanitizeHTML(date)}</a>
        </div>
    `;
}

// Verbindungskarte erstellen
function createRouteHTML(route, quantity, date) {
    validateApiData(route); // Validierung der API-Daten

    const routeElement = document.createElement('div');
    routeElement.innerHTML = `
        <form action="buchung.html" method="post" onsubmit="return ueberbuchung(this)">
            <div class="verbindungsbox">
                <div class="vObenUnten">
                    <div class="vBoxOben">
                        <div class="vUhrzeitStadt">
                            <div class="vUhrzeitStadtO">
                                <div class="vUhrzeit">${sanitizeHTML(route.departureTime)}</div>
                                <div class="vStadt">${sanitizeHTML(route.departure)} Hbf</div>
                            </div>
                            <div class="vUhrzeitStadtO">
                                <div class="vUhrzeit">${sanitizeHTML(route.destinationTime)}</div>
                                <div class="vStadt">${sanitizeHTML(route.destination)} Hbf</div>
                            </div>
                        </div>
                        <div class="vDauer">
                            <img src="../static/img/timer.png" alt="Dauer" width="35px" />
                            ${sanitizeHTML(route.durationHours)} Std ${sanitizeHTML(route.durationMinutes)} Min
                        </div>
                    </div>
                    <div class="vBoxUnten">
                        <div class="vPlaetzeFrei">
                            ${sanitizeHTML(route.availableSeats)} von 50 Plätzen frei
                        </div>
                        <div class="vPreisButton">
                            <div class="vPreis">${sanitizeHTML(route.price.toFixed(2))} €</div>
                            <div class="vButton">
                                <button 
                                    type="button" 
                                    class="subbutton" 
                                    onclick="redirectToBooking(
                                        '${sanitizeHTML(route.routeId)}', 
                                        '${sanitizeHTML(route.departureTime)}', 
                                        '${sanitizeHTML(date)}', 
                                        '${sanitizeHTML(quantity)}')">
                                    Zum Angebot
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    `;
    return routeElement;
}

// Navigation zur Buchungsseite
function redirectToBooking(routeId, departureTime, date, quantity) {
    const validatedRouteId = validateQueryParam(routeId, '^[a-zA-Z0-9_-]+$');
    const validatedDate = validateQueryParam(date, '^\\d{4}-\\d{2}-\\d{2}$');
    const validatedQuantity = validateQueryParam(quantity, '^\\d+$');

    const bookingUrl = `booking.html?routeId=${encodeURIComponent(validatedRouteId)}&departureTime=${encodeURIComponent(departureTime)}&date=${encodeURIComponent(validatedDate)}&quantity=${encodeURIComponent(validatedQuantity)}`;
    window.location.href = bookingUrl;
}

// Sicherheitsfunktion zum Bereinigen von HTML
function sanitizeHTML(string) {
    const tempDiv = document.createElement('div');
    tempDiv.textContent = string;
    return tempDiv.innerHTML;
}

// Validierung der API-Daten
function validateApiData(route) {
    if (!route || typeof route !== 'object') throw new Error('Ungültige API-Daten');

    const requiredFields = ['departureTime', 'departure', 'destinationTime', 'destination', 'durationHours', 'durationMinutes', 'availableSeats', 'price', 'routeId'];

    requiredFields.forEach(field => {
        if (!route[field]) {
            throw new Error(`Fehlendes Feld: ${field}`);
        }
    });

    if (isNaN(route.price) || route.price < 0) {
        throw new Error('Ungültiger Preiswert');
    }

    if (isNaN(route.availableSeats) || route.availableSeats < 0) {
        throw new Error('Ungültige Anzahl freier Plätze');
    }
}
// Fehlerbehandlung bei API-Aufruf
function handleError(error) {
    console.error('Fehler beim Abrufen der Daten:', error);
    const container = document.querySelector('.verbindungen-container');
    container.textContent = 'Fehler beim Laden der Verbindungen. Bitte versuchen Sie es später erneut.';
}