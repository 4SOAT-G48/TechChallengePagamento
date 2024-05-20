
.PHONY: build run test

build:
	@echo "Building the Docker image..."
	docker-compose build

run:
	@echo "Running the services..."
	docker-compose up

test:
	@echo "Running tests..."
	mvn test
