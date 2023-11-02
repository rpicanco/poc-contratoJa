import { CloudEvent } from "cloudevents";
import { v4 as uuidv4 } from 'uuid';

async function createEventContrato(contrato) {
    
    try {

        const contratoEvent = new CloudEvent({
            id: uuidv4(),
            source: "/contratos/" + contrato.number,        
            specversion: "1.0",
            type: 'contratoJa.contrato.ContratoCriado',
            datacontenttype: "application/json",
            data: contrato
        });
    
        console.log("Formato do CloudEvents");
        console.log(JSON.stringify(contratoEvent, null, 2));
    
        return contratoEvent;
    } catch (err) {
        console.log("Erro na criação do contrato no formato do CloudEvents: ", err);
    }    
}

export { createEventContrato };