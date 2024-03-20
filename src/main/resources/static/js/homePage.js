let bankAccounts = document.getElementsByClassName("bankAccounts");
let transactions = document.getElementsByClassName("manager-box-amount");
let trans = document.getElementsByClassName("transaction-list");

for(let i of bankAccounts) {
	i.addEventListener("click", ()=> {
		i.submit();
	})
}

for(let i of transactions) {
	i.addEventListener("click", ()=> {
		i.submit();
	})
}

for(let i of trans) {
	i.addEventListener("click", ()=> {
		i.submit();
	})
}