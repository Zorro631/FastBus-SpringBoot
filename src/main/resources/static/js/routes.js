document.addEventListener("DOMContentLoaded", () => {
    const urlParams = new URLSearchParams(window.location.search);
    const apiUrl = buildApiUrl(urlParams);
    fetchData(apiUrl)
        .then(data => renderVerbindungen(data,urlParams.get('quantity'),urlParams.get('date')))
        .catch(error => console.error('Fehler beim Abrufen der Daten:', error));
});

function buildApiUrl(params) {
    const departure = params.get('departure');
    const destination = params.get('destination');
    const date = params.get('date');
    const quantity = params.get('quantity');

    return `http://localhost:9000/routes?departure=${departure}&destination=${destination}&date=${date}&quantity=${quantity}`;
}

async function fetchData(url) {
    const response = await fetch(url);
    if (!response.ok) {
        throw new Error(`HTTP-Fehler: ${response.status}`);
    }
    return response.json();
}

function renderVerbindungen(verbindungen,quantity,date) {
    const container = document.querySelector('.verbindungen-container');

    container.innerHTML = createDateSection(verbindungen) + createRouteSections(verbindungen.routesDto,quantity,date);
}

function createDateSection(verbindungen) {
    return `
        <div class="vornachtagbox">
            <div class="vornachtag">
                <div class="vortag">
                    <img class="calendar" src="../static/img/calendar.png">
                    <a href="#" id="tagDavor">${verbindungen.dayBeforeSelectedDate}</a>
                </div>
                <div class="tagtag" id="ausgewaehlterTag">
                    <img class="calendar" src="../static/img/calendar.png">
                    <a href="#">${verbindungen.selectedDate}</a>
                </div>
                <div class="nachtag">
                    <img class="calendar" src="../static/img/calendar.png">
                    <a href="#" id="tagDanach">${verbindungen.dayAfterSelectedDate}</a>
                </div>
            </div>
        </div>
    `;
}

function createRouteSections(routes,quantity,date) {
    return routes.map(route => `
    <form action="buchung.html" method="post" onsubmit="return ueberbuchung(this)">
        <div class="verbindungsbox">
            <div class="vObenUnten">
                <div class="vBoxOben">
                    <div class="vUhrzeitStadt">
                        <div class="vUhrzeitStadtO">
                            <div class="vUhrzeit">${route.departureTime}</div>
                            <div class="vStadt">${route.departure} Hbf</div>
                        </div>
                        <div class="vUhrzeitStadtO">
                            <div class="vUhrzeit">${route.destinationTime}</div>
                            <div class="vStadt">${route.destination} Hbf</div>
                        </div>
                    </div>
                    <div class="vDauer">
                        <img src="../static/img/timer.png" alt="Dauer" width="35px" />
                        ${route.durationHours} Std ${route.durationMinutes} Min
                    </div>
                </div>
                <div class="vBoxUnten">
                    <div class="vPlaetzeFrei">
                        ${route.availableSeats} von 50 Plätzen frei
                    </div>
                    <div class="vPreisButton">
                        <div class="vPreis">${route.price.toFixed(2)} €</div>
                        <div class="vButton">
                             <button 
                                type="button" 
                                class="subbutton" 
                                onclick="redirectToBooking(
                                    '${route.routeId}', 
                                    '${route.departureTime}', 
                                    '${date}', 
                                    '${quantity}')">
                                Zum Angebot
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
    `).join('');
}
// Funktion zur Weiterleitung
function redirectToBooking(routeId, departureTime, date, quantity) {
    const bookingUrl = `booking.html?routeId=${encodeURIComponent(routeId)}&departureTime=${encodeURIComponent(departureTime)}&date=${encodeURIComponent(date)}&quantity=${encodeURIComponent(quantity)}`;
    window.location.href = bookingUrl;
}