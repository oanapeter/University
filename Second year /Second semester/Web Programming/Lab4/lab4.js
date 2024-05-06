const menu = document.getElementById("menu");
const ul = document.createElement("ul");


const makeupProducts = {
  "Face": ["Foundation", "Concealer", "Powder", "Blush", "Bronzer", "Highlighter"],
  "Eyes": ["Eyeshadow Palette", "Eyeliner", "Mascara"],
  "Lips": ["Lipstick", "Lip Gloss", "Lip Liner"],
  "Eyebrows": ["Eyebrow Pencil", "Brow Gel"],
  "Skincare": ["Cleanser", "Moisturizer", "Serum", "Sunscreen", "Face Mask"]
};


Object.keys(makeupProducts).forEach((category) => {
  const li = document.createElement("li");
  const p = document.createElement("p");
  p.textContent = category;

  const subUl = document.createElement("ul");
  const products = makeupProducts[category];

  
  products.forEach(product => {
      const subLi = document.createElement("li");
      const subP = document.createElement("p");
      subP.textContent = product;
      subLi.appendChild(subP);
      subUl.appendChild(subLi);
  });

  p.addEventListener("click", function () {
      if (subUl.style.display === "none") {
          subUl.style.display = "block";
      } else {
          subUl.style.display = "none";
      }
  });

  
  li.appendChild(p);
  li.appendChild(subUl);
  ul.appendChild(li);
});


const menuHeader = document.getElementById("menu-header");
menuHeader.addEventListener("click", function () {
  if(ul.style.display === "none"){
    ul.style.display = "block";
  } else {
    ul.style.display = "none";
  }
});


menu.appendChild(ul);
