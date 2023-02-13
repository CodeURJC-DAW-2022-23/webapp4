var btnAbrirPopup2 = document.getElementById('btn-abrir-popup'),
	overlay2 = document.getElementById('overlay'),
	popup2 = document.getElementById('popup'),
	btnCerrarPopup2 = document.getElementById('btn-cerrar-popup');

btnAbrirPopup2.addEventListener('click', function(){
	overlay2.classList.add('active');
	popup2.classList.add('active');
});

btnCerrarPopup2.addEventListener('click', function(e){
	e.preventDefault();
	overlay2.classList.remove('active');
	popup2.classList.remove('active');
});