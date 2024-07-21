/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* global welcome */

$(".toggle-password").click(function() {
    $(this).toggleClass("fa-eye fa-eye-slash");
    var input = $($(this).attr("toggle"));
    if (input.attr("type") == "password") {
        input.attr("type", "text");
    } else {
        input.attr("type", "password");
    }
});
const hamburger = document.querySelector(".hamburger-lines");
const navbar = document.querySelector(".navbar");
const nav = document.querySelector(".nav-container");
const links = document.querySelectorAll(".navbar__link");
const header = document.querySelector(".header-admin-page");
const Logout = document.querySelector(".Logout");
function toggleHamburger() {
    navbar.classList.toggle("opened");
    hamburger.classList.toggle("open");
    nav.classList.toggle("opened");
    header.classList.toggle("opened"); // Toggle opened class directly on .header-admin-page
    Logout.classList.toggle("opened");
    links.forEach(link => {
        link.classList.toggle("opened");
        link.querySelector("span").classList.toggle("opened");
    });
}

function handleHamburgerClick() {
    toggleHamburger();
    localStorage.setItem('hamburgerState', hamburger.classList.contains('open') ? 'open' : 'closed');
}

window.addEventListener('load', () => {
    const savedState = localStorage.getItem('hamburgerState');
    if (savedState === 'open') {
        toggleHamburger();
    }
});

hamburger.addEventListener("click", handleHamburgerClick);
feather.replace();

const welcome = document.querySelector(".Welcome-h1");
welcome.addEventListener("click",()=>{
    welcome.classList.toggle("opened-logout");
    Logout.classList.toggle("opened-logout");
});
