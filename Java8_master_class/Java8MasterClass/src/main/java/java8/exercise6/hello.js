
var hello = function() {
    print("Hello Nashorn!");
    const randomNumber = Math.floor(Math.random() * 10) + 1;
    console.log(randomNumber);
}

hello();