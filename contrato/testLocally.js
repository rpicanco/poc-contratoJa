import { handler } from "./index.js"

const event = {
    resource: "/my/path",
    path: "/my/path",
    httpMethod: "POST",
    body: {
        number: "e2d322bf-77a5-4451-8c7b-336a7ce01107", 
        amount: 500.01,
        installments: 5,
        uf: "RJ",
        customer: {
            firstName: "Roberto",
            lastName: "Picanço"
        },
        seller: "João da Silva"
    }
}

const main = async() => {
    const res = await handler(event);
    console.log(res);
}

main()