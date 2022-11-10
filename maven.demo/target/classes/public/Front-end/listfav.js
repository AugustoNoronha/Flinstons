console.log("iniciou")

window.onload = () => {
  main();
};

function get() {
 var url = "http://localhost:4200/getfav";//Sua URL

var xhttp = new XMLHttpRequest();
xhttp.open("GET", url, false);
xhttp.send();//A execução do script pára aqui até a requisição retornar do servidor
const json = JSON.parse(xhttp.responseText)
console.log("coisa loca");
console.log(json);

return json
}

function getCategoria(catName) {
  document.getElementById('dw-menu').textContent = catName;
  main(catName)
}

function main() {
  const card = document.getElementById("row");

  data = get();

  card.innerHTML = "";

  data.forEach((element) => {
   let id = element.id;
    let modelo = element.modelo;
    let preco = element.preco;
    let img = element.img;
    let link = element.link;
    let desc = element.cat;
    let menor = 0;
   

    var f = preco.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
    container = `
    
    <div class="card green" id="card">
        <img class="image" src="${img}" alt="money" width"120" height"120"/>
        <h4>${modelo}</h4>
        <p>${f}</p>
        <a class="loja" href="${link}" target="_blank"> Acesso a loja </a>
        
        <form action="/fav/delete/${id}" method="get" class="formulario"> 
         <input type="submit" name="sign-in" value="deletar" class="favbtn">
            <div class="user-box">
                <input id="modelo" type="text" name="modelo" required="" value="${modelo}" style="visibility: collapse;">
               
            </div>
            <div class="user-box">
                <input id="preco" type="text" name="preco" required="" value="${preco}" style="visibility: collapse;">
                
            </div>
            <div class="user-box">
                <input id="categoria" type="text" name="categoria" required="" value="${desc}" style="visibility: collapse;">
                
            </div>
            <div class="user-box">
                <input id="link" type="text" name="link" required="" value="${link}" style="visibility: collapse;">
                
            </div>
            <div class="user-box">
                <input id="img" type="text" name="img" required="" value="${img}" style="visibility: collapse;">
                
            </div>
        
            
   
        </form>
    </div>
    `;
    card.innerHTML += container;
  });
}
