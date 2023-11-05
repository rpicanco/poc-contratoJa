## Lambda

# 1 - Criar a policy, role e associar a policy a role para dar permissão para a Lambda publicar no barramento do Event Bridge core

awslocal iam create-policy \
	--policy-name LambdaEventBridgeExecutionPolicy \
	--policy-document file://LambdaEventBridgeExecutionPolicy.json
	
awslocal iam create-role \
	--role-name LambdaEventBridgeExecutionRole \
	--assume-role-policy-document '{"Version": "2012-10-17","Statement": [{ "Effect": "Allow", "Principal": {"Service": "lambda.amazonaws.com"}, "Action": "sts:AssumeRole"}]}'
	
awslocal iam attach-role-policy \
	--role-name LambdaEventBridgeExecutionRole \
	--policy-arn arn:aws:iam::000000000000:policy/LambdaEventBridgeExecutionPolicy

# 2 - Criar a Lambda

awslocal lambda create-function \
  --function-name contrato \
  --runtime nodejs18.x \
  --handler index.handler \
  --memory-size 128 \
  --zip-file fileb://contrato.zip \
  --role arn:aws:iam::000000000000:role/LambdaEventBridgeExecutionRole

## API Gateway
  
# 3 - Criar a API de contrato no AWS APIGateway

restApiId=$(awslocal apigateway create-rest-api --name 'API de contrato' --query 'id' --output text)
	
# 4 - Buscar a API de contrato recém criada para pegar o Parent_ID

parentId=$(awslocal apigateway get-resources --rest-api-id $restApiId --query 'items[0].id' --output text)

# 5 - Criar o recurso e associar a API de contratos

resourceId=$(awslocal apigateway create-resource --rest-api-id $restApiId --parent-id $parentId --path-part "contratos" --query 'id' --output text)
  
# 6 - Criar o método POST para o recurso

awslocal apigateway put-method \
  --rest-api-id $restApiId \
  --resource-id $resourceId \
  --http-method POST \
  --authorization-type "NONE"
  
# 7 - Integra o método POST ao lambda

awslocal apigateway put-integration \
  --rest-api-id $restApiId \
  --resource-id $resourceId \
  --http-method POST \
  --type AWS_PROXY \
  --integration-http-method POST \
  --uri arn:aws:apigateway:us-east-1:lambda:path/2015-03-31/functions/arn:aws:lambda:us-east-1:000000000000:function:contrato/invocations \
  --passthrough-behavior WHEN_NO_MATCH
  
# 8 - Cria o deployment

awslocal apigateway create-deployment \
  --rest-api-id $restApiId \
  --stage-name 'test'
  
## SQS

# 9 - Criar a fila-rj e fila-sc

awslocal sqs create-queue \
	--queue-name fila-rj

awslocal sqs create-queue \
	--queue-name fila-sc

## EventBridge

# 10 - Criar o barramento contratos no EventBridge

awslocal events create-event-bus \
	--name contratos

# 11 - Criar as regras para cada estado

awslocal events put-rule \
	--cli-input-json file://EstadoRJRule.json

awslocal events put-rule \
	--cli-input-json file://EstadoSCRule.json

# 12 - Adicionar as permissões para o EventBridge publicar nas filas SQS

awslocal sqs add-permission \
    --queue-url http://localhost:4566/000000000000/fila-rj \
    --label EstadoRJSendMessage \
    --actions 'SendMessage' \
    --aws-account-ids events.amazonaws.com
	
awslocal sqs add-permission \
    --queue-url http://localhost:4566/000000000000/fila-sc \
    --label EstadoSCSendMessage \
    --actions 'SendMessage' \
    --aws-account-ids events.amazonaws.com
	
# 13 - Adicionar os targets para cada fila SQS

awslocal events put-targets \
	--cli-input-json file://FilaRJSQStarget.json

awslocal events put-targets \
	--cli-input-json file://FilaSCSQStarget.json