import { PutEventsCommand } from "@aws-sdk/client-eventbridge";
import { ebClient } from "./eventBridgeClient.js";


async function sendEvent(eventContrato) {

    try {        
        const PutEventsInput = {
            Entries: [
                {
                    Source: "com.contratoja.contrato",
                    DetailType: eventContrato.type,
                    EventBusName: "core",
                    Detail: JSON.stringify(JSON.parse(eventContrato))
                },
              ]   
        }
    
        const result = await ebClient.send(new PutEventsCommand(PutEventsInput));

        console.log("Event enviado para o AWS EventBridge com sucesso!!!");
        console.log(JSON.stringify("EventId:" + result.Entries[0].EventId, null, 2));
    
        return result;
        
    } catch (err) {
        console.log("Error", err);
    }
}

export { sendEvent };