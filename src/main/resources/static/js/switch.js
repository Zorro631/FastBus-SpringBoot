
function setButtonClicked(btnName) {
	document.buttonClicked = btnName;
}
function switchIt() {
	var startStadt = document.getElementById("inputStart");
	var zielStadt = document.getElementById("inputEnd");
	var merker = startStadt.value;
	document.getElementById('inputStart').value = document.getElementById('inputEnd').value;
	document.getElementById('inputEnd').value = merker;
}
function stadt(myStadt) {
	var zielStadt = document.getElementById("inputEnd");
	zielStadt.value = myStadt;
}
function datum() {
	//für calendar vergangenheit disable
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1;
	var yyyy = today.getFullYear();
	if (dd < 10) {
		dd = '0' + dd
	}
	if (mm < 10) {
		mm = '0' + mm
	}

	today = yyyy + '-' + mm + '-' + dd;
	document.getElementById("dateInput").setAttribute("min", today);
	
}

function einTagDavor(){
	
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1;
	var yyyy = today.getFullYear();
	if (dd < 10) {
		dd = '0' + dd
	}
	if (mm < 10) {
		mm = '0' + mm
	}

	today = dd + '.' + mm + '.' + yyyy;
	
	var ausgewaehlterTag = document.getElementById("ausgewaehlterTag").innerText;
	if(today==ausgewaehlterTag){
		return false;
	}
	return true;
}
function checkSelbeStadt(verbindungSuche) {
	var verbindungSuche = true;
	if (document.buttonClicked == "btnVerbindung") {
	var startStadt=document.getElementById('inputStart').value;
	var zielStadt=document.getElementById('inputEnd').value;
	
		if (startStadt == zielStadt) {
			var myMsgField = document.getElementById("selbeStadtMsg");
			selbeStadtMsg.innerText = "Verschiedene Städte auswählen";
			verbindungSuche = false;
			return verbindungSuche;
		}
		startStadt
	}
	return verbindungSuche;
}
function ueberbuchung(verbindungen){
	var verbindungSuche = true;
	if (document.buttonClicked == "btnZumAngebot") {
	var plaetzefrei=parseInt(document.getElementById('freiePlaetze'+clicked).innerText);

	var wunschplaetze=parseInt(document.getElementById('invisibleWunschPlaetze').innerText);

		if(plaetzefrei < wunschplaetze){
			verbindungSuche=false;
		}
	}
	return verbindungSuche;
}
