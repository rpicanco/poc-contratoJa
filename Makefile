COMMAND=$(eval MS_LIST=$(shell ls | grep -E 'integra-' | cut -d "/" -f1 | sort -u))

define EXECUTE_COMMAND
	${MAKE} ${1} -C ./${2} TAG=${TAG};
endef

start-localstack:
	docker-compose up -d localstack
	
monta-localstack-env:
	sh script/monta-localstack-env.sh
	
build:
	@$(COMMAND)
	@$(foreach microservice,$(MS_LIST),$(call EXECUTE_COMMAND,'build',$(microservice),))
	
start:	
	docker-compose up -d --build integra-rj integra-sc
	
stop:
	docker-compose stop
	
start-mockoon:
	docker-compose up -d mockoon

build-start:
	make start-mockoon
	make build
	make start

destroy:
	make stop
	docker-compose rm -f

show-logs:
	docker-compose logs -f