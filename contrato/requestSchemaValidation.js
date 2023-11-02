import Joi from "joi";

// Validar payload recebido de acordo com os campos obrigatórios comuns para todos os estados: 
    /* {
		"number": "e2d322bf-77a5-4451-8c7b-336a7ce01107", (obrigatório)
		"amount": 5000.01, (obrigatório)
		"installments": 5,
		"uf": "RJ", (obrigatório)
		"customer": {
			"firstName": "Roberto", (obrigatório)
			"lastName": "Picanço"
		},
		"seller": "João da Silva" 
	}*/

const requestSchema = Joi.object ({
    number: Joi.string()
        .guid({ version: ['uuidv4'] })
        .required(), 

    amount: Joi.number()
        .positive()
        .precision(2)   
        .required(),

    installments: Joi.number()
        .positive(),      

    uf: Joi.string()
        .uppercase()        
        .min(2)
        .max(2)
        .required(),

    customer: Joi.object({
        firstName: Joi.string()                   
            .required(),

        lastName: Joi.string()}),
        
    seller: Joi.string()
})

export { requestSchema };