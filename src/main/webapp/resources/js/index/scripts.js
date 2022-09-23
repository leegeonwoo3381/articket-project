/*!
* Start Bootstrap - Shop Homepage v5.0.5 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2022 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project

const windowcon = document.querySelector('.windowcon'),
      container = document.querySelector('.container-1'),
        slides = document.querySelectorAll('.container-1 img'),
        slidecounter = slides.length;
let currentIndex = 0;
 
for(i=0; i < slidecounter; i++){
    slides[i].style.left = `${i*33.34}%`;
 
}
 
function calcul(){
    for(i=0; i<slidecounter; i++){
        if(windowcon.offsetHeight < slides[i].offsetHeight){
            windowcon.style.height = slides[i].offsetHeight +"px";
            windowcon.style.width = slides[i].offsetWidth +"px";
        }
    }
}
 
calcul();
var lele = 0;
var i = 0;
 
function moveevent(){
    setInterval(function(){
        lele += 100;
        DelayNode
        container.style.transition ='0.4s'
        container.style.left ="-" + lele +"%";
        i++;
        
        if(i === slidecounter-1){
            setTimeout(function(){
                container.style.transition ='0s'
                lele = 0;
                container.style.left ="-" + lele +"%";
            },201)
            i = 0;
        }
    }, 3500)
}
 
moveevent();