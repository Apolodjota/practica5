var osmUrl = 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
        osmAttrib = '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
        osm = L.tileLayer(osmUrl, {maxZoom: 15, attribution: osmAttrib});

var map = L.map('map').setView([-4.036, -79.201], 15);

L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

L.marker([-3.988510133828141, -79.26901217383768]).addTo(map)
.bindPopup('Antena EERSSA').openPopup();

L.marker([-4.031302566722161, -79.24413565246722]).addTo(map)
.bindPopup('Antenas del Villonaco').openPopup();

L.marker([-3.965923139053335, -79.2264453344924]).addTo(map)
.bindPopup('Antena CNT').openPopup();

L.marker([-3.983904225349028, -79.21133913362912]).addTo(map)
.bindPopup('AntenaCLARO').openPopup();

L.marker([-3.9718312535061706, -79.2038718638842]).addTo(map)
.bindPopup('Antena Jipiro').openPopup();

L.marker([-3.9870722808351733, -79.21339907011048]).addTo(map)
.bindPopup('Antena Centro').openPopup();

L.marker([-4.037074560146147, -79.20824922890706]).addTo(map)
.bindPopup('Antena UNL').openPopup();

L.marker([-4.019151236741871, -79.20327220273718]).addTo(map)
.bindPopup('Antena Parque La Tebaida').openPopup();

L.marker([-4.029339905520914, -79.18979678643025]).addTo(map)
.bindPopup('Antena SurEste').openPopup();

L.marker([-4.041112368164749, -79.19722114074445]).addTo(map)
.bindPopup('Antena 2 puentes').openPopup();
