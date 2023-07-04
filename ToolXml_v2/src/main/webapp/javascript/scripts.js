// Selectors
const dropArea = document.querySelector(".drag-area");
const dragText = document.querySelector(".header");
const downloadButton = document.querySelector(".download");
const input = document.querySelector("input[type='file']");
const button = dropArea.querySelector(".button");
const progress = document.querySelector(".progress");
const progressPercent = document.querySelector(".progress-percent");
const resetButton = document.getElementById("resetButton");
const exampleButton = document.getElementById("exampleButton");
const activityItems = document.getElementById("activityItems");
const clearActivityButton = document.getElementById("clearActivityButton");

let files = [];

downloadButton.disabled = true;

// Load saved activities from localStorage
loadSavedActivities();

//Functions
function addFileToList(file) {

	console.log(file); // Log the entire file object
	console.log(file.size); // Log just the file size

	files.push(file);

	const fileSizeInKB = (file.size / 1024).toFixed(2); // Calcola la dimensione del file
	console.log(`File size: ${fileSizeInKB} KB`); // Stampa la dimensione del file

	const listItem = document.createElement("li");
	//listItem.textContent = file.name; vedi solo il nome del file
	listItem.textContent = `${file.name} - ${(file.size / 1024).toFixed(2)} KB`; // Aggiunge il nome del file e la dimensione del file

	console.log(listItem.textContent);

	const fileActionButtons = document.createElement("div");
	fileActionButtons.classList.add("file-action-buttons");

	const downloadButton = createDownloadButton(file);
	const removeButton = document.createElement("button");
	removeButton.textContent = "Delete";
	removeButton.classList.add("file-action-button", "remove-button");
	removeButton.addEventListener("click", () => {
		files = files.filter((f) => f !== file);
		listItem.remove();
	});

	const convertButton = createConvertButton(file);
	fileActionButtons.appendChild(convertButton);
	fileActionButtons.appendChild(downloadButton);
	fileActionButtons.appendChild(removeButton);

	listItem.appendChild(fileActionButtons);
	document.getElementById("fileList").appendChild(listItem);

	addToActivityList(`File uploaded: ${file.name}`);
}

function createConvertButton(file) {
	const convertButton = document.createElement("button");
	convertButton.textContent = "XML";
	convertButton.classList.add("convert-button");
	convertButton.addEventListener("click", () => {
		convertFileToXml(file);
	});
	return convertButton;
}


function convertFileToXml(file) {

	let formData = new FormData();
	formData.append("file", file);

	// Mostra il loader
	const loader = document.getElementById("loader");
	loader.style.display = "block";

	fetch("uploadFile.action", {
		method: "POST",
		body: formData
	})
		.then(response => {
			if (!response.ok) {
				throw new Error("Errore durante la conversione del file in XML.");
			}
			return response.blob();  // restituisce un oggetto Blob
		})
		.then(blob => {
			// Crea un URL per il Blob
			let fileURL = URL.createObjectURL(blob);

			// Scarica il file XML
			let a = document.createElement("a");
			a.href = fileURL;
			a.download = "output.xml";
			document.body.appendChild(a);
			a.click();
			document.body.removeChild(a);

			// Nasconde il loader
			loader.style.display = "none";
		})
		.catch(error => {
			alert(error.message);
			// Nasconde il loader in caso di errore
			loader.style.display = "none";

			addToActivityList(`Error converting file to XML: ${file.name}`);
		});
}


function createDownloadButton(file) {
	const downloadButton = document.createElement("button");
	downloadButton.textContent = "EXCEL";
	downloadButton.addEventListener("click", () => {
		const fileURL = URL.createObjectURL(file);
		const a = document.createElement("a");
		a.href = fileURL;
		a.download = file.name;
		document.body.appendChild(a);
		a.click();
		document.body.removeChild(a);

	});
	return downloadButton;
}

function startProgressBar() {
	let width = 0;

	const interval = setInterval(() => {
		if (width >= 100) {
			clearInterval(interval);
			// Enable download button when progress reaches 100%
			downloadButton.disabled = false;
		} else {
			width++;
			progress.style.width = width + "%";
			progressPercent.textContent = width + "%";
		}
	}, 10);
}

function resetProgressBar() {
	progress.style.width = "0%";
	progressPercent.textContent = "0%";
}

function showProgressBar() {
	const progressContainer = document.querySelector(".progress-container");
	progressContainer.style.display = "flex";
}

function resetTool() {
	// Reset file
	file = null;
	// Reset input
	input.value = null;
	// Reset progress bar
	resetProgressBar();
	document.querySelector(".progress-container").style.display = "none";
	// Reset drop area
	dropArea.classList.remove("active");
	dragText.textContent = "Drag & Drop";
	// Disable download button
	downloadButton.disabled = true;
	// Remove all files from the file list
	const fileList = document.getElementById("fileList");
	while (fileList.firstChild) {
		fileList.firstChild.remove();
	}
	// Reset files array
	files = [];

	resetContainerColor();

}
resetButton.addEventListener("click", resetTool);

// Funzione per attivare le schede al click
function activateTab(tab) {
	const target = tab.dataset.target;
	const tabContent = document.querySelector(target);

	// Disattiva tutte le altre schede e contenuti delle schede
	const allTabs = document.querySelectorAll('.tab-item');
	const allTabContents = document.querySelectorAll('.tab-content');

	for (let i = 0; i < allTabs.length; i++) {
		allTabs[i].classList.remove('active');
		allTabContents[i].classList.remove('active');
	}

	// Attiva la scheda corrente e il suo contenuto
	tab.classList.add('active');
	tabContent.classList.add('active');
}


// Aggiungi l'event listener per le schede
const tabs = document.querySelectorAll('.tab-item');
for (let i = 0; i < tabs.length; i++) {
	tabs[i].addEventListener('click', function() {
		activateTab(tabs[i]);
	});
}


function displayFile() {
	if (file) {
		dropArea.classList.add("active");

		let fileType = file.type;
		let validExtensions = [
			"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
		];

		if (!validExtensions.includes(fileType)) {
			alert("Formato file non valido. Caricare solo file Excel.");
			dropArea.classList.remove("active");
			resetProgressBar();
			return;
		}

		addFileToList(file);

		startProgressBar();
		showProgressBar();

		let fileReader = new FileReader();

		fileReader.onload = () => {
			let fileURL = fileReader.result;

			if (
				fileType === "application/xml" ||
				fileType === "text/xml"
			) {
				// Gestisce i file XML
				let parser = new DOMParser();
				let xmlDoc = parser.parseFromString(
					fileURL,
					"application/xml"
				);
				// Elabora xmlDoc secondo le necessità
				console.log(xmlDoc);
			} else if (
				fileType ===
				"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			) {
				// Gestisce i file XML
				let workbook = XLSX.read(fileURL, { type: "binary" });
				// Processa i workbook secondo le necessità
				console.log(workbook);
			}
		};

		if (
			fileType ===
			"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
		) {
			fileReader.readAsArrayBuffer(file);
		} else {
			fileReader.readAsDataURL(file);
		}
		downloadButton.addEventListener("click", downloadFile);


	}
}

function downloadFile() {
	if (!file) {
		document.getElementById("error-message").innerHTML =
			"Nessun file da scaricare.";
		return;
	}

	if (
		file.type !==
		"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	) {
		let fileURL = URL.createObjectURL(file);
		let a = document.createElement("a");
		a.href = fileURL;
		a.download = file.name;
		document.body.appendChild(a);
		a.click();
		document.body.removeChild(a);
	} else {
		const errorMessage = document.getElementById("error-message");
		if (errorMessage) {
			errorMessage.remove();
		}
	}
}

function downloadExampleFile() {
	fetch('downloadExample.action')
		.then((response) => {
			if (!response.ok) {
				throw new Error('Errore durante il download del file di esempio');
			}
			return response.blob();
		})
		.then((blob) => {
			const url = URL.createObjectURL(blob);
			const a = document.createElement('a');
			a.href = url;
			a.download = 'FattoriRipartizione.xlsx';
			document.body.appendChild(a);
			a.click();
			document.body.removeChild(a);
		})
		.catch((error) => {
			console.error(`Si è verificato un errore durante il download del file di esempio: ${error}`);
		});
}

exampleButton.addEventListener('click', downloadExampleFile);

// Events
button.onclick = () => {
	input.click();
};

input.addEventListener("change", function() {
	file = this.files[0];
	dropArea.classList.add("active");
	displayFile();
});

dropArea.addEventListener("dragover", (event) => {
	event.preventDefault();
	dropArea.classList.add("active");
	dragText.textContent = "Release to Upload";
});

dropArea.addEventListener("dragleave", () => {
	dropArea.classList.remove("active");
	dragText.textContent = "Drag & Drop";
});

dropArea.addEventListener("drop", (event) => {
	event.preventDefault();
	file = event.dataTransfer.files[0];
	displayFile();
});


document.getElementById("uploadForm").addEventListener("submit", function(event) {
	event.preventDefault();

	if (!file) {
		alert("Nessun file da caricare.");
		return;
	}

	let formData = new FormData();
	formData.append("file", file);

	// Usa 'fetch' invece di 'XMLHttpRequest'
	fetch("uploadFile.action", {
		method: "POST",
		body: formData
	})
		.then(response => {
			if (!response.ok) {
				throw new Error("Errore durante l'elaborazione del file.");
			}
			return response.blob();  // restituisce un oggetto Blob
		})
		.then(blob => {
			// Crea un URL per il Blob
			let fileURL = URL.createObjectURL(blob);
			// Scarica il file
			let a = document.createElement("a");
			a.href = fileURL;
			a.download = "output.xml";
			document.body.appendChild(a);
			a.click();
			document.body.removeChild(a);
			resetProgressBar();
		})
		.catch(error => {
			alert(error.message);
			resetProgressBar();
		});
});

function toggleTheme() {
	// Controlla lo stato del checkbox per il tema del container
	var containerToggle = document.getElementById("container-toggle");
	var container = document.querySelector(".container");

	if (containerToggle.checked) {
		container.classList.add("dark-mode");
	} else {
		container.classList.remove("dark-mode");
	}
}

function changeContainerColor() {
	var container = document.querySelector('.container');
	var colorInput = document.querySelector('#container-color');
	var color = colorInput.value;
	container.style.backgroundColor = color;
}

// Carica le impostazioni salvate dal Local Storage, se presenti
window.addEventListener('DOMContentLoaded', () => {
	const containerToggle = document.getElementById('container-toggle');
	const containerColor = document.getElementById('container-color');

	// Controlla se esistono impostazioni salvate
	if (localStorage.getItem('darkMode')) {
		const darkMode = localStorage.getItem('darkMode') === 'true';
		containerToggle.checked = darkMode;
		toggleTheme();
	}

	if (localStorage.getItem('containerColor')) {
		const color = localStorage.getItem('containerColor');
		containerColor.value = color;
		changeContainerColor();
	}

	// Carica le attività salvate dal Local Storage
	loadSavedActivities();
});

// Salva le impostazioni nel Local Storage al click del bottone "Save Settings"
function saveSettings() {
	const containerToggle = document.getElementById('container-toggle');
	const containerColor = document.getElementById('container-color');

	const darkMode = containerToggle.checked;
	const color = containerColor.value;

	localStorage.setItem('darkMode', darkMode);
	localStorage.setItem('containerColor', color);

	// Save activities to localStorage
	const activityRows = document.querySelectorAll("#activityItems tr");
	const savedActivities = Array.from(activityRows).map(row => row.textContent.trim());
	localStorage.setItem("activities", JSON.stringify(savedActivities));
}

function loadSavedActivities() {
	const savedActivities = JSON.parse(localStorage.getItem("activities")) || [];

	savedActivities.forEach((activity) => {
		addToActivityList(activity);
	});
}

function addToActivityList(activity) {
	const activityRow = document.createElement("tr");
	const activityData = document.createElement("td");
	activityData.textContent = activity;
	activityRow.appendChild(activityData);
	activityItems.appendChild(activityRow);
}

clearActivityButton.addEventListener("click", () => {
	if (confirm("Are you sure you want to clear the activity log?")) {
		// Clear the activity log
		activityItems.innerHTML = "";

		// Clear activities from localStorage
		localStorage.removeItem("activities");
	}
});

function resetContainerColor() {
  const container = document.querySelector(".container");
  const defaultColor = "#ffffff"; // Imposta il colore predefinito del container qui

  // Resetti il colore dell'input di tipo "color" al valore predefinito
  const containerColorInput = document.getElementById("container-color");
  containerColorInput.value = defaultColor;

  // Cambia anche il colore di sfondo del container
  container.style.backgroundColor = defaultColor;
}

