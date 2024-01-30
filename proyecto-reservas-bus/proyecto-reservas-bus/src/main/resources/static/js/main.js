const container = document.querySelector('.container')
const btnSignIn = document.querySelector('.btnSign-in')
const btnSignUp = document.querySelector('.btnSign-up')

btnSignIn.addEventListener('click', () => {
    container.classList.add('active')
})

btnSignUp.addEventListener('click', () => {
    container.classList.remove('active')
})
  

 let pswrd = document.getElementById("passwordSignUp");
let togglePswrd = document.getElementById("togglePswrd");

// validation password - let
let lowerCase = document.getElementById("lower");
let upperCase = document.getElementById("upper");
let digit = document.getElementById("number");
let specialChar = document.getElementById("special");
let minLength = document.getElementById("length");

// validation password - function check
function checkPassword(data) {
  // javascript regular expression patern
  const lower = new RegExp("(?=.*[a-z])");
  const upper = new RegExp("(?=.*[A-Z])");
  const number = new RegExp("(?=.*[0-9])");
  const special = new RegExp("(?=.*[!@#$%^&*()+=:;<~>_/?`])");
  const length = new RegExp("(?=.{8,})");

  // lowercase validation check
  if (lower.test(data)) {
    lowerCase.classList.add("valid");
  } else {
    lowerCase.classList.remove("valid");
  }

  // upperrcase validation check
  if (upper.test(data)) {
    upperCase.classList.add("valid");
  } else {
    upperCase.classList.remove("valid");
  }

  // number validation check
  if (number.test(data)) {
    digit.classList.add("valid");
  } else {
    digit.classList.remove("valid");
  }

  // special character validation check
  if (special.test(data)) {
    specialChar.classList.add("valid");
  } else {
    specialChar.classList.remove("valid");
  }

  // minLength validation check
  if (length.test(data)) {
    minLength.classList.add("valid");
  } else {
    minLength.classList.remove("valid");
  }
}

// show and hide password
togglePswrd.onclick = function () {
  if (pswrd.type === "password") {
    pswrd.setAttribute("type", "text");
    togglePswrd.classList.add("hide");
  } else {
    pswrd.setAttribute("type", "password");
    togglePswrd.classList.remove("hide");
  }
};
