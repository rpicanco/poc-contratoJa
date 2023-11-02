import { requestSchema } from "./requestSchemaValidation.js";
import { createEventContrato } from "./createEventContrato.js"
import { sendEvent } from "./sendEvent.js"


export const handler = async(event) => { 
    
    console.log("## Requisição recebida", JSON.stringify(event, null, 2));

    const contratoRequest = JSON.parse(event.body);

    console.log("## Contrato recebido:", JSON.stringify(contratoRequest, null, 2));

    let response, contrato = {};    

    try {
        contrato = await requestSchema.validateAsync(contratoRequest, { abortEarly: false } );        
        console.log('Contrato válido!!!');
        console.log(JSON.stringify(contrato, null, 2));
    }
    catch (err) { 
        response = {
            statusCode: 400,
            body: `Contrato Inválido: "${err}"!!!`
        }
        return response;
    } 
    
    // Cria o contrato no formato CloudEvents  
    const contratoEvent = await createEventContrato(contrato);      
    
    // Publica o evento para o AWS EventBridge
    const sendEventResponse = await sendEvent(contratoEvent);
    
    if(contratoEvent != null && sendEventResponse !== null) {
        response = {
            statusCode: 202,
            body: `Contrato número "${contrato.number}" recebido com sucesso.`,
          };
    } else {
        response = {
            statusCode: 500,
            body: `Erro inesperado. Contactar o administrador`,
          };
    }    

    return response;
}