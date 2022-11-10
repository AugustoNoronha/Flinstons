window.onload = () => {
  main('Perfume');
};

function get(url) {
  let request = new XMLHttpRequest();
  request.open("GET", url, false);
  request.send();
  return request.responseText;
}

function getCategoria(catName) {
  document.getElementById('dw-menu').textContent = catName;
  main(catName)
}

function main(initial) {
  const card = document.getElementById("row");

  Baseurl = 'http://localhost:3001/'
  data = get(Baseurl + initial);


  catItem = JSON.parse(data);


  card.innerHTML = "";

  catItem.forEach((element) => {
    let modelo = element.modelo;
    let preco = element.preco;
    let img = element.img;
    let link = element.link;
    let desc = element.desc;
    let menor = 0;
   

    var f = preco.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
    container = `
    
    <div class="card green" id="card">
        <img class="image" src="${img}" alt="money" />
        <h2>${modelo}</h2>
        <p>${f}</p>
        <a class="loja" href="${link}" target="_blank"> Acesso a loja </a>
        
        <input id="modelo" type="text" name="modelo"  type="hidden">
        <input id="preco" type="text" name="preco"  type="hidden">
        <input id="desc" type="text" name="desc"  type="hidden">
        <input id="link" type="text" name="link" type="hidden" >
        <input id="img" type="text" name="img" type="hidden">
        
        

    </div>

    `;
    modeloValue = document.getElementById("modelo");
    modeloValue.value = modelo;
    
    precoValue = document.getElementById("preco");
    precoValue.value = preco;
    
    imgValue = document.getElementById("img");
   	imgValue.value = img;
    
    linkValue = document.getElementById("link");
    linkValue.value = link;
    
    descValue = document.getElementById("desc");
    descValue.value = desc;
    
    card.innerHTML += container;
  });
}
